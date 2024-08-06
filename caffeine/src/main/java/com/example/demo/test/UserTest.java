package com.example.demo.test;

import com.example.demo.CaffeineApplication;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @Author: zhoujing
 * @Date: 2024/8/6 13:31
 * @Description:
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CaffeineApplication.class)
public class UserTest {

    @Resource
    private UserService userService;

    @Test
    public void addUserTest() {
        User user = User.builder().id(1).name("add").createTime(new Date()).build();
        userService.addUser(user);
    }

    @Test
    public void getUserByIdTest() {
        User user = userService.getUserById(1);

        user = userService.getUserById(1);
        user = userService.getUserById(1);

        user = User.builder().id(1).name("add").createTime(new Date()).build();
        userService.addUser(user);

        user = userService.getUserById(1);
        user = userService.getUserById(1);
    }

    @Test
    public void updateUserTest() {
        User user = User.builder().id(1).name("update").createTime(new Date()).build();
        userService.updateUser(user);
    }

    @Test
    public void deleteUserTest() {
        userService.deleteUser(1);
    }
}
