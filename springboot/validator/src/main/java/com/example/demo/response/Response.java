package com.example.demo.response;

import com.example.demo.enums.ResponseCode;
import lombok.Data;
import org.slf4j.MDC;

import java.io.Serializable;

/**
 * @Author: zhoujing
 * @Date: 2025/2/24 14:44
 * @Description:
 */
@Data
public class Response<T> implements Serializable {
    private int code;

    private T data;

    private String msg;

    private String traceId = MDC.get("traceId");

    public static <T> Response<T> success() {
        return success(null);
    }

    public static <T> Response<T> success(T data) {
        return success(data,null);
    }

    public static <T> Response<T> success(T data, String msg) {
        return response(ResponseCode.SUCCESS,data,msg);
    }

    public static <T> Response<T> failed() {
        return response(ResponseCode.BUSINESS_ERROR, null);
    }

    public static <T> Response<T> failed(String msg) {
        return response(ResponseCode.BUSINESS_ERROR, null, msg);
    }

    public static <T> Response<T> failed(Throwable ex) {
        return response(ResponseCode.BUSINESS_ERROR, null, ex.getMessage());
    }

    public static <T> Response<T> failed(ResponseCode code) {
        return response(code, null);
    }

    public static <T> Response<T> failed(ResponseCode code, String msg) {
        return response(code, null, msg);
    }

    private static <T> Response<T> response(ResponseCode code, T data) {
        return response(code,data,code.getMsg());
    }

    private static <T> Response<T> response(ResponseCode code, T data, String msg) {
        Response<T> result = new Response<>();
        result.setCode(code.getCode());
        result.setData(data);
        result.setMsg(msg);
        return result;
    }
}
