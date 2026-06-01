package com.example.demo;

import com.example.demo.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.lang.reflect.Proxy;

/**
 * @Author: zhoujing
 * @Date: 2026/2/28 14:19
 * @Description:
 */
@SpringBootApplication
public class AopDemoApplication implements CommandLineRunner {

    private final UserService userService;

    public AopDemoApplication(UserService userService) {
        this.userService = userService;
    }

    public static void main(String[] args) {
        SpringApplication.run(AopDemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("=====正常调用addUser方法=====");
        userService.addUser("张三");

        System.out.println("=====调用getUser方法（触发异常=====）");
        try {
            userService.getUser("error");
        } catch (Exception e) {
            // ignore
        }

        System.out.println(userService.getClass().getName());
        boolean proxyClass = Proxy.isProxyClass(userService.getClass());
        System.out.println(proxyClass);
    }
}
