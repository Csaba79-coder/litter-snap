package com.csaba79coder.littersnap.model.report.persistence;

import com.csaba79coder.littersnap.model.report.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ReportRepository extends JpaRepository<Report, UUID> {

   Optional<Report> findReportById(UUID id);
}