package com.example.demo.coupon;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @Author: zhoujing
 * @Date: 2024/6/25 15:26
 * @Description:
 */
@Component
public class CouponService {

    public CouponResult sendCoupon(String uId, String couponNumber, String uuid) {
        System.out.println("模拟发放优惠券一张：" + uId + ", " + couponNumber + ", " + uId);
        return new CouponResult("0000", "发放成功");
    }
}
