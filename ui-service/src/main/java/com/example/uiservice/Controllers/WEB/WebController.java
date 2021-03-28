package com.example.uiservice.Controllers.WEB;

import com.example.uiservice.DATA.Entities.Course;
import com.example.uiservice.DATA.Entities.Teacher;
import com.example.uiservice.DATA.Repositories.Implimentations.CourseRepository;
import com.example.uiservice.DATA.Repositories.Implimentations.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.context.request.WebRequest;

import java.security.Principal;

@Controller
@CrossOrigin
public class WebController {

    @Autowired
    public TeacherRepository teacherRepository;
    @Autowired
    public CourseRepository courseRepository;
    private final static String path = "/homepage";

    @GetMapping("/homepage")
    public String showHome(WebRequest request, Model model) {
        //model.addAttribute("courses", courseRepository.findAll());
        Principal p = request.getUserPrincipal();
        if (request.getUserPrincipal() != null && !p.getName().isEmpty()) {
            return "redirect:/teacher/homepage";
        }
        model.addAttribute("courses", courseRepository.findAll());
        model.addAttribute("usertype", "unauth");
        return "/homepage";
    }

    @GetMapping({"/", "/course"})
    public String defaultPage() {
        return "redirect:" + path;
    }


}
