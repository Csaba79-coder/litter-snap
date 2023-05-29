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


    public List<ReportModel> getAllReports() {
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


    public ReportModel getReportById(UUID id) {
        Optional<Report> optionalReport = reportRepository.findReportById(id);
        if (optionalReport.isPresent()) {
            Report report = optionalReport.get();
            return Mapper.mapReportEntityToModel(report);
        }
        //TODO
        // If the report with the specified ID does not exist, you can throw an exception or return null
        return null;
    }

    public ReportModel updateExistingReport(UUID id, ReportModel model) {
        Optional<Report> optionalExistingReport = reportRepository.findById(id);
        if (optionalExistingReport.isPresent()) {
            Report existingReport = optionalExistingReport.get();

            // Update the properties of the existing report with the values from the model
            existingReport.setLitter(model.getLitter());

            // Save the updated report in the repository
            Report updatedReport = reportRepository.save(existingReport);

            // Map the updated report entity to a ReportModel and return it
            return Mapper.mapReportEntityToModel(updatedReport);
        }
        //TODO
        // If the report with the specified ID does not exist, you can throw an exception or return null
        return null;
    }

    public void deleteExistingReport(UUID id) {
        Optional<Report> optionalExistingReport = reportRepository.findById(id);
        if (optionalExistingReport.isPresent()) {
            reportRepository.deleteById(id);
        } else {
            //TODO
            // If the report with the specified ID does not exist, you can throw an exception or return null
        }
    }
}
