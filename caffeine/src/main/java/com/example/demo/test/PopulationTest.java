package com.example.demo.test;

import com.example.demo.CaffeineApplication;
import com.example.demo.entity.User;
import com.github.benmanes.caffeine.cache.AsyncCache;
import com.github.benmanes.caffeine.cache.AsyncLoadingCache;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.CacheLoader;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

/**
 * @Author: zhoujing
 * @Date: 2024/8/2 10:04
 * @Description: Caffeine提供了四种缓存添加策略：手动加载，自动加载，手动异步加载和自动异步加载。
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CaffeineApplication.class)
public class PopulationTest {

    /**
     * 手动加载
     */
    @Test
    public void manual() {
        Cache<Integer, User> cache = Caffeine.newBuilder()
                .expireAfterWrite(10, TimeUnit.MINUTES)
                .maximumSize(10000)
                .recordStats()
                .build();

        // 查找一个缓存元素，没有找到的时候返回null
        User user = cache.getIfPresent(1);
        // 查找缓存，如果缓存不存在则生成缓存元素，如果无法生成则返回null
        User user2 = cache.get(2, t -> User.builder().id(t).build());
        // 添加或者更新一个缓存元素
        User user1 = User.builder().id(1).name("manual").build();
        cache.put(1, user1);
        // 移除一个缓存元素
        cache.invalidate(1);
        user1 = cache.getIfPresent(1);
    }

    /**
     * 自动加载
     */
    @Test
    public void loading() {
        LoadingCache<Integer, User> cache = Caffeine.newBuilder()
                .maximumSize(10000)
                .expireAfterWrite(10, TimeUnit.MINUTES)
                .recordStats()
                .build(this::createUser);

        // 查找缓存，如果缓存不存在则生成缓存元素，如果无法生成则返回null
        User user = cache.get(1);
        // 批量查找缓存，如果缓存不存在则生成缓存元素
        Map<Integer, User> users = cache.getAll(Arrays.asList(1, 2, 3));
    }

    /**
     * 自动加载（自定义CacheLoader）
     * 一个LoadingCache是一个Cache 附加上 CacheLoader能力之后的缓存实现。
     *
     * 通过 getAll可以达到批量查找缓存的目的。 默认情况下，在getAll 方法中，将会对每个不存在对应缓存的key调用一次 CacheLoader.load 来生成缓存元素。
     * 在批量检索比单个查找更有效率的场景下，你可以覆盖并开发CacheLoader.loadAll 方法来使你的缓存更有效率。
     *
     * 值得注意的是，你可以通过实现一个 CacheLoader.loadAll并在其中为没有在参数中请求的key也生成对应的缓存元素。
     * 打个比方，如果对应某个key生成的缓存元素与包含这个key的一组集合剩余的key所对应的元素一致，那么在loadAll中也可以同时加载剩下的key对应的元素到缓存当中。
     */
    @Test
    public void loadingCustomCacheLoader() {
        LoadingCache<Integer, User> cache = Caffeine.newBuilder()
                .maximumSize(10000)
                .expireAfterWrite(10, TimeUnit.MINUTES)
                .recordStats()
                .build(new CacheLoader<Integer, User>() {
                    @Override
                    public @Nullable User load(@NonNull Integer key) throws Exception {
                        return User.builder().id(key).name(key + "-load").build();
                    }

                    @Override
                    public @NonNull Map<@NonNull Integer, @NonNull User> loadAll(@NonNull Iterable<? extends @NonNull Integer> keys) throws Exception {
                        HashMap<Integer, User> map = Maps.newHashMap();
                        for (Integer key : keys) {
                            User user = User.builder().id(key).name(key + "-loadAll").build();
                            map.put(key, user);
                        }
                        return map;
                    }
                });
        User user = cache.get(1);
        Map<Integer, User> users = cache.getAll(Arrays.asList(1, 2, 3));
    }

    /**
     * 手动异步加载
     * 一个AsyncCache 是 Cache 的一个变体，AsyncCache提供了在 Executor上生成缓存元素并返回 CompletableFuture的能力。这给出了在当前流行的响应式编程模型中利用缓存的能力。
     * synchronous()方法给 Cache提供了阻塞直到异步缓存生成完毕的能力。
     * 当然，也可以使用 AsyncCache.asMap()所暴露出来的ConcurrentMap的方法对缓存进行操作。
     * 默认的线程池实现是 ForkJoinPool.commonPool() ，当然你也可以通过覆盖并实现 Caffeine.executor(Executor)方法来自定义你的线程池选择。
     */
    @Test
    public void asyncManual() throws Throwable {
        AsyncCache<Integer, User> cache = Caffeine.newBuilder()
                .expireAfterWrite(10, TimeUnit.MINUTES)
                .maximumSize(10000)
                .recordStats()
                .buildAsync();
        // 查找一个缓存元素，没有找到的时候返回null
        CompletableFuture<User> future = cache.getIfPresent(1);
        User user = future != null ? future.get() : null;
        // 查找缓存元素，如果不存在，则异步生成
        future = cache.get(1, this::createUser);
        user = future.get();
        // 移除一个缓存元素
        cache.synchronous().invalidate(1);
    }

    /**
     * 自动异步加载
     * 一个 AsyncLoadingCache是一个 AsyncCache 加上 AsyncCacheLoader能力的实现。
     *
     * 在需要同步的方式去生成缓存元素的时候，CacheLoader是合适的选择。而在异步生成缓存的场景下， AsyncCacheLoader则是更合适的选择并且它会返回一个 CompletableFuture。
     *
     * 通过 getAll可以达到批量查找缓存的目的。 默认情况下，在getAll 方法中，将会对每个不存在对应缓存的key调用一次 AsyncCacheLoader.asyncLoad 来生成缓存元素。
     * 在批量检索比单个查找更有效率的场景下，你可以覆盖并开发AsyncCacheLoader.asyncLoadAll 方法来使你的缓存更有效率。
     *
     * 值得注意的是，你可以通过实现一个 AsyncCacheLoader.asyncLoadAll并在其中为没有在参数中请求的key也生成对应的缓存元素。
     * 打个比方，如果对应某个key生成的缓存元素与包含这个key的一组集合剩余的key所对应的元素一致，那么在asyncLoadAll中也可以同时加载剩下的key对应的元素到缓存当中。
     */
    @Test
    public void asyncLoading() throws Throwable {
        AsyncLoadingCache<Integer, User> cache = Caffeine.newBuilder()
                .maximumSize(10000)
                .expireAfterWrite(10, TimeUnit.MINUTES)
                .recordStats()
                // 你可以选择: 去异步的封装一段同步操作来生成缓存元素
//                .buildAsync(this::createUser);
                // 你也可以选择: 构建一个异步缓存元素操作并返回一个future
                .buildAsync(this::createUserAsync);
        // 查找缓存元素，如果其不存在，将会异步进行生成
        CompletableFuture<User> future = cache.get(1);
        User user = future.get();
        // 批量查找缓存元素，如果其不存在，将会异步进行生成
        CompletableFuture<Map<Integer, User>> futures = cache.getAll(Arrays.asList(1, 2, 3));
        Map<Integer, User> userMap = futures.get();
    }

    private User createUser(Integer key) {
        return User.builder().id(key).name(key + "-create").build();
    }

    private CompletableFuture<User> createUserAsync(Integer key, Executor executor) {
        // 1. 使用 CompletableFuture 的构造方法手动创建和完成 CompletableFuture
        CompletableFuture<User> future = new CompletableFuture<>();
        // 手动完成 CompletableFuture 的计算，并设置结果
        new Thread(() -> {
            try {
                Thread.sleep(1000);
                future.complete(User.builder().id(key).name(key + "-async").build());
            } catch (Exception e) {
                // 如果操作失败，可以使用 completeExceptionally 设置异常
                future.completeExceptionally(e);
            }
        }).start();

//        // 2.使用 supplyAsync 方法创建带有返回值的 CompletableFuture
//        CompletableFuture<User> future = CompletableFuture.supplyAsync(() -> {
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            return User.builder().id(key).name("async").build();
//        });

        return future;
    }
}
