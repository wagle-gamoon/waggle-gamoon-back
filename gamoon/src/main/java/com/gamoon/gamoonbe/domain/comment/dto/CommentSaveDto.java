package com.gamoon.gamoonbe.domain.comment.dto;

import com.gamoon.gamoonbe.domain.comment.domain.Comment;
import com.gamoon.gamoonbe.domain.post.domain.Post;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentSaveDto {

    private String content;

    @Builder
    public CommentSaveDto(String content) {
        this.content = content;
    }

    public Comment toEntity(Post post) {
        return Comment.builder()
                .post(post)
                .content(content)
                .build();
    }
}
