package com.csaba79coder.littersnap.model.litter.entity;

import com.csaba79coder.littersnap.model.address.entity.Address;
import com.csaba79coder.littersnap.model.base.entity.Auditable;
import com.csaba79coder.littersnap.value.LitterStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * This class contains the litter entity.
 */
@Entity
@Table(name = "litter")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Litter extends Auditable {

    /**
     * The litter entity fields.
     * <p>
     *     status: the litter status
     *     description: the litter description
     *     image: the litter image
     * </p>
     * Extends Auditable class, that extends Identifier class, which contains the following fields:
     * <p>
     *     createdAt: the date when the entity was created
     *     updatedAt: the date when the entity was last modified
     *     createdBy: the user who created the entity
     *     updatedBy: the user who last modified the entity
     *     id: the litter id (UUID) - this is the primary key and auto-generated
     * </p>
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "status" , nullable = false)
    private LitterStatus status = LitterStatus.REPORTED;

    @Column(name = "description",nullable = false)
    private String description;

    @Lob
    @Column(name = "image", length = Integer.MAX_VALUE, nullable = false)
    private byte[] image;

    /**
     * The litter entity relationships.
     * <p>
     *     address: the address associated with the litter
     * </p>
     */
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;
}
