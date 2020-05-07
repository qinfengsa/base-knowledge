package com.qinfengsa.algorithm.util;

/**
 * 排序工具类
 * SortUtils
 * @author wangheng
 * @date 2019/3/23 9:38
 */
public class SortUtils {

	/**
	 * 交换数组中的元素
	 * @param sortArray
	 * @param a
	 * @param b
	 */
	public static void swap(int[] sortArray,int a,int b) {
		int len = sortArray.length;
		if (a < 0 || a >= len) {
			return;
		}
		if (b < 0 || b >= len) {
			return;
		}
		int tmpValue = sortArray[a];
		sortArray[a] = sortArray[b];
		sortArray[b] = tmpValue;
	}
}
