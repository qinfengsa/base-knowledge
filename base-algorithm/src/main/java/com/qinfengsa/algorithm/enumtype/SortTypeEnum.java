package com.qinfengsa.algorithm.enumtype;

/**
 * @author qinfengsa
 * @className SortTypeEnum
 * @description 排序类型枚举
 * @date 2019/3/11 9:33
 */
public enum SortTypeEnum {

	/**
	 * 冒泡排序
	 */
	BUBBLE_SORT(1),
	/**
	 * 选择排序
	 */
	SELECTION_SORT(2),
	/**
	 * 插入排序
	 */
	INSERTION_SORT(3),
	/**
	 * 希尔排序
	 */
	SHELL_SORT(4),
	/**
	 * 归并排序
	 */
	MERGE_SORT(5),
	/**
	 * 快速排序
	 */
	QUICK_SORT(6),

	/**
	 * 堆排序
	 */
	HEAP_SORT(7),


	/**
	 * 桶排序
	 */
	BUCKET_SORT(8),

	/**
	 * 计数排序
	 */
	COUNTING_SORT(9),

	/**
	 * 基数排序
	 */
	RADIX_SORT(10),

	/**
	 * TIM排序
	 */
	TIM_SORT(11);

	int value;

	/**
	 * 排序类型
	 * @param value
	 */
	SortTypeEnum(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}
}
