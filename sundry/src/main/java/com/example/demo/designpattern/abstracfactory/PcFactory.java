package com.example.demo.designpattern.abstracfactory;

/**
 * @Author: zhoujing
 * @Date: 2024/5/28 19:19
 * @Description:
 */
public abstract class PcFactory {

    public abstract Mouse createMouse();

    public abstract Keybo createKeybo();

    public abstract Mic createMic();
}
