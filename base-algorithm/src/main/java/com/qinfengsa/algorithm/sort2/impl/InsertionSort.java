package com.qinfengsa.algorithm.sort2.impl;

import com.qinfengsa.algorithm.sort2.ISort;

/**
 * 插入排序
 * @author: qinfengsa
 * @date: 2019/7/31 12:45
 */
public class InsertionSort implements ISort {
    @Override
    public int[] sort(int[] sortArray) {
        int len = sortArray.length;
        // 插入排序，类似我们打扑克时，整理扑克牌
        // 假设存在一个有序的序列(一般以第一个元素做有序)，我们只需要从后往前比，找到第一个比他小的元素，然后放到后面
        // 这时元素就被放到的正确的位置
        for (int i = 1; i < len; i++) {
            int tmp = sortArray[i];
            int index = i ;
            while (index > 0 && sortArray[index-1] > tmp) {
                sortArray[index] = sortArray[index-1];
                index--;
            }
            if (index < i ) {
                sortArray[index] = tmp;
            }

        }
        return sortArray;
    }
}
