package com.example.demo.impl;

import com.example.demo.ICouponDiscount;

import java.math.BigDecimal;

/**
 * @Author: zhoujing
 * @Date: 2025/2/11 15:17
 * @Description: 直减
 */
public class ZJCouponDiscount implements ICouponDiscount<Double> {

    /**
     * 直减计算
     * 1. 使用商品价格减去优惠价格
     * 2. 最低支付金额1元
     */
    @Override
    public BigDecimal discountAmount(Double couponInfo, BigDecimal skuPrice) {
        BigDecimal discountAmount = skuPrice.subtract(new BigDecimal(couponInfo));
        if (discountAmount.compareTo(BigDecimal.ZERO) < 1) {
            return BigDecimal.ONE;
        }

        return discountAmount;
    }
}
