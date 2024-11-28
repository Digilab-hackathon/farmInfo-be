package com.example.farmInfo_be.enums;

import lombok.Getter;

@Getter
public enum LandCategory {         // 토지 구분
    PADDY_FIELD("논"),
    FIELD("밭"),
    ORCHARD("과수원"),
    MEADOW("목장");

    private final String description;

    LandCategory(String description) {
        this.description = description;
    }
}
