package com.waa.alumni_portal_service.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.waa.alumni_portal_service.entity.User;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class NotificationInfoDTO {
    private int id;
    private String token;
    @JsonIgnore
    private UserDTO user;
    private LocalDateTime time;
}
