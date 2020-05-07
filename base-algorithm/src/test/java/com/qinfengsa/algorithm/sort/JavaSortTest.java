package com.qinfengsa.algorithm.sort;

import com.qinfengsa.algorithm.enumtype.SortTypeEnum;
import org.junit.Test;


/**
 * 排序测试类
 * JavaSortTest
 * @author wangheng
 * @date 2019/3/7 8:59
 */
public class JavaSortTest {

	@Test
	public void sortTest() {

		int[] sortArray = {422, 235, 338, 555, 137, 777, 358, 490, 742, 356, 840, 162, 902, 326, 224, 581, 459, 703, 406, 548, 442, 784, 150, 2, 801, 568, 75, 118, 87, 827, 18, 750, 600, 336, 879, 287, 537, 482, 219, 723, 203, 386, 483, 242, 56, 804, 88, 987, 709, 723, 677, 872, 392, 201, 948, 305, 338, 744, 720, 220, 7, 743, 301, 997, 761, 31, 621, 27, 577, 252, 988, 239, 996, 208, 673, 149, 46, 648, 491, 539, 710, 27, 586, 696, 531, 143, 22, 423, 152, 493, 957, 358, 369, 81, 573, 692, 8, 1, 308, 328};
		//int[] sortArray = {82, 26, 79, 64, 37, 72, 39, 93, 25, 38};
		ISort javaSort = JavaSortFactory.getSortMethod(SortTypeEnum.SHELL_SORT);
		Long startTime = System.currentTimeMillis();
		sortArray = javaSort.sort(sortArray);
		Long endTime = System.currentTimeMillis();
		Long time = endTime - startTime;
		System.out.println("排序时间：" + time);
		for (int i = 0; i < sortArray.length; i++) {
			System.out.print(sortArray[i] + ",");
		}
	}

	@Test
	public void test1() {
		int heapHeight = 3;

		int heapLen = 1<<heapHeight - 1;
		int heapLen2 = (1<<heapHeight) - 1;
		System.out.println(heapLen);
		System.out.println(heapLen2);
		//for (int i = heapLen - 1; i >= 0; i--) {
			// 左子结点2 * i + 1
			//int leftIndex = i<<1 + 1;
			// 右子结点2 * i + 2
			//int rightIndex = i<<1 + 2;
	}


	@Test
	public void sortTest2() {

		//int[] sortArray = {422, 235, 338, 555, 137, 777, 358, 490, 742, 356, 840, 162, 902, 326, 224, 581, 459, 703, 406, 548, 442, 784, 150, 2, 801, 568, 75, 118, 87, 827, 18, 750, 600, 336, 879, 287, 537, 482, 219, 723, 203, 386, 483, 242, 56, 804, 88, 987, 709, 723, 677, 872, 392, 201, 948, 305, 338, 744, 720, 220, 7, 743, 301, 997, 761, 31, 621, 27, 577, 252, 988, 239, 996, 208, 673, 149, 46, 648, 491, 539, 710, 27, 586, 696, 531, 143, 22, 423, 152, 493, 957, 358, 369, 81, 573, 692, 8, 1, 308, 328};
		int[] sortArray = {82, 26, 79, 64, 37, 72, 39, 93, 25, 38};
		ISort javaSort = JavaSortFactory.getSortMethod(SortTypeEnum.INSERTION_SORT);
		Long startTime = System.currentTimeMillis();
		sortArray = javaSort.sort(sortArray);
		Long endTime = System.currentTimeMillis();
		Long time = endTime - startTime;
		System.out.println("排序时间：" + time);
		for (int i = 0; i < sortArray.length; i++) {
			System.out.print(sortArray[i] + ",");
		}
	}
}
