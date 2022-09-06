package com.spring.rest.phase1.service.Impl;

import com.spring.rest.phase1.entity.Course;
import com.spring.rest.phase1.entity.Student;
import com.spring.rest.phase1.service.ItemService;
import com.spring.rest.phase1.service.StudentCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentCourseServiceImpl implements StudentCourseService {

    @Autowired
    private ItemService<Student> studentItemService;

    @Autowired
    private ItemService<Course> courseItemService;


    @Override
    public void assignCourse(int studentId, int courseId) throws CourseAssignException  {
        Optional<Student> student = studentItemService.find(studentId);
        Optional<Course> course = courseItemService.find(courseId);

        if (student.isEmpty() || course.isEmpty()) {
            throw new CourseAssignException();
        }

        student.ifPresent(s ->
                s.addCourseTaken(course.get()));
    }

    public List<Student> getStudentsByMajor(String major) {
        return studentItemService.findAll()
                .stream()
                .filter(a -> a.getMajor().equals(major))
                .collect(Collectors.toList());
    }

    public List<Course> getCoursesByStudentId(int id) {
        return studentItemService.findAll().stream().filter(a -> a.getId() == id)
                .findAny()
                .map(Student::getCoursesTaken)
                .orElse(new ArrayList<>());
    }

}
