package com.example.demo.controller;

import com.example.demo.annotation.SysLog;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: zhoujing
 * @Date: 2023/11/20 14:21
 * @Description:
 */
@RestController
public class TestController {

    @SysLog("测试")
    @GetMapping("/test")
    public String test(@RequestParam("name") String name) {
        return name;
    }
}
