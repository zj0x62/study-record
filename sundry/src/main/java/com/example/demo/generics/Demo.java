package com.example.demo.generics;

import java.lang.reflect.Array;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * @Author: zhoujing
 * @Date: 2023/11/22 10:00
 * @Description:    <?> 无限制通配符
 *                  <? extends E> extends关键字声明了类型的上界，表示参数化的类型可能是所指定的类型，或者是此类型的子类
 *                  <? super E> super关键字声明了类型的下界，表示参数化的类型可能是所指定的类型，或者是此类型的父类
 *                  // 使用原则 《Effictive Java》
 *                  // 为了获得最大限度的灵活性，要在表示 生产者或者消费者 的输入参数上使用通配符，使用的规则就是：生产者有上限，消费者有下限
 *                  1. 如果参数化类型表示一个 T 的生产者，使用<? extends T>;
 *                  2. 如果它表示一个 T 的消费者，就使用<? super T>;
 *                  3. 如果既是生产者又是消费者，那使用通配符就没有什么意义了，因为你需要的是精确的参数类型。
 */
public class Demo {

    public static void main(String[] args) throws Throwable {
//        demo1();
//        demo2();
//        demo3();
//        demo4();
//        demo5();
//        demo6();
//        demo7();
//        demo8();
//        demo9();
//        demo10();
//        demo11();
//        demo12();
        demo13();
    }

    private static void demo1() {
        add(1, 2);
        add(1.5, 2.3);
    }

    private static void demo2() throws Throwable {
        Generic generic = new Generic();
        Object object = generic.getObject(Class.forName("com.example.demo.generics.User"));
        System.out.println("object: " + object);
    }

    private static void demo3() {
        Upper<Integer> v1 = new Upper<>();
        v1.setVar(18);
        System.out.println("v1: " + v1);
    }

    private static void demo4() {
        Lower<String> i1 = new Lower<>();
        Lower<Object> i2 = new Lower<>();
        i1.setVar("hello");
        i2.setVar(new Object());
        fun(i1);
        fun(i2);
    }

    private static void demo5() {
        List<Double> list = Arrays.asList(2.0, 1.5, 53.0, 0.8, 25.0);
        Double max = max(list);
        System.out.println("max: " + max);
    }

    private static void demo6() {
        String[] array = new String[10];
        List<String>[] list1 = (List<String>[])new ArrayList<?>[10];
        List<?>[] list2 = new ArrayList<?>[10];
        List<String>[] list3 = new ArrayList[10];
    }

    private static void demo7() {
        Integer[] i = fun1(1, 2, 3, 4, 5, 6);
        fun2(i);
    }

    /**
     * 证明Java泛型的类型擦除
     */
    private static void demo8() {
        List<Integer> list1 = new ArrayList<>();
        list1.add(123);
        List<String> list2 = new ArrayList<>();
        list2.add("123");
        System.out.println(list1.getClass() == list2.getClass());
    }

    /**
     * 通过反射添加其它类型元素
     */
    private static void demo9() throws Throwable{
        List<Integer> list = new ArrayList<>();
        list.add(123);
        list.getClass().getMethod("add", Object.class).invoke(list, "asd");
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }

    /**
     * 在调用泛型方法时，可以指定泛型，也可以不指定泛型
     * 在不指定泛型的情况下，泛型变量的类型为该方法中的几种类型的同一父类的最小级，直到Object。
     * 在指定泛型的情况下，该方法的几种类型必须是该泛型的实例的类型或者其子类。
     */
    private static void demo10() {
        // 不指定泛型
        int i = Test.test(1, 2);
        Number n = Test.test(1, 1.2);
        Object o = Test.test(1, "asd");

        //指定泛型
        int a = Test.<Integer>test(1, 2);
//        int b = Test.<Integer>test(1, 2.2);
        Number c = Test.<Number>test(1, 2.2);
    }

    private static void demo11() {
        List<?>[] lsa = new List<?>[10];
        Object o = lsa;
        Object[] oa = (Object[]) o;
        List<Integer> li = new ArrayList<Integer>();
        li.add(new Integer(3));
        oa[1] = li;
        Integer i = (Integer) lsa[1].get(0);
        System.out.println("i="+i);
    }

    private static void demo12() {
        ArrayWithTypeToken<Integer> arrayToken = new ArrayWithTypeToken<>(Integer.class, 100);
        Integer[] array = arrayToken.create();
    }

    /**
     * java.lang.reflect.Type是Java中所有类型的公共高级接口, 代表了Java中的所有类型.
     * Type体系中类型的包括：数组类型(GenericArrayType)、参数化类型(ParameterizedType)、类型变量(TypeVariable)、通配符类型(WildcardType)、
     * 原始类型(Class)、基本类型(Class), 以上这些类型都实现Type接口。
     */
    private static void demo13() {
        GenericType<String> genericType = new GenericType<String>(){};
        // getGenericSuperclass(): 从一个Class对象中，获取该对象父类接收到的参数化类型（泛型）
        Type superclass = genericType.getClass().getGenericSuperclass();
        // ParameterizedType: 参数化类型，即泛型
        // getActualTypeArguments(): 获取参数化类型的数组，泛型可能有多个
        Type[] types = ((ParameterizedType) superclass).getActualTypeArguments();
        System.out.println(Arrays.toString(types));
    }

    private static <T extends Number> double add(T a, T b) {
        System.out.println("a + b = "+ (a.doubleValue() + b.doubleValue()));
        return a.doubleValue() + b.doubleValue();
    }

    /**
     * @param temp 只能接收String或Object类型的泛型，String类的父类只有Object类
     */
    public static void fun(Lower<? super String> temp) {
        System.out.println("temp: " + temp);
    }

    private static <E extends Comparable<? super E>> E max(List<? extends E> e1) {
        if (e1 == null) {
            return null;
        }
        // 迭代器返回的元素属于E的某个子类型
        Iterator<? extends E> iterator = e1.iterator();
        E result = iterator.next();
        while (iterator.hasNext()) {
            E next = iterator.next();
            if (next.compareTo(result) > 0) {
                result = next;
            }
        }
        return result;
    }

    @SafeVarargs
    private static <T> T[] fun1(T... arg) {
        return arg;
    }

    private static <T> void fun2(T[] param) {
        System.out.println("接收泛型数组：");
        for (T t : param) {
            System.out.println(t + ",");
        }
    }

    public static class Test {
        public static <T> T test(T x, T y) {
            return y;
        }
    }

    public static class ArrayWithTypeToken<T> {
        private T[] array;

        public ArrayWithTypeToken(Class<?> type, int size) {
            array = (T[]) Array.newInstance(type, size);
        }

        public void put(int index, T item) {
            array[index] = item;
        }

        public T get(int index) {
            return array[index];
        }

        public T[] create() {
            return array;
        }
    }

    public static class GenericType<T> {
        private T data;

        public T getData() {
            return data;
        }

        public void setData(T data) {
            this.data = data;
        }
    }
}
