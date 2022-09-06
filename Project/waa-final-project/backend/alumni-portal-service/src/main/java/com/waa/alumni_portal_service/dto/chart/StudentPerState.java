package com.waa.alumni_portal_service.dto.chart;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StudentPerState {
    private String name;
    private Long value;
}
