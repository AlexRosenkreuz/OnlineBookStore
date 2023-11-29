package com.foalex.bookstore.mapper;

import com.foalex.bookstore.dto.user.UserRegistrationRequestDto;
import com.foalex.bookstore.dto.user.UserResponseDto;
import com.foalex.bookstore.model.User;
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
public interface UserMapper {
    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "deleted", ignore = true),
            @Mapping(target = "roles", ignore = true),
            @Mapping(target = "shoppingCart", ignore = true),
            @Mapping(target = "orders", ignore = true),
            @Mapping(target = "authorities", ignore = true)
    })
    User toUser(UserRegistrationRequestDto request);

    UserResponseDto toDto(User user);
}
