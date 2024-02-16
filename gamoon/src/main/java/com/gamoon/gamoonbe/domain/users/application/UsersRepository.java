package com.gamoon.gamoonbe.domain.users.application;

import com.gamoon.gamoonbe.domain.users.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users, Long> {
}
