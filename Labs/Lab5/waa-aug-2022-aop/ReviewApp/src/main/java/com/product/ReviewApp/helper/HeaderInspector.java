package com.product.ReviewApp.helper;


import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import javax.servlet.http.HttpServletRequest;

@Component
@Aspect
public class HeaderInspector {

    @Autowired
    private HttpServletRequest request;

    @Pointcut("within(com.product.ReviewApp.service..*)")
    public void cuttingPoint() { }


    @Before("cuttingPoint()")
    public void inspectHeaders() {
        var method = request.getMethod();
        if (!method.equals("POST")) {
            return;
        }

        String header = request.getHeader("AOP-IS-AWESOME");
        if (header == null) {
            throw new AopIsAwesomeHeaderException();
        }
    }

}
