package com.example.demo;

import java.math.BigDecimal;

/**
 * @Author: zhoujing
 * @Date: 2025/2/11 15:10
 * @Description: 优惠券折扣计算接口
 *               优惠券类型； 1. 直减券 2. 满减券 3. 折扣券 4. n元购
 */
public interface ICouponDiscount<T> {

    /**
     * 优惠券金额计算
     * @param couponInfo 券折扣信息；直减、满减、折扣、N元购
     * @param skuPrice   sku金额
     * @return           优惠后金额
     */
    BigDecimal discountAmount(T couponInfo, BigDecimal skuPrice);
}
