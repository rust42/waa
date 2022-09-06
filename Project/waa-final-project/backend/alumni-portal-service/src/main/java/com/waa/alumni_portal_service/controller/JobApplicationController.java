package com.waa.alumni_portal_service.controller;

import com.waa.alumni_portal_service.dto.JobApplicationDTO;
import com.waa.alumni_portal_service.service.JobApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/jobApplications")
@CrossOrigin
public class JobApplicationController {
    private final JobApplicationService jobApplicationService;

    @GetMapping("/findLastByJobAdsId")
    @RolesAllowed({"FACULTY","STUDENT"})
    List<JobApplicationDTO> findLastByJobAdsId(@RequestParam Integer id){
        return jobApplicationService.findByJobAdvId(id);
    }

    @GetMapping("/{id}")
    @RolesAllowed({"FACULTY","STUDENT"})
    Optional<JobApplicationDTO> findById(@PathVariable int id) {
        return jobApplicationService.findById(id);
    }
}
