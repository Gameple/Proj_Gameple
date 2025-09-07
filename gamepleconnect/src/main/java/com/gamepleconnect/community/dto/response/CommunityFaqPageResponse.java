package com.gamepleconnect.community.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class CommunityFaqPageResponse {
    private int currentPage;

    private int totalPages;

    private List<CommunityFaqResponse> content;
}