package com.spring.rest.phase1.controller;

import com.spring.rest.phase1.entity.Course;
import com.spring.rest.phase1.entity.Student;
import com.spring.rest.phase1.service.Impl.CourseAssignException;
import com.spring.rest.phase1.service.StudentCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/phase1/students/courses/")
@RestController
public class StudentCoursesController {

    @Autowired
    private StudentCourseService studentCourseService;

    @PostMapping("/{studentId}/{courseId}/assign")
    public void assignCourse(@PathVariable int studentId, @PathVariable int courseId) {
        try {
            studentCourseService.assignCourse(studentId, courseId);
        } catch (CourseAssignException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/{studentId}")
    public List<Course> allCourses(@PathVariable int studentId) {
        return studentCourseService.getCoursesByStudentId(studentId);
    }

    @GetMapping("/major/{major}")
    public List<Student> getStudentsByMajor(@PathVariable String major) {
        return studentCourseService.getStudentsByMajor(major);
    }
}
