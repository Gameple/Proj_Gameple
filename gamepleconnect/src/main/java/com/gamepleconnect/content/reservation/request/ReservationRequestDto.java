package com.gamepleconnect.content.reservation.request;

import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
public class ReservationRequestDto {

    @Email()
    @NotBlank()
    private String email;

    @NotNull()
    private Long gameCode;

    @NotBlank()
    private String lang;

    private String deviceOs;

    private String deviceModel;

    @NotBlank()
    private boolean promotionAgree;
}
