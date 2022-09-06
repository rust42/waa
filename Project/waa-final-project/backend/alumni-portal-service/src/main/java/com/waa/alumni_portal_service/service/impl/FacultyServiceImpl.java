package com.waa.alumni_portal_service.service.impl;

import com.waa.alumni_portal_service.dto.FacultyDTO;
import com.waa.alumni_portal_service.entity.Faculty;
import com.waa.alumni_portal_service.repository.FacultyRepository;
import com.waa.alumni_portal_service.security.SecurityUtil;
import com.waa.alumni_portal_service.service.FacultyService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FacultyServiceImpl implements FacultyService {

    private final FacultyRepository facultyRepository;

    private final ModelMapper modelMapper;

    private final SecurityUtil securityUtil;

    @Override
    public Optional<FacultyDTO> findById(Integer id) {
        return facultyRepository.findById(id).map(faculty -> modelMapper.map(faculty, FacultyDTO.class));
    }

    @Override
    public List<FacultyDTO> findAll() {
        var data = new ArrayList<FacultyDTO>();
        facultyRepository.findAll().forEach(faculty -> {
            data.add(modelMapper.map(faculty, FacultyDTO.class));
        });
        return data;
    }

    @Override
    public void save(FacultyDTO facultyDTO) {
        facultyRepository.save(modelMapper.map(facultyDTO, Faculty.class));
    }

    @Override
    public void update(FacultyDTO facultyDTO) {
        if (facultyDTO.getId() != securityUtil.getCurrentUser().getId())
            throw new IllegalArgumentException("Not valid user");
        findById(facultyDTO.getId()).map(existedFaculty -> {
            facultyDTO.setEmail(existedFaculty.getEmail());
            save(facultyDTO);
            return existedFaculty;
        });
    }

}
