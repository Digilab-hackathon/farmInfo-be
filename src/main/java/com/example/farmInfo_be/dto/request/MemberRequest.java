package com.example.farmInfo_be.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Builder;

@Getter
@NoArgsConstructor
public class MemberRequest {
    private String reporterName;
    private String birthday;
    private String address;
    private String phone;
    private String secondPhone;

    @Builder
    public MemberRequest(String reporterName, String birthday, String address,
                         String phone, String secondPhone) {
        this.reporterName = reporterName;
        this.birthday = birthday;
        this.address = address;
        this.phone = phone;
        this.secondPhone = secondPhone;
    }
}