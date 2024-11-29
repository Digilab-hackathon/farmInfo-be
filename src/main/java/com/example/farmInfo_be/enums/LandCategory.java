package com.example.farmInfo_be.enums;

import lombok.Getter;

@Getter
public enum LandCategory {
    PADDY_FIELD("답(논)"),
    FIELD("전(밭)"),
    ORCHARD("과수원"),
    PASTURE("목장용지"),
    MISCELLANEOUS("잡종지"),
    FOREST("임야(산지)"),
    FARM_HUT("농막"),
    VINYL_HOUSE("비닐하우스"),
    GREENHOUSE("고정식 온실");

    private final String description;

    LandCategory(String description) {
        this.description = description;
    }
}