package com.example.demo.goods;

import lombok.Data;

/**
 * @Author: zhoujing
 * @Date: 2024/6/25 15:28
 * @Description:
 */
@Data
public class DeliverReq {

    private String userName;              // 用户姓名
    private String userPhone;             // 用户手机
    private String sku;                   // 商品SKU
    private String orderId;               // 订单ID
    private String consigneeUserName;     // 收货人姓名
    private String consigneeUserPhone;    // 收货人手机
    private String consigneeUserAddress;  // 收获人地址
}
