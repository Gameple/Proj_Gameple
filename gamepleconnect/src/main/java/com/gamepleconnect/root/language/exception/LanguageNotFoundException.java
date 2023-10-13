package com.gamepleconnect.root.language.exception;

import com.gamepleconnect.common.code.StatusCode;

public class LanguageNotFoundException extends RuntimeException {

    private final String statusCode;
    private final String message;

    public LanguageNotFoundException() {
        super(StatusCode.LANGUAGE_NOT_FOUND.getStatusCode());
        this.statusCode = StatusCode.LANGUAGE_NOT_FOUND.getStatusCode();
        this.message = StatusCode.LANGUAGE_NOT_FOUND.getMessage();
    }

    public String getStatusCode() {
        return statusCode;
    }

    public String getCustomMessage() {
        return message;
    }
}
