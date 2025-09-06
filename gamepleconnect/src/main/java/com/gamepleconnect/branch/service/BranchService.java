package com.gamepleconnect.branch.service;

import com.gamepleconnect.branch.domain.CountryRestrictions;
import com.gamepleconnect.branch.dto.request.CountryCodeGetRequest;
import com.gamepleconnect.branch.dto.request.CountryRestrictionsRequest;
import com.gamepleconnect.branch.dto.response.CountryRestrictionsResponse;
import com.gamepleconnect.branch.dto.response.IpGeolocationApiResponse;
import com.gamepleconnect.branch.repository.restrictions.CountryRestrictionsRepository;
import com.gamepleconnect.common.code.StatusCode;
import com.gamepleconnect.common.response.ApiResponse;
import com.gamepleconnect.root.game.exception.GameNotFoundException;
import com.gamepleconnect.root.game.repository.GameRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class BranchService {

    @Value("${external-api-url.branch.ip-geolocation}")
    private String ipGeoLocationApiUri;

    private final RestTemplate restTemplate;

    private final CountryRestrictionsRepository countryRestrictionsRepository;

    private final GameRepository gameRepository;

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
                .statusCode(StatusCode.SUCCESS.getStatusCode())
                .message(StatusCode.SUCCESS.getMessage())
                .data(ipGeolocationApiResponse)
                .build();
    }

    @Cacheable(value="branchGetCountryRestrictionsCache", key = "#request")
    public ApiResponse getRestrictionsByCountry(CountryRestrictionsRequest request) {

        log.info("BRANCH API - COUNTRY RESTRICTIONS API REQUEST : {}", request);

        if(gameRepository.findByGameCode(request.getGameCode()).isEmpty()) {
            throw new GameNotFoundException();
        }

        CountryRestrictionsRequest normalizeRequest = CountryRestrictionsRequest.builder()
                .countryCode(request.getCountryCode())
                .languageCode(request.getLanguageCode())
                .gameCode(request.getGameCode())
                .build();

        List<CountryRestrictions> countryRestrictionsResponse =
                countryRestrictionsRepository.findByCountryCodeAndLanguageCode(normalizeRequest);

        return ApiResponse.builder()
                .statusCode(StatusCode.SUCCESS.getStatusCode())
                .message(StatusCode.SUCCESS.getMessage())
                .data(countryRestrictionsResponse)
                .build();
    }
}
