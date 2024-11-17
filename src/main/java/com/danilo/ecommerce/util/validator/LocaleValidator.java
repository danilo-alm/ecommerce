package com.danilo.ecommerce.util.validator;

import java.util.Locale;
import java.util.MissingResourceException;

public class LocaleValidator {
    public static boolean isValid(Locale locale) {
        try {
            return locale.getISO3Language() != null && locale.getISO3Country() != null;
        } catch (MissingResourceException e) {
            return false;
        }
    }
}
