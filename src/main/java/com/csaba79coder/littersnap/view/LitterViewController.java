package com.csaba79coder.littersnap.view;

import com.csaba79coder.littersnap.model.litter.dto.LitterCreateOrModifyModel;
import com.csaba79coder.littersnap.model.litter.dto.LitterModel;
import com.csaba79coder.littersnap.model.litter.service.LitterService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
        List<LitterModel> reports = litterService.getAllLitters();

        if (reports.isEmpty()) {
            return "error_page";
        } else {
            model.addAttribute("reports", reports);
            return "litter_list"; // Replace with the actual view name for displaying the list of reports
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
    public String addNewLitter(@ModelAttribute("litter") LitterCreateOrModifyModel litter) {
        litterService.addNewLitter(litter);
        return "redirect:/litters";
    }



}
