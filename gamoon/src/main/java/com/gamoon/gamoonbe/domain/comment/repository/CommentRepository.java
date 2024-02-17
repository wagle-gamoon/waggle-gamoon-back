package com.gamoon.gamoonbe.domain.comment.repository;

import com.gamoon.gamoonbe.domain.comment.domain.Comment;
import com.gamoon.gamoonbe.domain.comment.dto.CommentResponseDto;
import com.gamoon.gamoonbe.domain.post.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query("select new com.gamoon.gamoonbe.domain.comment.dto.CommentResponseDto(u.userNickname, c.content) from Comment c join c.user u where c.post = :post")
    List<CommentResponseDto> findCommentsByPost(Post post);
}
