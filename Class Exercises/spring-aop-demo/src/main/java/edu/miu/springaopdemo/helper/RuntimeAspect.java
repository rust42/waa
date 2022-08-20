package edu.miu.springaopdemo.helper;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class RuntimeAspect {

    @Pointcut("@annotation(edu.miu.springaopdemo.helper.ExecutionTime)")
    public void runtime() {}

    @Around("runtime()")
    public Object calculate(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        // call the method

        Object object = proceedingJoinPoint.proceed();
        long end = System.currentTimeMillis();
        System.out.println("Execution time is " + (end - start));
        // return the result
        return object;
    }
}
