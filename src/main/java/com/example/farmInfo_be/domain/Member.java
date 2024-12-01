// Member.java
package com.example.farmInfo_be.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {                    // 회원 정보
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;                     // 회원 ID
    private String name;                 // 이름
    private LocalDate birthDate;         // 생년월일
    private String address;              // 주소
    private String phoneNumber;          // 연락처
    private String homePhoneNumber;      // 자택번호

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<CultivationReport> cultivationReports = new ArrayList<>();

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ShipmentReport> shipmentReports = new ArrayList<>();     // 출하량 신고 목록

    @Builder
    public Member(String name, LocalDate birthDate, String address, String phoneNumber, String homePhoneNumber) {
        this.name = name;
        this.birthDate = birthDate;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.homePhoneNumber = homePhoneNumber;
    }
}

/**
 * 29 22 21 26
 */

/**
 * 8 4 2 5
 */