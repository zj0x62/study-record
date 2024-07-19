package com.example.leaf.snowflake.exception;

/**
 * @Author: zhoujing
 * @Date: 2024/7/18 10:38
 * @Description:
 */
public class ClockGoBackException extends RuntimeException {

    public ClockGoBackException(String msg) {
        super(msg);
    }
}
