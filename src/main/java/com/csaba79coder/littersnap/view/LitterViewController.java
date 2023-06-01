package com.csaba79coder.littersnap.view;

import com.csaba79coder.littersnap.model.address.entity.Address;
import com.csaba79coder.littersnap.model.litter.dto.LitterCreateOrModifyModel;
import com.csaba79coder.littersnap.model.litter.dto.LitterModel;
import com.csaba79coder.littersnap.model.litter.service.LitterService;
import com.csaba79coder.littersnap.util.Mapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.Base64;
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
            model.addAttribute("view", "litter_list");
            return "welcome"; // Replace with the actual view name for displaying the list of litters
        } catch (NoSuchElementException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "error_page"; // Redirect to the error page to display the error message
        }
    }

    @GetMapping("/{id}")
    public String getLitterById(@PathVariable("id") UUID id, Model model) {
        try {
            LitterModel litter = litterService.getLitterById(id);
            model.addAttribute("id", litter.getId());
            model.addAttribute("createdAt", litter.getCreatedAt());
            model.addAttribute("updatedAt", litter.getUpdatedAt());
            model.addAttribute("firstline", litter.getAddress().getFirstLine());
            model.addAttribute("city", litter.getAddress().getCity());
            model.addAttribute("country", litter.getAddress().getCountry());
            model.addAttribute("postcode", litter.getAddress().getPostCode());
            model.addAttribute("description", litter.getDescription());

            // Convert the byte array to a base64 encoded string
            String base64Image = Base64.getEncoder().encodeToString(litter.getImage());
            model.addAttribute("image", base64Image);

            model.addAttribute("status", litter.getStatus());
            model.addAttribute("view", "litter_details");
            return "welcome";
        } catch (NoSuchElementException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "error_page"; // Redirect to the error page to display the error message
        }
    }

    @GetMapping("/create")
    public String showAddLitterForm(Model model) {
        try {
            LitterCreateOrModifyModel litterModel = new LitterCreateOrModifyModel();

            // Set any other necessary properties in the litterModel object

            model.addAttribute("litter", litterModel);
            return "litter_add_form";
        } catch (NoSuchElementException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "error_page"; // Redirect to the error page to display the error message
        }
    }

    @PostMapping("/create")
    public String addNewLitter(@ModelAttribute("litter") LitterCreateOrModifyModel litterModel,
                               @ModelAttribute("address") Address address,
                               @RequestParam("file") MultipartFile file,
                               Model model) {
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
            LitterCreateOrModifyModel litter = Mapper.mapModelToLitterCreateOrModifyModel(litterService.getLitterById(id));
            model.addAttribute("id", litter.getId());
            model.addAttribute("firstline", litter.getAddress().getFirstLine());
            model.addAttribute("city", litter.getAddress().getCity());
            model.addAttribute("country", litter.getAddress().getCountry());
            model.addAttribute("postcode", litter.getAddress().getPostCode());
            model.addAttribute("description", litter.getDescription());
            model.addAttribute("image", litter.getImage());
            model.addAttribute("view","litter_edit_form");
            return "welcome";
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
            return "redirect:/thy/litter"; // Redirect to the URL for displaying all reports after successful deletion
        } catch (NoSuchElementException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "error_page"; // Redirect to the error page to display the error message
        }

    }



}
