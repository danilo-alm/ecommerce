package com.danilo.ecommerce.dto;

import java.time.ZoneId;
import java.util.Currency;
import java.util.Locale;
import java.util.Set;

public record UserRequestDTO(String username, String password, String fullName, boolean enabled, String phoneNumber,
                             String email, Currency currency, Locale locale, ZoneId timeZone, String language,
                             Set<String> authorities) {
}
