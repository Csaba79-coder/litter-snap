package com.csaba79coder.littersnap.controller;

import com.csaba79coder.littersnap.model.report.entity.Report;
import com.csaba79coder.littersnap.model.report.service.ReportService;
import com.csaba79coder.littersnap.model.report.service.ReportServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("/thy")
public class ReportController {

    private final ReportService reportService;

    public ReportController(ReportServiceImpl reportService) {
        this.reportService = reportService;
    }


    @GetMapping("reports")
    public String getAllReports(Model model) {
        List<Report> reports = reportService.findAll();
        model.addAttribute("reports", reports);
        return "report_list"; // Replace with the actual view name for displaying the list of reports
    }

    @GetMapping("/{id}")
    public String getReportById(@PathVariable UUID id, Model model) {
        Optional<Report> optionalReport = reportService.findById(id);
        if (optionalReport.isPresent()) {
            Report report = optionalReport.get();
            model.addAttribute("report", report);
            return "report_details"; // Replace with the actual view name for displaying the report details
        } else {
            // Report not found
            return "error_page"; // Replace with the actual view name for displaying the error message
        }
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("report", new Report());
        return "add_report"; // Replace with the actual view name for displaying the report creation form
    }

    @PostMapping("/create")
    public String createReport(@ModelAttribute("report") Report report) {
        reportService.save(report);
        return "redirect:/reports"; // Redirect to the URL for displaying all reports after successful creation
    }

    @GetMapping("{id}/edit")
    public String showEditForm(@PathVariable UUID id, Model model) {
        Optional<Report> optionalReport = reportService.findById(id);

        if (optionalReport.isPresent()) {
            Report report = optionalReport.get();
            model.addAttribute("report", report);
            return "edit_report"; // Replace with the actual view name for displaying the report edit form
        } else {
            // Report not found
            return "error_page"; // Replace with the actual view name for displaying the error message
        }
    }

    @PostMapping("{id}/edit")
    public String updateReport(@PathVariable UUID id, @ModelAttribute("report") Report report) {
        reportService.save(report);
        return "redirect:/reports"; // Redirect to the URL for displaying all reports after successful update
    }

    @GetMapping("{id}/delete")
    public String deleteReport(@PathVariable UUID id) {
        reportService.deleteById(id);
        return "redirect:/reports"; // Redirect to the URL for displaying all reports after successful deletion
    }
}


