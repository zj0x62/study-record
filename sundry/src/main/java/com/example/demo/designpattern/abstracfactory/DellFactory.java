package com.example.demo.designpattern.abstracfactory;

/**
 * @Author: zhoujing
 * @Date: 2024/5/28 19:25
 * @Description:
 */
public class DellFactory extends PcFactory{
    @Override
    public Mouse createMouse() {
        return new DellMouse();
    }

    @Override
    public Keybo createKeybo() {
        return new DellKeybo();
    }

    @Override
    public Mic createMic() {
        return new DellMic();
    }
}
