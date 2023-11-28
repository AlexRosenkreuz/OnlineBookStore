package com.foalex.bookstore.dto.shoppingcart;

import java.util.List;

public record ShoppingCartDto(
        Long id,
        List<CartItemDto> cartItems) {
}
