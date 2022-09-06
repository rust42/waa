package com.spring.rest.phase1.service;


import com.spring.rest.phase1.entity.Course;
import com.spring.rest.phase1.entity.Student;
import com.spring.rest.phase1.service.Impl.CourseAssignException;

import java.util.List;

public interface StudentCourseService {
    public void assignCourse(int studentId, int courseId) throws CourseAssignException;
    public List<Student> getStudentsByMajor(String major);
    public List<Course> getCoursesByStudentId(int id);
}
