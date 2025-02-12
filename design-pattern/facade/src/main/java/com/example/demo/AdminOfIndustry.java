package com.example.demo;

/**
 * @Author: zhoujing
 * @Date: 2025/2/8 13:15
 * @Description:
 */
public class AdminOfIndustry {

    public Company register(String name) {
        Company company = new Company();
        company.setId("1");
        company.setName(name);
        System.out.println("工商注册：" + name);
        return company;
    }
}
