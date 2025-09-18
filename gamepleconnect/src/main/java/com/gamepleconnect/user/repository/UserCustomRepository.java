package com.gamepleconnect.user.repository;

import com.gamepleconnect.root.game.domain.Game;

public interface UserCustomRepository {

    boolean existsByEmail(String email);

    boolean existsByUserName(String username);
}
