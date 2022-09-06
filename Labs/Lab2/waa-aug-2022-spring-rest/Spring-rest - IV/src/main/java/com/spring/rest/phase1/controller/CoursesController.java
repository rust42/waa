package com.spring.rest.phase1.controller;

import com.spring.rest.phase1.dto.CourseDto;
import com.spring.rest.phase1.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/courses")
public class CoursesController {
    @Autowired
    private ItemService<CourseDto> courseService;

    @GetMapping
    public List<CourseDto> findAll() {
        return courseService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<CourseDto> find(@PathVariable  int id) {
        return courseService.find(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public CourseDto create(@RequestBody CourseDto course) {
        courseService.save(course);
        return course;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        courseService.delete(id);
    }
}
