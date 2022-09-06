package com.waa.alumni_portal_service.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.waa.alumni_portal_service.entity.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
//person information
public class StudentDTO extends UserDTO {
    private DepartmentDTO major;
    private float gpa;
    private String state;
    private String city;
    private List<TagDTO> interestedTags;
}
