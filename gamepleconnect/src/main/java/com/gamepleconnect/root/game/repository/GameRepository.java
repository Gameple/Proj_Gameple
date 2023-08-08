package com.gamepleconnect.root.game.repository;

import com.gamepleconnect.root.game.domain.Game;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GameRepository extends JpaRepository<Game, Long> {

    Optional<Game> findByGameCode(Long gameCode);
}
