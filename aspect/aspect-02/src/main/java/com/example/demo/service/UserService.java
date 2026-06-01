package com.example.demo.service;

import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @Author: zhoujing
 * @Date: 2026/2/28 13:58
 * @Description:
 */
@Component
public class UserService {

    public String addUser(String username) {
        System.out.println("执行核心业务：添加用户 " + username);
        return "添加成功：" + username;
    }

    public String getUser(String username) {
        System.out.println("执行核心业务：查询用户 " + username);
        if (Objects.equals("error", username)) {
            throw new RuntimeException("用户查询失败");
        }

        return "查询成功 " + username;
    }
}
