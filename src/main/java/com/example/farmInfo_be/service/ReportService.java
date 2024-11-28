package com.example.farmInfo_be.service;

import com.example.farmInfo_be.domain.Member;
import com.example.farmInfo_be.domain.Report;
import com.example.farmInfo_be.dto.request.MemberRequest;
import com.example.farmInfo_be.dto.request.ReportRequest;
import com.example.farmInfo_be.dto.response.ReportResponse;
import com.example.farmInfo_be.repository.MemberRepository;
import com.example.farmInfo_be.repository.ReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ReportService {

    private final ReportRepository reportRepository;
    private final MemberRepository memberRepository;

    public ReportResponse createReport(MemberRequest memberRequest, ReportRequest reportRequest) {
        // 전화번호로 기존 회원 확인
        Member member = memberRepository
                .findByPhone(memberRequest.getPhone())
                .orElseGet(() -> createMember(memberRequest));

        Report report = Report.builder()
                .member(member)
                .productName(reportRequest.getProductName())
                .area(reportRequest.getArea())
                .ri(reportRequest.getRi())
                .region(reportRequest.getRegion())
                .detailedArea(reportRequest.getDetailedArea())
                .reportType(reportRequest.getReportType())
                .build();

        Report savedReport = reportRepository.save(report);

        return ReportResponse.from(savedReport);
    }

    private Member createMember(MemberRequest memberRequest) {
        Member newMember = Member.builder()
                .reporterName(memberRequest.getReporterName())
                .birthday(memberRequest.getBirthday())
                .address(memberRequest.getAddress())
                .phone(memberRequest.getPhone())
                .secondPhone(memberRequest.getSecondPhone())
                .build();

        return memberRepository.save(newMember);
    }
}
