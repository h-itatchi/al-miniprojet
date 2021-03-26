package com.example.uiservice.DATA.Repositories.Implimentations;

import com.example.uiservice.DATA.Entities.Course;
import com.example.uiservice.DATA.Entities.Teacher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;

@Component
public class CourseRepository {

    private final RestTemplate restTemplate;
    private final String coursesService = "http://localhost:7070";

    public CourseRepository() {
        restTemplate = new RestTemplate();
    }

    public Iterable<Course> findAll(Sort sort) {
        return null;
    }

    public Page<Course> findAll(Pageable pageable) {
        return null;
    }

    public Course save(Course entity) {
        HttpEntity<Course> request = new HttpEntity<>(entity);
        return restTemplate.postForObject(coursesService + "/course", request, Course.class);
    }

    public <S extends Course> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    public Optional<Course> findById(Long aLong) {
        return Optional.empty();
    }

    public boolean existsById(Long aLong) {
        return false;
    }

    public ArrayList<Course> findAll() {
        ArrayList<Course> courses;
        courses = new ArrayList<Course>(Objects.requireNonNull(restTemplate.getForObject(coursesService + "/course/", ArrayList.class)));
        return courses;
    }

    public Iterable<Course> findAllById(Iterable<Long> longs) {
        return null;
    }

    public long count() {
        return 0;
    }

    public void deleteById(Long aLong) {

    }

    public void delete(Course entity) {

    }

    public void deleteAll(Iterable<? extends Course> entities) {

    }

    public void deleteAll() {

    }

    public ArrayList<Course> getTeacherCourses(long id) {
        ArrayList<Course> courses;
        courses = new ArrayList<Course>(Objects.requireNonNull(restTemplate.getForObject(coursesService + "/course/search/findAllByTeachersContains?id=" + id, ArrayList.class)));
        return courses;
    }
}
