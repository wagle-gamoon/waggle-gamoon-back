package com.gamoon.gamoonbe.domain.match.domain;

import com.gamoon.gamoonbe.domain.users.domain.Users;
import com.gamoon.gamoonbe.global.shared.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Builder
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "who_meet")
public class WhoMeet extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "who_meet_id")
    private Long whoMeetId;

    @ManyToOne
    @JoinColumn(name = "user1_id", nullable = false)
    private Users metUser1;

    @ManyToOne
    @JoinColumn(name = "user2_id", nullable = false)
    private Users metUser2;

    @Column(nullable = false)
    private LocalDateTime createdAt;
}