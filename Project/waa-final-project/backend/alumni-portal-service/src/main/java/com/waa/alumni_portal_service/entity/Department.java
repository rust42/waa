package com.waa.alumni_portal_service.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Department {

    @Id
    @SequenceGenerator(name = "DEPARTMENT_SEQUENCE", sequenceName = "DEPARTMENT_SEQUENCE", allocationSize=1)
    @GeneratedValue(generator = "DEPARTMENT_SEQUENCE")
    private int id;

    private String type;

    //For instance: Computer science, Electrical...
    private String name;

    @OneToOne(mappedBy = "major")
    private Student student;

    @OneToOne(mappedBy = "department")
    private Faculty faculty;

    private boolean isDeleted;
}
