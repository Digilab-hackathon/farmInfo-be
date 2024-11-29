package com.example.farmInfo_be.enums;

import lombok.Getter;

@Getter
public enum Grade {                // 등급
    PREMIUM("상"),
    INTERMEDIATE("중"),
    BASIC("하");

    private final String description;

    Grade(String description) {
        this.description = description;
    }
}
