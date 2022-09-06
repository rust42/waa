package com.waa.alumni_portal_service.controller;

import com.waa.alumni_portal_service.dto.chart.EChartDTO;
import com.waa.alumni_portal_service.service.ActivityLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;

@RequiredArgsConstructor
@RestController
@RequestMapping("/activities")
@CrossOrigin
public class ActivityController {
    private final ActivityLogService activityLogService;

    @GetMapping("/echarts/activityCountByUser")
    @RolesAllowed({"FACULTY","STUDENT"})
    public EChartDTO activityCountByUser() {
        return activityLogService.activityCountByUser();
    }

    @GetMapping("/echarts/activityCountByAction")
    @RolesAllowed({"FACULTY","STUDENT"})
    public EChartDTO activityCountByAction() {
        return activityLogService.activityCountByAction();
    }

    @GetMapping("/echarts/activityCountByUserIn24")
    @RolesAllowed({"FACULTY","STUDENT"})
    public EChartDTO activityCountByUserIn24() {
        return activityLogService.activityCountByUserIn24();
    }

    @GetMapping("/echarts/activityCountByActionIn24")
    @RolesAllowed({"FACULTY","STUDENT"})
    public EChartDTO activityCountByActionIn24() {
        return activityLogService.activityCountByActionIn24();
    }
}
