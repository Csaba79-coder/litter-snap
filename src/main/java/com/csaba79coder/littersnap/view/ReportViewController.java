package com.csaba79coder.littersnap.view;

import com.csaba79coder.littersnap.model.address.dto.AddressModel;
import com.csaba79coder.littersnap.model.report.dto.ReportModel;
import com.csaba79coder.littersnap.model.report.entity.Report;
import com.csaba79coder.littersnap.model.report.service.ReportService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/thy")
public class ReportViewController {

    private final ReportService reportService;

    public ReportViewController(ReportService reportService) {
        this.reportService = reportService;
    }


    @GetMapping("/reports")
    public String getAllReports(Model model) {
        List<ReportModel> reports = reportService.getAllReports();

        if(reports.isEmpty()) {
            return "error_page";
        }else{
            model.addAttribute("reports", reports);
            return "report_list"; // Replace with the actual view name for displaying the list of reports
        }
    }

    @GetMapping("/reports/{id}")
    public String getReportById(@PathVariable UUID id, Model model) {
        ReportModel currentReport = reportService.getReportById(id);

        if (currentReport == null) {
            return "error_page";
        } else {
            model.addAttribute("report", currentReport);
            return "report_details";
        }
    }

    @GetMapping("/reports/create")
    public String showCreateForm(Model model) {
        model.addAttribute("report", new Report());
        return "add_report"; // Replace with the actual view name for displaying the report creation form
    }

    @PostMapping("/reports/create")
    public String createReport(@ModelAttribute("report") Report report, @RequestParam("image") MultipartFile file) throws IOException {

        AddressModel addressModel = new AddressModel();
//       ReportModel currentReportModel = new ReportModel(addressModel, ImageUtil.compressImage(file.getBytes()));
//        reportService.addNewReport(currentReportModel);
        return "redirect:/reports"; // Redirect to the URL for displaying all reports after successful creation
    }

    @GetMapping("/reports/{id}/edit")
    public String showEditForm(@PathVariable UUID id, Model model) {
        ReportModel currentReport = reportService.getReportById(id);
        if (currentReport == null) {
            return "error_page";
        } else {
            model.addAttribute("report", currentReport);
            return "edit_report";
        }
    }

    @PostMapping("/reports/{id}/edit")
    public String updateReport(@PathVariable UUID id, @ModelAttribute("report") Report report) {
//        reportService.addNewReport(report);
        return "redirect:/reports"; // Redirect to the URL for displaying all reports after successful update
    }

    @GetMapping("/reports/{id}/delete")
    public String deleteReport(@PathVariable UUID id) {
        reportService.deleteExistingReport(id);
        return "redirect:/reports"; // Redirect to the URL for displaying all reports after successful deletion
    }
}


