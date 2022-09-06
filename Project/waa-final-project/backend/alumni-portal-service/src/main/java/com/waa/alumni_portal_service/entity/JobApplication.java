package com.waa.alumni_portal_service.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobApplication {

    @Id
    @SequenceGenerator(name = "JOB_APPLIED_SEQUENCE", sequenceName = "JOB_APPLIED_SEQUENCE", allocationSize=1)
    @GeneratedValue(generator = "JOB_APPLIED_SEQUENCE")
    private int id;

    private String title;

    private String coverLetter;

    @ManyToOne
    private Student applicant;

    @ManyToOne
    private JobAdvertisement jobAdvertisement;

    @OneToMany(mappedBy = "jobApplication")
    private List<File> attachments = new ArrayList<>();

    //using for to show for student 10 latest applied CV
    @CreationTimestamp
    private LocalDateTime creationTime;

    private boolean isDeleted;

    public void addAttachment(File attachment) {
        attachments.add(attachment);
    }
}
