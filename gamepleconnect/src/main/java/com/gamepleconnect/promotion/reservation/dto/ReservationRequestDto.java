package com.gamepleconnect.promotion.reservation.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
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

    @Schema(description = "국가" , example = "KR")
    @NotBlank()
    private String region;

    @Schema(description = "디바이스 OS" , example = "AOS")
    private String deviceOs;

    @Schema(description = "디바이스 기종" , example = "iPhone 7")
    private String deviceModel;

    @Schema(description = "마케팅 수신 동의" , example = "true")
    @NotNull()
    private boolean promotionAgree;

    @Builder
    public ReservationRequestDto(String email, Long gameCode, String region, String deviceOs, String deviceModel, boolean promotionAgree) {
        this.email = email;
        this.gameCode = gameCode;
        this.region = region;
        this.deviceOs = deviceOs;
        this.deviceModel = deviceModel;
        this.promotionAgree = promotionAgree;
    }
}
