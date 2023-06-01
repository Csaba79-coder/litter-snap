package com.csaba79coder.littersnap.view;

import com.csaba79coder.littersnap.model.user.dto.UserModel;
import com.csaba79coder.littersnap.model.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.NoSuchElementException;

@Controller
@RequiredArgsConstructor
@RequestMapping("/thy/users")
public class UserViewController {

    private final UserService userService;

    @GetMapping
    public String getAllUsers(Model model) {
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