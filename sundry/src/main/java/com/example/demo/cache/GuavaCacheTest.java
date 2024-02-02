package com.example.demo.cache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import lombok.Data;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @Author: zhoujing
 * @Date: 2024/1/31 14:00
 * @Description: key对应的缓存值计算方式: CacheLoader、Callable、直接插入
 *               缓存淘汰机制: 基于大小剔除、基于缓存时间剔除、基于引用剔除
 */
public class GuavaCacheTest {

    public static void main(String[] args) throws Exception {
//        cacheLoader();
//        callable();
//        insert();
//        numSize();
//        weightSize();
//        expired();
//        writeExpired();
//        weak();
//        removalListener();
        refresh();
    }

    /**
     * CacheLoader方式
     * 计算方式作用于所有key，也就是说通过CacheLoader方法创建的Cache，不管你访问哪个key，它的计算方式都是同一个
     */
    private static void cacheLoader() {
        LoadingCache<String, String> loadingCache = CacheBuilder.newBuilder()
                .maximumSize(2)
                .build(new CacheLoader<String, String>() {
                    @Override
                    public String load(String key) {
                        System.out.println(key + "真正计算了");
                        return "cached-" + key;
                    }
                });

        System.out.println(loadingCache.getUnchecked("key1"));
        System.out.println(loadingCache.getUnchecked("key1"));
        System.out.println(loadingCache.getUnchecked("key2"));
        System.out.println(loadingCache.getUnchecked("key2"));
    }

    /**
     * Callable
     * 不同的缓存key的计算缓存值方式并不一样
     */
    private static void callable() throws Exception {
        Cache<Object, Object> cache = CacheBuilder.newBuilder().build();
        Object cacheKey1 = cache.get("key1", () -> {
            System.out.println("key1真正计算了");
            return "key1计算方式1";
        });
        System.out.println(cacheKey1);
        cacheKey1 = cache.get("key1", () -> {
            System.out.println("key1真正计算了");
            return "key1计算方式1";
        });
        System.out.println(cacheKey1);

        Object cacheKey2 = cache.get("key2", () -> {
            System.out.println("key2真正计算了");
            return "key2计算方式2";
        });
        System.out.println(cacheKey2);

        cacheKey2 = cache.get("key2", () -> {
            System.out.println("key2真正计算了");
            return "key2计算方式2";
        });
        System.out.println(cacheKey2);
    }

    /**
     * 直接插入
     * 这种方式计算缓存值的逻辑不再由Guava Cache管理，而是调用方可以调用put(key,value) 直接将要缓存的值插入。
     */
    private static void insert() throws Exception {
        Cache<Object, Object> cache = CacheBuilder.newBuilder().build();
        cache.put("key1", "cache-key1");
        System.out.println(cache.get("key1", () -> "callable cache-key1"));
    }

    /**
     * 基于大小剔除
     * 当缓存key的数量达到指定数量时，将按照LRU针对缓存key进行剔除。
     * 如果maximumSize传入0，则所有key都将不进行缓存！
     */
    private static void numSize() {
        LoadingCache<String, String> loadingCache = CacheBuilder.newBuilder()
                .maximumSize(3)
                .build(new CacheLoader<String, String>() {
            @Override
            public String load(String key) throws Exception {
                System.out.println(key + "真正计算了");
                return "cached-" + key;
            }
        });

        System.out.println("第一次访问");
        loadingCache.getUnchecked("key1");
        loadingCache.getUnchecked("key2");
        loadingCache.getUnchecked("key3");

        System.out.println("第二次访问");
        loadingCache.getUnchecked("key1");
        loadingCache.getUnchecked("key2");
        loadingCache.getUnchecked("key3");

        System.out.println("开始剔除");
        loadingCache.getUnchecked("key4");

        System.out.println("第三次访问");
        loadingCache.getUnchecked("key3");
        loadingCache.getUnchecked("key2");
        loadingCache.getUnchecked("key1");
    }

    /**
     * 基于权重大小剔除
     * 如果所有缓存的key的权重之和大于了我们指定的最大权重，那么将执行LRU淘汰策略
     */
    private static void weightSize() {
        LoadingCache<String, String> loadingCache = CacheBuilder.newBuilder().maximumWeight(6).weigher(((key, value) -> {
            if ("key1".equals(key)) {
                return 1;
            }
            if ("key2".equals(key)) {
                return 2;
            }
            if ("key3".equals(key)) {
                return 3;
            }
            if ("key4".equals(key)) {
                return 1;
            }

            return 0;
        })).build(new CacheLoader<String, String>() {
            @Override
            public String load(String key) {
                System.out.println(key + "真正计算了");
                return "cached-" + key;
            }
        });

        System.out.println("第一次访问");
        loadingCache.getUnchecked("key1");
        loadingCache.getUnchecked("key2");
        loadingCache.getUnchecked("key3");

        System.out.println("第二次访问");
        loadingCache.getUnchecked("key1");
        loadingCache.getUnchecked("key2");
        loadingCache.getUnchecked("key3");

        System.out.println("开始剔除");
        loadingCache.getUnchecked("key4");
        loadingCache.getUnchecked("key3");
        loadingCache.getUnchecked("key2");
        loadingCache.getUnchecked("key1");
    }

