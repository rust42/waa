package com.spring.rest.phase1.service.Impl;

import com.spring.rest.phase1.dto.CourseDto;
import com.spring.rest.phase1.entity.Course;
import com.spring.rest.phase1.repository.CourseRepo;
import com.spring.rest.phase1.service.ItemService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CourseServiceImpl implements ItemService<CourseDto> {

    @Autowired
    private CourseRepo courseRepo;

    @Override
    public List<CourseDto> findAll() {
        return courseRepo.findAll().stream().map(this::mapFromCourse).collect(Collectors.toList());
    }

    @Override
    public Optional<CourseDto> find(int id) {
        return courseRepo.find(id).map(this::mapFromCourse);
    }

    @Override
    public CourseDto save(CourseDto course) {
        Course course1 = new Course(){
            {
                setId(course.getId());
                setName(course.getName());
                setCode(course.getCode());
            }
        };

        return mapFromCourse(courseRepo.save(course1));
    }

    @Override
    public void delete(int courseId) {
        courseRepo.delete(courseId);
    }

    private CourseDto mapFromCourse(Course course) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(course, CourseDto.class);
    }
}
