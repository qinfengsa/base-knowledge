package com.qinfengsa.algorithm.sort.impl;

import com.qinfengsa.algorithm.sort.ISort;

/**
 * 插入排序
 * InsertionSort
 * @author qinfengsa
 * @date 2019/3/23 10:39
 */
public class InsertionSort  implements ISort {
	@Override
	public int[] sort(int[] sortArray) {
		insertionSort(sortArray);
		return sortArray;
	}

	/**
	 * 插入排序
	 * @author qinfengsa
	 * @date 2019/3/23 13:42
	 * @param sortArray
	 */
	private void insertionSort(int[] sortArray) {
		int len = sortArray.length;
		for (int i = 1; i < len; i++) {
			int tmpValue = sortArray[i];
			int currIndex = i;
			for (int j = i - 1; j >= 0; j--) {
				if (tmpValue < sortArray[j]) {
					// 后移1为
					sortArray[j + 1] = sortArray[j];
					currIndex = j;
				}
			}
			sortArray[currIndex] = tmpValue;

		}


	}
}
