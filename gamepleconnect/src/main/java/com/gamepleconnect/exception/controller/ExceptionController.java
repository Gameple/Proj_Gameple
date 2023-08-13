package com.gamepleconnect.exception.controller;

import com.gamepleconnect.common.code.StatusCode;
import com.gamepleconnect.common.response.ApiResponseDto;
import com.gamepleconnect.content.reservation.exception.DuplicatedEmailException;
import com.gamepleconnect.root.game.exception.GameNotFoundException;
import com.gamepleconnect.root.language.exception.LanguageNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionController {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ApiResponseDto invaildRequestHandler(MethodArgumentNotValidException e) {
        return ApiResponseDto.builder()
                .statusCode(StatusCode.BAD_REQUEST.getCode())
                .data(null)
                .build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(GameNotFoundException.class)
    @ResponseBody
    public ApiResponseDto invaildRequestHandler(GameNotFoundException e) {
        return ApiResponseDto.builder()
                .statusCode(e.getMessage())
                .data(null)
                .build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(LanguageNotFoundException.class)
    @ResponseBody
    public ApiResponseDto invaildRequestHandler(LanguageNotFoundException e) {
        return ApiResponseDto.builder()
                .statusCode(e.getMessage())
                .data(null)
                .build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(DuplicatedEmailException.class)
    @ResponseBody
    public ApiResponseDto invaildRequestHandler(DuplicatedEmailException e) {
        return ApiResponseDto.builder()
                .statusCode(e.getMessage())
                .data(null)
                .build();
    }
}
