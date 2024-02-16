package com.gamoon.gamoonbe.domain.users.repository;

import com.gamoon.gamoonbe.domain.users.domain.Gender;
import com.gamoon.gamoonbe.domain.users.domain.Prefer;
import com.gamoon.gamoonbe.domain.users.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PreferRepository extends JpaRepository<Prefer, Long> {
    Optional<Prefer> findById(Long preferId);

    List<Prefer> findAll();

    Optional<Prefer> findByUser(Users user);
//    @Query("SELECT p.preferInterests FROM prefer p WHERE p.user.userId = :userId")
//    Optional<String> findPreferInterestsByUserId(@Param("userId") Long userId);

    @Query("SELECT p FROM prefer p " +
            "INNER JOIN p.user u " +
            "WHERE u.userMatchActive = true " +
            "AND u.userGraduate = :graduate")
    List<Prefer> findByUserMatchActiveAndGraduateStatus(@Param("graduate") boolean graduate);

}