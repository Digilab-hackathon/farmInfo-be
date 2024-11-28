package com.example.farmInfo_be.enums;

import lombok.Getter;

@Getter
public enum WholesaleCompany {     // 출하처
    JEJUNET("제주넷주식회사"),
    JEJU_NONGHYUP("제주농협"),
    JEJU_AGRICULTURE("제주도청 농정과"),
    JEJU_WHOLESALE("제주도 농산물 도매시");

    private final String description;

    WholesaleCompany(String description) {
        this.description = description;
    }
}