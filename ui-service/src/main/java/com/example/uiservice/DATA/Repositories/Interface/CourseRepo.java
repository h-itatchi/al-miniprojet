package com.example.uiservice.DATA.Repositories.Interface;


import com.example.uiservice.DATA.Entities.Course;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepo extends PagingAndSortingRepository<Course, Long> {
    // add more functions
}
