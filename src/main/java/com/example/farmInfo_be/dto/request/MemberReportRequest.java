package com.example.farmInfo_be.dto.request;

import java.time.LocalDate;

public class MemberReportRequest {
    private String name;                // 성명
    private LocalDate birthDate;        // 생년월일
    private String address;             // 주소
    private String phoneNumber;         // 연락처
    private String homePhoneNumber;
}
