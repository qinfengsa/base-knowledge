package com.qinfengsa.algorithm.math;

import java.util.TreeSet;
import lombok.extern.slf4j.Slf4j;

/**
 * 数学
 *
 * @author qinfengsa
 * @date 2021/05/15 23:39
 */
@Slf4j
public class MathMain {

    /**
     * 5743. 增长的内存泄露
     *
     * <p>给你两个整数 memory1 和 memory2 分别表示两个内存条剩余可用内存的位数。现在有一个程序每秒递增的速度消耗着内存。
     *
     * <p>在第 i 秒（秒数从 1 开始），有 i 位内存被分配到 剩余内存较多 的内存条（如果两者一样多，则分配到第一个内存条）。如果两者剩余内存都不足 i 位，那么程序将 意外退出 。
     *
     * <p>请你返回一个数组，包含 [crashTime, memory1crash, memory2crash] ，其中 crashTime是程序意外退出的时间（单位为秒），
     * memory1crash 和 memory2crash 分别是两个内存条最后剩余内存的位数。
     *
     * <p>示例 1：
     *
     * <p>输入：memory1 = 2, memory2 = 2 输出：[3,1,0] 解释：内存分配如下： - 第 1 秒，内存条 1 被占用 1 位内存。内存条 1 现在有 1
     * 位剩余可用内存。 - 第 2 秒，内存条 2 被占用 2 位内存。内存条 2 现在有 0 位剩余可用内存。 - 第 3 秒，程序意外退出，两个内存条分别有 1 位和 0 位剩余可用内存。
     * 示例 2：
     *
     * <p>输入：memory1 = 8, memory2 = 11 输出：[6,0,4] 解释：内存分配如下： - 第 1 秒，内存条 2 被占用 1 位内存，内存条 2 现在有 10
     * 位剩余可用内存。 - 第 2 秒，内存条 2 被占用 2 位内存，内存条 2 现在有 8 位剩余可用内存。 - 第 3 秒，内存条 1 被占用 3 位内存，内存条 1 现在有 5
     * 位剩余可用内存。 - 第 4 秒，内存条 2 被占用 4 位内存，内存条 2 现在有 4 位剩余可用内存。 - 第 5 秒，内存条 1 被占用 5 位内存，内存条 1 现在有 0
     * 位剩余可用内存。 - 第 6 秒，程序意外退出，两个内存条分别有 0 位和 4 位剩余可用内存。
     *
     * <p>提示：
     *
     * <p>0 <= memory1, memory2 <= 231 - 1
     *
     * @param memory1
     * @param memory2
     * @return
     */
    public int[] memLeak(int memory1, int memory2) {
        int[] result = new int[3];
        int i = 1;
        while (memory1 >= i || memory2 >= i) {
            if (memory1 >= i && memory2 >= i) {
                if (memory1 >= memory2) {
                    memory1 -= i;
                } else {
                    memory2 -= i;
                }

            } else if (memory1 >= i) {
                memory1 -= i;
            } else {
                memory2 -= i;
            }
            i++;
        }
        result[0] = i;
        result[1] = memory1;
        result[2] = memory2;

        return result;
    }

