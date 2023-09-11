package com.gamepleconnect.branch.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class IpGeolocationApiResponse {

    private String ip;

    private String country;

    public static IpGeolocationApiResponse defaultResponse(String ip) {
        return builder()
                .ip(ip)
                .country("KR")
                .build();
    }
}
