package com.qinfengsa.algorithm.sort2.impl;

import com.qinfengsa.algorithm.sort2.ISort;
import com.qinfengsa.algorithm.util.SortUtils;
import lombok.extern.slf4j.Slf4j;

/**
 * 堆排序
 * @author: qinfengsa
 * @date: 2019/7/31 12:46
 */
@Slf4j
public class HeapSort implements ISort {

    /**
     * 堆的最小长度
     */
    static final int MIN_HEAP = 2;

    @Override
    public int[] sort(int[] sortArray) {
        heapSort(sortArray);
        return sortArray;
    }

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
        for (int i = sortArray.length / 2 - 1; i >= 0; i--) {
            down(sortArray, sortArray.length, i);
        }
        for (int i = sortArray.length - 1; i > 0; i--) {
            SortUtils.swap(sortArray, 0, i);
            down(sortArray, i, 0);
        }
    }



    void down(int[] arr, int n, int pos) {
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
    }


    /**
     * 构建指定长度的最大堆，得到最大值，并把堆顶元素和尾元素交换
     * @param sortArray
     * @param len
     */
    private void createMaxHeap(int[] sortArray,int len) {
        if (len <= MIN_HEAP) {
            return;
        }
        // 计算堆高度 1 + 2 + 2^2 + 2^3 …… 2^n = 2^(n+1) -1
        int heapHeight = (int) Math.floor(Math.log((double) len)/Math.log(2.0));
        // 位运算的优先级比 +- 低
        int heapLen = (1<<heapHeight) - 1;
        for (int i = heapLen; i >= 0; i--) {
            int leftChild = (i<<1) + 1;
            int rightChild = (i<<1) + 2;
            if (rightChild < len && sortArray[rightChild] > sortArray[i]) {
                SortUtils.swap(sortArray, rightChild, i);

            }
            if (leftChild < len && sortArray[leftChild] > sortArray[i]) {
                SortUtils.swap(sortArray, leftChild, i);

            }


        }

    }
}
