package com.example.demo.designpattern.abstracfactory;

/**
 * @Author: zhoujing
 * @Date: 2024/5/28 19:26
 * @Description:
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("===========惠普工厂=============");
        PcFactory factory = new HpFactory();
        Mouse mouse = factory.createMouse();
        mouse.sayHi();
        Keybo keybo = factory.createKeybo();
        keybo.sayHi();
        Mic mic = factory.createMic();
        mic.sayHi();

        System.out.println("===========戴尔工厂=============");
        PcFactory dellFactory = new DellFactory();
        Mouse dellMouse = dellFactory.createMouse();
        dellMouse.sayHi();
        Keybo dellKeybo = dellFactory.createKeybo();
        dellKeybo.sayHi();
        Mic dellMic = dellFactory.createMic();
        dellMic.sayHi();
    }
}
