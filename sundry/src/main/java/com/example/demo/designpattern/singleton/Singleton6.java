package com.example.demo.designpattern.singleton;

/**
 * @Author: zhoujing
 * @Date: 2022/11/23 11:24
 * @Description: 单例模式
 *  枚举实现
 *  这是单例模式的最佳实践，它实现简单，并且在面对复杂的序列化或者反射攻击的时候，能够防止实例化多次
 */
public enum Singleton6 {

    INSTANCE;

    public void whateverMethod() {

    }
}
