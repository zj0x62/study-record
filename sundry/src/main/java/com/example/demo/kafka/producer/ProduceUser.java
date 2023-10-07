package com.example.demo.kafka.producer;

import lombok.Data;
import lombok.ToString;

/**
 * @Author: zhoujing
 * @Date: 2023/8/14 16:28
 * @Description:
 */
@Data
@ToString
public class ProduceUser {

    private String name;

    private Integer age;
}
