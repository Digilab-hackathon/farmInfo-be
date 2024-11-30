package com.example.farmInfo_be.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ShipmentReportWithMemberDto {
    private ShipmentResponseDto shipmentReport;
    private MemberReponseDtoWithoutReport memberInfo;
}