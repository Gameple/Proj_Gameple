package com.gamepleconnect.common.code;

public enum StatusCode {
    SUCCESS("1", "OK"),
    BAD_REQUEST("-1", "BAD_REQUEST"),
    FORBIDDEN("-2", "FORBIDDEN"),
    NOT_FOUND("-3", "NOT_FOUND"),
    INTERNAL_SERVER_ERROR("-4", "INTERNAL_SERVER_ERROR"),
    GAME_NOT_FOUND("-5", "GAME_NOT_FOUND"),
    LANGUAGE_NOT_FOUND("-6", "LANGUAGE_NOT_FOUND"),
    EMAIL_DUPLICATED("-7", "ALREADY_EXISTS_EMAIL");

    private final String statusCode;
    private final String message;

    StatusCode(String statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public String getMessage() {
        return message;
    }
}
