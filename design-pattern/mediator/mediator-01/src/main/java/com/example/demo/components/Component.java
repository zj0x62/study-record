package com.example.demo.components;

import com.example.demo.mediator.Mediator;

/**
 * @Author: zhoujing
 * @Date: 2025/2/10 16:51
 * @Description:
 */
public interface Component {

    void setMediator(Mediator mediator);

    String getName();
}
