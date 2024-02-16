package com.gamoon.gamoonbe.domain.users.repository;

import com.gamoon.gamoonbe.domain.users.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users, Long> {
}
