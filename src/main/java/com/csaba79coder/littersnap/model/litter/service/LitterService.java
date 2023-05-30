package com.csaba79coder.littersnap.model.litter.service;

import com.csaba79coder.littersnap.model.litter.dto.LitterCreateOrModifyModel;
import com.csaba79coder.littersnap.model.litter.dto.LitterModel;
import com.csaba79coder.littersnap.model.litter.entity.Litter;
import com.csaba79coder.littersnap.model.litter.persistence.LitterRepository;
import com.csaba79coder.littersnap.util.Mapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class LitterService {

    private final LitterRepository litterRepository;

    //Image i will need @RequestParam("image") MultipartFile file


    public List<LitterModel> getAllLitters() {
        List<Litter> litters = litterRepository.findAll();
        return litters.stream()
                .map(Mapper::mapLitterEntityToModel)
                .collect(Collectors.toList());
    }

    public LitterModel addNewLitter(LitterCreateOrModifyModel litterModel) {
        Litter litterEntity = Mapper.mapLitterCreateOrModifyModelToEntity(litterModel);
        Litter savedLitterEntity = litterRepository.save(litterEntity);
        return Mapper.mapLitterEntityToModel(savedLitterEntity);
    }

    public LitterModel getLitterById(UUID id) {
        Optional<Litter> optionalLitter = litterRepository.findById(id);
        return optionalLitter.map(Mapper::mapLitterEntityToModel).orElse(null);
    }

    public LitterModel updateExistingLitter(UUID id, LitterCreateOrModifyModel model) {
        Optional<Litter> optionalExistingLitter = litterRepository.findById(id);
        if (optionalExistingLitter.isPresent()) {

            //Converting the litter to litterModel
            LitterModel existingLitter = Mapper.mapLitterEntityToModel(optionalExistingLitter.get());

            //To find out about addresses.
            existingLitter.setAddresses(model.getAddress());
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
