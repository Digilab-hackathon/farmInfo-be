package com.example.farmInfo_be.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reportId;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    private String productName;         // 품목명
    private String area;                // 면적
    private String ri;                  // 리
    private String region;              // 지역
    private String detailedArea;        // 실경적 면적
    private String reportType;          // 저장 여부 (저장/매수)
    private boolean approved;

    @CreatedDate
    private LocalDateTime createTime;

    @Builder
    public Report(Member member, String productName, String area, String ri,
                  String region, String detailedArea, String reportType) {
        this.member = member;
        this.productName = productName;
        this.area = area;
        this.ri = ri;
        this.region = region;
        this.detailedArea = detailedArea;
        this.reportType = reportType;
        this.approved = false;
    }

    public void approve() {
        this.approved = true;
    }
}