package com.csaba79coder.littersnap.model.report.entity;

import com.csaba79coder.littersnap.model.address.entity.Address;
import com.csaba79coder.littersnap.model.base.entity.Auditable;
import com.csaba79coder.littersnap.value.LitterStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Entity
@Embeddable
@Table(name = "litter_report")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Report extends Auditable {

    @ManyToOne
    private Address address;

    @Lob
    @Column(name = "image", length = Integer.MAX_VALUE, nullable = false)
    private byte[] image;

    private LitterStatus status;


    //    Litter instance should be injected as well

}
