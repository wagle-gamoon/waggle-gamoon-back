package com.gamoon.gamoonbe.domain.post.dto;

import com.gamoon.gamoonbe.domain.post.domain.Post;
import com.gamoon.gamoonbe.domain.users.domain.Users;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostSaveDto {

    private String title;
    private String place;
    private String content;
    private int totalCount;
    private int currentCount;

    @Builder
    public PostSaveDto(String title, String place, String content, int totalCount, int currentCount) {
        this.title = title;
        this.place = place;
        this.content = content;
        this.totalCount = totalCount;
        this.currentCount = currentCount;
    }

    public Post toEntity(Users users) {
        return Post.builder()
                .user(users)
                .title(title)
                .place(place)
                .content(content)
                .totalCount(totalCount)
                .currentCount(currentCount)
                .build();
    }

}
