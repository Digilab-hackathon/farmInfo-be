package com.example.farmInfo_be.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Builder;


@Getter
@NoArgsConstructor
public class ReportRequest {
    private Long phoneNumber;  // 숫자 타입으로 변경
    private String productName;
    private String area;
    private String ri;
    private String region;
    private String detailedArea;
    private String reportType;

    @Builder
    public ReportRequest(Long phoneNumber, String productName, String area,
                         String ri, String region, String detailedArea,
                         String reportType) {
        this.phoneNumber = phoneNumber;
        this.productName = productName;
        this.area = area;
        this.ri = ri;
        this.region = region;
        this.detailedArea = detailedArea;
        this.reportType = reportType;
    }
}