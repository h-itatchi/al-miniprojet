package com.example.uiservice.DATA.Repositories.Interface;

import com.example.uiservice.DATA.Entities.Teacher;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeachersRepo extends PagingAndSortingRepository<Teacher, Long> {
    // add more functions
    boolean valid(Teacher teacher);
}
