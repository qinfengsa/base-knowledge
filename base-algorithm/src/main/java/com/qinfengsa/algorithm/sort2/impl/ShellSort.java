package com.qinfengsa.algorithm.sort2.impl;

import com.qinfengsa.algorithm.sort2.ISort;

/**
 * 希尔排序
 * @author: qinfengsa
 * @date: 2019/7/31 12:45
 */
public class ShellSort implements ISort {
    @Override
    public int[] sort(int[] sortArray) {
        int len = sortArray.length;
        // 希尔排序，通过优化插入排序，让相距较远的元素优先做比较
        int shellNum = len/2;
        while (shellNum >= 1) {
            for (int i = shellNum; i < len; i++) {
                int tmp = sortArray[i];
                int index = i;
                while (index >= shellNum && sortArray[index - shellNum] > tmp ) {
                    sortArray[index] = sortArray[index - shellNum];
                    index -= shellNum;
                }
                sortArray[index] = tmp;
            }
            shellNum = shellNum/2;
        }

        return sortArray;
    }
}
