package com.example.farmInfo_be.controller;

import com.example.farmInfo_be.dto.response.MemberResponseDto;
import com.example.farmInfo_be.service.MemberService;
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
@RequestMapping("/api/members")
@Tag(name = "Member", description = "회원 API")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @Operation(summary = "회원 신고 내역 조회", description = "회원의 전체 신고 내역(재배면적, 출하량)을 조회합니다.")
    @GetMapping("/reports")
    public ResponseEntity<MemberResponseDto> getReports(
            @Parameter(description = "회원 ID", required = true) @RequestParam Long memberId
    ) {
        return ResponseEntity.ok(memberService.getReports(memberId));
    }
}