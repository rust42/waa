package com.waa.alumni_portal_service.service.impl;

import com.waa.alumni_portal_service.dto.JobHistoryDTO;
import com.waa.alumni_portal_service.dto.StudentDTO;
import com.waa.alumni_portal_service.entity.JobHistory;
import com.waa.alumni_portal_service.repository.JobHistoryRepository;
import com.waa.alumni_portal_service.service.JobHistoryService;
import com.waa.alumni_portal_service.util.UserDetailUtil;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class JobHistoryServiceImpl implements JobHistoryService {
    private final ModelMapper modelMapper;
    private final JobHistoryRepository jobHistoryRepository;

    private final UserDetailUtil userDetailUtil;

    @Override
    public List<JobHistoryDTO> findByStudentId(int id) {
        var result = new ArrayList<JobHistoryDTO>();
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setId(id);
        jobHistoryRepository.findByStudentId(studentDTO.getId()).forEach(jobHis -> {
            if (!jobHis.isDeleted()) result.add(modelMapper.map(jobHis, JobHistoryDTO.class));
        });
        return result;
    }

    @Override
    public void creatNewJobHistory(JobHistoryDTO jobHistoryDTO) {
        jobHistoryDTO.setStudent(userDetailUtil.getCurrentStudent());
        jobHistoryRepository.save(modelMapper.map(jobHistoryDTO, JobHistory.class));
    }

    @Override
    public List<JobHistoryDTO> deleteByStudentId(int id) {
        JobHistory jobHistory = jobHistoryRepository.findById(id);
        jobHistory.setDeleted(true);
        jobHistoryRepository.save(jobHistory);

        var result = new ArrayList<JobHistoryDTO>();
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setId(userDetailUtil.getCurrentUser().getId());
        jobHistoryRepository.findByStudentId(studentDTO.getId()).forEach(jobHis -> {
            if (!jobHis.isDeleted()) result.add(modelMapper.map(jobHis, JobHistoryDTO.class));
        });
        return result;
    }
}
