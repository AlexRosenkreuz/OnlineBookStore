package com.foalex.bookstore.controller;

import com.foalex.bookstore.dto.order.CreateOrderRequestDto;
import com.foalex.bookstore.dto.order.OrderDto;
import com.foalex.bookstore.dto.order.OrderItemDto;
import com.foalex.bookstore.dto.order.UpdateOrderRequestDto;
import com.foalex.bookstore.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Order management",
        description = "Endpoints for interacting with order")
@RequiredArgsConstructor
@Validated
@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderService orderService;

    @Operation(
            summary = "Get orders",
            description = "Retrieves all your order."
    )
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('USER')")
    public List<OrderDto> findAll(
            Authentication authentication,
            @ParameterObject @PageableDefault Pageable pageable) {
        return orderService.findAll(authentication, pageable);
    }

    @Operation(
            summary = "Place order",
            description = "Creates an order for shopping cart's items."
    )
    @PostMapping
    @PreAuthorize("hasRole('USER')")
    @ResponseStatus(HttpStatus.CREATED)
    public OrderDto createOrder(
            Authentication authentication,
            @Valid @RequestBody CreateOrderRequestDto requestDto) {
        return orderService.createOrder(authentication, requestDto);
    }

    @Operation(
            summary = "Get an order by id",
            description = "Retrieves your order with specified id."
    )
    @GetMapping("/{id}/items")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('USER')")
    public OrderDto findById(Authentication authentication, @PathVariable @Positive Long id) {
        return orderService.getById(authentication, id);
    }

    @Operation(
            summary = "Get an order item by id",
            description = "Gets an order item with specified id."
    )
    @GetMapping("/{orderId}/items/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('USER')")
    public OrderItemDto findOrderItemById(
            Authentication authentication,
            @PathVariable @Positive Long orderId,
            @PathVariable @Positive Long id) {
        return orderService.getOrderItemById(authentication, orderId, id);
    }

    @Operation(
            summary = "Change order status",
            description = "Changes status of the order with specified id."
    )
    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ADMIN')")
    public OrderDto update(
            @PathVariable @Positive Long id,
            @Valid @RequestBody UpdateOrderRequestDto requestDto) {
        return orderService.update(id, requestDto);
    }
}
