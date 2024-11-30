package com.example.farmInfo_be.dto.response;

import com.example.farmInfo_be.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public class MemberReponseDtoWithoutReport {
    private Long id;                     // 회원 ID
    private String name;                 // 이름
    private LocalDate birthDate;         // 생년월일
    private String address;              // 주소
    private String phoneNumber;          // 연락처
    private String homePhoneNumber;

    public static MemberReponseDtoWithoutReport from(Member member) {
        return new MemberReponseDtoWithoutReport(
                member.getId(),
                member.getName(),
                member.getBirthDate(),
                member.getAddress(),
                member.getPhoneNumber(),
                member.getHomePhoneNumber()
        );
    }
}
