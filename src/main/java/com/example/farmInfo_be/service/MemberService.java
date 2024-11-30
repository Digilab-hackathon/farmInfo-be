package com.example.farmInfo_be.service;

import com.example.farmInfo_be.dto.response.MemberReponseDtoWithoutReport;
import com.example.farmInfo_be.dto.response.MemberResponseDto;
import com.example.farmInfo_be.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberResponseDto getReports(String phoneNumber) {
        return MemberResponseDto.from(memberRepository.findByPhoneNumber(phoneNumber)
                .orElseThrow(() -> new IllegalArgumentException("Member not found")));
    }

    public MemberReponseDtoWithoutReport getMember(Long memberId) {
        return MemberReponseDtoWithoutReport.from(memberRepository.findById(memberId).orElseThrow(()->new IllegalArgumentException("Member not found")));
    }
}
