package com.spring.rest.phase1.service.Impl;

import com.spring.rest.phase1.entity.Course;
import com.spring.rest.phase1.dto.StudentDto;
import com.spring.rest.phase1.entity.Student;
import com.spring.rest.phase1.repository.StudentRepo;
import com.spring.rest.phase1.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.events.Event;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements ItemService<StudentDto> {
    @Autowired
    private StudentRepo studentRepo;

    @Override
    public List<StudentDto> findAll() {
        return studentRepo.findAll().stream().map(StudentDto::mapFromEntity).collect(Collectors.toList());
    }

    @Override
    public Optional<StudentDto> find(int id) {
        return studentRepo.find(id).map(StudentDto::mapFromEntity);
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

        return StudentDto.mapFromEntity(studentRepo.save(student1));
    }

    @Override
    public void delete(int studentId) {
        studentRepo.delete(studentId);
    }
}
