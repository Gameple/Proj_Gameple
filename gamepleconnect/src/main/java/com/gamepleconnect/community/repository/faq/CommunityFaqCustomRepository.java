package com.gamepleconnect.community.repository.faq;

import com.gamepleconnect.community.dto.request.CommunityFaqRequest;
import com.gamepleconnect.community.dto.response.CommunityFaqResponse;

import java.util.List;

public interface CommunityFaqCustomRepository {

    List<CommunityFaqResponse> findByGameCodeAndLanguageCode(CommunityFaqRequest request);
}
