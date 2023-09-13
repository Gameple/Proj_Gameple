package com.gamepleconnect.branch.service;

import com.gamepleconnect.branch.dto.request.CountryCodeGetRequest;
import com.gamepleconnect.branch.dto.response.IpGeolocationApiResponse;
import com.gamepleconnect.common.code.StatusCode;
import com.gamepleconnect.common.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
@Slf4j
public class BranchService {

    @Value("${branch.ip-geolocation.api}")
    private String ipGeoLocationApiUri;

    private final RestTemplate restTemplate;

    @Cacheable(value="branchGetCountryCodeCache", key = "#request")
    public ApiResponse getCountryCodeByIp(CountryCodeGetRequest request) {

        log.info("BRANCH API - IP GEOLOCATION API REQUEST : {}", request);

        String clientIp = request.getIp();
        IpGeolocationApiResponse ipGeolocationApiResponse = IpGeolocationApiResponse.defaultResponse(request.getIp());

        try {
            ResponseEntity<IpGeolocationApiResponse> responseEntity =
                    restTemplate.getForEntity(ipGeoLocationApiUri + clientIp, IpGeolocationApiResponse.class);
            ipGeolocationApiResponse = responseEntity.getBody();

            log.info("BRANCH API - IP GEOLOCATION API RESULT : {}", responseEntity);

        } catch (Exception e) {
            log.warn("BRANCH API - IP GEOLOCATION API ERROR REQUEST: {}", request);
        }

        return ApiResponse.builder()
                .statusCode(StatusCode.SUCCESS.getCode())
                .data(ipGeolocationApiResponse)
                .build();
    }
}
