package com.gamoon.gamoonbe.domain.comment.dto;

import com.gamoon.gamoonbe.domain.comment.domain.Comment;
import com.gamoon.gamoonbe.domain.post.domain.Post;
import com.gamoon.gamoonbe.domain.users.domain.Users;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentSaveDto {

    private Long userId;
    private String content;

    @Builder
    public CommentSaveDto(String content) {
        this.content = content;
    }

    public Comment toEntity(Post post, Users user) {
        return Comment.builder()
                .post(post)
                .user(user)
                .content(content)
                .build();
    }
}
