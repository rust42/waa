package com.spring.rest.phase1.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Student {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String major;
    private int gpa;
    private List<Course> coursesTaken;

    private boolean deleted;

    public Student() {
        this.coursesTaken = new ArrayList<>();
    }

    public void addCourseTaken(Course course) {
        coursesTaken.add(course);
    }
}
