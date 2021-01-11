package com.sunchs.store.order.aop;

import com.sunchs.store.framework.bean.ResultData;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ExceptionAspect {

    @Pointcut(value = "execution(* com.sunchs.store.order.controller.*.*(..))")
    private void controllerException(){}

    @Around("controllerException()")
    public Object ddd(ProceedingJoinPoint joinPoint) {
        try {
            return joinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return ResultData.getFailure(throwable.getMessage());
        }
    }

}
