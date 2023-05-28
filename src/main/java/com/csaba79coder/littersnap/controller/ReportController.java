package com.csaba79coder.littersnap.controller;

import com.csaba79coder.littersnap.model.report.entity.Report;
import com.csaba79coder.littersnap.model.report.persistence.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("/thy")
public class ReportController {

    @Autowired
    private ReportRepository reportRepository;

    @GetMapping("reports")
    public String getAllReports(Model model) {
        List<Report> reports = reportRepository.findAll();
        model.addAttribute("reports", reports);
        return "report_list"; // Replace with the actual view name for displaying the list of reports
    }

    @GetMapping("/{id}")
    public String getReportById(@PathVariable UUID id, Model model) {
        Optional<Report> optionalReport = reportRepository.findById(id);
        if (optionalReport.isPresent()) {
            Report report = optionalReport.get();
            model.addAttribute("report", report);
            return "report_details"; // Replace with the actual view name for displaying the report details

        } else {

            // Report not found
            return "error"; // Replace with the actual view name for displaying the error message

        }
    }

    @PostMapping("createReport")
    public String createReport(@ModelAttribute Report report) {
        // Logic to create the report
        return "redirect:/thy/reports";
    }



    @GetMapping("reports/{id}")
    public String showReport(@PathVariable("id") UUID id, Model model) {
        // Logic to fetch the report with the given ID and add it to the model
        return "report_details";
    }

    @GetMapping("reports/{id}/edit")
    public String showEditForm(@PathVariable("id") UUID id, Model model) {
        // Logic to fetch the report with the given ID and add it to the model
        return "edit_report";
    }

    @PostMapping("reports/{id}/edit")
    public String editReport(@PathVariable("id") UUID id, @ModelAttribute Report report) {
        // Logic to update the report with the given ID
        return "redirect:/thy/reports";
    }

    @PostMapping("reports/{id}/delete")
    public String deleteReport(@PathVariable("id") UUID id) {
        // Logic to delete the report with the given ID
        return "redirect:/thy/reports";
    }


}


