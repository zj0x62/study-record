package com.example.demo.kafka.producer;

import com.alibaba.fastjson.JSON;
import com.example.demo.kafka.constants.TopicConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @Author: zhoujing
 * @Date: 2023/8/14 18:56
 * @Description:
 */
@Component
@Slf4j
public class KafkaProducer {

    @Resource
    private KafkaTemplate<String, byte[]> kafkaTemplate;

    public void producer() {
        for (int i = 0; i < 100; i++) {
            ProduceUser user = new ProduceUser();
            user.setName("user" + i);
            user.setAge(i);
            System.out.println("producer:" + user.toString());
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            kafkaTemplate.send(TopicConstants.TEST_TOPIC, JSON.toJSONString(user).getBytes());
//            kafkaTemplate.send(TopicConstants.TEST_TOPIC, 0, "0", JSON.toJSONString(user).getBytes());
//            kafkaTemplate.send(TopicConstants.TEST_TOPIC, 1, "0", JSON.toJSONString(user).getBytes());
//            kafkaTemplate.send(TopicConstants.TEST_TOPIC, 2, "0", JSON.toJSONString(user).getBytes());
        }
    }
}
