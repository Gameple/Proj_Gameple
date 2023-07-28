package com.gamepleconnect.content.reservation.domain;

import com.gamepleconnect.root.game.domain.Game;
import com.gamepleconnect.root.language.domain.Language;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Getter
@Entity
@Table(name = "gameple_cs_advance_reservation")
@NoArgsConstructor
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reservation_id")
    private Long id;

    @Column(name = "user_email")
    private String email;

    @Column(name = "user_phone")
    private String phoneNum;

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
}
