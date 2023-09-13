package com.gamepleconnect.branch.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gamepleconnect.branch.dto.request.CountryCodeGetRequest;
import com.gamepleconnect.promotion.reservation.dto.ReservationRequestDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
class BranchControllerTest {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private MockMvc mockMvc;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    ObjectMapper objectMapper;

    @Test
    @DisplayName("IP 기반 국가코드 조회 - 성공")
    void test() throws Exception {

        // US IP
        final String clientIp = "100.42.19.255";

        LinkedMultiValueMap<String, String> requestParam = new LinkedMultiValueMap<>();
        requestParam.set("ip", clientIp);

        mockMvc.perform(get("/branch/country/code")
                        .params(requestParam)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.statusCode").value("1"))
                .andExpect(jsonPath("$.data.ip").value("100.42.19.255"))
                .andExpect(jsonPath("$.data.country").value("US"))
                .andDo(print());
    }

    @Test
    @DisplayName("IP 기반 국가코드 조회 - 실패(디폴트 응답)")
    void test1() throws Exception {

        final String clientIp = "127.0.0.1";

        LinkedMultiValueMap<String, String> requestParam = new LinkedMultiValueMap<>();
        requestParam.set("ip", clientIp);

        mockMvc.perform(get("/branch/country/code")
                        .params(requestParam)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.statusCode").value("1"))
                .andExpect(jsonPath("$.data.ip").value("127.0.0.1"))
                .andExpect(jsonPath("$.data.country").value("US"))
                .andDo(print());
    }
}