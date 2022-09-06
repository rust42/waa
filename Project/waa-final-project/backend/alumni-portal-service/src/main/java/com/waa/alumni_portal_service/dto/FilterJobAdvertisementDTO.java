package com.waa.alumni_portal_service.dto;

import lombok.Data;

import java.util.List;

@Data
public class FilterJobAdvertisementDTO {

    private List<TagDTO> tags;
    private String state;
    private String city;
    private String comapnyName;
}
