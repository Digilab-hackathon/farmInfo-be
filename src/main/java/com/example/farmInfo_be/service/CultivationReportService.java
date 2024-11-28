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
                request.getBirthDate(),
                request.getPhoneNumber()
        ).orElseGet(() -> memberRepository.save(Member.builder()
                .name(request.getName())
                .birthDate(request.getBirthDate())
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
}