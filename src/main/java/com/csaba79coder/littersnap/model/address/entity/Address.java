package com.csaba79coder.littersnap.model.address.entity;

import com.csaba79coder.littersnap.model.base.entity.Auditable;
import com.csaba79coder.littersnap.model.litter.entity.Litter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "address")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Address extends Auditable {

    @Column(name = "first_line")
    private String firstLine;

    @Column(name = "post_code")
    private String postCode;

    @Column(name = "city")
    private String city;

    @Column(name = "country")
    private String country;

    @ManyToMany
    private List<Litter> litters;

}
