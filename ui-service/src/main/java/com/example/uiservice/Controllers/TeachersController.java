package com.example.uiservice.Controllers;

import com.example.uiservice.DATA.Entities.Teacher;
import com.example.uiservice.DATA.Repositories.Interface.TeachersRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
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
public class TeachersController {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());
    @Autowired
    public TeachersRepo teachersRepo;

    @GetMapping("/teachers/registration")
    public String showRegistrationForm(WebRequest request, Model model) {
        final Teacher teacher = new Teacher();
        model.addAttribute("teacher", teacher);
        return "Teachers/Users/SignUp";
    }

    @PostMapping("/user/registration")
    public ModelAndView registerUserAccount(@ModelAttribute("teacher") final Teacher teacher, final HttpServletRequest request, final Errors errors) {
        LOGGER.debug("Registering user account with information: {}", teacher);
        String errMessage = "";
        // validate user
        if (teachersRepo.valid(teacher)) {
            errMessage = "teacher info invalid";
        }
        // save teacher
        Teacher registered = teachersRepo.save(teacher);
        // check if the user exists
        if (registered.getId() != 0) {

            errMessage = "User already exists";
        }

        if (errMessage.isEmpty()) {
            ModelAndView mav = new ModelAndView("Teachers/Users/SignUp", "user", teacher);
            mav.addObject("message", errMessage);
            return mav;
        }
        return new ModelAndView("courses/CoursesTable", "teacher", registered);
    }

}
