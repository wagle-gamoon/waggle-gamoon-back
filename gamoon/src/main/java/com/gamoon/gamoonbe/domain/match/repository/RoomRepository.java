package com.gamoon.gamoonbe.domain.match.repository;

import com.gamoon.gamoonbe.domain.match.domain.MatchRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface RoomRepository extends JpaRepository<MatchRoom, Long> {
    Optional<MatchRoom> findByRoomId(Long roomId);

    @Query("SELECT cr FROM match_room cr WHERE (cr.user1.userId = :userId OR cr.user2.userId = :userId) AND cr.roomActive = TRUE")
    Optional<MatchRoom> findActiveMatchRoomByUserId(@Param("userId") Long userId);

}