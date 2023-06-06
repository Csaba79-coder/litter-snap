package com.csaba79coder.littersnap.view;

import com.csaba79coder.littersnap.model.user.dto.UserModel;
import com.csaba79coder.littersnap.model.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * Controller for the user pages.
 * <p>
 * The controller is responsible for handling the GET and POST requests for the user pages.
 */
@Controller
@RequiredArgsConstructor
@RequestMapping("/thy/auth")
public class UserViewController {

    /**
     * Dependency injection for the UserService.
     * The UserService is used to save the new user to the database.
     */
    private final UserService userService;

    /**
     * Show the user form.
     * Render all users
     * @param model
     * @return
     */
    @GetMapping("/admin/users")
    public String renderAllUsers(Model model) {
        try {
            List<UserModel> users = userService.findAllUsers();
            model.addAttribute("users", users);
            model.addAttribute("view", "user_list");
            return "welcome"; // Replace with the actual view name for displaying the list of litters
        } catch (NoSuchElementException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "error_page"; // Redirect to the error page to display the error message
        }
    }
}
