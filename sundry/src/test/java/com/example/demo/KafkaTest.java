package com.example.demo;

import com.example.demo.kafka.producer.KafkaProducer;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author: zhoujing
 * @Date: 2023/8/14 16:51
 * @Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MyApplication.class)
@Slf4j
public class KafkaTest {

    @Autowired
    private KafkaProducer kafkaProducer;

    @Test
    public void test() {
        kafkaProducer.producer();
    }
}
