package com.csaba79coder.littersnap.controller;

import com.csaba79coder.littersnap.model.address.entity.Address;
import com.csaba79coder.littersnap.model.litter.dto.LitterCreateOrModifyModel;
import com.csaba79coder.littersnap.model.litter.dto.LitterModel;
import com.csaba79coder.littersnap.model.litter.service.LitterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/litters")
@RequiredArgsConstructor
public class LitterController {

    private final LitterService litterService;

    @GetMapping
    public List<LitterModel> getAllLitters() {
        return litterService.findAllLitters();
    }

    @PostMapping
    public ResponseEntity<LitterModel> addNewLitter(@RequestBody LitterCreateOrModifyModel litterModel,
                                                    @RequestBody Address address,
                                                    @RequestParam("file") MultipartFile file) {
        return ResponseEntity.status(201).body(litterService.addNewLitter(litterModel, address, file));
    }

    @GetMapping("/{id}")
    public ResponseEntity<LitterModel> getLitterById(@PathVariable("id") UUID id) {
        LitterModel litter = litterService.findLitterById(id);
        if (litter != null) {
            return new ResponseEntity<>(litter, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LitterModel> updateExistingLitter(@PathVariable("id") UUID id,
                                                            @RequestBody LitterCreateOrModifyModel model) {
        return ResponseEntity.status(200).body(litterService.modifyAnExistingLitter(id, model));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExistingLitter(@PathVariable("id") UUID id) {
        LitterModel litter = litterService.findLitterById(id);
        litterService.deleteAnExistingLitter(id);

        return ResponseEntity.status(204).build();
    }
}
