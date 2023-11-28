package com.foalex.bookstore.service.impl;

import com.foalex.bookstore.dto.shoppingcart.CartItemDto;
import com.foalex.bookstore.dto.shoppingcart.CreateCartItemRequestDto;
import com.foalex.bookstore.dto.shoppingcart.ShoppingCartDto;
import com.foalex.bookstore.dto.shoppingcart.UpdateCartItemRequestDto;
import com.foalex.bookstore.exception.EntityAlreadyExistsException;
import com.foalex.bookstore.exception.EntityNotFoundException;
import com.foalex.bookstore.mapper.CartItemMapper;
import com.foalex.bookstore.mapper.ShoppingCartMapper;
import com.foalex.bookstore.model.Book;
import com.foalex.bookstore.model.CartItem;
import com.foalex.bookstore.model.ShoppingCart;
import com.foalex.bookstore.repository.BookRepository;
import com.foalex.bookstore.repository.CartItemRepository;
import com.foalex.bookstore.repository.ShoppingCartRepository;
import com.foalex.bookstore.service.ShoppingCartService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShoppingCartServiceImpl implements ShoppingCartService {
    private final BookRepository bookRepository;
    private final ShoppingCartRepository shoppingCartRepository;
    private final CartItemRepository cartItemRepository;
    private final ShoppingCartMapper shoppingCartMapper;
    private final CartItemMapper cartItemMapper;

    @Override
    public ShoppingCartDto getUserCart(Authentication authentication) {
        String username = authentication.getName();
        ShoppingCart cart = shoppingCartRepository.findByUserEmailWithCartItems(username);
        return shoppingCartMapper.toDto(cart);
    }

    @Override
    public CartItemDto addCartItem(
            Authentication authentication, CreateCartItemRequestDto requestDto) {
        String email = authentication.getName();
        Long bookId = requestDto.bookId();

        ShoppingCart cart = shoppingCartRepository.findByUserEmail(email);
        checkIfCartItemExists(cart, bookId);

        CartItem cartItem = cartItemMapper.toCartItem(requestDto);
        cartItem.setShoppingCart(cart);
        cartItem.setBook(findBookById(bookId));

        return cartItemMapper.toDto(cartItemRepository.save(cartItem));
    }

    @Override
    public CartItemDto updateCartItem(Long id, UpdateCartItemRequestDto requestDto) {
        CartItem cartItem = findCartItemById(id);
        cartItem.setQuantity(requestDto.quantity());
        return cartItemMapper.toDto(cartItemRepository.save(cartItem));
    }

    @Override
    public void deleteCartItem(Long id) {
        cartItemRepository.delete(findCartItemById(id));
    }

    private void checkIfCartItemExists(ShoppingCart cart, Long bookId) {
        if (cartItemRepository.existsByShoppingCartAndBookId(cart, bookId)) {
            throw new EntityAlreadyExistsException(
                    "Cart item for book with id %d is already in your cart"
                            .formatted(bookId)
            );
        }
    }

    private CartItem findCartItemById(Long id) {
        return cartItemRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Cart item with id %d doesn't exist".formatted(id))
                );
    }

    private Book findBookById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Book with id %d doesn't exist".formatted(id))
                );
    }
}
