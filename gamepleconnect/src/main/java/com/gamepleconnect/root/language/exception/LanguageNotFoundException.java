package com.gamepleconnect.root.language.exception;

import com.gamepleconnect.common.code.StatusCode;

public class LanguageNotFoundException extends RuntimeException {

    private static final String STATUS_CODE = StatusCode.LAUGUAGE_NOT_FOUND.getCode();

    public LanguageNotFoundException() {super(STATUS_CODE);}
}
