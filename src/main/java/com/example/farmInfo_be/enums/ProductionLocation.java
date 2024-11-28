package com.example.farmInfo_be.enums;

import lombok.Getter;

@Getter
public enum ProductionLocation {   // 생산지 구분
    GREENHOUSE("시설"),
    OUTDOOR("노지"),
    VINYL_HOUSE("비닐하우스");

    private final String description;

    ProductionLocation(String description) {
        this.description = description;
    }
}
