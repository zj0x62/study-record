package com.example.demo;

import lombok.Data;

import java.util.Map;

/**
 * @Author: zhoujing
 * @Date: 2024/6/25 15:14
 * @Description:
 */
@Data
public class AwardReq {

    /**
     * 用户唯一id
     */
    private String uId;

    /**
     * 奖品类型（1：优惠券，2：事物商品，3：第三方兑换卡）
     */
    private Integer awardType;

    /**
     * 奖品编号
     */
    private String awardNumber;

    /**
     * 业务id
     */
    private String bizId;

    /**
     * 扩展信息
     */
    private Map<String, String> extMap;
}
