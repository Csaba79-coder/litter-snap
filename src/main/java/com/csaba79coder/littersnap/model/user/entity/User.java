package com.csaba79coder.littersnap.model.user.entity;

import com.csaba79coder.littersnap.model.base.entity.Auditable;
import com.csaba79coder.littersnap.value.Role;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.logging.log4j.core.config.plugins.validation.constraints.NotBlank;

/**
 * This class contains the user entity.
 */
@Entity
@Embeddable
@Table(name = "litter_snap_user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User extends Auditable {

    /**
     * The user entity fields.
     * <p>
     *     email: the user email
     *     firstName: the user first name
     *     password: the user password
     *     role: the user role
     * </p>
     *  Extends Auditable class, that extends Identifier class, which contains the following fields:
     * <p>
     *     createdAt: the date when the entity was created
     *     updatedAt: the date when the entity was last modified
     *     createdBy: the user who created the entity
     *     updatedBy: the user who last modified the entity
     *     id: the litter id (UUID) - this is the primary key and auto-generated
     *  </p>
     */
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    /**
     * The user password.
     * <p>
     *     This field is not returned in the response.
     * </p>
     */
    @Column(name = "password", nullable = false)
    @NotBlank(message = "password is mandatory")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    /**
     * The user role. Default value is ROLE_USER. Possible values (enum) are ROLE_USER and ROLE_ADMIN.
     * <p>
     *     This field is not returned in the response.
     * </p>
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    @NotBlank(message = "email is mandatory")
    private Role role = Role.ROLE_USER;
}