package com.example.farmInfo_be.dto.request;

import com.example.farmInfo_be.enums.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class ShipmentReportRequest {
    private String name;                // 성명
    private LocalDate birthDate;        // 생년월일
    private String address;             // 주소
    private String phoneNumber;         // 연락처
    private String homePhoneNumber;     // 연락처(자택)
    private LocalDate expectedShipDate; // 출하 예정일
    private WholesaleCompany wholesaleCompany;  // 출하처
    private TradeType tradeType;        // 거래 유형
    private TradingMethod tradingMethod;// 거래 방식
    private String producerName;        // 생산자명
    private ProductionLocation productionLocation;  // 생산지 구분
    private Crop crop;                  // 작물 종류
    private Packaging packaging;        // 포장 상태
    private Double unit;                // 출하량(kg)
    private Grade grade;                // 등급
}