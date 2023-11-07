package com.foalex.bookstore.service;

import com.foalex.bookstore.dto.user.UserRegistrationRequestDto;
import com.foalex.bookstore.dto.user.UserResponseDto;
import com.foalex.bookstore.exception.RegistrationException;

public interface UserService {
    UserResponseDto register(UserRegistrationRequestDto request) throws RegistrationException;
}
