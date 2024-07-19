package com.example.demo;

import lombok.Data;

/**
 * @Author: zhoujing
 * @Date: 2024/7/19 10:15
 * @Description:
 */
@Data
public class Task {

    private Long id;

    private String taskName;

    private String taskContext;

    private boolean finish;
}
