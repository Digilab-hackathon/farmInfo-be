package com.example.farmInfo_be.dto.request;

import com.example.farmInfo_be.enums.Crop;
import com.example.farmInfo_be.enums.LandCategory;
import com.example.farmInfo_be.enums.OwnershipType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CultivationReportRequest {
    private String name;                // 성명
    private String birthDate;        // 생년월일
    private String address;             // 주소
    private String phoneNumber;         // 연락처
    private String homePhoneNumber;     // 연락처(자택)
    private Crop crop;                  // 작물 종류
    private String district;            // 시/군/구
    private String village;             // 읍/면/동
    private LandCategory landCategory;  // 토지 구분
    private Double totalArea;           // 총 면적(평)
    private Double cultivatedArea;      // 재배 면적(평)
    private OwnershipType ownershipType;// 경작 구분
}