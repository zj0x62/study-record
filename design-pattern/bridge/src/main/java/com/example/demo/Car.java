package com.example.demo;

/**
 * @Author: zhoujing
 * @Date: 2025/2/7 10:15
 * @Description:
 */
public abstract class Car {

    protected Engine engine;

    public Car(Engine engine) {
        this.engine = engine;
    }

    public abstract void drive();
}
