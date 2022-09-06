package com.waa.alumni_portal_service.service;

import com.waa.alumni_portal_service.dto.DepartmentDTO;
import com.waa.alumni_portal_service.entity.Department;

import java.util.List;

public interface DepartmentService {
    List<DepartmentDTO> findAll();
}
