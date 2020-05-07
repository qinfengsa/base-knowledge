package com.qinfengsa.structure.array;

import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

/**
 * 寻找数组的中心索引
 * 给定一个整数类型的数组 nums，请编写一个能够返回数组“中心索引”的方法。
 *
 * 我们是这样定义数组中心索引的：数组中心索引的左侧所有元素相加的和等于右侧所有元素相加的和。
 *
 * 如果数组不存在中心索引，那么我们应该返回 -1。如果数组有多个中心索引，那么我们应该返回最靠近左边的那一个。
 *
 * 示例 1:
 *
 * 输入:
 * nums = [1, 7, 3, 6, 5, 6]
 * 输出: 3
 * 解释:
 * 索引3 (nums[3] = 6) 的左侧数之和(1 + 7 + 3 = 11)，与右侧数之和(5 + 6 = 11)相等。
 * 同时, 3 也是第一个符合要求的中心索引。
 * 示例 2:
 *
 * 输入:
 * nums = [1, 2, 3]
 * 输出: -1
 * 解释:
 * 数组中不存在满足此条件的中心索引。
 * @author: qinfengsa
 * @date: 2019/5/8 13:24
 */
@Slf4j
public class ArrayIndex {

    public static void main(String[] args) {
        int[] nums = {1,2,1};
        ArrayIndex arrayIndex = new ArrayIndex();
        int index = arrayIndex.pivotIndex(nums);
        log.info("index:{}" ,index);
    }

    public int pivotIndex(int[] nums) {
        if (Objects.isNull(nums)) {
            return -1;
        }
        if (nums.length <= 1) {
            return -1;
        }
        int sum = getSum(nums);
        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            if ((sum - nums[i] - index) == index) {
                return i;
            }
            index += nums[i];
        }

        return -1;
    }

    private int getSum(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        return sum;
    }



}
