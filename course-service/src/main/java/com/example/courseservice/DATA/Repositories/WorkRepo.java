package com.example.courseservice.DATA.Repositories;

import com.example.courseservice.DATA.Entities.Work;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "work", path = "work")
public interface WorkRepo extends PagingAndSortingRepository<Work, Long> {
}
