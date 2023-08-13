package com.gamepleconnect.content.reservation.exception;

import com.gamepleconnect.common.code.StatusCode;

public class DuplicatedEmailException extends RuntimeException {

    private static final String STATUS_CODE = StatusCode.EMAIL_DUPLICATED.getCode();

    public DuplicatedEmailException() {super(STATUS_CODE);}
}
