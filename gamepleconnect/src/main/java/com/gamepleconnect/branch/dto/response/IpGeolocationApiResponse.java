package com.gamepleconnect.branch.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IpGeolocationApiResponse implements Serializable {

    private static final String defaultCountryCode = "US";

    private String ip;

    private String country;

    public static IpGeolocationApiResponse defaultResponse(String ip) {
        return builder()
                .ip(ip)
                .country(defaultCountryCode)
                .build();
    }
}
