package com.gamepleconnect.branch.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gamepleconnect.branch.domain.CountryRestrictions;
import com.gamepleconnect.branch.dto.request.CountryCodeGetRequest;
import com.gamepleconnect.branch.dto.request.CountryRestrictionsRequest;
import com.gamepleconnect.branch.dto.response.IpGeolocationApiResponse;
import com.gamepleconnect.branch.repository.restrictions.CountryRestrictionsRepository;
import com.gamepleconnect.common.code.StatusCode;
import com.gamepleconnect.common.response.ApiResponse;
import com.gamepleconnect.root.game.domain.Game;
import com.gamepleconnect.root.game.repository.GameRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.core.AutoConfigureCache;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@AutoConfigureCache
@SpringBootTest
class BranchServiceTest {

    @Autowired
    BranchService branchService;

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
                .countryCode("KR")
                .reasonCode("AGE_LIMIT")
                .reasonText("만 18세 이상만 이용 가능합니다.")
                .languageCode("ko")
                .build();

        countryRestrictionsRepository.save(countryRestrictions);
    }

    @Test
    @DisplayName("IP 기반 국가코드 조회 - 성공")
    void test1() {

        // US IP
        final String clientIp = "100.42.19.255";

        CountryCodeGetRequest request = CountryCodeGetRequest.builder()
                .ip(clientIp)
                .build();

        ApiResponse response = branchService.getCountryCodeByIp(request);
        IpGeolocationApiResponse apiResponseData = objectMapper.convertValue(response.getData(), IpGeolocationApiResponse.class);

        assertEquals(StatusCode.SUCCESS.getStatusCode(), response.getStatusCode());
        assertEquals("100.42.19.255", apiResponseData.getIp());
        assertEquals("US", apiResponseData.getCountry());
    }

    @Test
    @DisplayName("IP 기반 국가코드 조회 - 실패(디폴트 응답)")
    void test2() {

        final String clientIp = "127.0.0.1";

        CountryCodeGetRequest request = CountryCodeGetRequest.builder()
                .ip(clientIp)
                .build();

        ApiResponse response = branchService.getCountryCodeByIp(request);
        IpGeolocationApiResponse apiResponseData = objectMapper.convertValue(response.getData(), IpGeolocationApiResponse.class);

        assertEquals(StatusCode.SUCCESS.getStatusCode(), response.getStatusCode());
        assertEquals("127.0.0.1", apiResponseData.getIp());
        assertEquals("US", apiResponseData.getCountry());
    }

    @Test
    @DisplayName("특정 국가 제한 여부 조회 - 데이터 O")
    void test3() {

        CountryRestrictionsRequest request = CountryRestrictionsRequest.builder()
                .gameCode(1)
                .languageCode("ko")
                .countryCode("KR")
                .build();

        ApiResponse response = branchService.getRestrictionsByCountry(request);
        List<CountryRestrictions> dataList = (List<CountryRestrictions>) response.getData();

        assertEquals(StatusCode.SUCCESS.getStatusCode(), response.getStatusCode());
        assertEquals(1, dataList.get(0).getGameCode());
        assertEquals("KR", dataList.get(0).getCountryCode());
        assertEquals("AGE_LIMIT", dataList.get(0).getReasonCode());
        assertEquals("만 18세 이상만 이용 가능합니다.", dataList.get(0).getReasonText());
        assertEquals("ko", dataList.get(0).getLanguageCode());
    }
}