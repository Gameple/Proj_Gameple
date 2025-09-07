package com.gamepleconnect.community.repository.faq;

import com.gamepleconnect.community.dto.request.CommunityFaqRequest;
import com.gamepleconnect.community.dto.response.CommunityFaqPageResponse;

public interface CommunityFaqCustomRepository {

    CommunityFaqPageResponse findByGameCodeAndLanguageCode(CommunityFaqRequest request);
}
