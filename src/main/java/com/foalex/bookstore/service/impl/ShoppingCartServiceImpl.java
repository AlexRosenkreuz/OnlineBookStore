package com.foalex.bookstore.service.impl;

import com.foalex.bookstore.dto.shoppingcart.CreateCartItemRequestDto;
import com.foalex.bookstore.dto.shoppingcart.ShoppingCartDto;
import com.foalex.bookstore.dto.shoppingcart.UpdateCartItemRequestDto;
import com.foalex.bookstore.exception.EntityNotFoundException;
import com.foalex.bookstore.mapper.CartItemMapper;
import com.foalex.bookstore.mapper.ShoppingCartMapper;
import com.foalex.bookstore.model.Book;
import com.foalex.bookstore.model.CartItem;
import com.foalex.bookstore.model.ShoppingCart;
import com.foalex.bookstore.model.User;
import com.foalex.bookstore.repository.BookRepository;
import com.foalex.bookstore.repository.CartItemRepository;
import com.foalex.bookstore.repository.ShoppingCartRepository;
import com.foalex.bookstore.repository.UserRepository;
import com.foalex.bookstore.service.ShoppingCartService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ShoppingCartServiceImpl implements ShoppingCartService {
    private final UserRepository userRepository;
    private final BookRepository bookRepository;
    private final ShoppingCartRepository shoppingCartRepository;
    private final CartItemRepository cartItemRepository;
    private final ShoppingCartMapper shoppingCartMapper;
    private final CartItemMapper cartItemMapper;

    @Override
    public ShoppingCartDto getUserCart(Authentication authentication) {
        ShoppingCart cart = getUserShoppingCart(authentication);
        return shoppingCartMapper.toDto(cart);
    }

    @Override
    @Transactional
    public ShoppingCartDto addCartItem(
            Authentication authentication,
            CreateCartItemRequestDto requestDto
    ) {
        ShoppingCart cart = getUserShoppingCart(authentication);
        cartItemRepository.findByShoppingCartAndBookId(cart, requestDto.bookId())
                .ifPresentOrElse(
                        ci -> ci.setQuantity(ci.getQuantity() + requestDto.quantity()),
                        () -> createCartItem(requestDto, cart)
                );
        return shoppingCartMapper.toDto(cart);
    }

    @Override
    @Transactional
    public ShoppingCartDto updateCartItem(
            Authentication authentication,
            Long id,
            UpdateCartItemRequestDto requestDto
    ) {
        ShoppingCart cart = getUserShoppingCart(authentication);
        CartItem cartItem = findCartItemByCartAndId(cart, id);
        cartItem.setQuantity(requestDto.quantity());
        return shoppingCartMapper.toDto(cart);
    }

    @Override
    @Transactional
    public ShoppingCartDto deleteCartItem(Authentication authentication, Long id) {
        ShoppingCart cart = getUserShoppingCart(authentication);
        CartItem cartItem = findCartItemByCartAndId(cart, id);
        cart.removeCartItem(cartItem);
        return shoppingCartMapper.toDto(cart);
    }

    private ShoppingCart getUserShoppingCart(Authentication authentication) {
        return shoppingCartRepository.findByUserEmail(authentication.getName())
                .orElseGet(() -> createShoppingCart((User) authentication.getPrincipal()));
    }

    private ShoppingCart createShoppingCart(User user) {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setUser(userRepository.getReferenceById(user.getId()));
        return shoppingCartRepository.save(shoppingCart);
    }

    private void createCartItem(CreateCartItemRequestDto requestDto, ShoppingCart cart) {
        CartItem cartItem = cartItemMapper.toCartItem(requestDto);
        cart.addCartItem(cartItem);
        cartItem.setBook(bookRepository.getReferenceById(requestDto.bookId()));
        cartItemRepository.save(cartItem);
    }

    private CartItem findCartItemByCartAndId(ShoppingCart cart, Long id) {
        return cartItemRepository.findByShoppingCartAndId(cart, id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Can't find cart item with id=%d in user's cart"
                                .formatted(id))
                );
    }

    private Book findBookById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Book with id %d doesn't exist".formatted(id))
                );
    }
}
