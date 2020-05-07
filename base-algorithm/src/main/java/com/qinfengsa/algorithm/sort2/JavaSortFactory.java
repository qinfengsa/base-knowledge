package com.qinfengsa.algorithm.sort2;

import com.qinfengsa.algorithm.enumtype.SortTypeEnum;
import com.qinfengsa.algorithm.sort2.impl.BubbleSort;
import com.qinfengsa.algorithm.sort2.impl.BucketSort;
import com.qinfengsa.algorithm.sort2.impl.CountingSort;
import com.qinfengsa.algorithm.sort2.impl.HeapSort;
import com.qinfengsa.algorithm.sort2.impl.InsertionSort;
import com.qinfengsa.algorithm.sort2.impl.MergeSort;
import com.qinfengsa.algorithm.sort2.impl.QuickSort;
import com.qinfengsa.algorithm.sort2.impl.RadixSort;
import com.qinfengsa.algorithm.sort2.impl.SelectionSort;
import com.qinfengsa.algorithm.sort2.impl.ShellSort;
import com.qinfengsa.algorithm.sort2.impl.TimSort;

/**
 * 排序工厂
 * JavaSortFactory
 * @author wangheng
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
			case BUCKET_SORT:
				return new BucketSort();
			case COUNTING_SORT:
				return new CountingSort();
			case RADIX_SORT:
				return new RadixSort();
			case TIM_SORT:
				return new TimSort();
			default:
				return null;
		}
	}
}
