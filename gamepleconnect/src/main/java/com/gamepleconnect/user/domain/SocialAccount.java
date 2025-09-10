package com.gamepleconnect.user.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Getter
@Entity
@Table(name = "gameple_user_social_accounts",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"provider", "provider_user_id"})})
@NoArgsConstructor
@ToString
public class SocialAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Enumerated(EnumType.STRING)
    @Column(name = "provider", nullable = false)
    private SocialAccountProvider socialAccountProvider;

    @Column(name = "provider_user_id")
    private String providerUserId;

    @Column(name = "access_token")
    private String accessToken;

    @Column(name = "refresh_token")
    private String refreshToken;

    @Column(name = "linked_at", nullable = false, updatable = false)
    private Date linkedAt;

    @Builder
    public SocialAccount(SocialAccountProvider socialAccountProvider, String providerUserId, String accessToken, String refreshToken) {
        this.socialAccountProvider = socialAccountProvider;
        this.providerUserId = providerUserId;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}
