package com.foalex.bookstore.service;

import com.foalex.bookstore.dto.order.CreateOrderRequestDto;
import com.foalex.bookstore.dto.order.OrderDto;
import com.foalex.bookstore.dto.order.OrderItemDto;
import com.foalex.bookstore.dto.order.UpdateOrderRequestDto;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;

public interface OrderService {
    OrderDto createOrder(Authentication authentication, CreateOrderRequestDto requestDto);

    List<OrderDto> findAll(Authentication authentication, Pageable pageable);

    OrderDto getById(Authentication authentication, Long id);

    OrderItemDto getOrderItemById(Authentication authentication, Long orderId, Long id);

    OrderDto update(Long id, UpdateOrderRequestDto requestDto);
}
