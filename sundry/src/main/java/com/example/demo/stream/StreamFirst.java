package com.example.demo.stream;

import org.apache.commons.math3.geometry.partitioning.BSPTree;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Author: zhoujing
 * @Date: 2022/11/21 13:14
 * @Description:
 */
public class StreamFirst {

    public static void main(String[] args) {
        List<Student> students = new ArrayList<>();
        students.add(new Student("小明", 18, 75));
        students.add(new Student("小李", 19, 45));
        students.add(new Student("小花", 20, 55));
        students.add(new Student("小王", 22, 80));
        students.add(new Student("小王", 22, 80));

//        test1(students);
//        test2(students);
//        test3(students);
//        test4(students);
//        test5(students);
//        test6();
//        test7();
        test8(students);
//        test9();
//        test10();
//        test11();
//        test12();
//        test13();
    }

    private static void test1(List<Student> students) {
        //筛选出成绩大于等于60的学生名字
        //使用普通集合操作
        List<String> nameList1 = new ArrayList<>();
        for (Student student : students) {
            if (student.getScore() >= 60) {
                nameList1.add(student.getName());
            }
        }
        nameList1.forEach(name -> System.out.println("普通集合操作：" + name));

        //使用流，不同的操作都是链式的
        List<String> nameList2 = students.stream()
                //筛选出成绩大于等于60的学生
                .filter(student -> student.getScore() >= 60)
                //收集学生名字
                .map(Student::getName)
                //返回结果
                .collect(Collectors.toList());
        nameList2.forEach(name -> System.out.println("流操作：" + name));
    }

    /**
     * 流的操作可以分为两类：
     *  1、中间操作
     *      （1）中间操作的一个显著特征是会返回另一个Stream流，比如filter、sorted、linit等等，这样的好处是我们可以进行链式编程，形成一个操作流水线！
     *      （2）中间操作的另一个隐藏特征是延迟执行，或者称为惰性求值！因为他只是描述了流水线要进行的操作，而并没有真正的执行这条流水线，它需要一个“触发操作”，这就是终端操作！
     *  2、终端操作
     *      （1）终端操作的一个显著特征是不会返回另一个Stream流，比如count、collect，相当于从流水线中获取结果，只有存在终端操作，中间操作才会执行。
     *      （2）终端操作的另一个隐藏特征是它会终结这个流，此后这个流不能被重复使用，也被称为及早求值！
     */
    private static void test2(List<Student> students) {
        //没有终端操作的流，不会执行中间操作
        students.stream()
                //筛选出成绩大于等于60的学生
                .filter(student -> {
                    System.out.println("中间操作：" + student.getScore());
                    return student.getScore() >= 60;
                })
                //收集学生名字
                .map(Student::getName);

        //有终端操作的流，才会执行
        //没有终端操作的流，不会执行中间操作
        students.stream()
                //筛选出成绩大于等于60的学生
                .filter(student -> {
                    System.out.println("终端操作：" + student.getScore());
                    return student.getScore() >= 60;
                })
                //收集学生名字
                .map(Student::getName)
                //collect是一个终端操作
                .collect(Collectors.toList());
    }

    /**
     * 筛选操作
     * @param students
     */
    private static void test3(List<Student> students) {
        System.out.println("filter筛选成绩大于等于70的学生");
        students.stream().filter(student -> student.getScore() >= 70).forEach(System.out::println);

        System.out.println("filter+distinct筛选成绩大于等于70的学生，且去除重复数据");
        students.stream().filter(student -> student.getScore() >= 70).distinct().forEach(System.out::println);

        System.out.println("limit最多截取前2个数据");
        students.stream().filter(student -> student.getScore() >= 70).limit(2).forEach(System.out::println);

        System.out.println("skip丢弃前2个数据");
        students.stream().filter(student -> student.getScore() >= 70).skip(2).forEach(System.out::println);

        System.out.println("skip丢弃前1个数据，limit最多截取前1个数据");
        students.stream().filter(student -> student.getScore() >= 70).skip(1).limit(1).forEach(System.out::println);
    }

