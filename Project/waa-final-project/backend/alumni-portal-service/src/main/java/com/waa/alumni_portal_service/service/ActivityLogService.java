package com.waa.alumni_portal_service.service;

import com.waa.alumni_portal_service.dto.ActivityLogDTO;
import com.waa.alumni_portal_service.dto.chart.EChartDTO;

import java.util.List;
import java.util.Optional;

public interface ActivityLogService {
    List<ActivityLogDTO> findAll();

    Optional<ActivityLogDTO> findById(Integer id);

    void save(ActivityLogDTO activityLogDTO);

    void deleteById(Integer id);

    EChartDTO activityCountByUser();

    EChartDTO activityCountByAction();

    EChartDTO activityCountByUserIn24();

    EChartDTO activityCountByActionIn24();

}
