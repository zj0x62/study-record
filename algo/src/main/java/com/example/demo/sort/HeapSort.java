package com.example.demo.sort;

/**
 * @Author: zhoujing
 * @Date: 2024/10/22 13:47
 * @Description: 堆排序
 *               算法步骤：
 *               1. 将待排序列（R0, R1,...,Rn）构建成最大堆（最小堆）；
 *               2. 将堆顶元素R[0]与最后一个元素R[n]进行交换，此时得到新的无序区（R0, R1,...,Rn-1）和新的有序区（Rn）,且满足R[0,1,...,n-1] <= R[n];
 *               3. 由于调整后的新堆可能违反堆的性质，因此需要对当前无序区（R0,R1,...,Rn-1）进行调整；
 *               4. 重复步骤2~3直到有序区的元素个数为n。
 */
public class HeapSort {

    private static int heapLen;

    public static void heapSort(int[] arr) {
        heapLen = arr.length;
        for (int i = heapLen - 1; i >= 0; i--) {
            heapify(arr, i);
        }
        for (int i = heapLen - 1; i > 0; i--) {
            swap(arr, 0, heapLen - 1);
            heapLen--;
            heapify(arr, 0);
        }
    }

    private static void heapify(int[] arr, int idx) {
        int left = idx * 2 + 1, right = idx * 2 + 2, largest = idx;
        if (left < heapLen && arr[left] > arr[largest]) {
            largest = left;
        }
        if (right < heapLen && arr[right] > arr[largest]) {
            largest = right;
        }
        if (largest != idx) {
            swap(arr, largest, idx);
            heapify(arr, largest);
        }
    }

    private static void swap(int[] arr, int idx1, int idx2) {
        int tmp = arr[idx1];
        arr[idx1] = arr[idx2];
        arr[idx2] = tmp;
    }
}