    /**
     * 排序操作
     * @param students
     */
    private static void test4(List<Student> students) {
        //自然排序 返回由此流的元素组成的流，根据自然顺序排序如果流元素不是Comparable类型 ，则执行终端操作时抛出ClassCastException。
        Stream.of(5, 2, 7, 8, 4).sorted().forEach(System.out::println);

        //指定比较规则 返回由该流的元素组成的流，根据提供的Comparator进行排序。对于有序流，排序稳定。 对于无序的流，不能保证稳定性。
        students.stream()
                .sorted(Comparator.comparingInt(Student::getScore).thenComparing(Student::getAge))
                .forEach(System.out::println);
    }

    /**
     * 映射操作 -- map
     * @param students
     */
    private static void test5(List<Student> students) {
        students.stream()
                .map(Student::getScore)
                .forEach(System.out::println);

        List<Integer> collect = students.stream()
                .map(Student::getScore)
                .collect(Collectors.toList());
        System.out.println(collect);

        //将小写字母转换为大写
        List<String> collected = Stream.of("a", "b", "c", "D")
                .map(String::toUpperCase)
                .collect(Collectors.toList());
        System.out.println(collected);
    }

    /**
     * 映射操作 -- flatMap
     */
    private static void test6() {
        List<String> words = Arrays.asList("hello", "world");

        //如果用map，可以看到流就成了Stream<Stream<String>>类型，那么最后收集的元素就是Stream<String>类型
        List<Stream<String>> collect = words.stream()
                .map(word -> Arrays.stream(word.split("")))
                .distinct()
                .collect(Collectors.toList());
        System.out.println(collect);

        //如果使用flatMap，那么就可以达到想要的结果
        List<String> collect1 = words.stream()
                .flatMap(word -> Arrays.stream(word.split("")))
                .distinct()
                .collect(Collectors.toList());
        System.out.println(collect1);

        //原因
        //可以看到Arrays.stream方法返回的就是Stream<String>类型
        Stream<String> stream = Arrays.stream("world".split(""));
        //如果使用map，那么返回的流就是一个Stream<Stream<String>>类型，流元素也是一个流
        //因为map将Arrays.stream方法返回的Stream<String>作为流元素使用一个流进行收集，随后返回这个流
        Stream<Stream<String>> streamStream = words.stream().map(word -> Arrays.stream(word.split("")));
        //如果使用flatMap，那么返回的流就是一个Stream<String>类型，这才是正常的类型
        //因为flatMap将Arrays.stream方法返回的Stream<String>流进行了合并，随后返回合并之后的大流
        Stream<String> stringStream = words.stream().flatMap(word -> Arrays.stream(word.split("")));
    }

    /**
     * 查看操作 -- peek
     * 返回由该流的元素组成的流，另外在从生成的流中消耗元素时对每个元素执行提供的消费操作。
     * 该方法可以用来查看流水线中间某个点的元素，当前也可以修改一些元素的属性，但是要自己保证线程安全！
     * 查看操作是一个中间操作！
     */
    private static void test7() {
        System.out.println(Stream.of(1, 2, 3, 4, 5)
                .map(i -> i + 1)
                .peek(System.out::println)
                .collect(Collectors.toList()));

        System.out.println(Stream.of("one", "two", "three", "four")
                .filter(e -> e.length() > 3)
                .peek(e -> System.out.println("Filtered value: " + e))
                .map(String::toUpperCase)
                .peek(e -> System.out.println("Mapped value: " + e))
                .collect(Collectors.toList()));
    }

