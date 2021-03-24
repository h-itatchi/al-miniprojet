package com.example.uiservice.DATA.Repositories.Implimentations;

import com.example.uiservice.DATA.Entities.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class TeacherRepository {

    @Autowired
    public PasswordEncoder encoder;
    private final RestTemplate restTemplate;
    private final String teachersService="http://localhost:9090";

    public TeacherRepository() {
        restTemplate = new RestTemplate();
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
        return restTemplate.postForObject(teachersService+"/teacher", request, Teacher.class);
    }


    public <S extends Teacher> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }


    public Teacher findById(Long aLong) {
        return restTemplate.getForObject(teachersService+"/teacher" + "/" + aLong, Teacher.class);
    }


    public boolean existsById(Long aLong) {
        return false;
    }


    public Iterable<Teacher> findAll() {
        return null;
    }


    public Iterable<Teacher> findAllById(Iterable<Long> longs) {
        return null;
    }


    public long count() {
        return 0;
    }


    public void deleteById(Long aLong) {
        restTemplate.delete(teachersService+"/teacher/"+aLong);
    }


    public void delete(Teacher entity) {

    }


    public void deleteAll(Iterable<? extends Teacher> entities) {

    }


    public void deleteAll() {

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

        return restTemplate.getForObject(teachersService+"/teacher/search/findByEmail?email=" + email, Teacher.class);
    }
}
