package com.waa.alumni_portal_service.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class File {

    @Id
    @SequenceGenerator(name = "FILE_SEQUENCE", sequenceName = "FILE_SEQUENCE", allocationSize=1)
    @GeneratedValue(generator = "FILE_SEQUENCE")
    private int id;

    private String path;

    @ManyToOne
    private JobAdvertisement jobAdvertisement;

    @ManyToOne
    private JobApplication jobApplication;

    @CreationTimestamp
    private Date creationTime;

    private boolean isDeleted;
}
