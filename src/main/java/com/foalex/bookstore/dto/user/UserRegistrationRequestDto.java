package com.foalex.bookstore.dto.user;

import com.foalex.bookstore.validation.FieldsValid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@FieldsValid(
        field = "password",
        fieldMatch = "verifyPassword",
        message = "Passwords don't match"
)
public record UserRegistrationRequestDto(
        @NotNull @Email
        String email,
        @NotNull @Size(max = 32)
        String firstName,
        @NotNull @Size(max = 32)
        String lastName,
        @NotNull @Size(max = 128)
        String shippingAddress,
        @NotNull @Size(min = 4, max = 32)
        @Pattern(
                regexp = "^(?=.*[a-zA-Z])(?=.*\\d)[a-zA-Z\\d_-]+$",
                message = "Password must contain at least one digit and letter"
        )
        String password,
        String verifyPassword) {
}
