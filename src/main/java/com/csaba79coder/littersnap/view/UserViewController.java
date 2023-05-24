package com.csaba79coder.littersnap.view;

import com.csaba79coder.littersnap.model.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class UserViewController {

    private final UserService userService;
}
