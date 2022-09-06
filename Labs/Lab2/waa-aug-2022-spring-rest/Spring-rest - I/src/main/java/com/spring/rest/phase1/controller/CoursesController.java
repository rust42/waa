package com.spring.rest.phase1.controller;

import com.spring.rest.phase1.entity.Course;
import com.spring.rest.phase1.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/phase1/courses")
public class CoursesController {
    @Autowired
    private ItemService<Course> courseService;

    @GetMapping
    public List<Course> findAll() {
        return courseService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Course> find(@PathVariable  int id) {
        return courseService.find(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Course create(@RequestBody Course course) {
        courseService.save(course);
        return course;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        courseService.delete(id);
    }
}