    /**
     * 1330. 翻转子数组得到最大的数组值
     *
     * <p>给你一个整数数组 nums 。「数组值」定义为所有满足 0 <= i < nums.length-1 的 |nums[i]-nums[i+1]| 的和。
     *
     * <p>你可以选择给定数组的任意子数组，并将该子数组翻转。但你只能执行这个操作 一次 。
     *
     * <p>请你找到可行的最大 数组值 。
     *
     * <p>示例 1：
     *
     * <p>输入：nums = [2,3,1,5,4] 输出：10 解释：通过翻转子数组 [3,1,5] ，数组变成 [2,5,1,3,4] ，数组值为 10 。 示例 2：
     *
     * <p>输入：nums = [2,4,9,24,2,1,10] 输出：68
     *
     * <p>提示：
     *
     * <p>1 <= nums.length <= 3*10^4 -10^5 <= nums[i] <= 10^5
     *
     * @param nums
     * @return
     */
    public int maxValueAfterReverse(int[] nums) {
        // 思路 子数组[i ~ j] 翻转 本身的数组值是不变的, 变动的是 i-1, i和 j, j + 1
        // 计算 变化前后的差值, 求出最大的变化值
        int sum = 0, len = nums.length;
        for (int i = 1; i < len; i++) {
            sum += Math.abs(nums[i] - nums[i - 1]);
        }
        int max = 0;
        // 计算 i == 0 或者 j == len -1  交换边界 的
        for (int i = 0; i < len; i++) {
            // 交换 0 ~ i;
            if (i < len - 1) {
                max =
                        Math.max(
                                max,
                                Math.abs(nums[i + 1] - nums[0]) - Math.abs(nums[i + 1] - nums[i]));
            }

            // 交换 i ~ len - 1;
            if (i > 0) {
                max =
                        Math.max(
                                max,
                                Math.abs(nums[len - 1] - nums[i - 1])
                                        - Math.abs(nums[i] - nums[i - 1]));
            }
        }
        int[] dirX = {1, 1, -1, -1}, dirY = {1, -1, 1, -1};
        // 原本的 val = Math.abs(nums[i] - nums[i - 1]) + Math.abs(nums[j + 1] - nums[j]);
        // 新的 val = Math.abs(nums[j] - nums[i - 1]) + Math.abs(nums[j + 1] - nums[i]);

        //  Math.abs(nums[i] - nums[i - 1]) 和  Math.abs(nums[j + 1] - nums[j]) 是 固定的 可以 在 O(n)
        // 的时间复杂度求出

        // Math.abs(nums[j] - nums[i - 1]) + Math.abs(nums[j + 1] - nums[i])
        // = Math.max (nums[j] - nums[i - 1] + nums[j + 1] - nums[i],
        // - nums[j] + nums[i - 1] + nums[j + 1] - nums[i],
        // nums[j] - nums[i - 1] - nums[j + 1] + nums[i],
        // - nums[j] + nums[i - 1] - nums[j + 1] + nums[i])
        //
        // =>Math.max ( (- nums[i] - nums[i - 1]) - (- nums[j + 1] - nums[j] ) ,
        // (- nums[i] + nums[i - 1]) - (- nums[j + 1] + nums[j]),
        // (nums[i] - nums[i - 1]) - (nums[j + 1] - nums[j]) ,
        // (nums[i] + nums[i - 1]) - (nums[j + 1] + nums[j]))
        //
        // 规律 设 sum[i] = - nums[i] - nums[i - 1]  sum[j + 1] = - nums[j + 1] - nums[j]
        // 差值 = sum[i] - sum[j + 1] - (Math.abs(nums[i] - nums[i - 1]) + Math.abs(nums[j + 1] -
        // nums[j]))
        // =>  (sum[i] - Math.abs(nums[i] - nums[i - 1])) - (sum[j + 1] + Math.abs(nums[j + 1] -
        // nums[j]))

        // 求 (sum[i] - Math.abs(nums[i] - nums[i - 1])) 的 最大值
        // 和 (sum[j + 1] + Math.abs(nums[j + 1] - nums[j]))最小值 相减
        for (int k = 0; k < 4; k++) {
            TreeSet<Integer> treeSet1 = new TreeSet<>(), treeSet2 = new TreeSet<>();
            for (int i = 1; i < len; i++) {
                int num = dirX[k] * nums[i] + dirY[k] * nums[i - 1];
                int curAbs = Math.abs(nums[i] - nums[i - 1]);
                treeSet1.add(num - curAbs);
                treeSet2.add(num + curAbs);
            }
            int num1 = treeSet1.last(), num2 = treeSet2.first();
            max = Math.max(max, num1 - num2);
        }

        return sum + max;
    }
}
