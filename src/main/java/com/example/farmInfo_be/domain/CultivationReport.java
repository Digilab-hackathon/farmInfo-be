package com.example.farmInfo_be.domain;

import com.example.farmInfo_be.enums.Crop;
import com.example.farmInfo_be.enums.LandCategory;
import com.example.farmInfo_be.enums.OwnershipType;
import com.example.farmInfo_be.enums.Status;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class CultivationReport {         // 재배면적 신고
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;                     // 신고 ID

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;               // 회원

    @Enumerated(EnumType.STRING)
    private Status status = Status.PENDING;  // 상태(PENDING/APPROVED/REJECTED)

    @Enumerated(EnumType.STRING)
    private Crop crop;                   // 작물 종류

    private String district;             // 시/군/구
    private String village;              // 읍/면/동

    @Enumerated(EnumType.STRING)
    private LandCategory landCategory;   // 토지 구분

    private Double totalArea;            // 총 면적(평)
    private Double cultivatedArea;       // 재배 면적(평)

    @Enumerated(EnumType.STRING)
    private OwnershipType ownershipType; // 경작 구분

    @CreatedDate
    private LocalDateTime createdAt;     // 생성일시

    @Builder
    public CultivationReport(Member member, Crop crop, String district, String village,
                             LandCategory landCategory, Double totalArea, Double cultivatedArea,
                             OwnershipType ownershipType) {
        this.member = member;
        this.crop = crop;
        this.district = district;
        this.village = village;
        this.landCategory = landCategory;
        this.totalArea = totalArea;
        this.cultivatedArea = cultivatedArea;
        this.ownershipType = ownershipType;
    }

    public void approve() {
        this.status = Status.APPROVED;
    }

    public void reject() {
        this.status = Status.REJECTED;
    }
}
