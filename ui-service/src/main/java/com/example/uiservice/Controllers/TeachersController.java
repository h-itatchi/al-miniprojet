package com.example.uiservice.Controllers;

import com.example.uiservice.DATA.Entities.Teacher;
import com.example.uiservice.DATA.Repositories.Implimentations.TeacherRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@CrossOrigin
public class TeachersController {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());
    @Autowired
    public TeacherRepository teachersRepo;
    //private Teacher currentTeacher;

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
    public String showHomePage(WebRequest request, Model model) {
        final Teacher teacher = new Teacher();
        teacher.setFirstName("Hamza");
        teacher.setLastName("Benbelkacem");
        teacher.setEmail("itatchi.hamza@gmail.com");
        teacher.setPassword("123456");
        model.addAttribute("teacher", teacher);
        return "TeacherHomepage";
    }

    @PostMapping("/teachers/registration")
    public ModelAndView registerUserAccount(@ModelAttribute("teacher") final Teacher teacher, final HttpServletRequest request, final Errors errors) {
        LOGGER.debug("Registering user account with information: {}", teacher);
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
            ModelAndView mav = new ModelAndView("SignUp", "teacher", teacher);
            mav.addObject("message", errMessage);
            return mav;
        }
        return new ModelAndView("TeacherHomepage", "teacher", registered);
    }
}
