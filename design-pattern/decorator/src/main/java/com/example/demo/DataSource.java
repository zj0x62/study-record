package com.example.demo;

/**
 * @Author: zhoujing
 * @Date: 2025/2/8 10:05
 * @Description:
 */
public interface DataSource {

    void writeData(String data);

    String readData();
}
