package com.gamepleconnect.promotion.reservation.controller;

import com.gamepleconnect.common.response.ApiResponse;
import com.gamepleconnect.promotion.reservation.dto.request.ReservationRequest;
import com.gamepleconnect.promotion.reservation.service.ReservationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Tag(name = "Reservation", description = "프로모션 - 사전예약 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/promotion")
public class ReservationController {

    private final ReservationService reservationService;

    @Operation(summary = "사전예약 등록 API", description = "사전예약 정보를 등록하는 API")
    @PostMapping("/pre-register")
    public ApiResponse preRegister(@RequestBody @Valid ReservationRequest requestDto) throws Exception {
        return reservationService.preRegister(requestDto);
    }
}
