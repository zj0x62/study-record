package com.example.demo;

import com.example.demo.store.ICommodity;
import com.example.demo.store.impl.CardCommodityServiceImpl;
import com.example.demo.store.impl.CouponCommodityServiceImpl;
import com.example.demo.store.impl.GoodsCommodityServiceImpl;

/**
 * @Author: zhoujing
 * @Date: 2024/6/25 15:49
 * @Description:
 */
public class StoreFactory {

    public ICommodity getCommodityService(Integer commodityType) {
        if (commodityType == null) {
            return null;
        }
        switch (commodityType) {
            case 1:
                return new CouponCommodityServiceImpl();
            case 2:
                return new GoodsCommodityServiceImpl();
            case 3:
                return new CardCommodityServiceImpl();
            default:
                throw new RuntimeException("不存在的商品服务类型");
        }
    }
}
