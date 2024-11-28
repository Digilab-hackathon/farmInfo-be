package com.example.farmInfo_be.dto.response;

import com.example.farmInfo_be.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public class MemberResponseDto {         // 회원 정보 응답
    private Long id;                     // 회원 ID
    private String name;                 // 이름
    private LocalDate birthDate;         // 생년월일
    private String address;              // 주소
    private String phoneNumber;          // 연락처
    private String homePhoneNumber;      // 자택번호
    private List<CultivationResponseDto> cultivationReports;  // 재배면적 신고 목록
    private List<ShipmentResponseDto> shipmentReports;        // 출하량 신고 목록

    public static MemberResponseDto from(Member member) {
        return new MemberResponseDto(
                member.getId(),
                member.getName(),
                member.getBirthDate(),
                member.getAddress(),
                member.getPhoneNumber(),
                member.getHomePhoneNumber(),
                member.getCultivationReports().stream()
                        .map(CultivationResponseDto::from)
                        .collect(Collectors.toList()),
                member.getShipmentReports().stream()
                        .map(ShipmentResponseDto::from)
                        .collect(Collectors.toList())
        );
    }
}
