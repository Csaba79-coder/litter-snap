package com.csaba79coder.littersnap.model.address.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddressModel {

    private String firstLine;
    private String postCode;
    private String city;
    private String country;

}
