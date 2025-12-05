package com.example.demo.service;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

/**
 * @Author: zhoujing
 * @Date: 2025/11/25 18:33
 * @Description:
 */
public class MyService implements InitializingBean {

    /**
     * 直接注入值
     */
    @Value("Some String Value")
    private String someString;

    /**
     * 从属性文件中注入值方式
     */
    @Value("${app.name}")
    private String appName;

    /**
     * 使用默认值方式
     */
    @Value("${app.description:我是默认值}")
    private String appDescription;

    /**
     * 注入列表和属性
     */
    @Value("#{'${app.servers}'.split(',')}")
    private List<String> servers;

    /**
     * 使用Spring的SpEL
     */
    @Value("#{${app.val1} + ${app.val2}}")
    private int sumOfValues;

    @Value("${myapp.names[0]}")
    private String firstName;

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("直接注入值：" + someString);
        System.out.println("从属性文件中注入值：" + appName);
        System.out.println("使用默认值：" + appDescription);
        System.out.println("注入列表和属性：" + servers);
        System.out.println("使用Spring的SpEL: " + sumOfValues);
        System.out.println("firstName: " + firstName);
    }
}
