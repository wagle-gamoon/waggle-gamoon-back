package com.gamoon.gamoonbe.domain.comment.application;

import com.gamoon.gamoonbe.domain.comment.dto.CommentSaveDto;
import com.gamoon.gamoonbe.domain.comment.repository.CommentRepository;
import com.gamoon.gamoonbe.domain.post.domain.Post;
import com.gamoon.gamoonbe.domain.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommentService {

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    @Transactional
    public Long saveComment(Long postId, CommentSaveDto commentSaveDto) {

        Post post = postRepository.findById(postId).orElseThrow(() -> new IllegalArgumentException("게시글이 존재하지 않습니다."));

        return commentRepository.save(commentSaveDto.toEntity(post)).getCommentId();

    }
}
