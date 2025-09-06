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
@Table(name = "gameple_community_faqs")
@NoArgsConstructor
@ToString
public class Faq {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "category_id", nullable = false)
    private Long categoryId;

    @Column(name = "question", length = 500, nullable = false)
    private String question;

    @Column(name = "answer", columnDefinition = "TEXT", nullable = false)
    private String answer;

    @Column(name = "is_active", nullable = false)
    @ColumnDefault("1")
    private Boolean isActive = true;

    @Column(name = "created_by", nullable = false)
    private Long createdBy;

    @Column(name = "updated_by", nullable = false)
    private Long updatedBy;

    @Column(name = "created_at", nullable = false, updatable = false)
    private Date createdAt;

    @Column(name = "updated_at")
    private Date updatedAt;

    @Builder
    public Faq(Long categoryId, String question, String answer, Long createdBy, Long updatedBy) {
        this.categoryId = categoryId;
        this.question = question;
        this.answer = answer;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
    }
}
