package com.example.demo.store.impl;

import com.alibaba.fastjson.JSON;
import com.example.demo.card.IQiYiCardService;
import com.example.demo.store.ICommodity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @Author: zhoujing
 * @Date: 2024/6/25 15:50
 * @Description:
 */
@Slf4j
@Service
public class CardCommodityServiceImpl implements ICommodity {

    @Override
    public void sendCommodity(String uId, String commodityId, String bizId, Map<String, String> extMap) {
        IQiYiCardService iQiYiCardService = new IQiYiCardService();
        String mobile = queryUserMobile(uId);
        iQiYiCardService.grantToken(mobile, bizId);
        log.info("请求参数[爱奇艺兑换卡] => uId：{} commodityId：{} bizId：{} extMap：{}", uId, commodityId, bizId, JSON.toJSON(extMap));
        log.info("测试结果[爱奇艺兑换卡]：success");
    }

    private String queryUserMobile(String uId) {
        return "15200101232";
    }
}
