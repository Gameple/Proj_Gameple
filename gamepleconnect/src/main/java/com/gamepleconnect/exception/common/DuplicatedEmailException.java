package com.gamepleconnect.exception.common;

import com.gamepleconnect.common.code.StatusCode;

public class DuplicatedEmailException extends RuntimeException {

    private final String statusCode;
    private final String message;

    public DuplicatedEmailException() {
        super(StatusCode.EMAIL_DUPLICATED.getStatusCode());
        this.statusCode = StatusCode.EMAIL_DUPLICATED.getStatusCode();
        this.message = StatusCode.EMAIL_DUPLICATED.getMessage();
    }

    public String getStatusCode() {
        return statusCode;
    }

    public String getCustomMessage() {
        return message;
    }
}
