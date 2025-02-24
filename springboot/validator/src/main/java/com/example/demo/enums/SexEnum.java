package com.example.demo.enums;

import com.example.demo.service.ArrayValuable;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @Author: zhoujing
 * @Date: 2025/2/24 14:16
 * @Description:
 */
@Getter
@AllArgsConstructor
public enum SexEnum implements ArrayValuable<Integer> {

    MALE(1),
    FEMALE(2),

    ;

    private final Integer code;

    private static final Integer[] ARRAYS = Arrays.stream(values()).map(SexEnum::getCode).collect(Collectors.toList()).toArray(new Integer[0]);

    @Override
    public Integer[] array() {
        return ARRAYS;
    }
}
