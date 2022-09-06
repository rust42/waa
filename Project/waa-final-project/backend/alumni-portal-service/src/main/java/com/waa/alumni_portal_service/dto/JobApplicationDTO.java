package com.waa.alumni_portal_service.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class JobApplicationDTO {
    private int id;

    private String title;

    private String coverLetter;

    private StudentDTO applicant;

    @JsonIgnore
    private JobAdvertisementDTO jobAdvertisement;

    private List<FileDTO> attachments;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime creationTime;
}
