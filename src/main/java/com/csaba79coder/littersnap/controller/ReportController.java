package com.csaba79coder.littersnap.controller;

import com.csaba79coder.littersnap.model.Report.entity.Report;
import com.csaba79coder.littersnap.model.Report.persistence.LitterReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/thy")
public class ReportController {

    @Autowired
    LitterReportRepository reportRepository;

    // Mapping for adding a new report (GET)
    @GetMapping("/reportForm")
    public String showAddReportForm(Model model) {
        model.addAttribute("report", new Report());
        model.addAttribute("view", "addReport");

        return "index";
    }

}


