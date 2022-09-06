package com.waa.alumni_portal_service.service;

import com.waa.alumni_portal_service.dto.OffensiveWordLogDTO;

import java.time.ZonedDateTime;
import java.util.List;

public interface OffensiveWordLogService {
    List<OffensiveWordLogDTO> findOffensiveWordLog(Integer userId, ZonedDateTime time);

    void logOffensiveWords(Integer userId, String word);
}
