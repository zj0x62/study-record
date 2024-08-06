package com.example.demo.test;

import com.example.demo.CaffeineApplication;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.Duration;

/**
 * @Author: zhoujing
 * @Date: 2024/8/6 9:23
 * @Description: 刷新
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CaffeineApplication.class)
public class RefreshTest {

    private static int NUM = 0;

    @Test
    public void refresh() throws Throwable {
        LoadingCache<Integer, Integer> cache = Caffeine.newBuilder()
                .maximumSize(10000)
                .expireAfterWrite(Duration.ofSeconds(3))
                .refreshAfterWrite(Duration.ofSeconds(1))
                .recordStats()
                .build(integer -> ++NUM);

        //获取ID=1的值，由于缓存里还没有，所以会自动放入缓存
        System.out.println(cache.get(1));
        // 延迟2秒后，理论上自动刷新缓存后取到的值是2
        // 但其实不是，值还是1，因为refreshAfterWrite并不是设置了n秒后重新获取就会自动刷新
        // 而是x秒后&&第二次调用getIfPresent的时候才会被动刷新
        Thread.sleep(2000);
        System.out.println(cache.get(1));
        //此时才会刷新缓存，而第一次拿到的还是旧值
        System.out.println(cache.get(1));

        System.out.println(cache.stats());
    }
}
