package com.csaba79coder.littersnap.view;

import com.csaba79coder.littersnap.model.user.dto.UserRegistrationModel;
import com.csaba79coder.littersnap.model.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/thy/security")
public class RegistrationViewController {

    private final UserService userService;

    @GetMapping("/registration")
    public String showRegistrationForm(Model model) {
        UserRegistrationModel tempModel = new UserRegistrationModel();
        model.addAttribute("userRegistrationModel", tempModel );
        model.addAttribute("view","signUp_form");
        return "index";
    }

    @PostMapping("/registration")
    public String registerUser(@ModelAttribute("userRegistrationModel") UserRegistrationModel registrationModel,
                               BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "signUp_form";
        }

        // Create a User object from the registration model and save it using the UserService
        userService.addNewUser(registrationModel);

        return "redirect:/index"; // Redirect to the index page after successful registration
    }
}


