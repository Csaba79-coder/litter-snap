package com.csaba79coder.littersnap.model.litter.service;

import com.csaba79coder.littersnap.model.address.entity.Address;
import com.csaba79coder.littersnap.model.address.persitence.AddressRepository;
import com.csaba79coder.littersnap.model.litter.dto.LitterCreateOrModifyModel;
import com.csaba79coder.littersnap.model.litter.dto.LitterModel;
import com.csaba79coder.littersnap.model.litter.entity.Litter;
import com.csaba79coder.littersnap.model.litter.persistence.LitterRepository;
import com.csaba79coder.littersnap.util.ImageUtil;
import com.csaba79coder.littersnap.util.Mapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * This class contains the litter service.
 * Also include logs errors and exceptions.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class LitterService {

    /**
     * Dependency injection fields.
     * <p>
     *     litterRepository: the litter repository
     *     addressRepository: the address repository
     * </p>
     */
    private final LitterRepository litterRepository;
    private final AddressRepository addressRepository;

    /**
     * This method finds all litters.
     * @return the litter model
     * @throws NoSuchElementException if the litter list is empty
     */
    public List<LitterModel> findAllLitters() {
        List<Litter> litters = litterRepository.findAll();

        if (litters.isEmpty()) {
            String message = "Litter list is empty";
            log.error(message);
            throw new NoSuchElementException(message);
        }

        return litters.stream()
                .map(litter -> {
                    LitterModel litterModel = Mapper.mapLitterEntityToModel(litter);
                    byte[] decompressedImage = ImageUtil.decompressImage(litter.getImage());
                    litterModel.setImage(decompressedImage);
                    return litterModel;
                })
                .collect(Collectors.toList());
    }

    /**
     * Adding a new litter.
     * @param litterModel
     * @param address
     * @param file
     * @return the litter model
     * @throws NoSuchElementException if the address is not found
     */
    public LitterModel addNewLitter(LitterCreateOrModifyModel litterModel, Address address, MultipartFile file) {
        Optional<Address> addressOptional = addressRepository.findAddressByCityContainingIgnoreCaseAndCountryContainingIgnoreCaseAndPostCodeAndFirstLineContainsIgnoreCase(
                address.getCity(), address.getCountry(), address.getPostCode(), address.getFirstLine());
        if (addressOptional.isPresent()) {
            address = addressOptional.get();
        } else {
            address = addressRepository.save(address);
        }
        Litter litterEntity = new Litter();
        litterEntity.setAddress(address);
        litterEntity.setDescription(litterModel.getDescription());
        try {
            litterEntity.setImage(ImageUtil.compressImage(file.getBytes())); // Compressing the image before saving
        } catch (IOException e) {
            String message = String.format("File not found."+ e.getMessage());
            log.error(message);
            throw new NoSuchElementException(e);
        }
        Litter savedLitterEntity = litterRepository.save(litterEntity);
        return Mapper.mapLitterEntityToModel(savedLitterEntity);
    }

    /**
     * This method finds a litter by id.
     * @param id the litter id
     * @return the litter model
     * @throws NoSuchElementException if the litter is not found
     */
    public LitterModel findLitterById(UUID id) {
        Optional<Litter> litterOptional = litterRepository.findById(id);
        if (litterOptional.isPresent()) {
            Litter litter = litterOptional.get();
            LitterModel litterModel = Mapper.mapLitterEntityToModel(litter);
            byte[] decompressedImage = ImageUtil.decompressImage(litter.getImage());
            litterModel.setImage(decompressedImage);
            return litterModel;
        } else {
            String message = String.format("Litter with id %s not found", id);
            log.error(message);
            throw new NoSuchElementException(message);
        }
    }

    /**
     * This method update a litter by id.
     * @param id
     * @param model
     * @return LitterModel
     * throws NoSuchElementException if the litter is not found
     */
    public LitterModel modifyAnExistingLitter(UUID id, LitterCreateOrModifyModel model) {
        Optional<Litter> optionalExistingLitter = litterRepository.findById(id);
        if (optionalExistingLitter.isPresent()) {

            //Converting the litter to litterModel
            LitterModel existingLitter = Mapper.mapLitterEntityToModel(optionalExistingLitter.get());

            //To find out about addresses.
            existingLitter.setAddress(model.getAddress());
            existingLitter.setDescription(model.getDescription());
            existingLitter.setImage(model.getImage());

            //Converting the model to entity and saving it.
            Litter updatedLitter = litterRepository.save(Mapper.mapLitterModelToEntity(existingLitter));

            return Mapper.mapLitterEntityToModel(updatedLitter);
        }else {
            String message = String.format("Couldn't update. Litter with id %s not found", id);
            log.error(message);
            throw new NoSuchElementException(message);
        }

    }

    /**
     * This method deletes a litter by id.
     * @param id
     * @return empty body (only status code)
     * @throws NoSuchElementException if the litter is not found
     */
    public void deleteAnExistingLitter(UUID id) {
        Litter litter = litterRepository.findById(id)
                .orElseThrow(() -> {
                    String message = "Litter not found with id: " + id;
                    log.error(message);
                    throw new IllegalArgumentException(message);
                });
        litterRepository.deleteById(litter.getId());
    }
}

