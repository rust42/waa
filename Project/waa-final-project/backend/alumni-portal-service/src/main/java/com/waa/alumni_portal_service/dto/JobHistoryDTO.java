package com.waa.alumni_portal_service.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.waa.alumni_portal_service.entity.Tag;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class JobHistoryDTO {

    private int id;

    private List<TagDTO> tags;

    private String company;
    private Date startDate;
    private Date endDate;
    private String description;
    private String title;
    private String comments;

    @JsonIgnore
    private StudentDTO student;
}
