package com.waa.alumni_portal_service.service;

import com.waa.alumni_portal_service.dto.JobApplicationDTO;
import com.waa.alumni_portal_service.dto.JobApplicationRequest;

import java.util.List;
import java.util.Optional;

public interface JobApplicationService {
    List<JobApplicationDTO> findByJobAdvId(Integer id);
    Optional<JobApplicationDTO> findById(Integer id);

    public void storeApplication(int advertisementId, JobApplicationRequest application) throws Exception;
}
