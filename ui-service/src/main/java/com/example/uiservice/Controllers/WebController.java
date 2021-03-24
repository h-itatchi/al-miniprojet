package com.example.uiservice.Controllers;

import com.example.uiservice.DATA.Entities.Teacher;
import com.example.uiservice.DATA.Repositories.Implimentations.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.context.request.WebRequest;

@Controller
@CrossOrigin
public class WebController {

    @Autowired
    public TeacherRepository repository;
    private final static String path = "/course/works";

    @GetMapping("/homepage")
    public String showHome(WebRequest request, Model model) {
        //String email = request.getUserPrincipal().getName();
        String email = "hamza.benbelkacem@univ-constantine2.dz";
        Teacher teacher = repository.findByEmail(email);
        model.addAttribute("teacher", teacher);
        return "/homepage";
    }

    @GetMapping("/")
    public String defaultPage() {
        return "redirect:" + path;
    }
}
