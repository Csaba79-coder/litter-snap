package com.csaba79coder.littersnap.model.address.persitence;

import com.csaba79coder.littersnap.model.address.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AddressRepository extends JpaRepository<Address, UUID> {

    Optional<Address> findAddressByCityContainingIgnoreCaseAndCountryContainingIgnoreCaseAndPostCodeAndFirstLineContainsIgnoreCase(
            String city, String country, String postCode, String firstLine);
}
