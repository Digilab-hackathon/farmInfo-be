package com.example.farmInfo_be.controller;

import com.example.farmInfo_be.dto.request.ReportRequest;
import com.example.farmInfo_be.dto.response.ReportResponse;
import com.example.farmInfo_be.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admins")
public class AdminController {

    private final AdminService adminService;

    @PatchMapping("/{reportId}/approve")
    public ResponseEntity approveReport(@PathVariable Long reportId){
        adminService.acceptReport(reportId);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{reportId}/reject")
    public ResponseEntity rejectReport(@PathVariable Long reportId){
        adminService.rejectReport(reportId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/report")
    public ResponseEntity<List<ReportResponse>> getReports(){
        return ResponseEntity.ok(adminService.getReportList());
    }
}
