package com.gamoon.gamoonbe.domain.post.api;

import com.gamoon.gamoonbe.domain.post.application.PostService;
import com.gamoon.gamoonbe.domain.post.dto.PostSaveDto;
import com.gamoon.gamoonbe.global.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
    public ApiResponse<Long> savePost(@PathVariable Long userId, @RequestBody @Valid PostSaveDto postSaveDto) {
        Long savedPostId = postService.savePost(userId, postSaveDto);
        return ApiResponse.createSuccess(savedPostId);
    }


//    @GetMapping
//    public ApiResponse<> getPosts(@RequestParam("userId") Long userId) {
//
//    }

}
