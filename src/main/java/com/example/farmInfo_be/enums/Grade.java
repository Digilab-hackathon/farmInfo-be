package com.example.farmInfo_be.enums;

import lombok.Getter;

@Getter
public enum Grade {                // 등급
    PREMIUM("특"),
    STANDARD("상"),
    SECONDARY("보통");

    private final String description;

    Grade(String description) {
        this.description = description;
    }
}
