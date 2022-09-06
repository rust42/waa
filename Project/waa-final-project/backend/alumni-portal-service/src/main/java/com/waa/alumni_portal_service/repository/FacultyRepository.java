package com.waa.alumni_portal_service.repository;

import com.waa.alumni_portal_service.entity.Faculty;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FacultyRepository extends CrudRepository<Faculty, Integer> {
}
