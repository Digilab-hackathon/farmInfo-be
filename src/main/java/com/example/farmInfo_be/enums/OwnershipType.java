package com.example.farmInfo_be.enums;

import lombok.Getter;

@Getter
public enum OwnershipType {        // 경작 구분
    SELF_OWNED("자경"),
    PURCHASED("매수");

    private final String description;

    OwnershipType(String description) {
        this.description = description;
    }
}
