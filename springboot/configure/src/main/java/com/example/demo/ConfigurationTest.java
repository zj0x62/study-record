package com.example.demo;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author: zhoujing
 * @Date: 2024/7/23 14:36
 * @Description:
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ConfigurationApplication.class)
public class ConfigurationTest {

    @Autowired
    User user;

    @Test
    public void test() {
        String name = user.getName();
        int age = user.getAge();
        log.info(user.toString());
    }
}
