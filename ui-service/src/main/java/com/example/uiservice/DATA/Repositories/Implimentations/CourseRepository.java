package com.example.uiservice.DATA.Repositories.Implimentations;

import com.example.uiservice.DATA.Entities.Course;
import com.example.uiservice.DATA.Entities.Teacher;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Component
public class CourseRepository {

    private final RestTemplate restTemplate;
    private final String coursesService;
    private final ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
    private DiscoveryClient discoveryClient;


    public CourseRepository() {
        restTemplate = new RestTemplate();
        String baseUrl = "";
        if (discoveryClient != null) {
            List<ServiceInstance> instances = discoveryClient.getInstances("zuul-service");
            if (!instances.isEmpty()) {
                ServiceInstance serviceInstance = instances.get(0);
                baseUrl = serviceInstance.getUri().toString();
                System.out.println("Connecting using proxy instance from eureka server");
            }
        } else {
            System.out.println("Connecting using only proxy");
            baseUrl = "http://localhost:8181";
        }
        coursesService = baseUrl + "/course-service";

    }

    public Course save(Course entity) {
        HttpEntity<Course> request = new HttpEntity<>(entity);
        return restTemplate.postForObject(coursesService + "/course", request, Course.class);
    }

    public Course update(Course entity) {
        HttpEntity<Course> request = new HttpEntity<>(entity);
        return restTemplate.postForObject(coursesService + "/course", request, Course.class);
    }

    public Course findById(long aLong) {
        return restTemplate.getForObject(coursesService + "/course/" + aLong, Course.class);
    }

    public ArrayList<Course> findAll() {
        JsonNode json = restTemplate.getForObject(coursesService + "/course/", JsonNode.class);
        System.out.println(json.toString());
        Course[] courses = null;
        try {
            courses = objectMapper.readValue(json.get("_embedded").get("course").toString(), Course[].class);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        if (courses == null) {
            courses = new Course[]{};
        }
        return new ArrayList<>(Arrays.asList(courses));
    }

    public void deleteById(long aLong) {
        restTemplate.delete(coursesService + "/course/" + aLong);
    }

    public ArrayList<Course> getTeacherCourses(long id) {
        JsonNode json = restTemplate.getForObject(coursesService + "/course/findbyteacher/" + id, JsonNode.class);
        System.out.println(json.toString());
        Course[] courses = null;
        try {
            courses = objectMapper.readValue(json.toString(), Course[].class);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        if (courses == null) {
            courses = new Course[]{};
        }
        return new ArrayList<>(Arrays.asList(courses));
    }

    public boolean validate(Course course) {
        return true;
    }
}
