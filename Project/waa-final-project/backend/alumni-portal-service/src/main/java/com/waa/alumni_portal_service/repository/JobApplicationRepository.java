package com.waa.alumni_portal_service.repository;

import com.waa.alumni_portal_service.entity.JobApplication;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobApplicationRepository extends CrudRepository<JobApplication, Integer> {
    List<JobApplication> findByJobAdvertisementIdOrderByCreationTimeAsc(Integer jobAdvertisementId);
}
