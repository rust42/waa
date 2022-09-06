package com.spring.rest.phase1.service;


import com.spring.rest.phase1.dto.CourseDto;
import com.spring.rest.phase1.dto.StudentDto;
import com.spring.rest.phase1.service.Impl.CourseAssignException;

import java.util.List;

public interface StudentCourseService {
    public void assignCourse(int studentId, int courseId) throws CourseAssignException;
    public List<StudentDto> getStudentsByMajor(String major);
    public List<CourseDto> getCoursesByStudentId(int id);
}
