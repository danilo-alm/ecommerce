package com.danilo.ecommerce.util.validator;

import java.time.ZoneId;
import java.util.HashSet;
import java.util.Set;

public class ZoneIdValidator {
    private static final Set<String> ZONE_IDS = new HashSet<>(ZoneId.getAvailableZoneIds());

    public static boolean isValid(String zoneId) {
        return ZONE_IDS.contains(zoneId);
    }
}
