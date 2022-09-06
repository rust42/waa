package com.waa.alumni_portal_service.controller;

import com.waa.alumni_portal_service.dto.JobAdvertisementDTO;
import com.waa.alumni_portal_service.dto.JobApplicationRequest;
import com.waa.alumni_portal_service.dto.PagingDTO;
import com.waa.alumni_portal_service.dto.chart.EChartDTO;
import com.waa.alumni_portal_service.security.SecurityUtil;
import com.waa.alumni_portal_service.service.JobAdvertisementService;
import com.waa.alumni_portal_service.service.JobApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.HashMap;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/jobAdvertisements")
@CrossOrigin
public class JobAdvertisementController {
    private final JobAdvertisementService jobAdvertisementService;
    private final SecurityUtil securityUtil;
    private final JobApplicationService jobApplicationService;

    @GetMapping
    public List<JobAdvertisementDTO> findAll() {
        return jobAdvertisementService.findAll();
    }

    @GetMapping("/getFirst10ByCreationTime")
    @RolesAllowed({"FACULTY","STUDENT"})
    public List<JobAdvertisementDTO> findFirst10OrderByCreationTimeDesc() {
        return jobAdvertisementService.findFirst10OrderByCreationTimeDesc();
    }

    @PostMapping(value = "/{jobId}/apply", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @RolesAllowed("STUDENT")
    public ResponseEntity apply(@PathVariable int jobId, @ModelAttribute JobApplicationRequest application) throws Exception {
        jobApplicationService.storeApplication(jobId, application);
        return ResponseEntity.ok(null);
    }

    @PostMapping
    @RolesAllowed("STUDENT")
    public ResponseEntity createNewJobAdvertisement(@RequestBody JobAdvertisementDTO jobAdvertisementDTO) {
        jobAdvertisementService.createNewJobAdvertisement(jobAdvertisementDTO);
        return ResponseEntity.ok(null);
    }

    @PutMapping("/{id}")
    @RolesAllowed("STUDENT")
    public ResponseEntity updateJobAdvertisement(@RequestBody JobAdvertisementDTO jobAdvertisementDTO, @PathVariable int id) {
        if (jobAdvertisementDTO.getId() != id || !jobAdvertisementService.updateJobAdvertisement(jobAdvertisementDTO)) {
            return (ResponseEntity) ResponseEntity.status(500);
        }
        return ResponseEntity.ok(null);
    }

    @GetMapping("/{id}")
    @RolesAllowed({"FACULTY","STUDENT"})
    public JobAdvertisementDTO findById(@PathVariable int id) {
        return jobAdvertisementService.findById(id);
    }

    @GetMapping("/searching")
    @RolesAllowed({"FACULTY","STUDENT"})
    public PagingDTO<JobAdvertisementDTO> filter(@RequestParam HashMap<String, String> params) {
        return jobAdvertisementService.findAllBySearchParams(params);
    }

    @GetMapping("/findMyPostedJob")
    @RolesAllowed("STUDENT")
    public List<JobAdvertisementDTO> findMyPostedJob() {
        return jobAdvertisementService.findByPosterId(securityUtil.getCurrentUser().getId());
    }

    @GetMapping("/states")
    public List<String> findAllState() {
        return jobAdvertisementService.findAllStates();
    }

    @GetMapping("/echarts/jobsByLocation")
    @RolesAllowed({"FACULTY","STUDENT"})
    public EChartDTO getAllJobsByLocation() {
        return jobAdvertisementService.getAllJobsByLocation();
    }

    @GetMapping("/recentlyAppliedJob")
    @RolesAllowed("STUDENT")
    public List<JobAdvertisementDTO> findRecentlyAppliedJob(){
        return jobAdvertisementService.findRecentlyAppliedJob();
    }

    @GetMapping("/echarts/tagsByJob")
    @RolesAllowed({"FACULTY","STUDENT"})
    public EChartDTO getTagsByJobs() {
        return jobAdvertisementService.getTagsByJobs();
    }

    @GetMapping("/echarts/tagsByLocation")
    @RolesAllowed({"FACULTY","STUDENT"})
    public EChartDTO getTagsByLocation(@RequestParam HashMap<String, String> params){
        return jobAdvertisementService.getTagsByLocation(params);
    }

    @GetMapping("/echarts/tagsByCity")
    @RolesAllowed({"FACULTY","STUDENT"})
    public EChartDTO getAllJobsByCity(){
        return jobAdvertisementService.getAllJobsByCity();
    }

    @GetMapping("/cities")
    public List<String> findCities() {
        return jobAdvertisementService.findAllCities();
    }

}
