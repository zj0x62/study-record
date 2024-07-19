package com.example.demo.coupon;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: zhoujing
 * @Date: 2024/6/25 15:26
 * @Description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CouponResult {

    private String code;

    private String info;
}
