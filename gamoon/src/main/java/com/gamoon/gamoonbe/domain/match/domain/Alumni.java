package com.gamoon.gamoonbe.domain.match.domain;

import java.util.ArrayList;
import java.util.List;

import com.gamoon.gamoonbe.domain.users.domain.Prefer;
import com.gamoon.gamoonbe.domain.users.domain.Users;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Alumni extends Person {
    private String partner;
    private Long partnerUserId;  // 현재 파트너
    private List<Student> proposals; // 졸업생이 재학생에게 받은 고백 목록

    public Alumni(Users user, Prefer prefer) {
        super(user, prefer);
        this.partner = null;
        this.partnerUserId = null;
        this.proposals = new ArrayList<>();
    }
    public Long getUserId() {
        return this.getUser().getUserId();
    }
}
