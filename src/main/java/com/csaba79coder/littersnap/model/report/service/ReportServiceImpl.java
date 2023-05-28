package com.csaba79coder.littersnap.model.report.service;

import com.csaba79coder.littersnap.model.report.entity.Report;
import com.csaba79coder.littersnap.model.report.persistence.ReportRepository;
import com.csaba79coder.littersnap.util.ImageUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReportServiceImpl implements ReportService {

    ReportRepository reportRepository;

    public ReportServiceImpl(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    @Override
    public List<Report> findAll() {

        List<Report> reports = reportRepository.findAll();
        return reports != null ? reports : Collections.emptyList();
    }

    @Override
    public Optional<Report> findById(UUID id) {
        return reportRepository.findById(id);

    }

    @Override
    public void save(Report report) {
        report.setImage(ImageUtil.compressImage(report.getImage()));
        reportRepository.save(report);
    }

    @Override
    public void deleteById(UUID id) {
        reportRepository.deleteById(id);
    }

}
