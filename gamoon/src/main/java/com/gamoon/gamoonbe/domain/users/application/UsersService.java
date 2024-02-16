package com.gamoon.gamoonbe.domain.users.application;

import com.gamoon.gamoonbe.domain.users.dto.UsersSaveDto;
import com.gamoon.gamoonbe.domain.users.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class UsersService {

    private final UserRepository userRepository;

    @Transactional
    public Long saveUser(UsersSaveDto usersSaveDto) {
        return userRepository.save(usersSaveDto.toEntity()).getUserId();
    }
}
