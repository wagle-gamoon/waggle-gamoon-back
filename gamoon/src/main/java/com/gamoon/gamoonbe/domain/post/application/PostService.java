package com.gamoon.gamoonbe.domain.post.application;

import com.gamoon.gamoonbe.domain.category.domain.Category;
import com.gamoon.gamoonbe.domain.category.repository.CategoryRepository;
import com.gamoon.gamoonbe.domain.comment.dto.CommentResponseDto;
import com.gamoon.gamoonbe.domain.comment.repository.CommentRepository;
import com.gamoon.gamoonbe.domain.post.domain.Post;
import com.gamoon.gamoonbe.domain.post.dto.PostResponseDto;
import com.gamoon.gamoonbe.domain.post.dto.PostSaveDto;
import com.gamoon.gamoonbe.domain.post.repository.PostRepository;
import com.gamoon.gamoonbe.domain.users.domain.Users;
import com.gamoon.gamoonbe.domain.users.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {

    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final CategoryRepository categoryRepository;
    private final CommentRepository commentRepository;

    @Transactional
    public Long savePost(Long userId, PostSaveDto postSaveDto) {
        Users findUsers = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("회원이 존재하지 않습니다."));

        if (postSaveDto.getCategorySort().equals("스터디모집게시판")) {
            validationCount(postSaveDto);
        }

        Category findCategory = categoryRepository.findByCategorySort(postSaveDto.getCategorySort());

        return postRepository.save(postSaveDto.toEntity(findUsers, findCategory)).getPostId();
    }

    public List<PostResponseDto> getPostsByCategoryId(Long categoryId) {
        Category findCategory = categoryRepository.findById(categoryId).orElseThrow(() -> new IllegalArgumentException("게시글이 존재하지 않습니다!"));
        return postRepository.findPostsByCategory(findCategory);
    }

    public PostResponseDto getPost(Long postId) {
        PostResponseDto findPost = postRepository.findPostByPostId(postId);
        return findPost;
    }

    public List<CommentResponseDto> getComments(Long postId) {
        Post findPost = postRepository.findById(postId).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다."));

        List<CommentResponseDto> postComments = commentRepository.findCommentsByPost(findPost);

        return postComments;
    }

    private static void validationCount(PostSaveDto postSaveDto) {
        if (isExceed(postSaveDto.getTotalCount(), postSaveDto.getCurrentCount())) {
            throw new IllegalArgumentException("인원 수가 옳지 않습니다.");
        }
    }

    public static boolean isExceed(int totalCount, int currentCount) {
        return totalCount <= currentCount ? true : false;
    }
}
