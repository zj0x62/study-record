package com.example.demo.sort;

/**
 * @Author: zhoujing
 * @Date: 2024/10/21 10:37
 * @Description: 冒泡排序
 *               算法步骤：
 *               1. 比较相邻的元素，如果第一个比第二个大，就交换它们两个；
 *               2. 对每一对相邻元素作同样的比价，从开始第一对到结尾的最后一对，这样在最后的元素就是最大的数；
 *               3. 针对所有的元素重复以上的步骤，除了数组最后已经排好序的数组；
 *               4. 重复步骤1~3，直到排序完成。
 */
public class BubbleSort {

    public static void bubbleSort(int[] arr) {
        int length = arr.length;
        for (int i = 0; i < length - 1; i++) {
            boolean flag = true;
            for (int j = 0; j < length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                    flag = false;
                }
            }
            if (flag) {
                break;
            }
        }
    }
}
