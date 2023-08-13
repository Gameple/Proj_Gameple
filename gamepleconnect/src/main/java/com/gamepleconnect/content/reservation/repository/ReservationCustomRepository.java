package com.gamepleconnect.content.reservation.repository;

import com.gamepleconnect.content.reservation.dto.ReservationRequestDto;
import com.gamepleconnect.root.game.domain.Game;

public interface ReservationCustomRepository {

    boolean existsByEmail(String email, Game game);
}
