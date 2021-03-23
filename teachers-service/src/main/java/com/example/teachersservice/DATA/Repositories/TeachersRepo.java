package com.example.teachersservice.DATA.Repositories;

import com.example.teachersservice.DATA.Entities.Teacher;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "teacher", path = "teacher")
public interface TeachersRepo extends PagingAndSortingRepository<Teacher, Long> {
    // add more functions
    Teacher findByEmail(@Param("email") String email);
}
