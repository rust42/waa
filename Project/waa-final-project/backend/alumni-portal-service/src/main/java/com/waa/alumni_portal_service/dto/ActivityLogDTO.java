package com.waa.alumni_portal_service.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ActivityLogDTO {
    private int id;
    private String operation;
    private Long duration;
    private String info;
    private UserDTO user;
    private LocalDateTime time;
}
