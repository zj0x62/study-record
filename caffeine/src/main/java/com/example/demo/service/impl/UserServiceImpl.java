package com.example.demo.service.impl;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: zhoujing
 * @Date: 2024/8/6 13:23
 * @Description: spring caffeine
 */
@Slf4j
@Service
@CacheConfig(cacheNames = "caffeineCacheManager")
public class UserServiceImpl implements UserService {

    private static final Map<Integer, User> userMap = new HashMap<>();

    /**
     * @CachePut
     * 不管缓存有没有，都将方法的返回结果写入缓存；适用于缓存更新
     */
    @CachePut(value = "user", key = "#user.id")
    @Override
    public void addUser(User user) {
        log.info("create user: {}", user);
        userMap.put(user.getId(), user);
    }

    /**
     * @Cacheable
     * 这个注解用于修饰方法或类，当我们访问它修饰的方法时，优先从缓存中获取，若缓存中存在，则直接获取缓存的值；缓存不存在时，执行方法，并将结果写入缓存。
     */
    @Cacheable(value = "user", key = "#id")
    @Override
    public User getUserById(Integer id) {
        log.info("get user, id: {}", id);
        return userMap.get(id);
    }

    @CachePut(value = "user", key = "#user.id")
    @Override
    public User updateUser(User user) {
        log.info("update user: {}", user);
        if (!userMap.containsKey(user.getId())) {
            return null;
        }
        User oldUser = userMap.get(user.getId());
        oldUser.setName(user.getName());
        userMap.put(user.getId(), oldUser);

        return oldUser;
    }

    /**
     * @CacheEvict
     * 删除缓存
     */
    @CacheEvict(value = "user", key = "#id")
    @Override
    public void deleteUser(Integer id) {
        log.info("delete user, id: {}", id);
        userMap.remove(id);
    }
}
