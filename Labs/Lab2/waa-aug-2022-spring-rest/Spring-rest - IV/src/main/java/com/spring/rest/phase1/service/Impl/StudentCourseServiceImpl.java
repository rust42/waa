package com.spring.rest.phase1.service.Impl;

import com.spring.rest.phase1.dto.CourseDto;
import com.spring.rest.phase1.dto.StudentDto;
import com.spring.rest.phase1.entity.Course;
import com.spring.rest.phase1.entity.Student;
import com.spring.rest.phase1.repository.CourseRepo;
import com.spring.rest.phase1.repository.StudentRepo;
import com.spring.rest.phase1.service.StudentCourseService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentCourseServiceImpl implements StudentCourseService {

    @Autowired
    private StudentRepo studentRepo;

    @Autowired
    private CourseRepo courseRepo;


    @Override
    public void assignCourse(int studentId, int courseId) throws CourseAssignException  {
        Optional<Student> student = studentRepo.find(studentId);
        Optional<Course> course = courseRepo.find(courseId);

        if (student.isEmpty() || course.isEmpty()) {
            throw new CourseAssignException();
        }

        student.ifPresent(s ->
                s.addCourseTaken(course.get()));
    }

    public List<StudentDto> getStudentsByMajor(String major) {
        return studentRepo.findAll(major)
                .stream().map(student -> {
                    ModelMapper mapper = new ModelMapper();
                    return mapper.map(student, StudentDto.class);
                })
                .toList();
    }

    public List<CourseDto> getCoursesByStudentId(int id) {
        return studentRepo.find(id)
                .map(Student::getCoursesTaken)
                .map(courses ->
                    courses.stream().map(course -> {
                                ModelMapper mapper = new ModelMapper();
                                return mapper.map(course, CourseDto.class);
                            })
                            .toList()
                )
                .orElse(new ArrayList<>());
    }

}
