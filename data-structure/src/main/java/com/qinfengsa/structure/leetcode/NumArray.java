package com.qinfengsa.structure.leetcode;


import static com.qinfengsa.structure.util.LogUtils.logResult;
/**
 * 307. 区域和检索 - 数组可修改
 * 给定一个整数数组  nums，求出数组从索引 i 到 j  (i ≤ j) 范围内元素的总和，包含 i,  j 两点。
 *
 * update(i, val) 函数可以通过将下标为 i 的数值更新为 val，从而对数列进行修改。
 *
 * 示例:
 *
 * Given nums = [1, 3, 5]
 *
 * sumRange(0, 2) -> 9
 * update(1, 2)
 * sumRange(0, 2) -> 8
 * 说明:
 *
 * 数组仅可以在 update 函数下进行修改。
 * 你可以假设 update 函数与 sumRange 函数的调用次数是均匀分布的。
 * @author qinfengsa
 * @create 2020/05/12 11:47
 */
public class NumArray {

    int[] sums;

    int[] nums;


    public NumArray(int[] nums) {
        this.nums = nums;
        sums = new int[nums.length + 1];
        for (int i = 0; i < nums.length; i++) {
            sums[i + 1] = sums[i] + nums[i];
        }
    }

    public void update(int i, int val) {
        int div = val - this.nums[i];
        for (int j = i + 1; j < sums.length; j++) {
            sums[j] += div;
        }
        this.nums[i] = val;
    }

    public int sumRange(int i, int j) {

        return sums[j + 1] - sums[i];
    }

    public static void main(String[] args) {
        int[] nums = {1, 3, 5};
        NumArray numArray = new NumArray(nums);
        logResult(numArray.sumRange(0, 2));
        numArray.update(1, 2);
        logResult(numArray.sumRange(0, 2));
    }
}
