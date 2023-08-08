package com.gamepleconnect.content.reservation.repository;

import com.gamepleconnect.content.reservation.domain.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

}
