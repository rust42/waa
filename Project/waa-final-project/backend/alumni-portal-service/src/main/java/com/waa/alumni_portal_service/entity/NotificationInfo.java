package com.waa.alumni_portal_service.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
public class NotificationInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    private String token;

    @OneToOne()
    private User user;

    @CreationTimestamp
    private LocalDateTime time;
}
