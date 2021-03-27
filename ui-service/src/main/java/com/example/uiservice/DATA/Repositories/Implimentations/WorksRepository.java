package com.example.uiservice.DATA.Repositories.Implimentations;

import com.example.uiservice.DATA.Entities.Course;
import com.example.uiservice.DATA.Entities.Teacher;
import com.example.uiservice.DATA.Entities.Work;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class WorksRepository {

    @Autowired
    public CourseRepository repository;
    @Autowired
    private DiscoveryClient discoveryClient;

    private final RestTemplate restTemplate;
    private final String worksService;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public WorksRepository() {
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
        worksService = baseUrl + "/course-service";
    }

    public Work save(Work entity) {
        String id = entity.getCourse();
        entity.setCourse(worksService + "/course/" + id);
        HttpEntity<Work> request = new HttpEntity<>(entity);
        System.out.println(request.toString());
        Work work = restTemplate.postForObject(worksService + "/work", request, Work.class);
        entity.setCourse(id);
        return work;
    }

    public Work findById(long aLong) {
        return restTemplate.getForObject(worksService + "/work/" + aLong, Work.class);
    }

    public ArrayList<Work> findAll() {
        JsonNode json = restTemplate.getForObject(worksService + "/work/", JsonNode.class);
        System.out.println(json.toString());
        Work[] works = null;
        try {
            works = objectMapper.readValue(json.get("_embedded").get("work").toString(), Work[].class);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        if (works == null) {
            works = new Work[]{};
        }
        return new ArrayList<>(Arrays.asList(works));
    }

    public void deleteById(long aLong) {
        restTemplate.delete(worksService + "/work/" + aLong);
    }

    public boolean validate(Course course) {
        return true;
    }

}
