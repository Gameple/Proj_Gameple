package com.gamepleconnect.common.code;

public enum StatusCode {
    SUCCESS("1"),
    BAD_REQUEST("-1"),
    FORBIDDEN("-2"),
    NOT_FOUND("-3"),
    INTERNAL_SERVER_ERROR("-4"),
    GAME_NOT_FOUND("-5"),
    LAUGUAGE_NOT_FOUND("-6"),
    EMAIL_DUPLICATED("-7");

    private final String code;

    StatusCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
