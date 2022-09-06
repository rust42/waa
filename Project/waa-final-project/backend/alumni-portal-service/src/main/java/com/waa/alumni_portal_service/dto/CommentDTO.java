package com.waa.alumni_portal_service.dto;

import lombok.Data;

@Data
public class CommentDTO {
    private int id;

    private String comment;

    private StudentDTO student;
}
