package com.example.demo;

/**
 * @Author: zhoujing
 * @Date: 2025/2/8 10:11
 * @Description: 抽象基础装饰
 */
public class DataSourceDecorator implements DataSource {

    private DataSource wrappee;

    public DataSourceDecorator(DataSource dataSource) {
        this.wrappee = dataSource;
    }

    @Override
    public void writeData(String data) {
        wrappee.writeData(data);
    }

    @Override
    public String readData() {
        return wrappee.readData();
    }
}
