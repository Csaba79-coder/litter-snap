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
import java.util.NoSuchElementException;
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
        try {
            List<LitterModel> litters = litterService.getAllLitters();
            model.addAttribute("litters", litters);
            return "litter_list"; // Replace with the actual view name for displaying the list of litters
        } catch (NoSuchElementException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "error_page"; // Redirect to the error page to display the error message
        }
    }

    @GetMapping("/{id}")
    public String getLitterById(@PathVariable("id") UUID id, Model model) {

        try {
            LitterModel litter = litterService.getLitterById(id);
            model.addAttribute("litter", litter);
            return "litter_details";
        } catch (NoSuchElementException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "error_page"; // Redirect to the error page to display the error message
        }
    }

    @GetMapping("/create")
    public String showAddLitterForm(Model model) {
        try {
            model.addAttribute("litter", new LitterCreateOrModifyModel());
            return "litter_add_form";
        } catch (NoSuchElementException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "error_page"; // Redirect to the error page to display the error message
        }
    }

    @PostMapping("/create")
    public String addNewLitter(@ModelAttribute("litter") LitterCreateOrModifyModel litterModel,
                               @ModelAttribute("address") Address address,
                               @RequestParam("file") MultipartFile file,Model model) {
        try {
            litterService.addNewLitter(litterModel, address, file);
            return "redirect:/thy/litter";
        } catch (NoSuchElementException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "error_page"; // Redirect to the error page to display the error message
        }
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable UUID id, Model model) {
        try {
            LitterCreateOrModifyModel currentLitter = Mapper.mapModelToLitterCreateOrModifyModel(litterService.getLitterById(id));
            model.addAttribute("litter", currentLitter);
            return "litter_edit_form";
        } catch (NoSuchElementException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "error_page"; // Redirect to the error page to display the error message
        }
    }

    @PostMapping("/edit/{id}")
    public String updateLitter(@PathVariable UUID id, @ModelAttribute("report") LitterCreateOrModifyModel litterModel, Model model) {
        try {
            litterService.updateExistingLitter(id, litterModel);
            return "redirect:/reports"; // Redirect to the URL for displaying all reports after successful update
        } catch (NoSuchElementException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "error_page"; // Redirect to the error page to display the error message
        }
    }


    @GetMapping("/delete/{id}")
    public String deleteLitter(@PathVariable UUID id,Model model) {

        try {
            litterService.deleteLitter(id);
            return "redirect:/reports"; // Redirect to the URL for displaying all reports after successful deletion
        } catch (NoSuchElementException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "error_page"; // Redirect to the error page to display the error message
        }

    }



}
