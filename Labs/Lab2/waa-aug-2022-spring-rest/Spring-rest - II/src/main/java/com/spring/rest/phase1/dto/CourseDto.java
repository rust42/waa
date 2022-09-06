package com.spring.rest.phase1.dto;

import com.spring.rest.phase1.entity.Course;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CourseDto {
    private int id;
    private String name;
    private String code;

    public static CourseDto mapFromCourseEntity(Course course){
        return new CourseDto(course.getId(),course.getName(),course.getCode());
    }
}
