package com.gamepleconnect.promotion.reservation.controller;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.gamepleconnect.promotion.reservation.domain.DeviceOS;
import com.gamepleconnect.promotion.reservation.domain.Reservation;
import com.gamepleconnect.promotion.reservation.dto.ReservationRequestDto;
import com.gamepleconnect.promotion.reservation.repository.ReservationRepository;
import com.gamepleconnect.root.game.domain.Game;
import com.gamepleconnect.root.game.repository.GameRepository;
import com.gamepleconnect.root.language.domain.Language;
import com.gamepleconnect.root.language.repository.LanguageRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest
class ReservationControllerTest {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private MockMvc mockMvc;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    GameRepository gameRepository;

    @Autowired
    LanguageRepository languageRepository;

    @Autowired
    private ReservationRepository reservationRepository;

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
    void test() throws Exception{

        ReservationRequestDto requestDto = ReservationRequestDto.builder()
                .email("test@test.com")
                .gameCode(1L)
                .lang("ko")
                .deviceModel("iPhone 22")
                .deviceOs("IOS")
                .promotionAgree(false)
                .build();

        String json = objectMapper.writeValueAsString(requestDto);

        mockMvc.perform(post("/promotion/pre-register")
                        .contentType(APPLICATION_JSON)
                        .content(json)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.statusCode").value("1"))
                .andExpect(jsonPath("$.data").isEmpty())
                .andDo(print());
    }

    @Test
    @DisplayName("사전예약 정보 등록 - 실패 - 존재하지 않는 게임 정보")
    void test1() throws Exception{

        ReservationRequestDto requestDto = ReservationRequestDto.builder()
                .email("test@test.com")
                .gameCode(2L)
                .lang("ko")
                .deviceModel("iPhone 22")
                .deviceOs("IOS")
                .promotionAgree(false)
                .build();

        String json = objectMapper.writeValueAsString(requestDto);

        mockMvc.perform(post("/promotion/pre-register")
                        .contentType(APPLICATION_JSON)
                        .content(json)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.statusCode").value("-5"))
                .andExpect(jsonPath("$.data").isEmpty())
                .andDo(print());
    }

    @Test
    @DisplayName("사전예약 정보 등록 - 실패 - 존재하지 않는 언어 정보")
    void test2() throws Exception {

        ReservationRequestDto requestDto = ReservationRequestDto.builder()
                .email("test@test.com")
                .gameCode(1L)
                .lang("en")
                .deviceModel("iPhone 22")
                .deviceOs("IOS")
                .promotionAgree(false)
                .build();

        String json = objectMapper.writeValueAsString(requestDto);

        mockMvc.perform(post("/promotion/pre-register")
                        .contentType(APPLICATION_JSON)
                        .content(json)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.statusCode").value("-6"))
                .andExpect(jsonPath("$.data").isEmpty())
                .andDo(print());
    }

    @Test
    @DisplayName("사전예약 정보 등록 - 실패 - 이미 동록된 이메일 정보")
    void test3() throws Exception {

        ReservationRequestDto requestDto = ReservationRequestDto.builder()
                .email("test@test.com")
                .gameCode(1L)
                .lang("ko")
                .deviceModel("iPhone 22")
                .deviceOs("IOS")
                .promotionAgree(false)
                .build();

        String json = objectMapper.writeValueAsString(requestDto);

        mockMvc.perform(post("/promotion/pre-register")
                        .contentType(APPLICATION_JSON)
                        .content(json)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.statusCode").value("1"))
                .andExpect(jsonPath("$.data").isEmpty())
                .andDo(print());

        mockMvc.perform(post("/promotion/pre-register")
                        .contentType(APPLICATION_JSON)
                        .content(json)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.statusCode").value("-7"))
                .andExpect(jsonPath("$.data").isEmpty())
                .andDo(print());
    }
}