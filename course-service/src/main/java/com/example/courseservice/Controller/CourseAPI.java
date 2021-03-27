package com.example.courseservice.Controller;

import com.example.courseservice.DATA.Entities.CourseDTO;
import com.example.courseservice.DATA.Repositories.CourseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class CourseAPI {
    @Autowired
    public CourseRepo repo;

    @GetMapping("/course/findbyteacher/{id}")
    public ArrayList<CourseDTO> getByTeacher(@PathVariable String id) {
        final ArrayList<CourseDTO> courses = new ArrayList<>();
        repo.findAll().forEach(course -> {
            if (course.getTeachers().contains(Integer.parseInt(id))) {
                courses.add(new CourseDTO(course));
            }
        });
        return courses;
    }
}
