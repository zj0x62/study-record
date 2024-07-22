package com.example.demo;

import lombok.CustomLog;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author: zhoujing
 * @Date: 2024/7/22 11:25
 * @Description:
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = BeanApplication.class)
public class BeanTest {

    @Autowired
    Customizer customizer;

    @Test
    public void test() {
        customizer.customizer();
    }
}
