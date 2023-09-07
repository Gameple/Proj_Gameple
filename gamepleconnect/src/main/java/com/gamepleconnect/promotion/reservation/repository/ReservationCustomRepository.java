package com.gamepleconnect.promotion.reservation.repository;

import com.gamepleconnect.root.game.domain.Game;

public interface ReservationCustomRepository {

    boolean existsByEmail(String email, Game game);
}
