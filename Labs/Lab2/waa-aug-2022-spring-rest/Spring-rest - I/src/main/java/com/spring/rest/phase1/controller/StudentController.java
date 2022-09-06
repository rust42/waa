package com.spring.rest.phase1.controller;


import com.spring.rest.phase1.entity.Student;
import com.spring.rest.phase1.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/phase1/students")
public class StudentController {

    @Autowired
    ItemService<Student> studentService;

    @GetMapping
    public List<Student> findAll() {
        return studentService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Student> find(@PathVariable int id) {
        return studentService.find(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Student create(@RequestBody Student student) {
        studentService.save(student);
        return student;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        studentService.delete(id);
    }
}
