package com.example.uiservice.Controllers;

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
import java.util.ArrayList;

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
        model.addAttribute("courses", setMockData());
        model.addAttribute("usertype", "student");
        return "/homepage";
    }

    @GetMapping("/")
    public String defaultPage() {
        return "redirect:" + path;
    }

    private ArrayList<Course> setMockData() {
        ArrayList<Course> courses = new ArrayList<>();
        Course c;
        for (long i = 0; i < 10; i++) {
            c = new Course();
            c.setId(i);
            c.setName("AL");
            c.setDescription("AL M2GL");
            c.setCoefficient(4);
            c.setCredits(5);
            c.setTeachersToString("Teacher 1, Teacher 2, Teacher 3");
            courses.add(c);
        }
        return courses;
    }
}
