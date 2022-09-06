package com.waa.alumni_portal_service.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.waa.alumni_portal_service.entity.Comment;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student extends User {

    @OneToOne
    private Department major;

    private float gpa;

    private Date lastLoggedIntAt;


    //experiences of students
    @OneToMany(mappedBy = "student")
    @Fetch(FetchMode.SUBSELECT)
    @BatchSize(size=10)
    private List<JobHistory> jobHistories;

    //what job students created
    @OneToMany(mappedBy = "student")
    @Fetch(FetchMode.SUBSELECT)
    @BatchSize(size=10)
    private List<JobAdvertisement> jobAdvertisements;

    //jobs which students applied
    @OneToMany(mappedBy = "applicant")
    @Fetch(FetchMode.SUBSELECT)
    @BatchSize(size=10)
    private List<JobApplication> applications = new ArrayList<>();

    //what student interest. Using for subscribed.
    @ManyToMany
    @Fetch(FetchMode.SUBSELECT)
    @BatchSize(size=10)
    private List<Tag> interestedTags;

    //state, city, name, major, studentId: using for filter by faculty
    private String state;
    private String city;

    //using for faculty to write comment to student
    @OneToMany(mappedBy = "student")
    @Fetch(FetchMode.SUBSELECT)
    @BatchSize(size=10)
    private List<Comment> comments;

    public void addApplication(JobApplication application) {
        applications.add(application);
    }

}
