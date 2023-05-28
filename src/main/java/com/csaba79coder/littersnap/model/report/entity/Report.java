package com.csaba79coder.littersnap.model.report.entity;

import com.csaba79coder.littersnap.model.address.entity.Address;
import com.csaba79coder.littersnap.model.base.entity.Auditable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;


@Entity
@Embeddable
@Table(name = "litter_report")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Report extends Auditable {

    @ManyToMany
    private Set<Address> addresses;

    @Lob
    @Column(name = "image", length = Integer.MAX_VALUE, nullable = false)
    private byte[] image;

    //    Litter instance should be injected as well

}
