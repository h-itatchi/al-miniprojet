package com.example.uiservice.Controllers.WEB;

import com.example.uiservice.DATA.Repositories.Implimentations.TeacherRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.context.request.WebRequest;

import java.security.Principal;

@Controller
public class LoginController {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());
    @Autowired
    public TeacherRepository teachersRepo;

    @GetMapping("/login")
    public String showLoginForm(WebRequest request, Model model) {
        return "/login";
    }

    @GetMapping("/logout")
    public String logout(WebRequest request, Model model) {
        Principal p = request.getUserPrincipal();
        if (p != null) {
            teachersRepo.removeFromLoggedIn(p.getName());
        }
        return "redirect:/perform_logout";
    }
}
