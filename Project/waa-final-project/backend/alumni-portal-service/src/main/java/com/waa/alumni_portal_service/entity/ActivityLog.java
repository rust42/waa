package com.waa.alumni_portal_service.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDateTime;

@Entity
@Data
public class ActivityLog {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    private String operation;
    private Long duration;
    private String info;
    @OneToOne()
    private User user;
    @CreationTimestamp
    private LocalDateTime time;
}
