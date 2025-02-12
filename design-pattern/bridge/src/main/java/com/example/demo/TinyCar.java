package com.example.demo;

/**
 * @Author: zhoujing
 * @Date: 2025/2/7 10:20
 * @Description:
 */
public class TinyCar extends RefinedCar {

    public TinyCar(Engine engine) {
        super(engine);
    }

    @Override
    public String getBrand() {
        return "Tiny";
    }
}
