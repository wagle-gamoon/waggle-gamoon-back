package com.gamoon.gamoonbe.domain.post.application;

import com.gamoon.gamoonbe.domain.category.domain.Category;
import com.gamoon.gamoonbe.domain.category.repository.CategoryRepository;
import com.gamoon.gamoonbe.domain.post.dto.PostSaveDto;
import com.gamoon.gamoonbe.domain.post.repository.PostRepository;
import com.gamoon.gamoonbe.domain.users.domain.Users;
import com.gamoon.gamoonbe.domain.users.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {

    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final CategoryRepository categoryRepository;

    @Transactional
    public Long savePost(Long userId, PostSaveDto postSaveDto) {
        Users findUsers = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("회원이 존재하지 않습니다."));

        if (isExceed(postSaveDto.getTotalCount(), postSaveDto.getCurrentCount())) {
            throw new IllegalArgumentException("인원 수가 옳지 않습니다.");
        }

        Category findCategory = categoryRepository.findByCategorySort(postSaveDto.getCategorySort());

        return postRepository.save(postSaveDto.toEntity(findUsers, findCategory)).getPostId();
    }

    public void getPosts() {

    }

    public static boolean isExceed(int totalCount, int currentCount) {
        return totalCount <= currentCount ? true : false;
    }
}
