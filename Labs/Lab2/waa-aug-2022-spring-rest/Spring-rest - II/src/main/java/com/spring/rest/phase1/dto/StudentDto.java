package com.spring.rest.phase1.dto;

import com.spring.rest.phase1.entity.Student;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StudentDto {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String major;
    private int gpa;

    public static StudentDto mapFromEntity(Student student){
        return new StudentDto(student.getId(),student.getFirstName(),student.getLastName(),
        student.getEmail(),student.getMajor(),student.getGpa());
    }
}
