package com.gamepleconnect.user.exception;

import com.gamepleconnect.common.code.StatusCode;

public class DuplicatedUserNameException extends RuntimeException {

    private final String statusCode;
    private final String message;

    public DuplicatedUserNameException() {
        super(StatusCode.USERNAME_DUPLICATED.getStatusCode());
        this.statusCode = StatusCode.USERNAME_DUPLICATED.getStatusCode();
        this.message = StatusCode.USERNAME_DUPLICATED.getMessage();
    }

    public String getStatusCode() {
        return statusCode;
    }

    public String getCustomMessage() {
        return message;
    }
}
