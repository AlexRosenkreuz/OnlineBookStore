package com.foalex.bookstore.dto.order;

import jakarta.validation.constraints.NotNull;

public record UpdateOrderRequestDto(
        @NotNull(message = "You must specify order status.")
        String status) {
}
