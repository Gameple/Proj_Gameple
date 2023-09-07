package com.gamepleconnect.promotion.reservation.domain;

import com.gamepleconnect.root.game.domain.Game;
import com.gamepleconnect.root.language.domain.Language;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Getter
@Entity
@Table(name = "gameple_cs_advance_reservation")
@NoArgsConstructor
@ToString
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reservation_id")
    private Long id;

    @Column(name = "user_email")
    private String email;

    @Column(name = "created_ip")
    private String createdIp;

    @Enumerated(EnumType.STRING)
    @Column(name = "device_os")
    private DeviceOS deviceOS;

    @Column(name = "device_model")
    private String deviceModel;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "game_code")
    private Game game;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lang_code")
    private Language language;

    @Column(name = "promotion_agree")
    @ColumnDefault("false")
    private boolean promotionAgree;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

    @Builder
    public Reservation(String email, String createdIp, DeviceOS deviceOS, String deviceModel, Game game, Language language, boolean promotionAgree) {
        this.email = email;
        this.createdIp = createdIp;
        this.deviceOS = deviceOS;
        this.deviceModel = deviceModel;
        this.game = game;
        this.language = language;
        this.promotionAgree = promotionAgree;
    }
}
