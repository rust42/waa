package com.waa.alumni_portal_service.service.impl;

import com.waa.alumni_portal_service.dto.DepartmentDTO;
import com.waa.alumni_portal_service.entity.Department;
import com.waa.alumni_portal_service.repository.DepartmentRepository;
import com.waa.alumni_portal_service.service.DepartmentService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final ModelMapper modelMapper;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository, ModelMapper modelMapper) {
        this.departmentRepository = departmentRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<DepartmentDTO> findAll() {
        Iterable<Department> departments = departmentRepository.findAll();
        var data = new ArrayList<DepartmentDTO>();
        departments.forEach(department ->{
            data.add(modelMapper.map(department, DepartmentDTO.class));
        });
        return data;
    }
}
