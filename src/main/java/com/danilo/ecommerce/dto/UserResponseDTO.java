package com.danilo.ecommerce.dto;

import com.danilo.ecommerce.domain.authority.Authority;
import com.danilo.ecommerce.domain.user.User;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

public record UserResponseDTO(String username, String fullName, String phoneNumber, String email, String currency, String language, LocalDateTime createdAt, LocalDateTime lastLogin, int failedLoginAttempts, boolean enabled, Set<String> authorities) {
    public UserResponseDTO(User user) {
        this(
            user.getUsername(),
            user.getFullName(),
            user.getPhoneNumber(),
            user.getEmail(),
            user.getCurrency(),
            user.getLanguage(),
            user.getCreatedAt(),
            user.getLastLogin(),
            user.getFailedLoginAttempts(),
            user.isEnabled(),
            user.getAuthorities().stream().map(Authority::getAuthority).collect(Collectors.toSet())
        );
    }
}
