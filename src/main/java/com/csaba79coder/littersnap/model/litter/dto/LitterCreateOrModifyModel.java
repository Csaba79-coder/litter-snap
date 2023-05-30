package com.csaba79coder.littersnap.model.litter.dto;

import com.csaba79coder.littersnap.model.address.dto.AddressModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LitterCreateOrModifyModel {

    private AddressModel address;
    private String description;
    private byte[] image;

}
