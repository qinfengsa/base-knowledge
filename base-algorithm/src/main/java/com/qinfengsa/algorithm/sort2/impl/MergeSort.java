package com.qinfengsa.algorithm.sort2.impl;

import com.qinfengsa.algorithm.sort2.ISort;
import com.qinfengsa.algorithm.util.SortUtils;

import java.util.Arrays;
import java.util.Objects;

/**
 * 归并排序
 * @author: qinfengsa
 * @date: 2019/7/31 12:45
 */
public class MergeSort implements ISort {
    @Override
    public int[] sort(int[] sortArray) {
        int len = sortArray.length;
        // 合并两个有序数组
        // 把数组拆分成两个，两个数组递归拆分成4个数组，当数组的大小小于等于2时，非常容易
        mergeSort(sortArray,0,len-1);
        return sortArray;
    }

    /**
     * 归并排序
     * @param sortArray
     * @param start
     * @param end
     */
    private void mergeSort(int[] sortArray,int start,int end) {
        int[] resultArray;
        // 当数组规模小于2时，非常容易
        if (end - start <= 1) {
            if (end - start == 1 && sortArray[start] > sortArray[start+1]) {
                SortUtils.swap(sortArray,start,start+1);
            }
            return;
        }
        int mid = start + (end - start)/2;
        mergeSort(sortArray,start,mid);
        mergeSort(sortArray,mid+1,end);
        // 合并有序数组
        merge(sortArray,start,mid,end);
    }

    /**
     * 合并两个有序数组 left - mid, mid+1 - right
     * @param sortArray
     * @param left
     * @param mid
     * @param right
     */
    private void merge(int[] sortArray,int left,int mid,int right) {
        int[] leftArray = Arrays.copyOfRange(sortArray,left,mid+1);
        int[] rightArray = Arrays.copyOfRange(sortArray,mid+1,right+1);
        int leftLen = leftArray.length;
        int rightLen = rightArray.length;
        int i = 0,j = 0;
        int index = left;
        while (index <= right) {
            if (i == leftLen) {
                sortArray[index++] = rightArray[j++];
                continue;
            }
            if (j == rightLen) {
                sortArray[index++] = leftArray[i++];
                continue;
            }
            if (leftArray[i] < rightArray[j]) {
                sortArray[index++] = leftArray[i++];
            } else {
                sortArray[index++] = rightArray[j++];
            }
        }

    }
}
