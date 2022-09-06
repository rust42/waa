package com.waa.alumni_portal_service.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class UserDTO {
    private int id;
    private String email;
    private String firstName;
    private String lastName;
}
