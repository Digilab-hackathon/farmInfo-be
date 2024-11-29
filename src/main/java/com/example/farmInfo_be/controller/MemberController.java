package com.example.farmInfo_be.controller;

import com.example.farmInfo_be.dto.response.MemberResponseDto;
import com.example.farmInfo_be.service.CultivationAreaService;
import com.example.farmInfo_be.service.MemberAnalyticsService;
import com.example.farmInfo_be.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/members")
@Tag(name = "Member", description = "회원 API")
@RequiredArgsConstructor
@CrossOrigin("*")
public class MemberController {
    private final MemberService memberService;
    private final CultivationAreaService cultivationAreaService;
    private final MemberAnalyticsService memberAnalyticsService;

    @Operation(summary = "회원 신고 내역 조회", description = "회원의 전체 신고 내역(재배면적, 출하량)을 조회합니다.")
    @GetMapping("/reports")
    public ResponseEntity<MemberResponseDto> getReports(
            @Parameter(description = "회원 전화번호", required = true) @RequestParam String phoneNumber
    ) {
        System.out.println("MemberController.getReports");
        System.out.println("phoneNumber = " + phoneNumber);
        return ResponseEntity.ok(memberService.getReports(phoneNumber));
    }
}