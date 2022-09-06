package com.waa.alumni_portal_service.service.impl;

import com.waa.alumni_portal_service.dto.OffensiveWordLogDTO;
import com.waa.alumni_portal_service.entity.OffensiveWordLog;
import com.waa.alumni_portal_service.entity.User;
import com.waa.alumni_portal_service.repository.OffensiveWordLogRepository;
import com.waa.alumni_portal_service.service.OffensiveWordLogService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OffensiveWordLogServiceImpl implements OffensiveWordLogService {

    private final ModelMapper modelMapper;
    private final OffensiveWordLogRepository offensiveWordLogRepository;

    @Override
    public List<OffensiveWordLogDTO> findOffensiveWordLog(Integer userId, ZonedDateTime time) {
        var result = new ArrayList<OffensiveWordLogDTO>();
        offensiveWordLogRepository.findByUserIdAndTimeAfter(userId, time)
            .forEach(item -> {
                result.add(modelMapper.map(item, OffensiveWordLogDTO.class));
            });
        return result;
    }

    @Override
    public void logOffensiveWords(Integer userId, String word) {
        OffensiveWordLog offensiveWordLog = new OffensiveWordLog();
        User user = new User();
        user.setId(userId);
        offensiveWordLog.setUser(user);
        offensiveWordLog.setWord(word);
        offensiveWordLogRepository.save(offensiveWordLog);
    }
}
