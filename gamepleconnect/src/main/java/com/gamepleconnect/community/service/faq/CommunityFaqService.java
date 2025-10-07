package com.gamepleconnect.community.service.faq;

import com.gamepleconnect.common.code.StatusCode;
import com.gamepleconnect.common.response.ApiResponse;
import com.gamepleconnect.community.dto.request.CommunityFaqRequest;
import com.gamepleconnect.community.dto.response.CommunityFaqPageResponse;
import com.gamepleconnect.community.repository.faq.CommunityFaqRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CommunityFaqService {

    private final CommunityFaqRepository communityFaqRepository;

    public ApiResponse<CommunityFaqPageResponse> getCommunityFaq(CommunityFaqRequest request) {
        return ApiResponse.<CommunityFaqPageResponse>builder()
                .statusCode(StatusCode.SUCCESS.getStatusCode())
                .message(StatusCode.SUCCESS.getMessage())
                .data(communityFaqRepository.findByGameCodeAndLanguageCode(request))
                .build();
    }
}
