package com.waa.alumni_portal_service.repository;

import com.waa.alumni_portal_service.dto.chart.ActivityChart;
import com.waa.alumni_portal_service.dto.chart.StudentPerState;
import com.waa.alumni_portal_service.entity.ActivityLog;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActivityLogRepository extends CrudRepository<ActivityLog, Integer> {

    @Query("SELECT new com.waa.alumni_portal_service.dto.chart.ActivityChart( s.user.id, COUNT(s.user.id) ) " +
            "from ActivityLog AS s GROUP BY s.user")
    List<ActivityChart> activityCountByUser();

    @Query("SELECT new com.waa.alumni_portal_service.dto.chart.ActivityChart( s.operation, COUNT(s.operation) ) " +
            "from ActivityLog AS s GROUP BY s.operation")
    List<ActivityChart> activityCountByAction();

    @Query("SELECT new com.waa.alumni_portal_service.dto.chart.ActivityChart( s.user.id, COUNT(s.user.id) ) " +
            "from ActivityLog AS s WHERE s.time > CURRENT_DATE GROUP BY s.user")
    List<ActivityChart> activityCountByUserIn24();

    @Query("SELECT new com.waa.alumni_portal_service.dto.chart.ActivityChart( s.operation, COUNT(s.operation) ) " +
            "from ActivityLog AS s WHERE s.time > CURRENT_DATE GROUP BY s.operation")
    List<ActivityChart> activityCountByActionIn24();
}