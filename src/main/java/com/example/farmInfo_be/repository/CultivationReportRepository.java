package com.example.farmInfo_be.repository;

import com.example.farmInfo_be.domain.CultivationReport;
import com.example.farmInfo_be.enums.Crop;
import com.example.farmInfo_be.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CultivationReportRepository extends JpaRepository<CultivationReport, Long> {
    List<CultivationReport> findByCrop(Crop crop);
    List<CultivationReport> findByStatus(Status status);
}