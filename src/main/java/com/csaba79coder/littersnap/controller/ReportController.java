package com.csaba79coder.littersnap.controller;

import com.csaba79coder.littersnap.model.report.dto.ReportModel;
import com.csaba79coder.littersnap.model.report.service.ReportService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping
    public List<ReportModel> renderAllReports() {
        return reportService.findAllReports();
    }

    @PostMapping
    public ResponseEntity<ReportModel> addNewReport(@RequestBody ReportModel model) {
        return ResponseEntity.status(201).body(reportService.addNewReport(model));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReportModel> renderReportById(@PathVariable("id") UUID id) {
        ReportModel report = reportService.findReportById(id);
        if (report != null) {
            return ResponseEntity.ok(report);  // Include the report in the response body with status code 200 (OK)
        }
        return ResponseEntity.notFound().build();  // Return 404 (Not Found) status
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReportModel> updateAnExistingReport(@PathVariable("id") UUID id, @RequestBody ReportModel model) {
        return ResponseEntity.status(200).body(reportService.modifyAnExistingReport(id, model));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAnExistingReport(@PathVariable("id") UUID id) {
        ReportModel litter = reportService.findReportById(id);
        reportService.deleteExistingReport(id);

        return ResponseEntity.status(204).build();
    }
}
