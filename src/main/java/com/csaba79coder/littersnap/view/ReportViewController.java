package com.csaba79coder.littersnap.view;

import com.csaba79coder.littersnap.model.report.dto.ReportModel;
import com.csaba79coder.littersnap.model.report.service.ReportService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/thy/reports")
public class ReportViewController {

    private final ReportService reportService;

    public ReportViewController(ReportService reportService) {
        this.reportService = reportService;
    }



    @GetMapping
    public String getAllReports(Model model) {
        List<ReportModel> reports = reportService.getAllReports();

        if (reports.isEmpty()) {
            return "error_page";
        } else {
            model.addAttribute("reports", reports);
            return "report_list"; // Replace with the actual view name for displaying the list of reports
        }
    }

    @GetMapping("/{id}")
    public String getReportById(@PathVariable UUID id, Model model) {
        ReportModel currentReport = reportService.getReportById(id);

        if (currentReport == null) {
            return "error_page";
        } else {
            model.addAttribute("report", currentReport);
            return "report_details";
        }
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable UUID id, Model model) {
        ReportModel currentReport = reportService.getReportById(id);
        if (currentReport == null) {
            return "error_page";
        } else {
            model.addAttribute("report", currentReport);
            return "edit_report";
        }
    }

    @PostMapping("/edit/{id}")
    public String updateReport(@PathVariable UUID id, @ModelAttribute("report") ReportModel report) {
        reportService.addNewReport(report);
        return "redirect:/reports"; // Redirect to the URL for displaying all reports after successful update
    }

    @GetMapping("/delete/{id}")
    public String deleteReport(@PathVariable UUID id) {
        reportService.deleteExistingReport(id);
        return "redirect:/reports"; // Redirect to the URL for displaying all reports after successful deletion
    }
}


