package com.spring.rest.phase1.service.Impl;

import com.spring.rest.phase1.entity.Course;
import com.spring.rest.phase1.dto.StudentDto;
import com.spring.rest.phase1.entity.Student;
import com.spring.rest.phase1.repository.StudentRepo;
import com.spring.rest.phase1.service.ItemService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements ItemService<StudentDto> {
    @Autowired
    private StudentRepo studentRepo;

    @Override
    public List<StudentDto> findAll() {
        return studentRepo.findAll().stream().map(this::mapFromStudent).collect(Collectors.toList());
    }

    @Override
    public Optional<StudentDto> find(int id) {
        return studentRepo.find(id).map(this::mapFromStudent);
    }

    @Override
    public StudentDto save(StudentDto student) {
        Student student1 = new Student(){
            {
                setId(student.getId());
                setFirstName(student.getFirstName());
                setLastName(student.getLastName());
                setMajor(student.getMajor());
                setGpa(student.getGpa());
                setEmail(student.getEmail());
            }
        };

        return mapFromStudent(studentRepo.save(student1));
    }

    @Override
    public void delete(int studentId) {
        studentRepo.delete(studentId);
    }

    private StudentDto mapFromStudent(Student student) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(student, StudentDto.class);
    }
}
