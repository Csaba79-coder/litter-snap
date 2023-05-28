package com.csaba79coder.littersnap.util;

import com.csaba79coder.littersnap.model.report.dto.ReportModel;
import com.csaba79coder.littersnap.model.report.entity.Report;
import org.modelmapper.ModelMapper;

public class Mapper {

    // static mapper methods comes here!
    private static final ModelMapper modelMapper = new ModelMapper();

    public static ReportModel mapReportEntityToModel(Report report) {
        return modelMapper.map(report, ReportModel.class);
    }

    // private constructor to prevent instantiation
    private Mapper() {
    }

}
