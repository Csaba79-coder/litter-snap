package com.csaba79coder.littersnap.util;

import com.csaba79coder.littersnap.model.address.dto.AddressModel;
import com.csaba79coder.littersnap.model.address.entity.Address;
import com.csaba79coder.littersnap.model.litter.dto.LitterCreateOrModifyModel;
import com.csaba79coder.littersnap.model.litter.dto.LitterModel;
import com.csaba79coder.littersnap.model.litter.entity.Litter;
import com.csaba79coder.littersnap.model.report.dto.ReportModel;
import com.csaba79coder.littersnap.model.report.entity.Report;
import com.csaba79coder.littersnap.model.user.dto.UserModel;
import com.csaba79coder.littersnap.model.user.dto.UserRegistrationModel;
import com.csaba79coder.littersnap.model.user.entity.User;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Mapper {

    // static mapper methods comes here!
    private static final ModelMapper modelMapper = new ModelMapper();

    public static User mapUserRegistrationModelToUserEntity(UserRegistrationModel userRegistrationModel) {
        User user = new User();
        user.setEmail(userRegistrationModel.getEmail());
        user.setPassword(new BCryptPasswordEncoder().encode(userRegistrationModel.getPassword()));
        user.setFirstName(userRegistrationModel.getFirstName());
        return user;
    }

    public static UserModel mapUserEntityToModel(User user) {
        return modelMapper.map(user, UserModel.class);
    }

    public static ReportModel mapReportEntityToModel(Report entity) {
        return modelMapper.map(entity, ReportModel.class);
    }

    public static Report mapReportModelToEntity(ReportModel entity) {
        return modelMapper.map(entity, Report.class);
    }

    public static LitterCreateOrModifyModel mapModelToLitterCreateOrModifyModel(LitterModel model) {
        return modelMapper.map(model, LitterCreateOrModifyModel.class);
    }

    public static LitterModel mapLitterEntityToModel(Litter entity) {
        LitterModel model = new LitterModel();
        model.setId(entity.getId());
        model.setCreatedAt(entity.getCreatedAt());
        model.setCreatedBy(entity.getCreatedBy());
        model.setUpdatedAt(entity.getUpdatedAt());
        model.setUpdatedBy(entity.getUpdatedBy());
        model.setImage(ImageUtil.decompressImage(entity.getImage()));
        model.setAddress(mapAddressEntityToModel(entity.getAddress()));
        model.setDescription(entity.getDescription());
        model.setStatus(entity.getStatus());
        return model;
    }

    public static Litter mapLitterModelToEntity(LitterModel model) {
        return modelMapper.map(model, Litter.class);
    }

    public static AddressModel mapAddressEntityToModel(Address entity) {
        return modelMapper.map(entity, AddressModel.class);
    }

    /**
     * private constructor to prevent instantiation
     */
    private Mapper() {
    }
}
