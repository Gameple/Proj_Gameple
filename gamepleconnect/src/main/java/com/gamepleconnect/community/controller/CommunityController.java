package com.gamepleconnect.community.controller;

import com.gamepleconnect.common.response.ApiResponse;
import com.gamepleconnect.community.dto.request.CommunityFaqRequest;
import com.gamepleconnect.community.service.faq.CommunityFaqService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Tag(name = "Community", description = "커뮤니티 - 커뮤니티 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/community")
public class CommunityController {

    private final CommunityFaqService communityFaqService;

    @GetMapping("/faqs")
    public ApiResponse getCommunityFaqs(@ModelAttribute @Valid CommunityFaqRequest request) {
        return communityFaqService.getCommunityFaq(request);
    }
}
