package com.example.aop.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executors;

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
    @Before("myPointA()")
    public void methodBefore(){
            System.out.println("======methodBefore======");
    }

    /**
     * 切点方法无论是否出现异常，都会执行的方法 最终通知
     */
    @After("myPointA()")
    public void methodAfter(JoinPoint joinPoint){
        System.out.println("======methodAfter======");
    }

    /**
     * 若出现异常，不执行
     * @param joinPoint
     * @param res
     */
    @AfterReturning(value = "myPointA()",returning = "res")
    public void methodAfterReturning(JoinPoint joinPoint,Object res){
        System.out.println("methodAfterReturning......");
        System.out.println(res);
    }
    @AfterThrowing(value = "myPointA()",throwing = "ex")
    public void methodAfterThrowing(Exception ex){
        System.out.println("methodAfterThrowing......");
        System.out.println(ex);
    }




}
