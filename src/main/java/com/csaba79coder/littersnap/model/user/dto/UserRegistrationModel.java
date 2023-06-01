package com.csaba79coder.littersnap.model.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRegistrationModel {

    private String email;
    private String firstName;
    private String password;
    private String passwordConfirmation;
}