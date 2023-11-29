package com.foalex.bookstore.service.impl;

import com.foalex.bookstore.dto.order.CreateOrderRequestDto;
import com.foalex.bookstore.dto.order.OrderDto;
import com.foalex.bookstore.dto.order.OrderItemDto;
import com.foalex.bookstore.dto.order.UpdateOrderRequestDto;
import com.foalex.bookstore.exception.CreateOrderException;
import com.foalex.bookstore.exception.EntityNotFoundException;
import com.foalex.bookstore.exception.OrderStatusInvalidException;
import com.foalex.bookstore.mapper.OrderItemMapper;
import com.foalex.bookstore.mapper.OrderMapper;
import com.foalex.bookstore.model.Order;
import com.foalex.bookstore.model.OrderItem;
import com.foalex.bookstore.model.ShoppingCart;
import com.foalex.bookstore.repository.OrderItemRepository;
import com.foalex.bookstore.repository.OrderRepository;
import com.foalex.bookstore.repository.ShoppingCartRepository;
import com.foalex.bookstore.service.OrderService;
import java.util.Arrays;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final ShoppingCartRepository shoppingCartRepository;
    private final OrderMapper orderMapper;
    private final OrderItemMapper orderItemMapper;

    @Override
    @Transactional
    public OrderDto createOrder(Authentication authentication, CreateOrderRequestDto requestDto) {
        String email = authentication.getName();
        ShoppingCart cart = shoppingCartRepository
                .findByUserEmail(email)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Shopping cart of user with email %s doesn't exist"
                                .formatted(email))
                );
        if (cart.size() == 0) {
            throw new CreateOrderException("Unable to create order from empty cart");
        }
        Order order = orderMapper.toOrder(cart, requestDto);
        cart.clear();
        return orderMapper.toDto(orderRepository.save(order));
    }

    @Override
    public List<OrderDto> findAll(Authentication authentication, Pageable pageable) {
        return orderRepository.findAllByUserEmail(authentication.getName(), pageable)
                .stream()
                .map(orderMapper::toDto)
                .toList();
    }

    @Override
    public OrderDto getById(Authentication authentication, Long id) {
        Order order = orderRepository.findByIdAndUserEmail(id, authentication.getName())
                .orElseThrow(() -> new EntityNotFoundException(
                        "You don't have an order with id %d".formatted(id))
                );
        return orderMapper.toDto(order);
    }

    @Override
    public OrderItemDto getOrderItemById(Authentication authentication, Long orderId, Long id) {
        OrderItem orderItem = orderItemRepository
                .findByIdAndOrderAndUser(id, orderId, authentication.getName())
                .orElseThrow(() -> new EntityNotFoundException(
                        "Item with id %d in order with id %d doesn't exist"
                                .formatted(id, orderId))
                );
        return orderItemMapper.toDto(orderItem);
    }

    @Override
    public OrderDto update(Long id, UpdateOrderRequestDto requestDto) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Order with id %d doesn't exist".formatted(id))
                );
        try {
            order.setStatus(Order.Status.valueOf(requestDto.status()));
        } catch (IllegalArgumentException exception) {
            throw new OrderStatusInvalidException("""
                    Update operation failed.
                    Invalid order status specified.
                    Must be one of the following: %s"""
                    .formatted(Arrays.toString(Order.Status.values()))
            );
        }
        return orderMapper.toDto(orderRepository.save(order));
    }
}
