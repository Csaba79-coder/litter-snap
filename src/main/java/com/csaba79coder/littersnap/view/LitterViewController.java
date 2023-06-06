package com.csaba79coder.littersnap.view;

import com.csaba79coder.littersnap.model.address.entity.Address;
import com.csaba79coder.littersnap.model.litter.dto.LitterCreateOrModifyModel;
import com.csaba79coder.littersnap.model.litter.dto.LitterModel;
import com.csaba79coder.littersnap.model.litter.service.LitterService;
import com.csaba79coder.littersnap.util.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Base64;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

/**
 * This class contains the litter view controller.
 * Also include logs errors and exceptions.
 */
@Controller
@RequiredArgsConstructor
@RequestMapping("/thy/auth")
public class LitterViewController {

    /**
     * Dependency injection fields.
     * <p>
     *     litterService: the litter service
     * </p>
     */
    private final LitterService litterService;

    /**
     * This method renders the litter list.
     * @param model the model
     * @return the view name
     */
    @GetMapping("/admin/litters")
    public String renderAllLitters(Model model) {
        try {
            List<LitterModel> litters = litterService.findAllLitters();
            model.addAttribute("litters", litters);
            model.addAttribute("view", "litter_list");
            return "welcome"; // Replace with the actual view name for displaying the list of litters
        } catch (NoSuchElementException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "error_page"; // Redirect to the error page to display the error message
        }
    }

    /**
     * This method renders the litter details.
     * @param id the litter id
     * @param model the model
     * @return the view name
     */
    @GetMapping("/admin/litters/{id}")
    public String renderLitterById(@PathVariable("id") UUID id, Model model) {
        try {
            LitterModel litter = litterService.findLitterById(id);
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

    /**
     * This method creates a litter
     * @param capturedCity and add button renders a form
     * @return the view name
     */
    @GetMapping("/admin/litters/create")
    public String showAddLitterForm(Model model, @RequestParam(value = "city", required = false) String capturedCity) {
        try {
            LitterCreateOrModifyModel litterModel = new LitterCreateOrModifyModel();
            // Set any other necessary properties in the litterModel object

            model.addAttribute("city", capturedCity);
            model.addAttribute("litter", litterModel);
            model.addAttribute("view","litter_add_form");
            return "welcome";
        } catch (NoSuchElementException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "error_page"; // Redirect to the error page to display the error message
        }
    }

    /**
     * This method creates a litter
     * by the get method with the captured city parameter you can set the litterModel fields
     * @param  litterModel
     * @param  address
     * @param  file
     * @param  model
     * @return the view name
     */
    @PostMapping("/admin/litters/create")
    public String addNewLitter(@ModelAttribute("litter") LitterCreateOrModifyModel litterModel, @ModelAttribute("address") Address address, @RequestParam("file") MultipartFile file, Model model) {
        try {
            litterService.addNewLitter(litterModel, address, file);
            return "redirect:/thy/litter";
        } catch (NoSuchElementException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "error_page"; // Redirect to the error page to display the error message
        }
    }

    /**
     * This method renders the litter edit form.
     * @param id the litter id
     * @param model the model
     * @return the view name
     */
    @GetMapping("/admin/litters/edit/{id}")
    public String showEditForm(@PathVariable UUID id, Model model) {
        try {
            LitterCreateOrModifyModel litter = Mapper.mapModelToLitterCreateOrModifyModel(litterService.findLitterById(id));
            model.addAttribute("id", litter.getId());
            model.addAttribute("firstline", litter.getAddress().getFirstLine());
            model.addAttribute("city", litter.getAddress().getCity());
            model.addAttribute("country", litter.getAddress().getCountry());
            model.addAttribute("postcode", litter.getAddress().getPostCode());
            model.addAttribute("description", litter.getDescription());
            model.addAttribute("image", litter.getImage());
            model.addAttribute("view", "litter_edit_form");
            return "welcome";
        } catch (NoSuchElementException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "error_page"; // Redirect to the error page to display the error message
        }
    }

    /**
     * This method updates an existing litter.
     * @param id the litter id
     * @param litterModel the litter model
     * @param model the model
     * @return the view name
     */
    @PostMapping("/admin/litters/edit/{id}")
    public String modifyExistingLitter(@PathVariable UUID id, @ModelAttribute("report") LitterCreateOrModifyModel litterModel, Model model) {
        try {
            litterService.modifyAnExistingLitter(id, litterModel);
            return "redirect:/reports"; // Redirect to the URL for displaying all reports after successful update
        } catch (NoSuchElementException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "error_page"; // Redirect to the error page to display the error message
        }
    }

    /**
     * This method deletes an existing litter.
     * @param id the litter id
     * @param model the model
     * @return the view name
     */
    @GetMapping("/admin/litters/delete/{id}")
    public String deleteAnExistingLitter(@PathVariable UUID id, Model model) {
        try {
            litterService.deleteAnExistingLitter(id);
            return "redirect:/thy/litter"; // Redirect to the URL for displaying all reports after successful deletion
        } catch (NoSuchElementException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "error_page"; // Redirect to the error page to display the error message
        }
    }
}
