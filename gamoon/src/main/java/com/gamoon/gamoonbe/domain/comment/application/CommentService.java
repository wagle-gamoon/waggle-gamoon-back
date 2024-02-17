package com.gamoon.gamoonbe.domain.comment.application;

import com.gamoon.gamoonbe.domain.comment.dto.CommentSaveDto;
import com.gamoon.gamoonbe.domain.comment.repository.CommentRepository;
import com.gamoon.gamoonbe.domain.post.domain.Post;
import com.gamoon.gamoonbe.domain.post.repository.PostRepository;
import com.gamoon.gamoonbe.domain.users.domain.Users;
import com.gamoon.gamoonbe.domain.users.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommentService {

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;

    @Transactional
    public Long saveComment(Long postId, CommentSaveDto commentSaveDto) {

        Post findPost = postRepository.findById(postId).orElseThrow(() -> new IllegalArgumentException("게시글이 존재하지 않습니다."));
        Users findUser = userRepository.findById(commentSaveDto.getUserId()).orElseThrow(() -> new IllegalArgumentException("회원이 존재하지 않습니다!"));

        return commentRepository.save(commentSaveDto.toEntity(findPost, findUser)).getCommentId();

    }
}
