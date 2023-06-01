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

@Service
@RequiredArgsConstructor
@Slf4j
public class LitterService {

    private final LitterRepository litterRepository;

    private final AddressRepository addressRepository;

    public List<LitterModel> getAllLitters() {
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

    public LitterModel getLitterById(UUID id) {
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

    public LitterModel updateExistingLitter(UUID id, LitterCreateOrModifyModel model) {
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

    public void deleteLitter(UUID id) {
        Optional<Litter> optionalExistingLitter = litterRepository.findById(id);
        if (optionalExistingLitter.isPresent()) {
            litterRepository.deleteById(id);
        } else {
            String message = String.format("Couldn't delete. Litter with id %s not found", id);
            log.error(message);
            throw new NoSuchElementException(message);
        }
    }
}
