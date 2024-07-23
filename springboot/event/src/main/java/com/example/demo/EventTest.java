package com.example.demo;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author: zhoujing
 * @Date: 2024/7/19 10:20
 * @Description:
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = EventApplication.class)
public class EventTest {

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @Test
    public void publishTest() {
        for (int i = 1; i <= 10; i++) {
            Task task = new Task();
            task.setId((long) i);
            task.setTaskName("测试任务" + i);
            task.setTaskContext("任务内容" + i);
            task.setFinish(false);
            MyEvent event = new MyEvent(task);
            log.info("开始发布任务");
            eventPublisher.publishEvent(event);
            log.info("发布任务完成");
        }
    }
}
