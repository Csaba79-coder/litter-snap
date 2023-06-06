package com.csaba79coder.littersnap.model.litter.dto;

import com.csaba79coder.littersnap.model.address.dto.AddressModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

/**
 * This class contains the litter create or modify model.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LitterCreateOrModifyModel {

    /**
     * The litter create or modify model fields.
     * <p>
     *     id: the litter id
     *     address: the litter address
     *     description: the litter description
     *     image: the litter image
     * </p>
     */
    private UUID id;
    private AddressModel address;
    private String description;
    private byte[] image;

}
