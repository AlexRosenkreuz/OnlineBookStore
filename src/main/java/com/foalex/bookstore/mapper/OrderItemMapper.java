package com.foalex.bookstore.mapper;

import com.foalex.bookstore.dto.order.OrderItemDto;
import com.foalex.bookstore.model.CartItem;
import com.foalex.bookstore.model.OrderItem;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
)
public interface OrderItemMapper {
    @Mapping(target = "bookId", source = "book.id")
    OrderItemDto toDto(OrderItem orderItem);

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "deleted", ignore = true),
            @Mapping(target = "order", ignore = true)
    })
    @Mapping(target = "price", source = "book.price")
    OrderItem toOrderItem(CartItem cartItem);
}
