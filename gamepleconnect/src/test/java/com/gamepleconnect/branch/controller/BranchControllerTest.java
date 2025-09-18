package com.gamepleconnect.branch.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gamepleconnect.branch.domain.CountryRestrictions;
import com.gamepleconnect.branch.repository.restrictions.CountryRestrictionsRepository;
import com.gamepleconnect.common.code.StatusCode;
import com.gamepleconnect.root.game.domain.Game;
import com.gamepleconnect.root.game.repository.GameRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.core.AutoConfigureCache;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureCache
@AutoConfigureMockMvc
@SpringBootTest
class BranchControllerTest {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private MockMvc mockMvc;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    GameRepository gameRepository;

    @Autowired
    CountryRestrictionsRepository countryRestrictionsRepository;

    @BeforeEach
    @Order(1)
    void cleanRepository() {
        gameRepository.deleteAll();
        countryRestrictionsRepository.deleteAll();
    }

    @BeforeEach
    @Order(2)
    void saveDummyData() {
        Game game = Game.builder()
                .gameCode(1L)
                .gameAlias("GAME - 1")
                .build();

        gameRepository.save(game);

        CountryRestrictions countryRestrictions = CountryRestrictions.builder()
                .gameCode(1l)
                .countryCode("US")
                .reasonCode("LEGAL_RESTRICTION")
                .reasonText("Service is not available due to local regulations.")
                .languageCode("en")
                .build();

        countryRestrictionsRepository.save(countryRestrictions);
    }

    @Test
    @DisplayName("IP 기반 국가코드 조회 - 성공")
    void test1() throws Exception {

        // US IP
        final String clientIp = "100.42.19.255";

        LinkedMultiValueMap<String, String> requestParam = new LinkedMultiValueMap<>();
        requestParam.set("ip", clientIp);

        mockMvc.perform(get("/branch/country/code")
                        .params(requestParam)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.statusCode").value(StatusCode.SUCCESS.getStatusCode()))
                .andExpect(jsonPath("$.data.ip").value("100.42.19.255"))
                .andExpect(jsonPath("$.data.country").value("US"))
                .andDo(print());
    }

    @Test
    @DisplayName("IP 기반 국가코드 조회 - 실패(디폴트 응답)")
    void test2() throws Exception {

        final String clientIp = "127.0.0.1";

        LinkedMultiValueMap<String, String> requestParam = new LinkedMultiValueMap<>();
        requestParam.set("ip", clientIp);

        mockMvc.perform(get("/branch/country/code")
                        .params(requestParam)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.statusCode").value(StatusCode.SUCCESS.getStatusCode()))
                .andExpect(jsonPath("$.data.ip").value("127.0.0.1"))
                .andExpect(jsonPath("$.data.country").value("US"))
                .andDo(print());
    }

    @Test
    @DisplayName("특정 국가 제한 여부 조회 - 데이터 0")
    void test3() throws Exception {

        LinkedMultiValueMap<String, String> requestParam = new LinkedMultiValueMap<>();
        requestParam.set("countryCode", "US");
        requestParam.set("languageCode", "en");
        requestParam.set("gameCode", "1");

        mockMvc.perform(get("/branch/country/restrictions")
                        .params(requestParam)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.statusCode").value(StatusCode.SUCCESS.getStatusCode()))
                .andExpect(jsonPath("$.data[0].gameCode").value("1"))
                .andExpect(jsonPath("$.data[0].countryCode").value("US"))
                .andExpect(jsonPath("$.data[0].reasonCode").value("LEGAL_RESTRICTION"))
                .andExpect(jsonPath("$.data[0].reasonText").value("Service is not available due to local regulations."))
                .andExpect(jsonPath("$.data[0].languageCode").value("en"))
                .andDo(print());
    }
}