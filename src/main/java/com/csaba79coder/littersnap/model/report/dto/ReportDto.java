package com.csaba79coder.littersnap.model.report.dto;

import com.csaba79coder.littersnap.model.address.dto.AddressDto;
import com.csaba79coder.littersnap.model.address.entity.Address;
import com.csaba79coder.littersnap.model.report.entity.Report;
import lombok.Data;
import java.util.HashSet;
import java.util.Set;

@Data
public class ReportDto {

    private Set<AddressDto> addresses;
    private byte[] image;
    //Add the litter class later.

    // Constructor to convert from Report entity to ReportDTO
    public ReportDto(Report report) {
        this.addresses = convertAddresses(report.getAddresses());
        this.image = report.getImage();
    }

    // Helper method to convert Address entities to AddressDTOs
    private Set<AddressDto> convertAddresses(Set<Address> addresses) {
        Set<AddressDto> addressDTOs = new HashSet<>();
        for (Address address : addresses) {
            addressDTOs.add(new AddressDto(address));
        }
        return addressDTOs;
    }


}
