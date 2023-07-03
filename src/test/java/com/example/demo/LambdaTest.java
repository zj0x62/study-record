package com.example.demo;

import org.junit.Test;

/**
 * @Author zhoujing
 * @Date 2022/8/3 15:12
 * @Desciption: lambda测试
 */
public class LambdaTest {

    @Test
    public void test() {
        engine(((int x, int y) -> x + y));
        engine(((long x, long y) -> x - y));
        engine(((int x, int y) -> x * y));
        engine(((long x, long y) -> x / y));
    }

    public void engine(IntCalculator calculator) {
        int x = 2, y = 4;
        int result = calculator.calculator(x, y);
        System.out.println(result);
    }

    public void engine(LongCalculator calculator) {
        long x = 10, y = 5;
        long result = calculator.calculator(x, y);
        System.out.println(result);
    }

    public interface IntCalculator {
        int calculator(int x, int y);
    }

    public interface LongCalculator {
        long calculator(long x, long y);
    }
}
