package com.example.farmInfo_be.controller;

import com.example.farmInfo_be.dto.request.CombinedReportRequest;
import com.example.farmInfo_be.dto.response.ReportResponse;
import com.example.farmInfo_be.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/reports")
@RequiredArgsConstructor
public class ReportController {

    private final ReportService reportService;

    @PostMapping
    public ResponseEntity<ReportResponse> createReport(
            @RequestBody CombinedReportRequest request) {
        ReportResponse response = reportService.createReport(
                request.getMemberRequest(),
                request.getReportRequest()
        );
        return ResponseEntity.ok(response);
    }
}