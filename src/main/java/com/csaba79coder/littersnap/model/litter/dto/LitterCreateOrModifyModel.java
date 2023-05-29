package com.csaba79coder.littersnap.model.litter.dto;

import com.csaba79coder.littersnap.model.address.dto.AddressModel;
import com.csaba79coder.littersnap.value.LitterStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LitterCreateOrModifyModel {

    private AddressModel addresses;
    private String description;
    private byte[] image;
    private LitterStatus status;

}
