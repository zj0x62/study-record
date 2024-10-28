package com.example.demo.sort;

/**
 * @Author: zhoujing
 * @Date: 2024/10/21 10:59
 * @Description: 选择排序
 *               算法步骤：
 *               1. 首先在未排序序列中找到最小（大）元素，存放到排序序列的起始位置；
 *               2. 再从剩余未排序元素中继续寻找最小（大）元素，然后放到已排序序列的末尾；
 *               3. 重复第2步，直到所有元素均排序完毕。
 */
public class SelectionSort {

    public static void selectionSort(int[] arr) {
        int length = arr.length;
        for (int i = 0; i < length - 1; i++) {
            int min = i;
            for (int j = i + 1; j < length; j++) {
                if (arr[min] > arr[j]) {
                    min = j;
                }
            }
            if (min != i) {
                int tmp = arr[i];
                arr[i] = arr[min];
                arr[min] = tmp;
            }
        }
    }
}
