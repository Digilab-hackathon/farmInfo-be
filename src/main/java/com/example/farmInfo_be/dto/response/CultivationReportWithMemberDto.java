package com.example.farmInfo_be.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CultivationReportWithMemberDto {
    private CultivationResponseDto cultivationReport;
    private MemberReponseDtoWithoutReport memberInfo;
}