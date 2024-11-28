package com.example.farmInfo_be.dto.response;

import com.example.farmInfo_be.domain.ShipmentReport;
import com.example.farmInfo_be.enums.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ShipmentResponseDto {       // 출하량 신고 응답
    private Long id;                     // 신고 ID
    private Long memberId;               // 회원 ID
    private Status status;               // 상태
    private LocalDate expectedShipDate;  // 출하 예정일
    private WholesaleCompany wholesaleCompany;  // 출하처
    private TradeType tradeType;         // 거래 유형
    private TradingMethod tradingMethod; // 거래 방식
    private String producerName;         // 생산자명
    private ProductionLocation productionLocation;  // 생산지 구분
    private Crop crop;                   // 작물 종류
    private Packaging packaging;         // 포장 상태
    private Double unit;                 // 출하량(kg)
    private Grade grade;                 // 등급
    private LocalDateTime createdAt;     // 생성일시

    public static ShipmentResponseDto from(ShipmentReport report) {
        return new ShipmentResponseDto(
                report.getId(),
                report.getMember().getId(),
                report.getStatus(),
                report.getExpectedShipDate(),
                report.getWholesaleCompany(),
                report.getTradeType(),
                report.getTradingMethod(),
                report.getProducerName(),
                report.getProductionLocation(),
                report.getCrop(),
                report.getPackaging(),
                report.getUnit(),
                report.getGrade(),
                report.getCreatedAt()
        );
    }
}