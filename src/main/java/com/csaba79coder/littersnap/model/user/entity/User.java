package com.csaba79coder.littersnap.model.user.entity;

import com.csaba79coder.littersnap.model.base.entity.Auditable;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Embeddable
@Table(name = "litter_snap_user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User extends Auditable {

    @Column(name = "email", nullable = false, unique = true)
    private String email;
}
