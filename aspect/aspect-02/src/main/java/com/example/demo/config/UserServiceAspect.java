package com.example.demo.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @Author: zhoujing
 * @Date: 2026/2/28 14:02
 * @Description:
 */
@Aspect
@Component
public class UserServiceAspect {

    @Pointcut("execution(* com.example.demo.service.UserService.*(..))")
    public void userServicePointCut() {

    }

    @Before("userServicePointCut()")
    public void beforeAdvice(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        System.out.println("[前置通知]方法" + methodName + "开始执行， 入参：" + args[0]);
    }

    @AfterReturning(pointcut = "userServicePointCut()", returning = "result")
    public void afterReturningAdvice(JoinPoint joinPoint, Object result) {
        String methodName = joinPoint.getSignature().getName();
        System.out.println("[返回后通知]方法" + methodName + "执行成功，返回值：" + result);
    }

    @AfterThrowing(pointcut = "userServicePointCut()", throwing = "ex")
    public void afterThrowingAdvice(JoinPoint joinPoint, Exception ex) {
        String methodName = joinPoint.getSignature().getName();
        System.out.println("[抛出后通知]方法" + methodName + "执行异常，异常信息：" + ex.getMessage());
    }

    @After("userServicePointCut()")
    public void afterAdvice(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        System.out.println("[最终通知]方法" + methodName + "执行结束（无论是否异常）");
    }

    @Around("userServicePointCut()")
    public Object aroundAdvice(ProceedingJoinPoint pjp) throws Throwable {
        long startTime = System.currentTimeMillis();
        try {
            Object result = pjp.proceed();
            long costTime = System.currentTimeMillis() - startTime;
            System.out.println("[环绕通知]方法" + pjp.getSignature().getName() + "执行耗时：" + costTime + "ms");
            return result;
        } catch (Throwable e) {
            System.out.println("[环绕通知]方法" + pjp.getSignature().getName() + "执行异常，已捕获");
            throw e;
        }
    }
}
