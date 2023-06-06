package com.csaba79coder.littersnap.model.user.dto;

import com.csaba79coder.littersnap.value.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * This class contains the user model.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserModel {

    /**
     * The user model fields.
     * <p>
     *     id: the user id
     *     createdAt: the user created at
     *     updatedAt: the user updated at
     *     createdBy: the user created by
     *     updatedBy: the user updated by
     *     email: the user email
     *     firstName: the user first name
     *     role: the user role
     * </p>
     */
    private UUID id;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private UUID createdBy;
    private UUID updatedBy;
    private String email;
    private String firstName;
    private Role role;

}
