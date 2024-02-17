package com.gamoon.gamoonbe.domain.post.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostResponseDto {

    private Long postId;
    private String title;
    private String userNickname;
    private LocalDateTime createAt;

    public PostResponseDto(Long postId, String title, String userNickname, LocalDateTime createAt) {
        this.postId = postId;
        this.title = title;
        this.userNickname = userNickname;
        this.createAt = createAt;
    }
}
