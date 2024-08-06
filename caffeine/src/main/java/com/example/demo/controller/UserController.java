package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author: zhoujing
 * @Date: 2024/8/6 13:40
 * @Description:
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping("/add")
    public Object addUser(User user) {
        userService.addUser(user);
        return "SUCCESS";
    }

    @PostMapping("info")
    public Object getUserById(Integer id) {
        return userService.getUserById(id);
    }

    @PostMapping("/update")
    public Object updateUser(User user) {
        return userService.updateUser(user);
    }

    @PostMapping("/delete")
    public Object deleteUser(Integer id) {
        userService.deleteUser(id);
        return "SUCCESS";
    }
}
