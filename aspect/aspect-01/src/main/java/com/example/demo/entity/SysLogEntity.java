package com.example.demo.entity;

import lombok.Data;

/**
 * @Author: zhoujing
 * @Date: 2023/11/20 13:59
 * @Description:
 */
@Data
public class SysLogEntity {

    private String className;

    private String methodName;

    private String params;

    private Long exeTime;

    private String remark;

    private String createDate;
}
