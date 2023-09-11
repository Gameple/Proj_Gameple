package com.gamepleconnect.branch.dto.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CountryCodeGetRequest {

    @NotBlank()
    private String ip;
}
