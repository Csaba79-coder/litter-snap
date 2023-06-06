package com.csaba79coder.littersnap.model.address.persitence;

import com.csaba79coder.littersnap.model.address.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

/**
 * This class contains the address repository.
 */
@Repository
public interface AddressRepository extends JpaRepository<Address, UUID> {

    /**
     * This method finds an address by city, country, post code and first line.
     *
     * @param city the city of the address
     * @param country the country of the address
     * @param postCode the post code of the address
     * @param firstLine the first line of the address
     * @return the address
     */
    Optional<Address> findAddressByCityContainingIgnoreCaseAndCountryContainingIgnoreCaseAndPostCodeAndFirstLineContainsIgnoreCase(
            String city, String country, String postCode, String firstLine);
}
