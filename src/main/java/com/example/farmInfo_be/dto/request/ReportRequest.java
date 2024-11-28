package com.example.farmInfo_be.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Builder;


@Getter
@NoArgsConstructor
public class ReportRequest {
    private String productName;
    private Long area;
    private String ri;
    private String category;
    private String region;
    private Long detailedArea;
    private String reportType;

    @Builder
    public ReportRequest(String productName, Long area,
                         String ri, String category, Long detailedArea,
                         String reportType, String region) {
        this.productName = productName;
        this.area = area;
        this.ri = ri;
        this.category = category;
        this.detailedArea = detailedArea;
        this.reportType = reportType;
        this.region = region;
    }
}