package com.example.farmInfo_be.enums;

import lombok.Getter;

@Getter
public enum Packaging {            // 포장 상태
    PACKAGED("포장"),
    UNPACKAGED("비포장");

    private final String description;

    Packaging(String description) {
        this.description = description;
    }
}
