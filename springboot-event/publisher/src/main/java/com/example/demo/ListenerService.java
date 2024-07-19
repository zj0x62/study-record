package com.example.demo;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @Author: zhoujing
 * @Date: 2024/7/19 11:13
 * @Description:
 */
@Slf4j
@Service
public class ListenerService {

    @Async
    @EventListener
    public void listen(MyEvent event) {
        if (Objects.isNull(event)) {
            return;
        }

        Task task = event.getTask();
        log.info("事件接收任务：{}", JSON.toJSONString(task));
        task.setFinish(true);
        log.info("完成任务：{}", JSON.toJSONString(task));
    }
}
