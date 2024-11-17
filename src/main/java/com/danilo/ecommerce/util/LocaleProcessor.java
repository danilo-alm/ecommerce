package com.danilo.ecommerce.util;

import java.util.Locale;
import java.util.MissingResourceException;

public class LocaleProcessor {
    public static Locale parseLocale(String locale) {
        String[] parts = locale.split("_");
        return switch (parts.length) {
            case 3 -> Locale.of(parts[0], parts[1], parts[2]);
            case 2 -> Locale.of(parts[0], parts[1]);
            case 1 -> Locale.of(parts[0]);
            default -> throw new IllegalArgumentException("Invalid locale: " + locale);
        };
    }

    public static boolean isValid(Locale locale) {
        try {
            return locale.getISO3Language() != null && locale.getISO3Country() != null;
        } catch (MissingResourceException e) {
            return false;
        }
    }
}
