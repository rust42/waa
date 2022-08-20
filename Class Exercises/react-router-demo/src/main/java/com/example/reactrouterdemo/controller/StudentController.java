package com.example.reactrouterdemo.controller;

import com.example.reactrouterdemo.dto.SimpleStudentDto;
import com.example.reactrouterdemo.dto.StudentDto;
import com.example.reactrouterdemo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping
    @CrossOrigin
    List<SimpleStudentDto> findAll() {
        return studentService.findAll();
    }

    @GetMapping("/{id}")
    StudentDto findById(@PathVariable int id) {
        return studentService.findById(id);
    }
}
