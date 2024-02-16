package com.gamoon.gamoonbe.domain.users.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Keyword {
    대면("대면"),
    비대면("비대면"),
    조언자("조언자"),
    경청자("경청자"),
    일회성("일회성"),
    꾸준히("꾸준히");

    private final String keyword;
}