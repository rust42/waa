package com.example.reactrouterdemo.service.impl;

import com.example.reactrouterdemo.dto.SimpleStudentDto;
import com.example.reactrouterdemo.dto.StudentDto;
import com.example.reactrouterdemo.entity.Student;
import com.example.reactrouterdemo.repository.StudentRepo;
import com.example.reactrouterdemo.service.StudentService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepo studentRepo;

    public StudentServiceImpl(StudentRepo studentRepo) {
        this.studentRepo = studentRepo;
    }

    @Override
    public List<SimpleStudentDto> findAll() {
        var data = studentRepo.findAll();
        var result = new ArrayList<SimpleStudentDto>();
        data.forEach (aData -> {
            SimpleStudentDto simpleStudentDto = new SimpleStudentDto(aData.getId(), aData.getName());
            result.add(simpleStudentDto);
        });
        return result;
    }

    @Override
    public StudentDto findById(int id) {
        Student student = studentRepo.findById(id).get();

        return new StudentDto(student.getId(), student.getName(), student.getEmail(), student.getGpa());
    }
}
