package com.gamoon.gamoonbe.domain.match.domain;

import java.util.ArrayList;
import java.util.List;

import com.gamoon.gamoonbe.domain.users.domain.Gender;
import com.gamoon.gamoonbe.domain.users.domain.Prefer;
import com.gamoon.gamoonbe.domain.users.domain.Users;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Person {
    private Users user;
    private Prefer prefer;
    private String name;  // 이름
    private String interests;  // 관심분야
    private List<String> preferences;  // 선호도 목록
    private Boolean graduate; // 졸업여부 졸업생은 1, 재학생은 0


    public Person(Users user, Prefer prefer) {
        this.user = user;
        this.prefer = prefer;
        this.name = user.getUserNickname();
        this.interests = prefer.getPreferInterests();
        this.preferences = new ArrayList<>();
        this.graduate = user.getUserGraduate();
    }
}