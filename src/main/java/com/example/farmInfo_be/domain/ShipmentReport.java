package com.example.farmInfo_be.domain;

import com.example.farmInfo_be.enums.*;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class ShipmentReport {            // 출하량 신고
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;                     // 신고 ID

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;               // 회원

    @Enumerated(EnumType.STRING)
    private Status status = Status.PENDING;   // 상태(PENDING/APPROVED/REJECTED)

    private LocalDate expectedShipDate;  // 출하 예정일

    @Enumerated(EnumType.STRING)
    private WholesaleCompany wholesaleCompany;  // 출하처

    @Enumerated(EnumType.STRING)
    private TradeType tradeType;         // 거래 유형

    @Enumerated(EnumType.STRING)
    private TradingMethod tradingMethod; // 거래 방식

    private String producerName;         // 생산자명

    @Enumerated(EnumType.STRING)
    private ProductionLocation productionLocation;  // 생산지 구분

    @Enumerated(EnumType.STRING)
    private Crop crop;                   // 작물 종류

    @Enumerated(EnumType.STRING)
    private Packaging packaging;         // 포장 상태

    private Double unit;                 // 출하량(kg)

    @Enumerated(EnumType.STRING)
    private Grade grade;                 // 등급

    @CreatedDate
    private LocalDateTime createdAt;     // 생성일시

    @Builder
    public ShipmentReport(Member member, LocalDate expectedShipDate, WholesaleCompany wholesaleCompany,
                          TradeType tradeType, TradingMethod tradingMethod, String producerName,
                          ProductionLocation productionLocation, Crop crop, Packaging packaging,
                          Double unit, Grade grade) {
        this.member = member;
        this.expectedShipDate = expectedShipDate;
        this.wholesaleCompany = wholesaleCompany;
        this.tradeType = tradeType;
        this.tradingMethod = tradingMethod;
        this.producerName = producerName;
        this.productionLocation = productionLocation;
        this.crop = crop;
        this.packaging = packaging;
        this.unit = unit;
        this.grade = grade;
    }

    public void approve() {
        this.status = Status.APPROVED;
    }

    public void reject() {
        this.status = Status.REJECTED;
    }
}