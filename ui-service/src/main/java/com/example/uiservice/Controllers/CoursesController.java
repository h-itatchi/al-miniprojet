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

    @GetMapping(value = {"/course/works/add-work/{course}"})
    public String showWorkForm(Model model, @PathVariable String course) {
        model.addAttribute("work", works.get(0));
        model.addAttribute("course", course);
        return "/addwork";
    }

    @PostMapping("/course/works/add-work/")
    public String addUpdateWork(@ModelAttribute("work") final Work work,Model model) {
        Work work1 = works.stream().filter(w -> work.getId() == w.getId()).findFirst().get();
        work1.setType(work.getType());
        work1.setTitle(work.getTitle());
        work1.setDate(work.getDate());
        work1.setDescription(work.getDescription());
        work1.setLinkToPDF(work.getLinkToPDF());
        work1.setCourse(work.getCourse());
        return "redirect:/course/works/"+work.getCourse();
    }

    @GetMapping("/course/works/{id}")
    public String showWorks(WebRequest request, Model model, @PathVariable String id) {
        model.addAttribute("works", works);
        model.addAttribute("name", "AL");
        model.addAttribute("course", id);
        model.addAttribute("usertype", "teacher");
        return "/CoursePage";
    }

    @GetMapping("/course/works/delete/{course}/{id}")
    public String deleteWork(@PathVariable String id, @PathVariable String course) {
        works.removeIf(work -> Long.parseLong(id) == work.getId());
        return "redirect:/course/works/" + course;
    }

    @GetMapping("/course/works/update/{course}/{id}")
    public String updateWork(@PathVariable String id, Model model, @PathVariable String course) {
        Work work = works.stream().filter(w -> Long.parseLong(id) == w.getId()).findFirst().get();
        model.addAttribute("work", work);
        model.addAttribute("course", course);
        model.addAttribute("update", true);
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
