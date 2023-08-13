package com.gamepleconnect.content.reservation.controller;

import com.gamepleconnect.common.response.ApiResponseDto;
import com.gamepleconnect.content.reservation.dto.ReservationRequestDto;
import com.gamepleconnect.content.reservation.service.ReservationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Tag(name = "Reservation", description = "유저 컨텐츠 - 사전예약 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/content")
public class ReservationController {

    private final ReservationService reservationService;

    @Operation(summary = "사전예약 등록 API", description = "사전예약 정보를 등록하는 API입니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "등록 성공 케이스", content = @Content(schema = @Schema(implementation = ApiResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "예외 발생 케이스", content = @Content(schema = @Schema(implementation = ApiResponseDto.class))),
    })
    @PostMapping("/pre-register")
    public ApiResponseDto preRegister(@RequestBody @Valid ReservationRequestDto requestDto) throws Exception {
        return reservationService.preRegister(requestDto);
    }
}
