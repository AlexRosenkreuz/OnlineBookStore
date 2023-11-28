package com.foalex.bookstore.dto.order;

import com.foalex.bookstore.model.Order;
import jakarta.validation.constraints.NotNull;

public record UpdateOrderRequestDto(@NotNull Order.Status status) {
}
