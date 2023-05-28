package com.csaba79coder.littersnap.model.address.dto;

import com.csaba79coder.littersnap.model.address.entity.Address;
import lombok.Data;

@Data
public class AddressDto {

    private String firstLine;
    private String postCode;
    private String city;
    private String country;

    public AddressDto(Address address) {
        this.firstLine = address.getFirstLine();
        this.postCode = address.getPostCode();
        this.city = address.getCity();
        this.country = address.getCountry();
    }


}
