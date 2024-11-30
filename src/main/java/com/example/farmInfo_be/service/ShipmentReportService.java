package com.example.farmInfo_be.service;

import com.example.farmInfo_be.domain.CultivationReport;
import com.example.farmInfo_be.domain.Member;
import com.example.farmInfo_be.domain.ShipmentReport;
import com.example.farmInfo_be.dto.request.ShipmentReportRequest;
import com.example.farmInfo_be.dto.response.ShipmentResponseDto;
import com.example.farmInfo_be.enums.Status;
import com.example.farmInfo_be.repository.MemberRepository;
import com.example.farmInfo_be.repository.ShipmentReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ShipmentReportService {
    private final ShipmentReportRepository reportRepository;
    private final MemberRepository memberRepository;

    public ShipmentResponseDto create(ShipmentReportRequest request) {
        Member member = memberRepository.findByNameAndBirthDateAndPhoneNumber(
                request.getName(),
                convertDate(request.getBirthDate()),
                request.getPhoneNumber()
        ).orElseGet(() -> memberRepository.save(Member.builder()
                .name(request.getName())
                .birthDate(convertDate(request.getBirthDate()))
                .address(request.getAddress())
                .phoneNumber(request.getPhoneNumber())
                .homePhoneNumber(request.getHomePhoneNumber())
                .build()));

        ShipmentReport report = ShipmentReport.builder()
                .member(member)
                .expectedShipDate(convertDate(request.getExpectedShipDate()))
                .wholesaleCompany(request.getWholesaleCompany())
                .tradeType(request.getTradeType())
                .tradingMethod(request.getTradingMethod())
                .producerName(request.getProducerName())
                .productionLocation(request.getProductionLocation())
                .crop(request.getCrop())
                .packaging(request.getPackaging())
                .unit(request.getUnit())
                .grade(request.getGrade())
                .build();

        return ShipmentResponseDto.from(reportRepository.save(report));
    }

    public void delete(Long id) {
        reportRepository.deleteById(id);
    }

    public List<ShipmentResponseDto> getAll() {
        return reportRepository.findAll().stream()
                .sorted(Comparator.comparing(ShipmentReport::getCreatedAt).reversed())
                .map(ShipmentResponseDto::from)
                .collect(Collectors.toList());
    }

    public List<ShipmentResponseDto> getByStatus(Status status) {
        return reportRepository.findByStatus(status).stream()
                .map(ShipmentResponseDto::from)
                .collect(Collectors.toList());
    }

    public void approve(Long id) {
        ShipmentReport report = reportRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Report not found"));
        report.approve();
        reportRepository.save(report);
    }

    public void reject(Long id) {
        ShipmentReport report = reportRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Report not found"));
        report.reject();
        reportRepository.save(report);
    }

    private LocalDate convertDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");
        return LocalDate.parse(date, formatter);
    }

    public ShipmentResponseDto getShipmentReportById(Long id) {
        return ShipmentResponseDto.from(reportRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Report not found")));
    }
}

/**
 * {
 *     "cultivationReport": {
 *       "id": 1,
 *       "memberId": 1,
 *       "status": "PENDING",
 *       "crop": "CABBAGE",
 *       "district": "서귀포시",
 *       "village": "구좌읍",
 *       "landCategory": "FIELD",
 *       "totalArea": 94.2,
 *       "cultivatedArea": 29.76,
 *       "ownershipType": "PURCHASED",
 *       "createdAt": "2021-12-31T15:00:00"
 *     },
 *     "memberInfo": {
 *       "id": 1,
 *       "name": "농바니즈",
 *       "birthDate": "1980-01-15",
 *       "address": "제주시 조천읍 와흘리 123",
 *       "phoneNumber": "010-1234-5678",
 *       "homePhoneNumber": "064-756-1234"
 *     }
 *   },
 */

/**
 *
 */