package com.gamoon.gamoonbe.domain.post.api;

import com.gamoon.gamoonbe.domain.comment.dto.CommentResponseDto;
import com.gamoon.gamoonbe.domain.post.application.PostService;
import com.gamoon.gamoonbe.domain.post.dto.PostResponseDto;
import com.gamoon.gamoonbe.domain.post.dto.PostSaveDto;
import com.gamoon.gamoonbe.global.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "게시글 API", description = "게시글 CRUD API 입니다.")
@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;

    @Operation(summary = "게시글 등록", description = "")
    @Parameter(name = "userId", description = "게시글을 생성하는 유저의 id")
    @Parameter(name = "postSaveDto", description = "")
    @PostMapping("/{userId}")
    public ApiResponse<Long> savePost(@PathVariable Long userId, @RequestBody PostSaveDto postSaveDto) {
        Long savedPostId = postService.savePost(userId, postSaveDto);
        return ApiResponse.createSuccess(savedPostId);
    }

    @Operation(summary = "게시판별 게시글 조회", description = "")
    @Parameter(name = "categoryId", description = "게시판 유형 1('자유게시판'), 2('질문게시판'), 3('스터디모집게시판')")
    @GetMapping
    public ApiResponse<List<PostResponseDto>> getPostsByCategoryId(@RequestParam("categoryId") Long categoryId) {
        List<PostResponseDto> postList = postService.getPostsByCategoryId(categoryId);
        return ApiResponse.createSuccess(postList);
    }

    @Operation(summary = "게시글 단일 조회", description = "")
    @Parameter(name = "postId", description = "게시글 id")
    @GetMapping("/{postId}")
    public ApiResponse<PostResponseDto> getPost(@PathVariable Long postId) {
        PostResponseDto findPost = postService.getPost(postId);
        return ApiResponse.createSuccess(findPost);
    }

    @Operation(summary = "게시글의 모든 댓글 조회", description = "")
    @Parameter(name = "postId", description = "게시글 id")
    @GetMapping("/{postId}/comments")
    public ApiResponse<List<CommentResponseDto>> getComments(@PathVariable Long postId) {
        List<CommentResponseDto> postComments = postService.getComments(postId);
        return ApiResponse.createSuccess(postComments);
    }


}
