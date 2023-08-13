package com.gamepleconnect.root.game.exception;

import com.gamepleconnect.common.code.StatusCode;

public class GameNotFoundException extends RuntimeException {

    private static final String STATUS_CODE = StatusCode.GAME_NOT_FOUND.getCode();

    public GameNotFoundException() {super(STATUS_CODE);}
}
