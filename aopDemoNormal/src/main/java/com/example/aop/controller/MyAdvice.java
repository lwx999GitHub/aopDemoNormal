package com.example.aop.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyAdvice {
private Logger logger= LoggerFactory.getLogger(MyAdvice.class);

@Pointcut(value = "execution(* com.example.aop.controller.*.*(..))")
public void myPointA(){

}

@Around("myPointA()")
public Object mylogger(ProceedingJoinPoint pjp) throws Throwable {

    String className=pjp.getTarget().getClass().toString();
    String methodName=pjp.getSignature().getName();
    Object[] array=pjp.getArgs();

    ObjectMapper mapper=new ObjectMapper();

    logger.info("调用前:"+className+";"+methodName+"传递参数为："+mapper.writeValueAsString(array));
    Object obj = pjp.proceed();
    logger.info("调用后:"+className+";"+methodName+"返回值为："+mapper.writeValueAsString(obj));
    return obj;
}


}
