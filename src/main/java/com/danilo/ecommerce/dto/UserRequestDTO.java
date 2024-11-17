package com.danilo.ecommerce.dto;

import java.util.Locale;
import java.util.Set;

public record UserRequestDTO(String username, String password, String fullName, boolean enabled, String phoneNumber,
                             String email, String currency, Locale locale, String timeZone, String language,
                             Set<String> authorities) {
}
