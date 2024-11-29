package com.example.farmInfo_be.controller;

import com.example.farmInfo_be.dto.response.MemberAnalyticsResponse;
import com.example.farmInfo_be.service.MemberAnalyticsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/members/analytics")
@Tag(name = "Member Analytics", description = "회원 분석 API")
@RequiredArgsConstructor
public class MemberAnalyticsController {
    private final MemberAnalyticsService analyticsService;

    @Operation(summary = "회원 재배/출하 분석", description = "회원의 품목별 비율과 면적당 출하량을 분석합니다.")
    @GetMapping
    public ResponseEntity<MemberAnalyticsResponse> getAnalytics(
            @Parameter(description = "회원 전화번호", required = true) @RequestParam String phoneNumber
    ) {
        return ResponseEntity.ok(analyticsService.getAnalytics(phoneNumber));
    }
}

