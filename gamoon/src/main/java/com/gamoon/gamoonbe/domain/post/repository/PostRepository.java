package com.gamoon.gamoonbe.domain.post.repository;

import com.gamoon.gamoonbe.domain.category.domain.Category;
import com.gamoon.gamoonbe.domain.post.domain.Post;
import com.gamoon.gamoonbe.domain.post.dto.PostResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    @Query("select new com.gamoon.gamoonbe.domain.post.dto.PostResponseDto(p.postId, p.title, u.userNickname, p.createdAt) from Post p join p.user u where p.category = :category")
    List<PostResponseDto> findPostsByCategory(@Param("category") Category category);

    @Query("select new com.gamoon.gamoonbe.domain.post.dto.PostResponseDto(p.postId, p.title, u.userNickname, p.createdAt) from Post p join p.user u where p.postId = :postId")
    PostResponseDto findPostByPostId(@Param("postId") Long postId);

}
