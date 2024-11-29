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
@CrossOrigin("*")
public class CultivationReportController {
    private final CultivationReportService reportService;

    @Operation(summary = "재배면적 신고 생성", description = "새로운 재배면적 신고를 생성합니다.")
    @PostMapping
    public ResponseEntity<CultivationResponseDto> create(
            @RequestBody @Parameter(description = "재배면적 신고 정보") CultivationReportRequest request
    ) {
        System.out.println("CultivationReportController.create");
        System.out.println(request.toString());
        return ResponseEntity.ok(reportService.create(request));
    }

    @Operation(summary = "특정 재배면적 신고 조회", description = "특정 재배면적 신고 조회")
    @GetMapping("/{id}")
    public ResponseEntity<CultivationResponseDto> getById(@PathVariable Long id){
        System.out.println("CultivationReportController.getById");
        System.out.println("id = " + id);
        return ResponseEntity.ok(reportService.getCultivationResponseById(id));
    }

//    @Operation(summary = "작물별 재배면적 신고 조회", description = "특정 작물의 재배면적 신고를 조회합니다.")
//    @GetMapping("/by-crop/{cropName}")
//    public ResponseEntity<List<CultivationResponseDto>> getByCrop(
//            @Parameter(description = "작물 이름", required = true) @PathVariable String cropName
//    ) {
//        System.out.println("CultivationReportController.getByCrop");
//        System.out.println(cropName);
//        return ResponseEntity.ok(reportService.getByCrop(Crop.valueOf(cropName)));
//    }

    @Operation(summary = "상태별 재배면적 신고 조회", description = "특정 상태의 재배면적 신고를 조회합니다.")
    @GetMapping("/status/{status}")
    public ResponseEntity<List<CultivationResponseDto>> getByStatus(
            @Parameter(description = "신고 상태(PENDING/APPROVED/REJECTED)", required = true) @PathVariable String status
    ) {
        System.out.println("CultivationReportController.getByStatus");
        System.out.println(status);
        return ResponseEntity.ok(reportService.getByStatus(Status.valueOf(status)));
    }

    @Operation(summary = "작물별 재배면적 승인된 면적", description = "특정 작물의 재배면적 신고 중 승인된 신고들의 면적 총합")
    @GetMapping("/area/{cropName}")
    public ResponseEntity<Double> getApprovedAreaByCrop(@PathVariable String cropName){
        System.out.println("CultivationReportController.getApprovedAreaByCrop");
        System.out.println(cropName);
        return ResponseEntity.ok(reportService.getApprovedAreaByCrop(Crop.valueOf(cropName)));
    }

    @Operation(summary = "재배면적 신고 승인", description = "재배면적 신고를 승인합니다. (관리자 전용)")
    @PatchMapping("/{id}/approve")
    public ResponseEntity<Void> approve(
            @Parameter(description = "신고 ID", required = true) @PathVariable Long id
    ) {
        System.out.println("CultivationReportController.approve");
        System.out.println("id = " + id);
        reportService.approve(id);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "재배면적 신고 반려", description = "재배면적 신고를 반려합니다. (관리자 전용)")
    @PatchMapping("/{id}/reject")
    public ResponseEntity<Void> reject(
            @Parameter(description = "신고 ID", required = true) @PathVariable Long id
    ) {
        System.out.println("CultivationReportController.reject");
        System.out.println("id = " + id);
        reportService.reject(id);
        return ResponseEntity.ok().build();
    }
}
