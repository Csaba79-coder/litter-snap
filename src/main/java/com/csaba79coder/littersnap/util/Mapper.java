package com.csaba79coder.littersnap.util;

import com.csaba79coder.littersnap.model.litter.dto.LitterCreateOrModifyModel;
import com.csaba79coder.littersnap.model.litter.dto.LitterModel;
import com.csaba79coder.littersnap.model.litter.entity.Litter;
import com.csaba79coder.littersnap.model.report.dto.ReportModel;
import com.csaba79coder.littersnap.model.report.entity.Report;
import org.modelmapper.ModelMapper;

public class Mapper {

    // static mapper methods comes here!
    private static final ModelMapper modelMapper = new ModelMapper();

    // private constructor to prevent instantiation
    private Mapper() {
    }

    public static ReportModel mapReportEntityToModel(Report entity) {
        return modelMapper.map(entity, ReportModel.class);
    }

    public static Report mapReportModelToEntity(ReportModel entity) {
        return modelMapper.map(entity, Report.class);
    }

    public static Litter mapLitterCreateOrModifyModelToEntity(LitterCreateOrModifyModel model) {
        return modelMapper.map(model, Litter.class);
    }

    public static LitterCreateOrModifyModel mapModelToLitterCreateOrModifyModel(LitterModel model) {
        return modelMapper.map(model, LitterCreateOrModifyModel.class);
    }

    public static LitterModel mapLitterEntityToModel(Litter entity) {
        return modelMapper.map(entity, LitterModel.class);
    }

    public static Litter mapLitterModelToEntity(LitterModel model) {
        return modelMapper.map(model, Litter.class);
    }
}
