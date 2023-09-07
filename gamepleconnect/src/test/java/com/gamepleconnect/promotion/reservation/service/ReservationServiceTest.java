package com.gamepleconnect.promotion.reservation.service;

import com.gamepleconnect.common.security.AES256;
import com.gamepleconnect.promotion.reservation.domain.Reservation;
import com.gamepleconnect.promotion.reservation.exception.DuplicatedEmailException;
import com.gamepleconnect.root.game.domain.Game;
import com.gamepleconnect.root.game.exception.GameNotFoundException;
import com.gamepleconnect.root.game.repository.GameRepository;
import com.gamepleconnect.promotion.reservation.dto.ReservationRequestDto;
import com.gamepleconnect.promotion.reservation.repository.ReservationRepository;
import com.gamepleconnect.root.language.domain.Language;
import com.gamepleconnect.root.language.exception.LanguageNotFoundException;
import com.gamepleconnect.root.language.repository.LanguageRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Assertions;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
class ReservationServiceTest {

    @Autowired
    ReservationService reservationService;

    @Autowired
    ReservationRepository reservationRepository;

    @Autowired
    GameRepository gameRepository;

    @Autowired
    LanguageRepository languageRepository;

    @Autowired
    AES256 aes256;

    @BeforeEach
    void init() {
        saveDummyGameEntity();
        saveDummyLanguage();
        reservationRepository.deleteAll();
    }

    void saveDummyGameEntity() {
        Game game = Game.builder()
                .gameCode(1L)
                .gameAlias("GAME - 1")
                .build();

        gameRepository.save(game);
    }

    void saveDummyLanguage() {
        Language language = Language.builder()
                .langCode(1L)
                .langAlias("ko")
                .build();

        languageRepository.save(language);
    }

    @Test
    @DisplayName("사전예약 정보 등록 - 성공")
    void test1() throws Exception {

        ReservationRequestDto requestDto = ReservationRequestDto.builder()
                .email("test@test.com")
                .gameCode(1L)
                .lang("ko")
                .deviceModel("iPhone 22")
                .deviceOs("IOS")
                .promotionAgree(false)
                .build();

        reservationService.preRegister(requestDto);

        assertEquals(1L, reservationRepository.count());
        Reservation reservation = reservationRepository.findAll().get(0);
        assertEquals(aes256.encrypt("test@test.com"), reservation.getEmail());
    }

    @Test
    @DisplayName("사전예약 정보 등록 - 실패 - 존재하지 않는 게임 정보")
    void test2() {

        ReservationRequestDto requestDto = ReservationRequestDto.builder()
                .email("test@test.com")
                .gameCode(2L)
                .lang("ko")
                .deviceModel("iPhone 22")
                .deviceOs("IOS")
                .promotionAgree(false)
                .build();

        assertThrows(GameNotFoundException.class, () -> {
            reservationService.preRegister(requestDto);
        });
    }

    @Test
    @DisplayName("사전예약 정보 등록 - 실패 - 존재하지 않는 언어 정보")
    void test3() {

        ReservationRequestDto requestDto = ReservationRequestDto.builder()
                .email("test@test.com")
                .gameCode(1L)
                .lang("en")
                .deviceModel("iPhone 22")
                .deviceOs("IOS")
                .promotionAgree(false)
                .build();

        assertThrows(LanguageNotFoundException.class, () -> {
            reservationService.preRegister(requestDto);
        });
    }

    @Test
    @DisplayName("사전예약 정보 등록 - 실패 - 이미 등록된 이메일 정보")
    void test4() throws Exception {

        ReservationRequestDto firstRequestDto = ReservationRequestDto.builder()
                .email("test@test.com")
                .gameCode(1L)
                .lang("ko")
                .deviceModel("iPhone 22")
                .deviceOs("IOS")
                .promotionAgree(false)
                .build();

        reservationService.preRegister(firstRequestDto);

        ReservationRequestDto secondRequestDto = ReservationRequestDto.builder()
                .email("test@test.com")
                .gameCode(1L)
                .lang("ko")
                .deviceModel("iPhone 22")
                .deviceOs("IOS")
                .promotionAgree(false)
                .build();

        assertThrows(DuplicatedEmailException.class, () -> {
            reservationService.preRegister(secondRequestDto);
        });
    }
}