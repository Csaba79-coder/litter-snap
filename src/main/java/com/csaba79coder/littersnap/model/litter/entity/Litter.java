package com.csaba79coder.littersnap.model.litter.entity;

import com.csaba79coder.littersnap.model.address.entity.Address;
import com.csaba79coder.littersnap.model.base.entity.Auditable;
import com.csaba79coder.littersnap.value.LitterStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "litter")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Litter extends Auditable {

    @Enumerated(EnumType.STRING)
    @Column(name = "status" , nullable = false)
    private LitterStatus status = LitterStatus.REPORTED;

    @Column(name = "description",nullable = false)
    private String description;

    @Lob
    @Column(name = "image", length = Integer.MAX_VALUE, nullable = false)
    private byte[] image;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;
}
