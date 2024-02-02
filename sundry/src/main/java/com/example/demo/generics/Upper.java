package com.example.demo.generics;

import lombok.Data;

/**
 * @Author: zhoujing
 * @Date: 2023/11/22 10:47
 * @Description: 泛型上限
 */
@Data
public class Upper<T extends Number> {

    private T var;
}
