package com.waa.alumni_portal_service.service;

import com.waa.alumni_portal_service.dto.PagingDTO;
import com.waa.alumni_portal_service.dto.StudentDTO;
import com.waa.alumni_portal_service.dto.chart.EChartDTO;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public interface StudentService {
    Optional<StudentDTO> findById(Integer id);

    PagingDTO<StudentDTO> findAll(HashMap<String, String> searchParams);

    EChartDTO getStudentByCity(HashMap<String, String> params);

    List<String> findAllCities();

    void save(StudentDTO studentDTO);

    void update(StudentDTO studentDTO);

    EChartDTO getStudentsPerState();

    List<String> getState();
}
