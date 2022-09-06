package com.spring.rest.phase1.controller;

import com.spring.rest.phase1.dto.CourseDto;
import com.spring.rest.phase1.dto.StudentDto;
import com.spring.rest.phase1.service.Impl.CourseAssignException;
import com.spring.rest.phase1.service.StudentCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/students/")
@RestController
public class StudentCoursesController {

    @Autowired
    private StudentCourseService studentCourseService;

    @PostMapping("/{studentId}/courses/{courseId}/assign")
    public void assignCourse(@PathVariable int studentId, @PathVariable int courseId) {
        try {
            studentCourseService.assignCourse(studentId, courseId);
        } catch (CourseAssignException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/{studentId}/courses")
    public List<CourseDto> allCourses(@PathVariable int studentId) {
        return studentCourseService.getCoursesByStudentId(studentId);
    }

    @GetMapping("/major/{major}")
    public List<StudentDto> getStudentsByMajor(@PathVariable String major) {
        return studentCourseService.getStudentsByMajor(major);
    }
}
