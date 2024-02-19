package com.example.demo;

import org.springframework.util.StopWatch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: zhoujing
 * @Date: 2024/2/19 9:43
 * @Description: 动态规划 - 给定一个共有n阶的楼梯，你每步可以上1阶或者2阶，请问有多少种方案可以爬到楼顶？
 */
public class DynamicProgramming {

    public static void main(String[] args) {
        int n = 40;
        StopWatch stopWatch = new StopWatch("爬楼梯");

        // 回溯穷举
        stopWatch.start("回溯穷举");
        int backtrack = climbingStairsBacktrack(n);
        System.out.println("方案数量(回溯穷举)：" + backtrack);
        stopWatch.stop();

        // 暴力搜索
        stopWatch.start("暴力搜索");
        int dfs = climbingStairsDFS(n);
        System.out.println("方案数量(暴力搜索)：" + dfs);
        stopWatch.stop();

        // 记忆化搜索
        stopWatch.start("记忆化搜索");
        int dfsMem = climbingStairsDFSMem(n);
        System.out.println("方案数量(记忆化搜索)：" + dfsMem);
        stopWatch.stop();

        // 动态规划
        stopWatch.start("动态规划");
        int dp = climbingStairsDP(n);
        System.out.println("方案数量(动态规划)：" + dp);
        stopWatch.stop();

        // 动态规划-空间优化
        stopWatch.start("动态规划-空间优化");
        int dpComp = climbingStairsDPComp(n);
        System.out.println("方案数量(动态规划-空间优化)：" + dpComp);
        stopWatch.stop();

        System.out.println(stopWatch.prettyPrint());
    }

/**************************************************方法一：回溯穷举 start*************************************************/
    /**
     * 回溯
     * @param choices 每次可选择爬的阶数
     * @param state 当前楼梯的阶数
     * @param n 楼梯阶数
     * @param res res[0]为方案数量
     */
    private static void backtrack(List<Integer> choices, int state, int n, List<Integer> res) {
        // 当爬到第n阶时，方案数量加1
        if (state == n) {
            res.set(0, res.get(0) + 1);
        }
        // 遍历所有选择
        for (Integer choice : choices) {
            // 剪枝：不允许越过第n阶
            if (state + choice > n) {
                continue;
            }
            // 尝试：做出选择，更新状态
            backtrack(choices, state + choice, n, res);
        }
    }

    /**
     * 爬楼梯：回溯
     * @param n 楼梯阶数
     * @return 方案数量
     */
    private static int climbingStairsBacktrack(int n) {
        // 可选择向上爬1阶或2阶
        List<Integer> choices = Arrays.asList(1, 2);
        // 从第0阶开始爬
        int state = 0;
        List<Integer> res = new ArrayList<>();
        // 使用res[0]记录方案数量
        res.add(0);
        backtrack(choices, state, n, res);
        return res.get(0);
    }
/**************************************************方法一：回溯穷举 end***************************************************/

/**************************************************方法二：暴力搜索 start*************************************************/
    /**
     * 深度优先搜索
     * @param n 楼梯阶数
     * @return 方案数量
     */
    private static int dfs(int n) {
        // 已知dp[1]和dp[2]， 直接返回
        if (n == 1 || n ==2) {
            return n;
        }
        // dp[i] = dp[i-1] + dp[i-2]
        return dfs(n -1) + dfs(n -2);
    }

    /**
     * 爬楼梯：搜索
     * @param n 楼梯阶数
     * @return 方案数量
     */
    private static int climbingStairsDFS(int n) {
        return dfs(n);
    }
/**************************************************方法二：暴力搜索 end***************************************************/

/**************************************************方法三：记忆化搜索 start***********************************************/
    /**
     * 记忆化搜索
     * @param i 楼梯阶数
     * @param mem 记录每个子问题的解
     * @return 方案数量
     */
    private static int dfs(int i, int[] mem) {
        // 已知dp[1]和dp[2]， 直接返回
        if (i == 1 || i == 2) {
            return i;
        }
        // 若存在记录dp[i], 直接返回
        if (mem[i] != -1) {
            return mem[i];
        }
        // dp[i] = dp[i-1] + dp[i-2]
        int count = dfs(i -1 , mem) + dfs(i - 2, mem);
        // 记录dp[i]
        mem[i] = count;
        return count;
    }

    /**
     * 爬楼梯：记忆化搜索
     * @param n 楼梯阶数
     * @return 方案数量
     */
    private static int climbingStairsDFSMem(int n) {
        // mem[i]记录爬到第i阶的方案总数，-1表示无记录
        int[] mem = new int[n + 1];
        Arrays.fill(mem, -1);
        return dfs(n, mem);
    }
/**************************************************方法三：记忆化搜索 end*************************************************/

/**************************************************方法四：动态规划 start*************************************************/
    /**
     * 爬楼梯：动态规划
     * @param n 楼梯阶数
     * @return 方案数量
     */
    private static int climbingStairsDP(int n) {
        if (n == 1 || n == 2) {
            return n;
        }
        // 初始化dp表，用于存子问题的解
        int[] dp = new int[n + 1];
        // 初始状态：预设最小子问题的解
        dp[1] = 1;
        dp[2] = 2;
        // 状态转移：从较小子问题逐步求解较大子问题
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }
/**************************************************方法四：动态规划 end***************************************************/

/**************************************************方法五：动态规划-空间优化 start******************************************/
    /**
     * 爬楼梯：空间优化后的动态规划
     * @param n 楼梯阶数
     * @return 方案数量
     */
    private static int climbingStairsDPComp(int n) {
        if (n == 1 || n == 2) {
            return n;
        }
        int a = 1, b = 2;
        for (int i = 3; i <= n; i++) {
            int temp = b;
            b = a + b;
            a = temp;
        }
        return b;
    }
/**************************************************方法五：动态规划-空间优化 end********************************************/
}
