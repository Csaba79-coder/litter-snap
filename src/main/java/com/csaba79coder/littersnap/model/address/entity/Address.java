package com.csaba79coder.littersnap.model.address.entity;

import com.csaba79coder.littersnap.model.base.entity.Auditable;
import com.csaba79coder.littersnap.model.litter.entity.Litter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * This class contains the address entity.
 */
@Entity
@Table(name = "address")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Address extends Auditable {

    /**
     * The address entity fields.
     * <p>
     *     id: the address id
     *     firstLine: the first line of the address
     *     postCode: the post code of the address
     *     city: the city of the address
     *     country: the country of the address
     * </p>
     * Extends Auditable class, that extends Identifier class, which contains the following fields:
     * <p>
     *     createdAt: the date when the entity was created
     *     updatedAt: the date when the entity was last modified
     *     createdBy: the user who created the entity
     *     updatedBy: the user who last modified the entity
     *     id: the address id (UUID) - this is the primary key and auto-generated
     * </p>
     */
    @Column(name = "first_line")
    private String firstLine;

    @Column(name = "post_code")
    private String postCode;

    @Column(name = "city")
    private String city;

    @Column(name = "country")
    private String country;

    /**
     * The address entity relationships.
     * <p>
     *     litters: the list of litters associated with the address
     * </p>
     */
    @OneToMany(mappedBy = "address")
    private List<Litter> litters;
}
