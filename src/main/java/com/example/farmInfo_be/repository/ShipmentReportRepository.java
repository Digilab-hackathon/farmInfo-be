package com.example.farmInfo_be.repository;

import com.example.farmInfo_be.domain.ShipmentReport;
import com.example.farmInfo_be.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShipmentReportRepository extends JpaRepository<ShipmentReport, Long> {
    List<ShipmentReport> findByStatus(Status status);
}