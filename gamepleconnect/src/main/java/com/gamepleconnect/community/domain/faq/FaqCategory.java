package com.gamepleconnect.community.domain.faq;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.Date;

@Getter
@Entity
@Table(name = "gameple_community_faq_categories")
@NoArgsConstructor
@ToString
public class FaqCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "game_code", nullable = false)
    private Long gameCode;

    @Column(name = "category_name", length = 100, nullable = false)
    private String categoryName;

    @Column(name = "sort_order", nullable = false)
    @ColumnDefault("0")
    private int sortOrder;

    @Column(name = "created_at", nullable = false, updatable = false)
    private Date createdAt;

    @Column(name = "updated_at")
    private Date updatedAt;

    @Builder
    public FaqCategory(Long gameCode, String categoryName, int sortOrder) {
        this.gameCode = gameCode;
        this.categoryName = categoryName;
        this.sortOrder = sortOrder;
    }
}
