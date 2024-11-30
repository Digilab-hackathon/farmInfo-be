package com.example.farmInfo_be.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ShipmentReportWithMemberDto {
    private ShipmentResponseDto cultivationReport;
    private MemberResponseDto memberInfo;
}