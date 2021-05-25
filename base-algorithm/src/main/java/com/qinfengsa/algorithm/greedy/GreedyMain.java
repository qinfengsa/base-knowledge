package com.qinfengsa.algorithm.greedy;

import java.util.Collections;
import java.util.PriorityQueue;
import lombok.extern.slf4j.Slf4j;

/**
 * 贪心算法
 *
 * @author qinfengsa
 * @date 2021/5/21 9:49
 */
@Slf4j
public class GreedyMain {

    /**
     * 1326. 灌溉花园的最少水龙头数目
     *
     * <p>在 x 轴上有一个一维的花园。花园长度为 n，从点 0 开始，到点 n 结束。
     *
     * <p>花园里总共有 n + 1 个水龙头，分别位于 [0, 1, ..., n] 。
     *
     * <p>给你一个整数 n 和一个长度为 n + 1 的整数数组 ranges ，其中 ranges[i] （下标从 0 开始）表示：如果打开点 i 处的水龙头，可以灌溉的区域为 [i -
     * ranges[i], i + ranges[i]] 。
     *
     * <p>请你返回可以灌溉整个花园的 最少水龙头数目 。如果花园始终存在无法灌溉到的地方，请你返回 -1 。
     *
     * <p>示例 1：
     *
     * <p>输入：n = 5, ranges = [3,4,1,1,0,0] 输出：1 解释： 点 0 处的水龙头可以灌溉区间 [-3,3] 点 1 处的水龙头可以灌溉区间 [-3,5] 点
     * 2 处的水龙头可以灌溉区间 [1,3] 点 3 处的水龙头可以灌溉区间 [2,4] 点 4 处的水龙头可以灌溉区间 [4,4] 点 5 处的水龙头可以灌溉区间 [5,5] 只需要打开点
     * 1 处的水龙头即可灌溉整个花园 [0,5] 。 示例 2：
     *
     * <p>输入：n = 3, ranges = [0,0,0,0] 输出：-1 解释：即使打开所有水龙头，你也无法灌溉整个花园。 示例 3：
     *
     * <p>输入：n = 7, ranges = [1,2,1,0,2,1,0,1] 输出：3 示例 4：
     *
     * <p>输入：n = 8, ranges = [4,0,0,0,0,0,0,0,4] 输出：2 示例 5：
     *
     * <p>输入：n = 8, ranges = [4,0,0,0,4,0,0,0,4] 输出：1
     *
     * <p>提示：
     *
     * <p>1 <= n <= 10^4 ranges.length == n + 1 0 <= ranges[i] <= 100
     *
     * @param n
     * @param ranges
     * @return
     */
    public int minTaps(int n, int[] ranges) {
        int[] nums = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            if (ranges[i] == 0) {
                continue;
            }
            int start = Math.max(0, i - ranges[i]), end = Math.min(n, i + ranges[i]);

            nums[start] = Math.max(nums[start], end);
        }
        log.debug("nums:{}", nums);
        // 45. 跳跃游戏II 的变形
        int start = 0, end = 0, result = 0;
        while (end < n) {
            int max = end;
            for (int i = start; i <= end; i++) {
                if (nums[i] > max) {
                    max = nums[i];
                }
            }
            if (max == end) {
                return -1;
            }
            start = end + 1;
            end = max;
            result++;
        }

        return result;
    }

    /**
     * 1354. 多次求和构造目标数组
     *
     * <p>给你一个整数数组 target 。一开始，你有一个数组 A ，它的所有元素均为 1 ，你可以执行以下操作：
     *
     * <p>令 x 为你数组里所有元素的和 选择满足 0 <= i < target.size 的任意下标 i ，并让 A 数组里下标为 i 处的值为 x 。 你可以重复该过程任意次 如果能从
     * A 开始构造出目标数组 target ，请你返回 True ，否则返回 False 。
     *
     * <p>示例 1：
     *
     * <p>输入：target = [9,3,5] 输出：true 解释：从 [1, 1, 1] 开始 [1, 1, 1], 和为 3 ，选择下标 1 [1, 3, 1], 和为 5，
     * 选择下标 2 [1, 3, 5], 和为 9， 选择下标 0 [9, 3, 5] 完成 示例 2：
     *
     * <p>输入：target = [1,1,1,2] 输出：false 解释：不可能从 [1,1,1,1] 出发构造目标数组。 示例 3：
     *
     * <p>输入：target = [8,5] 输出：true
     *
     * <p>提示：
     *
     * <p>N == target.length 1 <= target.length <= 5 * 10^4 1 <= target[i] <= 10^9
     *
     * @param target
     * @return
     */
    public boolean isPossible(int[] target) {
        int len = target.length;
        if (len == 1) {
            return target[0] == 1;
        }
        // 最大的元素一定是上一轮被替换的元素 且该元素的值一定是上一轮所有元素之和
        long sum = 0L;
        PriorityQueue<Long> queue = new PriorityQueue<>(Collections.reverseOrder());
        for (int num : target) {
            queue.offer((long) num);
            sum += num;
        }
        while (!queue.isEmpty()) {
            long num = queue.poll();
            // 最大值为1
            if (num == 1L) {
                break;
            }
            // 上一轮的 和是 num 除了自己其他元素的和 otherSum
            long otherSum = sum - num;
            if (otherSum == 0) {
                return false;
            }
            // 上一轮的 num 是 num - otherSum
            if (num <= otherSum) {
                // long lastNum = num - otherSum <= 0;
                return false;
            }
            // 当前 可以循环 cnt -1 论
            long cnt = num / otherSum;
            long lastNum = num - Math.max(cnt - 1, 1L) * otherSum;
            sum -= Math.max(cnt - 1, 1L) * otherSum;

            queue.offer(lastNum);
        }

        return true;
    }
}
