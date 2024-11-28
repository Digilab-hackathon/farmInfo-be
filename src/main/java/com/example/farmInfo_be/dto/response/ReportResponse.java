package com.example.farmInfo_be.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Builder;
import com.example.farmInfo_be.domain.Report;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class ReportResponse {
    private String productName;
    private String area;
    private String ri;
    private String region;
    private String detailedArea;
    private String reportType;
    private boolean approved;
    private LocalDateTime createTime;
    private MemberResponse member;

    @Builder
    public ReportResponse(String productName, String area, String ri, String region,
                          String detailedArea, String reportType, boolean approved,
                          LocalDateTime createTime, MemberResponse member) {
        this.productName = productName;
        this.area = area;
        this.ri = ri;
        this.region = region;
        this.detailedArea = detailedArea;
        this.reportType = reportType;
        this.approved = approved;
        this.createTime = createTime;
        this.member = member;
    }

    public static ReportResponse from(Report report) {
        return ReportResponse.builder()
                .productName(report.getProductName())
                .area(report.getArea())
                .ri(report.getRi())
                .region(report.getRegion())
                .detailedArea(report.getDetailedArea())
                .reportType(report.getReportType())
                .approved(report.isApproved())
                .createTime(report.getCreateTime())
                .member(MemberResponse.from(report.getMember()))
                .build();
    }
}