package com.waa.alumni_portal_service.service;

import com.waa.alumni_portal_service.dto.PagingDTO;
import com.waa.alumni_portal_service.dto.chart.EChartDTO;
import com.waa.alumni_portal_service.dto.JobAdvertisementDTO;

import java.util.HashMap;
import java.util.List;

public interface JobAdvertisementService {
    List<JobAdvertisementDTO> findAll();

    List<JobAdvertisementDTO> findFirst10OrderByCreationTimeDesc();

    void createNewJobAdvertisement(JobAdvertisementDTO jobAdvertisementDTO);

    boolean updateJobAdvertisement(JobAdvertisementDTO jobAdvertisementDTO);

    JobAdvertisementDTO findById(int id);

    List<JobAdvertisementDTO> findByPosterId(Integer id);

    PagingDTO<JobAdvertisementDTO> findAllBySearchParams(HashMap<String, String> searchParams);

    List<String> findAllStates();

    EChartDTO getAllJobsByLocation();

    EChartDTO getTagsByJobs();

    EChartDTO getTagsByLocation(HashMap<String, String> params);

    EChartDTO getAllJobsByCity();

    List<JobAdvertisementDTO> findRecentlyAppliedJob();

    List<String> findAllCities();
}
