package com.example.demo.test;

import com.example.demo.CaffeineApplication;
import com.github.benmanes.caffeine.cache.CacheLoader;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

/**
 * @Author: zhoujing
 * @Date: 2024/8/6 10:58
 * @Description:
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CaffeineApplication.class)
public class CaffeineWriterTest {

    @Test
    public void test() throws Throwable {
        LoadingCache<Integer, Integer> cache = Caffeine.newBuilder()
                .maximumSize(1)
                .expireAfterWrite(1, TimeUnit.SECONDS)
                .removalListener((key, value, cause) -> {
                    System.out.println("removalListener ---> key: " + key + ", value: " + value + ", cause: " + cause);
                    // 可以根据不同的淘汰原因进行处理
                    switch (cause) {
                        // 个数超过限制导致的移除
                        case SIZE:
                            break;
                        // 过期
                        case EXPIRED:
                            break;
                        // 手动删除
                        case EXPLICIT:
                            break;
                        // 条目本身实际上并没有被删除，但它的值被用户替换了。
                        case REPLACED:
                            break;
                        // 条目已被自动删除，因为其关键字或值被垃圾收集。当使用Caffee.weakKeys、Caffee.weakValues或Caffee.softValues时，可能会出现这种情况。
                        case COLLECTED:
                            break;
                        default:
                            break;
                    }
                })
                .build(new CacheLoader<Integer, Integer>() {
                    @Override
                    public @Nullable Integer load(@NonNull Integer key) throws Exception {
                        System.out.println("触发CacheLoader.load, key = " + key);
                        return key;
                    }
                });
        // size
        cache.put(1, 1);
        cache.put(2, 2);
        // expired
        Thread.sleep(2000);
        Integer value = cache.getIfPresent(1);
        // explicit
        cache.put(1, 1);
        cache.invalidate(1);
        // replaced
        cache.put(1, 1);
        cache.put(1, 2);

        cache.get(3);
    }
}
