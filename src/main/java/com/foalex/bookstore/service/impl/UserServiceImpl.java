package com.foalex.bookstore.service.impl;

import com.foalex.bookstore.dto.user.UserRegistrationRequestDto;
import com.foalex.bookstore.dto.user.UserResponseDto;
import com.foalex.bookstore.exception.RegistrationException;
import com.foalex.bookstore.mapper.UserMapper;
import com.foalex.bookstore.model.Role;
import com.foalex.bookstore.model.User;
import com.foalex.bookstore.repository.RoleRepository;
import com.foalex.bookstore.repository.UserRepository;
import com.foalex.bookstore.service.UserService;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserResponseDto register(UserRegistrationRequestDto requestDto)
            throws RegistrationException {
        if (userRepository.existsByEmail(requestDto.email())) {
            throw new RegistrationException("""
                    Registration was failed.
                    User with this email already exists.""");
        }
        User user = userMapper.toUser(requestDto);
        user.setPassword(passwordEncoder.encode(requestDto.password()));
        user.setRoles(getDefaultRoles());
        return userMapper.toDto(userRepository.save(user));
    }

    private Set<Role> getDefaultRoles() {
        return Set.of(roleRepository.findByName(Role.Name.USER));
    }
}
