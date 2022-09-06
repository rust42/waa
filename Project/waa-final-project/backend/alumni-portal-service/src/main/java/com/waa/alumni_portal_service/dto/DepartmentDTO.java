package com.waa.alumni_portal_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentDTO {
    private int id;

    private String type;

    //For instance: Computer science, Electrical...
    private String name;

}
