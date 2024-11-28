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
    private Long area;                // 면적
    private String ri;                  // 리
    private String category;              // 지역
    private Long detailedArea;        // 실경적 면적
    private String reportType;          // 저장 여부 (저장/매수)
    private boolean approved;
    private String region;

    @CreatedDate
    private LocalDateTime createTime;

    @Builder
    public Report(Member member, String productName, Long area, String ri,
                  String category, Long detailedArea, String reportType,
                  String region) {
        this.member = member;
        this.productName = productName;
        this.area = area;
        this.ri = ri;
        this.category = category;
        this.detailedArea = detailedArea;
        this.reportType = reportType;
        this.approved = false;
        this.region = region;
    }

    public void approve() {
        this.approved = true;
    }
}