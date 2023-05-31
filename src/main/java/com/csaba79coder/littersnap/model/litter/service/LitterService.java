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
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class LitterService {

    private final LitterRepository litterRepository;

    private final AddressRepository addressRepository;

    //Image i will need @RequestParam("image") MultipartFile file


    public List<LitterModel> getAllLitters() {
        List<Litter> litters = litterRepository.findAll();
        return litters.stream()
                .map(Mapper::mapLitterEntityToModel)
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
        litterEntity.setAddress(address); //TODO set an incoming address also here! if address is not found in entiy new must be created
        litterEntity.setDescription(litterModel.getDescription());
        try {
            litterEntity.setImage(ImageUtil.compressImage(file.getBytes())); // Compressing the image before saving
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Litter savedLitterEntity = litterRepository.save(litterEntity);
        return Mapper.mapLitterEntityToModel(savedLitterEntity);
    }

    public LitterModel getLitterById(UUID id) {
        Optional<LitterModel> model = litterRepository.findById(id).map(Mapper::mapLitterEntityToModel);
        if (model.isPresent()) {
            return model.get();
        } else {
            String message = String.format("Litter with id %s not found", id);
            log.error(message);
            throw new RuntimeException(message);
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
        }
        //TODO
        //Case where liter not found
        return null;
    }

    public void deleteLitter(UUID id) {
        Optional<Litter> optionalExistingLitter = litterRepository.findById(id);
        if (optionalExistingLitter.isPresent()) {
            litterRepository.deleteById(id);
        } else {
            //TODO
            //Case where liter not found
        }
    }
}
