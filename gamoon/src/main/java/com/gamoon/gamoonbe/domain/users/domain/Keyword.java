package com.gamoon.gamoonbe.domain.users.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Keyword {
    인기짱("인기짱");

    private final String keyword;
}