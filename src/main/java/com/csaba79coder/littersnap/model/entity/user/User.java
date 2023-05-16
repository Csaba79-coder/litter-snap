package com.csaba79coder.littersnap.model.entity.user;

import com.csaba79coder.littersnap.model.entity.base.Identifier;
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
public class User extends Identifier {

    @Column(name = "email", nullable = false, unique = true)
    private String email;
}
