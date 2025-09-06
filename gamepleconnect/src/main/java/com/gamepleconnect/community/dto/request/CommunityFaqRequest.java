package com.gamepleconnect.community.dto.request;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class CommunityFaqRequest {

    @NotNull()
    private Long gameCode;

    @NotBlank()
    private String languageCode;


    @Builder
    public CommunityFaqRequest(Long gameCode, String languageCode) {
        this.gameCode = gameCode;
        this.languageCode = languageCode;
    }
}
