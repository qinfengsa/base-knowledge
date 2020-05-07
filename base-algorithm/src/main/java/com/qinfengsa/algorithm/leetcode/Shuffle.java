package com.qinfengsa.algorithm.leetcode;

import java.util.Random;

/**
 * Shuffle an Array
 * 打乱一个没有重复元素的数组。
 *
 * 示例:
 *
 * // 以数字集合 1, 2 和 3 初始化数组。
 * int[] nums = {1,2,3};
 * Solution solution = new Solution(nums);
 *
 * // 打乱数组 [1,2,3] 并返回结果。任何 [1,2,3]的排列返回的概率应该相同。
 * solution.shuffle();
 *
 * // 重设数组到它的初始状态[1,2,3]。
 * solution.reset();
 *
 * // 随机返回数组[1,2,3]打乱后的结果。
 * solution.shuffle();
 * @author: qinfengsa
 * @date: 2019/5/30 08:22
 */
public class Shuffle {

    private int[] numsArray;

    private int[] result;

    private int len;

    public Shuffle(int[] nums) {
        this.numsArray = nums;
        this.len = nums.length;
        result = new int[len];
        for (int i = 0; i < len; i++) {
            result[i] = nums[i];
        }
    }

    public int[] reset() {
        for (int i = 0; i < len; i++) {
            result[i] = numsArray[i];
        }
        return result;
    }

    /** Returns a random shuffling of the array. */
    public int[] shuffle() {
        Random random = new Random();
        for (int i = 1; i < len; i++) {
            int index = random.nextInt(i);
            swap(result,i,index);
        }
        return result;
    }

    private void swap(int[] nums, int i,int j ) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }


}
