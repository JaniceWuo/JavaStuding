package com.utils;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * 用于记录日志的工具类
 */
@Component("logger")
@Aspect //表示当前类是一个切面类
public class Logger {
    @Pointcut("execution(* com.service.impl.*.*(..))")
    private void pt1(){

    }
    /**
     * 前置通知
     */
//    @Before("pt1()")
    public void beforePrintLog(){
        System.out.println("前置通知Logger类中的printLog方法开始记录日志了");
    }

    /**
     * 后置通知
     */
//    @AfterReturning("pt1()")
    public void afterReturningPrintLog(){
        System.out.println("后置通知Logger类中的afterReturningPrintLog方法开始记录日志了");
    }

    /**
     * 异常通知
     */
//    @AfterThrowing("pt1()")
    public void afterThrowingPrintLog(){
        System.out.println("异常通知Logger类中的afterThrowingPrintLog方法开始记录日志了");
    }

    /**
     * 最终通知
     */
//    @After("pt1()")
    public void afterPrintLog(){
        System.out.println("最终通知Logger类中的afterPrintLog方法开始记录日志了");
    }

    /**
     * 环绕通知（自己编码实现）
     */
    @Around("pt1()")
    public Object aroundPrintLog(ProceedingJoinPoint pjp){
        Object returnValue = null;
        try {
            Object[] args = pjp.getArgs();
            System.out.println("Logger类中的aroundPrintLog方法开始记录日志了 前置");
            returnValue = pjp.proceed(args);//明确调用切入点方法
            System.out.println("Logger类中的aroundPrintLog方法开始记录日志了 后置");
            return returnValue;
        } catch (Throwable e) {
            System.out.println("Logger类中的aroundPrintLog方法开始记录日志了 异常");
            throw new RuntimeException(e);
        } finally {
            System.out.println("Logger类中的aroundPrintLog方法开始记录日志了 最终");
        }
    }
}
