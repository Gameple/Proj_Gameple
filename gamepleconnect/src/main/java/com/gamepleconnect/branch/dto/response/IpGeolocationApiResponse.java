package com.gamepleconnect.branch.dto.response;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class IpGeolocationApiResponse implements Serializable {

    private String ip;

    private String country;

    public static IpGeolocationApiResponse defaultResponse(String ip) {
        return builder()
                .ip(ip)
                .country("US")
                .build();
    }
}
