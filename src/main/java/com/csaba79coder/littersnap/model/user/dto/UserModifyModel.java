package com.csaba79coder.littersnap.model.user.dto;

import com.csaba79coder.littersnap.value.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * This class contains the user modify model.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserModifyModel {

    /**
     * The user modify model fields.
     * <p>
     *     email: the user email
     *     firstName: the user first name
     *     password: the user password
     *     passwordConfirmation: the user password confirmation
     *     role: the user role
     * </p>
     */
    private String email;
    private String firstName;
    private String password;
    private String passwordConfirmation;
    private Role role;
}