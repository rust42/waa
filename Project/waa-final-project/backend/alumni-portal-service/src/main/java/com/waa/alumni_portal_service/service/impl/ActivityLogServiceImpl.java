package com.waa.alumni_portal_service.service.impl;

import com.waa.alumni_portal_service.dto.ActivityLogDTO;
import com.waa.alumni_portal_service.dto.chart.ActivityChart;
import com.waa.alumni_portal_service.dto.chart.EChartDTO;
import com.waa.alumni_portal_service.entity.ActivityLog;
import com.waa.alumni_portal_service.repository.ActivityLogRepository;
import com.waa.alumni_portal_service.service.ActivityLogService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ActivityLogServiceImpl implements ActivityLogService {
    private final ModelMapper modelMapper;
    private final ActivityLogRepository activityLogRepository;

    @Override
    public List<ActivityLogDTO> findAll() {
        var result = new ArrayList<ActivityLogDTO>();
        activityLogRepository.findAll().forEach(item -> {
            result.add(modelMapper.map(item, ActivityLogDTO.class));
        });
        return result;
    }

    @Override
    public Optional<ActivityLogDTO> findById(Integer id) {
        return activityLogRepository.findById(id).map(item -> modelMapper.map(item, ActivityLogDTO.class));
    }

    @Override
    public void save(ActivityLogDTO activityLogDTO) {
        activityLogRepository.save(modelMapper.map(activityLogDTO, ActivityLog.class));
    }

    @Override
    public void deleteById(Integer id) {
        activityLogRepository.deleteById(id);
    }

    @Override
    public EChartDTO activityCountByUser() {
        var data = activityLogRepository.activityCountByUser();
        return parseUser(data);
    }

    @Override
    public EChartDTO activityCountByAction() {
        var data = activityLogRepository.activityCountByAction();
        return parse(data);
    }

    @Override
    public EChartDTO activityCountByUserIn24() {
        var data = activityLogRepository.activityCountByUserIn24();
        return parseUser(data);
    }

    @Override
    public EChartDTO activityCountByActionIn24() {
        var data = activityLogRepository.activityCountByActionIn24();
        return parse(data);
    }

    public EChartDTO parseUser(List<ActivityChart> data) {
        var names = new ArrayList<String>();
        var values = new ArrayList<Long>();
        for (ActivityChart item : data) {
            names.add(item.getName().toString());
            values.add(item.getValue());
        }
        return new EChartDTO(names, values);
    }

    public EChartDTO parse(List<ActivityChart> data) {
        var names = new ArrayList<String>();
        var values = new ArrayList<Long>();
        for (ActivityChart item : data) {
            String temptData = (String) item.getName();
            if(temptData == null){
                temptData = "Anonymous";
            }
            String[] tempt = temptData.split("\\.");
            names.add(tempt[tempt.length - 1]);
            values.add(item.getValue());
        }
        return new EChartDTO(names, values);
    }
}
