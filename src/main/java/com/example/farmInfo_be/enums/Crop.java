package com.example.farmInfo_be.enums;

import lombok.Getter;

@Getter
public enum Crop {                    // 작물 종류
    CABBAGE("양배추"),
    RADISH("무"),
    CARROT("당근"),
    GARLIC("마늘"),
    ONION("양파"),
    BROCCOLI("브로콜리"),
    GREEN_ONION("쪽파"),
    KOHLRABI("콜라비"),
    WINTER_CABBAGE("월동배추"),
    RED_CABBAGE("적채"),
    CHERRY_TOMATO("방울토마토");

    private final String description;

    Crop(String description) {
        this.description = description;
    }
}