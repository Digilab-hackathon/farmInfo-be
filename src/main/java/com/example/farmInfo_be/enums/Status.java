package com.example.farmInfo_be.enums;

import lombok.Getter;

@Getter
public enum Status {               // 신고 상태
    PENDING("대기"),              // 승인 대기
    APPROVED("승인"),            // 승인됨
    REJECTED("반려");            // 반려됨

    private final String description;

    Status(String description) {
        this.description = description;
    }
}