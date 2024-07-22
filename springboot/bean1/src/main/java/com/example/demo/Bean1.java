package com.example.demo;

import org.springframework.stereotype.Component;

/**
 * @Author: zhoujing
 * @Date: 2024/7/22 11:00
 * @Description:
 */
@Component
public class Bean1 implements Customizer{
    @Override
    public void customizer() {
        System.out.println("Bean11111111111111111111111");
    }
}
