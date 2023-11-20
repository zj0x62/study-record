package com.example.demo.aspect;

import cn.hutool.core.date.DateUtil;
import com.example.demo.annotation.SysLog;
import com.example.demo.entity.SysLogEntity;
import com.example.demo.service.SysLogService;
import com.google.gson.Gson;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author: zhoujing
 * @Date: 2023/11/20 14:03
 * @Description: 系统日志切面
 */
@Aspect
@Component
public class SysLogAspect {

    @Autowired
    private SysLogService sysLogService;

    @Pointcut("@annotation(com.example.demo.annotation.SysLog)")
    public void logPointCut() {}

    @Around("logPointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        long t1 = System.currentTimeMillis();
        Object result = point.proceed();
        long exeTime = System.currentTimeMillis() - t1;
        try {
            saveLog(point, exeTime);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    private void saveLog(ProceedingJoinPoint joinPoint, long time) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        SysLogEntity sysLog = new SysLogEntity();
        sysLog.setExeTime(time);
        sysLog.setCreateDate(DateUtil.formatDateTime(new Date()));
        SysLog annotation = method.getAnnotation(SysLog.class);
        if (annotation != null) {
            // 注解上的描述
            sysLog.setRemark(annotation.value());
        }
        // 请求的类名、方法名
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = signature.getName();
        sysLog.setClassName(className);
        sysLog.setMethodName(methodName);
        // 请求的参数
        Object[] args = joinPoint.getArgs();
        try {
            List<String> list = new ArrayList<>();
            for (Object arg : args) {
                list.add(new Gson().toJson(arg));
            }
            sysLog.setParams(list.toString());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        sysLogService.save(sysLog);
    }
}
