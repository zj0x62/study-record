package com.example.demo.card;

import org.springframework.stereotype.Service;

/**
 * @Author: zhoujing
 * @Date: 2024/6/25 15:24
 * @Description:
 */
@Service
public class IQiYiCardService {

    public void grantToken(String bindMobileNumber, String cardId) {
        System.out.println("模拟发放爱奇艺会员卡一张：" + bindMobileNumber + ", " + cardId);
    }
}
