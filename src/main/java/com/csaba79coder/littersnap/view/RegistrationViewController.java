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

/**
 * Controller for the registration page.
 * <p>
 * The controller is responsible for handling the GET and POST requests for the registration page.
 */
@Controller
@RequiredArgsConstructor
@RequestMapping("/thy/security")
public class RegistrationViewController {

    /**
     * The UserService is used to save the new user to the database.
     */
    private final UserService userService;

    /**
     * Show the registration form.
     * @param model
     * @return
     */
    @GetMapping("/registration")
    public String showRegistrationForm(Model model) {
        model.addAttribute("userRegistrationModel", new UserRegistrationModel());
        return "registration";
    }

    /**
     * Handle the registration form submission.
     * @param registrationModel
     * @param bindingResult
     * @return
     */
    @PostMapping("/registration")
    public String registerUser(@ModelAttribute("userRegistrationModel") UserRegistrationModel registrationModel,
                               BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "registration";
        }
        // Create a User object from the registration model and save it using the UserService
        userService.addNewUser(registrationModel);
        return "redirect:/index"; // Redirect to the index page after successful registration
    }
}
