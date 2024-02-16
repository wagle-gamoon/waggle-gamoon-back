package com.gamoon.gamoonbe.domain.post.domain;

import com.gamoon.gamoonbe.domain.users.domain.Users;
import com.gamoon.gamoonbe.global.shared.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Post extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long postId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "users_id")
    private Users user;

    private String title;
    private String place;
    private String content;
    private int totalCount;
    private int currentCount;

}
