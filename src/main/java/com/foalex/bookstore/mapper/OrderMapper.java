package com.foalex.bookstore.mapper;

import com.foalex.bookstore.dto.order.CreateOrderRequestDto;
import com.foalex.bookstore.dto.order.OrderDto;
import com.foalex.bookstore.model.Order;
import com.foalex.bookstore.model.OrderItem;
import com.foalex.bookstore.model.ShoppingCart;
import java.math.BigDecimal;
import org.mapstruct.AfterMapping;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        uses = OrderItemMapper.class
)
public interface OrderMapper {
    @Mapping(target = "userId", source = "user.id")
    OrderDto toDto(Order order);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "orderItems", source = "cart.cartItems")
    Order toOrder(ShoppingCart cart, CreateOrderRequestDto requestDto);

    @AfterMapping
    default void setOrderToItems(@MappingTarget Order order) {
        order.getOrderItems()
                .forEach(item -> item.setOrder(order));
    }

    @AfterMapping
    default void setTotalPrice(@MappingTarget Order order) {
        order.setTotal(order.getOrderItems().stream()
                .map(this::getTotalItemPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add)
        );
    }

    private BigDecimal getTotalItemPrice(OrderItem orderItem) {
        BigDecimal quantity = BigDecimal.valueOf(orderItem.getQuantity());
        return orderItem.getPrice().multiply(quantity);
    }
}
