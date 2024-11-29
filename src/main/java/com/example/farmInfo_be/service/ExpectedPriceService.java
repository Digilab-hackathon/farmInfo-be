package com.example.farmInfo_be.service;

import com.example.farmInfo_be.enums.Crop;
import com.example.farmInfo_be.repository.CropPricingMap;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExpectedPriceService {
    private final CultivationAreaService areaService;
    private final CultivationReportService reportService;

    public Integer calculate(Crop crop) {
        double approvedArea = reportService.getApprovedAreaByCrop(crop);
        int optimalArea = areaService.get(crop);
        int basePrice = CropPricingMap.cropPricingMap.get(crop);

        return (int)(basePrice * (2 - (approvedArea / optimalArea)));
    }
}
