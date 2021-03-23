package com.example.uiservice.Controllers;

import com.example.uiservice.DATA.Entities.Teacher;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.context.request.WebRequest;

@Controller
public class WebController {

    @GetMapping("/homepage")
    public String showLoginForm(WebRequest request, Model model) {
        final Teacher teacher = new Teacher();
        model.addAttribute("teacher", teacher);
        return "/homepage";
    }
}
