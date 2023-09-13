package com.gamepleconnect.branch.dto.request;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
public class CountryCodeGetRequest implements Serializable {

    @NotBlank()
    private String ip;

    @Builder
    public  CountryCodeGetRequest(String ip) {
        this.ip = ip;
    }
}
