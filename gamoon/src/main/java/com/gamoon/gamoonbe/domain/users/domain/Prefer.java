package com.gamoon.gamoonbe.domain.users.domain;

import com.gamoon.gamoonbe.global.shared.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "prefer")
public class Prefer extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "prefer_id")
    private Long preferId;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private Users user;

    @Column(name = "prefer_interests", nullable = false)
    private String preferInterests;
}