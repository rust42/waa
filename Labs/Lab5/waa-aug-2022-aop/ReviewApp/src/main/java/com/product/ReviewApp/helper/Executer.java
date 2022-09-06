package com.product.ReviewApp.helper;


import com.product.ReviewApp.entity.ActivityLog;
import com.product.ReviewApp.repository.ActivityLogRepository;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Date;

@Aspect
@Component
public class Executer {

    private final ActivityLogRepository logRepo;

    Executer(ActivityLogRepository logRepo) {
        this.logRepo = logRepo;
    }

    @Pointcut("@annotation(com.product.ReviewApp.helper.ExecutionTime)")
    public void pointCut() { }


    @Around("pointCut()")
    public Object runtime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long end = System.currentTimeMillis();

        long duration = end - start;
        Date date = new Date();

        ActivityLog activityLog = new ActivityLog();
        activityLog.setDate(date);
        activityLog.setDuration(duration);
        activityLog.setOperation(joinPoint.toShortString());

        logRepo.save(activityLog);

        return result;
    }
}
