package com.waa.alumni_portal_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class JobApplicationRequest {

    private String title;
    private String coverLetter;
    private MultipartFile file;

}