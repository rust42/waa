package com.waa.alumni_portal_service.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobHistory {

    @Id
    @SequenceGenerator(name = "JOB_HISTORY_SEQUENCE", sequenceName = "JOB_HISTORY_SEQUENCE", allocationSize=1)
    @GeneratedValue(generator = "JOB_HISTORY_SEQUENCE")
    private int id;

    @ManyToMany
    private List<Tag> tags;

    private String company;
    private Date startDate;
    private Date endDate;
    private String Description;
    private String title;
    private String comments;

    @ManyToOne
    private Student student;

    private boolean isDeleted;
}
