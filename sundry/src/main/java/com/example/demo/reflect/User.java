package com.example.demo.reflect;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @Author: zhoujing
 * @Date: 2023/11/27 13:53
 * @Description:
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private String init = "init";

    private int age;
}
