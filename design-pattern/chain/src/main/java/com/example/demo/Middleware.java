package com.example.demo;

/**
 * @Author: zhoujing
 * @Date: 2025/2/10 10:44
 * @Description: 基础验证接口
 */
public abstract class Middleware {

    private Middleware next;

    public static Middleware link(Middleware first, Middleware... chain) {
        Middleware head = first;
        for (Middleware nextChain : chain) {
            head.next = nextChain;
            head = nextChain;
        }
        return first;
    }

    public abstract boolean check(String email, String password);

    protected boolean checkNext(String email, String password) {
        if (next == null) {
            return true;
        }
        return next.check(email, password);
    }
}
