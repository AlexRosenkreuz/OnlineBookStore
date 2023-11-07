package com.foalex.bookstore.service.impl;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import com.foalex.bookstore.dto.user.UserRegistrationRequestDto;
import com.foalex.bookstore.dto.user.UserResponseDto;
import com.foalex.bookstore.exception.RegistrationException;
import com.foalex.bookstore.mapper.UserMapper;
import com.foalex.bookstore.model.Role;
import com.foalex.bookstore.model.RoleName;
import com.foalex.bookstore.model.User;
import com.foalex.bookstore.repository.RoleRepository;
import com.foalex.bookstore.repository.UserRepository;
import com.foalex.bookstore.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository repository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserResponseDto register(UserRegistrationRequestDto request)
            throws RegistrationException {
        if (userRepository.findByEmail(request.email()).isPresent()) {
            throw new RegistrationException("Registration was failed");
        }
        User user = userMapper.toUser(request);
        user.setPassword(passwordEncoder.encode(request.password()));
        user.setRoles(getDefaultRoles());
        return userMapper.toDto(userRepository.save(user));
    }

    private Set<Role> getDefaultRoles() {
        return new HashSet<>(Collections.singletonList(
                repository.findByName(RoleName.ROLE_USER)
        ));
    }
}
