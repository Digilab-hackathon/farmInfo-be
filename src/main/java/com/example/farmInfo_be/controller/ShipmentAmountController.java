package com.example.farmInfo_be.controller;

import com.example.farmInfo_be.domain.Member;
import com.example.farmInfo_be.domain.ShipmentReport;
import com.example.farmInfo_be.enums.Status;
import com.example.farmInfo_be.repository.MemberRepository;
import com.example.farmInfo_be.repository.ShipmentReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/shipments")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ShipmentAmountController {
    private final ShipmentReportRepository shipmentReportRepository;
    private final MemberRepository memberRepository;

    @GetMapping("/total/{phoneNumber}/{year}")
    public ResponseEntity<Double> getTotalShipmentAmount(
            @PathVariable String phoneNumber,
            @PathVariable int year
    ) {
        System.out.println("ShipmentAmountController.getTotalShipmentAmount");
        System.out.println("phoneNumber = " + phoneNumber);
        System.out.println("year = " + year);
        Member member = memberRepository.findByPhoneNumber(phoneNumber)
                .orElseThrow(() -> new IllegalArgumentException("Member not found"));

        return ResponseEntity.ok(
                member.getShipmentReports().stream()
                        .filter(report -> report.getStatus() == Status.APPROVED)
                        .filter(report -> report.getCreatedAt().getYear() == year)
                        .mapToDouble(ShipmentReport::getUnit)
                        .sum()
        );
    }
}