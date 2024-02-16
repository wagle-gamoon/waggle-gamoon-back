package com.gamoon.gamoonbe.domain.users.dto;

import com.gamoon.gamoonbe.domain.users.domain.Gender;
import com.gamoon.gamoonbe.domain.users.domain.Keyword;
import com.gamoon.gamoonbe.domain.users.domain.Users;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UsersSaveDto {

    private String userNickname;

    private Gender userGender;

    private Integer year;

    private List<Keyword> userKeywords;

    private Boolean userGraduate;

    private String userDepartment;


    @Builder
    public UsersSaveDto(String userNickname, Gender userGender, Integer year, List<Keyword> userKeywords, Boolean userGraduate, String userDepartment) {
        this.userNickname = userNickname;
        this.userGender = userGender;
        this.year = year;
        this.userKeywords = userKeywords;
        this.userGraduate = userGraduate;
        this.userDepartment = userDepartment;
    }

    public Users toEntity() {
        return Users.builder()
                .userNickname(userNickname)
                .userGender(userGender)
                .year(year)
                .userKeywords(userKeywords)
                .userGraduate(userGraduate)
                .userDepartment(userDepartment)
                .userMatchActive(true)
                .build();
    }

}
