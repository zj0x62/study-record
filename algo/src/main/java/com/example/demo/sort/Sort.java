package com.example.demo.sort;

import org.springframework.util.StopWatch;

import java.util.Random;

/**
 * @Author: zhoujing
 * @Date: 2024/4/10 10:40
 * @Description:
 */
public class Sort {

    public static void main(String[] args) {
        Random random = new Random();
        int[] arr = new int[10000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(1000000) + 1;
        }



        StopWatch stopWatch = new StopWatch("排序算法");
//        stopWatch.start("冒泡排序");
//        bubbleSort(arr);
//        stopWatch.stop();

        stopWatch.start("选择排序");
        selectionSort(arr);
        stopWatch.stop();

        System.out.println(stopWatch.prettyPrint());
    }

    /**
     * 冒泡排序
     * flag 的作用是用来标记当前一轮冒泡排序是否发生了数据交换。在每一轮冒泡排序中，如果没有发生数据交换，则说明数组已经是有序的，
     * 无需继续进行后续的排序操作，可以直接退出循环，以提高排序的效率。
     */
    public static void bubbleSort(int[] arr) {
        int length = arr.length;
        for (int i = 0; i < length - 1; i++) {
            boolean flag = true;
            for (int j = 0; j < length - i - 1; j++) {
                if (arr[j] > arr[j +1 ]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    flag = false;
                }
            }
            if (flag) {
                break;
            }
        }
    }

    /**
     * 选择排序
     */
    public static void selectionSort(int[] arr) {
        int length = arr.length;
        for (int i = 0; i < length - 1; i++) {
            int minVal = i;
            for (int j = i + 1; j < length; j++) {
                if (arr[minVal] > arr[j]) {
                    minVal = j;
                }
            }

            if (minVal != i) {
                int temp = arr[i];
                arr[i] = arr[minVal];
                arr[minVal] = temp;
            }
        }
    }
}
