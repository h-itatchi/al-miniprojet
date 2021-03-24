package com.example.uiservice.Controllers;

import com.example.uiservice.DATA.Entities.Teacher;
import com.example.uiservice.DATA.Entities.Work;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;

@Controller
public class CoursesController {

    @GetMapping("/test")
    public String showWorkForm(WebRequest request, Model model) {
        Work w = new Work();
        w.setTitle("VUE JS");
        w.setDescription("AL");
        w.setDate(LocalDate.now());
        model.addAttribute("work",w);
        return "/addwork";
        //return "/homepage";
    }

    @PostMapping("/test")
    public ModelAndView test(@ModelAttribute("work") final Work work, final HttpServletRequest request, final Errors errors) {
        System.out.println(work.getTitle()+" "+work.getType());
        Teacher teacher = new Teacher();
        ModelAndView mav = new ModelAndView("/homepage", "teacher", teacher);
        return mav;
    }
}
