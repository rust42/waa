package com.waa.alumni_portal_service.controller;

import com.waa.alumni_portal_service.dto.CommentDTO;
import com.waa.alumni_portal_service.dto.PagingDTO;
import com.waa.alumni_portal_service.dto.StudentDTO;
import com.waa.alumni_portal_service.dto.chart.EChartDTO;
import com.waa.alumni_portal_service.service.CommentService;
import com.waa.alumni_portal_service.service.StudentService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/students")
@CrossOrigin
@SecurityRequirement(name = "bearerAuth")
public class StudentController {
    private final StudentService studentService;

    private final CommentService commentService;

    @GetMapping
    public PagingDTO<StudentDTO> findAll(@RequestParam HashMap<String, String> params) {
        return studentService.findAll(params);
    }

    @GetMapping("/{id}")
    public Optional<StudentDTO> findById(@PathVariable Integer id) {
        return studentService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @RolesAllowed("STUDENT")
    void save(@RequestBody StudentDTO student) {
        studentService.save(student);
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @RolesAllowed("STUDENT")
    void update(@RequestBody StudentDTO student) {
        studentService.update(student);
    }

    @GetMapping("/cities")
    public List<String> findCities() {
        return studentService.findAllCities();
    }

    @GetMapping("/{id}/comments")
    @RolesAllowed("FACULTY")
    public List<CommentDTO> findAllComments(@PathVariable int id) {
        ;
        return commentService.findAllComments(id);
    }

    @PostMapping("/{id}/comments")
    @RolesAllowed("FACULTY")
    public void createNewComment(@RequestBody CommentDTO commentDTO, @PathVariable int id) {
        commentService.creatNewComment(commentDTO, id);
    }

    @GetMapping("/states")
    @RolesAllowed({"FACULTY","STUDENT"})
    public List<String> getState(){
        return studentService.getState();
    }

    @GetMapping("/echarts/studentsPerState")
    @RolesAllowed({"FACULTY","STUDENT"})
    public EChartDTO getStudentsPerState(){
        return studentService.getStudentsPerState();
    }

    @GetMapping("/echarts/studentsByCity")
    @RolesAllowed({"FACULTY","STUDENT"})
    public EChartDTO getStudentsByCity(@RequestParam HashMap<String, String> params){
        return studentService.getStudentByCity(params);
    }
}