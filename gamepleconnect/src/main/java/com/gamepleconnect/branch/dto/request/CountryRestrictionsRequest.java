package com.gamepleconnect.branch.dto.request;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class CountryRestrictionsRequest implements Serializable {

    @NotBlank()
    private String countryCode;

    @NotBlank()
    private String languageCode;

    @NotNull()
    private Long gameCode;

    public void setCountryCodeUpper(String countryCode) {
        this.countryCode = countryCode.toUpperCase();
    }

    public void setLanguageCodeLower(String languageCode) {
        this.languageCode = languageCode.toLowerCase();
    }

    @Builder
    public  CountryRestrictionsRequest(String countryCode, String languageCode, long gameCode) {
        this.setCountryCodeUpper(countryCode);
        this.setLanguageCodeLower(languageCode);
        this.gameCode = gameCode;
    }
}
