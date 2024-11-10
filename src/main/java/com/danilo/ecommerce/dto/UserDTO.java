package com.danilo.ecommerce.dto;

import java.util.Set;

public record UserDTO(String username, String password, String fullName, boolean enabled, String phoneNumber, String email, String currency, String language, Set<String> authorities) {
}
