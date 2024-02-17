package com.gamoon.gamoonbe.domain.comment.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentResponseDto {

    private String userNickname;
    private String content;

    public CommentResponseDto(String userNickname, String content) {
        this.userNickname = userNickname;
        this.content = content;
    }
}
