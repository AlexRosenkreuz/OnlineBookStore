package com.foalex.bookstore.service;

import com.foalex.bookstore.dto.shoppingcart.CreateCartItemRequestDto;
import com.foalex.bookstore.dto.shoppingcart.ShoppingCartDto;
import com.foalex.bookstore.dto.shoppingcart.UpdateCartItemRequestDto;
import org.springframework.security.core.Authentication;

public interface ShoppingCartService {
    ShoppingCartDto getUserCart(Authentication authentication);

    ShoppingCartDto addCartItem(
            Authentication authentication,
            CreateCartItemRequestDto requestDto
    );

    ShoppingCartDto updateCartItem(
            Authentication authentication,
            Long id,
            UpdateCartItemRequestDto requestDto
    );

    ShoppingCartDto deleteCartItem(Authentication authentication, Long id);
}
