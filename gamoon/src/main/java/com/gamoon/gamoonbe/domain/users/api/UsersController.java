package com.gamoon.gamoonbe.domain.users.api;

import com.gamoon.gamoonbe.domain.users.application.UsersService;
import com.gamoon.gamoonbe.domain.users.dto.UsersSaveDto;
import com.gamoon.gamoonbe.global.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "유저 API", description = "유저 등록, 조회 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
@Slf4j
public class UsersController {

    private final UsersService usersService;

    @Operation(summary = "유저 등록", description = "")
    @Parameter(name = "usersSaveDto", description = "")
    @PostMapping
    public ApiResponse<Long> saveUser(@RequestBody UsersSaveDto usersSaveDto) {
        Long savedUserId = usersService.saveUser(usersSaveDto);

        return ApiResponse.createSuccess(savedUserId);
    }




}
