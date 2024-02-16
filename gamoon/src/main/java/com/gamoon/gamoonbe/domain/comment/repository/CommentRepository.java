package com.gamoon.gamoonbe.domain.comment.repository;

import com.gamoon.gamoonbe.domain.comment.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
