package com.gamoon.gamoonbe.domain.post.repository;

import com.gamoon.gamoonbe.domain.post.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
