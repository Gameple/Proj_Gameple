package com.gamepleconnect.promotion.reservation.repository;

import com.gamepleconnect.promotion.reservation.domain.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Long>, ReservationCustomRepository {

}
