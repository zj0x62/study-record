package com.example.demo.cache;

import cn.hutool.core.util.ObjectUtil;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

/**
 * @Author: zhoujing
 * @Date: 2024/2/2 13:45
 * @Description:
 */
public class CacheDemo {

    private static final User USER_NULL = new User();

    private static final LoadingCache<Long, User> USER_CACHE = CacheUtil.buildAsyncReloadingCache(Duration.ofSeconds(10L),
            new CacheLoader<Long, User>() {

        @Override
        public User load(Long id) {
            User user = selectUser(id);
            System.out.println("从数据库中查询：" + id);
            return ObjectUtil.defaultIfNull(user, USER_NULL);
        }
    });

    /**
     * 模拟从数据库中查询
     * @param id 用户id
     * @return 用户
     */
    private static User selectUser(Long id) {
        User user = new User();
        user.setId(id);

        return user;
    }

    /**
     * 从缓存中获取
     * @param id 用户id
     * @return 用户
     */
    public static User getUser(Long id) {
        return USER_CACHE.getUnchecked(id);
    }

    /**
     * 显式清除缓存
     * @param id 用户id
     */
    public static void clearCache(Long id) {
        USER_CACHE.invalidate(id);
    }

    public static void main(String[] args) throws Exception {
        long counter = 0;
        for (int i = 0; i < 30; i++) {
            if (counter % 5 == 0) {
                counter = 0;
            }
            User user = getUser(counter++);
            System.out.println(user);
            System.out.println("命中率：" + USER_CACHE.stats().hitCount());
            System.out.println("加载损耗的平均时间：" + USER_CACHE.stats().averageLoadPenalty());
            System.out.println("回收次数：" + USER_CACHE.stats().evictionCount());
            TimeUnit.SECONDS.sleep(1);
        }
    }
}
