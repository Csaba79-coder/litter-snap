package com.csaba79coder.littersnap.model.report.dto;

import com.csaba79coder.littersnap.model.address.dto.AddressModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReportModel {

    AddressModel addresses;
    private byte[] image;
//    private LitterStatus status;

    //TODO
    //Add the litter class later.
}
