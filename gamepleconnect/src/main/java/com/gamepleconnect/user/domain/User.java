package com.gamepleconnect.user.domain;

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
@Table(name = "gameple_user")
@NoArgsConstructor
@ToString
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "username", length = 50, unique = true, nullable = false)
    private String userName;

    @Column(name = "password_hash")
    private String passwordHash;

    @Enumerated(EnumType.STRING)
    @Column(name = "signup_type", nullable = false)
    private SignUpType signupType = SignUpType.LOCAL;

    @Column(name = "profile_image_url", length = 500)
    private String profileImageUrl;

    @Column(name = "email_verified", nullable = false)
    @ColumnDefault("1")
    private Boolean emailVerified;

    @Column(name = "created_ip", length = 50, nullable = false)
    private String createdIp;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = false, updatable = false)
    private Date createdAt;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    private Date updatedAt;

    @Builder
    public User(String email, String userName, String passwordHash, SignUpType signupType, String profileImageUrl, String createdIp) {
        this.email = email;
        this.userName = userName;
        this.passwordHash = passwordHash;
        this.signupType = signupType;
        this.profileImageUrl = profileImageUrl;
        this.createdIp = createdIp;
    }
}
