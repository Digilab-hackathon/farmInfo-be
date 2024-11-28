package com.example.farmInfo_be.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Builder;
import com.example.farmInfo_be.domain.Member;

@Getter
@NoArgsConstructor
public class MemberResponse {
    private String reporterName;
    private String birthday;
    private String address;
    private String phone;
    private String secondPhone;

    @Builder
    public MemberResponse(String reporterName, String birthday, String address,
                          String phone, String secondPhone) {
        this.reporterName = reporterName;
        this.birthday = birthday;
        this.address = address;
        this.phone = phone;
        this.secondPhone = secondPhone;
    }

    public static MemberResponse from(Member member) {
        return MemberResponse.builder()
                .reporterName(member.getReporterName())
                .birthday(member.getBirthday())
                .address(member.getAddress())
                .phone(member.getPhone())
                .secondPhone(member.getSecondPhone())
                .build();
    }
}