package com.waa.alumni_portal_service.controller;

import com.waa.alumni_portal_service.dto.JobHistoryDTO;
import com.waa.alumni_portal_service.service.JobHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/jobHistories")
@CrossOrigin
public class JobHistoryController {
    private final JobHistoryService jobHistoryService;

    @GetMapping("/student/{id}")
    @RolesAllowed({"STUDENT","FACULTY"})
    public List<JobHistoryDTO> findAllJobHistoriesByStudentId(@PathVariable int id) {
        return jobHistoryService.findByStudentId(id);
    }

    @PostMapping
    @RolesAllowed("STUDENT")
    public ResponseEntity createNewJobHistory(@RequestBody JobHistoryDTO jobHistoryDTO) {
        jobHistoryService.creatNewJobHistory(jobHistoryDTO);
        return ResponseEntity.ok(null);
    }

    @DeleteMapping("/{id}")
    @RolesAllowed("STUDENT")
    public List<JobHistoryDTO> deleteById(@PathVariable int id){
       return jobHistoryService.deleteByStudentId(id);
    }
}
