package com.qinfengsa.algorithm.sort2.impl;

import com.qinfengsa.algorithm.sort2.ISort;
import com.qinfengsa.algorithm.util.SortUtils;

/**
 * 冒泡排序
 * @author: qinfengsa
 * @date: 2019/7/31 12:45
 */
public class BubbleSort implements ISort {


    @Override
    public int[] sort(int[] sortArray) {
        // 冒泡排序
        int len = sortArray.length;
        for (int i = 0; i < len; i++) {
            // 从第一位开始遍历，每一位和后面作比较，把最大的数移动到最后
            for (int j = 0; j < len - 1 - i; j++) {

                if (sortArray[j] > sortArray[j+1]) {
                    SortUtils.swap(sortArray,j,j+1);
                }

            }

        }
        return sortArray;
    }
}
