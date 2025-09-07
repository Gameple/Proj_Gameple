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

    private Integer page = 1;

    private Integer size = 10;

    @Builder
    public CommunityFaqRequest(Long gameCode, String languageCode) {
        this.gameCode = gameCode;
        this.languageCode = languageCode;
        this.page = page != null ? page : 1;
        this.size = size != null ? size : 10;
    }
}
