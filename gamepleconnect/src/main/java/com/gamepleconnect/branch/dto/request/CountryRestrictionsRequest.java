package com.gamepleconnect.branch.dto.request;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
public class CountryRestrictionsRequest implements Serializable {

    @NotBlank()
    private String country;

    @Builder
    public  CountryRestrictionsRequest(String country) {
        this.country = country;
    }
}
