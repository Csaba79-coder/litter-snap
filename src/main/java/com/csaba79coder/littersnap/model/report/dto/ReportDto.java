package com.csaba79coder.littersnap.model.report.dto;

import com.csaba79coder.littersnap.model.address.dto.AddressDto;
import lombok.Data;


@Data
public class ReportDto {

    private AddressDto addresses;
    private byte[] image;
    //Add the litter class later.

    // Constructor to convert from Report entity to ReportDTO


    public ReportDto(AddressDto addresses, byte[] image) {
        this.addresses = addresses;
        this.image = image;
    }



}
