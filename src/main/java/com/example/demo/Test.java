package com.example.demo;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: zhoujing
 * @Date: 2023/6/28 9:58
 * @Description:
 */
public class Test {

    public static void main(String[] args) {
        Map<String,String> map = new HashMap<>();
        for (int i = 0;i < 500;i++) {
            new Thread(() -> {
                for (int j = 0;j < 500;j++) {
                    map.put(Thread.currentThread().getName() + "-" + j, j+"");
                }
            }).start();
        }
        try {
            Thread.sleep(2000);
//            map.forEach((x,y) -> System.out.println(x));
            System.out.println(map.size());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
