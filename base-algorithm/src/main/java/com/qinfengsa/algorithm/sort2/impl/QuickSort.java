package com.qinfengsa.algorithm.sort2.impl;

import com.qinfengsa.algorithm.sort2.ISort;
import com.qinfengsa.algorithm.util.SortUtils;
import lombok.extern.slf4j.Slf4j;

/**
 * 快速排序
 * @author: qinfengsa
 * @date: 2019/7/31 12:46
 */
@Slf4j
public class QuickSort implements ISort {
    @Override
    public int[] sort(int[] sortArray) {
        int len = sortArray.length;
        // 核心思想。找到元素x(一般使用第一个元素), 使左边的元素小于x,右边的元素大于x;
        // 左右两边重复之前的操作，分而治之
        quickSort(sortArray,0,len-1);

        return sortArray;
    }

    private void quickSort(int[] sortArray,int start,int end) {
        // 如果是相邻的两个元素，直接比较就可以了
        if (end - start <= 1) {
            if (end - start == 1 && sortArray[start] > sortArray[start+1]) {
                SortUtils.swap(sortArray,start,start+1);
            }
            return;
        }
        int left = start,right = end;
        int key = sortArray[start];
        while (left < right ) {
            // 从后往前寻找第一个小于key的数，赋值给left
            while (left < right && sortArray[right] >= key) {
                right--;
            }
            sortArray[left] = sortArray[right];
            // 从前往后寻找第一个大于key的数，赋值给right
            while (left < right && sortArray[left] <= key) {
                left++;
            }
            sortArray[right] = sortArray[left];

        }
        // left 和 right相遇，把key赋值到中央位置
        // 这样保证左边的元素小于key，右边的元素大于key
        sortArray[left] = key;

        // 递归, 分而治之
        quickSort(sortArray,start, left - 1);
        quickSort(sortArray,left + 1, end);
    }
}
