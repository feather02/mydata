package com.thymeleaf.thymeeleaf;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {
    @GetMapping(value = "/index")
    public String index() {
        return "index";
    }

    @GetMapping(value = "/page2")
    public String page2() {
        return "page2";
    }
}