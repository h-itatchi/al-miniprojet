package com.example.uiservice.DATA.Repositories.Implimentations;

import com.example.uiservice.DATA.Entities.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class TeacherRepository {

    @Autowired
    public PasswordEncoder encoder;
    @Autowired
    public CourseRepository repository;
    @Autowired
    private DiscoveryClient discoveryClient;
    private final RestTemplate restTemplate;
    private final String teachersService;
    private static final ArrayList<Teacher> loggedin = new ArrayList<>();

    public TeacherRepository() {
        restTemplate = new RestTemplate();
        String baseUrl = "";
        if (discoveryClient != null) {
            List<ServiceInstance> instances = discoveryClient.getInstances("zuul-service");
            if (!instances.isEmpty()) {
                ServiceInstance serviceInstance = instances.get(0);
                baseUrl = serviceInstance.getUri().toString();
            }
        } else {
            System.out.println("instance not found");
            baseUrl = "http://localhost:8181";
        }
        teachersService = baseUrl + "/teachers-service";
    }

    public Iterable<Teacher> findAll(Sort sort) {
        return null;
    }

    public Page<Teacher> findAll(Pageable pageable) {
        return null;
    }

    public Teacher save(Teacher entity) {
        entity.setPassword(encoder.encode(entity.getPassword()));
        HttpEntity<Teacher> request = new HttpEntity<>(entity);
        return restTemplate.postForObject(teachersService + "/teacher", request, Teacher.class);
    }

    public Teacher findById(Long aLong) {
        return restTemplate.getForObject(teachersService + "/teacher" + "/" + aLong, Teacher.class);
    }

    public boolean existsById(Long aLong) {
        return false;
    }

    public ArrayList<Teacher> findAll() {
        return null;
    }

    public Iterable<Teacher> findAllById(Iterable<Long> longs) {
        return null;
    }

    public long count() {
        return 0;
    }

    public void deleteById(Long aLong) {
        restTemplate.delete(teachersService + "/teacher/" + aLong);
    }

    public void delete(Teacher entity) {

    }

    public boolean valid(Teacher t) {
        if (t.getFirstName().isEmpty()) {
            return false;
        }
        if (t.getLastName().isEmpty()) {
            return false;
        }
        if (t.getPassword().isEmpty()) {
            return false;
        }
        if (t.getEmail().isEmpty()) {
            return false;
        }
        return true;
    }

    public Teacher findByEmail(String email) {
        return restTemplate.getForObject(teachersService + "/teacher/search/findByEmail?email=" + email, Teacher.class);
    }

    private void setTeacherCourses(Teacher teacher) {
        teacher.setCourseList(repository.getTeacherCourses(teacher.getId()));
    }

    public String getTeacherFullName(String email) {
        Teacher teacher = loggedin.stream().filter(teacher1 -> teacher1.getEmail().equals(email)).findFirst().get();
        return (teacher.getLastName() + " " + teacher.getFirstName()).toUpperCase();
    }

    public String getTeacherFullName(Teacher teacher) {
        return (teacher.getLastName() + " " + teacher.getFirstName()).toUpperCase();
    }

    public void addToLoggedIn(Teacher teacher) {
        loggedin.add(teacher);
    }

    public void removeFromLoggedIn(String email) {
        loggedin.removeIf(teacher -> teacher.getEmail().equals(email));
    }

    public Teacher getFromLoggedIn(String email) {
        Optional<Teacher> t = loggedin.stream().filter(teacher1 -> teacher1.getEmail().equals(email)).findFirst();
        if (t.isPresent()) {
            return loggedin.stream().filter(teacher1 -> teacher1.getEmail().equals(email)).findFirst().get();
        }
        return null;
    }
}
