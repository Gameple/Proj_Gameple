package com.gamepleconnect.community.repository.faq;

import com.gamepleconnect.community.dto.request.CommunityFaqRequest;
import com.gamepleconnect.community.dto.response.CommunityFaqResponse;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CommunityFaqCustomRepositoryImpl implements CommunityFaqCustomRepository {

    @PersistenceContext
    private EntityManager em;

    private final JPAQueryFactory jpaQueryFactory;

    public CommunityFaqCustomRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
        this.jpaQueryFactory = jpaQueryFactory;
    }

    @Override
    public List<CommunityFaqResponse> findByGameCodeAndLanguageCode(CommunityFaqRequest request) {

        String sql = "SELECT " +
                "b.id AS category_id, " +
                "b.category_name, " +
                "a.id AS faq_id, " +
                "a.question, " +
                "a.answer, " +
                "a.created_at, " +
                "a.updated_at " +
                "FROM gameple_community_faqs a " +
                "JOIN gameple_community_faq_categories b ON a.category_id = b.id " +
                "WHERE (:languageCode IS NULL OR a.language_code = :languageCode) " +
                "AND (:gameCode IS NULL OR b.game_code = :gameCode) " +
                "AND a.is_active = 1 " +
                "ORDER BY a.created_at DESC";

        List<Object[]> results = em.createNativeQuery(sql)
                .setParameter("languageCode", request.getLanguageCode())
                .setParameter("gameCode", request.getGameCode())
                .getResultList();

        List<CommunityFaqResponse> responseList = new ArrayList<>();
        for (Object[] row : results) {
            CommunityFaqResponse dto = new CommunityFaqResponse();
            dto.setCategoryId(((Number) row[0]).longValue());
            dto.setCategoryName((String) row[1]);
            dto.setFaqId(((Number) row[2]).longValue());
            dto.setQuestion((String) row[3]);
            dto.setAnswer((String) row[4]);
            dto.setCreatedDate((Date) row[5]);
            dto.setUpdatedDate((Date) row[6]);
            responseList.add(dto);
        }

        return responseList;
    }
}
