package com.waa.alumni_portal_service.dto;

import com.waa.alumni_portal_service.entity.Department;
import lombok.Data;

@Data

public class FacultyDTO extends UserDTO {

    private DepartmentDTO department;
}
