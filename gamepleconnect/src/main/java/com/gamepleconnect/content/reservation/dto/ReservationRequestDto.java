package com.gamepleconnect.content.reservation.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
public class ReservationRequestDto {

    @Schema(description = "이메일" , example = "test@test.com")
    @Email()
    @NotBlank()
    private String email;

    @Schema(description = "게임 코드" , example = "1")
    @NotNull()
    private Long gameCode;

    @Schema(description = "언어" , example = "EN")
    @NotBlank()
    private String lang;

    @Schema(description = "디바이스 OS" , example = "AOS")
    private String deviceOs;

    @Schema(description = "디바이스 기종" , example = "iPhone 7")
    private String deviceModel;

    @Schema(description = "마케팅 수신 동의" , example = "true")
    @NotBlank()
    private boolean promotionAgree;
}
