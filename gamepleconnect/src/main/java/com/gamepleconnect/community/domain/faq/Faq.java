package com.gamepleconnect.community.domain.faq;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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

    @Column(name = "language_code", length = 2, nullable = false)
    private String languageCode;

    @Column(name = "is_active", nullable = false)
    @ColumnDefault("1")
    private Boolean isActive = true;

    @Column(name = "created_by", nullable = false)
    private Long createdBy;

    @Column(name = "updated_by", nullable = false)
    private Long updatedBy;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Date createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Date updatedAt;

    @Builder
    public Faq(Long categoryId, String question, String answer, String languageCode, Long createdBy, Long updatedBy) {
        this.categoryId = categoryId;
        this.question = question;
        this.answer = answer;
        this.languageCode = languageCode;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
    }
}
