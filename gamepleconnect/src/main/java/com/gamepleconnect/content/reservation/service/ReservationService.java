package com.gamepleconnect.content.reservation.service;

import com.gamepleconnect.common.code.StatusCode;
import com.gamepleconnect.common.response.ApiResponseDto;
import com.gamepleconnect.common.security.AES256;
import com.gamepleconnect.common.util.CommonUtil;
import com.gamepleconnect.content.reservation.domain.DeviceOS;
import com.gamepleconnect.content.reservation.domain.Reservation;
import com.gamepleconnect.content.reservation.exception.DuplicatedEmailException;
import com.gamepleconnect.content.reservation.repository.ReservationRepository;
import com.gamepleconnect.content.reservation.dto.ReservationRequestDto;
import com.gamepleconnect.root.game.domain.Game;
import com.gamepleconnect.root.game.exception.GameNotFoundException;
import com.gamepleconnect.root.game.repository.GameRepository;
import com.gamepleconnect.root.language.domain.Language;
import com.gamepleconnect.root.language.exception.LanguageNotFoundException;
import com.gamepleconnect.root.language.repository.LanguageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReservationService {

    private final ReservationRepository reservationRepository;

    private final GameRepository gameRepository;

    private final LanguageRepository languageRepository;

    private final AES256 aes256;

    public ApiResponseDto preRegister(ReservationRequestDto requestDto) throws Exception {

        Game game = gameRepository.findByGameCode(requestDto.getGameCode())
                .orElseThrow(GameNotFoundException::new);

        Language language = languageRepository.findByLangAlias(requestDto.getLang().toUpperCase())
                .orElseThrow(LanguageNotFoundException::new);

        if(reservationRepository.existsByEmail(aes256.encrypt(requestDto.getEmail()), game)) {
            throw new DuplicatedEmailException();
        }

        Reservation reservation = Reservation.builder()
                .email(aes256.encrypt(requestDto.getEmail()))
                .createdIp(aes256.encrypt(CommonUtil.getIp()))
                .deviceOS(DeviceOS.valueOfOrNull(requestDto.getDeviceOs()))
                .deviceModel(requestDto.getDeviceModel())
                .promotionAgree(requestDto.isPromotionAgree())
                .game(game)
                .language(language)
                .build();

        reservationRepository.save(reservation);

        return ApiResponseDto.builder()
                .statusCode(StatusCode.SUCCESS.getCode())
                .data(requestDto)
                .build();
    }
}
