package com.example.demo.sort;

/**
 * @Author: zhoujing
 * @Date: 2024/10/21 14:51
 * @Description: 快速排序
 *               算法步骤：
 *               1. 从序列中随机挑出一个元素，作为基准（pivot, 这里选择序列的最左边元素作为基准）
 *               2. 重新排列序列，将所有比基准值小的元素摆放在基准前面，所有比基准大的摆在基准的后面。该操作结束之后，该基准就处于数列的中间位置。这个操作称为分区（partition）;
 *               3. 递归地把小于基准值元素的子序列和大于基准值元素的子序列进行上述操作即可。
 */
public class QuickSort {

    public static void quickSort(int[] arr) {
        sort(arr, 0, arr.length - 1);
    }

    private static void sort(int[] arr, int left, int right) {
        if (left < right) {
            int pivotIdx = partition(arr, left, right);
            sort(arr, left, pivotIdx - 1);
            sort(arr, pivotIdx + 1, right);
        }
    }

    private static int partition(int[] arr, int left, int right) {
        int idx = left + 1;
        for (int i = idx; i <= right; i++) {
            if (arr[left] > arr[i]) {
                swap(arr, i, idx++);
            }
        }
        swap(arr, left, idx - 1);
        return idx - 1;
    }

    private static void swap(int[] arr, int idx1, int idx2) {
        int tmp = arr[idx1];
        arr[idx1] = arr[idx2];
        arr[idx2] = tmp;
    }
}
