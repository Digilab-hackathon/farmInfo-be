package com.example.farmInfo_be.controller;

import com.example.farmInfo_be.dto.request.ShipmentReportRequest;
import com.example.farmInfo_be.dto.response.MemberReponseDtoWithoutReport;
import com.example.farmInfo_be.dto.response.MemberResponseDto;
import com.example.farmInfo_be.dto.response.ShipmentReportWithMemberDto;
import com.example.farmInfo_be.dto.response.ShipmentResponseDto;
import com.example.farmInfo_be.enums.Status;
import com.example.farmInfo_be.service.MemberService;
import com.example.farmInfo_be.service.ShipmentReportService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/shipment-reports")
@Tag(name = "ShipmentReport", description = "출하량 신고 API")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ShipmentReportController {
    private final ShipmentReportService reportService;
    private final MemberService memberService;

    @Operation(summary = "출하량 신고 생성", description = "새로운 출하량 신고를 생성합니다.")
    @PostMapping
    public ResponseEntity<ShipmentResponseDto> create(
            @RequestBody @Parameter(description = "출하량 신고 정보") ShipmentReportRequest request
    ) {
        System.out.println("ShipmentReportController.create");
        System.out.println("request = " + request);
        return ResponseEntity.ok(reportService.create(request));
    }

    @Operation(summary = "특정 출하량 신고 조회", description = "특정 출하량 신고 조회")
    @GetMapping("/{id}")
    public ResponseEntity<ShipmentReportWithMemberDto> getById(@PathVariable Long id){
        System.out.println("ShipmentReportController.getById");
        System.out.println("id = " + id);
        ShipmentResponseDto shipmentReportById=reportService.getShipmentReportById(id);
        MemberReponseDtoWithoutReport memberInfo = memberService.getMember(shipmentReportById.getMemberId());




        return ResponseEntity.ok(new ShipmentReportWithMemberDto(shipmentReportById, memberInfo));
    }

//    @Operation(summary = "상태별 출하량 신고 조회", description = "특정 상태의 출하량 신고를 조회합니다.")
//    @GetMapping("/status/{status}")
//    public ResponseEntity<List<ShipmentResponseDto>> getByStatus(
//            @Parameter(description = "신고 상태(PENDING/APPROVED/REJECTED)", required = true) @PathVariable String status
//    ) {
//        System.out.println("ShipmentReportController.getByStatus");
//        System.out.println("status = " + status);
//        return ResponseEntity.ok(reportService.getByStatus(Status.valueOf(status)));
//    }

    @Operation(summary = "상태별 재배면적 신고 조회", description = "특정 상태의 재배면적 신고를 조회합니다.")
    @GetMapping("/status/{status}")
    public ResponseEntity<List<ShipmentReportWithMemberDto>> getByStatus(
            @Parameter(description = "신고 상태(PENDING/APPROVED/REJECTED)", required = true) @PathVariable String status
    ) {
        System.out.println("ShipmentReportController.getByStatus");
        System.out.println(status);

        // 상태별 재배면적 신고 목록 조회
        List<ShipmentResponseDto> shipmentReports = reportService.getByStatus(Status.valueOf(status));

        // 각 신고에 대한 회원 정보를 조회하여 새로운 DTO 리스트 생성
        List<ShipmentReportWithMemberDto> result = shipmentReports.stream()
                .map(report -> {
                    Long memberId = report.getMemberId(); // memberId 조회
                    MemberReponseDtoWithoutReport memberInfo = memberService.getMember(memberId);
                    return new ShipmentReportWithMemberDto(report, memberInfo);
                })
                .collect(Collectors.toList());

        return ResponseEntity.ok(result);
    }

    @Operation(summary = "출하량 신고 승인", description = "출하량 신고를 승인합니다. (관리자 전용)")
    @PatchMapping("/{id}/approve")
    public ResponseEntity<Void> approve(
            @Parameter(description = "신고 ID", required = true) @PathVariable Long id
    ) {
        System.out.println("ShipmentReportController.approve");
        System.out.println("id = " + id);
        reportService.approve(id);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "출하량 신고 반려", description = "출하량 신고를 반려합니다. (관리자 전용)")
    @PatchMapping("/{id}/reject")
    public ResponseEntity<Void> reject(
            @Parameter(description = "신고 ID", required = true) @PathVariable Long id
    ) {
        System.out.println("ShipmentReportController.reject");
        System.out.println("id = " + id);
        reportService.reject(id);
        return ResponseEntity.ok().build();
    }
}