package com.example.uiservice.Controllers.API;

import com.example.uiservice.DATA.Entities.Course;
import com.example.uiservice.DATA.Entities.DTO.ResponseMessage;
import com.example.uiservice.DATA.Repositories.Implimentations.CourseRepository;
import com.example.uiservice.DATA.Repositories.Implimentations.TeacherRepository;
import com.example.uiservice.DATA.Repositories.Implimentations.WorksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@CrossOrigin
public class CourseAPI {

    @Autowired
    public CourseRepository courseRepo;
    @Autowired
    public TeacherRepository teachersRepo;
    @Autowired
    public WorksRepository worksRepo;

    @PostMapping(value = "/api/course/exist")
    public ResponseMessage checkExist(@RequestBody Course course) {
        ResponseMessage r = new ResponseMessage();
        r.setResult("exists");
        if (courseRepo.existByName(course.getName())) {
            r.setReason("Course already exists");
            return r;
        }
        r.setResult("true");
        return r;
    }

    @PostMapping(value = "/api/course/save")
    public ResponseEntity<Course> save(@RequestBody Course course) {
        return ResponseEntity.ok(courseRepo.save(course));
    }

    @GetMapping(value = "/api/course/{id}")
    public ResponseEntity<ArrayList<Course>> getAll(@PathVariable String id) {
        final ArrayList<Course> courses;
        if (id.equals("0")) {
            courses = new ArrayList<>(courseRepo.findAll());
        } else {
            courses = new ArrayList<>(courseRepo.getTeacherCourses(Long.parseLong(id)));
        }
        courses.forEach(course -> {
            course.teachersToString(teachersRepo.findAllById(course.getTeachers()));
            System.out.println(course.getTeachersText());
        });
        return ResponseEntity.ok(courses);
    }

}
