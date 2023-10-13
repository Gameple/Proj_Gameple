package com.gamepleconnect.exception.controller;

import com.gamepleconnect.common.code.StatusCode;
import com.gamepleconnect.common.response.ApiResponse;
import com.gamepleconnect.promotion.reservation.exception.DuplicatedEmailException;
import com.gamepleconnect.root.game.exception.GameNotFoundException;
import com.gamepleconnect.root.language.exception.LanguageNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionController {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BindException.class)
    @ResponseBody
    public ApiResponse invalidRequestHandler(BindException e) {
        return ApiResponse.builder()
                .statusCode(StatusCode.BAD_REQUEST.getStatusCode())
                .message(StatusCode.BAD_REQUEST.getMessage())
                .data(null)
                .build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ApiResponse invaildRequestHandler(MethodArgumentNotValidException e) {
        return ApiResponse.builder()
                .statusCode(StatusCode.BAD_REQUEST.getStatusCode())
                .message(StatusCode.BAD_REQUEST.getMessage())
                .data(null)
                .build();
    }

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(GameNotFoundException.class)
    @ResponseBody
    public ApiResponse invaildRequestHandler(GameNotFoundException e) {
        return ApiResponse.builder()
                .statusCode(e.getMessage())
                .message(e.getCustomMessage())
                .data(null)
                .build();
    }

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(LanguageNotFoundException.class)
    @ResponseBody
    public ApiResponse invaildRequestHandler(LanguageNotFoundException e) {
        return ApiResponse.builder()
                .statusCode(e.getMessage())
                .message(e.getCustomMessage())
                .data(null)
                .build();
    }

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(DuplicatedEmailException.class)
    @ResponseBody
    public ApiResponse invaildRequestHandler(DuplicatedEmailException e) {
        return ApiResponse.builder()
                .statusCode(e.getMessage())
                .message(e.getCustomMessage())
                .data(null)
                .build();
    }
}
