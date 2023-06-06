package com.csaba79coder.littersnap.view;

import com.csaba79coder.littersnap.model.report.dto.ReportModel;
import com.csaba79coder.littersnap.model.report.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.UUID;

/**
 * Controller for the report pages.
 * <p>
 * The controller is responsible for handling the GET and POST requests for the report pages.
 */
@Controller
@RequiredArgsConstructor
@RequestMapping("/thy/auth")
public class ReportViewController {

    /**
     * Dependency injection for the ReportService.
     * The ReportService is used to save the new report to the database.
     */
    private final ReportService reportService;

    /**
     * Show the report form.
     * Render all reports
     * @param model
     * @return
     */
    @GetMapping("/admin/reports")
    public String renderAllReports(Model model) {
        List<ReportModel> reports = reportService.findAllReports();

        if (reports.isEmpty()) {
            return "error_page";
        } else {
            model.addAttribute("reports", reports);
            return "litter_list"; // Replace with the actual view name for displaying the list of reports
        }
    }

    /**
     * Show the report form. Renders a report by id
     @ param id
     * @param model
     * @return
     */
    @GetMapping("/admin/reports/{id}")
    public String renderReportById(@PathVariable UUID id, Model model) {
        ReportModel currentReport = reportService.findReportById(id);

        if (currentReport == null) {
            return "error_page";
        } else {
            model.addAttribute("report", currentReport);
            return "litter_details";
        }
    }

    /**
     * Update a report by id
     * @param id
     * @param model
     * @return litter_edit_form.html
     */
    @GetMapping("/admin/reports/edit/{id}")
    public String showEditForm(@PathVariable UUID id, Model model) {
        ReportModel currentReport = reportService.findReportById(id);
        if (currentReport == null) {
            return "error_page";
        } else {
            model.addAttribute("report", currentReport);
            return "litter_edit_form";
        }
    }

    /**
     * Update a report by id
     * @param id
     * @param report
     * @return redirect:/reports
     */
    @PostMapping("/admin/reports/edit/{id}")
    public String updateReport(@PathVariable UUID id, @ModelAttribute("report") ReportModel report) {
        reportService.addNewReport(report);
        return "redirect:/reports"; // Redirect to the URL for displaying all reports after successful update
    }

    /**
     * Delete a report by id
     * @param id
     * @return
     */
    @GetMapping("/admin/reports/delete/{id}")
    public String deleteReport(@PathVariable UUID id) {
        reportService.deleteExistingReport(id);
        return "redirect:/reports"; // Redirect to the URL for displaying all reports after successful deletion
    }
}
