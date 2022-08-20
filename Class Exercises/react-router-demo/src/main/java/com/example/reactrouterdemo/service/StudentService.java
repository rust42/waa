package com.example.reactrouterdemo.service;

import com.example.reactrouterdemo.dto.SimpleStudentDto;
import com.example.reactrouterdemo.dto.StudentDto;

import java.util.List;

public interface StudentService {

    List<SimpleStudentDto> findAll();

    StudentDto findById(int id);
}
