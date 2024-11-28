package com.example.farmInfo_be.controller;

import com.example.farmInfo_be.dto.request.CultivationReportRequest;
import com.example.farmInfo_be.dto.response.CultivationResponseDto;
import com.example.farmInfo_be.enums.Crop;
import com.example.farmInfo_be.enums.Status;
import com.example.farmInfo_be.service.CultivationReportService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cultivation-reports")
@Tag(name = "CultivationReport", description = "재배면적 신고 API")
@RequiredArgsConstructor
public class CultivationReportController {
    private final CultivationReportService reportService;

    @Operation(summary = "재배면적 신고 생성", description = "새로운 재배면적 신고를 생성합니다.")
    @PostMapping
    public ResponseEntity<CultivationResponseDto> create(
            @RequestBody @Parameter(description = "재배면적 신고 정보") CultivationReportRequest request
    ) {
        return ResponseEntity.ok(reportService.create(request));
    }

    @Operation(summary = "재배면적 신고 삭제", description = "재배면적 신고를 삭제합니다.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @Parameter(description = "신고 ID", required = true) @PathVariable Long id
    ) {
        reportService.delete(id);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "재배면적 신고 전체 조회", description = "모든 재배면적 신고를 조회합니다.")
    @GetMapping
    public ResponseEntity<List<CultivationResponseDto>> getAll() {
        return ResponseEntity.ok(reportService.getAll());
    }

    @Operation(summary = "작물별 재배면적 신고 조회", description = "특정 작물의 재배면적 신고를 조회합니다.")
    @GetMapping("/by-crop/{cropName}")
    public ResponseEntity<List<CultivationResponseDto>> getByCrop(
            @Parameter(description = "작물 이름", required = true) @PathVariable String cropName
    ) {
        return ResponseEntity.ok(reportService.getByCrop(Crop.valueOf(cropName)));
    }

    @Operation(summary = "상태별 재배면적 신고 조회", description = "특정 상태의 재배면적 신고를 조회합니다.")
    @GetMapping("/status/{status}")
    public ResponseEntity<List<CultivationResponseDto>> getByStatus(
            @Parameter(description = "신고 상태(PENDING/APPROVED/REJECTED)", required = true) @PathVariable String status
    ) {
        return ResponseEntity.ok(reportService.getByStatus(Status.valueOf(status)));
    }

    @Operation(summary = "재배면적 신고 승인", description = "재배면적 신고를 승인합니다. (관리자 전용)")
    @PatchMapping("/{id}/approve")
    public ResponseEntity<Void> approve(
            @Parameter(description = "신고 ID", required = true) @PathVariable Long id
    ) {
        reportService.approve(id);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "재배면적 신고 반려", description = "재배면적 신고를 반려합니다. (관리자 전용)")
    @PatchMapping("/{id}/reject")
    public ResponseEntity<Void> reject(
            @Parameter(description = "신고 ID", required = true) @PathVariable Long id
    ) {
        reportService.reject(id);
        return ResponseEntity.ok().build();
    }
}
