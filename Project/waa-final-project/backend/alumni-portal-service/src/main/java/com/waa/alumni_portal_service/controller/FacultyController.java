package com.waa.alumni_portal_service.controller;

import com.waa.alumni_portal_service.dto.FacultyDTO;
import com.waa.alumni_portal_service.dto.StudentDTO;
import com.waa.alumni_portal_service.security.SecurityUtil;
import com.waa.alumni_portal_service.service.FacultyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/faculties")
@CrossOrigin
public class FacultyController {
    private final FacultyService facultyService;
    @GetMapping
    public List<FacultyDTO> findAll(){
        return facultyService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<FacultyDTO> findById(@PathVariable Integer id){
        return facultyService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    void save(@RequestBody FacultyDTO faculty) {
        facultyService.save(faculty);
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    void update(@RequestBody FacultyDTO faculty) {
        facultyService.update(faculty);
    }

}
