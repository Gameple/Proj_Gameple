package com.gamepleconnect.branch.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Getter
@Entity
@Table(name = "gameple_country_block_reason")
@NoArgsConstructor
@ToString
public class CountryRestrictions implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "game_code", nullable = false)
    private Long gameCode;

    @Column(name = "country_code", length = 2, nullable = false)
    private String countryCode;

    @Column(name = "reason_code", length = 50, nullable = false)
    private String reasonCode;

    @Column(name = "reason_text", length = 255, nullable = false)
    private String reasonText;

    @Column(name = "language_code", length = 2, nullable = false)
    private String languageCode = "en";

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Date createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Date updatedAt;

    @Builder
    public CountryRestrictions(Long gameCode, String countryCode, String reasonCode, String reasonText, String languageCode) {
        this.gameCode = gameCode;
        this.countryCode = countryCode;
        this.reasonCode = reasonCode;
        this.reasonText = reasonText;
        this.languageCode = languageCode;
    }
}
