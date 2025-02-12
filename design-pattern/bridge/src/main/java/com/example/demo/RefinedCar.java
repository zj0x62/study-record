package com.example.demo;

/**
 * @Author: zhoujing
 * @Date: 2025/2/7 10:17
 * @Description:
 */
public abstract class RefinedCar extends Car{

    public RefinedCar(Engine engine) {
        super(engine);
    }

    public void drive() {
        this.engine.start();
        System.out.println("Drive " + getBrand() + " car...");
    }

    public abstract String getBrand();
}
