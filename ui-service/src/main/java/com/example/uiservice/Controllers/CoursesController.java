package com.example.uiservice.Controllers;

import com.example.uiservice.DATA.Entities.Course;
import com.example.uiservice.DATA.Entities.Work;
import com.example.uiservice.DATA.Repositories.Implimentations.CourseRepository;
import com.example.uiservice.DATA.Repositories.Implimentations.TeacherRepository;
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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

@Controller
public class CoursesController {

    @Autowired
    public CourseRepository repository;
    @Autowired
    public TeacherRepository teachersRepo;
    private final ArrayList<Work> works;

    public CoursesController() {
        works = new ArrayList<>(getWorks());
    }

    @GetMapping(value = {"/course/works/add-work/{course}"})
    public String showWorkForm(Model model, @PathVariable String course, WebRequest request) {
        model.addAttribute("work", works.get(0));
        model.addAttribute("course", course);
        setModelHeader(model, request.getUserPrincipal());
        return "/addwork";
    }

    @PostMapping("/course/works/add-work/")
    public String addUpdateWork(@ModelAttribute("work") final Work work, Model model, WebRequest request) {
        Optional<Work> t = works.stream().filter(w -> work.getId() == w.getId()).findFirst();
        if (t.isPresent()) {
            Work work1 = t.get();
            work1.setType(work.getType());
            work1.setTitle(work.getTitle());
            work1.setDate(work.getDate());
            work1.setDescription(work.getDescription());
            work1.setLinkToPDF(work.getLinkToPDF());
            work1.setCourse(work.getCourse());
        } else {
            work.setId(works.size());
            works.add(work);
        }
        setModelHeader(model, request.getUserPrincipal());
        return "redirect:/course/works/" + work.getCourse();
    }

    @GetMapping("/course/works/{id}")
    public String showWorks(WebRequest request, Model model, @PathVariable String id) {
        model.addAttribute("works", works);
        model.addAttribute("name", "AL");
        model.addAttribute("course", id);
        setModelHeader(model, request.getUserPrincipal());
        return "/CoursePage";
    }

    @GetMapping("/course/works/delete/{course}/{id}")
    public String deleteWork(@PathVariable String id, @PathVariable String course) {
        works.removeIf(work -> Long.parseLong(id) == work.getId());
        return "redirect:/course/works/" + course;
    }

    @GetMapping("/course/works/update/{course}/{id}")
    public String updateWork(@PathVariable String id, Model model, @PathVariable String course,WebRequest request) {
        Work work = works.stream().filter(w -> Long.parseLong(id) == w.getId()).findFirst().get();
        model.addAttribute("work", work);
        model.addAttribute("course", course);
        model.addAttribute("update", true);
        setModelHeader(model, request.getUserPrincipal());
        return "/addwork";
    }

    @GetMapping("/course/add-course")
    public String addCourse(Model model) {
        Course c = new Course();
        model.addAttribute("course", c);
        return "/addCourse";
    }

    @PostMapping("/course/add-course")
    public ModelAndView addUpdateCourse(@ModelAttribute("course") final Course course) {
        ModelAndView model;
        if (repository.validate(course)) {
            repository.save(course);
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
    public String updateCourse(Model model, @PathVariable String id) {
        Course course = repository.findById(Long.parseLong(id));
        model.addAttribute("course", course);
        model.addAttribute("update", true);
        return "/addCourse";
    }

    @GetMapping("/course/delete/{id}")
    public String deleteCourse(Model model, @PathVariable String id) {
        repository.deleteById(Long.parseLong(id));
        return "redirect:/course";
    }


    private ArrayList<Work> getWorks() {
        ArrayList<Work> temp = new ArrayList<>();
        Work w;
        for (int i = 0; i < 10; i++) {
            w = new Work();
            w.setId(i);
            w.setTitle("Patron abstract factory");
            w.setDescription("suivre l'exemple du patron abstract factory du document pdf " +
                    "en le créant et l'implémentant en java avec  visual paradigme et l'exécuter par la suite sur eclipse ou autre IDE java.\n" +
                    "\n" +
                    "Il est recommandé de faire aussi les patrons  singleton et prototype");
            w.setType("TP");
            w.setLinkToPDF("https://elearning.univ-constantine2.dz/elearning/course/view.php?id=1149");
            w.setDate(LocalDate.now());
            temp.add(w);
        }
        return temp;
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
