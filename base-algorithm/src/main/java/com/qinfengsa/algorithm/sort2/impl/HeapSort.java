package com.qinfengsa.algorithm.sort2.impl;

import com.qinfengsa.algorithm.sort2.ISort;
import com.qinfengsa.algorithm.util.SortUtils;
import lombok.extern.slf4j.Slf4j;

/**
 * 堆排序
 *
 * @author: qinfengsa
 * @date: 2019/7/31 12:46
 */
@Slf4j
public class HeapSort implements ISort {

    /** 堆的最小长度 */
    static final int MIN_HEAP = 2;

    @Override
    public int[] sort(int[] sortArray) {
        heapSort(sortArray);
        return sortArray;
    }

    private void heapSort(int[] sortArray) {
        int len = sortArray.length;
        if (len < MIN_HEAP) {
            return;
        }
        if (len == MIN_HEAP) {
            if (sortArray[0] > sortArray[1]) {
                SortUtils.swap(sortArray, 0, 1);
            }
            return;
        }
        // 创建最大堆,堆顶是最大值
        createMaxHeap(sortArray);
        log.debug("heap:{}", sortArray);
        //
        for (int i = sortArray.length - 1; i > 0; i--) {
            // 交换堆顶和最后一位
            SortUtils.swap(sortArray, 0, i);
            // 当前堆顶元素下移,移动到合适的位置
            down(sortArray, i, 0);
        }
        /*for (int i = len; i > 1; i--) {
            if (i == MIN_HEAP) {
                if (sortArray[0] > sortArray[1]) {
                    SortUtils.swap(sortArray, 0, 1);
                }
                return;
            } else {
                createMaxHeap(sortArray, i);
                SortUtils.swap(sortArray, 0, i - 1);
            }
        }
        for (int i = sortArray.length / 2 - 1; i >= 0; i--) {
            down(sortArray, sortArray.length, i);
        }
        for (int i = sortArray.length - 1; i > 0; i--) {
            SortUtils.swap(sortArray, 0, i);
            down(sortArray, i, 0);
        }*/
    }

    private void down(int[] sortArray, int n, int currIdx) {
        int num = sortArray[currIdx];
        for (int child = (currIdx << 1) + 1; child < n; child = (currIdx << 1) + 1) {
            // 左右两个子节点,找到较大的一个
            if (child < n - 1 && sortArray[child] < sortArray[child + 1]) {
                child++;
            }
            if (sortArray[child] <= num) {
                break;
            }

            sortArray[currIdx] = sortArray[child];
            currIdx = child;
        }
        sortArray[currIdx] = num;
    }

    /*void down(int[] arr, int n, int pos) {
        // 取出需要下滤值向下比较一直到叶子节点或比较完成
        int current = arr[pos];
        for (int child = 2 * pos + 1; child < n; child = 2 * pos + 1) {
            if (child != n - 1 && arr[child] < arr[child + 1]) {
                child += 1;
            }
            if (arr[child] <= current) {
                break;
            }

            arr[pos] = arr[child];
            pos = child;
        }
        arr[pos] = current;
    }*/

    /**
     * 构建最大堆，得到最大值，并把堆顶元素和尾元素交换
     *
     * @param sortArray
     */
    private void createMaxHeap(int[] sortArray) {
        // 计算堆高度 1 + 2 + 2^2 + 2^3 …… 2^n = 2^(n+1) -1
        int heapHeight = (int) Math.floor(Math.log((double) sortArray.length) / Math.log(2.0));
        // 位运算的优先级比 +- 低
        int heapLen = (1 << heapHeight) - 1;
        log.debug("heapLen:{}", heapLen);
        for (int i = heapLen; i >= 0; i--) {
            down(sortArray, sortArray.length, i);
            /*int child = (i << 1) + 1;
            if (child >= sortArray.length) {
                continue;
            }
            // 左右两个子节点,找到较大的一个
            if (child < sortArray.length - 1 && sortArray[child] < sortArray[child + 1]) {
                child++;
            }
            if (sortArray[child] > sortArray[i]) {
                SortUtils.swap(sortArray, child, i);
            }*/
            log.debug("heap:{}", sortArray);
        }
    }
}
