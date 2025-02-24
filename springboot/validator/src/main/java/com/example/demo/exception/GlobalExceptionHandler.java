package com.example.demo.exception;

import com.example.demo.enums.ResponseCode;
import com.example.demo.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author: zhoujing
 * @Date: 2025/2/24 14:41
 * @Description:
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Response MethodArgumentNotValidExceptionResponse(MethodArgumentNotValidException exception, HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        List<FieldError> allErrors = exception.getFieldErrors();
        StringBuilder sb = new StringBuilder();
        for (FieldError errorMessage : allErrors) {
            sb.append(errorMessage.getField()).append(": ").append(errorMessage.getDefaultMessage()).append(", ");
        }

        log.error("请求地址'{}',MethodArgumentNotValidException: '{}'", requestURI, sb.toString());
        return Response.failed(ResponseCode.PARAM_ERROR, sb.toString());
    }

    @ExceptionHandler(Exception.class)
    public Response BusinessExceptionResponse(Exception exception, HttpServletRequest request) {
        System.out.println(exception.getClass().getName());
        String requestURI = request.getRequestURI();
        log.error("请求地址'{}',发生错误：'{}'", requestURI, exception.getMessage());

        return Response.failed(ResponseCode.BUSINESS_ERROR, exception.getMessage());
    }
}
