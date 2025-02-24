package com.example.demo.vo;

import com.example.demo.enums.ColorEnum;
import com.example.demo.annotation.InEnum;
import com.example.demo.enums.SexEnum;
import lombok.Data;

import java.util.List;

/**
 * @Author: zhoujing
 * @Date: 2025/2/24 14:23
 * @Description:
 */
@Data
public class ReqVO {

    @InEnum(value = SexEnum.class, message = "性别必须在{value}范围内")
    private Integer sex;

    @InEnum(value = ColorEnum.class, message = "颜色必须在{value}范围内")
    private String color;

    @InEnum(value = SexEnum.class, message = "性别2必须在{value}范围内")
    private List<Integer> sexs;

    @InEnum(value = ColorEnum.class, message = "颜色2必须在{value}范围内")
    private List<String> colors;
}
