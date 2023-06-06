package com.csaba79coder.littersnap.model.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * This class contains the user registration model.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRegistrationModel {

    /**
     * The user registration model fields.
     * <p>
     *     email: the user email
     *     firstName: the user first name
     *     password: the user password
     *     passwordConfirmation: the user password confirmation
     * </p>
     */
    private String email;
    private String firstName;
    private String password;
    private String passwordConfirmation;
}