package com.example.courseservice.DATA.Repositories;

import com.example.courseservice.DATA.Entities.Course;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "course", path = "course")
public interface CourseRepo extends PagingAndSortingRepository<Course, Long> {
    // add more functions
}
