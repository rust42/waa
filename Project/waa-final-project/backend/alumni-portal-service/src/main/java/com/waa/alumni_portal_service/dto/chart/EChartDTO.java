package com.waa.alumni_portal_service.dto.chart;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class EChartDTO {
    private List<String> names;
    //special word in front end
    private List<Long> numbers;
}
