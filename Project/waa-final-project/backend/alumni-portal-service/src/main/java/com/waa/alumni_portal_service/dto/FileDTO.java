package com.waa.alumni_portal_service.dto;

import lombok.Data;

import java.sql.Date;

@Data
public class FileDTO {

    private int id;

    private String base64;

    private Date creationTime;
}
