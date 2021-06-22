package com.qinfengsa.algorithm.greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
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

    /**
     * 1383. 最大的团队表现值
     *
     * <p>公司有编号为 1 到 n 的 n 个工程师，给你两个数组 speed 和 efficiency ，其中 speed[i] 和 efficiency[i] 分别代表第 i
     * 位工程师的速度和效率。请你返回由最多 k 个工程师组成的 最大团队表现值 ，由于答案可能很大，请你返回结果对 10^9 + 7 取余后的结果。
     *
     * <p>团队表现值 的定义为：一个团队中「所有工程师速度的和」乘以他们「效率值中的最小值」。
     *
     * <p>示例 1：
     *
     * <p>输入：n = 6, speed = [2,10,3,1,5,8], efficiency = [5,4,3,9,7,2], k = 2 输出：60 解释： 我们选择工程师
     * 2（speed=10 且 efficiency=4）和工程师 5（speed=5 且 efficiency=7）。他们的团队表现值为 performance = (10 + 5) *
     * min(4, 7) = 60 。 示例 2：
     *
     * <p>输入：n = 6, speed = [2,10,3,1,5,8], efficiency = [5,4,3,9,7,2], k = 3 输出：68 解释：
     * 此示例与第一个示例相同，除了 k = 3 。我们可以选择工程师 1 ，工程师 2 和工程师 5 得到最大的团队表现值。表现值为 performance = (2 + 10 + 5) *
     * min(5, 4, 7) = 68 。 示例 3：
     *
     * <p>输入：n = 6, speed = [2,10,3,1,5,8], efficiency = [5,4,3,9,7,2], k = 4 输出：72
     *
     * <p>提示：
     *
     * <p>1 <= n <= 10^5 speed.length == n efficiency.length == n 1 <= speed[i] <= 10^5 1 <=
     * efficiency[i] <= 10^8 1 <= k <= n
     *
     * @param n
     * @param speed
     * @param efficiency
     * @param k
     * @return
     */
    public int maxPerformance(int n, int[] speed, int[] efficiency, int k) {
        // 枚举
        List<Engineer> list = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            list.add(new Engineer(speed[i], efficiency[i]));
        }
        // 按 效率 从高到低排序
        list.sort((a, b) -> b.efficiency - a.efficiency);

        // 构建 最小 堆 记录 speed
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        long result = 0, speedSum = 0;
        for (int i = 0; i < n; i++) {
            if (heap.size() == k) {
                speedSum -= heap.poll();
            }
            Engineer eg = list.get(i);
            int sp = eg.speed, ef = eg.efficiency;
            speedSum += sp;
            heap.offer(sp);

            result = Math.max(result, speedSum * ef);
        }

        return (int) (result % MOD);
    }

    static int MOD = 1_000_000_007;

    static class Engineer {
        int speed, efficiency;

        public Engineer(int speed, int efficiency) {
            this.speed = speed;
            this.efficiency = efficiency;
        }
    }

    /**
     * 1840. 最高建筑高度
     *
     * <p>在一座城市里，你需要建 n 栋新的建筑。这些新的建筑会从 1 到 n 编号排成一列。
     *
     * <p>这座城市对这些新建筑有一些规定：
     *
     * <p>每栋建筑的高度必须是一个非负整数。 第一栋建筑的高度 必须 是 0 。 任意两栋相邻建筑的高度差 不能超过 1 。
     * 除此以外，某些建筑还有额外的最高高度限制。这些限制会以二维整数数组 restrictions 的形式给出，其中 restrictions[i] = [idi, maxHeighti]
     * ，表示建筑 idi 的高度 不能超过 maxHeighti 。
     *
     * <p>题目保证每栋建筑在 restrictions 中 至多出现一次 ，同时建筑 1 不会 出现在 restrictions 中。
     *
     * <p>请你返回 最高 建筑能达到的 最高高度 。
     *
     * <p>示例 1：
     *
     * <p>输入：n = 5, restrictions = [[2,1],[4,1]] 输出：2 解释：上图中的绿色区域为每栋建筑被允许的最高高度。 我们可以使建筑高度分别为
     * [0,1,2,1,2] ，最高建筑的高度为 2 。 示例 2：
     *
     * <p>输入：n = 6, restrictions = [] 输出：5 解释：上图中的绿色区域为每栋建筑被允许的最高高度。 我们可以使建筑高度分别为 [0,1,2,3,4,5]
     * ，最高建筑的高度为 5 。 示例 3：
     *
     * <p>输入：n = 10, restrictions = [[5,3],[2,5],[7,4],[10,3]] 输出：5 解释：上图中的绿色区域为每栋建筑被允许的最高高度。
     * 我们可以使建筑高度分别为 [0,1,2,3,3,4,4,5,4,3] ，最高建筑的高度为 5 。
     *
     * <p>提示：
     *
     * <p>2 <= n <= 109 0 <= restrictions.length <= min(n - 1, 105) 2 <= idi <= n idi 是 唯一的 。 0 <=
     * maxHeighti <= 109
     *
     * @param n
     * @param restrictions
     * @return
     */
    public int maxBuilding(int n, int[][] restrictions) {
        int len = restrictions.length;
        if (len == 0) {
            return n - 1;
        }

        Arrays.sort(restrictions, Comparator.comparingInt(a -> a[0]));

        restrictions[len - 1][1] = Math.min(restrictions[len - 1][1], restrictions[len - 1][0] - 1);
        for (int i = len - 2; i >= 0; i--) {
            // 比较 i 和 i - 1 之间 的 楼
            int leftId = restrictions[i][0], rightId = restrictions[i + 1][0];
            // 第一栋必须是0 最高 restrictions[i][0] - 1
            restrictions[i][1] = Math.min(restrictions[i][1], leftId - 1);
            // 右边 一路递增
            restrictions[i][1] =
                    Math.min(restrictions[i][1], restrictions[i + 1][1] + rightId - leftId);
        }

        // 比较 restrictions[0] 之前的 最高
        int maxHeight = (restrictions[0][1] + restrictions[0][0] - 1) >> 1;
        for (int i = 1; i < len; i++) {
            // 比较 i 和 i - 1 之间 的 楼 最大
            int leftId = restrictions[i - 1][0], rightId = restrictions[i][0];
            // 左边 一路递增
            restrictions[i][1] =
                    Math.min(restrictions[i][1], restrictions[i - 1][1] + rightId - leftId);

            // 左右 中间 最高
            int height = (restrictions[i - 1][1] + restrictions[i][1] + rightId - leftId) >> 1;
            maxHeight = Math.max(maxHeight, height);
        }
        // len - 1 之后的楼
        maxHeight = Math.max(maxHeight, restrictions[len - 1][1] + n - restrictions[len - 1][0]);
        return maxHeight;
    }

    /**
     * LCS 02. 完成一半题目
     *
     * <p>有 N 位扣友参加了微软与力扣举办了「以扣会友」线下活动。主办方提供了 2*N 道题目，整型数组 questions 中每个数字对应了每道题目所涉及的知识点类型。
     * 若每位扣友选择不同的一题，请返回被选的 N 道题目至少包含多少种知识点类型。
     *
     * <p>示例 1：
     *
     * <p>输入：questions = [2,1,6,2]
     *
     * <p>输出：1
     *
     * <p>解释：有 2 位扣友在 4 道题目中选择 2 题。 可选择完成知识点类型为 2 的题目时，此时仅一种知识点类型 因此至少包含 1 种知识点类型。
     *
     * <p>示例 2：
     *
     * <p>输入：questions = [1,5,1,3,4,5,2,5,3,3,8,6]
     *
     * <p>输出：2
     *
     * <p>解释：有 6 位扣友在 12 道题目中选择题目，需要选择 6 题。 选择完成知识点类型为 3、5 的题目，因此至少包含 2 种知识点类型。
     *
     * <p>提示：
     *
     * <p>questions.length == 2*n 2 <= questions.length <= 10^5 1 <= questions[i] <= 1000
     *
     * @param questions
     * @return
     */
    public int halfQuestions(int[] questions) {
        int n = questions.length >> 1;
        int result = 0;
        int[] counts = new int[1001];
        for (int question : questions) {
            counts[question]++;
        }
        Arrays.sort(counts);
        for (int i = counts.length - 1; i >= 0; i--) {
            int count = counts[i];
            result++;
            n -= count;
            if (n <= 0) {
                break;
            }
        }

        return result;
    }
}
