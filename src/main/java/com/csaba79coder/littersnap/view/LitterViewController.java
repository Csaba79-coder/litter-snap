package com.csaba79coder.littersnap.view;

import com.csaba79coder.littersnap.model.address.entity.Address;
import com.csaba79coder.littersnap.model.litter.dto.LitterCreateOrModifyModel;
import com.csaba79coder.littersnap.model.litter.dto.LitterModel;
import com.csaba79coder.littersnap.model.litter.service.LitterService;
import com.csaba79coder.littersnap.util.Mapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/thy/litter")
public class LitterViewController {

    private final LitterService litterService;

    public LitterViewController(LitterService litterService) {
        this.litterService = litterService;
    }

    @GetMapping
    public String getAllLitters(Model model) {
        List<LitterModel> litters = litterService.getAllLitters();

        if (litters.isEmpty()) {
            return "error_page";
        } else {
            model.addAttribute("litters", litters);
            return "litter_list"; // Replace with the actual view name for displaying the list of litters
        }
    }

    @GetMapping("/{id}")
    public String getLitterById(@PathVariable("id") UUID id, Model model) {
        LitterModel litter = litterService.getLitterById(id);
        model.addAttribute("litter", litter);
        return "litter_details";
    }

    @GetMapping("/create")
    public String showAddLitterForm(Model model) {
        model.addAttribute("litter", new LitterCreateOrModifyModel());
        return "add_litter_form";
    }

    @PostMapping("/create")
    public String addNewLitter(@ModelAttribute("litter") LitterCreateOrModifyModel litterModel,
                               @ModelAttribute("address") Address address,
                               @RequestParam("file") MultipartFile file) {
        litterService.addNewLitter(litterModel, address, file);
        return "redirect:/litters";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable UUID id, Model model) {
        LitterCreateOrModifyModel currentLitter = Mapper.mapModelToLitterCreateOrModifyModel(litterService.getLitterById(id));
        if (currentLitter == null) {
            return "error_page";
        } else {
            model.addAttribute("litter", currentLitter);
            return "edit_litter";
        }
    }

    @PostMapping("/edit/{id}")
    public String updateLitter(@PathVariable UUID id, @ModelAttribute("report") LitterCreateOrModifyModel litterModel) {
        litterService.updateExistingLitter(id,litterModel);
        return "redirect:/reports"; // Redirect to the URL for displaying all reports after successful update
    }

    @GetMapping("/delete/{id}")
    public String deleteLitter(@PathVariable UUID id) {
        litterService.deleteLitter(id);
        return "redirect:/reports"; // Redirect to the URL for displaying all reports after successful deletion
    }



}
