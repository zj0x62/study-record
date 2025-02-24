package com.example.demo.service.impl;

import com.example.demo.vo.ReqVO;
import org.springframework.stereotype.Service;

/**
 * @Author: zhoujing
 * @Date: 2025/2/24 14:30
 * @Description:
 */
@Service
public class TestServiceImpl implements TestService{


    @Override
    public void testEnum(ReqVO param) {
        System.out.println("=============");
    }
}
