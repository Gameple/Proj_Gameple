package com.gamepleconnect.promotion.reservation.domain;

import java.util.HashMap;
import java.util.Map;

public enum DeviceOS {

    AOS("AOS"),
    IOS("IOS");

    private final String value;

    private static final Map<String, DeviceOS> enumMap = new HashMap<>();

    static {
        for (DeviceOS os : DeviceOS.values()) {
            enumMap.put(os.getValue(), os);
        }
    }

    DeviceOS(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static DeviceOS valueOfOrNull(String value) {
        return value != null ? enumMap.get(value) : null;
    }
}



