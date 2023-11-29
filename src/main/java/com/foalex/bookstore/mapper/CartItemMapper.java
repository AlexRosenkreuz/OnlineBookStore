package com.foalex.bookstore.mapper;

import com.foalex.bookstore.dto.shoppingcart.CartItemDto;
import com.foalex.bookstore.dto.shoppingcart.CreateCartItemRequestDto;
import com.foalex.bookstore.model.CartItem;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
)
public interface CartItemMapper {
    @Mapping(source = "book.id", target = "bookId")
    @Mapping(source = "book.title", target = "bookTitle")
    CartItemDto toDto(CartItem cartItem);

    CartItem toCartItem(CreateCartItemRequestDto requestDto);
}
