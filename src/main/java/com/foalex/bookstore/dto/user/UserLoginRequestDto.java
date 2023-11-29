package com.foalex.bookstore.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record UserLoginRequestDto(
        @NotNull(message = "You must specify an email.")
        @Email(message = "Invalid email format.")
        String email,
        @NotNull(message = "You must specify a password.")
        String password) {
}
