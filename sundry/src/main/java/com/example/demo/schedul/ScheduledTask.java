package com.example.demo.schedul;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author zhoujing
 * @Date 2022/9/13 9:38
 * @Desciption: 定时任务
 */
@Component
public class ScheduledTask {

    @Scheduled(cron = "0 0 2 * * ?")
    public static void scheduledTask() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("任务执行时间1：" + df.format(new Date()));
    }

    @Scheduled(cron = "*/7 * * * * ?")
    public static void scheduledTask2() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("任务执行时间2：" + df.format(new Date()));
    }
}
