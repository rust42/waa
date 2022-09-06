package com.spring.rest.phase1.controller;


import com.spring.rest.phase1.dto.StudentDto;
import com.spring.rest.phase1.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    @Autowired
    ItemService<StudentDto> studentService;

    @GetMapping
    public List<StudentDto> findAll() {
        return studentService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<StudentDto> find(@PathVariable int id) {
        return studentService.find(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public StudentDto create(@RequestBody StudentDto student) {
        studentService.save(student);
        return student;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        studentService.delete(id);
    }
}
