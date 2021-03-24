package com.example.uiservice.Controllers;

import com.example.uiservice.DATA.Entities.Teacher;
import com.example.uiservice.DATA.Repositories.Implimentations.TeacherRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());
    @Autowired
    public TeacherRepository teachersRepo;

    @GetMapping("/login")
    public String showLoginForm(WebRequest request, Model model) {
        return "/login";
    }
}
