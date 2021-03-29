package com.example.uiservice.Controllers.API;

import com.example.uiservice.DATA.Entities.Work;
import com.example.uiservice.DATA.Repositories.Implimentations.CourseRepository;
import com.example.uiservice.DATA.Repositories.Implimentations.TeacherRepository;
import com.example.uiservice.DATA.Repositories.Implimentations.WorksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@CrossOrigin
public class WorkAPI {

    @Autowired
    public CourseRepository courseRepo;
    @Autowired
    public TeacherRepository teachersRepo;
    @Autowired
    public WorksRepository worksRepo;

    @PostMapping(value = "/api/work/save")
    public ResponseEntity<Work> save(@RequestBody Work work) {
        return ResponseEntity.ok(worksRepo.save(work));
    }

    @GetMapping(value = "/api/work/{id}")
    public ResponseEntity<ArrayList<Work>> getAll(@PathVariable String id) {
        final ArrayList<Work> courses = new ArrayList<>(worksRepo.findByCourses(id));
        return ResponseEntity.ok(courses);
    }
}
