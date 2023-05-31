package com.csaba79coder.littersnap.util;

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

    // private constructor to prevent instantiation
    private Mapper() {
    }
}