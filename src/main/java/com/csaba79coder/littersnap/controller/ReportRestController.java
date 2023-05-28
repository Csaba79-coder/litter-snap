package com.csaba79coder.littersnap.controller;

import com.csaba79coder.littersnap.model.report.entity.Report;
import com.csaba79coder.littersnap.model.report.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ReportRestController {

    private final ReportService reportService;

    @GetMapping("/reports")
    public List<Report> renderAllReports(){
        return reportService.findAllReports();
    }



}
