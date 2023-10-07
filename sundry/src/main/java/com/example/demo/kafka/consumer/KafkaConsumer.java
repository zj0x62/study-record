package com.example.demo.kafka.consumer;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.kafka.constants.TopicConstants;
import com.example.demo.kafka.consumer.ConsumeUser;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Author: zhoujing
 * @Date: 2023/8/14 18:53
 * @Description:
 * 一个生产者，多个消费者消费，每个消费者都要消费到所有数据。现有两种方案：
 * 1. 消费者的group_id相同，消费topic的不同分区。这样需要生产者往每个分区都发送消息。后续新增消费者，生产者可能也需要改。（不推荐）
 * 2. 指定消费者的group_id不同。后续新增消费者，只需指定group_id即可，对之前的代码不用改动。
 */
@Component
@Slf4j
public class KafkaConsumer {

//    @KafkaListener(groupId = "allynav-wisdom-iot-test",topicPartitions = {@TopicPartition(topic = TopicConstants.TEST_TOPIC, partitions = { "0" })})
    @KafkaListener(topics = {TopicConstants.TEST_TOPIC}, properties = {ConsumerConfig.MAX_POLL_RECORDS_CONFIG + "=" + 100}, groupId = "allynav-wisdom-iot-test")
    public void consumer(List<ConsumerRecord<String, byte[]>> records) {
        List<ConsumerRecord<String, byte[]>> datas = Collections.synchronizedList(new ArrayList<>());
        datas.addAll(records);
        datas.forEach(record -> {
            ConsumeUser user = new ConsumeUser();
            user = JSONObject.parseObject(record.value(), ConsumeUser.class);
            System.out.println("consumer:" + user.toString());
        });
    }

//    @KafkaListener(groupId = "allynav-wisdom-iot-test",topicPartitions = {@TopicPartition(topic = TopicConstants.TEST_TOPIC, partitions = { "1" })})
    @KafkaListener(topics = {TopicConstants.TEST_TOPIC}, properties = {ConsumerConfig.MAX_POLL_RECORDS_CONFIG + "=" + 100}, groupId = "allynav-wisdom-iot-test2")
    public void consumer2(List<ConsumerRecord<String, byte[]>> records) {
        List<ConsumerRecord<String, byte[]>> datas = Collections.synchronizedList(new ArrayList<>());
        datas.addAll(records);
        datas.forEach(record -> {
            ConsumeUser user = new ConsumeUser();
            user = JSONObject.parseObject(record.value(), ConsumeUser.class);
            System.out.println("consumer2:" + user.toString());
        });
    }
}
