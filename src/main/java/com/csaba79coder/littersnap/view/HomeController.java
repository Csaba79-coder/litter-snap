package com.csaba79coder.littersnap.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controller for the index page
 */
@Controller
public class HomeController {

    /**
     * Renders the index page
     * @return the index page
     */
    @GetMapping({"/","","index","index.html"})
    public String renderIndexPage() {
        return "index";
    }
}
