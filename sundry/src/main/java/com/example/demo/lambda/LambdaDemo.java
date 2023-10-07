package com.example.demo.lambda;

import cn.hutool.core.lang.Assert;
import jdk.nashorn.internal.runtime.JSONListAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * @Author: zhoujing
 * @Date: 2022/11/18 10:36
 * @Description:
 */
public class LambdaDemo {

    public static void main(String[] args) {
        //Consumer
        List<Integer> objects = Arrays.asList(1, 2, 3, 4, 5);
        //对集合数据进行 加1然后输出的操作
        consume(objects, i -> System.out.println(i + 1));
        System.out.println(objects);

        //Supplier
        List<Integer> objects2 = new ArrayList<>();
        //填充10个随机数，Supplier是一个获取随机数的动作
        Random random = new Random();
        supplier(objects2, 10, () -> random.nextInt(10));
        System.out.println(objects2);

        //Function
        //对集合中的数据进行自增1的操作
        function(objects, i -> ++i);
        System.out.println(objects);

        //Predicate
        predicate(objects, i -> i > 3);

    }

    /**
     * 使用Consumer对集合元素进行操作的方法
     * @param list 需要操作的集合
     * @param consumer 对元素的具体操作，在调用的时候传递某个动作就行了
     * @param <T>
     */
    private static <T> void consume(List<T> list, Consumer<T> consumer) {
        for (T t : list) {
            consumer.accept(t);
        }
    }

    /**
     * 填充集合数据的方法
     * @param list 需要填充的集合
     * @param count 需要填充的数量
     * @param supplier 获取数据的操作，在调用的时候传递某个动作就行了
     * @param <T>
     */
    private static <T> void supplier(List<T> list, int count, Supplier<T> supplier) {
        for (int i = 0; i < count; i++) {
            list.add(supplier.get());
        }
    }

    /**
     * 使用Function对集合元素进行操作的方法
     * @param list 需要操作的集合
     * @param function 对元素的具体的函数操作，在调用的时候传递某个动作就行了
     * @param <T>
     */
    private static <T> void function(List<T> list, Function<T, T> function) {
        for (int i = 0; i < list.size(); i++) {
            //将通过传入的函数操作获取的结果替换原来的集合对应的数据
            list.set(i, function.apply(list.get(i)));
        }
    }

    /**
     *
     * @param list
     * @param predicate
     * @param <T>
     */
    private static <T> void predicate(List<T> list, Predicate<T> predicate) {
        for (T t : list) {
            System.out.println(predicate.test(t));
        }
    }
}
