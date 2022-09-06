package com.waa.alumni_portal_service.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.waa.alumni_portal_service.entity.File;
import com.waa.alumni_portal_service.entity.Tag;
import lombok.Data;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

@Data
//show 10 latest job advertisement
//filter page
public class JobAdvertisementDTO {

    private int id;

    private String title;

    private List<TagDTO> tags;

    private StudentDTO student;

    private List<FileDTO> files;

    private String shortDescription;

    private String description;

    private String benefits;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime creationTime;

    private String state;

    private String companyName;

    private String city;

}
