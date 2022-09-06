package com.waa.alumni_portal_service.repository;

import com.waa.alumni_portal_service.entity.OffensiveWordLog;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;
import java.util.List;

@Repository
public interface OffensiveWordLogRepository extends CrudRepository<OffensiveWordLog, Integer> {
    List<OffensiveWordLog> findByUserIdAndTimeAfter(Integer userId, ZonedDateTime time);
}
