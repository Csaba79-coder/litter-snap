package com.csaba79coder.littersnap.model.address.dto;

import lombok.*;

/**
 * This class contains the address model.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddressModel {

    /**
     * The address model fields.
     * <p>
     *     id: the address id
     *     firstLine: the first line of the address
     *     postCode: the post code of the address
     *     city: the city of the address
     *     country: the country of the address
     * </p>
     */
    private String firstLine;
    private String postCode;
    private String city;
    private String country;

}