    /**
     * 匹配操作 -- allMatch,anyMatch,noneMatch
     * 匹配操作是一个终端操作！
     * 注意：流是否为空是最先判断的条件。空集合、空数组、空文件等等来源都将构建一个空流。
     *
     * anyMatch：判断的条件里，任意一个元素成功，返回true
     * allMatch：判断条件里的元素，所有的都是，返回true
     * noneMatch：与allMatch相反，判断条件里的元素，所有的都不是，返回true
     */
    private static void test8(List<Student> students) {
        //空流测试
        System.out.println(Stream.of().allMatch(i -> true));
        System.out.println(Stream.of().allMatch(i -> false));
        System.out.println(Stream.of().anyMatch(i -> true));
        System.out.println(Stream.of().anyMatch(i -> false));
        System.out.println(Stream.of().noneMatch(i -> true));
        System.out.println(Stream.of().noneMatch(i -> false));

        System.out.println("---------------");
        System.out.println(students.stream().allMatch(student -> student.getScore() >= 60));
        System.out.println(students.stream().allMatch(student -> student.getScore() >= 30));

        System.out.println(students.stream().anyMatch(student -> "小明".equals(student.getName())));
        System.out.println(students.stream().anyMatch(student -> "小明1".equals(student.getName())));

        System.out.println(students.stream().noneMatch(student -> student.getScore() <= 60));
        System.out.println(students.stream().noneMatch(student -> student.getScore() <= 30));
    }

    /**
     * 查找操作 -- findFirst,findAny
     * 查找操作是一个终端操作！
     *
     * findFirst - 返回此流的第一个元素的Optional。如果流为空，则返回一个空的Optional，如果源数据没有顺序，则可能会返回任何元素。
     * findAny - 返回此流的任意一个元素的Optional。如果流为空，则返回一个空的Optional，如果源数据没有顺序，则可能会返回任何元素。
     *           这个方法主要是为了并行流的效率考虑的，如果是单线程下也是返回第一个流元素！
     */
    private static void test9() {
        Optional<String> first = Stream.of("aa", "bb", "cc").findFirst();
        Optional<String> any = Stream.of("aa", "bb", "cc").findAny();

        if (first.isPresent()) {
            String s = first.get();
            System.out.println(s);
        }

        if (any.isPresent()) {
            String s = any.get();
            System.out.println(s);
        }

        first.ifPresent(System.out::println);
        any.ifPresent(System.out::println);
    }

    /**
     * Optional
     */
    private static void test10() {
        System.out.println("filter:");
        System.out.println(Optional.of(3).filter(i -> i > 2));
        System.out.println(Optional.of(3).filter(i -> i > 3));

        System.out.println("map:");
        System.out.println(Optional.of(3).map(i -> i + 2));
        System.out.println(Optional.of(2).map(i -> i + 2));

        System.out.println("flatMap:");
        System.out.println(Optional.of(3).flatMap(i -> Optional.of(i + 2)));
        System.out.println(Optional.of(2).flatMap(i -> Optional.of(i + 2)));

        //map对比，可以看到和Stream的flatMap与map方法差不多
        System.out.println(Optional.of(2).map(i -> Optional.of(i + 2)));
    }

