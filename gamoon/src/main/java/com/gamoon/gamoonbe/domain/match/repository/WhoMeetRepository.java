package com.gamoon.gamoonbe.domain.match.repository;

import com.gamoon.gamoonbe.domain.match.domain.WhoMeet;
import com.gamoon.gamoonbe.domain.users.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WhoMeetRepository extends JpaRepository<WhoMeet, Long> {
    // 특정 여성 사용자와 매칭된 남성 목록 조회
    List<WhoMeet> findByMetUser1(Users user);

    // 특정 남성 사용자와 매칭된 여성 목록 조회
    List<WhoMeet> findByMetUser2(Users user);
}