package com.example.demo;

import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import org.assertj.core.util.DateUtil;
import org.junit.Test;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.EnumMap;

/**
 * @Author zhoujing
 * @Date 2022/8/11 10:02
 * @Desciption:
 */
public class CodeTest {

    @Test
    public void test() throws ParseException {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(format.parse(getTime()));
    }

    public String getTime() {
        try {
            //第零步：首先肯定要自己规定时间格式
            SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            //这里的parse方法是设置固定时间抛出异常：ParseException

            //第一步：得到结束时间的毫秒数
            //将时间转化成Date类型
            Date endTime=simpleDateFormat.parse("2022-05-21 00:00:00");
//            System.out.println(endTime);//Sat May 21 00:00:00 CST 2022
            //这里得到一个Date类型时间，由（Date）.gettime得到毫秒数
            //毫秒数很大，故用long接收
            long endSeconds=endTime.getTime();
//            System.out.println(endSeconds);//1653062400000

            //第二步：运用同样的方法得到起始时间的毫秒数
            Date startTime=simpleDateFormat.parse("2021-05-21 00:00:00");
//            System.out.println(startTime);//Fri May 21 00:00:00 CST 2021
            long startSeconds=startTime.getTime();
//            System.out.println(startSeconds);//1621526400000

            //第三步：用结束的毫秒数-起始的毫秒数=相隔的毫秒数
            long apartSeconds=endSeconds-startSeconds;

            //第四步：将相隔的毫秒数随机化+起始毫秒数
            long timeSeconds= (long) (apartSeconds*Math.random());
            long realSeconds=startSeconds+timeSeconds;

            //第五步：将毫秒数转化为时间
            String realTime=simpleDateFormat.format(realSeconds);
            System.out.println("realTime = " + realTime);//随机时间：2022-03-31 09:26:16
//            Date realTime1=simpleDateFormat.parse(realTime);
            //哎！就害怕你要这种类型的时间
//            System.out.println(realTime1);//随机时间：Thu Mar 31 09:26:16 CST 2022

            return realTime;
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
