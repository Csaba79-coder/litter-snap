package com.csaba79coder.littersnap.model.report.service;

import com.csaba79coder.littersnap.model.report.entity.Report;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ReportService {

    List<Report> findAll();
    Optional<Report> findById(UUID id);
    void save(Report report);
    void deleteById(UUID id);

}
