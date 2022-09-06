package com.waa.alumni_portal_service.security;

import com.waa.alumni_portal_service.dto.OffensiveWordLogDTO;
import com.waa.alumni_portal_service.service.OffensiveWordLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.List;

//@Component
@RequiredArgsConstructor
public class WaaRequestFilter extends OncePerRequestFilter {

    private final SecurityUtil securityUtil;
    private final Integer LIMIT_TIME_FRAME_MIN = 30;
    private final Integer LIMIT_DURATION_MIN = 15;
    private final Integer MAX_OFFENSIVE_WORD_COUNT = 5;
    private final String ERROR_MSG = "Max Bad Words Requests Limit has been Reached. You need wait for %d minutes.";
    private final OffensiveWordLogService offensiveWordLogService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        MyUserDetails userDetails = securityUtil.getCurrentUser();
        if (userDetails != null) {
            ZonedDateTime timeCountLimit = ZonedDateTime.now().minusMinutes(LIMIT_DURATION_MIN + LIMIT_TIME_FRAME_MIN);
            List<OffensiveWordLogDTO> offensiveWordLogDTOList =
                    offensiveWordLogService.findOffensiveWordLog(securityUtil.getCurrentUser().getId(), timeCountLimit);
            if (offensiveWordLogDTOList.size() > MAX_OFFENSIVE_WORD_COUNT) {
                ZonedDateTime lastTimeLog = offensiveWordLogDTOList.get(offensiveWordLogDTOList.size() - 1).getTime();
                Duration timeWait = Duration.between(lastTimeLog, ZonedDateTime.now());
                response.setStatus(HttpStatus.TOO_MANY_REQUESTS.value());
                response.getWriter().write(String.format(ERROR_MSG, LIMIT_DURATION_MIN - timeWait.toMinutes()));
                return;
            }
        }

        filterChain.doFilter(request, response);
    }
}
