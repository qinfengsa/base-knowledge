package com.qinfengsa.algorithm.greedy;

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
}
