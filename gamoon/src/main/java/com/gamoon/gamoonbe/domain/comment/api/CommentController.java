package com.gamoon.gamoonbe.domain.comment.api;

import com.gamoon.gamoonbe.domain.comment.application.CommentService;
import com.gamoon.gamoonbe.domain.comment.dto.CommentSaveDto;
import com.gamoon.gamoonbe.global.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "댓글 API", description = "댓글 CRUD API 입니다.")
@RestController
@RequiredArgsConstructor
@RequestMapping("/comments")
public class CommentController {

    private final CommentService commentService;

    @Operation(summary = "댓글 등록", description = "")
    @Parameter(name = "postId", description = "게시글 id")
    @Parameter(name = "commentSaveDto", description = "")
    @PostMapping("/{postId}")
    public ApiResponse<Long> saveComment(@PathVariable Long postId, @RequestBody CommentSaveDto commentSaveDto) {
        Long savedCommentId = commentService.saveComment(postId, commentSaveDto);
        return ApiResponse.createSuccess(savedCommentId);
    }


}
