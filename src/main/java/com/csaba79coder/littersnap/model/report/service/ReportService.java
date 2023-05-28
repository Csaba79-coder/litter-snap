package com.csaba79coder.littersnap.model.report.service;

import com.csaba79coder.littersnap.model.report.dto.ReportModel;
import com.csaba79coder.littersnap.model.report.entity.Report;
import com.csaba79coder.littersnap.model.report.persistence.ReportRepository;
import com.csaba79coder.littersnap.util.Mapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReportService  {

    private final ReportRepository reportRepository;

    public List<ReportModel> findAllReports() {
        return reportRepository.findAll()
                .stream()
                .map(Mapper::mapReportEntityToModel)
                .collect(Collectors.toList());
    }

    public ReportModel findReportByID(UUID id) {
        Optional<Report> optionalReport = reportRepository.findReportById(id);
        return optionalReport.map(Mapper::mapReportEntityToModel).orElse(null);
    }

    public ReportModel addNewReport(Report report) {
        return Mapper.mapReportEntityToModel(reportRepository.save(report));
    }

    public void deleteReportById(UUID id) {
        reportRepository.deleteById(id);
    }

}
