package com.example.uiservice.Controllers;

import com.example.uiservice.DATA.Entities.Course;
import com.example.uiservice.DATA.Entities.Work;
import com.example.uiservice.DATA.Repositories.Implimentations.CourseRepository;
import com.example.uiservice.DATA.Repositories.Implimentations.TeacherRepository;
import com.example.uiservice.DATA.Repositories.Implimentations.WorksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;

import java.security.Principal;
import java.time.LocalDate;

@Controller
public class WorkController {

    @Autowired
    public CourseRepository courseRepo;
    @Autowired
    public TeacherRepository teachersRepo;
    @Autowired
    public WorksRepository worksRepo;

    @GetMapping(value = {"/course/works/add-work/{course}"})
    public String showWorkForm(Model model, @PathVariable String course, WebRequest request) {
        Work work = new Work();
        work.setCourse(course);
        work.setDate(LocalDate.now());
        model.addAttribute("work", work);
        model.addAttribute("course", course);
        setModelHeader(model, request.getUserPrincipal());
        return "/addwork";
    }

    @PostMapping("/course/works/add-work/")
    public String addUpdateWork(@ModelAttribute("work") final Work work, Model model, WebRequest request) {
        worksRepo.save(work);
        setModelHeader(model, request.getUserPrincipal());
        return "redirect:/course/works/" + work.getCourse();
    }



    @GetMapping("/course/works/delete/{course}/{id}")
    public String deleteWork(@PathVariable String id, @PathVariable String course) {
        worksRepo.deleteById(Long.parseLong(id));
        return "redirect:/course/works/" + course;
    }

    @GetMapping("/course/works/update/{course}/{id}")
    public String updateWork(@PathVariable String id, Model model, @PathVariable String course, WebRequest request) {
        model.addAttribute("work", worksRepo.findById(Long.parseLong(id)));
        model.addAttribute("course", course);
        model.addAttribute("update", true);
        setModelHeader(model, request.getUserPrincipal());
        return "/addwork";
    }

    private void setModelHeader(Model model, Principal p) {
        if (p != null && !p.getName().isEmpty()) {
            model.addAttribute("usertype", "auth");
            model.addAttribute("username", teachersRepo.getTeacherFullName(p.getName()));
        } else {
            model.addAttribute("usertype", "unauth");
        }
    }
}
