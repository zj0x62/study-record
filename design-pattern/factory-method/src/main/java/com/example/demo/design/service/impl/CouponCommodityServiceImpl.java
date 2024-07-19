package com.example.demo.design.service.impl;

import com.alibaba.fastjson.JSON;
import com.example.demo.coupon.CouponResult;
import com.example.demo.coupon.CouponService;
import com.example.demo.design.service.ICommodity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @Author: zhoujing
 * @Date: 2024/6/25 15:52
 * @Description:
 */
@Slf4j
@Service
public class CouponCommodityServiceImpl implements ICommodity {

    @Override
    public void sendCommodity(String uId, String commodityId, String bizId, Map<String, String> extMap) {
        CouponService couponService = new CouponService();
        CouponResult couponResult = couponService.sendCoupon(uId, commodityId, bizId);
        log.info("请求参数[优惠券] => uId：{} commodityId：{} bizId：{} extMap：{}", uId, commodityId, bizId, JSON.toJSON(extMap));
        log.info("测试结果[优惠券]：{}", JSON.toJSON(couponResult));
        if (!"0000".equals(couponResult.getCode())) {
            throw new RuntimeException(couponResult.getInfo());
        }
    }
}
