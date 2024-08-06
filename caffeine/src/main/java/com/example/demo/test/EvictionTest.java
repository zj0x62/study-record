package com.example.demo.test;

import com.example.demo.CaffeineApplication;
import com.example.demo.entity.User;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.Expiry;
import com.github.benmanes.caffeine.cache.LoadingCache;
import com.github.benmanes.caffeine.cache.Weigher;
import com.google.common.testing.FakeTicker;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.index.qual.NonNegative;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @Author: zhoujing
 * @Date: 2024/8/5 14:32
 * @Description: Caffeine 提供了三种驱逐策略，分别是基于容量，基于时间和基于引用三种类型。
 * 术语:
 * 驱逐 缓存元素因为策略被移除
 * 失效 缓存元素被手动移除
 * 移除 由于驱逐或者失效而最终导致的结果
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CaffeineApplication.class)
public class EvictionTest {

    /**
     * 基于容量
     * 如果你的缓存容量不希望超过某个特定的大小，那么记得使用Caffeine.maximumSize(long)。缓存将会尝试通过基于就近度和频率的算法来驱逐掉不会再被使用到的元素。
     *
     * 另一种情况，你的缓存可能中的元素可能存在不同的“权重”--打个比方，你的缓存中的元素可能有不同的内存占用--你也许需要借助Caffeine.weigher(Weigher) 方法
     * 来界定每个元素的权重并通过 Caffeine.maximumWeight(long)方法来界定缓存中元素的总权重来实现上述的场景。
     * 除了“最大容量”所需要的注意事项，在基于权重驱逐的策略下，一个缓存元素的权重计算是在其创建和更新时，此后其权重值都是静态存在的，
     * 在两个元素之间进行权重的比较的时候，并不会根据进行相对权重的比较。
     */
    @Test
    public void size() throws Throwable {
        // 基于缓存内的元素个数进行驱逐
        LoadingCache<Integer, User> sizeCache = Caffeine.newBuilder()
                .maximumSize(5)
                .evictionListener((key, value, cause) -> {
                    log.info("基于元素个数驱逐缓存：key:{}, value:{}, cause:{}", key, value, cause);
                })
                .recordStats()
                .build(this::createUser);

        // 基于缓存内元素权重进行驱逐
        LoadingCache<Integer, User> weightCache = Caffeine.newBuilder()
                .maximumWeight(5)
                .weigher(new Weigher<Integer, User>() {

                    @Override
                    public @NonNegative int weigh(@NonNull Integer key, @NonNull User value) {
                        int weight = key % 2 + 1;
                        log.info("key: {}, weight: {}", key, weight);
                        return weight;
                    }
                })
                .evictionListener((key, value, cause) -> {
                    log.info("基于元素权重驱逐缓存：key:{}, value:{}, cause:{}", key, value, cause);
                })
                .recordStats()
                .build(this::createUser);

        for (int i = 1; i <= 10; i++) {
            User user = createUser(i);
            sizeCache.put(i, user);
            weightCache.put(i, user);
        }

        Thread.sleep(1000);

        System.out.println("sizeCache: " + sizeCache.asMap());
        System.out.println("weightCache: " + weightCache.asMap());
    }

