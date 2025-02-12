package com.example.demo.impl;

import com.example.demo.ICouponDiscount;

import java.math.BigDecimal;

/**
 * @Author: zhoujing
 * @Date: 2025/2/11 15:16
 * @Description: n元购买
 */
public class NYGCouponDiscount implements ICouponDiscount<Double> {

    /**
     * n元购购买
     * 1. 无论原价多少钱都固定金额购买
     */
    @Override
    public BigDecimal discountAmount(Double couponInfo, BigDecimal skuPrice) {
        return new BigDecimal(couponInfo);
    }
}
