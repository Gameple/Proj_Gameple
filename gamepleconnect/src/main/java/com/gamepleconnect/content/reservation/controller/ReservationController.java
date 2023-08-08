package com.gamepleconnect.content.reservation.controller;

import com.gamepleconnect.common.response.ApiResponse;
import com.gamepleconnect.content.reservation.request.ReservationRequestDto;
import com.gamepleconnect.content.reservation.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/content")
public class ReservationController {

    private final ReservationService reservationService;

    @PostMapping("/pre-register")
    public ApiResponse preRegister(@RequestBody @Valid ReservationRequestDto requestDto) {
        return reservationService.preRegister(requestDto);
    }
}
