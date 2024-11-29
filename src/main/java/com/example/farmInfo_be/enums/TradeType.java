package com.example.farmInfo_be.enums;

import lombok.Getter;

@Getter
public enum TradeType {            // 거래 유형
    CONSIGNMENT("위탁"),
    PURCHASE("매수");

    private final String description;

    TradeType(String description) {
        this.description = description;
    }
}
