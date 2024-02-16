package com.gamoon.gamoonbe.domain.match.domain;

import java.util.ArrayList;
import java.util.List;

import com.gamoon.gamoonbe.domain.users.domain.Prefer;
import com.gamoon.gamoonbe.domain.users.domain.Users;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Student extends Person {
    private boolean engaged;  // 재학생의 현재 매칭 상태
    private List<Alumni> proposed; // 재학생의 프로포즈한 졸업생 목록

    public Student(Users user, Prefer prefer) {
        super(user, prefer);
        this.engaged = false;
        this.proposed = new ArrayList<>();
    }
}
