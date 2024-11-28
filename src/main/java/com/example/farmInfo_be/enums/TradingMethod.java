package com.example.farmInfo_be.enums;

import lombok.Getter;

@Getter
public enum TradingMethod {        // 거래 방식
    AUCTION("경매"),
    FIXED_PRICE("수의");

    private final String description;

    TradingMethod(String description) {
        this.description = description;
    }
}
