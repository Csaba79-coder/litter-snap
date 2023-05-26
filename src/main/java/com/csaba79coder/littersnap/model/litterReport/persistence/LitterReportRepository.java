package com.csaba79coder.littersnap.model.litterReport.persistence;

import com.csaba79coder.littersnap.model.litterReport.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface LitterReportRepository extends JpaRepository<Report, UUID> {
}
