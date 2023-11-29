package com.foalex.bookstore.dto.shoppingcart;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record UpdateCartItemRequestDto(
        @NotNull(message = "You must specify a quantity.")
        @Positive(message = "Quantity must be a positive number.")
        Integer quantity) {
}
