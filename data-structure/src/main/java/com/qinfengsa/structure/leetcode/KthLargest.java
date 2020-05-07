package com.qinfengsa.structure.leetcode;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Objects;

/**
 * Kth Largest Element in a Stream
 * 设计一个找到数据流中第K大元素的类（class）。注意是排序后的第K大元素，不是第K个不同的元素。
 *
 * 你的 KthLargest 类需要一个同时接收整数 k 和整数数组nums 的构造器，它包含数据流中的初始元素。每次调用 KthLargest.add，返回当前数据流中第K大的元素。
 *
 * 示例:
 *
 * int k = 3;
 * int[] arr = [4,5,8,2];
 * KthLargest kthLargest = new KthLargest(3, arr);
 * kthLargest.add(3);   // returns 4
 * kthLargest.add(5);   // returns 5
 * kthLargest.add(10);  // returns 5
 * kthLargest.add(9);   // returns 8
 * kthLargest.add(4);   // returns 8
 * 说明:
 * 你可以假设 nums 的长度≥ k-1 且k ≥ 1。
 * @author: qinfengsa
 * @date: 2019/5/15 22:51
 */
@Slf4j
public class KthLargest {


    // 1、构建长度为k的有序数组，报错前k个数据，然后新数据插入，用冒泡排序
    Integer[] topK ;
    // 2、用堆排序性能会好logN

    public KthLargest(int k, int[] nums) {

        topK = new Integer[k];

        if (nums.length == 0) {
            return;
        }

        // 选择排序，每次选择最大的 获取前k个元素
        int size = k < nums.length ? k :nums.length;
        for (int i = 0; i < size ; i++) {

            int index = i;
            for (int j = i + 1; j < nums.length; j++) {

                if (nums[j] > nums[index]) {
                    index = j;
                }
            }

            // 交换
            int tmp = nums[i];
            nums[i] = nums[index];
            nums[index] = tmp;

            topK[k-1-i] = nums[i];

        }

        log.debug("topK:{}", Arrays.toString(topK));
    }


    public int add(int val) {
        Integer num = topK[0];

        if (Objects.isNull(num)) {
            topK[0] = val;
            sort();
            // val > 第一位，重排序
        } else if (val > num) {
            topK[0] = val;
            sort();
        }
        log.debug("topK:{}", Arrays.toString(topK));
        return topK[0];
    }

    private void sort() {
        int size = topK.length;
        if (size < 2) {
            return;
        }
        for (int i = 0; i < size - 1; i++ ) {

            if (topK[i] > topK[i+1]) {
                // 交换
                int tmp = topK[i];
                topK[i] = topK[i+1];
                topK[i+1] = tmp;
            }

        }

    }


}
