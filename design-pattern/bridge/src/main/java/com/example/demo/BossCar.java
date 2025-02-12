package com.example.demo;

/**
 * @Author: zhoujing
 * @Date: 2025/2/7 10:18
 * @Description:
 */
public class BossCar extends RefinedCar {

    public BossCar(Engine engine) {
        super(engine);
    }

    @Override
    public String getBrand() {
        return "Boss";
    }
}
