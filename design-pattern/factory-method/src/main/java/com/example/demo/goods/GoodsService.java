package com.example.demo.goods;

import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Service;

/**
 * @Author: zhoujing
 * @Date: 2024/6/25 15:29
 * @Description:
 */
@Service
public class GoodsService {

    public Boolean deliverGoods(DeliverReq req) {
        System.out.println("模拟发货实物商品一个：" + JSON.toJSONString(req));
        return true;
    }
}
