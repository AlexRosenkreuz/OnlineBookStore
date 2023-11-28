package com.foalex.bookstore.service;

import com.foalex.bookstore.dto.shoppingcart.CartItemDto;
import com.foalex.bookstore.dto.shoppingcart.CreateCartItemRequestDto;
import com.foalex.bookstore.dto.shoppingcart.ShoppingCartDto;
import com.foalex.bookstore.dto.shoppingcart.UpdateCartItemRequestDto;
import org.springframework.security.core.Authentication;

public interface ShoppingCartService {
    ShoppingCartDto getUserCart(Authentication authentication);

    CartItemDto addCartItem(Authentication authentication, CreateCartItemRequestDto requestDto);

    CartItemDto updateCartItem(Long id, UpdateCartItemRequestDto requestDto);

    void deleteCartItem(Long id);
}
