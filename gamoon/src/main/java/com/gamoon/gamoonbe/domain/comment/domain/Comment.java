package com.gamoon.gamoonbe.domain.comment.domain;

import com.gamoon.gamoonbe.domain.post.domain.Post;
import com.gamoon.gamoonbe.domain.users.domain.Users;
import com.gamoon.gamoonbe.global.shared.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Comment extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long commentId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    private Users user;

    private String content;

    @Builder
    public Comment(Post post, Users user, String content) {
        this.post = post;
        this.user = user;
        this.content = content;
    }
}
