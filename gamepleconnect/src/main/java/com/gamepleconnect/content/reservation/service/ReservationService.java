package com.gamepleconnect.content.reservation.service;

import com.gamepleconnect.common.response.ApiResponse;
import com.gamepleconnect.common.util.CommonUtil;
import com.gamepleconnect.content.reservation.domain.DeviceOS;
import com.gamepleconnect.content.reservation.domain.Reservation;
import com.gamepleconnect.content.reservation.repository.ReservationRepository;
import com.gamepleconnect.content.reservation.request.ReservationRequestDto;
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

    public ApiResponse preRegister(ReservationRequestDto requestDto) {

        Game game = gameRepository.findByGameCode(requestDto.getGameCode())
                .orElseThrow(GameNotFoundException::new);

        Language language = languageRepository.findByLangAlias(requestDto.getLang())
                .orElseThrow(LanguageNotFoundException::new);

        Reservation reservation = Reservation.builder()
                .email(requestDto.getEmail())
                .createdIp(CommonUtil.getIp())
                .deviceOS(DeviceOS.valueOf(requestDto.getDeviceOs()))
                .deviceModel(requestDto.getDeviceModel())
                .promotionAgree(requestDto.isPromotionAgree())
                .game(game)
                .language(language)
                .build();

        reservationRepository.save(reservation);

        return new ApiResponse();
    }
}
