package com.example.farmInfo_be.service;

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

    public MemberResponseDto getReports(Long memberId) {
        return MemberResponseDto.from(memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("Member not found")));
    }
}
