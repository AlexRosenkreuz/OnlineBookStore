package com.foalex.bookstore.dto.user;

import com.foalex.bookstore.validation.FieldsValid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@FieldsValid(
        field = "password",
        fieldMatch = "verifyPassword",
        message = "Password mismatch"
)
public record UserRegistrationRequestDto(
        @NotNull @Email
        String email,
        @NotNull @Size(min = 2, max = 32)
        String firstName,
        @NotNull @Size(min = 2, max = 32)
        String lastName,
        @NotNull @Size(max = 128)
        String shippingAddress,
        @NotNull @Size(min = 10, max = 20)
        @Pattern(
                regexp = "^(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d_-]{10,20}$",
                message = "Password must:\n " +
                        "1. Be 10 to 20 characters long;\n" +
                        "2. Only contain alphabetical characters, digits, underscores (_), and hyphens (-);\n" +
                        "3. Contain at least one uppercase letter;\n" +
                        "4. Contain at least one digit."
        )
        String password,
        String verifyPassword) {
}
