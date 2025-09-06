package com.gamepleconnect.branch.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gamepleconnect.branch.dto.request.CountryCodeGetRequest;
import com.gamepleconnect.branch.dto.response.IpGeolocationApiResponse;
import com.gamepleconnect.common.response.ApiResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.core.AutoConfigureCache;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@AutoConfigureCache
@SpringBootTest
class BranchServiceTest {

    @Autowired
    BranchService branchService;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    ObjectMapper objectMapper;

    @Test
    @DisplayName("IP 기반 국가코드 조회 - 성공")
    void test1() {

        // US IP
        final String clientIp = "100.42.19.255";

        CountryCodeGetRequest request = CountryCodeGetRequest.builder()
                .ip(clientIp)
                .build();

        ApiResponse apiResponse = branchService.getCountryCodeByIp(request);
        IpGeolocationApiResponse apiResponseData = objectMapper.convertValue(apiResponse.getData(), IpGeolocationApiResponse.class);

        assertEquals("1", apiResponse.getStatusCode());
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

        ApiResponse apiResponse = branchService.getCountryCodeByIp(request);
        IpGeolocationApiResponse apiResponseData = objectMapper.convertValue(apiResponse.getData(), IpGeolocationApiResponse.class);

        assertEquals("1", apiResponse.getStatusCode());
        assertEquals("127.0.0.1", apiResponseData.getIp());
        assertEquals("US", apiResponseData.getCountry());
    }
}