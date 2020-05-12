package com.qinfengsa.algorithm.sort;

import com.qinfengsa.algorithm.enumtype.SortTypeEnum;
import com.qinfengsa.algorithm.sort.impl.*;

/**
 * 排序工厂
 * JavaSortFactory
 * @author qinfengsa
 * @date 2019/3/23 10:38
 */
public class JavaSortFactory {

	/**
	 * 排序
	 * @param method
	 * @return
	 */
	public static ISort getSortMethod(SortTypeEnum method) {
		switch (method) {
			case BUBBLE_SORT :
				return new BubbleSort();
			case SELECTION_SORT :
				return new SelectionSort();
			case INSERTION_SORT :
				return new InsertionSort();
			case SHELL_SORT :
				return new ShellSort();
			case MERGE_SORT :
				return new MergeSort();
			case QUICK_SORT:
				return new QuickSort();
			case HEAP_SORT:
				return new HeapSort();
			default:
				return null;
		}
	}
}
