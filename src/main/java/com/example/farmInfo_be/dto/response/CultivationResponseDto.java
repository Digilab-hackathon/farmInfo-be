package com.example.farmInfo_be.dto.response;

import com.example.farmInfo_be.domain.CultivationReport;
import com.example.farmInfo_be.enums.Crop;
import com.example.farmInfo_be.enums.LandCategory;
import com.example.farmInfo_be.enums.OwnershipType;
import com.example.farmInfo_be.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class CultivationResponseDto {    // 재배면적 신고 응답
    private Long id;                     // 신고 ID
    private Long memberId;               // 회원 ID
    private Status status;               // 상태
    private Crop crop;                   // 작물 종류
    private String district;             // 시/군/구
    private String village;              // 읍/면/동
    private LandCategory landCategory;   // 토지 구분
    private Double totalArea;            // 총 면적(평)
    private Double cultivatedArea;       // 재배 면적(평)
    private OwnershipType ownershipType; // 경작 구분
    private LocalDateTime createdAt;     // 생성일시

    public static CultivationResponseDto from(CultivationReport report) {
        return new CultivationResponseDto(
                report.getId(),
                report.getMember().getId(),
                report.getStatus(),
                report.getCrop(),
                report.getDistrict(),
                report.getVillage(),
                report.getLandCategory(),
                report.getTotalArea(),
                report.getCultivatedArea(),
                report.getOwnershipType(),
                report.getCreatedAt()
        );
    }
}
