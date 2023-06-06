package com.csaba79coder.littersnap.controller;

import com.csaba79coder.littersnap.model.report.dto.ReportModel;
import com.csaba79coder.littersnap.model.report.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * The ReportController.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class ReportController {

    /**
     * Dependency injection for the ReportService.
     * TODO: render all reports for the logged in user only!
     * TODO: create findByUserId method in ReportRepository
     */
    private final ReportService reportService;

    /**
     * This method is used to render all reports.
     * @return the list of reports, and status code 200 (OK)
     */
    @GetMapping("/admin/reports")
    public ResponseEntity<List<ReportModel>> renderAllReports() {
        return ResponseEntity.status(200).body(reportService.findAllReports());
    }

    /**
     * This method is used to add a new report.
     * @param model the report model
     * @return the response entity and status code 201 (Created)
     */
    @PostMapping("/admin/reports")
    public ResponseEntity<ReportModel> addNewReport(@RequestBody ReportModel model) {
        return ResponseEntity.status(201).body(reportService.addNewReport(model));
    }

    /**
     * This method is used to render a report by its id.
     * @param id the id
     * @return the response entity and status code 200 (OK)
     */
    @GetMapping("/admin/reports/{id}")
    public ResponseEntity<ReportModel> renderReportById(@PathVariable("id") UUID id) {
        ReportModel report = reportService.findReportById(id);
        if (report != null) {
            return ResponseEntity.ok(report);  // Include the report in the response body with status code 200 (OK)
        }
        return ResponseEntity.notFound().build();  // Return 404 (Not Found) status
    }

    /**
     * This method is used to update an existing report.
     * @param id the id
     * @param model the report model
     * @return the response entity and status code 200 (OK)
     */
    @PutMapping("/admin/reports/{id}")
    public ResponseEntity<ReportModel> updateExistingReport(@PathVariable("id") UUID id, @RequestBody ReportModel model) {
        return ResponseEntity.status(200).body(reportService.modifyAnExistingReport(id, model));
    }

    /**
     * This method is used to delete an existing report.
     * @param id the id
     * @return status code 204 (No Content) if successful
     */
    @DeleteMapping("/admin/reports/{id}")
    public ResponseEntity<Void> deleteExistingReport(@PathVariable("id") UUID id) {
        reportService.deleteExistingReport(id);
        return ResponseEntity.status(204).build();
    }
}
