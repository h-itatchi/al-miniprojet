package com.example.uiservice.Controllers.WEB;

import com.example.uiservice.DATA.Entities.Course;
import com.example.uiservice.DATA.Entities.Teacher;
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
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.ArrayList;

@Controller
public class CoursesController {

    @Autowired
    public CourseRepository courseRepo;
    @Autowired
    public TeacherRepository teachersRepo;
    @Autowired
    public WorksRepository worksRepo;

    public CoursesController() {
    }

    @GetMapping("/course/add-course")
    public String addCourse(Model model, WebRequest request) {
        Course c = new Course();
        model.addAttribute("course", c);
        setModelHeader(model, request.getUserPrincipal());
        return "/addCourse";
    }

    @PostMapping("/course/add-course")
    public ModelAndView addUpdateCourse(@ModelAttribute("course") final Course course) {
        ModelAndView model;
        if (courseRepo.validate(course)) {
            courseRepo.save(course);
            model = new ModelAndView("homepage");
        } else {
            model = new ModelAndView("/addCourse");
            model.addObject("course", course);
            model.addObject("update", true);
            model.addObject("errorMsg", "Invalid input");
        }
        return model;
    }

    @GetMapping("/course/update/{id}")
    public String updateCourse(Model model, @PathVariable String id, WebRequest request) {
        Course course = courseRepo.findById(Long.parseLong(id));
        model.addAttribute("course", course);
        model.addAttribute("update", true);
        setModelHeader(model, request.getUserPrincipal());
        return "/addCourse";
    }

    @GetMapping("/course/delete/{id}")
    public String deleteCourse(Model model, @PathVariable String id, WebRequest request) {
        courseRepo.deleteById(Long.parseLong(id));
        setModelHeader(model, request.getUserPrincipal());
        return "redirect:/course";
    }

    @GetMapping("/course/works/{id}")
    public String showWorks(WebRequest request, Model model, @PathVariable String id) {
        Course course = courseRepo.findById(Long.parseLong(id));
        course.teachersToString(teachersRepo.findAllById(course.getTeachers()));
        model.addAttribute("works", worksRepo.findByCourses(id));
        model.addAttribute("name", "AL");
        model.addAttribute("course", id);
        model.addAttribute("c", course);
        setModelHeader(model, request.getUserPrincipal());
        return "/CoursePage";
    }

    @GetMapping({"/course/set-teacher/{id}", "/t/{id}"})
    public String showSetTeachers(WebRequest request, Model model, @PathVariable String id) {
        setModelHeader(model, request.getUserPrincipal());
        Course course = courseRepo.findById(Long.parseLong(id));
        model.addAttribute("course", course);
        model.addAttribute("teachers", getUnsignedTeachers(course.getTeachers()));
        return "/setTeachersToCourses";
    }

    @GetMapping({"/course/{id}/{teacher}", "/t/{id}/{teacher}"})
    public String setTeachers(WebRequest request, Model model, @PathVariable String id, @PathVariable String teacher) {
        Course course = courseRepo.findById(Long.parseLong(id));
        course.getTeachers().add(Integer.parseInt(teacher));
        courseRepo.update(course);
        System.out.println(course.getTeachers().size());
        return "redirect:/t/"+id;
    }

    private void setModelHeader(Model model, Principal p) {
        if (p != null && !p.getName().isEmpty()) {
            model.addAttribute("usertype", "auth");
            model.addAttribute("username", teachersRepo.getTeacherFullName(p.getName()));
        } else {
            model.addAttribute("usertype", "unauth");
        }
    }

    private ArrayList<Teacher> getUnsignedTeachers(final ArrayList<Integer> t) {
        final ArrayList<Teacher> temp = new ArrayList<>(teachersRepo.findAll());
        for (Integer teacher : t) {
            temp.removeIf(teacher1 -> ((long) teacher == teacher1.getId()));
        }
        return temp;
    }

}
