package com.waa.alumni_portal_service.service;

import com.waa.alumni_portal_service.dto.FacultyDTO;
import com.waa.alumni_portal_service.dto.StudentDTO;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public interface FacultyService {
    Optional<FacultyDTO> findById(Integer id);
    List<FacultyDTO> findAll();
    void save(FacultyDTO facultyDTO);
    void update(FacultyDTO facultyDTO);
}
