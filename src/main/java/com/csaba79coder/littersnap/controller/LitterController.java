package com.csaba79coder.littersnap.controller;

import com.csaba79coder.littersnap.model.address.entity.Address;
import com.csaba79coder.littersnap.model.litter.dto.LitterCreateOrModifyModel;
import com.csaba79coder.littersnap.model.litter.dto.LitterModel;
import com.csaba79coder.littersnap.model.litter.service.LitterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class LitterController {

    // TODO: render all litters for the logged in user only!
    // TODO: create findByUserId method in ReportRepository

    private final LitterService litterService;

    @GetMapping("/admin/litters")
    public List<LitterModel> renderAllLitters() {
        return litterService.findAllLitters();
    }

    @PostMapping(value = "/admin/litters",
        consumes = "multipart/form-data",
            produces = "application/json")
    public ResponseEntity<LitterModel> addNewLitter(@ModelAttribute LitterCreateOrModifyModel litterModel,
                                                    @ModelAttribute Address address,
                                                    @RequestParam("file") MultipartFile file) {
        return ResponseEntity.status(201).body(litterService.addNewLitter(litterModel, address, file));
    }

    @GetMapping("/admin/litters/{id}")
    public ResponseEntity<LitterModel> renderLitterById(@PathVariable("id") UUID id) {
        LitterModel litter = litterService.findLitterById(id);
        if (litter != null) {
            return new ResponseEntity<>(litter, HttpStatus.OK);

        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping(value = "/admin/litters/modify/{id}",
            consumes = "multipart/form-data",
            produces = "application/json")
    public ResponseEntity<LitterModel> updateExistingLitter(@PathVariable("id") UUID id,
                                                            @RequestBody LitterCreateOrModifyModel model) {
        return ResponseEntity.status(200).body(litterService.modifyAnExistingLitter(id, model));
    }

    @DeleteMapping("/admin/litters/delete/{id}")
    public ResponseEntity<Void> deleteExistingLitter(@PathVariable("id") UUID id) {
        litterService.deleteAnExistingLitter(id);
        return ResponseEntity.status(204).build();
    }
}
