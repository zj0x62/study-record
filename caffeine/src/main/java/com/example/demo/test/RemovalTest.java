package com.example.demo.test;

import com.example.demo.CaffeineApplication;
import com.example.demo.entity.User;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @Author: zhoujing
 * @Date: 2024/8/5 17:41
 * @Description: 移除
 * 术语:
 * 驱逐 缓存元素因为策略被移除
 * 失效 缓存元素被手动移除
 * 移除 由于驱逐或者失效而最终导致的结果
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CaffeineApplication.class)
public class RemovalTest {

    /**
     * 显示移除
     */
    @Test
    public void explicitRemovals() {
        Cache<Integer, User> cache = Caffeine.newBuilder()
                .expireAfterWrite(10, TimeUnit.MINUTES)
                .recordStats()
                .build();

        for (int i = 1; i <= 10; i++) {
            User user = createUser(i);
            cache.put(i, user);
        }

        // 失效key
        cache.invalidate(1);
        // 批量失效key
        cache.invalidateAll(Arrays.asList(2, 3, 4));
        // 失效所有的key
        cache.invalidateAll();
    }

    /**
     * 移除监听器
     * 你可以为你的缓存通过Caffeine.removalListener(RemovalListener)方法定义一个移除监听器在一个元素被移除的时候进行相应的操作。这些操作是使用 Executor 异步执行的，其中默认的 Executor 实现是 ForkJoinPool.commonPool() 并且可以通过覆盖Caffeine.executor(Executor)方法自定义线程池的实现。
     * 当移除之后的自定义操作必须要同步执行的时候，你需要使用 Caffeine.evictionListener(RemovalListener) 。这个监听器将在 RemovalCause.wasEvicted() 为 true 的时候被触发。为了移除操作能够明确生效， Cache.asMap() 提供了方法来执行原子操作。
     * 记住任何在 RemovalListener中被抛出的异常将会被打印日志 (通过Logger)并被忽略。
     */
    @Test
    public void removalListeners() {
        Cache<Integer, User> cache = Caffeine.newBuilder()
                .evictionListener((key, value, cause) -> {
                    log.info("驱逐缓存：key:{}, value:{}, cause:{}", key, value, cause);
                })
                .removalListener((key, value, cause) -> {
                    log.info("移除缓存：key:{}, value:{}, cause:{}", key, value, cause);
                })
                .recordStats()
                .build();

        for (int i = 1; i <= 10; i++) {
            User user = createUser(i);
            cache.put(i, user);
        }

        // 失效key
        cache.invalidate(1);
        // 批量失效key
        cache.invalidateAll(Arrays.asList(2, 3, 4));
        // 失效所有的key
        cache.invalidateAll();
    }

    private User createUser(Integer key) {
        return User.builder().id(key).name(key + "-create").createTime(new Date()).build();
    }
}