    /**
     * 基于时间
     * Caffeine提供了三种方法进行基于时间的驱逐策略:
     * expireAfterAccess(long, TimeUnit): 一个元素在上一次读写操作后一段时间之后，在指定的时间后没有被再次访问将会被认定为过期项。在当被缓存的元素时被绑定在一个session上时，当session因为不活跃而使元素过期的情况下，这是理想的选择。
     * expireAfterWrite(long, TimeUnit): 一个元素将会在其创建或者最近一次被更新之后的一段时间后被认定为过期项。在对被缓存的元素的时效性存在要求的场景下，这是理想的选择。
     * expireAfter(Expiry): 一个元素将会在指定的时间后被认定为过期项。当被缓存的元素过期时间收到外部资源影响的时候，这是理想的选择。
     *
     * 在写操作，和偶尔的读操作中将会进行周期性的过期事件的执行。过期事件的调度和触发将会在O(1)的时间复杂度内完成。
     *
     * 为了使过期更有效率，可以通过在你的Cache构造器中通过Scheduler接口和Caffeine.scheduler(Scheduler) 方法去指定一个调度线程代替在缓存活动中去对过期事件进行调度。
     * 使用Java 9以上版本的用户可以选择Scheduler.systemScheduler()利用系统范围内的调度线程。
     */
    @Test
    public void time() {
        FakeTicker ticker = new FakeTicker();
        // 基于固定的过期时间驱逐策略
        LoadingCache<Integer, User> accessCache = Caffeine.newBuilder()
                .expireAfterAccess(5, TimeUnit.MINUTES)
                .evictionListener((key, value, cause) -> {
                    log.info("expireAfterAccess驱逐缓存：key:{}, value:{}, cause:{}", key, value, cause);
                })
                .recordStats()
                .ticker(ticker::read)
                .build(this::createUser);
        LoadingCache<Integer, User> writeCache = Caffeine.newBuilder()
                .expireAfterWrite(5, TimeUnit.MINUTES)
                .evictionListener((key, value, cause) -> {
                    log.info("writeCache驱逐缓存：key:{}, value:{}, cause:{}", key, value, cause);
                })
                .ticker(ticker::read)
                .build(this::createUser);

        // 基于不同的过期驱逐策略
        LoadingCache<Integer, User> cache = Caffeine.newBuilder()
                .expireAfter(new Expiry<Integer, User>() {
                    @Override
                    public long expireAfterCreate(@NonNull Integer id, @NonNull User user, long currentTime) {
                        // 元素创建后的过期时间
                        return TimeUnit.SECONDS.toNanos(7);
                    }

                    @Override
                    public long expireAfterUpdate(@NonNull Integer id, @NonNull User user, long currentTime, @NonNegative long currentDuration) {
                        // 元素更新后的过期时间
                        return TimeUnit.SECONDS.toNanos(6);
                    }

                    @Override
                    public long expireAfterRead(@NonNull Integer id, @NonNull User user, long currentTime, @NonNegative long currentDuration) {
                        // 元素被访问后的过期时间
                        return TimeUnit.SECONDS.toNanos(5);
                    }
                })
                .evictionListener((key, value, cause) -> {
                    log.info("expireAfter驱逐缓存：key:{}, value:{}, cause:{}", key, value, cause);
                })
                .recordStats()
                .ticker(ticker::read)
                .build(this::createUser);

//        for (int i = 1; i <= 10; i++) {
//            User user = createUser(i);
//            accessCache.put(i, user);
//            writeCache.put(i, user);
//            cache.put(i, user);
//        }

        for (int i = 1; i <= 10; i++) {
            User accessUser = accessCache.get(i);
            User writeUser = writeCache.get(i);
            // 创建
            User user = cache.get(i);
            System.out.println("accessUser: " + accessUser);
            System.out.println("writeUser: " + writeUser);
            System.out.println("user" + user);
        }

        // 访问
        User user = cache.get(1);
        // 更新
        cache.put(2, User.builder().id(2).name("update").build());

//        System.out.println("=======十分钟后=========");
//        ticker.advance(10, TimeUnit.MINUTES);
//        // accessCache和writeCache中的所有元素都过期
//        System.out.println("accessCache: " + accessCache.asMap());
//        System.out.println("writeCache: " + writeCache.asMap());

        System.out.println("======expireAfter=========");
//        // key=1过期, 过期顺序expireAfterRead
//        ticker.advance(5, TimeUnit.SECONDS);
//        // key=1,2过期，过期顺序expireAfterRead、expireAfterUpdate
//        ticker.advance(6, TimeUnit.SECONDS);
        // 所有key过期，过期顺序expireAfterRead、expireAfterUpdate、expireAfterCreate
        ticker.advance(7, TimeUnit.SECONDS);
        System.out.println("accessCache: " + accessCache.asMap());
        System.out.println("writeCache: " + writeCache.asMap());
        System.out.println("cache: " + cache.asMap());
    }

    /**
     * 基于引用
     * Caffeine 允许你配置你的缓存去让GC去帮助清理缓存当中的元素，其中key支持弱引用，而value则支持弱引用和软引用。记住 AsyncCache不支持软引用和弱引用。
     * Caffeine.weakKeys() 在保存key的时候将会进行弱引用。这允许在GC的过程中，当key没有被任何强引用指向的时候去将缓存元素回收。由于GC只依赖于引用相等性。这导致在这个情况下，缓存将会通过引用相等(==)而不是对象相等 equals()去进行key之间的比较。
     * Caffeine.weakValues()在保存value的时候将会使用弱引用。这允许在GC的过程中，当value没有被任何强引用指向的时候去将缓存元素回收。由于GC只依赖于引用相等性。这导致在这个情况下，缓存将会通过引用相等(==)而不是对象相等 equals()去进行value之间的比较。
     * Caffeine.softValues()在保存value的时候将会使用软引用。为了相应内存的需要，在GC过程中被软引用的对象将会被通过LRU算法回收。由于使用软引用可能会影响整体性能，我们还是建议通过使用基于缓存容量的驱逐策略代替软引用的使用。同样的，使用 softValues() 将会通过引用相等(==)而不是对象相等 equals()去进行value之间的比较。
     */
    @Test
    public void reference() {
        // 当key和缓存元素都不再其他强引用的时候驱逐
        LoadingCache<Integer, User> weakCache = Caffeine.newBuilder()
                .weakKeys()
                .weakValues()
                .build(this::createUser);

        // 当进行GC的时候进行驱逐
        LoadingCache<Integer, User> softCache = Caffeine.newBuilder()
                .softValues()
                .build(this::createUser);
    }

    private User createUser(Integer key) {
        return User.builder().id(key).name(key + "-create").createTime(new Date()).build();
    }
}
