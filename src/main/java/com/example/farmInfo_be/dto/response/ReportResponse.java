package com.example.farmInfo_be.dto.response;

import com.example.farmInfo_be.ReportStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Builder;
import com.example.farmInfo_be.domain.Report;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class ReportResponse {
    private Long reportId;
    private String productName;
    private Long area;
    private String ri;
    private String region;
    private Long detailedArea;
    private String reportType;
    private ReportStatus status;
    private LocalDateTime createTime;
    private MemberResponse member;

    @Builder
    public ReportResponse(String productName, Long area, String ri, String region,
                          Long detailedArea, String reportType, ReportStatus status,
                          LocalDateTime createTime, MemberResponse member,
                          Long reportId) {
        this.productName = productName;
        this.area = area;
        this.ri = ri;
        this.region = region;
        this.detailedArea = detailedArea;
        this.reportType = reportType;
        this.status = status;
        this.createTime = createTime;
        this.member = member;
        this.reportId = reportId;
    }

    public static ReportResponse from(Report report) {
        return ReportResponse.builder()
                .reportId(report.getReportId())
                .productName(report.getProductName())
                .area(report.getArea())
                .ri(report.getRi())
                .region(report.getCategory())
                .detailedArea(report.getDetailedArea())
                .reportType(report.getReportType())
                .status(report.getStatus())
                .createTime(report.getCreateTime())
                .member(MemberResponse.from(report.getMember()))
                .build();
    }
}