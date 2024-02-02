package com.example.demo.generics;

import lombok.Data;

/**
 * @Author: zhoujing
 * @Date: 2023/11/22 10:49
 * @Description: 泛型下限
 */
@Data
public class Lower<T> {

    private T var;
}