    /**
     * 归纳操作 -- reduce,max,min等
     * 归纳操作是一个终端操作！
     */
    private static void test11() {
        //求差  前一个计算的值减去后一个元素
        Optional<Integer> reduce = Stream.of(1, 3, 4, 2, 8).reduce((i, j) -> i - j);
        reduce.ifPresent(System.out::println);

        //求差  后一个元素减去前一个计算的值
        Optional<Integer> reduce1 = Stream.of(1, 3, 4, 2, 8).reduce((i, j) -> j - i);
        reduce1.ifPresent(System.out::println);

        //求和
        Optional<Integer> reduce2 = Stream.of(1, 3, 4, 2, 8).reduce(Integer::sum);
        reduce2.ifPresent(System.out::println);

        //求最值
        Optional<Integer> reduce3 = Stream.of(1, 3, 4, 2, 8).reduce(Integer::max);
        reduce3.ifPresent(System.out::println);

        //求最值，初始值是10
        Integer reduce4 = Stream.of(1, 3, 4, 2, 8).reduce(10, Integer::max);
        System.out.println(reduce4);

        //计数
        Integer reduce5 = Stream.of(1, 3, 4, 2, 8).map(d -> 1).reduce(0, Integer::sum);
        System.out.println(reduce5);

        //计数
        long count = Stream.of(1, 3, 4, 2, 8).count();
        System.out.println(count);

        //串行模式下无效
        System.out.println(Stream.of(1, 2, 3, 4, 7).reduce(2, Integer::sum, Integer::sum));
        System.out.println(Stream.of(1, 2, 3, 4, 7).reduce(2, Integer::sum, Integer::max));

        //并行模式下有效
        //初始值为2，首先对每一个元素应用一个计算accumulator，得到结果：3，4，5，6，9，最后combiner整合这些值：得到27
        System.out.println(Stream.of(1, 2, 3, 4, 7).parallel().reduce(2, Integer::sum, Integer::sum));
        //初始值为2，首先对每一个元素应用一个计算accumulator，得到结果：3，4，5，6，9，最后combiner整合这些值：得到9
        System.out.println(Stream.of(1, 2, 3, 4, 7).parallel().reduce(2, Integer::sum, Integer::max));
        //初始值为2，首先对每一个元素应用一个计算accumulator，得到结果：1，2，3，4，7，最后combiner整合这些值：得到1
        System.out.println(Stream.of(1, 2, 3, 4, 7).parallel().reduce(0, Integer::sum, Integer::min));

        //初始值为2，首先对每一个元素应用一个计算accumulator，得到结果：-1，0，1，2，5，最后combiner整合这些值：得到7
        System.out.println(Stream.of(1, 2, 3, 4, 7).parallel().reduce(2, (i, j) -> j - i, Integer::sum));
        //初始值为2，首先对每一个元素应用一个计算accumulator，得到结果：1，0，-1，-2，-5，最后combiner整合这些值：得到-7
        System.out.println(Stream.of(1, 2, 3, 4, 7).parallel().reduce(2, (i, j) -> i - j, Integer::sum));


        System.out.println(Stream.of(1, 2, 3, 4, 7).parallel().reduce(2, (i, j) -> {
                    //查看线程，多线程
                    System.out.println(Thread.currentThread().getName());
                    return j - i;
                }
                , Integer::sum));

        System.out.println(Stream.of(1, 2, 3, 4, 7).reduce(2, (i, j) -> {
                    //查看线程，单线程
                    System.out.println(Thread.currentThread().getName());
                    return j - i;
                }
                , Integer::sum));
    }

