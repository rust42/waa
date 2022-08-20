package com.example.reactrouterdemo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StudentDto {
    private int id;

    private String name;
    private String email;
    private String gpa;
}
