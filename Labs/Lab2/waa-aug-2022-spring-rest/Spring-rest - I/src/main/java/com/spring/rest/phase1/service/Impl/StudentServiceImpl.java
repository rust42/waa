package com.spring.rest.phase1.service.Impl;

import com.spring.rest.phase1.entity.Course;
import com.spring.rest.phase1.entity.Student;
import com.spring.rest.phase1.repository.StudentRepo;
import com.spring.rest.phase1.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements ItemService<Student> {
    @Autowired
    private StudentRepo studentRepo;

    @Override
    public List<Student> findAll() {
        return studentRepo.findAll();
    }

    @Override
    public Optional<Student> find(int id) {
        return studentRepo.find(id);
    }

    @Override
    public Student save(Student student) {
        studentRepo.save(student);
        return student;
    }

    @Override
    public void delete(int studentId) {
        studentRepo.delete(studentId);
    }
}
