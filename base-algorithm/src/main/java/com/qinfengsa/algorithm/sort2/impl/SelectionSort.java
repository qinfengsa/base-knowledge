package com.qinfengsa.algorithm.sort2.impl;

import com.qinfengsa.algorithm.sort2.ISort;
import com.qinfengsa.algorithm.util.SortUtils;

/**
 * 选择排序
 * @author: qinfengsa
 * @date: 2019/7/31 12:45
 */
public class SelectionSort implements ISort {
    @Override
    public int[] sort(int[] sortArray) {
        // 选择排序，每次选出最小的数；
        int len = sortArray.length;
        for (int i = 0; i < len; i++ ) {
            int min = i;
            for (int j = i + 1; j < len; j++) {
                if (sortArray[j] < sortArray[min]) {
                    min = j;
                }
            }
            if (min != i) {
                SortUtils.swap(sortArray,i,min);
            }
        }
        return sortArray;
    }
}
