package com.gamoon.gamoonbe.domain.users.domain;

import com.gamoon.gamoonbe.global.shared.BaseTimeEntity;
import jakarta.annotation.PreDestroy;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Users extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "user_nickname", nullable = true)
    private String userNickname;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_gender", nullable = true)
    private Gender userGender;

    @Column(name = "year", nullable = true)
    private Integer year;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "user_keywords", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "user_keywords", nullable = true)
    private List<Keyword> userKeywords;

    @Column(name = "user_graduate", nullable = true)
    private Boolean userGraduate;

    @Column(name = "user_department", nullable = true)
    private String userDepartment;

    @Column(name = "user_match_active", nullable = false)
    private Boolean userMatchActive;

    @Column()
    private LocalDateTime deletedAt;

    @Builder
    public Users(String userNickname, Gender userGender, Integer year, List<Keyword> userKeywords, Boolean userGraduate, Boolean userMatchActive, String userDepartment) {
        this.userNickname = userNickname;
        this.userGender = userGender;
        this.year = year;
        this.userKeywords = userKeywords;
        this.userGraduate = userGraduate;
        this.userMatchActive = userMatchActive;
        this.userDepartment = userDepartment;
    }

    @PreDestroy()
    public void preDestroy() {
        this.deletedAt = LocalDateTime.now();
    }
}
