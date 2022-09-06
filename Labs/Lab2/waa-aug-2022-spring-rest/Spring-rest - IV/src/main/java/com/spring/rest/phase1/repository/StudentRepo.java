package com.spring.rest.phase1.repository;

import com.spring.rest.phase1.entity.Course;
import com.spring.rest.phase1.entity.Student;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class StudentRepo {
    private List<Student> students = new ArrayList<>();

    public List<Student> findAll() {
        return students.stream()
                .filter(a -> !a.isDeleted())
                .collect(Collectors.toList());
    }

    public List<Student> findAll(String major) {
        return findAll()
                .stream()
                .filter(a -> a.getMajor().equals(major))
                .collect(Collectors.toList());
    }

    public Student save(Student student) {
        student.setId(students.size() + 1);
        students.add(student);
        return student;
    }

    public Optional<Student> find(int id) {
        return findAll().stream()
                .filter(a -> a.getId() == id)
                .findFirst();
    }

    public void delete(int studentId) {
        find(studentId)
                .ifPresent(value -> value.setDeleted(true));
    }
}