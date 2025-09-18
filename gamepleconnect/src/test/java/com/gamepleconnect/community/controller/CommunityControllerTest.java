package com.gamepleconnect.community.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gamepleconnect.branch.domain.CountryRestrictions;
import com.gamepleconnect.common.code.StatusCode;
import com.gamepleconnect.community.domain.faq.Faq;
import com.gamepleconnect.community.repository.faq.CommunityFaqRepository;
import com.gamepleconnect.root.game.domain.Game;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.core.AutoConfigureCache;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureCache
@AutoConfigureMockMvc
@SpringBootTest
public class CommunityControllerTest {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private MockMvc mockMvc;

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
    void test1() throws Exception {

        LinkedMultiValueMap<String, String> requestParam = new LinkedMultiValueMap<>();
        requestParam.set("gameCode", "1");
        requestParam.set("languageCode", "ko");
        requestParam.set("page", "1");
        requestParam.set("size", "1");

        mockMvc.perform(get("/community/faqs")
                        .params(requestParam)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.statusCode").value(StatusCode.SUCCESS.getStatusCode()))
                .andExpect(jsonPath("$.data.currentPage").value("1"))
                .andExpect(jsonPath("$.data.totalPages").value("1"))
                .andExpect(jsonPath("$.data.content[0].categoryId").value("2"))
                .andExpect(jsonPath("$.data.content[0].categoryName").value("결제"))
                .andExpect(jsonPath("$.data.content[0].question").value("간편결제를 사용할 수 있나요?"))
                .andExpect(jsonPath("$.data.content[0].answer").value("네! 사용 가능합니다. 게임플에선 카카오페이 및 네이버페이 간편결제를 제공하고 있습니다."))
                .andDo(print());
    }

    @Test
    @DisplayName("커뮤니티 FAQ 게시글 조회 - 데이터 X")
    void test2() throws Exception {

        LinkedMultiValueMap<String, String> requestParam = new LinkedMultiValueMap<>();
        requestParam.set("gameCode", "1");
        requestParam.set("languageCode", "ar");
        requestParam.set("page", "1");
        requestParam.set("size", "1");

        mockMvc.perform(get("/community/faqs")
                        .params(requestParam)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.statusCode").value(StatusCode.SUCCESS.getStatusCode()))
                .andExpect(jsonPath("$.data.currentPage").value("1"))
                .andExpect(jsonPath("$.data.totalPages").value("0"))
                .andExpect(jsonPath("$.data.content").isEmpty())
                .andDo(print());
    }

}
