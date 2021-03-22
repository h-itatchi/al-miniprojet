package com.example.uiservice.DATA.Repositories.Implimentations;

import com.example.uiservice.DATA.Entities.Teacher;
import com.example.uiservice.DATA.Repositories.Interface.TeachersRepo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class TeacherRepository implements TeachersRepo {

    @Override
    public Iterable<Teacher> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<Teacher> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Teacher> S save(S entity) {
        return null;
    }

    @Override
    public <S extends Teacher> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<Teacher> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Iterable<Teacher> findAll() {
        return null;
    }

    @Override
    public Iterable<Teacher> findAllById(Iterable<Long> longs) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(Teacher entity) {

    }

    @Override
    public void deleteAll(Iterable<? extends Teacher> entities) {

    }

    @Override
    public void deleteAll() {

    }
}
