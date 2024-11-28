package com.example.farmInfo_be.service;

import com.example.farmInfo_be.ReportStatus;
import com.example.farmInfo_be.controller.ReportController;
import com.example.farmInfo_be.domain.Report;
import com.example.farmInfo_be.dto.response.ReportResponse;
import com.example.farmInfo_be.repository.MemberRepository;
import com.example.farmInfo_be.repository.ReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final ReportRepository reportRepository;
    private final MemberRepository memberRepository;

    public void acceptReport(Long reportId){
        Report report = reportRepository.findById(reportId)
                .orElseThrow(()-> new IllegalStateException("리포트가 없어용"));
        report.approve();
        reportRepository.save(report);
    }

    public void rejectReport(Long reportId){
        Report report = reportRepository.findById(reportId)
                .orElseThrow(()-> new IllegalStateException("리포트가 없어용"));
        report.reject();
        reportRepository.save(report);
    }

    public List<ReportResponse> getReportList(){
        List<Report> reportList = reportRepository.findAll();
        return reportList.stream()
                .filter(report -> report.getStatus() == ReportStatus.PENDING)
                .map(ReportResponse::from)
                .toList();
    }
}
