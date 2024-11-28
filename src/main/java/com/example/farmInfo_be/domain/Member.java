// Member.java
package com.example.farmInfo_be.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    private String reporterName;        // 성명 (법인명)
    private String birthday;            // 생년월일 (법인 번호)
    private String address; // 주소

    @Column(unique = true)
    private String phone;               // 연락처 (핸드폰)
    private String secondPhone;         // 연락처 (자택)

    @OneToMany(mappedBy = "member")
    private List<Report> reports = new ArrayList<>();

    @Builder
    public Member(String reporterName, String birthday, String address,
                  String phone, String secondPhone) {
        this.reporterName = reporterName;
        this.birthday = birthday;
        this.address = address;
        this.phone = phone;
        this.secondPhone = secondPhone;
    }
}