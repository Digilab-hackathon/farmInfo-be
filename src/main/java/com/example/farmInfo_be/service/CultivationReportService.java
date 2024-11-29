package com.example.farmInfo_be.service;

import com.example.farmInfo_be.domain.CultivationReport;
import com.example.farmInfo_be.domain.Member;
import com.example.farmInfo_be.dto.request.CultivationReportRequest;
import com.example.farmInfo_be.dto.response.CultivationResponseDto;
import com.example.farmInfo_be.enums.Crop;
import com.example.farmInfo_be.enums.Status;
import com.example.farmInfo_be.repository.CultivationReportRepository;
import com.example.farmInfo_be.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class CultivationReportService {
    private final CultivationReportRepository reportRepository;
    private final MemberRepository memberRepository;

    public CultivationResponseDto create(CultivationReportRequest request) {
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

        CultivationReport report = CultivationReport.builder()
                .member(member)
                .crop(request.getCrop())
                .district(request.getDistrict())
                .village(request.getVillage())
                .landCategory(request.getLandCategory())
                .totalArea(request.getTotalArea())
                .cultivatedArea(request.getCultivatedArea())
                .ownershipType(request.getOwnershipType())
                .build();

        return CultivationResponseDto.from(reportRepository.save(report));
    }

    public void delete(Long id) {
        reportRepository.deleteById(id);
    }

    public List<CultivationResponseDto> getAll() {
        return reportRepository.findAll().stream()
                .map(CultivationResponseDto::from)
                .collect(Collectors.toList());
    }

    public double getApprovedAreaByCrop(Crop crop) {
        return reportRepository.findByCrop(crop)
                .stream()
                .mapToDouble(dto -> dto.getTotalArea())
                .sum();
    }

    public List<CultivationResponseDto> getByCrop(Crop crop) {
        return reportRepository.findByCrop(crop).stream()
                .map(CultivationResponseDto::from)
                .collect(Collectors.toList());
    }

    public List<CultivationResponseDto> getByStatus(Status status) {
        return reportRepository.findByStatus(status).stream()
                .map(CultivationResponseDto::from)
                .collect(Collectors.toList());
    }

    public void approve(Long id) {
        reportRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Report not found"))
                .approve();
    }

    public void reject(Long id) {
        reportRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Report not found"))
                .reject();
    }

    private LocalDate convertDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");
        return LocalDate.parse(date, formatter);
    }

    public CultivationResponseDto getCultivationResponseById(Long id) {
        return CultivationResponseDto.from(reportRepository.findById(id).orElseThrow(()->new IllegalArgumentException("ReportNotFound")));
    }
}