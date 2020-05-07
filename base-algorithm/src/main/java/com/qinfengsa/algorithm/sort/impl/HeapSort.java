package com.qinfengsa.algorithm.sort.impl;

import com.qinfengsa.algorithm.sort.ISort;
import com.qinfengsa.algorithm.util.SortUtils;

/**
 * 堆排序
 * HeapSort
 * @author wangheng
 * @date 2019/3/23 10:40
 */
public class HeapSort implements ISort {

	/**
	 * 堆得最新长度
	 */
	static final int MIN_HEAP = 2;

	@Override
	public int[] sort(int[] sortArray) {
		heapSort(sortArray);
		return sortArray;
	}

	/**
	 * todo
	 * @author wangheng
	 * @date 2019/3/23 13:05
	 * @param sortArray
	 */
	private void heapSort(int[] sortArray) {
		int len = sortArray.length;
		for (int i = len; i > 1; i-- ) {
			if (i == MIN_HEAP) {
				if (sortArray[0] > sortArray[1]) {
					SortUtils.swap(sortArray,0,1);
				}
				return;
			} else {
				createMaxHeap(sortArray,i);
				SortUtils.swap(sortArray,0,i - 1);
			}

		}

	}

	/**
	 * 构建指定长度的最大堆，得到最大值，并把堆顶元素和尾元素交换
	 * @author wangheng
	 * @date 2019/3/23 12:02
	 * @param
	 */
	private void createMaxHeap(int[] sortArray,int len) {
		if (len <= MIN_HEAP) {
			return;
		}
		// 计算堆高度 1 + 2 + 2^2 + 2^3 …… 2^(n-1) = 2^n -1
		int heapHeight = (int) Math.floor(Math.log((double) len)/Math.log(2.0));
        // 位运算的优先级比 +-低
		int heapLen = (1<<heapHeight) - 1;
		for (int i = heapLen - 1; i >= 0; i--) {
			// 左子结点2 * i + 1
			int leftIndex = (i<<1) + 1;
			// 右子结点2 * i + 2
			int rightIndex = (i<<1) + 2;
			if (leftIndex < len && sortArray[leftIndex] > sortArray[i]) {
				SortUtils.swap(sortArray,leftIndex,i);
			}
			if (rightIndex < len && sortArray[rightIndex] > sortArray[i]) {
				SortUtils.swap(sortArray,rightIndex,i);
			}
		}

	}
}
