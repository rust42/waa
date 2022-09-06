package com.waa.alumni_portal_service.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.lang.annotation.Inherited;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tag {

    @Id
    @SequenceGenerator(name = "TAG_SEQUENCE", sequenceName = "TAG_SEQUENCE", allocationSize=1)
    @GeneratedValue(generator = "TAG_SEQUENCE")
    private int id;

    private String tagName;

    @ManyToMany(mappedBy = "tags")
    private List<JobHistory> jobHistories;

    @ManyToMany(mappedBy = "tags")
    private List<JobAdvertisement> jobAdvertisements;

    //using to notification
    @ManyToMany(mappedBy = "interestedTags")
    private List<Student> studentList;
}
