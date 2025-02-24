package com.example.demo.enums;

import com.example.demo.service.ArrayValuable;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @Author: zhoujing
 * @Date: 2025/2/24 14:21
 * @Description:
 */
@Getter
@AllArgsConstructor
public enum ColorEnum implements ArrayValuable<String> {

    RED("red"),
    GREEN("green"),
    BLUE("blue"),
    ;

    private final String code;
    private static final String[] ARRAYS = Arrays.stream(values()).map(ColorEnum::getCode).collect(Collectors.toList()).toArray(new String[0]);

    @Override
    public String[] array() {
        return ARRAYS;
    }
}
