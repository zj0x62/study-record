package com.example.demo;

/**
 * @Author: zhoujing
 * @Date: 2025/2/7 10:19
 * @Description:
 */
public class BigCar extends RefinedCar {

    public BigCar(Engine engine) {
        super(engine);
    }

    @Override
    public String getBrand() {
        return "Big";
    }
}
