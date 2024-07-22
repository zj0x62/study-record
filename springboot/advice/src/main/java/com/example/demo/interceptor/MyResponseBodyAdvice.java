package com.example.demo.interceptor;


import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.DigestUtil;
import cn.hutool.crypto.digest.MD5;
import com.alibaba.fastjson.JSON;
import com.example.demo.annotation.SignAdvice;
import com.example.demo.entity.Response;
import com.example.demo.entity.Sign;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Objects;

/**
 * @Author: zhoujing
 * @Date: 2023/10/10 14:11
 * @Description:
 */
@Slf4j
@ControllerAdvice // 此处可以限制需要拦截的包路径，不填写为全部拦截进入supports 方法验证
public class MyResponseBodyAdvice implements ResponseBodyAdvice<Response> {

    private static final String SIGN_KEY = "sign_key";

    /**
     * 该方法可以用于制定准入规则，只有当该方法返回True时，才会进入beforeBodyWrite方法
     */
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        //根据自定义注解判断方法上是否有指定需要拦截，当然这里也可以什么都不写直接返回true，根据自身业务场景做限制校验即可
        Method method = returnType.getMethod();
        SignAdvice annotation = method.getAnnotation(SignAdvice.class);
        return Objects.nonNull(annotation);
    }

    @Override
    public Response beforeBodyWrite(Response body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        if (body == null) {
            return null;
        }
        log.info("未加签名前的Response： {}", JSON.toJSONString(body));
        Response<Sign> sign = body;
        sign.setData(new Sign());
        sign.getData().setDaTime(System.currentTimeMillis());

        StringBuilder sb = new StringBuilder();
        SignAdvice signAdvice = returnType.getMethod().getAnnotation(SignAdvice.class);
        sb.append(sign.getCode())
                .append(sign.getData().getDaTime())
                .append(sign.getMessage())
                .append(JSON.toJSONString(sign.getData()))
                .append(StrUtil.isBlank(signAdvice.signKey()) ? SIGN_KEY : signAdvice.signKey());
        sign.getData().setSign(Arrays.toString(DigestUtil.md5(sb.toString())));
        log.info("添加签名后的Response:  {}",JSON.toJSONString(sign));
        return sign;
    }
}
