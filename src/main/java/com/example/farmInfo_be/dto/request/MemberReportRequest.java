package com.example.farmInfo_be.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MemberReportRequest {
    private String name;                // 성명
    private String birthDate;        // 생년월일
    private String address;             // 주소
    private String phoneNumber;         // 연락처
    private String homePhoneNumber;

}
