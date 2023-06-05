package com.csaba79coder.littersnap.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping({"/","","index","index.html"})
    public String renderIndexPage() {
        return "index";
    }
}
