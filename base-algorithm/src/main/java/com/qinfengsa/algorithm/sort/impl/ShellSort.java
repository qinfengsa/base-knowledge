package com.qinfengsa.algorithm.sort.impl;

import com.qinfengsa.algorithm.sort.ISort;

/**
 * 希尔排序
 * ShellSort
 *
 * @author wangheng
 * @date 2019/3/23 10:39
 */
public class ShellSort  implements ISort {
	@Override
	public int[] sort(int[] sortArray) {
		int len = sortArray.length;

		// 希尔排序，通过优化插入排序，让相距较远的元素优先做比较
		int shellNum = len/2;
		while (shellNum > 0) {
			for (int i = 0; i < len; i = i + shellNum) {
				int tmp = sortArray[i];
				int index = i;
				while (index >= shellNum && sortArray[index - shellNum] > tmp ) {
					sortArray[index] = sortArray[index - shellNum];
					index = index - shellNum;
				}
				sortArray[index] = tmp;
			}
			shellNum--;
		}

		return sortArray;
	}
}