    /**
     * 收集操作
     */
    private static void test12() {
        //Stream的count方法
        System.out.println(Stream.of(1, 2, 3, 4, 7).count());
        //特性化流的count方法
        System.out.println(Stream.of(1, 2, 3, 4, 7).mapToInt(x -> x).count());
        //map+reduce实现计数
        System.out.println(Stream.of(1, 2, 3, 4, 7).map(d -> 1).reduce(0, Integer::sum));
        //collect操作提供计数功能
        System.out.println(Stream.of(1, 2, 3, 4, 7).collect(Collectors.counting()));

        //特性化流的方法
        Stream.of(1, 2, 3, 4, 7).mapToInt(x -> x).max().ifPresent(System.out::println);
        //reduce方法
        Optional<Integer> maxReduce = Stream.of(1, 2, 3, 4, 7)
                //使用方法引用Integer::max来表示求最值的意图
                .reduce(Integer::max);
        maxReduce.ifPresent(System.out::println);

        //现在，collect操作也提供求最值功能，并且已经提供了预定于的收集器
        //minBy(Comparator) 静态方法就返回一个用于求最小值的收集器
        Stream.of(1, 2, 3, 4, 7).collect(Collectors.minBy(Integer::compareTo)).ifPresent(System.out::println);
        //maxBy(Comparator) 静态方法就返回一个用于求最大值的收集器
        Stream.of(1, 2, 3, 4, 7).collect(Collectors.maxBy(Integer::compareTo)).ifPresent(System.out::println);
        Stream.of(1, 2, 3, 4, 7).collect(Collectors.maxBy(Comparator.comparingInt(x -> x))).ifPresent(System.out::println);

        //特性化流的方法
        System.out.println(Stream.of(1, 2, 3, 4, 7).mapToInt(x -> x).sum());
        //reduce方法
        Stream.of(1, 2, 3, 4, 7)
                //使用方法引用Integer::sum来表示求和的意图
                .reduce(Integer::sum)
                .ifPresent(System.out::println);


        //现在，collect操作也提供求和功能，并且已经提供了预定于的收集器

        //summingInt(ToIntFunction) 静态方法就返回一个用于求int数据和的收集器，返回int类型的值
        System.out.println(Stream.of(1, 2, 3, 4, 7)
                .collect(Collectors.summingInt(x -> x)));

        //summingLong(ToLongFunction) 静态方法就返回一个用于求long数据和的收集器，返回long类型的值
        System.out.println(Stream.of(1, 2, 3, 4, 7)
                .collect(Collectors.summingLong(x -> x)));

        //summingDouble(ToDoubleFunction) 静态方法就返回一个用于求double数据和的收集器，返回double类型的值
        System.out.println(Stream.of(1, 2, 3, 4, 7)
                .collect(Collectors.summingDouble(x -> x)));

        //现在，count操作还提供求平均数功能，并且已经提供了预定于的收集器

        //averagingInt(ToIntFunction) 静态方法就返回一个用于求int数据算术平均数的收集器
        System.out.println(Stream.of(1, 2, 3, 4, 7)
                .collect(Collectors.averagingInt(x -> x)));

        //averagingLong(ToLongFunction) 静态方法就返回一个用于求long数据算术平均数的收集器
        System.out.println(Stream.of(1, 2, 3, 4, 7)
                .collect(Collectors.averagingLong(x -> x)));

        //averagingDouble(ToDoubleFunction) 静态方法就返回一个用于求double数据算术平均数的收集器
        System.out.println(Stream.of(1, 2, 3, 4, 7)
                .collect(Collectors.averagingDouble(x -> x)));


        //summarizingInt(ToIntFunction) 静态方法就返回一个用于求int数据的总和、平均值、最大值和最小值的收集器
        //返回IntSummaryStatistics对象，内部收集了所有的值
        System.out.println(Stream.of(1, 2, 3, 4, 7)
                .collect(Collectors.summarizingInt(x -> x)));

        //summarizingLong(ToLongFunction) 静态方法就返回一个用于求long数据的总和、平均值、最大值和最小值的收集器
        //返回LongSummaryStatistics对象，内部收集了所有的值
        System.out.println(Stream.of(1, 2, 3, 4, 7)
                .collect(Collectors.summarizingLong(x -> x)));

        //summarizingDouble(ToDoubleFunction) 静态方法就返回一个用于求double数据的总和、平均值、最大值和最小值的收集器
        //返回DoubleSummaryStatistics对象，内部收集了所有的值
        System.out.println(Stream.of(1, 2, 3, 4, 7)
                .collect(Collectors.summarizingDouble(x -> x)));
    }

    /**
     * 连接字符串
     */
    private static void test13() {
        System.out.println(Stream.of("校花","小花","晓华","笑话").collect(Collectors.joining()));
        System.out.println(String.join("", "校花","小花","晓华","笑话"));

        //joining(CharSequence delimiter)
        System.out.println(Stream.of("校花","小花","晓华","笑话").collect(Collectors.joining("——")));
        System.out.println(String.join("——", "校花","小花","晓华","笑话"));

        //joining(CharSequence delimiter, CharSequence prefix, CharSequence suffix)
        System.out.println(Stream.of("校花","小花","晓华","笑话").collect(Collectors.joining("——","开始：","。结束")));
    }
}
