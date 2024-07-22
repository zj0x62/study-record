package com.example.demo;

import org.springframework.stereotype.Component;

/**
 * @Author: zhoujing
 * @Date: 2024/7/22 11:00
 * @Description:
 */
public class Bean2 implements Customizer{
    @Override
    public void customizer() {
        System.out.println("Bean2222222222222222222222");
    }
}
