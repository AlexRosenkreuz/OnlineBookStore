package com.foalex.bookstore.dto.order;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.foalex.bookstore.model.Order;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record OrderDto(
        Long id,
        Long userId,
        List<OrderItemDto> orderItems,
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
        LocalDateTime orderDate,
        BigDecimal total,
        Order.Status status) {
}
