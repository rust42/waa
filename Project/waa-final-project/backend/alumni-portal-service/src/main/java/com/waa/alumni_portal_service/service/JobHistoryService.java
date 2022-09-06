package com.waa.alumni_portal_service.service;

import com.waa.alumni_portal_service.dto.JobHistoryDTO;

import java.util.List;

public interface JobHistoryService {
    List<JobHistoryDTO> findByStudentId(int id);

    void creatNewJobHistory(JobHistoryDTO jobHistoryDTO);

    List<JobHistoryDTO> deleteByStudentId(int id);
}
