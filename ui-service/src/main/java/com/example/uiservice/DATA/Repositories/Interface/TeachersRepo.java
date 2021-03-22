package com.example.uiservice.DATA.Repositories.Interface;

import com.example.uiservice.DATA.Entities.Teacher;
import org.springframework.data.repository.PagingAndSortingRepository;


public interface TeachersRepo extends PagingAndSortingRepository<Teacher, Long> {
    // add more functions
}
