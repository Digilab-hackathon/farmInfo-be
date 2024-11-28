package com.example.farmInfo_be.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CombinedReportRequest {
    private MemberRequest memberRequest;
    private ReportRequest reportRequest;
}