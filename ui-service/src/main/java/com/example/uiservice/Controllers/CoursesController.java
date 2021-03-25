package com.example.uiservice.Controllers;

import com.example.uiservice.DATA.Entities.Teacher;
import com.example.uiservice.DATA.Entities.Work;
import com.example.uiservice.DATA.Repositories.Implimentations.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.util.ArrayList;

@Controller
public class CoursesController {

    @Autowired
    public CourseRepository repository;
    private final ArrayList<Work> works;

    public CoursesController() {
        works = new ArrayList<>(getWorks());
    }

    @GetMapping(value = {"/course/works/add-work/"})
    public String showWorkForm(Model model) {
        Work w;
        w = new Work();
        w.setTitle("VUE JS");
        w.setDescription("AL");
        w.setDate(LocalDate.now());
        model.addAttribute("work", w);
        return "/addwork";
    }

    @PostMapping("/course/works/add-work/")
    public ModelAndView addUpdateWork(@ModelAttribute("work") final Work work) {
        System.out.println(work.getTitle() + " " + work.getType());
        Teacher teacher = new Teacher();
        ModelAndView mav = new ModelAndView("/homepage", "teacher", teacher);
        return mav;
    }

    @GetMapping("/course/works")
    public String showWorks(WebRequest request, Model model) {
        model.addAttribute("works", works);
        model.addAttribute("name", "AL");
        model.addAttribute("usertype", "teacher");
        return "/CoursePage";
    }

    @GetMapping("/course/works/delete/{id}")
    public String deleteWork(@PathVariable String id) {
        works.removeIf(work -> Long.parseLong(id) == work.getId());
        return "redirect:/course/works";
    }

    @GetMapping("/course/works/update/{id}")
    public String updateWork(@PathVariable String id, Model model) {
        Work work = works.stream().filter(w -> Long.parseLong(id) == w.getId()).findFirst().get();
        model.addAttribute("work", work);
        return "/addwork";
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

}