    /**
     * 基于时间剔除
     * expireAfterAccess: 当某个缓存key自最后一次访问（读取或者写入）超过指定时间后，那么这个缓存key将失效。
     */
    private static void expired() throws Exception {
        LoadingCache<String, String> loadingCache = CacheBuilder.newBuilder()
                .expireAfterAccess(3, TimeUnit.SECONDS)
                .build(new CacheLoader<String, String>() {
                    @Override
                    public String load(String key) {
                        System.out.println(key + "真正计算了");
                        return "cached-" + key;
                    }
                });

        System.out.println("第一次访问(写入)");
        loadingCache.getUnchecked("key1");

        System.out.println("第二次访问");
        loadingCache.getUnchecked("key1");

        TimeUnit.SECONDS.sleep(3);
        System.out.println("过3秒后访问");
        loadingCache.getUnchecked("key1");
    }

    /**
     * 基于时间剔除
     * expireAfterWrite: 当缓存key自最近一次写入（注意，这就是和expireAfterAccess的区别，expireAfterWrite强调写，不关心读）超过一定时间则过期剔除.
     */
    private static void writeExpired() throws Exception {
        LoadingCache<String, String> loadingCache = CacheBuilder.newBuilder()
                .expireAfterWrite(3, TimeUnit.SECONDS)
                .build(new CacheLoader<String, String>() {
                    @Override
                    public String load(String key) {
                        System.out.println(key + "真正计算了");
                        return "cached-" + key;
                    }
                });

        for (int i = 0; i < 4; i++) {
            System.out.println(new Date());
            loadingCache.getUnchecked("key1");
            TimeUnit.SECONDS.sleep(1);
        }
    }

    /**
     * 基于引用剔除
     * CacheBuilder.weakKeys() 当使用了weakKeys() 后，Guava cache将以弱引用 的方式去存储缓存key,那么根据弱引用的定义：当发生垃圾回收时，不管当前系统资源是否充足，弱引用都会被回收
     */
    private static void weak() throws Exception {
        LoadingCache<MyKey, String> loadingCache = CacheBuilder.newBuilder()
                .weakKeys()
                .build(new CacheLoader<MyKey, String>() {
            @Override
            public String load(MyKey key) throws Exception {
                System.out.println(key.getKey() + "真正计算了");
                return "cached-" + key.getKey();
            }
        });

        MyKey key1 = new MyKey("key1");
        System.out.println("第一次访问");
        loadingCache.getUnchecked(key1);
        System.out.println(loadingCache.asMap());

        System.out.println("第二次访问");
        loadingCache.getUnchecked(key1);
        System.out.println(loadingCache.asMap());

        System.out.println("key失去强引用GC后访问");
        key1 = null;
        System.gc();
        TimeUnit.SECONDS.sleep(3);
        System.out.println(loadingCache.asMap());
    }

    /**
     * 缓存失效监听器
     * 当缓存失效时，将回调监听器
     * Guava cache并不是自己主动去清理那些失效缓存的，而是当我们对缓存进行了操作时，才会进行检查清理以及其他动作
     */
    private static void removalListener() throws Exception {
        LoadingCache<String, String> loadingCache = CacheBuilder.newBuilder()
                .removalListener(notification -> {
                    System.out.printf("缓存 %s 因为 %s 失效了，它的value是%s%n", notification.getKey(), notification.getCause(), notification.getValue());
                })
                .expireAfterAccess(3, TimeUnit.SECONDS)
                .build(new CacheLoader<String, String>() {
                    @Override
                    public String load(String key) {
                        System.out.println(key + "真正计算了");
                        return "cached-" + key;
                    }
                });

        System.out.println("第一次访问(写入)");
        loadingCache.getUnchecked("key1");

        System.out.println("第二次访问");
        loadingCache.getUnchecked("key1");
        TimeUnit.SECONDS.sleep(3);

        System.out.println("3秒后");
        loadingCache.getUnchecked("key1");
    }

    /**
     * 缓存刷新
     * 指定缓存key写入多久后重新进行计算并缓存
     */
    private static void refresh() throws Exception {
        LoadingCache<String, String> loadingCache = CacheBuilder.newBuilder()
                .refreshAfterWrite(1, TimeUnit.SECONDS)
                .build(new CacheLoader<String, String>() {
                    @Override
                    public String load(String key) {
                        System.out.println(key + "真正计算了");
                        return "cached-" + key;
                    }
                });

        for (int i = 0; i < 3; i++) {
            loadingCache.getUnchecked("key1");
            TimeUnit.SECONDS.sleep(2);
        }
    }

    @Data
    private static class MyKey {

        String key;

        public MyKey(String key) {
            this.key = key;
        }
    }
}