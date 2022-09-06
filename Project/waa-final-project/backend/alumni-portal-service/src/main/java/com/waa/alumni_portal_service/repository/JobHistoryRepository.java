package com.waa.alumni_portal_service.repository;

import com.waa.alumni_portal_service.entity.JobHistory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobHistoryRepository extends CrudRepository<JobHistory, Integer> {

    List<JobHistory> findByStudentId(int id);

    JobHistory findById(int id);

}
