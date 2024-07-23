package com.example.demo;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @Author: zhoujing
 * @Date: 2024/7/19 10:18
 * @Description:
 */
@Component
@Slf4j
public class MyEventListener implements ApplicationListener<MyEvent> {

    @Override
    public void onApplicationEvent(MyEvent event) {
        if (Objects.isNull(event)) {
            return;
        }

        Task task = event.getTask();
        log.info("事件接收任务：{}", JSON.toJSONString(task));
        task.setFinish(true);
        log.info("完成任务：{}", JSON.toJSONString(task));
    }
}
