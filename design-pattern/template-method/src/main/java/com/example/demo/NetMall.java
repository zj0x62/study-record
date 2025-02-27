package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * @Author: zhoujing
 * @Date: 2025/2/11 16:39
 * @Description: 模板基类：基础电商推广服务
 *                  1. 生成最优价商品海报
 *                  2. 海报含带推广邀请码
 */
public abstract class NetMall {

    protected Logger logger = LoggerFactory.getLogger(NetMall.class);

    String uId;
    String uPwd;

    public NetMall(String uId, String uPwd) {
        this.uId = uId;
        this.uPwd = uPwd;
    }

    /**
     * 生成商品推广海报
     *
     * @param skuUrl 商品地址(京东、淘宝、当当)
     * @return 海报图片base64位信息
     */
    public String generateGoodsPoster(String skuUrl) {
        // 1. 验证登录
        if (!login(uId, uPwd)) {
            return null;
        }
        // 2. 爬虫商品
        Map<String, String> reptile = reptile(skuUrl);
        // 3. 组装海报
        return createBase64(reptile);
    }

    /**
     * 模拟登录
     */
    protected abstract Boolean login(String uId, String uPwd);

    /**
     * 爬虫提取商品信息(登录后的优惠价格)
     */
    protected abstract Map<String, String> reptile(String skuUrl);

    /**
     * 生成商品海报信息
     */
    protected abstract String createBase64(Map<String, String> goodsInfo);
}
