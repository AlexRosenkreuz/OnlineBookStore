package com.foalex.bookstore.controller;

import com.foalex.bookstore.dto.user.UserLoginRequestDto;
import com.foalex.bookstore.dto.user.UserLoginResponseDto;
import com.foalex.bookstore.dto.user.UserRegistrationRequestDto;
import com.foalex.bookstore.dto.user.UserResponseDto;
import com.foalex.bookstore.exception.RegistrationException;
import com.foalex.bookstore.security.AuthenticationService;
import com.foalex.bookstore.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Authentication management",
        description = "Endpoints for JWT authentication")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {
    private final UserService userService;
    private final AuthenticationService authenticationService;

    @Operation(
            summary = "Login to the system",
            description = """
                    Login to the system using email and password.
                     In response, the JWT token is returned"""
    )
    @PostMapping("/login")
    public UserLoginResponseDto login(@RequestBody @Valid UserLoginRequestDto request) {
        return authenticationService.authenticate(request);
    }

    @Operation(
            summary = "Register new user to the system",
            description = """
                    Register new user to the system using email, firstName, lastName,
                     shippingAddress and passwords. All fields are validated"""
    )
    @PostMapping("/register")
    public UserResponseDto register(@RequestBody @Valid UserRegistrationRequestDto request)
            throws RegistrationException {
        return userService.register(request);
    }
}
