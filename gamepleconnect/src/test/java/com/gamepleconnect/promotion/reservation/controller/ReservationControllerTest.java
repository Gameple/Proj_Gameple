package com.gamepleconnect.promotion.reservation.controller;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.gamepleconnect.common.code.StatusCode;
import com.gamepleconnect.promotion.reservation.dto.request.ReservationRequest;
import com.gamepleconnect.promotion.reservation.repository.ReservationRepository;
import com.gamepleconnect.root.game.domain.Game;
import com.gamepleconnect.root.game.repository.GameRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.core.AutoConfigureCache;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureCache
@AutoConfigureMockMvc
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
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
    private ReservationRepository reservationRepository;

    @BeforeEach
    @Order(1)
    void cleanRepository() {
        reservationRepository.deleteAll();
        gameRepository.deleteAll();
    }

    @BeforeEach
    @Order(2)
    void saveDummyData() {
        Game game = Game.builder()
                .gameCode(1L)
                .gameAlias("GAME - 1")
                .build();

        gameRepository.save(game);
    }


    @Test
    @DisplayName("사전예약 정보 등록 - 성공")
    void test() throws Exception{

        ReservationRequest requestDto = ReservationRequest.builder()
                .email("test@test.com")
                .gameCode(1L)
                .region("KR")
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
                .andExpect(jsonPath("$.message").value("OK"))
                .andExpect(jsonPath("$.data").isEmpty())
                .andDo(print());
    }

    @Test
    @DisplayName("사전예약 정보 등록 - 실패 - 존재하지 않는 게임 정보")
    void test1() throws Exception{

        ReservationRequest requestDto = ReservationRequest.builder()
                .email("test@test.com")
                .gameCode(2L)
                .region("KR")
                .deviceOs("IOS")
                .promotionAgree(false)
                .build();

        String json = objectMapper.writeValueAsString(requestDto);

        mockMvc.perform(post("/promotion/pre-register")
                        .contentType(APPLICATION_JSON)
                        .content(json)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.statusCode").value(StatusCode.GAME_NOT_FOUND.getStatusCode()))
                .andExpect(jsonPath("$.message").value(StatusCode.GAME_NOT_FOUND.getMessage()))
                .andExpect(jsonPath("$.data").isEmpty())
                .andDo(print());
    }

    @Test
    @DisplayName("사전예약 정보 등록 - 실패 - 이미 동록된 이메일 정보")
    void test2() throws Exception {

        ReservationRequest requestDto = ReservationRequest.builder()
                .email("test@test.com")
                .gameCode(1L)
                .region("KR")
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
                .andExpect(jsonPath("$.statusCode").value(StatusCode.EMAIL_DUPLICATED.getStatusCode()))
                .andExpect(jsonPath("$.message").value(StatusCode.EMAIL_DUPLICATED.getMessage()))
                .andExpect(jsonPath("$.data").isEmpty())
                .andDo(print());
    }

    @Test
    @DisplayName("사전예약 정보 등록 - 실패 - BAD REQUEST")
    void test3() throws Exception {

        ReservationRequest requestDto = ReservationRequest.builder()
                .gameCode(2L)
                .deviceOs("IOS")
                .promotionAgree(false)
                .build();

        String json = objectMapper.writeValueAsString(requestDto);

        mockMvc.perform(post("/promotion/pre-register")
                        .contentType(APPLICATION_JSON)
                        .content(json)
                )
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.statusCode").value("-1"))
                .andExpect(jsonPath("$.message").value("BAD_REQUEST"))
                .andExpect(jsonPath("$.data").isEmpty())
                .andDo(print());
    }
}