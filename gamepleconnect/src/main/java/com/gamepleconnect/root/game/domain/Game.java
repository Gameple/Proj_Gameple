package com.gamepleconnect.root.game.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Entity
@Table(name = "gameple_game")
@NoArgsConstructor
public class Game {

    @Id
    @Column(name = "game_code")
    private Long gameCode;

    @Column(name = "game_alias", nullable = false)
    private String gameAlias;

    @Builder
    public Game(Long gameCode, String gameAlias) {
        this.gameCode = gameCode;
        this.gameAlias = gameAlias;
    }
}