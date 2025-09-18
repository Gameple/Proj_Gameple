package com.gamepleconnect.user.repository;

import com.gamepleconnect.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>, UserCustomRepository {

}
