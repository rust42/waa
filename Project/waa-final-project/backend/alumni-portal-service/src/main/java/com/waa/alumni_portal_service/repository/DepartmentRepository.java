package com.waa.alumni_portal_service.repository;


import com.waa.alumni_portal_service.entity.Department;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepository extends CrudRepository<Department, Integer> {
    List<Department> findAll();
}
