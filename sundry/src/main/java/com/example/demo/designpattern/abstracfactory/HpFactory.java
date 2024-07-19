package com.example.demo.designpattern.abstracfactory;

/**
 * @Author: zhoujing
 * @Date: 2024/5/28 19:24
 * @Description:
 */
public class HpFactory extends PcFactory{
    @Override
    public Mouse createMouse() {
        return new HpMouse();
    }

    @Override
    public Keybo createKeybo() {
        return new HpKeybo();
    }

    @Override
    public Mic createMic() {
        return new HpMic();
    }
}
