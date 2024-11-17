package com.danilo.ecommerce.util.validator;

import java.util.Currency;
import java.util.Set;
import java.util.stream.Collectors;

public class CurrencyValidator {
    private static final Set<String> CURRENCIES = Currency.getAvailableCurrencies()
        .stream()
        .map(Currency::getCurrencyCode)
        .collect(Collectors.toSet());

    public static boolean isValid(String currencyCode) {
        return CURRENCIES.contains(currencyCode);
    }
}
