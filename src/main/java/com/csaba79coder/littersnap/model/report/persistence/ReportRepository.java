package com.csaba79coder.littersnap.model.report.persistence;

import com.csaba79coder.littersnap.model.report.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

/**
 * This class contains the report repository.
 */
@Repository
public interface ReportRepository extends JpaRepository<Report, UUID> {

   /**
    * This method finds a report by id.
    *
    * @param id the report id
    * @return the optional report
    */
   Optional<Report> findReportById(UUID id);
}
