package com.foalex.bookstore.dto.order;

import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public record CreateOrderRequestDto(
        @NotNull(message = "You must specify a shipping address.")
        @Length(
                max = 100,
                message = "Shipping address must not exceed 100 characters."
        )
        String shippingAddress) {
}
