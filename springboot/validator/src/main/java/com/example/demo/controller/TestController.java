package com.example.demo.controller;

import com.example.demo.response.Response;
import com.example.demo.service.impl.TestService;
import com.example.demo.vo.ReqVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: zhoujing
 * @Date: 2025/2/24 14:31
 * @Description:
 */
@RestController
@RequestMapping(path = "/test")
public class TestController {

    @Autowired
    private TestService testService;

    @PostMapping("/enum")
    public Response<Boolean> test(@RequestBody @Validated ReqVO param) {
        testService.testEnum(param);
        return Response.success();
    }
}
