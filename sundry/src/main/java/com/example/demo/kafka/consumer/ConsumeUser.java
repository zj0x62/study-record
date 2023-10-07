package com.example.demo.kafka.consumer;

import lombok.Data;
import lombok.ToString;

/**
 * @Author: zhoujing
 * @Date: 2023/8/14 16:38
 * @Description:
 */
@Data
@ToString
public class ConsumeUser {

    private String name;

    private Integer age;
}
