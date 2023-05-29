package com.csaba79coder.littersnap.controller;

import com.csaba79coder.littersnap.model.litter.dto.LitterCreateOrModifyModel;
import com.csaba79coder.littersnap.model.litter.dto.LitterModel;
import com.csaba79coder.littersnap.model.litter.service.LitterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class LitterController {

    private final LitterService litterService;

    @GetMapping("/litters")
    public List<LitterModel> getAllLitters() {
        return litterService.getAllLitters();
    }

    @PostMapping("/litters")
    public ResponseEntity<LitterModel> addNewLitter(@RequestBody LitterCreateOrModifyModel litterModel) {
        return ResponseEntity.status(201).body(litterService.addNewLitter(litterModel));
    }

    @GetMapping("/litters/{id}")
    public ResponseEntity<LitterModel> getLitterById(@PathVariable("id") UUID id) {
        LitterModel litter = litterService.getLitterById(id);
        if (litter != null) {
            return new ResponseEntity<>(litter, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/litters/{id}")
    public ResponseEntity<LitterModel> updateExistingLitter(
            @PathVariable("id") UUID id,
            @RequestBody LitterCreateOrModifyModel model
    ) {
        return ResponseEntity.status(200).body(litterService.updateExistingLitter(id,model));
    }

    @DeleteMapping("/litters/{id}")
    public ResponseEntity<Void> deleteExistingLitter(@PathVariable("id") UUID id) {
        LitterModel litter = litterService.getLitterById(id);
            litterService.deleteLitter(id);

        return ResponseEntity.status(204).build();
    }
}
