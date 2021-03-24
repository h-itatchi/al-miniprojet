package com.example.uiservice.Controllers;

import com.example.uiservice.DATA.Entities.Teacher;
import com.example.uiservice.DATA.Repositories.Implimentations.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

@Controller
@CrossOrigin
public class WebController {

    @Autowired
    public TeacherRepository repository;

    @GetMapping("/homepage")
    public String showHome(WebRequest request, Model model) {
        //String email = request.getUserPrincipal().getName();
        String email = "hamza.benbelkacem@univ-constantine2.dz";
        Teacher teacher = repository.findByEmail(email);
        model.addAttribute("teacher", teacher);
        return "/homepage";
    }

    @GetMapping("/teacher/homepage")
    public String showTeacherHome(WebRequest request, Model model) {
        //String email = request.getUserPrincipal().getName();
        String email = "hamza.benbelkacem@univ-constantine2.dz";
        Teacher teacher = repository.findByEmail(email);
        model.addAttribute("teacher", teacher);
        return "/homepage";
    }
}
