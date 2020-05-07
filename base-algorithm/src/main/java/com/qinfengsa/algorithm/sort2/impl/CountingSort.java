package com.qinfengsa.algorithm.sort2.impl;

import com.qinfengsa.algorithm.sort2.ISort;

/**
 * 计数排序
 * @author: qinfengsa
 * @date: 2019/8/9 06:50
 */
public class CountingSort implements ISort {


    @Override
    public int[] sort(int[] sortArray) {
        int len  = sortArray.length;
        // 核心思想，用空间换取时间, 只能对整形排序
        // 求数组的最大和最小值
        int max = sortArray[0], min = sortArray[0];
        for (int num : sortArray) {
            if (num > max) {
                max = num;
            }
            if (num < min) {
                min = num;
            }
        }
        int countingArrayLen = max - min + 1;
        // 构造一个 min ~ max 的数组
        int[] countingArray = new int[countingArrayLen];
        // 根据sortArray中元素的值打到countingArray对应的位置
        for (int num : sortArray) {
            countingArray[num-min]++;
        }
        int index = 0;
        // 遍历countingArray, 按数量遍历，放入排序数组中
        for (int i = 0; i < countingArrayLen; i++) {
            int count = countingArray[i];
            for (int j = 0; j < count; j++) {
                sortArray[index] = min + i;
                index++;
            }
        }
        return sortArray;
    }
}
