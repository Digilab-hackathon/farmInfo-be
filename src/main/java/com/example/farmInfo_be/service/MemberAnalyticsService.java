package com.example.farmInfo_be.service;

import com.example.farmInfo_be.domain.CultivationReport;
import com.example.farmInfo_be.domain.Member;
import com.example.farmInfo_be.domain.ShipmentReport;
import com.example.farmInfo_be.dto.response.MemberAnalyticsResponse;
import com.example.farmInfo_be.enums.Crop;
import com.example.farmInfo_be.enums.Status;
import com.example.farmInfo_be.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberAnalyticsService {
    private final MemberRepository memberRepository;

    public MemberAnalyticsResponse getAnalytics(String phoneNumber) {
        Member member = memberRepository.findByPhoneNumber(phoneNumber)
                .orElseThrow(() -> new IllegalArgumentException("Member not found"));

        List<CultivationReport> approvedCultivationReports = member.getCultivationReports().stream()
                .filter(report -> report.getStatus() == Status.APPROVED)
                .collect(Collectors.toList());

        List<ShipmentReport> approvedShipmentReports = member.getShipmentReports().stream()
                .filter(report -> report.getStatus() == Status.APPROVED)
                .collect(Collectors.toList());

        Map<Crop, Double> cropRatios = calculateCropRatios(approvedCultivationReports);
        Map<Crop, Double> yieldPerArea = calculateYieldPerArea(
                approvedCultivationReports,
                approvedShipmentReports
        );
        return MemberAnalyticsResponse.builder()
                .cropRatios(cropRatios)
                .yieldPerArea(yieldPerArea)
                .build();
    }

    private Map<Crop, Double> calculateCropRatios(List<CultivationReport> reports) {
        Map<Crop, Double> cropAreas = new HashMap<>();
        double totalArea = 0.0;

        // 2024년 데이터만 집계
        for (CultivationReport report : reports) {
            if (report.getCreatedAt().getYear() == 2024) {
                cropAreas.merge(report.getCrop(), report.getCultivatedArea(), Double::sum);
                totalArea += report.getCultivatedArea();
            }
        }

        // 비율 계산
        double finalTotalArea = totalArea;
        return cropAreas.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        e -> (e.getValue() / finalTotalArea) * 100
                ));
    }

    private Map<Crop, Double> calculateYieldPerArea(
            List<CultivationReport> cultivationReports,
            List<ShipmentReport> shipmentReports
    ) {
        Map<Crop, Double> cultivationAreas = new HashMap<>();
        Map<Crop, Double> shipmentAmounts = new HashMap<>();

        // 2024년 재배 면적 집계
        for (CultivationReport report : cultivationReports) {
            if (report.getCreatedAt().getYear() == 2024) {
                cultivationAreas.merge(report.getCrop(), report.getCultivatedArea(), Double::sum);
            }
        }

        // 2024년 출하량 집계
        for (ShipmentReport report : shipmentReports) {
            if (report.getCreatedAt().getYear() == 2024) {
                shipmentAmounts.merge(report.getCrop(), report.getUnit(), Double::sum);
            }
        }

        // 면적당 출하량 계산 (kg/평)
        return cultivationAreas.entrySet().stream()
                .filter(e -> shipmentAmounts.containsKey(e.getKey()) && e.getValue() > 0)
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        e -> shipmentAmounts.get(e.getKey()) / e.getValue()
                ));
    }

    public MemberAnalyticsResponse getAnalyticsPrevYear(String phoneNumber) {
        Member member = memberRepository.findByPhoneNumber(phoneNumber)
                .orElseThrow(() -> new IllegalArgumentException("Member not found"));

        List<CultivationReport> approvedCultivationReports = member.getCultivationReports().stream()
                .filter(report -> report.getStatus() == Status.APPROVED)
                .collect(Collectors.toList());

        List<ShipmentReport> approvedShipmentReports = member.getShipmentReports().stream()
                .filter(report -> report.getStatus() == Status.APPROVED)
                .collect(Collectors.toList());

        Map<Crop, Double> cropRatios = calculateCropRatiosPrevYear(approvedCultivationReports);
        Map<Crop, Double> yieldPerArea = calculateYieldPerAreaPrevYear(
                approvedCultivationReports,
                approvedShipmentReports
        );

        return MemberAnalyticsResponse.builder()
                .cropRatios(cropRatios)
                .yieldPerArea(yieldPerArea)
                .build();
    }

    private Map<Crop, Double> calculateCropRatiosPrevYear(List<CultivationReport> reports) {
        Map<Crop, Double> cropAreas = new HashMap<>();
        double totalArea = 0.0;

        // 2023년 데이터만 집계
        for (CultivationReport report : reports) {
            if (report.getCreatedAt().getYear() == 2023) {
                cropAreas.merge(report.getCrop(), report.getCultivatedArea(), Double::sum);
                totalArea += report.getCultivatedArea();
            }
        }

        // 비율 계산
        double finalTotalArea = totalArea;
        return cropAreas.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        e -> (e.getValue() / finalTotalArea) * 100
                ));
    }

    private Map<Crop, Double> calculateYieldPerAreaPrevYear(
            List<CultivationReport> cultivationReports,
            List<ShipmentReport> shipmentReports
    ) {
        Map<Crop, Double> cultivationAreas = new HashMap<>();
        Map<Crop, Double> shipmentAmounts = new HashMap<>();

        // 2023년 재배 면적 집계
        for (CultivationReport report : cultivationReports) {
            if (report.getCreatedAt().getYear() == 2023) {
                cultivationAreas.merge(report.getCrop(), report.getCultivatedArea(), Double::sum);
            }
        }

        // 2023년 출하량 집계
        for (ShipmentReport report : shipmentReports) {
            if (report.getCreatedAt().getYear() == 2023) {
                shipmentAmounts.merge(report.getCrop(), report.getUnit(), Double::sum);
            }
        }

        // 면적당 출하량 계산 (kg/평)
        return cultivationAreas.entrySet().stream()
                .filter(e -> shipmentAmounts.containsKey(e.getKey()) && e.getValue() > 0)
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        e -> shipmentAmounts.get(e.getKey()) / e.getValue()
                ));
    }
}