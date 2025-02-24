package com.example.demo.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.io.Serializable;

/**
 * @Author: zhoujing
 * @Date: 2025/2/24 14:45
 * @Description:
 */
public enum ResponseCode implements Serializable {

    /** 成功 **/
    SUCCESS(200, "成功!"),

    PARAM_ERROR(400, "用户请求参数错误"),                   //请求参数错误
    TOKEN_INVALID_OR_EXPIRED(401, "token错误或不合法"),     //无效认证信息
    ACCESS_UNAUTHORIZED_ERROR(403, "未授权操作"),                 //接口未授权
    INTERFACE_NOT_FOUND(404, "接口/方法不存在"),            //接口不存在
    METHOD_NOT_SUPPORT(405, "请求方式错误"),                //请求方式错误
    INNER_INTERFACE_ERROR(406, "请求错误，内部接口"),        //请求方式错误
    AUTHENTICATION_FAILED(422, "未登录"),                   //未携带认证信息

    UNEXPECT_ERROR(500, "系统执行出错"),                     //系统异常
    BUSINESS_ERROR(501, "业务验证出现错误"),                 //可以预见的错误，例如：账户冻结、账户停用，账户不存在、用户名或密码错误
    EXECUTION_TIMEOUT(502, "系统执行超时"),                  //执行超时
    FLOW_LIMITING(503, "系统繁忙，请稍后再试"),               //系统限流
    DEGRADATION(504, "系统功能降级"),                     //接口降级
    ACCESS_FORBIDDEN_ERROR(403, "系统检测到潜在的安全风险，请修改您的输入内容。");               //接口未授权
    private int code;
    private String msg;

    ResponseCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @JsonValue
    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    @JsonCreator
    public static ResponseCode getValue(int code){
        for (ResponseCode value : values()) {
            if (value.getCode() == code) {
                return value;
            }
        }
        return UNEXPECT_ERROR; // 默认系统执行错误
    }

    @Override
    public String toString() {
        return "{" +
                "\"code\":\"" + code + '\"' +
                ", \"msg\":\"" + msg + '\"' +
                '}';
    }
}