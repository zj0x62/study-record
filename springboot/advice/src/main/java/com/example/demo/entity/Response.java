package com.example.demo.entity;

import com.example.demo.enums.ResponseCode;
import lombok.Data;
import org.apache.poi.ss.formula.functions.T;
import org.slf4j.MDC;

/**
 * @Author: zhoujing
 * @Date: 2023/10/10 15:00
 * @Description:
 */
@Data
public class Response <T> {

    private int code;
    private String message;
    private T data;
    private String traceId = MDC.get("traceId");

    public static <T> Response<T> success() {
        return success(null);
    }

    public static <T> Response<T> success(T data) {
        return success(null, data);
    }

    public static <T> Response<T> success(String message, T data) {
        return response(ResponseCode.SUCCESS, message, data);
    }

    public static <T> Response<T> fail() {
        return response(ResponseCode.BUSINESS_ERROR, null);
    }

    public static <T> Response<T> fail(Throwable throwable) {
        return response(ResponseCode.BUSINESS_ERROR, throwable.getMessage(), null);
    }

    public static <T> Response<T> fail(String message) {
        return response(ResponseCode.BUSINESS_ERROR, message, null);
    }

    public static <T> Response<T> fail(ResponseCode code) {
        return response(code, null);
    }

    public static <T> Response<T> fail(ResponseCode code, String message) {
        return response(code, message, null);
    }

    private static <T> Response<T> response(ResponseCode code, T data) {
        return response(code, code.getMessage(), data);
    }

    private static <T> Response<T> response(ResponseCode code, String message, T data) {
        Response<T> result = new Response<T>();
        result.setCode(code.getCode());
        result.setMessage(message);
        result.setData(data);
        return result;
    }
}
