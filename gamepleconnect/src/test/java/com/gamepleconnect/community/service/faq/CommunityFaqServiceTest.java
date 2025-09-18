package com.gamepleconnect.community.service.faq;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.gamepleconnect.branch.dto.request.CountryCodeGetRequest;
import com.gamepleconnect.branch.dto.response.IpGeolocationApiResponse;
import com.gamepleconnect.common.code.StatusCode;
import com.gamepleconnect.common.response.ApiResponse;
import com.gamepleconnect.community.domain.faq.Faq;
import com.gamepleconnect.community.dto.request.CommunityFaqRequest;
import com.gamepleconnect.community.dto.response.CommunityFaqPageResponse;
import com.gamepleconnect.community.dto.response.CommunityFaqResponse;
import com.gamepleconnect.community.repository.faq.CommunityFaqRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.core.AutoConfigureCache;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@AutoConfigureCache
@SpringBootTest
public class CommunityFaqServiceTest {

    @Autowired
    CommunityFaqService communityFaqService;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    CommunityFaqRepository communityFaqRepository;

    @BeforeEach
    @Order(1)
    void cleanRepository() {
        communityFaqRepository.deleteAll();
    }

    @BeforeEach
    @Order(2)
    void saveDummyData() {

        jdbcTemplate.execute("INSERT INTO gameple_community_faq_categories " +
                "(game_code, category_name, sort_order, created_at, updated_at) " +
                "VALUES (1, '계정', 0, NOW(), NOW())");

        jdbcTemplate.execute("INSERT INTO gameple_community_faq_categories " +
                "(game_code, category_name, sort_order, created_at, updated_at) " +
                "VALUES (1, '결제', 1, NOW(), NOW())");

        Faq faq = Faq.builder()
                .categoryId(2L)
                .question("간편결제를 사용할 수 있나요?")
                .answer("네! 사용 가능합니다. 게임플에선 카카오페이 및 네이버페이 간편결제를 제공하고 있습니다.")
                .languageCode("ko")
                .createdBy(1L)
                .updatedBy(1L)
                .build();

        communityFaqRepository.save(faq);
    }

    @Test
    @DisplayName("커뮤니티 FAQ 게시글 조회 - 데이터 0")
    void test1() {

        CommunityFaqRequest request = CommunityFaqRequest.builder()
                .gameCode(1L)
                .languageCode("ko")
                .build();

        ApiResponse apiResponse = communityFaqService.getCommunityFaq(request);
        assertEquals(StatusCode.SUCCESS.getStatusCode(), apiResponse.getStatusCode());

        CommunityFaqPageResponse dataResponse = (CommunityFaqPageResponse) apiResponse.getData();
        assertEquals(1, dataResponse.getCurrentPage());
        assertEquals(1, dataResponse.getTotalPages());

        List<CommunityFaqResponse> responsesList = dataResponse.getContent();
        assertEquals(2, responsesList.get(0).getCategoryId());
        assertEquals("결제", responsesList.get(0).getCategoryName());
        assertEquals("간편결제를 사용할 수 있나요?", responsesList.get(0).getQuestion());
        assertEquals("네! 사용 가능합니다. 게임플에선 카카오페이 및 네이버페이 간편결제를 제공하고 있습니다.", responsesList.get(0).getAnswer());

    }

    @Test
    @DisplayName("커뮤니티 FAQ 게시글 조회 - 데이터 X")
    void test2() {

        CommunityFaqRequest request = CommunityFaqRequest.builder()
                .gameCode(1L)
                .languageCode("ar")
                .build();

        ApiResponse apiResponse = communityFaqService.getCommunityFaq(request);
        assertEquals(StatusCode.SUCCESS.getStatusCode(), apiResponse.getStatusCode());

        CommunityFaqPageResponse dataResponse = (CommunityFaqPageResponse) apiResponse.getData();
        assertEquals(1, dataResponse.getCurrentPage());
        assertEquals(0, dataResponse.getTotalPages());

        List<CommunityFaqResponse> responsesList = dataResponse.getContent();
        assertTrue(responsesList.isEmpty());
    }
}
