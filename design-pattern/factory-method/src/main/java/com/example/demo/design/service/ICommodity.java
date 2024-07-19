package com.example.demo.design.service;

import java.util.Map;

/**
 * @Author: zhoujing
 * @Date: 2024/6/25 15:48
 * @Description:
 */
public interface ICommodity {

    void sendCommodity(String uId, String commodityId, String bizId, Map<String, String> extMap);
}
