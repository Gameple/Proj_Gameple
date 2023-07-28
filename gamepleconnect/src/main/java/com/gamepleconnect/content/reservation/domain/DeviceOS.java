package com.gamepleconnect.content.reservation.domain;

public enum DeviceOS {

    AOS("AOS"),
    IOS("IOS");

    private final String value;

    DeviceOS(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
