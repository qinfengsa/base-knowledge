package com.qinfengsa.algorithm.sort.impl;

import com.qinfengsa.algorithm.sort.ISort;
import com.qinfengsa.algorithm.util.SortUtils;

/**
 * 冒泡排序
 * BubbleSort
 * @author qinfengsa
 * @date 2019/3/23 9:29
 */
public class BubbleSort implements ISort {


	@Override
	public int[] sort(int[] sortArray) {
		int len = sortArray.length - 1;
		for (int i = 0; i < len; i++) {
			for (int j = 0; j < len - i; j++) {
				if (sortArray[j] > sortArray[j+1]) {
					SortUtils.swap(sortArray,j,j+1);
				}
			}
		}
		return sortArray;
	}
}
