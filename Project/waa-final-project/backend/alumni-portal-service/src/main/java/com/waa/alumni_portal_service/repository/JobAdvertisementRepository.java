package com.waa.alumni_portal_service.repository;

import com.waa.alumni_portal_service.dto.chart.JobByState;
import com.waa.alumni_portal_service.entity.JobAdvertisement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobAdvertisementRepository extends PagingAndSortingRepository<JobAdvertisement, Integer>, CrudRepository<JobAdvertisement, Integer>, JpaSpecificationExecutor<JobAdvertisement> {
    List<JobAdvertisement> findFirst10ByOrderByCreationTimeDesc();

    List<JobAdvertisement> findByStudentId(Integer id);

    @Query(value = "SELECT DISTINCT j.state from  job_advertisement as j", nativeQuery = true)
    List<String> findAllStates();

//    @Query(value = "SELECT new com.waa.alumni_portal_service.dto.Testing(j.state, COUNT(j.state)) " +
//            "from job_advertisement AS J GROUP BY J.state", nativeQuery = true)
    @Query("SELECT new com.waa.alumni_portal_service.dto.chart.JobByState( j.state, COUNT(j.state) ) " +
            "from JobAdvertisement AS j GROUP BY j.state")
    List<JobByState> getAllJobsByState();

    @Query("SELECT new com.waa.alumni_portal_service.dto.chart.JobByState(t.tagName, COUNT(j.state) ) " +
            "from JobAdvertisement AS j JOIN j.tags t GROUP BY t.tagName")
    List<JobByState> getTagsByJobs();


    @Query("SELECT new com.waa.alumni_portal_service.dto.chart.JobByState(t.tagName, COUNT(j.state) ) " +
            "from JobAdvertisement AS j JOIN j.tags t WHERE j.state = :state GROUP BY t.tagName")
    List<JobByState> getTagsByLocation(String state);

    @Query("SELECT new com.waa.alumni_portal_service.dto.chart.JobByState( j.city, COUNT(j.city) ) " +
            "from JobAdvertisement AS j GROUP BY j.city")
    List<JobByState> getAllJobsByCity();

    @Query(value = "SELECT DISTINCT j.*, max(ja.creation_time) as appliedTime FROM job_advertisement j " +
        "JOIN job_application ja on j.id = ja.job_advertisement_id " +
        "GROUP BY j.id " +
        "ORDER BY appliedTime DESC LIMIT :top", nativeQuery = true)
    List<JobAdvertisement> findRecentlyAppliedJob(Integer top);

    @Query(value = "SELECT DISTINCT j.city from job_advertisement j", nativeQuery = true)
    List<String> findAllCities();
}
