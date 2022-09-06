package com.spring.rest.phase1.repository;

import com.spring.rest.phase1.entity.Course;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class CourseRepo {
    private List<Course> courses = new ArrayList<>();

    public List<Course> findAll()  {
        return courses.stream()
                .filter(a -> !a.isDeleted())
                .collect(Collectors.toList());
    }

    public Optional<Course> find(int id)  {
        return courses.stream()
                .filter(a -> !a.isDeleted() && a.getId() == id)
                .findFirst();
    }


    public Course save(Course course) {
        course.setId(courses.size() + 1);
        courses.add(course);
        return course;
    }

    public void delete(int id) {
        courses.stream()
                .filter(a -> a.getId() == id)
                .findAny()
                .ifPresent(value -> value.setDeleted(true));
    }
}
