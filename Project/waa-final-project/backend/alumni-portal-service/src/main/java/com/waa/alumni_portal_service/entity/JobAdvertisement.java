package com.waa.alumni_portal_service.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobAdvertisement {

    @Id
    @SequenceGenerator(name = "JOB_ADVERTISEMENT_SEQUENCE", sequenceName = "JOB_ADVERTISEMENT_SEQUENCE", allocationSize=1)
    @GeneratedValue(generator = "JOB_ADVERTISEMENT_SEQUENCE")
    private int id;

    private String title;
    
    //tags are the kind of job(For example: React, Angular, Java Spring boot...)
    @ManyToMany
    private List<Tag> tags;

    //One student can create tons of job advertisement.
    @ManyToOne(fetch = FetchType.LAZY)
    private Student student;

    @Column(columnDefinition="text")
    private String shortDescription;

    @Column(columnDefinition="text")
    private String description;

    @Column(columnDefinition="text")
    private String benefits;

    //images, pdf... related to this job
    @OneToMany(mappedBy = "jobAdvertisement")
    private List<File> files;

    //using to display 10 latest job advertisements
    @UpdateTimestamp
    private LocalDateTime creationTime;

    //List of student apply for this job and only who post this job adv can see it
    @OneToMany(mappedBy = "jobAdvertisement")
    private List<JobApplication> jobApplications;

    // using for filter
    private String state;
    private String companyName;
    private String city;

    private boolean isDeleted;
}