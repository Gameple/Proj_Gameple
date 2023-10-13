package com.gamepleconnect.root.game.exception;

import com.gamepleconnect.common.code.StatusCode;

public class GameNotFoundException extends RuntimeException {

    private final String statusCode;
    private final String message;

    public GameNotFoundException() {
        super(StatusCode.GAME_NOT_FOUND.getStatusCode());
        this.statusCode = StatusCode.GAME_NOT_FOUND.getStatusCode();
        this.message = StatusCode.GAME_NOT_FOUND.getMessage();
    }

    public String getStatusCode() {
        return statusCode;
    }

    public String getCustomMessage() {
        return message;
    }
}
