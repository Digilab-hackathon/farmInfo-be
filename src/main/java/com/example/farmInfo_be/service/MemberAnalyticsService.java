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
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberAnalyticsService {
    private final MemberRepository memberRepository;

    public MemberAnalyticsResponse getAnalytics(String phoneNumber) {
        Member member = memberRepository.findByPhoneNumber(phoneNumber)
                .orElseThrow(() -> new IllegalArgumentException("Member not found"));

        Map<Crop, Double> cropRatios = calculateCropRatios(member.getCultivationReports());
        Map<Crop, Double> yieldPerArea = calculateYieldPerArea(
                member.getCultivationReports(),
                member.getShipmentReports()
        );

        return MemberAnalyticsResponse.builder()
                .cropRatios(cropRatios)
                .yieldPerArea(yieldPerArea)
                .build();
    }

    private Map<Crop, Double> calculateCropRatios(List<CultivationReport> reports) {
        Map<Crop, Double> cropAreas = new HashMap<>();
        double totalArea = 0.0;

        // 승인된 재배 신고만 집계 (현재 년도)
        for (CultivationReport report : reports) {
            if (report.getStatus() == Status.APPROVED &&
                    report.getCreatedAt().getYear() == LocalDate.now().getYear()) {
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

        // 승인된 재배 면적 집계 (현재 년도)
        for (CultivationReport report : cultivationReports) {
            if (report.getStatus() == Status.APPROVED &&
                    report.getCreatedAt().getYear() == LocalDate.now().getYear()) {
                cultivationAreas.merge(report.getCrop(), report.getCultivatedArea(), Double::sum);
            }
        }

        // 승인된 출하량 집계 (현재 년도)
        for (ShipmentReport report : shipmentReports) {
            if (report.getStatus() == Status.APPROVED &&
                    report.getCreatedAt().getYear() == LocalDate.now().getYear()) {
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

        Map<Crop, Double> cropRatios = calculateCropRatiosPrevYear(member.getCultivationReports());
        Map<Crop, Double> yieldPerArea = calculateYieldPerAreaPrevYear(
                member.getCultivationReports(),
                member.getShipmentReports()
        );

        return MemberAnalyticsResponse.builder()
                .cropRatios(cropRatios)
                .yieldPerArea(yieldPerArea)
                .build();
    }

    private Map<Crop, Double> calculateCropRatiosPrevYear(List<CultivationReport> reports) {
        Map<Crop, Double> cropAreas = new HashMap<>();
        double totalArea = 0.0;

        // 승인된 재배 신고만 집계 (이전 년도)
        for (CultivationReport report : reports) {
            if (report.getStatus() == Status.APPROVED &&
                    report.getCreatedAt().getYear() == LocalDate.now().minusYears(1).getYear()) {
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

        for (CultivationReport report : cultivationReports) {
            if (report.getStatus() == Status.APPROVED &&
                    report.getCreatedAt().getYear() == LocalDate.now().minusYears(1).getYear()) {
                cultivationAreas.merge(report.getCrop(), report.getCultivatedArea(), Double::sum);
            }
        }

        // 승인된 출하량 집계 (이전 년도)
        for (ShipmentReport report : shipmentReports) {
            if (report.getStatus() == Status.APPROVED &&
                    report.getCreatedAt().getYear() == LocalDate.now().minusYears(1).getYear()) {
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