package com.danilo.ecommerce.dto;

import com.danilo.ecommerce.domain.authority.Authority;
import com.danilo.ecommerce.domain.user.User;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Currency;
import java.util.Locale;
import java.util.Set;
import java.util.stream.Collectors;

public record UserResponseDTO(BigInteger id, String username, String fullName, String phoneNumber, String email,
                              Currency currency, Locale locale, ZoneId timeZone,
                              LocalDateTime createdAt, LocalDateTime lastLogin,
                              int failedLoginAttempts, boolean enabled, Set<String> authorities) {
    public UserResponseDTO(User user) {
        this(
            user.getId(),
            user.getUsername(),
            user.getFullName(),
            user.getPhoneNumber(),
            user.getEmail(),
            user.getPreferences().getCurrency(),
            user.getPreferences().getLocale(),
            user.getPreferences().getTimeZone(),
            user.getCreatedAt(),
            user.getLastLogin(),
            user.getFailedLoginAttempts(),
            user.isEnabled(),
            user.getAuthorities().stream().map(Authority::getAuthority).collect(Collectors.toSet())
        );
    }
}
