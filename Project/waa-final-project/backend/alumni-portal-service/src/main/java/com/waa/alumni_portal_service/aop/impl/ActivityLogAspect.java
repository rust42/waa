package com.waa.alumni_portal_service.aop.impl;

import com.waa.alumni_portal_service.dto.ActivityLogDTO;
import com.waa.alumni_portal_service.dto.UserDTO;
import com.waa.alumni_portal_service.security.SecurityUtil;
import com.waa.alumni_portal_service.service.ActivityLogService;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;

@Aspect
@Component
@RequiredArgsConstructor
public class ActivityLogAspect {
    Logger logger = LoggerFactory.getLogger(ActivityLogAspect.class);

    private final ActivityLogService activityLogService;
    private final SecurityUtil securityUtil;
    private final HttpServletRequest request;

    @Pointcut("@annotation(com.waa.alumni_portal_service.aop.ActivityLogger)")
    public void activityLoggerAnno(){}

    @Pointcut("within(com.waa.alumni_portal_service.controller.*)")
    public void allController(){}

    @Around("allController() || activityLoggerAnno()")
    public Object getExecutionTime(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        Object result = proceedingJoinPoint.proceed();
        long executionTime = System.currentTimeMillis() - start;
        String methodName = String.format( "%s.%s",
                proceedingJoinPoint.getSignature().getDeclaringTypeName(),
                proceedingJoinPoint.getSignature().getName());
        logger.info("Access method: " + methodName);
        String logInfo = getClientInfo(request);
        logActivity(methodName, executionTime, logInfo);
        return result;
    }

    @Async()
    public void logActivity(String methodName, long executionTime, String logInfo){
        ActivityLogDTO activityLogDTO = new ActivityLogDTO();
        activityLogDTO.setOperation(methodName);
        activityLogDTO.setDuration(executionTime);
        activityLogDTO.setInfo(logInfo);
        if(securityUtil.getCurrentUser() != null) {
            UserDTO user = new UserDTO();
            user.setId(securityUtil.getCurrentUser().getId());
            activityLogDTO.setUser(user);
        }
        activityLogService.save(activityLogDTO);
    }

    private String getClientInfo(HttpServletRequest request) {
        return request.getHeader("User-Agent");
    }

}
