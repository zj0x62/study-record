package com.example.demo.service;

import com.example.demo.entity.User;

/**
 * @Author: zhoujing
 * @Date: 2024/8/6 13:21
 * @Description:
 */
public interface UserService {

    void addUser(User user);

    User getUserById(Integer id);

    User updateUser(User user);

    void deleteUser(Integer id);
}
