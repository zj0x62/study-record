package com.example.demo.enums;

import lombok.Getter;

/**
 * @Author: zhoujing
 * @Date: 2023/10/10 14:34
 * @Description:
 */
@Getter
public enum ResponseCode {

    SUCCESS(200, "操作成功"),
    Fail(500, "操作失败"),
    BUSINESS_ERROR(501, "业务验证出现错误"),
    ;

    private final Integer code;

    private final String message;

    ResponseCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
