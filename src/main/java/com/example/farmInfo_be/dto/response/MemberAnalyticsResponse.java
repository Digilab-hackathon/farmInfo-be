package com.example.farmInfo_be.dto.response;

import com.example.farmInfo_be.enums.Crop;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
@Builder
public class MemberAnalyticsResponse {
    private Map<Crop, Double> cropRatios;      // 품목별 재배 비율 (%)
    private Map<Crop, Double> yieldPerArea;    // 품목별 면적당 출하량 (kg/평)
}