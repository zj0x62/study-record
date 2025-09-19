package com.example.demo.netty.case3;

/**
 * @Author: zhoujing
 * @Date: 2025/3/6 11:20
 * @Description:
 */
public class Client3 {

    public static void main(String[] args) throws Exception {
        new GroupChatClient("127.0.0.1", 6998).run();
    }
}
