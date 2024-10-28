package com.example.demo.sort;

import org.springframework.util.StopWatch;

import java.util.Arrays;
import java.util.Random;

/**
 * @Author: zhoujing
 * @Date: 2024/10/21 11:10
 * @Description:
 */
public class SortTest {

    public static void main(String[] args) {
        int[] arr = generate(50, 0, 10000);
        int[] bubbleArr = Arrays.copyOf(arr, arr.length);
        int[] selectionArr = Arrays.copyOf(arr, arr.length);
        int[] insertionArr = Arrays.copyOf(arr, arr.length);
        int[] quickArr = Arrays.copyOf(arr, arr.length);
        int[] heapArr = Arrays.copyOf(arr, arr.length);

        System.out.println("排序前：");
        System.out.println(Arrays.toString(arr));

        StopWatch stopWatch = new StopWatch("排序算法");

        stopWatch.start("冒泡排序");
        BubbleSort.bubbleSort(bubbleArr);
        stopWatch.stop();

        stopWatch.start("选择排序");
        SelectionSort.selectionSort(selectionArr);
        stopWatch.stop();

        stopWatch.start("插入排序");
        InsertionSort.insertionSort(insertionArr);
        stopWatch.stop();

        stopWatch.start("快速排序");
        QuickSort.quickSort(quickArr);
        stopWatch.stop();

        stopWatch.start("堆排序");
        HeapSort.heapSort(heapArr);
        stopWatch.stop();

        System.out.println(stopWatch.prettyPrint());
    }

    public static int[] generate(int length, int min, int max) {
        int[] arr = new int[length];
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            int value = random.nextInt(max - min + 1) + min;
            arr[i] = value;
        }
        return arr;
    }
}
