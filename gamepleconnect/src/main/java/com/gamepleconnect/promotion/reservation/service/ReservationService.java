package com.gamepleconnect.promotion.reservation.service;

import com.gamepleconnect.common.code.StatusCode;
import com.gamepleconnect.common.response.ApiResponse;
import com.gamepleconnect.common.security.AES256;
import com.gamepleconnect.common.util.CommonUtil;
import com.gamepleconnect.promotion.reservation.domain.DeviceOS;
import com.gamepleconnect.promotion.reservation.domain.Reservation;
import com.gamepleconnect.exception.common.DuplicatedEmailException;
import com.gamepleconnect.promotion.reservation.repository.ReservationRepository;
import com.gamepleconnect.promotion.reservation.dto.request.ReservationRequest;
import com.gamepleconnect.root.game.domain.Game;
import com.gamepleconnect.root.game.exception.GameNotFoundException;
import com.gamepleconnect.root.game.repository.GameRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReservationService {

    private final ReservationRepository reservationRepository;

    private final GameRepository gameRepository;

    @Transactional
    public ApiResponse preRegister(ReservationRequest requestDto) throws Exception {

        Game game = gameRepository.findByGameCode(requestDto.getGameCode())
                .orElseThrow(GameNotFoundException::new);

        if(reservationRepository.existsByEmail(AES256.encrypt(requestDto.getEmail()), game)) {
            throw new DuplicatedEmailException();
        }

        Reservation reservation = Reservation.builder()
                .email(AES256.encrypt(requestDto.getEmail()))
                .createdIp(AES256.encrypt(CommonUtil.getIp()))
                .deviceOS(DeviceOS.valueOfOrNull(requestDto.getDeviceOs()))
                .deviceModel(requestDto.getDeviceModel())
                .promotionAgree(requestDto.isPromotionAgree())
                .game(game)
                .region(requestDto.getRegion())
                .build();

        reservationRepository.save(reservation);

        log.info("RESERVATION INFO SAVE : {}", reservation.toString());

        return ApiResponse.builder()
                .statusCode(StatusCode.SUCCESS.getStatusCode())
                .message(StatusCode.SUCCESS.getMessage())
                .data(null)
                .build();
    }
}
