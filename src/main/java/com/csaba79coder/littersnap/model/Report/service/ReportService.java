package com.csaba79coder.littersnap.model.Report.service;

import com.csaba79coder.littersnap.model.Report.persistence.LitterReportRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReportService {

    LitterReportRepository litterReportRepository;

}
