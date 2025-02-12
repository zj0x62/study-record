package com.example.demo;

/**
 * @Author: zhoujing
 * @Date: 2025/2/10 10:53
 * @Description: 检查用户角色
 */
public class RoleCheckMiddleware extends Middleware {

    @Override
    public boolean check(String email, String password) {
        if (email.equals("admin@example.com")) {
            System.out.println("Hello, admin!");
            return true;
        }
        System.out.println("Hello, user!");
        return checkNext(email, password);
    }
}
