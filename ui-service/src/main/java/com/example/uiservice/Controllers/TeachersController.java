package com.example.uiservice.Controllers;

import com.example.uiservice.DATA.Entities.Course;
import com.example.uiservice.DATA.Entities.Teacher;
import com.example.uiservice.DATA.Repositories.Implimentations.CourseRepository;
import com.example.uiservice.DATA.Repositories.Implimentations.TeacherRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.ArrayList;

@Controller
@CrossOrigin
public class TeachersController {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());
    @Autowired
    public TeacherRepository teachersRepo;
    @Autowired
    public CourseRepository courseRepo;

    @GetMapping("/teachers/registration")
    public String showRegistrationForm(WebRequest request, Model model) {
        Teacher currentTeacher = new Teacher();
        currentTeacher.setFirstName("Hamza");
        currentTeacher.setLastName("Benbelkacem");
        currentTeacher.setEmail("itatchi.hamza@gmail.com");
        currentTeacher.setPassword("123456");
        model.addAttribute("teacher", currentTeacher);
        return "SignUp";
    }

    @GetMapping("/teacher/homepage")
    public String showHomePage(Model model, WebRequest request) {
        Principal p = request.getUserPrincipal();
        if (request.getUserPrincipal() != null && !p.getName().isEmpty()) {
            final Teacher teacher = teachersRepo.getFromLoggedIn(p.getName());
            model.addAttribute("teacher", teacher);
            //model.addAttribute("courses", teacher.getCourseList());
            model.addAttribute("courses", courseRepo.findAll());
            model.addAttribute("usertype", "auth");
            model.addAttribute("username", (teachersRepo.getTeacherFullName(teacher)));
        }
        return "homepage";
    }

    @PostMapping("/teachers/registration")
    public ModelAndView registerUserAccount(@ModelAttribute("teacher") final Teacher teacher) {
        LOGGER.debug("Registering user account with information: {}", teacher);
        ModelAndView mav;
        String errMessage = "";
        Teacher registered = null;
        // validate user
        if (!teachersRepo.valid(teacher)) {
            errMessage = "teacher info invalid";
        } else {
            // save teacher
            teacher.getRoles().add("TEACHER");
            registered = teachersRepo.save(teacher);
            // check if the user exists
            if (registered == null) {
                errMessage = "User already exists";
            }
        }
        if (!errMessage.isEmpty()) {
            mav = new ModelAndView("SignUp", "teacher", teacher);
            mav.addObject("message", errMessage);
            return mav;
        }
        mav = new ModelAndView("redirect:/teacher/homepage", "teacher", registered);
        mav.addObject("usertype", "auth");
        return mav;
    }
}
