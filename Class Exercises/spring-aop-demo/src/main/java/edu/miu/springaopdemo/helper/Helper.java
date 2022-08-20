package edu.miu.springaopdemo.helper;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class Helper {


    @Pointcut("@annotation(edu.miu.springaopdemo.helper.HelloWorld)")
    public void helloWorld() {}

    @Before("helloWorld()")
    public void beforePrint(JoinPoint joinPoint) {
        System.out.println("Printing before helper");
    }

    @After("@annotation(edu.miu.springaopdemo.helper.HelloWorld)")
    public void afterPrint(JoinPoint joinPoint) {
        System.out.println("Printing after helper");
    }

}
