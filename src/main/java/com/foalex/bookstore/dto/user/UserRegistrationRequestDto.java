package com.foalex.bookstore.dto.user;

import com.foalex.bookstore.validation.FieldsValid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;

@FieldsValid(
        field = "password",
        fieldMatch = "verifyPassword",
        message = "Passwords don't match"
)
public record UserRegistrationRequestDto(
        @NotNull(message = "You must specify an email.")
        @Email(message = "Invalid email format.")
        String email,

        @NotNull(message = "You must specify a first name.")
        @Length(
                max = 30,
                message = "First name must not exceed 30 characters."
        )
        String firstName,

        @NotNull(message = "You must specify a last name."
        )
        @Length(
                max = 30,
                message = "Last name must not exceed 30 characters."
        )
        String lastName,

        @NotNull(message = "You must specify a shipping address.")
        @Length(
                max = 100,
                message = "Shipping address must not exceed 100 characters."
        )
        String shippingAddress,

        @NotNull(message = "You must specify a password.")
        @Length(
                min = 6, max = 30,
                message = "Password must be between 6 and 30 characters."
        )
        @Pattern(
                regexp = "^(?=.*[a-zA-Z])(?=.*\\d)[a-zA-Z\\d_-]+$",
                message = "Password must contain at least one digit and letter"
        )
        String password,
        String verifyPassword) {
}
