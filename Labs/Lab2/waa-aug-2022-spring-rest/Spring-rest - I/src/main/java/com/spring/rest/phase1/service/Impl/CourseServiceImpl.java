package com.spring.rest.phase1.service.Impl;

import com.spring.rest.phase1.entity.Course;
import com.spring.rest.phase1.repository.CourseRepo;
import com.spring.rest.phase1.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements ItemService<Course> {

    @Autowired
    private CourseRepo courseRepo;

    @Override
    public List<Course> findAll() {
        return courseRepo.findAll();
    }

    @Override
    public Optional<Course> find(int id) {
        return courseRepo.find(id);
    }

    @Override
    public Course save(Course course) {
        return courseRepo.save(course);
    }

    @Override
    public void delete(int courseId) {
        courseRepo.delete(courseId);
    }
}
