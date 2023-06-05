package com.csaba79coder.littersnap.model.report.service;

import com.csaba79coder.littersnap.model.report.dto.ReportModel;
import com.csaba79coder.littersnap.model.report.entity.Report;
import com.csaba79coder.littersnap.model.report.persistence.ReportRepository;
import com.csaba79coder.littersnap.util.Mapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
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

    public ReportModel addNewReport(ReportModel reportModel) {
        Report reportEntity = Mapper.mapReportModelToEntity(reportModel);
        Report savedReportEntity = reportRepository.save(reportEntity);
        return Mapper.mapReportEntityToModel(savedReportEntity);
    }


    public ReportModel findReportById(UUID id) {
        Report report = reportRepository.findReportById(id)
                .orElseThrow(() -> {
                    String message = "Report not found with id: " + id;
                    log.error(message);
                    throw new NoSuchElementException("Report not found with id: " + id);
                });
        return Mapper.mapReportEntityToModel(report);
    }

    public ReportModel modifyAnExistingReport(UUID id, ReportModel model) {
        Report report = reportRepository.findReportById(id)
                .orElseThrow(() -> {
                    String message = "Report not found with id: " + id;
                    log.error(message);
                    throw new NoSuchElementException("Report not found with id: " + id);
                });

        // Update the properties of the existing report with the values from the model
        report.setLitter(model.getLitter());

        // Save the updated report in the repository
        Report updatedReport = reportRepository.save(report);

        // Map the updated report entity to a ReportModel and return it
        return Mapper.mapReportEntityToModel(updatedReport);
    }

    public void deleteExistingReport(UUID id) {
        Report report = reportRepository.findReportById(id)
                .orElseThrow(() -> {
                    String message = "Report not found with id: " + id;
                    log.error(message);
                    throw new NoSuchElementException("Report not found with id: " + id);
                });
        reportRepository.deleteById(report.getId());
    }
}
