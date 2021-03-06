package com.qinfengsa.algorithm.dynamic;

import static com.qinfengsa.algorithm.util.LogUtils.logResult;

import com.qinfengsa.algorithm.util.MathUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * 动态规划
 *
 * @author: qinfengsa
 * @date: 2019/8/10 23:20
 */
@Slf4j
public class DynamicPlanTest {

    // 动态规划有两种等价的实现方法,下面以钢条切割问题为例展示这两种方法.
    // 第一种方法称为带备忘的自顶向下法( top-down with memoization)°.带备忘录的递归
    //
    // 第二种方法称为自底向上法( bottom-up method).这种方法一般需要恰当定义子问题"规模"
    /** 动态规划1，切割绳子 */
    @Test
    public void ropeCutting() {
        Map<Integer, Integer> priceMap =
                new HashMap<Integer, Integer>() {
                    {
                        put(1, 1);
                        put(2, 5);
                        put(3, 8);
                        put(4, 9);
                        put(5, 10);
                        put(6, 17);
                        put(7, 17);
                        put(8, 20);
                        put(9, 24);
                        put(10, 30);
                    }
                };
        int[] arrayLen = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int[] arrayPrice = {1, 5, 8, 9, 10, 17, 17, 20, 24, 30};
        int n = 4;
        int result = ropeCutting(arrayLen, arrayPrice, n);
        log.debug("result:{}", result);
    }

    /**
     * 钢条切割问题是这样的: 给定一段长度为n英寸的钢条和一个价格表p,(i=1,2,…,n),求切割钢条方案,使得销售收益rn最大.
     * 注意,如果长度为n英寸的钢条的价格p.足够大,最优解可能就是完全不需要切割.
     *
     * @param arrayLen 长度
     * @param arrayPrice 价格
     * @param n
     * @return
     */
    private int ropeCutting(int[] arrayLen, int[] arrayPrice, int n) {
        // 动态规划，对于上述价格表样例,我们可以观察所有最优收益值r(i=1,2,……10)及对应的最优切割方案:
        // r1=1,切割方案1=1(无切割)
        // r2=5,切割方案2=2(无切割)
        // r3=8,切割方案3=3(无切割)
        // r4=10,切割方案4=2+2
        // r5=13,切割方案5=2+3
        // r6=17,切割方案6=6(无切割)
        // r7=18,切割方案7=1+6或7=2+2+3
        // r8=22,切割方案8=2+6
        // r9=25,切割方案9=3+6
        // r10=30,切割方案10=10(无切割)
        // 更一般地,对于rn(n≥1),我们可以用更短的钢条的最优切割收益来描述它:
        // max(p,,n+rmI,r2 +rr2,",rmI+n)

        // 我们  求长度为N的最优方案，只需要把1~N-1的最优方案记录下来，通过1~N-1中的两个组合就可以了

        int[] maxValue = new int[n];

        int size = arrayLen.length;
        for (int i = 0; i < size; i++) {
            int len = arrayLen[i];
            if (len > 0 && len < n) {
                maxValue[len - 1] = arrayPrice[i];
            }
        }

        for (int i = 1; i < n; i++) {

            int max = maxValue[i];
            for (int j = 0; j < size; j++) {
                int len = arrayLen[j];
                if (i + 1 - len > 0) {
                    int value = maxValue[i - len] + arrayPrice[j];
                    max = Math.max(max, value);
                }
            }

            maxValue[i] = max;
        }
        return maxValue[n - 1];
    }

    @Test
    public void coinChange() {
        int[] coins = {235, 326, 180, 11, 61, 483, 464, 125, 403, 241};
        int amount = 5926;

        // int[] coins = {1,2,5};
        // int amount = 100;
        int result = coinChange(coins, amount);
        log.debug("result:{}", result);
    }

    /**
     * 零钱兑换 给定不同面额的硬币 coins 和一个总金额 amount。编写一个函数来计算可以凑成总金额所需的最少的硬币个数。如果没有任何一种硬币组合能组成总金额，返回 -1。 示例 1:
     *
     * <p>输入: coins = [1, 2, 5], amount = 11 输出: 3 解释: 11 = 5 + 5 + 1 示例 2:
     *
     * <p>输入: coins = [2], amount = 3 输出: -1 说明: 你可以认为每种硬币的数量是无限的。
     *
     * @param coins
     * @param amount
     * @return
     */
    public int coinChange(int[] coins, int amount) {

        if (amount < 0) {
            return -1;
        }
        if (amount == 0) {
            return 0;
        }
        // 思路，amout 金额的最少硬币个数 等价于
        // amout - 1 最少硬币个数 + 1
        // amout - 2 最少硬币个数 + 1
        // amout - 5 最少硬币个数 + 1
        int[] arrayAmount = new int[amount + 1];
        for (int i = 0; i <= amount; i++) {
            arrayAmount[i] = Integer.MAX_VALUE;
        }
        for (int c : coins) {
            if (c <= amount) {
                arrayAmount[c] = 1;
            }
        }
        for (int i = 2; i <= amount; i++) {
            int min = arrayAmount[i];
            for (int c : coins) {
                if (i - c > 0) {
                    int value = arrayAmount[i - c];
                    if (value != Integer.MAX_VALUE) {
                        min = Math.min(min, value + 1);
                    }
                }
            }
            arrayAmount[i] = min;
        }

        int result = arrayAmount[amount];
        if (result == Integer.MAX_VALUE) {
            result = -1;
        }

        return result;
    }

    private int coinChange2(int[] coins, int amount) {
        if (amount < 1) return 0;
        return coinChange(coins, amount, new int[amount]);
    }

    private int coinChange(int[] coins, int rem, int[] count) {
        if (rem < 0) return -1;
        if (rem == 0) return 0;
        if (count[rem - 1] != 0) return count[rem - 1];
        int min = Integer.MAX_VALUE;
        for (int coin : coins) {
            int res = coinChange(coins, rem - coin, count);
            if (res >= 0 && res < min) min = 1 + res;
        }
        count[rem - 1] = (min == Integer.MAX_VALUE) ? -1 : min;
        return count[rem - 1];
    }

    @Test
    public void canJump() {

        int[] nums = {3, 2, 1, 0, 4};

        boolean result = canJump(nums);

        log.debug("result :{}", result);
    }

    /**
     * 跳跃游戏 给定一个非负整数数组，你最初位于数组的第一个位置。
     *
     * <p>数组中的每个元素代表你在该位置可以跳跃的最大长度。
     *
     * <p>判断你是否能够到达最后一个位置。
     *
     * <p>示例 1:
     *
     * <p>输入: [2,3,1,1,4] 输出: true 解释: 从位置 0 到 1 跳 1 步, 然后跳 3 步到达最后一个位置。 示例 2:
     *
     * <p>输入: [3,2,1,0,4] 输出: false 解释: 无论怎样，你总会到达索引为 3 的位置。但该位置的最大跳跃长度是 0 ， 所以你永远不可能到达最后一个位置。
     *
     * @param nums
     * @return
     */
    public boolean canJump(int[] nums) {

        int len = nums.length;
        /*if (len <= 1) {
            return true;
        }

        boolean[] canJumpArray = new boolean[len];

        canJumpArray[0] = true;
        for (int i = 0; i < len - 1; i++) {
            if (!canJumpArray[i]) {
                continue;
            }
            int num = nums[i];
            for (int j = 1; j <= num; j++) {
                if (i + j < len) {
                    canJumpArray[i + j] = true;
                }

            }

        }

        return canJumpArray[len - 1];*/

        int pos = nums.length - 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            if (i + nums[i] >= pos) {
                pos = i;
            }
        }
        return pos == 0;
    }

    @Test
    public void jump() {
        int[] nums = {2, 3, 1, 1, 4};

        logResult(jump(nums));
    }

    /**
     * 45. 跳跃游戏 II 给定一个非负整数数组，你最初位于数组的第一个位置。
     *
     * <p>数组中的每个元素代表你在该位置可以跳跃的最大长度。
     *
     * <p>你的目标是使用最少的跳跃次数到达数组的最后一个位置。
     *
     * <p>示例:
     *
     * <p>输入: [2,3,1,1,4] 输出: 2 解释: 跳到最后一个位置的最小跳跃数是 2。 从下标为 0 跳到下标为 1 的位置，跳 1 步，然后跳 3 步到达数组的最后一个位置。
     * 说明:
     *
     * <p>假设你总是可以到达数组的最后一个位置。
     *
     * @param nums
     * @return
     */
    public int jump(int[] nums) {
        int len = nums.length;
        int start = 0, end = 0, step = 0;
        while (end < len - 1) {
            int max = end;
            for (int i = start; i <= end; i++) {
                if (nums[i] + i > max) max = nums[i] + i;
            }
            start = end + 1;
            end = max;
            step++;
        }

        return step;
        /* int[] jumpArrays = new int[len];
        jumpArrays[0] = 0;
        for (int i = 0; i < len - 1; i++) {
            int jumpStep = jumpArrays[i];
            int num = nums[i];
            for (int j = 1; j <= num; j++) {
                if (i + j < len) {
                    int jumpStep2 = jumpArrays[i + j];
                    if (jumpStep2 == 0) {
                        jumpArrays[i + j] = jumpStep + 1;
                    } else {
                        jumpArrays[i + j] = Math.min(jumpStep + 1,jumpStep2);
                    }
                }

            }
        }

        return jumpArrays[len - 1];*/
    }

    @Test
    public void findTargetSumWays() {
        int[] nums = {7, 9, 3, 8, 0, 2, 4, 8, 3, 9};
        int S = 1;
        int result = findTargetSumWays(nums, S);
        log.debug("result:{}", result);
    }

    @Test
    public void climbStairs() {
        int n = 44;
        int result = climbStairs(n);
        log.debug("result:{}", result);
    }

    /**
     * 爬楼梯 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
     *
     * <p>每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
     *
     * <p>注意：给定 n 是一个正整数。
     *
     * <p>示例 1：
     *
     * <p>输入： 2 输出： 2 解释： 有两种方法可以爬到楼顶。 1. 1 阶 + 1 阶 2. 2 阶 示例 2：
     *
     * <p>输入： 3 输出： 3 解释： 有三种方法可以爬到楼顶。 1. 1 阶 + 1 阶 + 1 阶 2. 1 阶 + 2 阶 3. 2 阶 + 1 阶
     *
     * @param n
     * @return
     */
    public int climbStairs(int n) {
        if (n == 1) {
            return 1;
        }
        // 动态规划，需要记录之前的解
        int[] resArray = new int[n];

        resArray[0] = 1; // n = 1 1种
        resArray[1] = 2; // n = 2 2种

        for (int i = 2; i < n; i++) {
            int a = resArray[i - 2]; // 从i-2 -> i
            int b = resArray[i - 1]; // 从i-1 -> i

            resArray[i] = a + b;
        }

        return resArray[n - 1];
    }

    @Test
    public void maxProfitTest() {

        int[] prices = {7, 1, 5, 3, 6, 4};

        int result = maxProfit(prices);

        log.debug("result :{}", result);
    }

    /**
     * 买卖股票的最佳时机 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
     *
     * <p>如果你最多只允许完成一笔交易（即买入和卖出一支股票），设计一个算法来计算你所能获取的最大利润。
     *
     * <p>注意你不能在买入股票前卖出股票。
     *
     * <p>示例 1:
     *
     * <p>输入: [7,1,5,3,6,4] 输出: 5 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
     * 注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格。 示例 2:
     *
     * <p>输入: [7,6,4,3,1] 输出: 0 解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
     *
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        // 只能买入一次
        int maxprofit = 0;
        if (prices.length == 0) {
            return 0;
        }
        /* for (int i = 0; i < prices.length - 1; i++) {

            for (int j = i + 1; j < prices.length; j++) {
                int profit = prices[j] - prices[i];
                if (profit > maxprofit) {
                    maxprofit = profit;
                }
            }

        }*/
        // 低价和高价
        int low = prices[0], high = prices[0];
        for (int price : prices) {
            if (price < low) { // 存在低价，高价只能取之后的数据
                low = price;
                high = price;
            } else if (price > high) {
                high = price;
                if (high - low > maxprofit) {
                    maxprofit = high - low;
                }
            }
        }
        return maxprofit;
    }

    @Test
    public void maxProfit2Test() {

        int[] prices = {6, 1, 3, 2, 4, 7};

        int result = maxProfit2(prices);

        log.debug("result :{}", result);
    }

    /**
     * 买卖股票的最佳时机 II
     *
     * <p>给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
     *
     * <p>设计一个算法来计算你所能获取的最大利润。你可以尽可能地完成更多的交易（多次买卖一支股票）。
     *
     * <p>注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
     *
     * <p>示例 1:
     *
     * <p>输入: [7,1,5,3,6,4] 输出: 7 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 3 天（股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1
     * = 4 。 随后，在第 4 天（股票价格 = 3）的时候买入，在第 5 天（股票价格 = 6）的时候卖出, 这笔交易所能获得利润 = 6-3 = 3 。 示例 2:
     *
     * <p>输入: [1,2,3,4,5] 输出: 4 解释: 在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 =
     * 4 。 注意你不能在第 1 天和第 2 天接连购买股票，之后再将它们卖出。 因为这样属于同时参与了多笔交易，你必须在再次购买前出售掉之前的股票。 示例 3:
     *
     * <p>输入: [7,6,4,3,1] 输出: 0 解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
     *
     * @param prices
     * @return
     */
    public int maxProfit2(int[] prices) {

        int maxprofit = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1]) {
                maxprofit += prices[i] - prices[i - 1];
            }
        }
        return maxprofit;

        /*int result = 0;

        int size = prices.length;

        int left = 0;
        int right = 1;

        while (left < right && right < size) {
            int price1 = prices[left];
            int price2 = prices[right];
            boolean isBuy = false;
            if (right < size - 1) {
                // 后面的价格比前面低
                if (prices[right + 1] < price2) {
                    isBuy = true;
                }
            } else {
                isBuy = true;
            }
            if (price1 < price2 && isBuy) {
                result +=(price2 - price1);
                left = right + 1;
                right = left + 1;
            } else if (price1 < price2) {
                right++;
            } else {
                left = right;
                right = left + 1;
            }

        }


        return result;*/
    }

    @Test
    public void maxSubArray() {

        int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};

        int result = maxSubArray(nums);

        log.debug("result :{}", result);
    }

    /**
     * 最大子序和 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
     *
     * <p>示例:
     *
     * <p>输入: [-2,1,-3,4,-1,2,1,-5,4], 输出: 6 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。 进阶:
     *
     * <p>如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的分治法求解。
     *
     * @param nums
     * @return
     */
    public int maxSubArray(int[] nums) {
        int result = 0;
        int sum = 0;

        for (int num : nums) {
            sum = sum > 0 ? sum + num : num;
            if (result < sum) {
                result = sum;
            }
        }
        return result;
    }

    @Test
    public void rob() {

        int[] nums = {2, 7, 9, 3, 1, 2};

        int result = rob(nums);

        log.debug("result :{}", result);
    }

    /**
     * 打家劫舍
     * 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
     *
     * <p>给定一个代表每个房屋存放金额的非负整数数组，计算你在不触动警报装置的情况下，能够偷窃到的最高金额。
     *
     * <p>示例 1:
     *
     * <p>输入: [1,2,3,1] 输出: 4 解释: 偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。 偷窃到的最高金额 = 1 + 3 = 4 。 示例
     * 2:
     *
     * <p>输入: [2,7,9,3,1] 输出: 12 解释: 偷窃 1 号房屋 (金额 = 2), 偷窃 3 号房屋 (金额 = 9)，接着偷窃 5 号房屋 (金额 = 1)。
     * 偷窃到的最高金额 = 2 + 9 + 1 = 12 。
     *
     * @param nums
     * @return
     */
    public int rob(int[] nums) {

        int len = nums.length;
        if (len == 0) {
            return 0;
        }
        if (len == 1) {
            return nums[0];
        }

        // 动态规划，记录每个房间的最大收益
        int[] robs = new int[len];
        robs[0] = nums[0];
        robs[1] = nums[1] > nums[0] ? nums[1] : nums[0];
        for (int i = 2; i < len; i++) {
            // 当前房屋的金额 + i - 2 号的最大金额
            int a = robs[i - 2] + nums[i];
            int b = robs[i - 1];

            robs[i] = a > b ? a : b;
        }

        int result = robs[len - 1] > robs[len - 2] ? robs[len - 1] : robs[len - 2];

        return result;
    }

    @Test
    public void uniquePaths() {
        int m = 3, n = 2;
        int result = uniquePaths(m, n);
        log.debug("result :{}", result);
    }

    /**
     * 不同路径 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。
     *
     * <p>机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。
     *
     * <p>问总共有多少条不同的路径？
     *
     * <p>例如，上图是一个7 x 3 的网格。有多少可能的路径？
     *
     * <p>说明：m 和 n 的值均不超过 100。
     *
     * <p>示例 1:
     *
     * <p>输入: m = 3, n = 2 输出: 3 解释: 从左上角开始，总共有 3 条路径可以到达右下角。 1. 向右 -> 向右 -> 向下 2. 向右 -> 向下 -> 向右 3.
     * 向下 -> 向右 -> 向右 示例 2:
     *
     * <p>输入: m = 7, n = 3 输出: 28
     *
     * @param m
     * @param n
     * @return
     */
    public int uniquePaths(int m, int n) {
        int[][] pashArray = new int[n][m];

        for (int i = 0; i < n; i++) {
            pashArray[i][0] = 1;
        }
        for (int j = 0; j < m; j++) {
            pashArray[0][j] = 1;
        }

        for (int i = 1; i < n; i++) {

            for (int j = 1; j < m; j++) {
                pashArray[i][j] = pashArray[i - 1][j] + pashArray[i][j - 1];
            }
        }
        int result = pashArray[n - 1][m - 1];
        return result;
    }

    @Test
    public void uniquePathsWithObstacles() {
        int[][] obstacleGrid = {{0, 0, 0}, {0, 1, 0}, {0, 0, 0}};
        logResult(uniquePathsWithObstacles(obstacleGrid));
    }

    /**
     * 63. 不同路径 II 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。
     *
     * <p>机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。
     *
     * <p>现在考虑网格中有障碍物。那么从左上角到右下角将会有多少条不同的路径？
     *
     * <p>网格中的障碍物和空位置分别用 1 和 0 来表示。
     *
     * <p>说明：m 和 n 的值均不超过 100。
     *
     * <p>示例 1:
     *
     * <p>输入: [ [0,0,0], [0,1,0], [0,0,0] ] 输出: 2 解释: 3x3 网格的正中间有一个障碍物。 从左上角到右下角一共有 2 条不同的路径： 1. 向右
     * -> 向右 -> 向下 -> 向下 2. 向下 -> 向下 -> 向右 -> 向右
     *
     * @param obstacleGrid
     * @return
     */
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        // 思路 动态规划
        int rows = obstacleGrid.length;
        if (rows == 0) {
            return 0;
        }
        int cols = obstacleGrid[0].length;
        int routeArray[][] = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (i == 0 && j == 0) {
                    routeArray[i][j] = 1;
                    continue;
                }
                // 上方路径数
                int upRoute = 0;
                // 左方路径数
                int leftRoute = 0;
                // 上方不是障碍物
                if (i > 0 && obstacleGrid[i - 1][j] == 0) {
                    upRoute = routeArray[i - 1][j];
                }
                // 左方方不是障碍物
                if (j > 0 && obstacleGrid[i][j - 1] == 0) {
                    leftRoute = routeArray[i][j - 1];
                }
                routeArray[i][j] = upRoute + leftRoute;
            }
        }

        return routeArray[rows - 1][cols - 1];
    }

    @Test
    public void testAr() {
        int[][] pashArray = new int[7][3];
        log.debug("a:{}", pashArray.length);
        log.debug("b:{}", pashArray[0].length);
    }

    @Test
    public void maxProfit3() {
        int[] prices = {1, 2, 4};
        int result = maxProfit3(prices);
        log.debug("result:{}", result);
    }

    /**
     * Best Time to Buy and Sell Stock with Cooldown 给定一个整数数组，其中第 i 个元素代表了第 i 天的股票价格 。
     *
     * <p>设计一个算法计算出最大利润。在满足以下约束条件下，你可以尽可能地完成更多的交易（多次买卖一支股票）:
     *
     * <p>你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。 卖出股票后，你无法在第二天买入股票 (即冷冻期为 1 天)。 示例:
     *
     * <p>输入: [1,2,3,0,2] 输出: 3 解释: 对应的交易状态为: [买入, 卖出, 冷冻期, 买入, 卖出]
     *
     * @param prices
     * @return
     */
    public int maxProfit3(int[] prices) {
        int len = prices.length;
        if (len == 0) {
            return 0;
        }
        // 0 表示卖出, 1 表示买入
        int[][] profit = new int[len][2];
        profit[0][0] = 0;
        profit[0][1] = -prices[0];
        int maxprofit = 0;
        for (int i = 1; i < len; i++) {
            // 第i天的利润（卖出） = 第i-1天的利润（卖出）  第i-1天的利润（买入） + i天的价格
            profit[i][0] = Math.max(profit[i - 1][0], profit[i - 1][1] + prices[i]);
            // 第i天的利润（买入） = 第i-1天的利润（买入）  第i-2天的利润（卖出） - i天的价格
            profit[i][1] =
                    Math.max(
                            profit[i - 1][1],
                            i - 2 < 0 ? -prices[i] : profit[i - 2][0] - prices[i]);
        }
        for (int[] a : profit) {
            log.debug("a:{}", a);
        }
        return Math.max(profit[len - 1][0], profit[len - 1][1]);
    }

    @Test
    public void maxCoins() {
        int[] nums = {3, 1, 5, 8};
        int result = maxCoins(nums);
        log.debug("result:{}", result);
    }

    /**
     * 戳气球 有 n 个气球，编号为0 到 n-1，每个气球上都标有一个数字，这些数字存在数组 nums 中。
     *
     * <p>现在要求你戳破所有的气球。每当你戳破一个气球 i 时，你可以获得 nums[left] * nums[i] * nums[right] 个硬币。 这里的 left 和 right
     * 代表和 i 相邻的两个气球的序号。注意当你戳破了气球 i 后，气球 left 和气球 right 就变成了相邻的气球。
     *
     * <p>求所能获得硬币的最大数量。
     *
     * <p>说明:
     *
     * <p>你可以假设 nums[-1] = nums[n] = 1，但注意它们不是真实存在的所以并不能被戳破。 0 ≤ n ≤ 500, 0 ≤ nums[i] ≤ 100 示例:
     *
     * <p>输入: [3,1,5,8] 输出: 167 解释: nums = [3,1,5,8] --> [3,5,8] --> [3,8] --> [8] --> [] coins =
     * 3*1*5 + 3*5*8 + 1*3*8 + 1*8*1 = 167
     *
     * @param nums
     * @return
     */
    public int maxCoins(int[] nums) {
        int len = nums.length + 2;
        int[] newNums = new int[len];
        System.arraycopy(nums, 0, newNums, 1, nums.length);
        newNums[0] = 1;
        newNums[len - 1] = 1;
        // f(0,len-1) = max {f(0,i) + f(i,len-1) + nums[0]*nums[i]*nums[len-1]}
        int[][] funs = new int[len][len];

        for (int i = len - 2; i >= 0; i--) {
            for (int j = i + 2; j < len; j++) {
                int max = 0;
                for (int k = i + 1; k < j; k++) {
                    int num = funs[i][k] + funs[k][j] + newNums[i] * newNums[k] * newNums[j];
                    if (num > max) {
                        max = num;
                    }
                }
                funs[i][j] = max;
            }
        }
        return funs[0][len - 1];
    }

    /**
     * 目标和 给定一个非负整数数组，a1, a2, ..., an, 和一个目标数，S。现在你有两个符号 + 和 -。 对于数组中的任意一个整数，你都可以从 + 或
     * -中选择一个符号添加在前面。
     *
     * <p>返回可以使最终数组和为目标数 S 的所有添加符号的方法数。
     *
     * <p>示例 1:
     *
     * <p>输入: nums: [1, 1, 1, 1, 1], S: 3 输出: 5 解释:
     *
     * <p>-1+1+1+1+1 = 3 +1-1+1+1+1 = 3 +1+1-1+1+1 = 3 +1+1+1-1+1 = 3 +1+1+1+1-1 = 3
     *
     * <p>一共有5种方法让最终目标和为3。 注意:
     *
     * <p>数组非空，且长度不会超过20。 初始的数组的和不会超过1000。 保证返回的最终结果能被32位整数存下。
     *
     * @param nums
     * @param S
     * @return
     */
    public int findTargetSumWays(int[] nums, int S) {
        int result = 0;
        // 思路 : nums: [1, 1, 1, 1, 1], S: 3
        // -> （1）[1, 1, 1, 1] , S : 2 和 （-1）[1, 1, 1, 1] , S : 4
        List<Integer> numList = new ArrayList<>();
        for (int num : nums) {
            numList.add(num);
        }
        result = findTargetSum(numList, S);
        return result;
    }

    private int findTargetSum(List<Integer> nums, int s) {
        int result = 0;
        if (nums.size() == 0) {
            return 0;
        }
        if (nums.size() == 1) {
            if (nums.get(0) == 0 && s == 0) {
                return 2;
            } else if (Objects.equals(nums.get(0), s) || Objects.equals(nums.get(0), 0 - s)) {
                return 1;
            } else {
                return 0;
            }
        }
        List<Integer> a = new ArrayList<>(nums);
        List<Integer> b = new ArrayList<>(nums);
        // 移除第一个元素，s +/- 第一个元素
        result += findTargetSum(a, s - a.remove(0));
        result += findTargetSum(b, s + b.remove(0));

        return result;
    }

    public int findTargetSumWays2(int[] nums, int S) {
        int sum = 0;
        for (int n : nums) sum += n;
        return sum < S || (S + sum) % 2 > 0 ? 0 : subsetSum(nums, (S + sum) >>> 1);
    }

    public int subsetSum(int[] nums, int s) {
        int[] dp = new int[s + 1];
        dp[0] = 1;
        for (int n : nums) for (int i = s; i >= n; i--) dp[i] += dp[i - n];
        return dp[s];
    }

    @Test
    public void minimumTotal() {
        List<List<Integer>> triangle = new ArrayList<>();
        List<Integer> list1 = new ArrayList<>();
        list1.add(2);
        List<Integer> list2 = new ArrayList<>();
        list2.add(3);
        list2.add(4);
        List<Integer> list3 = new ArrayList<>();
        list3.add(6);
        list3.add(5);
        list3.add(7);
        List<Integer> list4 = new ArrayList<>();
        list4.add(4);
        list4.add(1);
        list4.add(8);
        list4.add(3);
        triangle.add(list1);
        triangle.add(list2);
        triangle.add(list3);
        triangle.add(list4);
        int num = minimumTotal(triangle);
        log.debug("result:{}", num);
    }
    /**
     * 三角形最小路径和 给定一个三角形，找出自顶向下的最小路径和。每一步只能移动到下一行中相邻的结点上。
     *
     * <p>例如，给定三角形：
     *
     * <p>[ [2], [3,4], [6,5,7], [4,1,8,3] ] 自顶向下的最小路径和为 11（即，2 + 3 + 5 + 1 = 11）。
     *
     * <p>说明：
     *
     * <p>如果你可以只使用 O(n) 的额外空间（n 为三角形的总行数）来解决这个问题，那么你的算法会很加分。
     *
     * @param triangle
     * @return
     */
    public int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return triangle.get(0).get(0);
        }
        /*int[] lastDistances;
        int[] distances = new int[1];
        distances[0] = triangle.get(0).get(0);
        for (int i = 1; i < n; i++) {
            List<Integer> list = triangle.get(i);
            lastDistances = distances;
            int len = list.size();
            distances = new int[len];
            for (int j = 0; j < len; j++) {
                Integer num = list.get(j);
                if (j == 0) {
                    distances[j] = lastDistances[j] + num;
                } else if (j == list.size() - 1) {
                    distances[j] = lastDistances[j - 1] + num;
                } else {
                    distances[j] = Math.min(lastDistances[j - 1],lastDistances[j]) + num;
                }
            }
        }

        int min = distances[0];
        for (int dis : distances) {
            if (dis < min) {
                min = dis;
            }
        }
        return min;*/

        int[] dp = new int[n];

        for (int i = n - 1; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                if (i == n - 1) {
                    dp[j] = triangle.get(i).get(j);
                } else {
                    dp[j] = Math.min(dp[j], dp[j + 1]) + triangle.get(i).get(j);
                }
            }
        }

        return dp[0];
    }

    @Test
    public void maximalSquare() {
        char[][] matrix = {
            {'1', '0', '1', '0', '0'},
            {'1', '0', '1', '1', '1'},
            {'1', '1', '1', '1', '1'},
            {'1', '0', '1', '1', '1'}
        };
        // char[][] matrix =
        // {{'1','1','1','1','1'},{'1','1','1','1','1'},{'1','1','1','1','1'},{'1','1','1','1','1'}};
        int result = maximalSquare(matrix);
        log.debug("result:{}", result);
    }

    /**
     * 最大正方形 在一个由 0 和 1 组成的二维矩阵内，找到只包含 1 的最大正方形，并返回其面积。
     *
     * <p>示例:
     *
     * <p>输入:
     *
     * <p>1 0 1 0 0
     *
     * <p>1 0 1 1 1
     *
     * <p>1 1 1 1 1
     *
     * <p>1 0 0 1 0
     *
     * <p>输出: 4
     *
     * @param matrix
     * @return
     */
    public int maximalSquare(char[][] matrix) {
        int row = matrix.length;
        if (row == 0) {
            return 0;
        }
        int col = matrix[0].length;
        if (col == 0) {
            return 0;
        }
        // 使用 sideLen 数组，存储 边长（1 的个数）
        int max = 0;
        int[][] sideLen = new int[row + 1][col + 1];
        for (int i = 1; i <= row; i++) {
            for (int j = 1; j <= col; j++) {
                if (matrix[i - 1][j - 1] == '1') {
                    int min = Math.min(sideLen[i - 1][j], sideLen[i][j - 1]);
                    min = Math.min(min, sideLen[i - 1][j - 1]);
                    int side = min + 1;
                    sideLen[i][j] = side;
                    if (side > max) {
                        max = side;
                    }
                } else {
                    sideLen[i][j] = 0;
                }
            }
        }

        for (int[] nums : sideLen) {
            log.debug("sideLen:{}", nums);
        }
        // dp(i, j)=min(dp(i−1, j), dp(i−1, j−1), dp(i, j−1))+1

        return max * max;
    }

    @Test
    public void maxEnvelopes() {
        int[][] envelopes = {{5, 4}, {6, 4}, {6, 7}, {2, 3}};
        /*int[][] envelopes = {{15,22},{8,34},{44,40},{9,17},{43,23},{4,7},{20,8},{30,46},{39,36},{45,14},{24,19},{24,36},
        {31,34},{32,19},{29,13},{16,48},{8,36},{44,2},{11,5},{2,50},{29,6}, {18,38},{15,49},{22,37},{6,20},
        {25,11},{1,50},{19,40},{45,35},{37,21},{4,29},{40,5},{4,49},{1,45},{14,32},{14,37},{23,22},{31,21},
        {2,36},{43,4},{21,32},{41,2}, {44,32},{36,20},{22,45},{3,41},{44,29},{29,33},{42,2},{38,17},{43,26},
        {30,15},{28,12},{33,30},{49,7},{8,14},{1,9},{41,25},{7,15},{26,32},{11,33},{12,45},{33,7},{16,34},
        {39,1},{20,49},{50,45},{14,29},{50,41},{1,45},{14,43},{49,20},{41,37},{43,22},{45,19},{20,21},{28,19},
        {2,1},{7,49},{3,3},{49,48},{34,35},{10,2}};*/
        int result = maxEnvelopes(envelopes);
        log.debug("result:{}", result);
    }
    /**
     * 俄罗斯套娃信封问题 给定一些标记了宽度和高度的信封，宽度和高度以整数对形式 (w, h)
     * 出现。当另一个信封的宽度和高度都比这个信封大的时候，这个信封就可以放进另一个信封里，如同俄罗斯套娃一样。
     *
     * <p>请计算最多能有多少个信封能组成一组“俄罗斯套娃”信封（即可以把一个信封放到另一个信封里面）。
     *
     * <p>说明: 不允许旋转信封。
     *
     * <p>示例:
     *
     * <p>输入: envelopes = [[5,4],[6,4],[6,7],[2,3]] 输出: 3 解释: 最多信封的个数为 3, 组合为: [2,3] => [5,4] =>
     * [6,7]。
     *
     * @param envelopes
     * @return
     */
    public int maxEnvelopes(int[][] envelopes) {
        int len = envelopes.length;
        if (len <= 1) {
            return len;
        }
        // w 升序， h 降序
        Arrays.sort(
                envelopes,
                (int[] a, int[] b) -> {
                    return a[0] == b[0] ? b[1] - a[1] : a[0] - b[0];
                });
        // 查找 h 的 最长递增 子序列
        int[] height = new int[len];
        for (int i = 0; i < len; i++) {
            height[i] = envelopes[i][1];
        }

        return lengthOfLIS(height);
        /* Arrays.sort(envelopes, (int[] a, int[] b) ->{
            return a[0] == b[0] ? a[1] - b[1] : a[0] - b[0];
        });

        for (int[] envelope :envelopes) {
            log.debug("envelope:{}",envelope);
        }
        // 动态规划
        int[] nums = new int[len];
        nums[0] = 1;
        for (int i = 1; i < len; i++) {
            nums[i] = 1;
            for (int j = 0; j < i ; j++) {

                if (envelopes[j][0] < envelopes[i][0] && envelopes[j][1] < envelopes[i][1]) {
                    if (nums[j] + 1 > nums[i]) {
                        nums[i] = nums[j] + 1;
                    }
                }

            }
        }
        log.debug("nums :{}",nums);
        int max = 0;
        for (int num : nums) {
            if (num > max) {
                max = num;
            }
        }


        return max;*/
    }

    @Test
    public void lengthOfLIS() {
        int[] nums = {1, 3, 6, 7, 9, 4, 10, 5, 6};
        int result = lengthOfLIS(nums);
        log.debug("result :{}", result);
    }

    /**
     * Longest Increasing Subsequence 给定一个无序的整数数组，找到其中最长上升子序列的长度。
     *
     * <p>示例:
     *
     * <p>输入: [10,9,2,5,3,7,101,18] 输出: 4 解释: 最长的上升子序列是 [2,3,7,101]，它的长度是 4。 说明:
     *
     * <p>可能会有多种最长上升子序列的组合，你只需要输出对应的长度即可。 你算法的时间复杂度应该为 O(n2) 。 进阶: 你能将算法的时间复杂度降低到 O(n log n) 吗?
     *
     * @param nums
     * @return
     */
    public int lengthOfLIS(int[] nums) {

        int len = nums.length;
        if (len <= 1) {
            return len;
        }

        int[] lenArray = new int[len];
        int max = 1;
        lenArray[0] = 1;
        for (int i = 1; i < len; i++) {
            int maxval = 0;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    maxval = Math.max(maxval, lenArray[j]);
                }
            }

            lenArray[i] = maxval + 1;
            max = Math.max(max, lenArray[i]);
        }

        return max;
    }

    @Test
    public void longestSubstring() {
        String s = "aaacbbb";
        int k = 3;
        int result = longestSubstring(s, k);
        log.debug("result:{}", result);
    }

    /**
     * 至少有K个重复字符的最长子串 找到给定字符串（由小写字符组成）中的最长子串 T ， 要求 T 中的每一字符出现次数都不少于 k 。输出 T 的长度。
     *
     * <p>示例 1:
     *
     * <p>输入: s = "aaabb", k = 3
     *
     * <p>输出: 3
     *
     * <p>最长子串为 "aaa" ，其中 'a' 重复了 3 次。 示例 2:
     *
     * <p>输入: s = "ababbc", k = 2
     *
     * <p>输出: 5
     *
     * <p>最长子串为 "ababb" ，其中 'a' 重复了 2 次， 'b' 重复了 3 次。
     *
     * @param s
     * @param k
     * @return
     */
    public int longestSubstring(String s, int k) {
        int result = 0;
        int len = s.length();
        if (len < k) {
            return 0;
        }

        result = longestCount(s.toCharArray(), k, 0, len - 1);
        return result;
    }

    private int longestCount(char[] chars, int k, int left, int right) {
        if (right - left + 1 < 0) {
            return 0;
        }
        // 统计 所有 字母出现的频率
        int[] counts = new int[26];
        for (int i = left; i <= right; i++) {
            counts[chars[i] - 'a']++;
        }
        // 移动 左右指针，保证 right 和 left 的 频率 >= k
        while (right - left + 1 >= k && counts[chars[left] - 'a'] < k) {
            left++;
        }
        while (right - left + 1 >= k && counts[chars[right] - 'a'] < k) {
            right--;
        }
        if (right - left + 1 < 0) {
            return 0;
        }
        // 遍历 判断
        for (int i = left; i <= right; i++) {
            if (counts[chars[i] - 'a'] < k) {
                return Math.max(
                        longestCount(chars, k, left, i - 1), longestCount(chars, k, i + 1, right));
            }
        }
        return right - left + 1;
    }

    @Test
    public void longestIncreasingPath() {
        // int[][] matrix = {{9,9,4}, {6,6,8}, {2,1,1}};
        int[][] matrix = {{3, 4, 5}, {3, 2, 6}, {2, 2, 1}};
        int result = longestIncreasingPath(matrix);
        log.debug("result:{}", result);
    }
    /**
     * 矩阵中的最长递增路径 给定一个整数矩阵，找出最长递增路径的长度。
     *
     * <p>对于每个单元格，你可以往上，下，左，右四个方向移动。 你不能在对角线方向上移动或移动到边界外（即不允许环绕）。
     *
     * <p>示例 1:
     *
     * <p>输入: nums = [ [9,9,4], [6,6,8], [2,1,1] ] 输出: 4 解释: 最长递增路径为 [1, 2, 6, 9]。 示例 2:
     *
     * <p>输入: nums = [ [3,4,5], [3,2,6], [2,2,1] ] 输出: 4 解释: 最长递增路径是 [3, 4, 5, 6]。注意不允许在对角线方向上移动。
     *
     * @param matrix
     * @return
     */
    public int longestIncreasingPath(int[][] matrix) {
        int rows = matrix.length;
        if (rows == 0) {
            return 0;
        }
        int cols = matrix[0].length;
        if (cols == 0) {
            return 0;
        }
        int[][] visited = new int[rows][cols];
        int result = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int max = dfs(matrix, i, j, visited);
                if (max > result) {
                    result = max;
                }
            }
        }
        return result;
    }

    public int dfs(int[][] matrix, int row, int col, int[][] visited) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        // 越界退出
        if (row < 0 || row > rows - 1 || col < 0 || col > cols - 1) {
            return 0;
        }
        // 如果之前这个位置已经计算过了,则直接返回
        if (visited[row][col] != 0) {
            return visited[row][col];
        }
        int value = matrix[row][col];
        int max = 0;
        // 左
        if (col >= 1 && matrix[row][col - 1] > value) {
            max = Math.max(max, dfs(matrix, row, col - 1, visited));
        }
        // 右
        if (col < cols - 1 && matrix[row][col + 1] > value) {
            max = Math.max(max, dfs(matrix, row, col + 1, visited));
        }
        // 上
        if (row >= 1 && matrix[row - 1][col] > value) {
            max = Math.max(max, dfs(matrix, row - 1, col, visited));
        }
        // 下
        if (row < rows - 1 && matrix[row + 1][col] > value) {
            max = Math.max(max, dfs(matrix, row + 1, col, visited));
        }

        if (max > 0) {
            visited[row][col] = 1 + max;
        } else {
            visited[row][col] = 1;
        }
        return visited[row][col];
    }

    @Test
    public void longestValidParentheses() {
        String s = ")()())()";
        logResult(longestValidParentheses(s));
    }

    /**
     * 32. 最长有效括号 给定一个只包含 '(' 和 ')' 的字符串，找出最长的包含有效括号的子串的长度。
     *
     * <p>示例 1:
     *
     * <p>输入: "(()" 输出: 2 解释: 最长有效括号子串为 "()" 示例 2:
     *
     * <p>输入: ")()())" 输出: 4 解释: 最长有效括号子串为 "()()"
     *
     * @param s
     * @return
     */
    public int longestValidParentheses(String s) {
        int result = 0;
        int len = s.length();

        int[] nums = new int[len + 1];
        int leftCount = 0;
        //
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            if (c == '(') {
                leftCount++;
                nums[i + 1] = nums[i];
            }
            if (c == ')') {
                // 有效括号
                if (leftCount > 0) {
                    leftCount--;
                    nums[i + 1] = nums[i] + 2;
                }
            }
            if (leftCount == 0) {
                nums[i + 1] = 0;
            }
        }
        int max = 0;
        for (int num : nums) {
            if (num > max) {
                max = num;
            }
        }

        return max;
    }

    public int longestValidParenthesesStack(String s) {
        int max = 0;
        int len = s.length();
        Deque<Integer> deque = new LinkedList<>();
        deque.addLast(-1);
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            if (c == '(') {
                // 入栈
                deque.addLast(i);
            } else {
                deque.pollLast();
                // 有效括号 出栈
                if (deque.isEmpty()) {
                    deque.addLast(i);
                } else {
                    max = Math.max(max, i - deque.peekLast());
                }
            }
        }

        return max;
    }

    @Test
    public void minPathSum() {
        // int[][] grid = {{1,3,1},{1,5,1},{4,2,1}};
        int[][] grid = {{1, 2, 5}, {3, 2, 1}};
        logResult(minPathSum(grid));
    }
    /**
     * 64. 最小路径和 给定一个包含非负整数的 m x n 网格，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
     *
     * <p>说明：每次只能向下或者向右移动一步。
     *
     * <p>示例:
     *
     * <p>输入: [ [1,3,1], [1,5,1], [4,2,1] ] 输出: 7 解释: 因为路径 1→3→1→1→1 的总和最小。
     *
     * @param grid
     * @return
     */
    public int minPathSum(int[][] grid) {
        int rows = grid.length;
        if (rows == 0) {
            return 0;
        }
        int cols = grid[0].length;
        int[][] pathSum = new int[rows][cols];
        pathSum[0][0] = grid[0][0];
        for (int i = 1; i < rows; i++) {
            pathSum[i][0] = pathSum[i - 1][0] + grid[i][0];
        }
        for (int j = 1; j < cols; j++) {
            pathSum[0][j] = pathSum[0][j - 1] + grid[0][j];
        }
        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < cols; j++) {
                pathSum[i][j] = Math.min(pathSum[i - 1][j], pathSum[i][j - 1]) + grid[i][j];
            }
        }
        logResult(pathSum);
        return pathSum[rows - 1][cols - 1];
    }

    @Test
    public void minCostClimbingStairs() {
        int[] cost = {1, 100, 1, 1, 1, 100, 1, 1, 100, 1};
        logResult(minCostClimbingStairs(cost));
    }
    /**
     * 746. 使用最小花费爬楼梯 数组的每个索引做为一个阶梯，第 i个阶梯对应着一个非负数的体力花费值 cost[i](索引从0开始)。
     *
     * <p>每当你爬上一个阶梯你都要花费对应的体力花费值，然后你可以选择继续爬一个阶梯或者爬两个阶梯。
     *
     * <p>您需要找到达到楼层顶部的最低花费。在开始时，你可以选择从索引为 0 或 1 的元素作为初始阶梯。
     *
     * <p>示例 1:
     *
     * <p>输入: cost = [10, 15, 20] 输出: 15 解释: 最低花费是从cost[1]开始，然后走两步即可到阶梯顶，一共花费15。 示例 2:
     *
     * <p>输入: cost = [1, 100, 1, 1, 1, 100, 1, 1, 100, 1] 输出: 6 解释:
     * 最低花费方式是从cost[0]开始，逐个经过那些1，跳过cost[3]，一共花费6。 注意：
     *
     * <p>cost 的长度将会在 [2, 1000]。 每一个 cost[i] 将会是一个Integer类型，范围为 [0, 999]。
     *
     * @param cost
     * @return
     */
    public int minCostClimbingStairs(int[] cost) {
        int len = cost.length;
        if (len == 1) {
            return 0;
        }
        int[] minCost = new int[len + 1];
        minCost[0] = cost[0];
        minCost[1] = cost[1];
        for (int i = 2; i <= len; i++) {
            int num = Math.min(minCost[i - 1], minCost[i - 2]);

            if (i == len) {
                minCost[i] = num;
            } else {
                minCost[i] = num + cost[i];
            }
        }
        log.debug("minCost:{}", minCost);

        return minCost[len];
    }

    @Test
    public void minDistance() {
        String word1 = "sea", word2 = "eat";
        logResult(minDistance(word1, word2));
    }
    /**
     * 72. 编辑距离 给你两个单词 word1 和 word2，请你计算出将 word1 转换成 word2 所使用的最少操作数 。
     *
     * <p>你可以对一个单词进行如下三种操作：
     *
     * <p>插入一个字符 删除一个字符 替换一个字符
     *
     * <p>示例 1：
     *
     * <p>输入：word1 = "horse", word2 = "ros" 输出：3 解释： horse -> rorse (将 'h' 替换为 'r') rorse -> rose
     * (删除 'r') rose -> ros (删除 'e') 示例 2：
     *
     * <p>输入：word1 = "intention", word2 = "execution" 输出：5 解释： intention -> inention (删除 't')
     * inention -> enention (将 'i' 替换为 'e') enention -> exention (将 'n' 替换为 'x') exention ->
     * exection (将 'n' 替换为 'c') exection -> execution (插入 'u')
     *
     * @param word1
     * @param word2
     * @return
     */
    public int minDistance(String word1, String word2) {
        int result = 0;
        if (Objects.equals(word1, word2)) {
            return 0;
        }
        int len1 = word1.length(), len2 = word2.length();

        if (len1 * len2 == 0) {
            return len1 + len2;
        }
        char[] chars1 = word1.toCharArray();
        char[] chars2 = word2.toCharArray();
        int[][] dp = new int[len1 + 1][len2 + 1];
        for (int i = 0; i < len1 + 1; i++) {
            dp[i][0] = i;
        }
        for (int j = 0; j < len2 + 1; j++) {
            dp[0][j] = j;
        }
        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                // 删除当前字符
                int left = dp[i - 1][j] + 1;
                // 增加一个字符
                int up = dp[i][j - 1] + 1;

                int min = Math.min(left, up);
                int len = dp[i - 1][j - 1];
                if (chars1[i - 1] != chars2[j - 1]) {
                    len += 1;
                }
                min = Math.min(min, len);
                dp[i][j] = min;
            }
        }

        logResult(dp);
        return dp[len1][len2];
    }

    private int minDistance2(String word1, String word2) {
        int len1 = word1.length(), len2 = word2.length();
        char[] chars1 = word1.toCharArray();
        char[] chars2 = word2.toCharArray();
        int[][] dp = new int[len1 + 1][len2 + 1];

        for (int i = 0; i < len1; i++) {
            for (int j = 0; j <= i && j < len2; j++) {
                boolean same = false;
                if (chars1[i] == chars2[j]) {
                    same = true;
                }

                if (i == 0) {
                    dp[i][j] = same ? 0 : 1;
                    continue;
                }
                if (i == j) {
                    dp[i][j] = dp[i - 1][j - 1] + (same ? 0 : 1);
                    continue;
                }
                int min = 0;
                if (j == 0) {
                    // 两种情况
                    // 删除 前面的 i 个字符 + 把当前字符改为 char1[0];
                    int distance1 = i + (same ? 0 : 1);
                    // 删除当前字符,取dp[i - 1] ;
                    int distance2 = dp[i - 1][j] + 1;

                    dp[i][j] = Math.min(distance1, distance2);
                } else {
                    // 两种情况
                    // 删除当前字符,取dp[i - 1][j] ;
                    int distance1 = dp[i - 1][j] + 1;
                    // 把 i位置的字符转变成j位置的字符 dp[i - 1][j - 1]  ;
                    int distance2 = dp[i - 1][j - 1] + (same ? 0 : 1);
                    dp[i][j] = Math.min(distance1, distance2);
                }
            }
        }

        return dp[len1][len2];
    }

    /**
     * 1025. 除数博弈 爱丽丝和鲍勃一起玩游戏，他们轮流行动。爱丽丝先手开局。
     *
     * <p>最初，黑板上有一个数字 N 。在每个玩家的回合，玩家需要执行以下操作：
     *
     * <p>选出任一 x，满足 0 < x < N 且 N % x == 0 。 用 N - x 替换黑板上的数字 N 。 如果玩家无法执行这些操作，就会输掉游戏。
     *
     * <p>只有在爱丽丝在游戏中取得胜利时才返回 True，否则返回 false。假设两个玩家都以最佳状态参与游戏。
     *
     * <p>示例 1：
     *
     * <p>输入：2 输出：true 解释：爱丽丝选择 1，鲍勃无法进行操作。 示例 2：
     *
     * <p>输入：3 输出：false 解释：爱丽丝选择 1，鲍勃也选择 1，然后爱丽丝无法进行操作。
     *
     * <p>提示：
     *
     * <p>1 <= N <= 1000
     */
    public boolean divisorGame(int N) {
        if (N == 1) {
            return false;
        }
        int[] nums = new int[N];

        return N % 2 == 0;
    }

    @Test
    public void waysToStep() {
        int n = 5;
        logResult(waysToStep(n));
    }

    /**
     * 面试题 08.01. 三步问题 三步问题。有个小孩正在上楼梯，楼梯有n阶台阶，小孩一次可以上1阶、2阶或3阶。实现一种方法，计算小孩有多少种上楼梯的方式。结果可能很大，
     * 你需要对结果模1000000007。
     *
     * <p>示例1:
     *
     * <p>输入：n = 3 输出：4 说明: 有四种走法 示例2:
     *
     * <p>输入：n = 5 输出：13 提示:
     *
     * <p>n范围在[1, 1000000]之间
     *
     * @param n
     * @return
     */
    public int waysToStep(int n) {
        int mod = 1000000007;
        // 动态规划
        long[] nums = new long[n + 1];

        for (int i = 1; i <= n; i++) {
            if (i == 1) {
                nums[i] = 1;
                continue;
            }
            if (i == 2) {
                nums[i] = 2;
                continue;
            }
            if (i == 3) {
                nums[i] = 4;
                continue;
            }
            nums[i] = (nums[i - 3] + nums[i - 2] + nums[i - 1]) % mod;
        }
        return (int) nums[n];
    }

    /**
     * 面试题 16.17. 连续数列 给定一个整数数组（有正数有负数），找出总和最大的连续数列，并返回总和。
     *
     * <p>示例：
     *
     * <p>输入： [-2,1,-3,4,-1,2,1,-5,4] 输出： 6 解释： 连续子数组 [4,-1,2,1] 的和最大，为 6。 进阶：
     *
     * <p>如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的分治法求解。
     *
     * @param nums
     * @return
     */
    public int maxSubArray2(int[] nums) {
        int len = nums.length;
        int[] sums = new int[len];

        sums[0] = nums[0];
        for (int i = 1; i < len; i++) {
            sums[i] = nums[i];
            if (sums[i - 1] > 0) {
                sums[i] += sums[i - 1];
            }
        }
        int max = sums[0];
        for (int i = 1; i < len; i++) {
            if (sums[i] > max) {
                max = sums[i];
            }
        }
        return max;
    }

    /**
     * 466. 统计重复个数 由 n 个连接的字符串 s 组成字符串 S，记作 S = [s,n]。例如，["abc",3]=“abcabcabc”。
     *
     * <p>如果我们可以从 s2 中删除某些字符使其变为 s1，则称字符串 s1 可以从字符串 s2 获得。例如，根据定义，"abc" 可以从 “abdbec” 获得， 但不能从
     * “acbbe” 获得。
     *
     * <p>现在给你两个非空字符串 s1 和 s2（每个最多 100 个字符长）和两个整数 0 ≤ n1 ≤ 106 和 1 ≤ n2 ≤ 106。现在考虑字符串 S1 和 S2， 其中
     * S1=[s1,n1] 、S2=[s2,n2] 。
     *
     * <p>请你找出一个可以满足使[S2,M] 从 S1 获得的最大整数 M 。
     *
     * <p>示例：
     *
     * <p>输入： s1 ="acb",n1 = 4 s2 ="ab",n2 = 2
     *
     * <p>返回： 2
     *
     * @param s1
     * @param n1
     * @param s2
     * @param n2
     * @return
     */
    public int getMaxRepetitions(String s1, int n1, String s2, int n2) {

        return 0;
    }

    @Test
    public void waysToChange() {
        int n = 10;
        logResult(waysToChange(n));
    }

    /**
     * 面试题 08.11. 硬币 硬币。给定数量不限的硬币，币值为25分、10分、5分和1分，编写代码计算n分有几种表示法。(结果可能会很大，你需要将结果模上1000000007)
     *
     * <p>示例1:
     *
     * <p>输入: n = 5 输出：2 解释: 有两种方式可以凑成总金额: 5=5 5=1+1+1+1+1 示例2:
     *
     * <p>输入: n = 10 输出：4 解释: 有四种方式可以凑成总金额: 10=10 10=5+5 10=5+1+1+1+1+1 10=1+1+1+1+1+1+1+1+1+1 说明：
     *
     * <p>注意:
     *
     * <p>你可以假设：
     *
     * <p>0 <= n (总金额) <= 1000000
     *
     * @param n
     * @return
     */
    public int waysToChange(int n) {
        int[] nums = new int[n + 1];
        int[] coins = {1, 5, 10, 25};
        nums[0] = 1;
        int mod = 1000000007;
        for (int i = 0; i < coins.length; i++) {
            int coin = coins[i];
            for (int j = coin; j <= n; j++) {
                nums[j] = (nums[j] + nums[j - coin]) % mod;
            }
        }
        /*for (int i = 1; i <= n; i++) {
            if (i == 1 || i == 5 || i == 10 || i == 25 ) {
                nums[i] = 1;
            }
            if (i - 1 > 0) {
                nums[i] += nums[i - 1];
            }
            if (i - 5 > 0) {
                nums[i] += nums[i - 5];
            }
            if (i - 10 > 0) {
                nums[i] += nums[i - 10];
            }
            if (i - 25 > 0) {
                nums[i] += nums[i - 25];
            }
        }*/
        log.debug("nums:{}", nums);
        return nums[n];
    }

    @Test
    public void cuttingRope() {
        int n = 10;
        logResult(cuttingRope(n));
    }
    /**
     * 面试题14- I. 剪绳子 给你一根长度为 n 的绳子，请把绳子剪成整数长度的 m 段（m、n都是整数，n>1并且m>1）， 每段绳子的长度记为 k[0],k[1]...k[m] 。请问
     * k[0]*k[1]*...*k[m] 可能的最大乘积是多少？例如，当绳子的长度是8时，我们把它剪成长度分别为2、3、3的三段，此时得到的最大乘积是18。
     *
     * <p>示例 1：
     *
     * <p>输入: 2 输出: 1 解释: 2 = 1 + 1, 1 × 1 = 1 示例 2:
     *
     * <p>输入: 10 输出: 36 解释: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36 提示：
     *
     * <p>2 <= n <= 58
     *
     * @param n
     * @return
     */
    public int cuttingRope(int n) {
        if (n <= 3) {
            return n - 1;
        }
        int[] nums = new int[n + 1];
        nums[2] = 1;
        nums[3] = 2;

        for (int i = 4; i <= n; i++) {
            int max = 0;
            for (int j = 1; j < i; j++) {
                max = Math.max(max, j * (i - j));
                max = Math.max(max, nums[j] * (i - j));
            }
            nums[i] = max;
        }
        log.debug("nums:{}", nums);
        return nums[n];
    }

    /**
     * 53. 最大子序和 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
     *
     * <p>示例:
     *
     * <p>输入: [-2,1,-3,4,-1,2,1,-5,4], 输出: 6 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。 进阶:
     *
     * <p>如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的分治法求解。
     *
     * @param nums
     * @return
     */
    public int maxSubArray3(int[] nums) {
        int len = nums.length;
        int result = nums[0];

        for (int i = 1; i < len; i++) {

            int num = nums[i];
            result = Math.max(result + num, num);
        }

        return result;
    }

    /**
     * 357. 计算各个位数不同的数字个数 给定一个非负整数 n，计算各位数字都不同的数字 x 的个数，其中 0 ≤ x < 10n 。
     *
     * <p>示例:
     *
     * <p>输入: 2 输出: 91 解释: 答案应为除去 11,22,33,44,55,66,77,88,99 外，在 [0,100) 区间内的所有数字。
     *
     * @param n
     * @return
     */
    public int countNumbersWithUniqueDigits(int n) {
        int[] dp = new int[11];

        // 排列组合 从10位数字中找出1 ~ 10个数字都不同的组合
        dp[0] = 1;
        dp[1] = 9;
        // 1 -> 10
        // 2 -> 9 * 9
        // 3 -> 9 * 9 * 8
        // 4 -> 9 * 9 * 8 * 7

        for (int i = 2; i <= n; i++) {

            dp[i] = dp[i - 1] * (11 - i);
        }
        int result = 0;
        for (int i = 0; i <= 10 && i <= n; i++) {
            result += dp[i];
        }

        return result;
    }

    @Test
    public void mincostTickets() {
        int[] days = {1, 4, 6, 7, 8, 20};
        int[] costs = {7, 2, 15};
        logResult(mincostTickets(days, costs));
    }

    /**
     * 983. 最低票价 在一个火车旅行很受欢迎的国度，你提前一年计划了一些火车旅行。在接下来的一年里，你要旅行的日子将以一个名为 days 的数组给出。每一项是一个从 1 到 365
     * 的整数。
     *
     * <p>火车票有三种不同的销售方式：
     *
     * <p>一张为期一天的通行证售价为 costs[0] 美元； 一张为期七天的通行证售价为 costs[1] 美元； 一张为期三十天的通行证售价为 costs[2] 美元。
     * 通行证允许数天无限制的旅行。 例如，如果我们在第 2 天获得一张为期 7 天的通行证，那么我们可以连着旅行 7 天：第 2 天、第 3 天、第 4 天、第 5 天、第 6 天、第 7
     * 天和第 8 天。
     *
     * <p>返回你想要完成在给定的列表 days 中列出的每一天的旅行所需要的最低消费。
     *
     * <p>示例 1：
     *
     * <p>输入：days = [1,4,6,7,8,20], costs = [2,7,15] 输出：11 解释： 例如，这里有一种购买通行证的方法，可以让你完成你的旅行计划： 在第 1
     * 天，你花了 costs[0] = $2 买了一张为期 1 天的通行证，它将在第 1 天生效。 在第 3 天，你花了 costs[1] = $7 买了一张为期 7 天的通行证，它将在第
     * 3, 4, ..., 9 天生效。 在第 20 天，你花了 costs[0] = $2 买了一张为期 1 天的通行证，它将在第 20 天生效。 你总共花了
     * $11，并完成了你计划的每一天旅行。 示例 2：
     *
     * <p>输入：days = [1,2,3,4,5,6,7,8,9,10,30,31], costs = [2,7,15] 输出：17 解释：
     * 例如，这里有一种购买通行证的方法，可以让你完成你的旅行计划： 在第 1 天，你花了 costs[2] = $15 买了一张为期 30 天的通行证，它将在第 1, 2, ..., 30
     * 天生效。 在第 31 天，你花了 costs[0] = $2 买了一张为期 1 天的通行证，它将在第 31 天生效。 你总共花了 $17，并完成了你计划的每一天旅行。
     *
     * <p>提示：
     *
     * <p>1 <= days.length <= 365 1 <= days[i] <= 365 days 按顺序严格递增 costs.length == 3 1 <= costs[i]
     * <= 1000
     *
     * @param days
     * @param costs
     * @return
     */
    public int mincostTickets(int[] days, int[] costs) {
        int len = days.length;
        int[] dp = new int[len];

        int[] div = new int[] {1, 7, 30};
        for (int i = 0; i < len; i++) {
            int min = Integer.MAX_VALUE;

            for (int j = 0; j < costs.length; j++) {
                int index = getDayIndex(days, i, div[j]);
                log.debug("index:{}", index);
                int cost = costs[j];
                if (index != -1) {
                    cost += dp[index];
                }

                min = Math.min(min, cost);
            }

            dp[i] = min;
        }
        log.debug("dp:{}", dp);
        return dp[len - 1];
    }

    private int getDayIndex(int[] days, int current, int div) {
        if (div == 1) {
            return current - div;
        }
        int index = days[current] - div;

        // 二分法
        int low = 0, high = current;
        while (low < high) {
            int mid = (low + high) >> 1;
            if (days[mid] == index) {
                return mid;
            }
            if (days[mid] > index) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low - 1;
    }

    /**
     * 213. 打家劫舍 II 你是一个专业的小偷，计划偷窃沿街的房屋，每间房内都藏有一定的现金。这个地方所有的房屋都围成一圈，
     * 这意味着第一个房屋和最后一个房屋是紧挨着的。同时，相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
     *
     * <p>给定一个代表每个房屋存放金额的非负整数数组，计算你在不触动警报装置的情况下，能够偷窃到的最高金额。
     *
     * <p>示例 1:
     *
     * <p>输入: [2,3,2] 输出: 3 解释: 你不能先偷窃 1 号房屋（金额 = 2），然后偷窃 3 号房屋（金额 = 2）, 因为他们是相邻的。 示例 2:
     *
     * <p>输入: [1,2,3,1] 输出: 4 解释: 你可以先偷窃 1 号房屋（金额 = 1），然后偷窃 3 号房屋（金额 = 3）。 偷窃到的最高金额 = 1 + 3 = 4 。
     *
     * @param nums
     * @return
     */
    public int rob2(int[] nums) {
        int len = nums.length;
        if (len == 0) {
            return 0;
        }
        if (len == 1) {
            return nums[0];
        }
        return Math.max(
                myRob(Arrays.copyOfRange(nums, 0, nums.length - 1)),
                myRob(Arrays.copyOfRange(nums, 1, nums.length)));
        /*int[] rob = new int[len];
        rob[0] = nums[0];
        rob[1] = nums[1];
        for (int i = 2; i < len; i++) {

            rob[i] = rob[i - 2] + nums[i];
            if (i - 3 > 0) {

                rob[i] = Math.max(rob[i],rob[i - 3] + nums[i]);
            }

        }
        return Math.max(rob[len - 1],rob[len - 2]);*/
    }

    private int myRob(int[] nums) {
        int pre = 0, cur = 0, tmp;
        for (int num : nums) {
            tmp = cur;
            cur = Math.max(pre + num, cur);
            pre = tmp;
        }
        return cur;
    }

    /**
     * 221. 最大正方形 在一个由 0 和 1 组成的二维矩阵内，找到只包含 1 的最大正方形，并返回其面积。
     *
     * <p>示例:
     *
     * <p>输入:
     *
     * <p>1 0 1 0 0 1 0 1 1 1 1 1 1 1 1 1 0 0 1 0
     *
     * <p>输出: 4
     *
     * @param matrix
     * @return
     */
    public int maximalSquare2(char[][] matrix) {
        int rows = matrix.length;
        if (rows == 0) {
            return 0;
        }
        int cols = matrix[0].length;
        if (cols == 0) {
            return 0;
        }

        int[][] sideLens = new int[rows + 1][cols + 1];
        int max = 0;
        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= cols; j++) {
                if (matrix[i][j] == '1') {
                    int min = Math.min(sideLens[i - 1][j], sideLens[i][j - 1]);
                    min = Math.min(min, sideLens[i - 1][j - 1]);
                    sideLens[i][j] = min + 1;
                    max = Math.max(max, sideLens[i][j]);
                }
            }
        }

        return max * max;
    }

    /**
     * 368. 最大整除子集 给出一个由无重复的正整数组成的集合，找出其中最大的整除子集，子集中任意一对 (Si，Sj) 都要满足：Si % Sj = 0 或 Sj % Si = 0。
     *
     * <p>如果有多个目标子集，返回其中任何一个均可。
     *
     * <p>示例 1:
     *
     * <p>输入: [1,2,3] 输出: [1,2] (当然, [1,3] 也正确) 示例 2:
     *
     * <p>输入: [1,2,4,8] 输出: [1,2,4,8]
     *
     * @param nums
     * @return
     */
    public List<Integer> largestDivisibleSubset(int[] nums) {
        if (nums.length == 0) {
            return new ArrayList<>();
        }
        Arrays.sort(nums);
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);
        int maxIndex = 0, maxSize = 1;
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] % nums[j] == 0) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            if (dp[i] > maxSize) {
                maxIndex = i;
                maxSize = dp[i];
            }
        }

        List<Integer> result = new ArrayList<>();
        if (maxSize == 1) {
            result.add(nums[0]);
            return result;
        }
        for (int i = maxIndex; i >= 0; i--) {
            if (nums[maxIndex] % nums[i] == 0 && dp[i] == maxSize) {
                maxSize--;
                result.add(nums[i]);
                maxIndex = i;
            }
        }
        Collections.reverse(result);
        return result;
    }

    @Test
    public void largestDivisibleSubset() {
        int[] nums = {2, 3, 8, 9, 27};
        logResult(largestDivisibleSubset(nums));
    }

    @Test
    public void getMoneyAmount() {
        int n = 5;
        logResult(getMoneyAmount(n));
    }

    /**
     * 375. 猜数字大小 II 我们正在玩一个猜数游戏，游戏规则如下：
     *
     * <p>我从 1 到 n 之间选择一个数字，你来猜我选了哪个数字。
     *
     * <p>每次你猜错了，我都会告诉你，我选的数字比你的大了或者小了。
     *
     * <p>然而，当你猜了数字 x 并且猜错了的时候，你需要支付金额为 x 的现金。直到你猜到我选的数字，你才算赢得了这个游戏。
     *
     * <p>示例:
     *
     * <p>n = 10, 我选择了8.
     *
     * <p>第一轮: 你猜我选择的数字是5，我会告诉你，我的数字更大一些，然后你需要支付5块。 第二轮: 你猜是7，我告诉你，我的数字更大一些，你支付7块。 第三轮:
     * 你猜是9，我告诉你，我的数字更小一些，你支付9块。
     *
     * <p>游戏结束。8 就是我选的数字。
     *
     * <p>你最终要支付 5 + 7 + 9 = 21 块钱。 给定 n ≥ 1，计算你至少需要拥有多少现金才能确保你能赢得这个游戏。
     *
     * @param n
     * @return
     */
    public int getMoneyAmount(int n) {
        // dp[i][j] 表示 i ~ j 直接的坏情况下最小开销的代价
        int[][] dp = new int[n + 1][n + 1];
        // 0 1
        //   0 2
        for (int len = 2; len <= n; len++) {

            for (int start = 1; start <= n - len + 1; start++) {
                int min = Integer.MAX_VALUE;
                for (int i = start; i < start + len - 1; i++) {
                    int amount = i + Math.max(dp[start][i - 1], dp[i + 1][start + len - 1]);
                    min = Math.min(min, amount);
                }
                dp[start][start + len - 1] = min;
            }
        }

        return dp[1][n];
    }

    /**
     * 376. 摆动序列 如果连续数字之间的差严格地在正数和负数之间交替，则数字序列称为摆动序列。第一个差（如果存在的话）可能是正数或负数。少于两个元素的序列也是摆动序列。
     *
     * <p>例如， [1,7,4,9,2,5] 是一个摆动序列，因为差值 (6,-3,5,-7,3) 是正负交替出现的。相反, [1,4,7,2,5] 和 [1,7,4,5,5]
     * 不是摆动序列， 第一个序列是因为它的前两个差值都是正数，第二个序列是因为它的最后一个差值为零。
     *
     * <p>给定一个整数序列，返回作为摆动序列的最长子序列的长度。 通过从原始序列中删除一些（也可以不删除）元素来获得子序列，剩下的元素保持其原始顺序。
     *
     * <p>示例 1:
     *
     * <p>输入: [1,7,4,9,2,5] 输出: 6 解释: 整个序列均为摆动序列。 示例 2:
     *
     * <p>输入: [1,17,5,10,13,15,10,5,16,8] 输出: 7 解释: 这个序列包含几个长度为 7 摆动序列，其中一个可为[1,17,10,13,10,16,8]。
     * 示例 3:
     *
     * <p>输入: [1,2,3,4,5,6,7,8,9] 输出: 2 进阶: 你能否用 O(n) 时间复杂度完成此题?
     *
     * @param nums
     * @return
     */
    public int wiggleMaxLength(int[] nums) {
        if (nums.length < 2) {
            return nums.length;
        }
        // 动态规划
        int[] up = new int[nums.length];
        int[] down = new int[nums.length];
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    up[i] = Math.max(up[i], down[j] + 1);
                } else if (nums[i] < nums[j]) {
                    down[i] = Math.max(down[i], up[j] + 1);
                }
            }
        }
        return 1 + Math.max(down[nums.length - 1], up[nums.length - 1]);
    }

    @Test
    public void maxDotProduct() {
        // int[] nums1 = {2,1,-2,5}, nums2 = {3,0,-6};
        int[] nums1 = {-3, -8, 3, -10, 1, 3, 9}, nums2 = {9, 2, 3, 7, -9, 1, -8, 5, -1, -1};
        logResult(maxDotProduct(nums1, nums2));
    }

    /**
     * 5419. 两个子序列的最大点积 给你两个数组 nums1 和 nums2 。
     *
     * <p>请你返回 nums1 和 nums2 中两个长度相同的 非空 子序列的最大点积。
     *
     * <p>数组的非空子序列是通过删除原数组中某些元素（可能一个也不删除）后剩余数字组成的序列，但不能改变数字间相对顺序。 比方说，[2,3,5] 是 [1,2,3,4,5] 的一个子序列而
     * [1,5,3] 不是。
     *
     * <p>示例 1：
     *
     * <p>输入：nums1 = [2,1,-2,5], nums2 = [3,0,-6] 输出：18 解释：从 nums1 中得到子序列 [2,-2] ，从 nums2 中得到子序列
     * [3,-6] 。 它们的点积为 (2*3 + (-2)*(-6)) = 18 。 示例 2：
     *
     * <p>输入：nums1 = [3,-2], nums2 = [2,-6,7] 输出：21 解释：从 nums1 中得到子序列 [3] ，从 nums2 中得到子序列 [7] 。
     * 它们的点积为 (3*7) = 21 。 示例 3：
     *
     * <p>输入：nums1 = [-1,-1], nums2 = [1,1] 输出：-1 解释：从 nums1 中得到子序列 [-1] ，从 nums2 中得到子序列 [1] 。
     * 它们的点积为 -1 。
     *
     * <p>提示：
     *
     * <p>1 <= nums1.length, nums2.length <= 500 -1000 <= nums1[i], nums2[i] <= 100
     *
     * <p>点积：
     *
     * <p>定义 a = [a1, a2,…, an] 和 b = [b1, b2,…, bn] 的点积为：
     *
     * <p>\mathbf{a}\cdot \mathbf{b} = \sum_{i=1}^n a_ib_i = a_1b_1 + a_2b_2 + \cdots + a_nb_n
     *
     * <p>这里的 Σ 指示总和符号。
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public int maxDotProduct(int[] nums1, int[] nums2) {
        int[][] dp = new int[nums1.length][nums2.length];

        dp[0][0] = nums1[0] * nums2[0];
        for (int i = 1; i < nums1.length; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], nums1[i] * nums2[0]);
        }
        for (int j = 1; j < nums2.length; j++) {
            dp[0][j] = Math.max(dp[0][j - 1], nums1[0] * nums2[j]);
        }

        for (int i = 1; i < nums1.length; i++) {
            for (int j = 1; j < nums2.length; j++) {
                int max = nums1[i] * nums2[j];
                if (dp[i - 1][j - 1] > 0) {
                    max += dp[i - 1][j - 1];
                }

                max = Math.max(max, dp[i][j - 1]);
                max = Math.max(max, dp[i - 1][j]);

                dp[i][j] = max;
            }
        }
        logResult(dp);
        return dp[nums1.length - 1][nums2.length - 1];
    }

    /**
     * 518. 零钱兑换 II 给定不同面额的硬币和一个总金额。写出函数来计算可以凑成总金额的硬币组合数。假设每一种面额的硬币有无限个。
     *
     * <p>示例 1:
     *
     * <p>输入: amount = 5, coins = [1, 2, 5] 输出: 4 解释: 有四种方式可以凑成总金额: 5=5 5=2+2+1 5=2+1+1+1
     * 5=1+1+1+1+1 示例 2:
     *
     * <p>输入: amount = 3, coins = [2] 输出: 0 解释: 只用面额2的硬币不能凑成总金额3。 示例 3:
     *
     * <p>输入: amount = 10, coins = [10] 输出: 1
     *
     * <p>注意:
     *
     * <p>你可以假设：
     *
     * <p>0 <= amount (总金额) <= 5000 1 <= coin (硬币面额) <= 5000 硬币种类不超过 500 种 结果符合 32 位符号整数
     *
     * @param amount
     * @param coins
     * @return
     */
    public int change(int amount, int[] coins) {
        int[] dp = new int[amount + 1];

        dp[0] = 1;
        for (int coin : coins) {
            for (int i = coin; i <= amount; i++) {
                dp[i] += dp[i - coin];
            }
        }
        return dp[amount];
    }

    /**
     * 413. 等差数列划分 如果一个数列至少有三个元素，并且任意两个相邻元素之差相同，则称该数列为等差数列。
     *
     * <p>例如，以下数列为等差数列:
     *
     * <p>1, 3, 5, 7, 9 7, 7, 7, 7 3, -1, -5, -9 以下数列不是等差数列。
     *
     * <p>1, 1, 2, 5, 7
     *
     * <p>数组 A 包含 N 个数，且索引从0开始。数组 A 的一个子数组划分为数组 (P, Q)，P 与 Q 是整数且满足 0<=P<Q<N 。
     *
     * <p>如果满足以下条件，则称子数组(P, Q)为等差数组：
     *
     * <p>元素 A[P], A[p + 1], ..., A[Q - 1], A[Q] 是等差的。并且 P + 1 < Q 。
     *
     * <p>函数要返回数组 A 中所有为等差数组的子数组个数。
     *
     * <p>示例:
     *
     * <p>A = [1, 2, 3, 4]
     *
     * <p>返回: 3, A 中有三个子等差数组: [1, 2, 3], [2, 3, 4] 以及自身 [1, 2, 3, 4]。
     *
     * @param A
     * @return
     */
    public int numberOfArithmeticSlices(int[] A) {
        int[] counts = new int[A.length];

        for (int i = 2; i < A.length; i++) {
            int first = A[i - 2];
            int secoud = A[i - 1];
            int third = A[i];
            if (first + third == (secoud << 1)) {
                counts[i] = counts[i - 1] + 1;
            }
        }
        int result = 0;
        for (int count : counts) {
            result += count;
        }
        return result;
    }

    /**
     * 416. 分割等和子集 给定一个只包含正整数的非空数组。是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
     *
     * <p>注意:
     *
     * <p>每个数组中的元素不会超过 100 数组的大小不会超过 200 示例 1:
     *
     * <p>输入: [1, 5, 11, 5]
     *
     * <p>输出: true
     *
     * <p>解释: 数组可以分割成 [1, 5, 5] 和 [11].
     *
     * <p>示例 2:
     *
     * <p>输入: [1, 2, 3, 5]
     *
     * <p>输出: false
     *
     * <p>解释: 数组不能分割成两个元素和相等的子集.
     *
     * @param nums
     * @return
     */
    public boolean canPartition(int[] nums) {
        if (nums.length <= 1) {
            return false;
        }
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }

        if ((sum & 1) == 1) {
            return false;
        }
        int target = sum >> 1;

        boolean[][] dp = new boolean[nums.length][target + 1];
        Arrays.sort(nums);
        dp[0][0] = true;
        dp[0][nums[0]] = true;
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j <= target; j++) {
                dp[i][j] = dp[i - 1][j];
                if (!dp[i][j] && nums[i] <= j) {
                    dp[i][j] = dp[i - 1][j - nums[i]];
                }
            }
            if (dp[i][target]) {
                return true;
            }
        }

        return dp[nums.length - 1][target];
    }

    @Test
    public void oneEditAway() {
        String first = "pale", second = "pwle";
        logResult(oneEditAway(first, second));
    }

    /**
     * 面试题 01.05. 一次编辑 字符串有三种编辑操作:插入一个字符、删除一个字符或者替换一个字符。 给定两个字符串，编写一个函数判定它们是否只需要一次(或者零次)编辑。
     *
     * <p>示例 1:
     *
     * <p>输入: first = "pale" second = "ple" 输出: True
     *
     * <p>示例 2:
     *
     * <p>输入: first = "pales" second = "pal" 输出: False
     *
     * @param first
     * @param second
     * @return
     */
    public boolean oneEditAway(String first, String second) {
        int[][] dp = new int[first.length() + 1][second.length() + 1];
        dp[0][0] = 0;
        for (int i = 0; i < first.length(); i++) {
            dp[i + 1][0] = i + 1;
        }
        for (int j = 0; j < second.length(); j++) {
            dp[0][j + 1] = j + 1;
        }
        for (int i = 0; i < first.length(); i++) {
            for (int j = 0; j < second.length(); j++) {
                if (first.charAt(i) == second.charAt(j)) {
                    dp[i + 1][j + 1] = dp[i][j];
                } else {
                    dp[i + 1][j + 1] = Math.min(dp[i][j], Math.min(dp[i + 1][j], dp[i][j + 1])) + 1;
                }

                // dp[i + 1][j + 1] = dp[i][j] && first.charAt(i) == second.charAt(j);
            }
        }
        logResult(dp);

        return dp[first.length()][second.length()] <= 1;
    }

    @Test
    public void numberOf2sInRange() {
        int n = 25;
        logResult(numberOf2sInRange(n));
    }

    /**
     * 面试题 17.06. 2出现的次数
     *
     * <p>编写一个方法，计算从 0 到 n (含 n) 中数字 2 出现的次数。
     *
     * <p>示例:
     *
     * <p>输入: 25 输出: 9 解释: (2, 12, 20, 21, 22, 23, 24, 25)(注意 22 应该算作两次) 提示：
     *
     * <p>n <= 10^9
     *
     * @param n
     * @return
     */
    public int numberOf2sInRange(int n) {
        if (n == 0) {
            return 0;
        }
        int digit = (int) Math.log10(n) + 1;
        int[][] dp = new int[digit + 1][2];
        // dp[i][0] = numberOf2sInRange(n % pow(10, i)) 保存0~n的1-i位组成的数包含2的个数
        // dp[i][1] = numberOf2sInRange(99..9) 保存i位均为9包含2的个数
        dp[1][0] = n % 10 >= 2 ? 1 : 0;
        dp[1][1] = 1;
        for (int i = 2; i <= digit; i++) {
            int k = n / ((int) Math.pow(10, i - 1)) % 10;
            dp[i][0] = k * dp[i - 1][1] + dp[i - 1][0];
            if (k == 2) {
                dp[i][0] += n % (int) Math.pow(10, i - 1) + 1;
            } else if (k > 2) {
                dp[i][0] += (int) Math.pow(10, i - 1);
            }
            dp[i][1] = 10 * dp[i - 1][1] + (int) Math.pow(10, i - 1); // 计算1-i位均为9的值包含2的个数
        }
        return dp[digit][0];
    }

    /*private int calNumber2(int n,int) {

    }*/

    /**
     * 837. 新21点 爱丽丝参与一个大致基于纸牌游戏 “21点” 规则的游戏，描述如下：
     *
     * <p>爱丽丝以 0 分开始，并在她的得分少于 K 分时抽取数字。 抽取时，她从 [1, W] 的范围中随机获得一个整数作为分数进行累计，其中 W 是整数。
     * 每次抽取都是独立的，其结果具有相同的概率。
     *
     * <p>当爱丽丝获得不少于 K 分时，她就停止抽取数字。 爱丽丝的分数不超过 N 的概率是多少？
     *
     * <p>示例 1：
     *
     * <p>输入：N = 10, K = 1, W = 10 输出：1.00000 说明：爱丽丝得到一张卡，然后停止。 示例 2：
     *
     * <p>输入：N = 6, K = 1, W = 10 输出：0.60000 说明：爱丽丝得到一张卡，然后停止。 在 W = 10 的 6 种可能下，她的得分不超过 N = 6 分。 示例
     * 3：
     *
     * <p>输入：N = 21, K = 17, W = 10 输出：0.73278 提示：
     *
     * <p>0 <= K <= N <= 10000 1 <= W <= 10000 如果答案与正确答案的误差不超过 10^-5，则该答案将被视为正确答案通过。 此问题的判断限制时间已经减少。
     *
     * @param N
     * @param K
     * @param W
     * @return
     */
    public double new21Game(int N, int K, int W) {
        if (K == 0) {
            return 1.0;
        }
        double[] dp = new double[K + W];
        // dp[x]= dp[x+1]+dp[x+2]+⋯+dp[x+W] /W
        for (int i = K; i <= N && i < K + W; i++) {
            dp[i] = 1.0;
        }
        /*
        for (int i = K - 1; i >= 0; i--){
            double sum = 0.0;
            for (int j = 1; j <= W; j++) {
                sum += dp[i + j];
            }
            dp[i] = sum /(double)W;
        }

        return dp[0]; */
        dp[K - 1] = 1.0 * Math.min(N - K + 1, W) / W;
        for (int i = K - 2; i >= 0; i--) {
            dp[i] = dp[i + 1] - (dp[i + W + 1] - dp[i + 1]) / W;
        }
        return dp[0];
    }

    @Test
    public void findSquare() {
        int[][] matrix = {{1, 0, 1}, {0, 0, 1}, {0, 0, 1}};
        int[] result = findSquare(matrix);
        log.debug("result:{}", result);
    }

    /**
     * 面试题 17.23. 最大黑方阵
     *
     * <p>给定一个方阵，其中每个单元(像素)非黑即白。设计一个算法，找出 4 条边皆为黑色像素的最大子方阵。
     *
     * <p>返回一个数组 [r, c, size] ，其中 r, c 分别代表子方阵左上角的行号和列号， size 是子方阵的边长。若有多个满足条件的子方阵，返回 r 最小的，若 r
     * 相同，返回 c 最小的子方阵。若无满足条件的子方阵，返回空数组。
     *
     * <p>示例 1:
     *
     * <p>输入: [ [1,0,1], [0,0,1], [0,0,1] ] 输出: [1,0,2] 解释: 输入中 0 代表黑色，1 代表白色，标粗的元素即为满足条件的最大子方阵
     *
     * <p>示例 2:
     *
     * <p>输入: [ [0,1,1], [1,0,1], [1,1,0] ] 输出: [0,0,1]
     *
     * <p>提示： matrix.length == matrix[0].length <= 200
     *
     * @param matrix
     * @return
     */
    public int[] findSquare(int[][] matrix) {
        int[] result = new int[3];
        int n = matrix.length;
        if (n == 0) {
            return new int[0];
        }
        int max = 0;
        // 行, 列,  {0 横向, 1纵向}
        int[][][] dp = new int[n + 1][n + 1][2];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 1) {
                    continue;
                }
                //  0 横向, 1纵向
                dp[i + 1][j + 1][0] = dp[i + 1][j][0] + 1;
                dp[i + 1][j + 1][1] = dp[i][j + 1][1] + 1;

                max = Math.min(dp[i + 1][j + 1][0], dp[i][j - 1][1]);
                if (max <= result[2]) {
                    continue;
                }

                for (int k = max; k > result[2]; k--) {
                    if (dp[i + 1 - k + 1][j + 1][0] < k) {
                        continue;
                    }
                    if (dp[i + 1][j + 1 - k + 1][1] < k) {
                        continue;
                    }
                    if (k > result[2]) {
                        result[2] = k;
                        result[1] = j - k + 1;
                        result[0] = i - k + 1;
                        break;
                    }
                }
            }
        }
        logResult(dp);
        /*int n = matrix.length;
        int[] result = new int[3];
        int[][] right = new int[n][n];
        int[][] down = new int[n][n];
        if (matrix[n - 1][n - 1] == 0) {
            right[n - 1][n - 1] = 1;
            down[n - 1][n - 1] = 1;
        }
        for (int i = n - 2; i >= 0; i--) {
            if (matrix[i][n - 1] == 0) {
                right[i][n - 1] = 1;
                down[i][n - 1] = down[i + 1][n - 1] + 1;
            }
            if (matrix[n - 1][i] == 0) {
                right[n - 1][i] = right[n - 1][i + 1] + 1;
                down[n - 1][i] = 1;
            }
        }
        for (int i = n - 2; i >= 0; i--)
            for (int j = n - 2; j >= 0; j--) {
                if (matrix[i][j] == 0) {
                    right[i][j] = right[i][j + 1] + 1;
                    down[i][j] = down[i + 1][j] + 1;
                }
            }
        for (int i = 0; i < n - result[2]; i++)
            for (int j = 0; j < n - result[2]; j++) {
                if (matrix[i][j] == 0) {
                    int maxn;
                    if (right[i][j] < down[i][j]) maxn = right[i][j];
                    else maxn = down[i][j];
                    for (int k = result[2]; k < maxn; k++) {
                        if (right[i + k][j] > k && down[i][j + k] > k) {
                            result[0] = i;
                            result[1] = j;
                            result[2] = k + 1;
                        }
                    }
                }
            }*/
        return result[2] == 0 ? new int[0] : result;
    }

    @Test
    public void pathWithObstacles() {
        int[][] obstacleGrid = {{0, 0, 0}, {0, 1, 0}, {0, 0, 0}};
        logResult(pathWithObstacles(obstacleGrid));
    }

    /**
     * 面试题 08.02. 迷路的机器人
     *
     * <p>设想有个机器人坐在一个网格的左上角，网格 r 行 c 列。机器人只能向下或向右移动，但不能走到一些被禁止的网格（有障碍物）。设计一种算法，寻找机器人从左上角移动到右下角的路径。
     *
     * <p>网格中的障碍物和空位置分别用 1 和 0 来表示。
     *
     * <p>返回一条可行的路径，路径由经过的网格的行号和列号组成。左上角为 0 行 0 列。如果没有可行的路径，返回空数组。
     *
     * <p>示例 1:
     *
     * <p>输入: [ [0,0,0], [0,1,0], [0,0,0] ] 输出: [[0,0],[0,1],[0,2],[1,2],[2,2]] 解释:
     * 输入中标粗的位置即为输出表示的路径，即 0行0列（左上角） -> 0行1列 -> 0行2列 -> 1行2列 -> 2行2列（右下角） 说明：r 和 c 的值均不超过 100。
     *
     * @param obstacleGrid
     * @return
     */
    public List<List<Integer>> pathWithObstacles(int[][] obstacleGrid) {
        List<List<Integer>> result = new ArrayList<>();
        int rows = obstacleGrid.length;
        if (rows == 0) {
            return result;
        }
        int cols = obstacleGrid[0].length;
        if (cols == 0) {
            return result;
        }
        boolean[][] dp = new boolean[rows][cols];
        if (obstacleGrid[0][0] == 0) {
            dp[0][0] = true;
        }
        for (int i = 1; i < rows; i++) {
            if (obstacleGrid[i][0] == 1) {
                continue;
            }
            dp[i][0] = dp[i - 1][0];
        }
        for (int j = 1; j < cols; j++) {
            if (obstacleGrid[0][j] == 1) {
                continue;
            }
            dp[0][j] = dp[0][j - 1];
        }
        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < cols; j++) {
                if (obstacleGrid[i][j] == 1) {
                    continue;
                }
                dp[i][j] = dp[i - 1][j] || dp[i][j - 1];
            }
        }
        logResult(dp);
        if (!dp[rows - 1][cols - 1]) {
            return result;
        }
        int row = rows - 1, col = cols - 1;
        // 从终点反推
        while (row > 0 || col > 0) {
            List<Integer> list = Arrays.asList(row, col);
            result.add(list);
            if (row > 0 && dp[row - 1][col]) {
                row--;
            } else if (col > 0 && dp[row][col - 1]) {
                col--;
            }
        }
        result.add(Arrays.asList(0, 0));
        Collections.reverse(result);

        return result;
    }

    /**
     * 面试题 17.08. 马戏团人塔
     *
     * <p>有个马戏团正在设计叠罗汉的表演节目，一个人要站在另一人的肩膀上。出于实际和美观的考虑，在上面的人要比下面的人矮一点且轻一点。已知马戏团每个人的身高和体重，请编写代码计算叠罗汉最多能叠几个人。
     *
     * <p>示例：
     *
     * <p>输入：height = [65,70,56,75,60,68] weight = [100,150,90,190,95,110] 输出：6 解释：从上往下数，叠罗汉最多能叠 6
     * 层：(56,90), (60,95), (65,100), (68,110), (70,150), (75,190) 提示：
     *
     * <p>height.length == weight.length <= 10000
     *
     * @param height
     * @param weight
     * @return
     */
    public int bestSeqAtIndex(int[] height, int[] weight) {
        // 俄罗斯套娃问题
        int len = height.length;
        int[][] person = new int[len][2];
        for (int i = 0; i < len; i++) {
            person[i] = new int[] {height[i], weight[i]};
        }
        // height 升序排列, height相同 weight 降序
        Arrays.sort(person, (a, b) -> a[0] == b[0] ? b[1] - a[1] : a[0] - b[0]);
        // 查询 weight的最长递增子序列
        int[] dp = new int[len];
        int result = 0;
        for (int[] pair : person) {
            // 二分法查找 0 ~ result 下标 第一个 pair[1]的下标
            int i = Arrays.binarySearch(dp, 0, result, pair[1]);
            if (i < 0) {
                i = -(i + 1);
            }
            dp[i] = pair[1];
            if (i == result) {
                ++result;
            }
        }

        return result;
    }

    @Test
    public void isMatch() {
        String s = "aab", p = "c*a*b";
        logResult(isMatch(s, p));
    }

    /**
     * 面试题19. 正则表达式匹配
     *
     * <p>请实现一个函数用来匹配包含'.'和'*'的正则表达式。模式中的字符'.'表示任意一个字符，而'*'表示它前面的字符可以出现任意次（含0次）。
     * 在本题中，匹配是指字符串的所有字符匹配整个模式。例如，字符串"aaa"与模式"a.a"和"ab*ac*a"匹配，但与"aa.a"和"ab*a"均不匹配。
     *
     * <p>示例 1:
     *
     * <p>输入: s = "aa" p = "a" 输出: false 解释: "a" 无法匹配 "aa" 整个字符串。 示例 2:
     *
     * <p>输入: s = "aa" p = "a*" 输出: true 解释: 因为 '*' 代表可以匹配零个或多个前面的那一个元素, 在这里前面的元素就是 'a'。因此，字符串 "aa"
     * 可被视为 'a' 重复了一次。 示例 3:
     *
     * <p>输入: s = "ab" p = ".*" 输出: true 解释: ".*" 表示可匹配零个或多个（'*'）任意字符（'.'）。 示例 4:
     *
     * <p>输入: s = "aab" p = "c*a*b" 输出: true 解释: 因为 '*' 表示零个或多个，这里 'c' 为 0 个, 'a' 被重复一次。因此可以匹配字符串
     * "aab"。 示例 5:
     *
     * <p>输入: s = "mississippi" p = "mis*is*p*." 输出: false s 可能为空，且只包含从 a-z 的小写字母。 p 可能为空，且只包含从 a-z
     * 的小写字母以及字符 . 和 *，无连续的 '*'。 注意：本题与主站 10
     * 题相同：https://leetcode-cn.com/problems/regular-expression-matching/
     *
     * @param s
     * @param p
     * @return
     */
    public boolean isMatch(String s, String p) {
        int m = s.length(), n = p.length();
        boolean[][] match = new boolean[m + 1][n + 1];
        match[m][n] = true;
        for (int j = n - 2; j >= 0; j--) {
            match[m][j] = p.charAt(j + 1) == '*' && match[m][j + 2];
        }
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                char c1 = s.charAt(i), c2 = p.charAt(j);
                // 同位置的字符相同或者有.号, 查看后面一位是否匹配
                boolean charMatch = c1 == c2 || c2 == '.';
                // 当前位置后面是 *
                if (j < n - 1 && p.charAt(j + 1) == '*') {
                    match[i][j] = match[i][j + 2] || charMatch && match[i + 1][j];
                } else {
                    match[i][j] = charMatch && match[i + 1][j + 1];
                }
            }
        }
        logResult(match);

        return match[0][0];
    }

    @Test
    public void getMaxMatrix() {

        int[][] matrix = {{9, -8, 1, 3, -2}, {-3, 7, 6, -2, 4}, {6, -4, -4, 8, -7}};
        int[] result = getMaxMatrix(matrix);
        log.debug("result:{}", result);
    }
    /**
     * 面试题 17.24. 最大子矩阵
     *
     * <p>给定一个正整数和负整数组成的 N × M 矩阵，编写代码找出元素总和最大的子矩阵。
     *
     * <p>返回一个数组 [r1, c1, r2, c2]，其中 r1, c1 分别代表子矩阵左上角的行号和列号，r2, c2
     * 分别代表右下角的行号和列号。若有多个满足条件的子矩阵，返回任意一个均可。
     *
     * <p>注意：本题相对书上原题稍作改动
     *
     * <p>示例:
     *
     * <p>输入: [ [-1,0], [0,-1] ] 输出: [0,1,0,1] 解释: 输入中标粗的元素即为输出所表示的矩阵 说明：
     *
     * <p>1 <= matrix.length, matrix[0].length <= 200
     *
     * @param matrix
     * @return
     */
    public int[] getMaxMatrix(int[][] matrix) {
        int[] result = new int[4];
        int rows = matrix.length;
        int cols = matrix[0].length;
        int max = Integer.MIN_VALUE;
        // int[][] dp = new int[rows + 1][cols + 1];

        int startRow = 0, startCol = 0;
        //
        for (int i = 0; i < rows; i++) {
            int[] dp = new int[cols];
            for (int j = i; j < rows; j++) {
                // 计算每一行的和
                int sum = 0;
                for (int k = 0; k < cols; k++) {
                    dp[k] += matrix[j][k];
                    if (sum > 0) {
                        sum += dp[k];
                    } else {
                        sum = dp[k];
                        startRow = i;
                        startCol = k;
                    }
                    if (sum > max) {
                        max = sum;
                        result[0] = startRow;
                        result[1] = startCol;
                        result[2] = j;
                        result[3] = k;
                    }
                }
            }
        }

        logResult(max);

        return result;
    }

    /**
     * 面试题 08.13.堆箱子
     *
     * <p>堆箱子。给你一堆n个箱子，箱子宽 wi、深 di、高
     * hi。箱子不能翻转，将箱子堆起来时，下面箱子的宽度、高度和深度必须大于上面的箱子。实现一种方法，搭出最高的一堆箱子。箱堆的高度为每个箱子高度的总和。
     *
     * <p>输入使用数组[wi, di, hi]表示每个箱子。
     *
     * <p>示例1:
     *
     * <p>输入：box = [[1, 1, 1], [2, 2, 2], [3, 3, 3]] 输出：6 示例2:
     *
     * <p>输入：box = [[1, 1, 1], [2, 3, 4], [2, 6, 7], [3, 4, 5]] 输出：10 提示:
     *
     * <p>箱子的数目不大于3000个。
     *
     * @param box
     * @return
     */
    public int pileBox(int[][] box) {
        int len = box.length;
        if (len == 0) {
            return 0;
        }

        int[] dp = new int[len];
        Arrays.sort(
                box,
                (a, b) -> {
                    if (a[0] != b[0]) {
                        return a[0] - b[0];
                    }
                    if (a[1] != b[1]) {
                        return a[1] - b[1];
                    }
                    return a[2] - b[2];
                });
        dp[0] = box[0][2];
        int result = dp[0];
        for (int i = 1; i < len; i++) {
            int[] cur = box[i];
            int max = 0;
            for (int j = 0; j < i; j++) {
                if (cur[0] > box[j][0] && cur[1] > box[j][1] && cur[2] > box[j][2]) {
                    max = Math.max(max, dp[j]);
                }
            }
            dp[i] = max + cur[2];
            result = Math.max(result, dp[i]);
        }

        return result;
    }

    @Test
    public void isMatch2() {
        String s = "aab", p = "c*a*b";
        logResult(isMatch2(s, p));
    }

    /**
     * 10. 正则表达式匹配
     *
     * <p>给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。
     *
     * <p>'.' 匹配任意单个字符 '*' 匹配零个或多个前面的那一个元素 所谓匹配，是要涵盖 整个 字符串 s的，而不是部分字符串。
     *
     * <p>说明:
     *
     * <p>s 可能为空，且只包含从 a-z 的小写字母。 p 可能为空，且只包含从 a-z 的小写字母，以及字符 . 和 *。 示例 1:
     *
     * <p>输入: s = "aa" p = "a" 输出: false 解释: "a" 无法匹配 "aa" 整个字符串。 示例 2:
     *
     * <p>输入: s = "aa" p = "a*" 输出: true 解释: 因为 '*' 代表可以匹配零个或多个前面的那一个元素, 在这里前面的元素就是 'a'。因此，字符串 "aa"
     * 可被视为 'a' 重复了一次。 示例 3:
     *
     * <p>输入: s = "ab" p = ".*" 输出: true 解释: ".*" 表示可匹配零个或多个（'*'）任意字符（'.'）。 示例 4:
     *
     * <p>输入: s = "aab" p = "c*a*b" 输出: true 解释: 因为 '*' 表示零个或多个，这里 'c' 为 0 个, 'a' 被重复一次。因此可以匹配字符串
     * "aab"。 示例 5:
     *
     * <p>输入: s = "mississippi" p = "mis*is*p*." 输出: false
     *
     * @param s
     * @param p
     * @return
     */
    public boolean isMatch2(String s, String p) {
        int m = s.length(), n = p.length();
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[m][n] = true;
        for (int j = n - 2; j >= 0; j--) {
            dp[m][j] = p.charAt(j + 1) == '*' && dp[m][j + 2];
        }
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                char c1 = s.charAt(i), c2 = p.charAt(j);
                // 同位置的字符相同或者有.号, 查看后面一位是否匹配
                boolean charMatch = c1 == c2 || c2 == '.';
                // 后面一位是*
                if (j < n - 1 && p.charAt(j + 1) == '*') {
                    // 1 * 匹配 1 或 多个 dp[i + 1][j] 后一位 为 true + charMatch
                    // 2 * 匹配 0 个 dp[i][j + 2]
                    dp[i][j] = charMatch && dp[i + 1][j] || dp[i][j + 2];
                } else {
                    dp[i][j] = charMatch && dp[i + 1][j + 1];
                }
            }
        }
        logResult(dp);

        return dp[0][0];
    }

    /**
     * 464. 我能赢吗
     *
     * <p>在 "100 game" 这个游戏中，两名玩家轮流选择从 1 到 10 的任意整数，累计整数和，先使得累计整数和达到 100 的玩家，即为胜者。
     *
     * <p>如果我们将游戏规则改为 “玩家不能重复使用整数” 呢？
     *
     * <p>例如，两个玩家可以轮流从公共整数池中抽取从 1 到 15 的整数（不放回），直到累计整数和 >= 100。
     *
     * <p>给定一个整数 maxChoosableInteger （整数池中可选择的最大数）和另一个整数
     * desiredTotal（累计和），判断先出手的玩家是否能稳赢（假设两位玩家游戏时都表现最佳）？
     *
     * <p>你可以假设 maxChoosableInteger 不会大于 20， desiredTotal 不会大于 300。
     *
     * <p>示例：
     *
     * <p>输入： maxChoosableInteger = 10 desiredTotal = 11
     *
     * <p>输出： false
     *
     * <p>解释： 无论第一个玩家选择哪个整数，他都会失败。 第一个玩家可以选择从 1 到 10 的整数。 如果第一个玩家选择 1，那么第二个玩家只能选择从 2 到 10 的整数。
     * 第二个玩家可以通过选择整数 10（那么累积和为 11 >= desiredTotal），从而取得胜利. 同样地，第一个玩家选择任意其他整数，第二个玩家都会赢。
     *
     * @param maxChoosableInteger
     * @param desiredTotal
     * @return
     */
    public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
        if (maxChoosableInteger >= desiredTotal) {
            return true;
        }
        if ((1 + maxChoosableInteger) * maxChoosableInteger / 2 < desiredTotal) {
            return false; // 1,..maxChoosable数列总和都比目标和小
        }
        int[] state = new int[maxChoosableInteger + 1]; // state[1]=1表示1被选了

        return backtraceWitMemo(state, desiredTotal, new HashMap<String, Boolean>());
    }

    private boolean backtraceWitMemo(int[] state, int desiredTotal, HashMap<String, Boolean> map) {
        String key = Arrays.toString(state); // 这里比较关键，如何表示这个唯一的状态，例如[2,3]和[3,2]都是"0011"，状态一样
        if (map.containsKey(key)) {
            return map.get(key); // 如果已经记忆了这样下去的输赢结果,记忆是为了防止如[2,3]，[3,2]之后的[1,4,5,..]这个选择区间被重复计算
        }

        for (int i = 1; i < state.length; i++) {
            if (state[i] == 0) { // 如果这个数字i还没有被选中
                state[i] = 1;
                // 如果当前选了i已经赢了或者选了i还没赢但是后面对方选择输了
                if (desiredTotal - i <= 0 || !backtraceWitMemo(state, desiredTotal - i, map)) {
                    map.put(key, true);
                    state[i] = 0; // 在返回之前回溯
                    return true;
                }
                // 如果不能赢也要记得回溯
                state[i] = 0;
            }
        }
        // 如果都赢不了
        map.put(key, false);
        return false;
    }

    @Test
    public void findSubstringInWraproundString() {
        String s = "cac";
        logResult(findSubstringInWraproundString(s));
    }

    /**
     * 467. 环绕字符串中唯一的子字符串
     *
     * <p>把字符串 s 看作是“abcdefghijklmnopqrstuvwxyz”的无限环绕字符串，所以 s
     * 看起来是这样的："...zabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcd....".
     *
     * <p>现在我们有了另一个字符串 p 。你需要的是找出 s 中有多少个唯一的 p 的非空子串，尤其是当你的输入是字符串 p ，你需要输出字符串 s 中 p 的不同的非空子串的数目。
     *
     * <p>注意: p 仅由小写的英文字母组成，p 的大小可能超过 10000。
     *
     * <p>示例 1:
     *
     * <p>输入: "a" 输出: 1 解释: 字符串 S 中只有一个"a"子字符。
     *
     * <p>示例 2:
     *
     * <p>输入: "cac" 输出: 2 解释: 字符串 S 中的字符串“cac”只有两个子串“a”、“c”。.
     *
     * <p>示例 3:
     *
     * <p>输入: "zab" 输出: 6 解释: 在字符串 S 中有六个子串“z”、“a”、“b”、“za”、“ab”、“zab”。.
     *
     * @param p
     * @return
     */
    public int findSubstringInWraproundString(String p) {
        if (p.length() == 0) {
            return 0;
        }
        // 思路： 记录每个字母结尾的最大长度
        int[] dp = new int[26];
        int count = 0;
        for (int i = 0; i < p.length(); i++) {
            char c = p.charAt(i);
            if (i > 0 && (c - p.charAt(i - 1) - 1) % 26 == 0) {
                count++;
            } else {
                count = 1;
            }
            dp[c - 'a'] = Math.max(dp[c - 'a'], count);
        }
        int sum = 0;
        for (int n : dp) {
            sum += n;
        }

        return sum;
    }

    @Test
    public void wordBreak() {
        // String s = "catsandog";
        String s =
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab";
        List<String> wordDict = new ArrayList<>();
        /*wordDict.add("cats");
        wordDict.add("dog");
        wordDict.add("and");
        wordDict.add("cat");*/
        wordDict.add("a");
        wordDict.add("aa");
        wordDict.add("aaa");
        wordDict.add("aaaa");
        wordDict.add("aaaaa");
        wordDict.add("aaaaaa");
        wordDict.add("aaaaaaaaaaaaaaaaaaa");
        boolean result = wordBreak(s, wordDict);
        logResult(result);
    }

    /**
     * 139. 单词拆分
     *
     * <p>给定一个非空字符串 s 和一个包含非空单词列表的字典 wordDict，判定 s 是否可以被空格拆分为一个或多个在字典中出现的单词。
     *
     * <p>说明：
     *
     * <p>拆分时可以重复使用字典中的单词。 你可以假设字典中没有重复的单词。 示例 1：
     *
     * <p>输入: s = "leetcode", wordDict = ["leet", "code"] 输出: true 解释: 返回 true 因为 "leetcode" 可以被拆分成
     * "leet code"。 示例 2：
     *
     * <p>输入: s = "applepenapple", wordDict = ["apple", "pen"] 输出: true 解释: 返回 true 因为
     * "applepenapple" 可以被拆分成 "apple pen apple"。 注意你可以重复使用字典中的单词。 示例 3：
     *
     * <p>输入: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"] 输出: false
     *
     * @param s
     * @param wordDict
     * @return
     */
    public boolean wordBreak(String s, List<String> wordDict) {
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        for (int i = 1; i <= s.length(); i++) {
            if (dp[i]) {
                continue;
            }
            for (int j = 0; j < wordDict.size(); j++) {
                String word = wordDict.get(j);
                if (i >= word.length()
                        && dp[i - word.length()]
                        && Objects.equals(word, s.substring(i - word.length(), i))) {
                    dp[i] = true;
                }
            }
        }

        return dp[s.length()];
    }

    @Test
    public void test2() {
        String s = "abcdefg";
        int len = 2;

        for (int i = 1; i < s.length(); i++) {
            if (i >= len) {
                log.debug("i :{} s :{}", i, s.substring(i - len, i));
            }
        }
    }

    @Test
    public void findMaxForm() {
        String[] strs = {"10", "0001", "111001", "1", "0"};
        int m = 3, n = 4;
        logResult(findMaxForm(strs, m, n));
    }
    /**
     * 474. 一和零
     *
     * <p>在计算机界中，我们总是追求用有限的资源获取最大的收益。
     *
     * <p>现在，假设你分别支配着 m 个 0 和 n 个 1。另外，还有一个仅包含 0 和 1 字符串的数组。
     *
     * <p>你的任务是使用给定的 m 个 0 和 n 个 1 ，找到能拼出存在于数组中的字符串的最大数量。每个 0 和 1 至多被使用一次。
     *
     * <p>注意:
     *
     * <p>给定 0 和 1 的数量都不会超过 100。 给定字符串数组的长度不会超过 600。 示例 1:
     *
     * <p>输入: Array = {"10", "0001", "111001", "1", "0"}, m = 5, n = 3 输出: 4
     *
     * <p>解释: 总共 4 个字符串可以通过 5 个 0 和 3 个 1 拼出，即 "10","0001","1","0" 。 示例 2:
     *
     * <p>输入: Array = {"10", "0", "1"}, m = 1, n = 1 输出: 2
     *
     * <p>解释: 你可以拼出 "10"，但之后就没有剩余数字了。更好的选择是拼出 "0" 和 "1"
     *
     * @param strs
     * @param m
     * @param n
     * @return
     */
    public int findMaxForm(String[] strs, int m, int n) {
        // 0 1 背包问题
        int[] zeroCounts = new int[strs.length];
        int[] oneCounts = new int[strs.length];
        for (int i = 0; i < strs.length; i++) {
            String str = strs[i];
            int oneCount = 0, zeroCount = 0;
            for (char c : str.toCharArray()) {
                switch (c) {
                    case '0':
                        zeroCount++;
                        break;
                    case '1':
                        oneCount++;
                        break;
                }
            }
            zeroCounts[i] = zeroCount;
            oneCounts[i] = oneCount;
        }
        log.debug("zeroCounts :{}", zeroCounts);
        log.debug("oneCounts :{}", oneCounts);
        int[][][] dp = new int[strs.length + 1][m + 1][n + 1];
        for (int i = 1; i <= strs.length; i++) {
            int zeroCount = zeroCounts[i - 1], oneCount = oneCounts[i - 1];
            for (int j = 0; j <= m; j++) {
                if (n + 1 >= 0) System.arraycopy(dp[i - 1][j], 0, dp[i][j], 0, n + 1);
            }
            for (int j = zeroCount; j <= m; j++) {
                for (int k = oneCount; k <= n; k++) {
                    dp[i][j][k] =
                            Math.max(dp[i - 1][j][k], dp[i - 1][j - zeroCount][k - oneCount] + 1);
                }
            }
        }
        logResult(dp);

        return dp[strs.length][m][n];
    }

    @Test
    public void predictTheWinner() {
        int[] nums = {1, 5, 2, 4, 6};
        logResult(PredictTheWinner(nums));
    }

    /**
     * 486. 预测赢家
     *
     * <p>给定一个表示分数的非负整数数组。
     * 玩家1从数组任意一端拿取一个分数，随后玩家2继续从剩余数组任意一端拿取分数，然后玩家1拿，……。每次一个玩家只能拿取一个分数，分数被拿取之后不再可取。直到没有剩余分数可取时游戏结束。最终获得分数总和最多的玩家获胜。
     *
     * <p>给定一个表示分数的数组，预测玩家1是否会成为赢家。你可以假设每个玩家的玩法都会使他的分数最大化。
     *
     * <p>示例 1:
     *
     * <p>输入: [1, 5, 2] 输出: False 解释: 一开始，玩家1可以从1和2中进行选择。
     * 如果他选择2（或者1），那么玩家2可以从1（或者2）和5中进行选择。如果玩家2选择了5，那么玩家1则只剩下1（或者2）可选。 所以，玩家1的最终分数为 1 + 2 = 3，而玩家2为
     * 5。 因此，玩家1永远不会成为赢家，返回 False。 示例 2:
     *
     * <p>输入: [1, 5, 233, 7] 输出: True 解释: 玩家1一开始选择1。然后玩家2必须从5和7中进行选择。无论玩家2选择了哪个，玩家1都可以选择233。
     * 最终，玩家1（234分）比玩家2（12分）获得更多的分数，所以返回 True，表示玩家1可以成为赢家。 注意:
     *
     * <p>1 <= 给定的数组长度 <= 20. 数组里所有分数都为非负数且不会大于10000000。 如果最终两个玩家的分数相等，那么玩家1仍为赢家。
     *
     * @param nums
     * @return
     */
    public boolean PredictTheWinner(int[] nums) {
        // [1,5,2,4,6]

        // 使用动态规划来解决这个问题。用 dp(i, j) 表示当剩下的数为 nums[i .. j]
        // 时，当前操作的选手（注意，不一定是先手）与另一位选手最多的分数差。当前操作的选手可以选择 nums[i] 并留下 nums[i+1 .. j]，或选择 nums[j] 并留下
        // nums[i .. j-1]，因此状态转移方程为：
        //
        //
        // dp(i, j) = max(nums[i] - dp(i+1, j), nums[j] - dp(i, j-1))
        // dp(i, i) = nums[i]
        //

        int len = nums.length;
        int[][] dp = new int[len][len];
        for (int i = 0; i < len; i++) {
            dp[i][i] = nums[i];
        }
        for (int i = len - 2; i >= 0; i--) {
            for (int j = i + 1; j < len; j++) {
                int a = nums[i] - dp[i + 1][j];
                int b = nums[j] - dp[i][j - 1];
                dp[i][j] = Math.max(a, b);
            }
        }
        logResult(dp);

        return dp[0][len - 1] >= 0;
    }

    /**
     * 516. 最长回文子序列
     *
     * <p>给定一个字符串 s ，找到其中最长的回文子序列，并返回该序列的长度。可以假设 s 的最大长度为 1000 。
     *
     * <p>示例 1: 输入:
     *
     * <p>"bbbab" 输出:
     *
     * <p>4 一个可能的最长回文子序列为 "bbbb"。
     *
     * <p>示例 2: 输入:
     *
     * <p>"cbbd" 输出:
     *
     * <p>2 一个可能的最长回文子序列为 "bb"。
     *
     * <p>提示：
     *
     * <p>1 <= s.length <= 1000 s 只包含小写英文字母
     *
     * @param s
     * @return
     */
    public int longestPalindromeSubseq(String s) {
        int len = s.length();
        // dp[i][j] 表示 i ~ j 最长的回文子序列长度
        int[][] dp = new int[len][len];
        for (int i = len - 1; i >= 0; i--) {
            dp[i][i] = 1;
            for (int j = i + 1; j < len; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }

        return dp[0][len - 1];
    }

    @Test
    public void numSubseq() {
        int[] nums = {3, 5, 6, 7};
        int target = 9;
        logResult(numSubseq(nums, target));
    }

    /**
     * 5450. 满足条件的子序列数目
     *
     * <p>给你一个整数数组 nums 和一个整数 target 。
     *
     * <p>请你统计并返回 nums 中能满足其最小元素与最大元素的 和 小于或等于 target 的 非空 子序列的数目。
     *
     * <p>由于答案可能很大，请将结果对 10^9 + 7 取余后返回。
     *
     * <p>示例 1：
     *
     * <p>输入：nums = [3,5,6,7], target = 9 输出：4 解释：有 4 个子序列满足该条件。 [3] -> 最小元素 + 最大元素 <= target (3 + 3
     * <= 9) [3,5] -> (3 + 5 <= 9) [3,5,6] -> (3 + 6 <= 9) [3,6] -> (3 + 6 <= 9) 示例 2：
     *
     * <p>输入：nums = [3,3,6,8], target = 10 输出：6 解释：有 6 个子序列满足该条件。（nums 中可以有重复数字） [3] , [3] , [3,3],
     * [3,6] , [3,6] , [3,3,6] 示例 3：
     *
     * <p>输入：nums = [2,3,3,4,6,7], target = 12 输出：61 解释：共有 63 个非空子序列，其中 2 个不满足条件（[6,7], [7]）
     * 有效序列总数为（63 - 2 = 61） 示例 4：
     *
     * <p>输入：nums = [5,2,4,1,7,6,8], target = 16 输出：127 解释：所有非空子序列都满足条件 (2^7 - 1) = 127
     *
     * <p>提示：
     *
     * <p>1 <= nums.length <= 10^5 1 <= nums[i] <= 10^6 1 <= target <= 10^6
     *
     * @param nums
     * @param target
     * @return
     */
    public int numSubseq(int[] nums, int target) {
        int len = nums.length;
        int MOD = 1_000_000_007;
        int result = 0;
        Arrays.sort(nums);
        if (nums[0] * 2 > target) {
            return 0;
        }
        if (nums[len - 1] * 2 <= target) {
            return (int) Math.pow(2, len) - 1;
        }
        int[] pow = new int[len];
        pow[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            pow[i] = pow[i - 1] * 2;
            pow[i] %= MOD;
        }
        log.debug("pow:{}", pow);
        int left = 0, right = len - 1;
        while (left <= right) {
            if (nums[left] + nums[right] <= target) {
                result += pow[right - left];
                result %= MOD;
                left++;
            } else {
                right--;
            }
        }

        return result;
    }

    @Test
    public void checkSubarraySum() {
        int[] nums = {1, 0, 2};
        int k = 2;
        logResult(checkSubarraySum(nums, k));
    }

    /**
     * 523. 连续的子数组和
     *
     * <p>给定一个包含 非负数 的数组和一个目标 整数 k，编写一个函数来判断该数组是否含有连续的子数组，其大小至少为 2，且总和为 k 的倍数，即总和为 n*k，其中 n 也是一个整数。
     *
     * <p>示例 1：
     *
     * <p>输入：[23,2,4,6,7], k = 6 输出：True 解释：[2,4] 是一个大小为 2 的子数组，并且和为 6。 示例 2：
     *
     * <p>输入：[23,2,6,4,7], k = 6 输出：True 解释：[23,2,6,4,7]是大小为 5 的子数组，并且和为 42。
     *
     * <p>说明：
     *
     * <p>数组的长度不会超过 10,000 。 你可以认为所有数字总和在 32 位有符号整数范围内。
     *
     * @param nums
     * @param k
     * @return
     */
    public boolean checkSubarraySum(int[] nums, int k) {
        int sum = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (k != 0) {
                sum %= k;
            }
            if (map.containsKey(sum)) {
                if (i - map.get(sum) > 1) {
                    return true;
                }
            } else {
                map.put(sum, i);
            }
        }
        return false;
        /*if (nums.length < 2) {
            return false;
        }
        // 同余问题
        int[] dp = new int[k];
        Arrays.fill(dp, -2);
        dp[0] = -1;
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (k != 0) {
                sum %= k;
            }
            if (dp[sum] != -2) {
                if (i - dp[sum] > 1) {
                    return true;
                }
            } else {
                dp[sum] = i;
            }
        }

        return false;*/

        /*if (k == 0) {

            for (int i = 1; i < nums.length; i++) {
                if (nums[i] == 0 && nums[i - 1] == 0) {
                    return true;
                }
            }
            return false;
        }
        if (k < 0) {
            k = -k;
        }
        // 同余问题
        int[] dp = new int[k];
        Arrays.fill(dp, -1);
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            int r = sum % k;
            int current = nums[i] % k;
            if (current == 0) {

            } else if (dp[r] != -1) {
                return true;
            }
            dp[r] = i;
        }
        return false;*/
    }

    /**
     * 650. 只有两个键的键盘
     *
     * <p>最初在一个记事本上只有一个字符 'A'。你每次可以对这个记事本进行两种操作：
     *
     * <p>Copy All (复制全部) : 你可以复制这个记事本中的所有字符(部分的复制是不允许的)。 Paste (粘贴) : 你可以粘贴你上一次复制的字符。 给定一个数字 n
     * 。你需要使用最少的操作次数，在记事本中打印出恰好 n 个 'A'。输出能够打印出 n 个 'A' 的最少操作次数。
     *
     * <p>示例 1:
     *
     * <p>输入: 3 输出: 3 解释: 最初, 我们只有一个字符 'A'。 第 1 步, 我们使用 Copy All 操作。 第 2 步, 我们使用 Paste 操作来获得 'AA'。 第
     * 3 步, 我们使用 Paste 操作来获得 'AAA'。 说明:
     *
     * <p>n 的取值范围是 [1, 1000] 。
     *
     * @param n
     * @return
     */
    public int minSteps(int n) {
        if (n <= 1) {
            return 0;
        }
        // 不允许部分复制
        // 素数分解
        int d = 2;
        int result = 0;
        while (n > 1) {
            while (n % d == 0) {
                result += d;
                n /= d;
            }
            d += 1;
        }
        return result;
    }

    @Test
    public void findLength() {
        int[] A = {1, 2, 3, 2, 1}, B = {3, 2, 1, 4, 7};
        logResult(findLength(A, B));
    }

    /**
     * 718. 最长重复子数组
     *
     * <p>给两个整数数组 A 和 B ，返回两个数组中公共的、长度最长的子数组的长度。
     *
     * <p>示例 1:
     *
     * <p>输入: A: [1,2,3,2,1] B: [3,2,1,4,7] 输出: 3 解释: 长度最长的公共子数组是 [3, 2, 1]。 说明:
     *
     * <p>1 <= len(A), len(B) <= 1000 0 <= A[i], B[i] < 100 通过次数14,541提交次数28,448
     *
     * @param A
     * @param B
     * @return
     */
    public int findLength(int[] A, int[] B) {
        int max = 0;
        int[][] dp = new int[A.length + 1][B.length + 1];

        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < B.length; j++) {
                if (A[i] != B[j]) {
                    continue;
                }
                dp[i + 1][j + 1] = dp[i][j] + 1;
                max = Math.max(max, dp[i + 1][j + 1]);
            }
        }
        return max;
    }

    @Test
    public void longestValidParentheses2() {
        String s = "((()))";
        logResult(longestValidParentheses2(s));
    }

    /**
     * 32. 最长有效括号
     *
     * <p>给定一个只包含 '(' 和 ')' 的字符串，找出最长的包含有效括号的子串的长度。
     *
     * <p>示例 1:
     *
     * <p>输入: "(()" 输出: 2 解释: 最长有效括号子串为 "()" 示例 2:
     *
     * <p>输入: ")()())" 输出: 4 解释: 最长有效括号子串为 "()()"
     *
     * @param s
     * @return
     */
    public int longestValidParentheses2(String s) {
        char[] chars = s.toCharArray();
        int max = 0;
        int[] dp = new int[chars.length];
        for (int i = 1; i < chars.length; i++) {
            char c = chars[i];
            if (chars[i] == ')') {
                if (chars[i - 1] == '(') {
                    dp[i] = (i > 2 ? dp[i - 2] : 0) + 2;
                } else {
                    int lastLeft = i - dp[i - 1] - 1;
                    if (lastLeft >= 0 && chars[lastLeft] == '(') {
                        dp[i] = dp[i - 1] + 2 + (lastLeft - 1 >= 0 ? dp[lastLeft - 1] : 0);
                    }
                }

                max = Math.max(dp[i], max);
            }
        }
        log.debug("dp:{}", dp);
        return max;
    }

    @Test
    public void minDistance3() {
        String word1 = "sea", word2 = "eat";

        logResult(minDistance3(word1, word2));
    }

    /**
     * 583. 两个字符串的删除操作
     *
     * <p>给定两个单词 word1 和 word2，找到使得 word1 和 word2 相同所需的最小步数，每步可以删除任意一个字符串中的一个字符。
     *
     * <p>示例：
     *
     * <p>输入: "sea", "eat" 输出: 2 解释: 第一步将"sea"变为"ea"，第二步将"eat"变为"ea"
     *
     * <p>提示：
     *
     * <p>给定单词的长度不超过500。 给定单词中的字符只含有小写字母。
     *
     * @param word1
     * @param word2
     * @return
     */
    public int minDistance3(String word1, String word2) {
        if (Objects.equals(word1, word2)) {
            return 0;
        }
        int len1 = word1.length(), len2 = word2.length();
        if (len1 * len2 == 0) {
            return len1 + len2;
        }
        int[][] dp = new int[len1 + 1][len2 + 1];
        for (int i = 0; i < len1; i++) {
            for (int j = 0; j < len2; j++) {
                if (word1.charAt(i) == word2.charAt(j)) {
                    dp[i + 1][j + 1] = dp[i][j] + 1;
                } else {
                    dp[i + 1][j + 1] = Math.max(dp[i + 1][j], dp[i][j + 1]);
                }
            }
        }
        logResult(dp);
        int max = dp[len1][len2];
        return len1 + len2 - 2 * max;
    }

    @Test
    public void numSubmat() {
        int[][] mat = {
            {1, 1, 1, 1, 0, 1, 0},
            {1, 1, 1, 0, 0, 0, 1},
            {0, 1, 1, 1, 1, 0, 0},
            {1, 1, 0, 1, 1, 0, 1},
            {1, 0, 0, 0, 0, 0, 1},
            {1, 1, 0, 1, 1, 1, 1},
            {1, 1, 0, 0, 1, 1, 1}
        };

        logResult(numSubmat(mat));
    }
    /**
     * 5454. 统计全 1 子矩形
     *
     * <p>给你一个只包含 0 和 1 的 rows * columns 矩阵 mat ，请你返回有多少个 子矩形 的元素全部都是 1 。
     *
     * <p>示例 1：
     *
     * <p>输入：mat = [[1,0,1], [1,1,0], [1,1,0]] 输出：13 解释： 有 6 个 1x1 的矩形。 有 2 个 1x2 的矩形。 有 3 个 2x1
     * 的矩形。 有 1 个 2x2 的矩形。 有 1 个 3x1 的矩形。 矩形数目总共 = 6 + 2 + 3 + 1 + 1 = 13 。 示例 2：
     *
     * <p>输入：mat = [[0,1,1,0], [0,1,1,1], [1,1,1,0]] 输出：24 解释： 有 8 个 1x1 的子矩形。 有 5 个 1x2 的子矩形。 有 2 个
     * 1x3 的子矩形。 有 4 个 2x1 的子矩形。 有 2 个 2x2 的子矩形。 有 2 个 3x1 的子矩形。 有 1 个 3x2 的子矩形。 矩形数目总共 = 8 + 5 + 2
     * + 4 + 2 + 2 + 1 = 24 。 示例 3：
     *
     * <p>输入：mat = [[1,1,1,1,1,1]] 输出：21 示例 4：
     *
     * <p>输入：mat = [[1,0,1],[0,1,0],[1,0,1]] 输出：5
     *
     * <p>提示：
     *
     * <p>1 <= rows <= 150 1 <= columns <= 150 0 <= mat[i][j] <= 1
     *
     * @param mat
     * @return
     */
    public int numSubmat(int[][] mat) {
        int rows = mat.length, cols = mat[0].length;
        int[][] dp = new int[rows + 1][cols + 1];
        // 长度
        int[][] side = new int[rows][cols];
        int result = 0;
        for (int i = 0; i < rows; i++) {
            int len = 0;
            for (int j = 0; j < cols; j++) {
                if (mat[i][j] == 1) {
                    len++;
                } else {
                    len = 0;
                }
                // 长度
                side[i][j] = len;
            }
        }
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int min = Integer.MAX_VALUE;
                for (int k = i; k >= 0; k--) {
                    if (side[k][j] == 0) {
                        break;
                    }
                    min = Math.min(min, side[k][j]);
                    result += min;
                }
            }
        }
        return result;
    }

    @Test
    public void findPaths() {
        int m = 8, n = 50, N = 23, i = 5, j = 26;
        logResult(findPaths(m, n, N, i, j));
    }

    /**
     * 576. 出界的路径数
     *
     * <p>给定一个 m × n 的网格和一个球。球的起始坐标为 (i,j) ，你可以将球移到相邻的单元格内，或者往上、下、左、右四个方向上移动使球穿过网格边界。但是，你最多可以移动 N
     * 次。找出可以将球移出边界的路径数量。答案可能非常大，返回 结果 mod 109 + 7 的值。
     *
     * <p>示例 1：
     *
     * <p>输入: m = 2, n = 2, N = 2, i = 0, j = 0 输出: 6 解释:
     *
     * <p>示例 2：
     *
     * <p>输入: m = 1, n = 3, N = 3, i = 0, j = 1 输出: 12 解释:
     *
     * <p>说明:
     *
     * <p>球一旦出界，就不能再被移动回网格内。 网格的长度和高度在 [1,50] 的范围内。 N 在 [0,50] 的范围内。
     *
     * @param m
     * @param n
     * @param N
     * @param i
     * @param j
     * @return
     */
    public int findPaths(int m, int n, int N, int i, int j) {
        if (N == 0) {
            return 0;
        }
        int MOD = 1_000_000_007;
        // 最外层加一圈
        int[][][] dp = new int[m + 2][n + 2][N + 1];

        for (int l = 0; l <= m + 1; l++) {
            dp[l][0][0] = 1;
            dp[l][n + 1][0] = 1;
        }
        for (int o = 0; o <= n + 1; o++) {
            dp[0][o][0] = 1;
            dp[m + 1][o][0] = 1;
        }
        for (int k = 1; k <= N; k++) {
            for (int l = 1; l <= m; l++) {
                for (int o = 1; o <= n; o++) {
                    dp[l][o][k] += dp[l - 1][o][k - 1];
                    dp[l][o][k] %= MOD;
                    dp[l][o][k] += dp[l + 1][o][k - 1];
                    dp[l][o][k] %= MOD;
                    dp[l][o][k] += dp[l][o - 1][k - 1];
                    dp[l][o][k] %= MOD;
                    dp[l][o][k] += dp[l][o + 1][k - 1];
                    dp[l][o][k] %= MOD;
                }
            }
        }
        int result = 0;
        for (int k = 1; k <= N; k++) {
            result = (result + dp[i + 1][j + 1][k]) % MOD;
        }
        return result;
    }

    @Test
    public void maximalRectangle() {
        char[][] matrix = {
            {'1', '0', '1', '0', '0'},
            {'1', '0', '1', '1', '1'},
            {'1', '1', '1', '1', '1'},
            {'1', '0', '0', '1', '0'}
        };
        logResult(maximalRectangle(matrix));
    }
    /**
     * 85. 最大矩形
     *
     * <p>给定一个仅包含 0 和 1 的二维二进制矩阵，找出只包含 1 的最大矩形，并返回其面积。
     *
     * <p>示例:
     *
     * <p>输入: [ ["1","0","1","0","0"], ["1","0","1","1","1"], ["1","1","1","1","1"],
     * ["1","0","0","1","0"] ] 输出: 6
     *
     * @param matrix
     * @return
     */
    public int maximalRectangle(char[][] matrix) {
        int rows = matrix.length;
        if (rows == 0) {
            return 0;
        }
        int cols = matrix[0].length;
        int[][] side = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            int len = 0;
            for (int j = 0; j < cols; j++) {
                if (matrix[i][j] == '1') {
                    len++;
                } else {
                    len = 0;
                }
                side[i][j] = len;
            }
        }
        int max = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int min = Integer.MAX_VALUE;
                for (int k = i; k >= 0; k--) {
                    if (side[k][j] == 0) {
                        break;
                    }
                    // 最短边长
                    min = Math.min(min, side[k][j]);
                    // 边长 * 高度
                    max = Math.max(min * (i - k + 1), max);
                }
            }
        }
        logResult(side);
        return max;
    }

    @Test
    public void findLongestChain() {
        int[][] pairs = {{3, 4}, {2, 3}, {1, 2}};
        logResult(findLongestChain(pairs));
    }

    /**
     * 646. 最长数对链
     *
     * <p>给出 n 个数对。 在每一个数对中，第一个数字总是比第二个数字小。
     *
     * <p>现在，我们定义一种跟随关系，当且仅当 b < c 时，数对(c, d) 才可以跟在 (a, b) 后面。我们用这种形式来构造一个数对链。
     *
     * <p>给定一个对数集合，找出能够形成的最长数对链的长度。你不需要用到所有的数对，你可以以任何顺序选择其中的一些数对来构造。
     *
     * <p>示例 :
     *
     * <p>输入: [[1,2], [2,3], [3,4]] 输出: 2 解释: 最长的数对链是 [1,2] -> [3,4] 注意：
     *
     * <p>给出数对的个数在 [1, 1000] 范围内。
     *
     * @param pairs
     * @return
     */
    public int findLongestChain(int[][] pairs) {
        int result = 0;
        int len = pairs.length;
        int[] dp = new int[pairs.length];
        Arrays.sort(pairs, (a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
        dp[0] = 1;
        for (int i = 1; i < len; i++) {
            int num = pairs[i][0];
            int index = i - 1;
            while (index >= 0 && pairs[index][1] >= num) {
                index--;
            }
            if (index >= 0) {
                dp[i] = dp[index] + 1;
            } else {
                dp[i] = 1;
            }
            result = Math.max(dp[i], result);
        }
        log.debug("dp:{}", dp);

        return result;
    }

    @Test
    public void countSubstrings() {
        String s = "aaaaa";
        logResult(countSubstrings(s));
    }

    int countSubstringsResult = 0;
    /**
     * 647. 回文子串
     *
     * <p>给定一个字符串，你的任务是计算这个字符串中有多少个回文子串。
     *
     * <p>具有不同开始位置或结束位置的子串，即使是由相同的字符组成，也会被计为是不同的子串。
     *
     * <p>示例 1:
     *
     * <p>输入: "abc" 输出: 3 解释: 三个回文子串: "a", "b", "c". 示例 2:
     *
     * <p>输入: "aaa" 输出: 6 说明: 6个回文子串: "a", "a", "a", "aa", "aa", "aaa". 注意:
     *
     * <p>输入的字符串长度不会超过1000。
     *
     * @param s
     * @return
     */
    public int countSubstrings(String s) {
        // dp[i][j] 表示 位置 i ~ j 是否回文子串
        // dp[i][j] = dp[i - 1][j - 1]
        int len = s.length();
        int result = 0;

        for (int i = 0; i < len; i++) {
            countSubstrings(s, i, i);
            countSubstrings(s, i, i + 1);
        }

        /*boolean[][] dp = new boolean[len][len];

        for (int i = 0; i < len; i++) {
            dp[i][i] = true;
            result++;
        }
        for (int i = len - 1; i >= 0; i--) {
            for (int j = i + 1; j < len; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    if (j - i == 1 || dp[i + 1][j - 1]) {
                        dp[i][j] = true;
                        result++;
                    }
                }
            }
        }*/

        return countSubstringsResult;
    }

    private void countSubstrings(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            countSubstringsResult++;
            left--;
            right++;
        }
    }

    /**
     * 309. 最佳买卖股票时机含冷冻期
     *
     * <p>给定一个整数数组，其中第 i 个元素代表了第 i 天的股票价格 。
     *
     * <p>设计一个算法计算出最大利润。在满足以下约束条件下，你可以尽可能地完成更多的交易（多次买卖一支股票）:
     *
     * <p>你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。 卖出股票后，你无法在第二天买入股票 (即冷冻期为 1 天)。 示例:
     *
     * <p>输入: [1,2,3,0,2] 输出: 3 解释: 对应的交易状态为: [买入, 卖出, 冷冻期, 买入, 卖出]
     *
     * @param prices
     * @return
     */
    public int maxProfit4(int[] prices) {
        int len = prices.length;
        if (len == 0) {
            return 0;
        }
        // dp 表示第i天的利润 0 表示卖出, 1 表示买入
        int[][] dp = new int[len][2];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        for (int i = 1; i < len; i++) {
            // 第i天的利润（卖出） = 第i-1天的利润（卖出） / 第i-1天的利润（买入） + i天的价格
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            // 第i天的利润（买入） = 第i-1天的利润（买入）  第i-2天的利润（卖出） - i天的价格
            dp[i][1] = Math.max(dp[i - 1][1], i - 2 >= 0 ? dp[i - 2][0] - prices[i] : -prices[i]);
        }

        return Math.max(dp[len - 1][0], dp[len - 1][1]);
    }

    /**
     * 174. 地下城游戏
     *
     * <p>一些恶魔抓住了公主（P）并将她关在了地下城的右下角。地下城是由 M x N
     * 个房间组成的二维网格。我们英勇的骑士（K）最初被安置在左上角的房间里，他必须穿过地下城并通过对抗恶魔来拯救公主。
     *
     * <p>骑士的初始健康点数为一个正整数。如果他的健康点数在某一时刻降至 0 或以下，他会立即死亡。
     *
     * <p>有些房间由恶魔守卫，因此骑士在进入这些房间时会失去健康点数（若房间里的值为负整数，则表示骑士将损失健康点数）；其他房间要么是空的（房间里的值为
     * 0），要么包含增加骑士健康点数的魔法球（若房间里的值为正整数，则表示骑士将增加健康点数）。
     *
     * <p>为了尽快到达公主，骑士决定每次只向右或向下移动一步。
     *
     * <p>编写一个函数来计算确保骑士能够拯救到公主所需的最低初始健康点数。
     *
     * <p>例如，考虑到如下布局的地下城，如果骑士遵循最佳路径 右 -> 右 -> 下 -> 下，则骑士的初始健康点数至少为 7。
     *
     * <p>-2 (K) -3 3 -5 -10 1 10 30 -5 (P)
     *
     * <p>说明:
     *
     * <p>骑士的健康点数没有上限。
     *
     * <p>任何房间都可能对骑士的健康点数造成威胁，也可能增加骑士的健康点数，包括骑士进入的左上角房间以及公主被监禁的右下角房间。
     *
     * @param dungeon
     * @return
     */
    public int calculateMinimumHP(int[][] dungeon) {
        int rows = dungeon.length, cols = dungeon[0].length;
        int[][] dp = new int[rows + 1][cols + 1];
        for (int i = 0; i <= rows; ++i) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        dp[rows][cols - 1] = dp[rows - 1][cols] = 1;

        for (int i = rows - 1; i >= 0; i--) {
            for (int j = cols - 1; j >= 0; j--) {
                int min = Math.min(dp[i + 1][j], dp[i][j + 1]);
                dp[i][j] = Math.max(min - dungeon[i][j], 1);
            }
        }

        return dp[0][0];
    }

    @Test
    public void findNumberOfLIS() {
        int[] nums = {1, 3, 5, 4, 7};
        logResult(findNumberOfLIS(nums));
    }
    /**
     * 673. 最长递增子序列的个数
     *
     * <p>给定一个未排序的整数数组，找到最长递增子序列的个数。
     *
     * <p>示例 1:
     *
     * <p>输入: [1,3,5,4,7] 输出: 2 解释: 有两个最长递增子序列，分别是 [1, 3, 4, 7] 和[1, 3, 5, 7]。 示例 2:
     *
     * <p>输入: [2,2,2,2,2] 输出: 5 解释: 最长递增子序列的长度是1，并且存在5个子序列的长度为1，因此输出5。 注意: 给定的数组长度不超过 2000
     * 并且结果一定是32位有符号整数。
     *
     * @param nums
     * @return
     */
    public int findNumberOfLIS(int[] nums) {
        int count = 0;
        int maxLen = 0;
        int[] lens = new int[nums.length];
        int[] counts = new int[nums.length];
        Arrays.fill(counts, 1);
        // lens[0] = 1;
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            for (int j = 0; j < i; j++) {
                if (num <= nums[j]) {
                    continue;
                }
                // lens[i] = Math.max(lens[j] + 1, lens[i]);
                if (lens[j] >= lens[i]) {
                    lens[i] = lens[j] + 1;
                    counts[i] = counts[j];
                } else if (lens[j] + 1 == lens[i]) {
                    counts[i] += counts[j];
                }
            }
            maxLen = Math.max(maxLen, lens[i]);
        }
        log.debug("lens:{}", lens);
        log.debug("counts:{}", counts);
        for (int i = 0; i < lens.length; i++) {
            if (lens[i] == maxLen) {
                count += counts[i];
            }
        }
        return count;
    }

    /**
     * 97. 交错字符串
     *
     * <p>给定三个字符串 s1, s2, s3, 验证 s3 是否是由 s1 和 s2 交错组成的。
     *
     * <p>示例 1:
     *
     * <p>输入: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac" 输出: true 示例 2:
     *
     * <p>输入: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc" 输出: false
     *
     * @param s1
     * @param s2
     * @param s3
     * @return
     */
    public boolean isInterleave(String s1, String s2, String s3) {
        int len1 = s1.length(), len2 = s2.length();
        if (len1 + len2 != s3.length()) {
            return false;
        }

        boolean[][] dp = new boolean[len1 + 1][len2 + 1];
        dp[0][0] = true;
        for (int i = 0; i <= len1; i++) {
            for (int j = 0; j <= len2; j++) {
                if (i == 0 && j == 0) {

                } else if (i == 0) {
                    dp[i][j] = dp[i][j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1);
                } else if (j == 0) {
                    dp[i][j] = dp[i - 1][j] && s1.charAt(i - 1) == s3.charAt(i + j - 1);
                } else {
                    dp[i][j] =
                            (dp[i][j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1))
                                    || (dp[i - 1][j] && s1.charAt(i - 1) == s3.charAt(i + j - 1));
                }
            }
        }

        return dp[len1][len2];
    }

    @Test
    public void maxProfit5() {
        int[] prices = {1, 3, 2, 8, 4, 9};
        int fee = 2;
        logResult(maxProfit5(prices, fee));
    }
    /**
     * 714. 买卖股票的最佳时机含手续费
     *
     * <p>给定一个整数数组 prices，其中第 i 个元素代表了第 i 天的股票价格 ；非负整数 fee 代表了交易股票的手续费用。
     *
     * <p>你可以无限次地完成交易，但是你每笔交易都需要付手续费。如果你已经购买了一个股票，在卖出它之前你就不能再继续购买股票了。
     *
     * <p>返回获得利润的最大值。
     *
     * <p>注意：这里的一笔交易指买入持有并卖出股票的整个过程，每笔交易你只需要为支付一次手续费。
     *
     * <p>示例 1:
     *
     * <p>输入: prices = [1, 3, 2, 8, 4, 9], fee = 2 输出: 8 解释: 能够达到的最大利润: 在此处买入 prices[0] = 1 在此处卖出
     * prices[3] = 8 在此处买入 prices[4] = 4 在此处卖出 prices[5] = 9 总利润: ((8 - 1) - 2) + ((9 - 4) - 2) = 8.
     * 注意:
     *
     * <p>0 < prices.length <= 50000. 0 < prices[i] < 50000. 0 <= fee < 50000.
     *
     * @param prices
     * @param fee
     * @return
     */
    public int maxProfit5(int[] prices, int fee) {
        int len = prices.length;
        if (len == 0) {
            return 0;
        }
        // dp 表示第i天的利润 0 表示卖出, 1 表示买入
        int[][] dp = new int[len][2];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        for (int i = 1; i < len; i++) {
            // 第i天的利润（卖出） = 第i-1天的利润（卖出） | 第i-1天的利润（买入） + i天的价格 - 手续费
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i] - fee);
            // 第i天的利润（买入） = 第i-1天的利润（买入） | 第i-1天的利润（卖出） - i天的价格
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
        }

        return Math.max(dp[len - 1][0], dp[len - 1][1]);
    }

    @Test
    public void deleteAndEarn() {
        int[] nums = {2, 2, 3, 3, 3, 4};
        logResult(deleteAndEarn(nums));
    }

    /**
     * 740. 删除与获得点数
     *
     * <p>给定一个整数数组 nums ，你可以对它进行一些操作。
     *
     * <p>每次操作中，选择任意一个 nums[i] ，删除它并获得 nums[i] 的点数。之后，你必须删除每个等于 nums[i] - 1 或 nums[i] + 1 的元素。
     *
     * <p>开始你拥有 0 个点数。返回你能通过这些操作获得的最大点数。
     *
     * <p>示例 1:
     *
     * <p>输入: nums = [3, 4, 2] 输出: 6 解释: 删除 4 来获得 4 个点数，因此 3 也被删除。 之后，删除 2 来获得 2 个点数。总共获得 6 个点数。 示例
     * 2:
     *
     * <p>输入: nums = [2, 2, 3, 3, 3, 4] 输出: 9 解释: 删除 3 来获得 3 个点数，接着要删除两个 2 和 4 。 之后，再次删除 3 获得 3
     * 个点数，再次删除 3 获得 3 个点数。 总共获得 9 个点数。 注意:
     *
     * <p>nums的长度最大为20000。 每个整数nums[i]的大小都在[1, 10000]范围内。
     *
     * @param nums
     * @return
     */
    public int deleteAndEarn(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        int max = 0;
        for (int num : nums) {
            max = Math.max(max, num);
        }
        int[] counts = new int[max + 1];

        for (int num : nums) {
            counts[num]++;
        }
        int[] dp = new int[max + 1];
        dp[1] = counts[1];
        int result = 0;
        for (int i = 2; i <= max; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + i * counts[i]);
            result = Math.max(result, dp[i]);
        }

        return result;
    }

    @Test
    public void minimumDeleteSum() {
        String s1 = "delete", s2 = "";
        logResult(minimumDeleteSum(s1, s2));
    }

    /**
     * 712. 两个字符串的最小ASCII删除和
     *
     * <p>给定两个字符串s1, s2，找到使两个字符串相等所需删除字符的ASCII值的最小和。
     *
     * <p>示例 1:
     *
     * <p>输入: s1 = "sea", s2 = "eat" 输出: 231 解释: 在 "sea" 中删除 "s" 并将 "s" 的值(115)加入总和。 在 "eat" 中删除 "t"
     * 并将 116 加入总和。 结束时，两个字符串相等，115 + 116 = 231 就是符合条件的最小和。 示例 2:
     *
     * <p>输入: s1 = "delete", s2 = "leet" 输出: 403 解释: 在 "delete" 中删除 "dee" 字符串变成 "let"， 将
     * 100[d]+101[e]+101[e] 加入总和。在 "leet" 中删除 "e" 将 101[e] 加入总和。 结束时，两个字符串都等于 "let"，结果即为
     * 100+101+101+101 = 403 。 如果改为将两个字符串转换为 "lee" 或 "eet"，我们会得到 433 或 417 的结果，比答案更大。 注意:
     *
     * <p>0 < s1.length, s2.length <= 1000。 所有字符串中的字符ASCII值在[97, 122]之间。
     *
     * @param s1
     * @param s2
     * @return
     */
    public int minimumDeleteSum(String s1, String s2) {
        int len1 = s1.length(), len2 = s2.length();
        int[][] dp = new int[len1 + 1][len2 + 1];
        // 状态：使用 dp[i][j]表示s1前i个字符和s2前j个字符的最小和结果
        // 转移：如果当前字符相等 dp[i][j] = dp[i-1][j-1]
        // dp[i][j] =
        // min(dp[i-1][j]+ascii(s1[i]),dp[i][j-1]+ascii(s2[j])) 表示删除，加上ascii的值更新
        //
        // 注意边界，对于空串就是将其所有ascii值相加

        for (int i = 0; i < len1; i++) {
            dp[i + 1][0] = dp[i][0] + s1.codePointAt(i);
        }
        for (int j = 0; j < len2; j++) {
            dp[0][j + 1] = dp[0][j] + s2.codePointAt(j);
        }

        for (int i = 0; i < len1; i++) {
            for (int j = 0; j < len2; j++) {
                char c1 = s1.charAt(i), c2 = s2.charAt(j);
                if (c1 == c2) {
                    dp[i + 1][j + 1] = dp[i][j];
                } else {
                    dp[i + 1][j + 1] = Math.min(dp[i][j + 1] + c1, dp[i + 1][j] + c2);
                }
            }
        }
        logResult(dp);
        return dp[len1][len2];
    }

    @Test
    public void orderOfLargestPlusSign() {
        int N = 1;
        int[][] mines = {{0, 0}};
        logResult(orderOfLargestPlusSign(N, mines));
    }

    /**
     * 764. 最大加号标志
     *
     * <p>在一个大小在 (0, 0) 到 (N-1, N-1) 的2D网格 grid 中，除了在 mines 中给出的单元为 0，其他每个单元都是 1。网格中包含 1
     * 的最大的轴对齐加号标志是多少阶？返回加号标志的阶数。如果未找到加号标志，则返回 0。
     *
     * <p>一个 k" 阶由 1 组成的“轴对称”加号标志具有中心网格 grid[x][y] = 1 ，以及4个从中心向上、向下、向左、向右延伸，长度为 k-1，由 1 组成的臂。下面给出
     * k" 阶“轴对称”加号标志的示例。注意，只有加号标志的所有网格要求为 1，别的网格可能为 0 也可能为 1。
     *
     * <p>k 阶轴对称加号标志示例:
     *
     * <p>阶 1: 000 010 000
     *
     * <p>阶 2: 00000 00100 01110 00100 00000
     *
     * <p>阶 3: 0000000 0001000 0001000 0111110 0001000 0001000 0000000
     *
     * <p>示例 1：
     *
     * <p>输入: N = 5, mines = [[4, 2]] 输出: 2 解释:
     *
     * <p>11111 11111 11111 11111 11011
     *
     * <p>在上面的网格中，最大加号标志的阶只能是2。一个标志已在图中标出。
     *
     * <p>示例 2：
     *
     * <p>输入: N = 2, mines = [] 输出: 1 解释:
     *
     * <p>11 11
     *
     * <p>没有 2 阶加号标志，有 1 阶加号标志。
     *
     * <p>示例 3：
     *
     * <p>输入: N = 1, mines = [[0, 0]] 输出: 0 解释:
     *
     * <p>0
     *
     * <p>没有加号标志，返回 0 。
     *
     * <p>提示：
     *
     * <p>整数N 的范围： [1, 500]. mines 的最大长度为 5000. mines[i] 是长度为2的由2个 [0, N-1] 中的数组成. (另外,使用 C, C++, 或者
     * C# 编程将以稍小的时间限制进行​​判断.)
     *
     * @param N
     * @param mines
     * @return
     */
    public int orderOfLargestPlusSign(int N, int[][] mines) {

        /*Set<Integer> banned = new HashSet();
        int[][] dp = new int[N][N];

        for (int[] mine : mines) {
            banned.add(mine[0] * N + mine[1]);
        }
        int ans = 0, count;

        for (int r = 0; r < N; ++r) {
            count = 0;
            for (int c = 0; c < N; ++c) {
                count = banned.contains(r * N + c) ? 0 : count + 1;
                dp[r][c] = count;
            }

            count = 0;
            for (int c = N - 1; c >= 0; --c) {
                count = banned.contains(r * N + c) ? 0 : count + 1;
                dp[r][c] = Math.min(dp[r][c], count);
            }
        }

        for (int c = 0; c < N; ++c) {
            count = 0;
            for (int r = 0; r < N; ++r) {
                count = banned.contains(r * N + c) ? 0 : count + 1;
                dp[r][c] = Math.min(dp[r][c], count);
            }

            count = 0;
            for (int r = N - 1; r >= 0; --r) {
                count = banned.contains(r * N + c) ? 0 : count + 1;
                dp[r][c] = Math.min(dp[r][c], count);
                ans = Math.max(ans, dp[r][c]);
            }
        }

        return ans;*/

        if (N == 0) {
            return 0;
        }

        int[][] dp = new int[N][N];

        int matrix[][] = new int[N][N];
        for (int i = 0; i < N; i++) {
            Arrays.fill(matrix[i], 1);
        }

        for (int i = 0; i < mines.length; i++) {
            matrix[mines[i][0]][mines[i][1]] = 0;
        }
        int count = 0;
        // 求左、右
        for (int i = 0; i < N; i++) {

            // 求左臂并将值放入dp left->right
            count = 0;
            for (int j = 0; j < N; j++) {
                count = matrix[i][j] == 1 ? count + 1 : 0;
                dp[i][j] = count;
            }

            // 求右臂 并将当前dp与右臂之间的最小值放入dp right->left
            count = 0;
            for (int j = N - 1; j >= 0; j--) {
                count = matrix[i][j] == 1 ? count + 1 : 0;
                dp[i][j] = Math.min(dp[i][j], count);
            }
        }
        int max = 0;
        // 求上、下
        for (int j = 0; j < N; j++) {
            // 求上臂 并将当前dp与上臂间的最小值放入dp up->down
            count = 0;
            for (int i = 0; i < N; i++) {
                count = matrix[i][j] == 1 ? count + 1 : 0;
                dp[i][j] = Math.min(dp[i][j], count);
            }
            // 求下臂 并将当前dp与下臂间的最小值放入dp down->up
            // 此时的整个dp数组中的最大值就是所求的k
            count = 0;
            for (int i = N - 1; i >= 0; i--) {
                count = matrix[i][j] == 1 ? count + 1 : 0;
                dp[i][j] = Math.min(dp[i][j], count);
                max = Math.max(max, dp[i][j]);
            }
        }

        return max;
    }

    @Test
    public void knightProbability() {
        int N = 3, K = 2, r = 0, c = 0;
        logResult(knightProbability(N, K, r, c));
    }

    /**
     * 688. “马”在棋盘上的概率
     *
     * <p>已知一个 NxN 的国际象棋棋盘，棋盘的行号和列号都是从 0 开始。即最左上角的格子记为 (0, 0)，最右下角的记为 (N-1, N-1)。
     *
     * <p>现有一个 “马”（也译作 “骑士”）位于 (r, c) ，并打算进行 K 次移动。
     *
     * <p>如下图所示，国际象棋的 “马” 每一步先沿水平或垂直方向移动 2 个格子，然后向与之相垂直的方向再移动 1 个格子，共有 8 个可选的位置。
     *
     * <p>现在 “马” 每一步都从可选的位置（包括棋盘外部的）中独立随机地选择一个进行移动，直到移动了 K 次或跳到了棋盘外面。
     *
     * <p>求移动结束后，“马” 仍留在棋盘上的概率。
     *
     * <p>示例：
     *
     * <p>输入: 3, 2, 0, 0 输出: 0.0625 解释: 输入的数据依次为 N, K, r, c 第 1 步时，有且只有 2 种走法令 “马”
     * 可以留在棋盘上（跳到（1,2）或（2,1））。对于以上的两种情况，各自在第2步均有且只有2种走法令 “马” 仍然留在棋盘上。 所以 “马” 在结束后仍在棋盘上的概率为 0.0625。
     *
     * <p>注意：
     *
     * <p>N 的取值范围为 [1, 25] K 的取值范围为 [0, 100] 开始时，“马” 总是位于棋盘上
     *
     * @param N
     * @param K
     * @param r
     * @param c
     * @return
     */
    public double knightProbability(int N, int K, int r, int c) {
        double[][][] dp = new double[N][N][K + 1];
        int[] dr = {2, 2, 1, 1, -1, -1, -2, -2};
        int[] dc = {1, -1, 2, -2, 2, -2, 1, -1};
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                dp[i][j][0] = 1.0;
            }
        }
        for (int k = 1; k <= K; k++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    for (int l = 0; l < 8; l++) {
                        int row = i + dr[l];
                        int col = j + dc[l];
                        if (row >= 0 && row < N && col >= 0 && col < N) {
                            dp[row][col][k] += dp[i][j][k - 1] / 8.0;
                        }
                    }
                }
            }
        }

        return dp[r][c][K];
    }

    /**
     * 1105. 填充书架
     *
     * <p>附近的家居城促销，你买回了一直心仪的可调节书架，打算把自己的书都整理到新的书架上。
     *
     * <p>你把要摆放的书 books 都整理好，叠成一摞：从上往下，第 i 本书的厚度为 books[i][0]，高度为 books[i][1]。
     *
     * <p>按顺序 将这些书摆放到总宽度为 shelf_width 的书架上。
     *
     * <p>先选几本书放在书架上（它们的厚度之和小于等于书架的宽度 shelf_width），然后再建一层书架。重复这个过程，直到把所有的书都放在书架上。
     *
     * <p>需要注意的是，在上述过程的每个步骤中，摆放书的顺序与你整理好的顺序相同。 例如，如果这里有 5
     * 本书，那么可能的一种摆放情况是：第一和第二本书放在第一层书架上，第三本书放在第二层书架上，第四和第五本书放在最后一层书架上。
     *
     * <p>每一层所摆放的书的最大高度就是这一层书架的层高，书架整体的高度为各层高之和。
     *
     * <p>以这种方式布置书架，返回书架整体可能的最小高度。
     *
     * <p>示例：
     *
     * <p>输入：books = [[1,1],[2,3],[2,3],[1,1],[1,1],[1,1],[1,2]], shelf_width = 4 输出：6 解释： 3
     * 层书架的高度和为 1 + 3 + 2 = 6 。 第 2 本书不必放在第一层书架上。
     *
     * <p>提示：
     *
     * <p>1 <= books.length <= 1000 1 <= books[i][0] <= shelf_width <= 1000 1 <= books[i][1] <= 1000
     *
     * @param books
     * @param shelf_width
     * @return
     */
    public int minHeightShelves(int[][] books, int shelf_width) {
        int len = books.length;
        int[] dp = new int[len + 1];
        Arrays.fill(dp, Integer.MAX_VALUE >> 1);
        dp[0] = 0;
        for (int i = 1; i <= len; i++) {
            int width = 0, height = 0;
            for (int j = i; j > 0; j--) {
                width += books[j - 1][0];
                if (width > shelf_width) {
                    break;
                }
                height = Math.max(height, books[j - 1][1]);
                dp[i] = Math.min(dp[i], dp[j - 1] + height);
            }
        }

        return dp[len];
    }

    @Test
    public void largest1BorderedSquare() {
        int[][] grid = {
            {0, 1, 1, 1}, {1, 1, 1, 1}, {1, 0, 0, 1}, {1, 1, 1, 1}, {1, 0, 1, 1}, {1, 1, 0, 1}
        };
        logResult(largest1BorderedSquare(grid));
    }

    /**
     * 1139. 最大的以 1 为边界的正方形
     *
     * <p>给你一个由若干 0 和 1 组成的二维网格 grid，请你找出边界全部由 1 组成的最大 正方形 子网格，并返回该子网格中的元素数量。如果不存在，则返回 0。
     *
     * <p>示例 1：
     *
     * <p>输入：grid = [[1,1,1],[1,0,1],[1,1,1]] 输出：9 示例 2：
     *
     * <p>输入：grid = [[1,1,0,0]] 输出：1
     *
     * <p>提示：
     *
     * <p>1 <= grid.length <= 100 1 <= grid[0].length <= 100 grid[i][j] 为 0 或 1
     *
     * @param grid
     * @return
     */
    public int largest1BorderedSquare(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        int[][][] dp = new int[rows][cols][2];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 0) {
                    continue;
                }
                dp[i][j][0] = (i > 0 ? dp[i - 1][j][0] : 0) + 1;
                dp[i][j][1] = (j > 0 ? dp[i][j - 1][1] : 0) + 1;
            }
        }
        logResult(dp);
        int maxSide = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int side = Math.min(dp[i][j][0], dp[i][j][1]);
                for (int k = side; k >= 1; k--) {
                    if (dp[i][j - k + 1][0] >= k && dp[i - k + 1][j][1] >= k) {
                        maxSide = Math.max(maxSide, k);
                        break; // 更短的就没必要考虑了
                    }
                }
            }
        }
        return maxSide * maxSide;
    }

    /**
     * 1140. 石子游戏 II
     *
     * <p>亚历克斯和李继续他们的石子游戏。许多堆石子 排成一行，每堆都有正整数颗石子 piles[i]。游戏以谁手中的石子最多来决出胜负。
     *
     * <p>亚历克斯和李轮流进行，亚历克斯先开始。最初，M = 1。
     *
     * <p>在每个玩家的回合中，该玩家可以拿走剩下的 前 X 堆的所有石子，其中 1 <= X <= 2M。然后，令 M = max(M, X)。
     *
     * <p>游戏一直持续到所有石子都被拿走。
     *
     * <p>假设亚历克斯和李都发挥出最佳水平，返回亚历克斯可以得到的最大数量的石头。
     *
     * <p>示例：
     *
     * <p>输入：piles = [2,7,9,4,4] 输出：10 解释： 如果亚历克斯在开始时拿走一堆石子，李拿走两堆，接着亚历克斯也拿走两堆。在这种情况下，亚历克斯可以拿到 2 + 4
     * + 4 = 10 颗石子。 如果亚历克斯在开始时拿走两堆石子，那么李就可以拿走剩下全部三堆石子。在这种情况下，亚历克斯可以拿到 2 + 7 = 9 颗石子。 所以我们返回更大的 10。
     *
     * <p>提示：
     *
     * <p>1 <= piles.length <= 100 1 <= piles[i] <= 10 ^ 4
     *
     * @param piles
     * @return
     */
    public int stoneGameII(int[] piles) {
        int len = piles.length;
        int[][] dp = new int[len][len + 1];

        int sum = 0;

        // dp[i, M] 表示，当从第 i 堆石子开始拿，允许拿 M <= x <= 2 * M 时，在剩余石子中所能拿到的最大值
        for (int i = len - 1; i >= 0; i--) {
            sum += piles[i];
            for (int M = 0; M <= len; M++) {
                if (i + 2 * M >= len) {
                    dp[i][M] = sum;
                } else {
                    for (int j = 1; j <= 2 * M; j++) {
                        dp[i][M] = Math.max(dp[i][M], sum - dp[i + j][Math.max(M, j)]);
                    }
                }
            }
        }

        return dp[0][1];
    }

    @Test
    public void longestCommonSubsequence() {
        String text1 = "abc", text2 = "def";
        logResult(longestCommonSubsequence(text1, text2));
    }

    /**
     * 1143. 最长公共子序列
     *
     * <p>给定两个字符串 text1 和 text2，返回这两个字符串的最长公共子序列的长度。
     *
     * <p>一个字符串的 子序列 是指这样一个新的字符串：它是由原字符串在不改变字符的相对顺序的情况下删除某些字符（也可以不删除任何字符）后组成的新字符串。 例如，"ace" 是
     * "abcde" 的子序列，但 "aec" 不是 "abcde" 的子序列。两个字符串的「公共子序列」是这两个字符串所共同拥有的子序列。
     *
     * <p>若这两个字符串没有公共子序列，则返回 0。
     *
     * <p>示例 1:
     *
     * <p>输入：text1 = "abcde", text2 = "ace" 输出：3 解释：最长公共子序列是 "ace"，它的长度为 3。 示例 2:
     *
     * <p>输入：text1 = "abc", text2 = "abc" 输出：3 解释：最长公共子序列是 "abc"，它的长度为 3。 示例 3:
     *
     * <p>输入：text1 = "abc", text2 = "def" 输出：0 解释：两个字符串没有公共子序列，返回 0。
     *
     * <p>提示:
     *
     * <p>1 <= text1.length <= 1000 1 <= text2.length <= 1000 输入的字符串只含有小写英文字符。
     *
     * @param text1
     * @param text2
     * @return
     */
    public int longestCommonSubsequence(String text1, String text2) {
        int len1 = text1.length(), len2 = text2.length();
        int[][] dp = new int[len1 + 1][len2 + 1];
        for (int i = 0; i < len1; i++) {
            for (int j = 0; j < len2; j++) {
                char c1 = text1.charAt(i), c2 = text2.charAt(j);
                if (c1 == c2) {
                    dp[i + 1][j + 1] = dp[i][j] + 1;
                } else {
                    dp[i + 1][j + 1] = Math.max(dp[i + 1][j], dp[i][j + 1]);
                }
            }
        }

        return dp[len1][len2];
    }

    /**
     * 1155. 掷骰子的N种方法
     *
     * <p>这里有 d 个一样的骰子，每个骰子上都有 f 个面，分别标号为 1, 2, ..., f。
     *
     * <p>我们约定：掷骰子的得到总点数为各骰子面朝上的数字的总和。
     *
     * <p>如果需要掷出的总点数为 target，请你计算出有多少种不同的组合情况（所有的组合情况总共有 f^d 种），模 10^9 + 7 后返回。
     *
     * <p>示例 1：
     *
     * <p>输入：d = 1, f = 6, target = 3 输出：1 示例 2：
     *
     * <p>输入：d = 2, f = 6, target = 7 输出：6 示例 3：
     *
     * <p>输入：d = 2, f = 5, target = 10 输出：1 示例 4：
     *
     * <p>输入：d = 1, f = 2, target = 3 输出：0 示例 5：
     *
     * <p>输入：d = 30, f = 30, target = 500 输出：222616187
     *
     * <p>提示：
     *
     * <p>1 <= d, f <= 30 1 <= target <= 1000
     *
     * @param d
     * @param f
     * @param target
     * @return
     */
    public int numRollsToTarget(int d, int f, int target) {
        int MOD = 1000000007;
        if (target > f * d) {
            return 0;
        }
        int[][] dp = new int[d + 1][target + 1];
        dp[0][0] = 1;
        for (int count = 1; count <= d; count++) {
            for (int num = count; num <= target && num <= count * f; num++) {

                for (int i = 1; i <= f && num - i >= 0; i++) {
                    dp[count][num] = (dp[count][num] + dp[count - 1][num - i]) % MOD;
                }
            }
        }

        return dp[f][target];
    }

    @Test
    public void minDays() {
        // int n = 466;
        int n = 61455274;
        logResult(minDays(n));
    }

    private Map<Integer, Integer> minDayMap = new HashMap<>();
    /**
     * 5490. 吃掉 N 个橘子的最少天数
     *
     * <p>厨房里总共有 n 个橘子，你决定每一天选择如下方式之一吃这些橘子：
     *
     * <p>吃掉一个橘子。 如果剩余橘子数 n 能被 2 整除，那么你可以吃掉 n/2 个橘子。 如果剩余橘子数 n 能被 3 整除，那么你可以吃掉 2*(n/3) 个橘子。 每天你只能从以上
     * 3 种方案中选择一种方案。
     *
     * <p>请你返回吃掉所有 n 个橘子的最少天数。
     *
     * <p>示例 1：
     *
     * <p>输入：n = 10 输出：4 解释：你总共有 10 个橘子。 第 1 天：吃 1 个橘子，剩余橘子数 10 - 1 = 9。 第 2 天：吃 6 个橘子，剩余橘子数 9 -
     * 2*(9/3) = 9 - 6 = 3。（9 可以被 3 整除） 第 3 天：吃 2 个橘子，剩余橘子数 3 - 2*(3/3) = 3 - 2 = 1。 第 4 天：吃掉最后 1
     * 个橘子，剩余橘子数 1 - 1 = 0。 你需要至少 4 天吃掉 10 个橘子。 示例 2：
     *
     * <p>输入：n = 6 输出：3 解释：你总共有 6 个橘子。 第 1 天：吃 3 个橘子，剩余橘子数 6 - 6/2 = 6 - 3 = 3。（6 可以被 2 整除） 第 2 天：吃
     * 2 个橘子，剩余橘子数 3 - 2*(3/3) = 3 - 2 = 1。（3 可以被 3 整除） 第 3 天：吃掉剩余 1 个橘子，剩余橘子数 1 - 1 = 0。 你至少需要 3
     * 天吃掉 6 个橘子。 示例 3：
     *
     * <p>输入：n = 1 输出：1 示例 4：
     *
     * <p>输入：n = 56 输出：6 466 11
     *
     * <p>提示：
     *
     * <p>1 <= n <= 2*10^9
     *
     * @param n
     * @return
     */
    public int minDays(int n) {

        if (n == 1) {
            return 1;
        }

        if (n == 2 || n == 3) {
            return 2;
        }

        if (minDayMap.containsKey(n)) {
            return minDayMap.get(n);
        }
        /*int num = 0;
        if (n % 2 == 0) {
            num = n / 2;
            result = Math.min(result, 1 + minDays(n - num));
        }
        if (n % 3 == 0) {
            num = 2 * (n / 3);
            result = Math.min(result, 1 + minDays(n - num));
        }
        if ((n - 1) % 3 == 0 && n > 1) {
            result = Math.min(result, 1 + minDays(n - 1));
        } else if ((n - 2) % 3 == 0 && n > 2) {
            result = Math.min(result, 2 + minDays(n - 2));
        } else if ((n - 1) % 2 == 0 && n > 1) {
            result = Math.min(result, 1 + minDays(n - 1));
        }*/

        int m2 = minDays(n / 2) + n % 2;
        int m3 = minDays(n / 3) + n % 3;
        int result = Math.min(m2, m3) + 1;
        minDayMap.put(n, result);

        return result;
    }

    @Test
    public void kConcatenationMaxSum() {
        int[] arr = {-1, -2};
        int k = 7;
        logResult(kConcatenationMaxSum(arr, k));
    }
    /**
     * 1191. K 次串联后最大子数组之和
     *
     * <p>给你一个整数数组 arr 和一个整数 k。
     *
     * <p>首先，我们要对该数组进行修改，即把原数组 arr 重复 k 次。
     *
     * <p>举个例子，如果 arr = [1, 2] 且 k = 3，那么修改后的数组就是 [1, 2, 1, 2, 1, 2]。
     *
     * <p>然后，请你返回修改后的数组中的最大的子数组之和。
     *
     * <p>注意，子数组长度可以是 0，在这种情况下它的总和也是 0。
     *
     * <p>由于 结果可能会很大，所以需要 模（mod） 10^9 + 7 后再返回。
     *
     * <p>示例 1：
     *
     * <p>输入：arr = [1,2], k = 3 输出：9 示例 2：
     *
     * <p>输入：arr = [1,-2,1], k = 5 输出：2 示例 3：
     *
     * <p>输入：arr = [-1,-2], k = 7 输出：0
     *
     * <p>提示：
     *
     * <p>1 <= arr.length <= 10^5 1 <= k <= 10^5 -10^4 <= arr[i] <= 10^4
     *
     * @param arr
     * @param k
     * @return
     */
    public int kConcatenationMaxSum(int[] arr, int k) {
        int MOD = 1000000007;
        // 解题分两种情况：
        //
        // k = 1: max = 最大子串
        // k > 1:
        // 如果数组和>0：max = 最大后子串 + (k - 2)*数组和 + 最大前子串
        // 如果数组和<=0：max = 最大后子串 + 最大前子串
        // 最后max与最大子串做对比，取最大值

        if (Objects.isNull(arr) || arr.length == 0) {
            return 0;
        }
        int len = arr.length;
        if (len == 1) {
            if (arr[0] > 0) {
                return arr[0] * k;
            } else {
                return 0;
            }
        }

        int prefix = arr[0], suffix = arr[len - 1], sum = arr[0];
        int maxprefix = prefix, maxsuffix = suffix;
        for (int i = 1; i < len; i++) {
            prefix += arr[i];
            maxprefix = Math.max(maxprefix, prefix);
            sum += arr[i];
            suffix += arr[len - i - 1];
            maxsuffix = Math.max(maxsuffix, suffix);
        }
        int sum2 = 0, maxn = 0;
        for (int i = 0; i < len; i++) {
            if (sum2 <= 0) {
                sum2 = arr[i];
            } else {
                sum2 += arr[i];
            }
            maxn = Math.max(maxn, sum2);
        }

        if (k == 1) {
            return maxn;
        }

        int result = maxsuffix + maxprefix;
        while (sum > 0 && --k >= 2) {
            result = (result + sum) % MOD;
        }
        return Math.max(result, maxn);
    }

    @Test
    public void longestSubsequence() {
        int[] arr = {3, 0, -3, 4, -4, 7, 6};
        int difference = 3;

        // [3,0,-3,4,-4,7,6]
        // 3
        logResult(longestSubsequence(arr, difference));
    }
    /**
     * 1218. 最长定差子序列
     *
     * <p>给你一个整数数组 arr 和一个整数 difference，请你找出 arr 中所有相邻元素之间的差等于给定 difference 的等差子序列，并返回其中最长的等差子序列的长度。
     *
     * <p>示例 1：
     *
     * <p>输入：arr = [1,2,3,4], difference = 1 输出：4 解释：最长的等差子序列是 [1,2,3,4]。 示例 2：
     *
     * <p>输入：arr = [1,3,5,7], difference = 1 输出：1 解释：最长的等差子序列是任意单个元素。 示例 3：
     *
     * <p>输入：arr = [1,5,7,8,5,3,4,2,1], difference = -2 输出：4 解释：最长的等差子序列是 [7,5,3,1]。
     *
     * <p>提示：
     *
     * <p>1 <= arr.length <= 10^5 -10^4 <= arr[i], difference <= 10^4
     *
     * @param arr
     * @param difference
     * @return
     */
    public int longestSubsequence(int[] arr, int difference) {
        Map<Integer, Integer> map = new HashMap<>();
        int max = 0;
        for (int num : arr) {
            int count = map.getOrDefault(num - difference, 0) + 1;
            map.put(num, count);
            max = Math.max(count, max);
        }

        return max;
    }

    /**
     * 1262. 可被三整除的最大和
     *
     * <p>给你一个整数数组 nums，请你找出并返回能被三整除的元素最大和。
     *
     * <p>示例 1：
     *
     * <p>输入：nums = [3,6,5,1,8] 输出：18 解释：选出数字 3, 6, 1 和 8，它们的和是 18（可被 3 整除的最大和）。 示例 2：
     *
     * <p>输入：nums = [4] 输出：0 解释：4 不能被 3 整除，所以无法选出数字，返回 0。 示例 3：
     *
     * <p>输入：nums = [1,2,3,4,4] 输出：12 解释：选出数字 1, 3, 4 以及 4，它们的和是 12（可被 3 整除的最大和）。
     *
     * <p>提示：
     *
     * <p>1 <= nums.length <= 4 * 10^4 1 <= nums[i] <= 10^4
     *
     * @param nums
     * @return
     */
    public int maxSumDivThree(int[] nums) {
        int len = nums.length;
        // 余数0 余数1 余数2
        int[] dp = new int[3];

        dp[1] = Integer.MIN_VALUE;
        dp[2] = Integer.MIN_VALUE;

        for (int i = 0; i < len; i++) {
            int mod = nums[i] % 3;
            // 上一个 余数
            int a = dp[(3 - mod) % 3];
            int b = dp[(4 - mod) % 3];
            int c = dp[(5 - mod) % 3];

            dp[0] = Math.max(dp[0], a + nums[i]);
            dp[1] = Math.max(dp[1], b + nums[i]);
            dp[2] = Math.max(dp[2], c + nums[i]);
        }

        return dp[0];
    }

    @Test
    public void dieSimulator() {
        int n = 100;
        int[] rollMax = {7, 5, 15, 5, 1, 7};
        logResult(dieSimulator(n, rollMax));
    }

    /**
     * 1223. 掷骰子模拟
     *
     * <p>有一个骰子模拟器会每次投掷的时候生成一个 1 到 6 的随机数。
     *
     * <p>不过我们在使用它时有个约束，就是使得投掷骰子时，连续 掷出数字 i 的次数不能超过 rollMax[i]（i 从 1 开始编号）。
     *
     * <p>现在，给你一个整数数组 rollMax 和一个整数 n，请你来计算掷 n 次骰子可得到的不同点数序列的数量。
     *
     * <p>假如两个序列中至少存在一个元素不同，就认为这两个序列是不同的。由于答案可能很大，所以请返回 模 10^9 + 7 之后的结果。
     *
     * <p>示例 1：
     *
     * <p>输入：n = 2, rollMax = [1,1,2,2,2,3] 输出：34 解释：我们掷 2 次骰子，如果没有约束的话，共有 6 * 6 = 36 种可能的组合。但是根据
     * rollMax 数组，数字 1 和 2 最多连续出现一次，所以不会出现序列 (1,1) 和 (2,2)。因此，最终答案是 36-2 = 34。 示例 2：
     *
     * <p>输入：n = 2, rollMax = [1,1,1,1,1,1] 输出：30 示例 3：
     *
     * <p>输入：n = 3, rollMax = [1,1,1,2,2,3] 输出：181
     *
     * <p>提示：
     *
     * <p>1 <= n <= 5000 rollMax.length == 6 1 <= rollMax[i] <= 15
     *
     * @param n
     * @param rollMax
     * @return
     */
    public int dieSimulator(int n, int[] rollMax) {
        int[][][] dp = new int[n + 1][7][16];
        for (int i = 1; i <= 6; i++) {
            dp[1][i][1] = 1;
        }
        int MOD = 1000000007;
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= 6; j++) {
                for (int k = 1; k <= 6; k++) {
                    if (k != j) {
                        for (int t = 1; t <= rollMax[k - 1]; t++) {
                            dp[i][j][1] += dp[i - 1][k][t];
                            dp[i][j][1] %= MOD;
                        }

                    } else {
                        // 连续
                        for (int t = 1; t < rollMax[k - 1]; t++) {
                            dp[i][j][t + 1] += dp[i - 1][k][t];
                            dp[i][j][t + 1] %= MOD;
                        }
                    }
                }
            }
        }
        // 输出结果统计；
        int sum = 0;
        for (int i = 1; i <= 6; i++) {
            for (int t = 1; t <= rollMax[i - 1]; t++) {
                sum += dp[n][i][t];
                sum %= MOD;
            }
        }
        return sum;
    }

    /**
     * 1049. 最后一块石头的重量 II
     *
     * <p>有一堆石头，每块石头的重量都是正整数。
     *
     * <p>每一回合，从中选出任意两块石头，然后将它们一起粉碎。假设石头的重量分别为 x 和 y，且 x <= y。那么粉碎的可能结果如下：
     *
     * <p>如果 x == y，那么两块石头都会被完全粉碎； 如果 x != y，那么重量为 x 的石头将会完全粉碎，而重量为 y 的石头新重量为 y-x。
     * 最后，最多只会剩下一块石头。返回此石头最小的可能重量。如果没有石头剩下，就返回 0。
     *
     * <p>示例：
     *
     * <p>输入：[2,7,4,1,8,1] 输出：1 解释： 组合 2 和 4，得到 2，所以数组转化为 [2,7,1,8,1]， 组合 7 和 8，得到 1，所以数组转化为
     * [2,1,1,1]， 组合 2 和 1，得到 1，所以数组转化为 [1,1,1]， 组合 1 和 1，得到 0，所以数组转化为 [1]，这就是最优值。
     *
     * <p>提示：
     *
     * <p>1 <= stones.length <= 30 1 <= stones[i] <= 1000
     *
     * @param stones
     * @return
     */
    public int lastStoneWeightII(int[] stones) {
        // 将这些数字分成两拨,使得他们的和的差最小
        int sum = 0, len = stones.length;
        for (int stone : stones) {
            sum += stone;
        }
        int helf = sum >> 1;
        int[] dp = new int[helf + 1];
        for (int stone : stones) {
            for (int j = helf; j >= stone; j--) {
                dp[j] = Math.max(dp[j], dp[j - stone] + stone);
            }
        }
        return sum - 2 * dp[helf];
    }

    /**
     * 1048. 最长字符串链
     *
     * <p>给出一个单词列表，其中每个单词都由小写英文字母组成。
     *
     * <p>如果我们可以在 word1 的任何地方添加一个字母使其变成 word2，那么我们认为 word1 是 word2 的前身。例如，"abc" 是 "abac" 的前身。
     *
     * <p>词链是单词 [word_1, word_2, ..., word_k] 组成的序列，k >= 1，其中 word_1 是 word_2 的前身，word_2 是 word_3
     * 的前身，依此类推。
     *
     * <p>从给定单词列表 words 中选择单词组成词链，返回词链的最长可能长度。
     *
     * <p>示例：
     *
     * <p>输入：["a","b","ba","bca","bda","bdca"] 输出：4 解释：最长单词链之一为 "a","ba","bda","bdca"。
     *
     * <p>提示：
     *
     * <p>1 <= words.length <= 1000 1 <= words[i].length <= 16 words[i] 仅由小写英文字母组成。
     *
     * @param words
     * @return
     */
    public int longestStrChain(String[] words) {
        Arrays.sort(words, Comparator.comparingInt(String::length));
        int[] dp = new int[words.length];
        int[] lenIndex = new int[17];
        Arrays.fill(lenIndex, -1);
        for (int i = 0; i < words.length; i++) {
            int len = words[i].length();
            lenIndex[len] = i;
        }

        int max = 0;
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            int len = word.length() - 1;
            int index = lenIndex[len];
            while (index >= 0 && words[index].length() == len) {
                if (contains(word, words[index])) {
                    dp[i] = Math.max(dp[i], dp[index] + 1);
                }
                index--;
            }
            max = Math.max(dp[i], max);
        }

        return max + 1;
    }

    private boolean contains(String word, String str) {
        if (word.length() != str.length() + 1) {
            return false;
        }
        int i = 0, j = 0;
        while (i < word.length() && j < str.length()) {
            if (word.charAt(i) == str.charAt(j)) {
                i++;
                j++;
            } else {
                i++;
            }
        }
        return j == str.length();
    }

    @Test
    public void getMaxLen() {
        int[] nums = {1, 2, 3, 5, -6, 4, 0, 10};
        logResult(getMaxLen(nums));
    }
    /**
     * 5500. 乘积为正数的最长子数组长度
     *
     * <p>给你一个整数数组 nums ，请你求出乘积为正数的最长子数组的长度。
     *
     * <p>一个数组的子数组是由原数组中零个或者更多个连续数字组成的数组。
     *
     * <p>请你返回乘积为正数的最长子数组长度。
     *
     * <p>示例 1：
     *
     * <p>输入：nums = [1,-2,-3,4] 输出：4 解释：数组本身乘积就是正数，值为 24 。 示例 2：
     *
     * <p>输入：nums = [0,1,-2,-3,-4] 输出：3 解释：最长乘积为正数的子数组为 [1,-2,-3] ，乘积为 6 。 注意，我们不能把 0
     * 也包括到子数组中，因为这样乘积为 0 ，不是正数。 示例 3：
     *
     * <p>输入：nums = [-1,-2,-3,0,1] 输出：2 解释：乘积为正数的最长子数组是 [-1,-2] 或者 [-2,-3] 。 示例 4：
     *
     * <p>输入：nums = [-1,2] 输出：1 示例 5：
     *
     * <p>输入：nums = [1,2,3,5,-6,4,0,10] 输出：4
     *
     * <p>提示：
     *
     * <p>1 <= nums.length <= 10^5 -10^9 <= nums[i] <= 10^9
     *
     * @param nums
     * @return
     */
    public int getMaxLen(int[] nums) {
        int max = 0, len = nums.length;
        // 0 整数 1 负数
        int[][] dp = new int[len + 1][2];
        int num = 1;
        for (int i = 0; i < len; i++) {
            if (nums[i] == 0) {
                dp[i + 1][0] = 0;
                dp[i + 1][1] = 0;
                continue;
            }
            if (nums[i] > 0) {
                dp[i + 1][0] = dp[i][0] + 1;
                if (dp[i][1] > 0) {
                    dp[i + 1][1] = dp[i][1] + 1;
                }
            } else {
                dp[i + 1][1] = dp[i][0] + 1;
                if (dp[i][1] > 0) {
                    dp[i + 1][0] = dp[i][1] + 1;
                }
            }
            max = Math.max(dp[i + 1][0], max);
        }

        return max;
    }

    /**
     * 1277. 统计全为 1 的正方形子矩阵
     *
     * <p>给你一个 m * n 的矩阵，矩阵中的元素不是 0 就是 1，请你统计并返回其中完全由 1 组成的 正方形 子矩阵的个数。
     *
     * <p>示例 1：
     *
     * <p>输入：matrix = [ [0,1,1,1], [1,1,1,1], [0,1,1,1] ] 输出：15 解释： 边长为 1 的正方形有 10 个。 边长为 2 的正方形有 4
     * 个。 边长为 3 的正方形有 1 个。 正方形的总数 = 10 + 4 + 1 = 15. 示例 2：
     *
     * <p>输入：matrix = [ [1,0,1], [1,1,0], [1,1,0] ] 输出：7 解释： 边长为 1 的正方形有 6 个。 边长为 2 的正方形有 1 个。
     * 正方形的总数 = 6 + 1 = 7.
     *
     * <p>提示：
     *
     * <p>1 <= arr.length <= 300 1 <= arr[0].length <= 300 0 <= arr[i][j] <= 1
     *
     * @param matrix
     * @return
     */
    public int countSquares(int[][] matrix) {
        int rows = matrix.length, cols = matrix[0].length;
        int result = 0;
        int[][] dp = new int[rows + 1][cols + 1];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (matrix[i][j] == 0) {
                    continue;
                }
                dp[i + 1][j + 1] = Math.min(Math.min(dp[i][j + 1], dp[i + 1][j]), dp[i][j]) + 1;
                result += dp[i + 1][j + 1];
            }
        }

        return result;
    }

    static final int MOD = 1_000_000_007;

    @Test
    public void maxProductPath() {
        // {{1, 3}, {0, -4}};
        // {  {-1, -2, -3}, {-2, -3, -3}, {-3, -3, -2}};
        // {{1, 4, 4, 0}, {-2, 0, 0, 1}, {1, -1, 1, 1}};
        int[][] grid = {{2, 4, -3, 0, 3}, {-1, 0, 3, -2, 4}, {-3, -3, 1, 2, -4}, {3, 4, 0, 0, 1}};

        logResult(maxProductPath(grid));
    }
    /**
     * 5521. 矩阵的最大非负积
     *
     * <p>给你一个大小为 rows x cols 的矩阵 grid 。最初，你位于左上角 (0, 0) ，每一步，你可以在矩阵中 向右 或 向下 移动。
     *
     * <p>在从左上角 (0, 0) 开始到右下角 (rows - 1, cols - 1) 结束的所有路径中，找出具有 最大非负积 的路径。路径的积是沿路径访问的单元格中所有整数的乘积。
     *
     * <p>返回 最大非负积 对 109 + 7 取余 的结果。如果最大积为负数，则返回 -1 。
     *
     * <p>注意，取余是在得到最大积之后执行的。
     *
     * <p>示例 1：
     *
     * <p>输入：grid = [[-1,-2,-3], [-2,-3,-3], [-3,-3,-2]] 输出：-1 解释：从 (0, 0) 到 (2, 2) 的路径中无法得到非负积，所以返回
     * -1 示例 2：
     *
     * <p>输入：grid = [[1,-2,1], [1,-2,1], [3,-4,1]] 输出：8 解释：最大非负积对应的路径已经用粗体标出 (1 * 1 * -2 * -4 * 1 =
     * 8) 示例 3：
     *
     * <p>输入：grid = [[1, 3], [0,-4]] 输出：0 解释：最大非负积对应的路径已经用粗体标出 (1 * 0 * -4 = 0) 示例 4：
     *
     * <p>输入：grid = [[ 1, 4,4,0], [-2, 0,0,1], [ 1,-1,1,1]] 输出：2 解释：最大非负积对应的路径已经用粗体标出 (1 * -2 * 1 *
     * -1 * 1 * 1 = 2)
     *
     * <p>提示：
     *
     * <p>1 <= rows, cols <= 15 -4 <= grid[i][j] <= 4
     *
     * @param grid
     * @return
     */
    public int maxProductPath(int[][] grid) {
        int rows = grid.length, cols = grid[0].length;
        long[][][] dp = new long[rows][cols][2];
        if (grid[0][0] == 0) {
            return 0;
        }
        dp[0][0][0] = grid[0][0];
        dp[0][0][1] = grid[0][0];

        for (int i = 1; i < rows; i++) {
            if (grid[i][0] == 0) {
                continue;
            }
            if (grid[i][0] > 0) {
                dp[i][0][0] = dp[i - 1][0][0] * grid[i][0];
                dp[i][0][1] = dp[i - 1][0][1] * grid[i][0];
            } else {
                dp[i][0][0] = dp[i - 1][0][1] * grid[i][0];
                dp[i][0][1] = dp[i - 1][0][0] * grid[i][0];
            }
        }
        for (int j = 1; j < cols; j++) {
            if (grid[0][j] == 0) {
                continue;
            }
            if (grid[0][j] > 0) {
                dp[0][j][0] = dp[0][j - 1][0] * grid[0][j];
                dp[0][j][1] = dp[0][j - 1][1] * grid[0][j];
            } else {
                dp[0][j][0] = dp[0][j - 1][1] * grid[0][j];
                dp[0][j][1] = dp[0][j - 1][0] * grid[0][j];
            }
        }

        for (int i = 1; i < rows; i++) {

            for (int j = 1; j < cols; j++) {
                if (grid[i][j] == 0) {
                    continue;
                }
                if (grid[i][j] > 0) {
                    dp[i][j][0] = Math.max(dp[i - 1][j][0], dp[i][j - 1][0]) * grid[i][j];
                    dp[i][j][1] = Math.min(dp[i - 1][j][1], dp[i][j - 1][1]) * grid[i][j];
                } else {
                    dp[i][j][0] = Math.min(dp[i - 1][j][1], dp[i][j - 1][1]) * grid[i][j];
                    dp[i][j][1] = Math.max(dp[i - 1][j][0], dp[i][j - 1][0]) * grid[i][j];
                }
            }
        }
        logResult(dp);
        return dp[rows - 1][cols - 1][0] < 0 ? -1 : (int) dp[rows - 1][cols - 1][0] % MOD;
    }

    /**
     * 1043. 分隔数组以得到最大和
     *
     * <p>给出整数数组 A，将该数组分隔为长度最多为 K 的几个（连续）子数组。分隔完成后，每个子数组的中的值都会变为该子数组中的最大值。
     *
     * <p>返回给定数组完成分隔后的最大和。
     *
     * <p>示例：
     *
     * <p>输入：A = [1,15,7,9,2,5,10], K = 3 输出：84 解释：A 变为 [15,15,15,9,10,10,10]
     *
     * <p>提示：
     *
     * <p>1 <= K <= A.length <= 500 0 <= A[i] <= 10^6
     *
     * @param arr
     * @param k
     * @return
     */
    public int maxSumAfterPartitioning(int[] arr, int k) {
        // 设dp[i]表示前i个元素能达到的最大和。考虑到长度最大为K，那么dp[i]可以由dp[i-K] ~ dp[i-1]推导而来，即状态转移为：
        // dp[i] = max( dp[j] + max(A[j:i]) * (i - j) )，其中i - K <= j <= i - 1
        int len = arr.length;
        int[] dp = new int[len + 1];

        for (int i = 1; i <= len; i++) {
            int max = -1;
            for (int j = i - 1; j >= Math.max(i - k, 0); j--) {
                max = Math.max(max, arr[j]);
                dp[i] = Math.max(dp[i], dp[j] + max * (i - j));
            }
        }

        return dp[len];
    }

    /**
     * 790. 多米诺和托米诺平铺
     *
     * <p>有两种形状的瓷砖：一种是 2x1 的多米诺形，另一种是形如 "L" 的托米诺形。两种形状都可以旋转。
     *
     * <p>XX <- 多米诺
     *
     * <p>XX <- "L" 托米诺 X 给定 N 的值，有多少种方法可以平铺 2 x N 的面板？返回值 mod 10^9 + 7。
     *
     * <p>（平铺指的是每个正方形都必须有瓷砖覆盖。两个平铺不同，当且仅当面板上有四个方向上的相邻单元中的两个，使得恰好有一个平铺有一个瓷砖占据两个正方形。）
     *
     * <p>示例: 输入: 3 输出: 5 解释: 下面列出了五种不同的方法，不同字母代表不同瓷砖： XYZ XXZ XYY XXY XYY XYZ YYZ XZZ XYY XXY 提示：
     *
     * <p>N 的范围是 [1, 1000]
     *
     * @param N
     * @return
     */
    public int numTilings(int N) {
        // dp[n] = 2*dp[n-1] + dp[n-3]
        int[] dp = new int[N + 3];
        dp[0] = 1;
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 5;
        for (int i = 4; i <= N; i++) {
            dp[i] = (2 * dp[i - 1] % MOD + dp[i - 3]) % MOD;
        }
        return dp[N];
    }

    @Test
    public void champagneTower() {
        int poured = 6, query_row = 3, query_glass = 1;
        logResult(champagneTower(poured, query_row, query_glass));
    }
    /**
     * 799. 香槟塔
     *
     * <p>我们把玻璃杯摆成金字塔的形状，其中第一层有1个玻璃杯，第二层有2个，依次类推到第100层，每个玻璃杯(250ml)将盛有香槟。
     *
     * <p>从顶层的第一个玻璃杯开始倾倒一些香槟，当顶层的杯子满了，任何溢出的香槟都会立刻等流量的流向左右两侧的玻璃杯。当左右两边的杯子也满了，就会等流量的流向它们左右两边的杯子，依次类推。（当最底层的玻璃杯满了，香槟会流到地板上）
     *
     * <p>例如，在倾倒一杯香槟后，最顶层的玻璃杯满了。倾倒了两杯香槟后，第二层的两个玻璃杯各自盛放一半的香槟。在倒三杯香槟后，第二层的香槟满了 -
     * 此时总共有三个满的玻璃杯。在倒第四杯后，第三层中间的玻璃杯盛放了一半的香槟，他两边的玻璃杯各自盛放了四分之一的香槟，如下图所示。
     *
     * <p>现在当倾倒了非负整数杯香槟后，返回第 i 行 j 个玻璃杯所盛放的香槟占玻璃杯容积的比例（i 和 j都从0开始）。
     *
     * <p>示例 1: 输入: poured(倾倒香槟总杯数) = 1, query_glass(杯子的位置数) = 1, query_row(行数) = 1 输出: 0.0 解释:
     * 我们在顶层（下标是（0，0））倒了一杯香槟后，没有溢出，因此所有在顶层以下的玻璃杯都是空的。
     *
     * <p>示例 2: 输入: poured(倾倒香槟总杯数) = 2, query_glass(杯子的位置数) = 1, query_row(行数) = 1 输出: 0.5 解释:
     * 我们在顶层（下标是（0，0）倒了两杯香槟后，有一杯量的香槟将从顶层溢出，位于（1，0）的玻璃杯和（1，1）的玻璃杯平分了这一杯香槟，所以每个玻璃杯有一半的香槟。 注意:
     *
     * <p>poured 的范围[0, 10 ^ 9]。 query_glass 和query_row 的范围 [0, 99]。
     *
     * @param poured
     * @param query_row
     * @param query_glass
     * @return
     */
    public double champagneTower(int poured, int query_row, int query_glass) {
        double[][] dp = new double[query_row + 1][query_row + 1];
        dp[0][0] = poured;
        for (int i = 1; i <= query_row; i++) {
            for (int j = 0; j <= i; j++) {
                double left = j == 0 ? 0.0 : dp[i - 1][j - 1];
                double right = j == i ? 0.0 : dp[i - 1][j];

                left = left <= 1.0 ? 0 : left - 1.0;
                right = right <= 1.0 ? 0 : right - 1.0;
                if (left + right <= 0.0) {
                    continue;
                }
                dp[i][j] = (left + right) / 2.0;
            }
        }
        logResult(dp);
        return Math.min(dp[query_row][query_glass], 1.0);
    }

    /**
     * 801. 使序列递增的最小交换次数
     *
     * <p>我们有两个长度相等且不为空的整型数组 A 和 B 。
     *
     * <p>我们可以交换 A[i] 和 B[i] 的元素。注意这两个元素在各自的序列中应该处于相同的位置。
     *
     * <p>在交换过一些元素之后，数组 A 和 B 都应该是严格递增的（数组严格递增的条件仅为A[0] < A[1] < A[2] < ... < A[A.length - 1]）。
     *
     * <p>给定数组 A 和 B ，请返回使得两个数组均保持严格递增状态的最小交换次数。假设给定的输入总是有效的。
     *
     * <p>示例: 输入: A = [1,3,5,4], B = [1,2,3,7] 输出: 1 解释: 交换 A[3] 和 B[3] 后，两个数组如下: A = [1, 3, 5, 7] ，
     * B = [1, 2, 3, 4] 两个数组均为严格递增的。 注意:
     *
     * <p>A, B 两个数组的长度总是相等的，且长度的范围为 [1, 1000]。 A[i], B[i] 均为 [0, 2000]区间内的整数。
     *
     * @param A
     * @param B
     * @return
     */
    public int minSwap(int[] A, int[] B) {
        // n 不交换  s 交换
        int n = 0, s = 1;
        for (int i = 1; i < A.length; i++) {
            int n2 = Integer.MAX_VALUE, s2 = Integer.MAX_VALUE;
            if (A[i - 1] < A[i] && B[i - 1] < B[i]) {
                n2 = Math.min(n2, n);
                s2 = Math.min(s2, s + 1);
            }
            if (A[i - 1] < B[i] && B[i - 1] < A[i]) {
                n2 = Math.min(n2, s);
                s2 = Math.min(s2, n + 1);
            }
            n = n2;
            s = s2;
        }

        return Math.min(n, s);
    }

    /**
     * 823. 带因子的二叉树
     *
     * <p>给出一个含有不重复整数元素的数组，每个整数均大于 1。
     *
     * <p>我们用这些整数来构建二叉树，每个整数可以使用任意次数。
     *
     * <p>其中：每个非叶结点的值应等于它的两个子结点的值的乘积。
     *
     * <p>满足条件的二叉树一共有多少个？返回的结果应模除 10 ** 9 + 7。
     *
     * <p>示例 1:
     *
     * <p>输入: A = [2, 4] 输出: 3 解释: 我们可以得到这些二叉树: [2], [4], [4, 2, 2] 示例 2:
     *
     * <p>输入: A = [2, 4, 5, 10] 输出: 7 解释: 我们可以得到这些二叉树: [2], [4], [5], [10], [4, 2, 2], [10, 2, 5],
     * [10, 5, 2].
     *
     * <p>提示:
     *
     * <p>1 <= A.length <= 1000. 2 <= A[i] <= 10 ^ 9.
     *
     * @param A
     * @return
     */
    public int numFactoredBinaryTrees(int[] A) {
        Arrays.sort(A);
        int len = A.length;
        long[] dp = new long[len];
        Arrays.fill(dp, 1);
        Map<Integer, Integer> indexMap = new HashMap<>();
        for (int i = 0; i < len; i++) {
            indexMap.put(A[i], i);
        }
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < i; j++) {
                if (A[i] % A[j] != 0) {
                    continue;
                }
                int l = A[i] / A[j];
                if (!indexMap.containsKey(l)) {
                    continue;
                }
                dp[i] += dp[j] * dp[indexMap.get(l)] % MOD;
                dp[i] %= MOD;
            }
        }
        long result = 0;
        for (long num : dp) {
            result += num;
            result %= MOD;
        }
        return (int) result;
    }

    /**
     * 808. 分汤
     *
     * <p>有 A 和 B 两种类型的汤。一开始每种类型的汤有 N 毫升。有四种分配操作：
     *
     * <p>提供 100ml 的汤A 和 0ml 的汤B。 提供 75ml 的汤A 和 25ml 的汤B。 提供 50ml 的汤A 和 50ml 的汤B。 提供 25ml 的汤A 和 75ml
     * 的汤B。
     * 当我们把汤分配给某人之后，汤就没有了。每个回合，我们将从四种概率同为0.25的操作中进行分配选择。如果汤的剩余量不足以完成某次操作，我们将尽可能分配。当两种类型的汤都分配完时，停止操作。
     *
     * <p>注意不存在先分配100 ml汤B的操作。
     *
     * <p>需要返回的值： 汤A先分配完的概率 + 汤A和汤B同时分配完的概率 / 2。
     *
     * <p>示例: 输入: N = 50 输出: 0.625 解释: 如果我们选择前两个操作，A将首先变为空。对于第三个操作，A和B会同时变为空。对于第四个操作，B将首先变为空。
     * 所以A变为空的总概率加上A和B同时变为空的概率的一半是 0.25 *(1 + 1 + 0.5 + 0)= 0.625。 注释:
     *
     * <p>0 <= N <= 10^9。 返回值在 10^-6 的范围将被认为是正确的。
     *
     * @param N
     * @return
     */
    public double soupServings(int N) {
        N = N / 25 + (N % 25 > 0 ? 1 : 0);
        // dp(i, j) = 1/4 * (dp(i - 4, y) + dp(i - 3, y - 1) + dp(i - 2, y - 2) + dp(i - 1, y - 3))
        if (N >= 500) {
            return 1.0;
        }
        double[][] dp = new double[N + 1][N + 1];
        dp[0][0] = 0.5; // 特殊情况，0毫升A、0毫升B（同时分配完 1 * 0.5）
        for (int i = 1; i <= N; ++i) {
            dp[i][0] = 0; // i毫升A，0毫升B，则B必定先分配完，不可能出现A先分配完或者A、B同时分配完
            dp[0][i] = 1; // 0毫升A，i毫升B，则A必定先分配完，概率为1
        }
        for (int i = 1; i <= N; ++i) {
            int a1 = Math.max(i - 4, 0); // 不足4，按4算（实际上是不足100，按100算，然后分配完了，没有剩余）
            int a2 = Math.max(i - 3, 0); // 不足3，按3算（实际上是不足75，按75算，然后分配完了，没有剩余）
            int a3 = Math.max(i - 2, 0); // 不足2，按2算（实际上是不足50，按75算，然后分配完了，没有剩余）
            int a4 = Math.max(i - 1, 0); // 不足1，按1算（实际上是不足25，按25算，然后分配完了，没有剩余）
            for (int j = 1; j <= N; ++j) {
                int b1 = j;
                int b2 = Math.max(j - 1, 0); // 不足1，按1算（实际上是不足25，按25算，然后分配完了，没有剩余）
                int b3 = Math.max(j - 2, 0); // 不足2，按2算（实际上是不足50，按75算，然后分配完了，没有剩余）
                int b4 = Math.max(j - 3, 0); // 不足3，按3算（实际上是不足75，按75算，然后分配完了，没有剩余）
                // 状态转移方程：dp[i][j] = 0.25 * (dp[i-100][j] + dp[i-75][j-25] + dp[i-50][j-50] +
                // dp[i-75][j-25])
                // 将N缩小为原来的25分之一的转移方程：dp[i][j] = 0.25 * (dp[i-4][j] + dp[i-3][j-1] + dp[i-2][j-2] +
                // dp[i-3][j-1])
                dp[i][j] = 0.25 * (dp[a1][b1] + dp[a2][b2] + dp[a3][b3] + dp[a4][b4]);
            }
        }
        return dp[N][N];
    }

    /**
     * 813. 最大平均值和的分组
     *
     * <p>我们将给定的数组 A 分成 K 个相邻的非空子数组 ，我们的分数由每个子数组内的平均值的总和构成。计算我们所能得到的最大分数是多少。
     *
     * <p>注意我们必须使用 A 数组中的每一个数进行分组，并且分数不一定需要是整数。
     *
     * <p>示例: 输入: A = [9,1,2,3,9] K = 3 输出: 20 解释: A 的最优分组是[9], [1, 2, 3], [9]. 得到的分数是 9 + (1 + 2 +
     * 3) / 3 + 9 = 20. 我们也可以把 A 分成[9, 1], [2], [3, 9]. 这样的分组得到的分数为 5 + 2 + 6 = 13, 但不是最大值. 说明:
     *
     * <p>1 <= A.length <= 100. 1 <= A[i] <= 10000. 1 <= K <= A.length. 答案误差在 10^-6 内被视为是正确的。
     *
     * @param A
     * @param K
     * @return
     */
    public double largestSumOfAverages(int[] A, int K) {
        // 设 dp(i, k) 表示将数组 A 中的前 i 个元素 A[:i] 分成 k 个相邻的非空子数组，可以得到的最大分数。dp(i, k) 的值可以通过 dp(j, k - 1)
        // 转移而来，其中 j < i，状态转移方程为：
        // dp(i, k) = max(dp(j, k - 1) + average(j + 1, i))
        // dp(i, 0) = average(0, i)
        int n = A.length;
        double[][] dp = new double[K][n];
        double sum = 0;
        for (int i = 0; i < n; i++) {
            sum += A[i];
            dp[0][i] = sum / (i + 1);
        }

        for (int k = 1; k < K; k++) {
            for (int i = k; i < n; i++) {
                sum = 0.0;
                for (int j = i; j > k - 1; j--) {
                    sum += A[j];
                    dp[k][i] = Math.max(dp[k][i], sum / (i - j + 1) + dp[k - 1][j - 1]);
                }
            }
        }
        return dp[K - 1][n - 1];
    }

    /**
     * 1626. 无矛盾的最佳球队
     *
     * <p>假设你是球队的经理。对于即将到来的锦标赛，你想组合一支总体得分最高的球队。球队的得分是球队中所有球员的分数 总和 。
     *
     * <p>然而，球队中的矛盾会限制球员的发挥，所以必须选出一支 没有矛盾 的球队。如果一名年龄较小球员的分数 严格大于 一名年龄较大的球员，则存在矛盾。同龄球员之间不会发生矛盾。
     *
     * <p>给你两个列表 scores 和 ages，其中每组 scores[i] 和 ages[i] 表示第 i 名球员的分数和年龄。请你返回 所有可能的无矛盾球队中得分最高那支的分数 。
     *
     * <p>示例 1：
     *
     * <p>输入：scores = [1,3,5,10,15], ages = [1,2,3,4,5] 输出：34 解释：你可以选中所有球员。 示例 2：
     *
     * <p>输入：scores = [4,5,6,5], ages = [2,1,2,1] 输出：16 解释：最佳的选择是后 3 名球员。注意，你可以选中多个同龄球员。 示例 3：
     *
     * <p>输入：scores = [1,2,3,5], ages = [8,9,10,1] 输出：6 解释：最佳的选择是前 3 名球员。
     *
     * <p>提示：
     *
     * <p>1 <= scores.length, ages.length <= 1000 scores.length == ages.length 1 <= scores[i] <= 106
     * 1 <= ages[i] <= 1000
     *
     * @param scores
     * @param ages
     * @return
     */
    public int bestTeamScore(int[] scores, int[] ages) {
        int len = scores.length;
        int[][] nums = new int[len][2];
        for (int i = 0; i < len; i++) {
            nums[i][0] = ages[i];
            nums[i][1] = scores[i];
        }
        Arrays.sort(nums, (a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
        int[] dp = new int[len];
        int max = 0;
        for (int i = 0; i < len; i++) {
            // 找到最大的
            for (int j = i - 1; j >= 0; j--) {
                if (nums[i][1] >= nums[j][1]) {
                    dp[i] = Math.max(dp[i], dp[j]);
                }
            }
            dp[i] += nums[i][1];
            max = Math.max(max, dp[i]);
        }

        return max;
    }

    /**
     * 1621. 大小为 K 的不重叠线段的数目
     *
     * <p>给你一维空间的 n 个点，其中第 i 个点（编号从 0 到 n-1）位于 x = i 处，请你找到 恰好 k 个不重叠 线段且每个线段至少覆盖两个点的方案数。线段的两个端点必须都是
     * 整数坐标 。这 k 个线段不需要全部覆盖全部 n 个点，且它们的端点 可以 重合。
     *
     * <p>请你返回 k 个不重叠线段的方案数。由于答案可能很大，请将结果对 109 + 7 取余 后返回。
     *
     * <p>示例 1：
     *
     * <p>输入：n = 4, k = 2 输出：5 解释： 如图所示，两个线段分别用红色和蓝色标出。 上图展示了 5 种不同的方案
     * {(0,2),(2,3)}，{(0,1),(1,3)}，{(0,1),(2,3)}，{(1,2),(2,3)}，{(0,1),(1,2)} 。 示例 2：
     *
     * <p>输入：n = 3, k = 1 输出：3 解释：总共有 3 种不同的方案 {(0,1)}, {(0,2)}, {(1,2)} 。 示例 3：
     *
     * <p>输入：n = 30, k = 7 输出：796297179 解释：画 7 条线段的总方案数为 3796297200 种。将这个数对 109 + 7 取余得到 796297179 。
     * 示例 4：
     *
     * <p>输入：n = 5, k = 3 输出：7 示例 5：
     *
     * <p>输入：n = 3, k = 2 输出：1
     *
     * <p>提示：
     *
     * <p>2 <= n <= 1000 1 <= k <= n-1
     *
     * @param n
     * @param k
     * @return
     */
    public int numberOfSets(int n, int k) {
        // 在 [0, n+k-1)[0,n+k−1) 共 n+k-1n+k−1 个数中选择 2k个
        int result = 0;
        int a = 1, b = 1;
        for (int i = 1; i <= k; i++) {
            a = (int) ((long) a * (n + i - 1) * (n - i) % MOD);
            b = (int) ((long) b * (2 * i - 1) * (2 * i) % MOD);
            int bo = quickPower(b, MOD - 2);
            result = (int) ((long) a * bo % MOD);
        }
        return result;
    }

    private int quickPower(int a, int y) {
        int result = 1;
        int x = a;
        while (y > 0) {
            if ((y & 1) == 1) {
                result = (int) (((long) result * x) % MOD);
            }
            x = (int) (((long) x * x) % MOD);
            y >>= 1;
        }
        return result % MOD;
    }

    @Test
    public void videoStitching() {
        int[][] clips = {{3, 12}, {7, 14}, {9, 14}, {12, 15}, {0, 9}, {4, 14}, {2, 7}, {1, 11}};
        int T = 10;
        logResult(videoStitching(clips, T));
    }

    /**
     * 1024. 视频拼接
     *
     * <p>你将会获得一系列视频片段，这些片段来自于一项持续时长为 T 秒的体育赛事。这些片段可能有所重叠，也可能长度不一。
     *
     * <p>视频片段 clips[i] 都用区间进行表示：开始于 clips[i][0] 并于 clips[i][1] 结束。我们甚至可以对这些片段自由地再剪辑，例如片段 [0, 7]
     * 可以剪切成 [0, 1] + [1, 3] + [3, 7] 三部分。
     *
     * <p>我们需要将这些片段进行再剪辑，并将剪辑后的内容拼接成覆盖整个运动过程的片段（[0, T]）。返回所需片段的最小数目，如果无法完成该任务，则返回 -1 。
     *
     * <p>示例 1：
     *
     * <p>输入：clips = [[0,2],[4,6],[8,10],[1,9],[1,5],[5,9]], T = 10 输出：3 解释： 我们选中 [0,2], [8,10],
     * [1,9] 这三个片段。 然后，按下面的方案重制比赛片段： 将 [1,9] 再剪辑为 [1,2] + [2,8] + [8,9] 。 现在我们手上有 [0,2] + [2,8] +
     * [8,10]，而这些涵盖了整场比赛 [0, 10]。 示例 2：
     *
     * <p>输入：clips = [[0,1],[1,2]], T = 5 输出：-1 解释： 我们无法只用 [0,1] 和 [1,2] 覆盖 [0,5] 的整个过程。 示例 3：
     *
     * <p>输入：clips =
     * [[0,1],[6,8],[0,2],[5,6],[0,4],[0,3],[6,7],[1,3],[4,7],[1,4],[2,5],[2,6],[3,4],[4,5],[5,7],[6,9]],
     * T = 9 输出：3 解释： 我们选取片段 [0,4], [4,7] 和 [6,9] 。 示例 4：
     *
     * <p>输入：clips = [[0,4],[2,8]], T = 5 输出：2 解释： 注意，你可能录制超过比赛结束时间的视频。
     *
     * <p>提示：
     *
     * <p>1 <= clips.length <= 100 0 <= clips[i][0] <= clips[i][1] <= 100 0 <= T <= 100
     *
     * @param clips
     * @param T
     * @return
     */
    public int videoStitching(int[][] clips, int T) {
        int min = Integer.MAX_VALUE;
        // 先按结束时间，再按开始时间 排序

        Arrays.sort(clips, (a, b) -> a[1] == b[1] ? a[0] - b[0] : a[1] - b[1]);
        int len = clips.length;
        int[] dp = new int[len];

        logResult(clips);
        for (int i = 0; i < len; i++) {
            dp[i] = clips[i][0] == 0 ? 1 : Integer.MAX_VALUE;
            if (clips[i][0] == 0 && clips[i][1] >= T) {
                return 1;
            }
        }

        for (int i = 1; i < len; i++) {
            int j = i - 1;
            while (j >= 0 && clips[j][1] >= clips[i][0]) {
                if (dp[j] != Integer.MAX_VALUE) {
                    dp[i] = Math.min(dp[i], dp[j] + 1);
                }
                j--;
                if (dp[i] != Integer.MAX_VALUE && clips[i][1] >= T) {
                    min = Math.min(min, dp[i]);
                }
            }
        }

        return min == Integer.MAX_VALUE ? -1 : min;
    }

    /**
     * 873. 最长的斐波那契子序列的长度
     *
     * <p>如果序列 X_1, X_2, ..., X_n 满足下列条件，就说它是 斐波那契式 的：
     *
     * <p>n >= 3 对于所有 i + 2 <= n，都有 X_i + X_{i+1} = X_{i+2} 给定一个严格递增的正整数数组形成序列，找到 A
     * 中最长的斐波那契式的子序列的长度。如果一个不存在，返回 0 。
     *
     * <p>（回想一下，子序列是从原序列 A 中派生出来的，它从 A 中删掉任意数量的元素（也可以不删），而不改变其余元素的顺序。例如， [3, 5, 8] 是 [3, 4, 5, 6, 7,
     * 8] 的一个子序列）
     *
     * <p>示例 1：
     *
     * <p>输入: [1,2,3,4,5,6,7,8] 输出: 5 解释: 最长的斐波那契式子序列为：[1,2,3,5,8] 。 示例 2：
     *
     * <p>输入: [1,3,7,11,12,14,18] 输出: 3 解释: 最长的斐波那契式子序列有： [1,11,12]，[3,11,14] 以及 [7,11,18] 。
     *
     * <p>提示：
     *
     * <p>3 <= A.length <= 1000 1 <= A[0] < A[1] < ... < A[A.length - 1] <= 10^9 （对于以 Java，C，C++，以及
     * C# 的提交，时间限制被减少了 50%）
     *
     * @param A
     * @return
     */
    public int lenLongestFibSubseq(int[] A) {
        Map<Integer, Integer> indexMap = new HashMap<>();
        int max = 0, len = A.length;
        for (int i = 0; i < len; i++) {
            indexMap.put(A[i], i);
        }
        // dp[i][j]：表示以A[i],A[j]结尾的斐波那契数列的最大长度
        int[][] dp = new int[len][len];
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                dp[i][j] = 2;
            }
        }

        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                int left = A[j] - A[i];
                if (left >= A[i]) {
                    continue;
                }
                int leftIndex = indexMap.getOrDefault(left, -1);
                if (leftIndex != -1) {
                    dp[i][j] = dp[leftIndex][i] + 1;
                }

                max = Math.max(max, dp[i][j]);
            }
        }

        return max > 2 ? max : 0;
    }

    /**
     * 877. 石子游戏
     *
     * <p>亚历克斯和李用几堆石子在做游戏。偶数堆石子排成一行，每堆都有正整数颗石子 piles[i] 。
     *
     * <p>游戏以谁手中的石子最多来决出胜负。石子的总数是奇数，所以没有平局。
     *
     * <p>亚历克斯和李轮流进行，亚历克斯先开始。 每回合，玩家从行的开始或结束处取走整堆石头。 这种情况一直持续到没有更多的石子堆为止，此时手中石子最多的玩家获胜。
     *
     * <p>假设亚历克斯和李都发挥出最佳水平，当亚历克斯赢得比赛时返回 true ，当李赢得比赛时返回 false 。
     *
     * <p>示例：
     *
     * <p>输入：[5,3,4,5] 输出：true 解释： 亚历克斯先开始，只能拿前 5 颗或后 5 颗石子 。 假设他取了前 5 颗，这一行就变成了 [3,4,5] 。 如果李拿走前 3
     * 颗，那么剩下的是 [4,5]，亚历克斯拿走后 5 颗赢得 10 分。 如果李拿走后 5 颗，那么剩下的是 [3,4]，亚历克斯拿走后 4 颗赢得 9 分。 这表明，取前 5
     * 颗石子对亚历克斯来说是一个胜利的举动，所以我们返回 true 。
     *
     * <p>提示：
     *
     * <p>2 <= piles.length <= 500 piles.length 是偶数。 1 <= piles[i] <= 500 sum(piles) 是奇数。
     *
     * @param piles
     * @return
     */
    public boolean stoneGame(int[] piles) {
        int len = piles.length;
        int[][] dp = new int[len][len];
        for (int i = 0; i < len; i++) {
            dp[i][i] = piles[i];
        }
        for (int i = len - 2; i >= 0; i--) {
            for (int j = i + 1; j < len; j++) {
                dp[i][j] = Math.max(piles[i] - dp[i + 1][j], piles[j] - dp[i][j - 1]);
            }
        }

        return dp[0][len - 1] > 0;
    }

    @Test
    public void countVowelStrings() {
        int n = 33;
        logResult(countVowelStrings(n));
    }

    /**
     * 5555. 统计字典序元音字符串的数目
     *
     * <p>给你一个整数 n，请返回长度为 n 、仅由元音 (a, e, i, o, u) 组成且按 字典序排列 的字符串数量。
     *
     * <p>字符串 s 按 字典序排列 需要满足：对于所有有效的 i，s[i] 在字母表中的位置总是与 s[i+1] 相同或在 s[i+1] 之前。
     *
     * <p>示例 1：
     *
     * <p>输入：n = 1 输出：5 解释：仅由元音组成的 5 个字典序字符串为 ["a","e","i","o","u"] 示例 2：
     *
     * <p>输入：n = 2 输出：15 解释：仅由元音组成的 15 个字典序字符串为
     * ["aa","ae","ai","ao","au","ee","ei","eo","eu","ii","io","iu","oo","ou","uu"] 注意，"ea"
     * 不是符合题意的字符串，因为 'e' 在字母表中的位置比 'a' 靠后 示例 3：
     *
     * <p>输入：n = 33 输出：66045
     *
     * <p>提示：
     *
     * <p>1 <= n <= 50
     *
     * @param n
     * @return
     */
    public int countVowelStrings(int n) {
        // dp[i] 以 a, e, i, o, u 结尾的字符串个数
        int[] dp = new int[5];
        Arrays.fill(dp, 1);
        for (int i = 1; i < n; i++) {

            // e 结尾的个数
            dp[1] += dp[0];
            // i 结尾的字符串个数
            dp[2] += dp[1];
            // o 结尾的字符串个数
            dp[3] += dp[2];
            // u 结尾的字符串个数
            dp[4] += dp[3];
        }

        return Arrays.stream(dp).sum();
    }

    /**
     * 918. 环形子数组的最大和
     *
     * <p>给定一个由整数数组 A 表示的环形数组 C，求 C 的非空子数组的最大可能和。
     *
     * <p>在此处，环形数组意味着数组的末端将会与开头相连呈环状。（形式上，当0 <= i < A.length 时 C[i] = A[i]，且当 i >= 0 时 C[i+A.length]
     * = C[i]）
     *
     * <p>此外，子数组最多只能包含固定缓冲区 A 中的每个元素一次。（形式上，对于子数组 C[i], C[i+1], ..., C[j]，不存在 i <= k1, k2 <= j 其中 k1
     * % A.length = k2 % A.length）
     *
     * <p>示例 1：
     *
     * <p>输入：[1,-2,3,-2] 输出：3 解释：从子数组 [3] 得到最大和 3 示例 2：
     *
     * <p>输入：[5,-3,5] 输出：10 解释：从子数组 [5,5] 得到最大和 5 + 5 = 10 示例 3：
     *
     * <p>输入：[3,-1,2,-1] 输出：4 解释：从子数组 [2,-1,3] 得到最大和 2 + (-1) + 3 = 4 示例 4：
     *
     * <p>输入：[3,-2,2,-3] 输出：3 解释：从子数组 [3] 和 [3,-2,2] 都可以得到最大和 3 示例 5：
     *
     * <p>输入：[-2,-3,-1] 输出：-1 解释：从子数组 [-1] 得到最大和 -1
     *
     * <p>提示：
     *
     * <p>-30000 <= A[i] <= 30000 1 <= A.length <= 30000
     *
     * @param A
     * @return
     */
    public int maxSubarraySumCircular(int[] A) {
        // 求最大和最小值, sum - 最小 = 循环的最大
        int len = A.length;
        int lastMax = A[0], lastMin = A[0];
        int sum = A[0], max = lastMax, min = 0;
        for (int i = 1; i < len; i++) {
            sum += A[i];
            if (lastMax < 0) {
                lastMax = A[i];
            } else {
                lastMax += A[i];
            }
            if (lastMin > 0) {
                lastMin = A[i];
            } else {
                lastMin += A[i];
            }
            max = Math.max(max, lastMax);
            min = Math.min(min, lastMin);
        }
        return Math.max(max, sum - min);
    }

    /**
     * 931. 下降路径最小和
     *
     * <p>给定一个方形整数数组 A，我们想要得到通过 A 的下降路径的最小和。
     *
     * <p>下降路径可以从第一行中的任何元素开始，并从每一行中选择一个元素。在下一行选择的元素和当前行所选元素最多相隔一列。
     *
     * <p>示例：
     *
     * <p>输入：[[1,2,3],[4,5,6],[7,8,9]] 输出：12 解释： 可能的下降路径有： [1,4,7], [1,4,8], [1,5,7], [1,5,8],
     * [1,5,9] [2,4,7], [2,4,8], [2,5,7], [2,5,8], [2,5,9], [2,6,8], [2,6,9] [3,5,7], [3,5,8],
     * [3,5,9], [3,6,8], [3,6,9] 和最小的下降路径是 [1,4,7]，所以答案是 12。
     *
     * <p>提示：
     *
     * <p>1 <= A.length == A[0].length <= 100 -100 <= A[i][j] <= 100
     *
     * @param A
     * @return
     */
    public int minFallingPathSum(int[][] A) {
        int rows = A.length, cols = A[0].length;

        for (int i = 1; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int min = A[i - 1][j];
                if (j > 0) {
                    min = Math.min(min, A[i - 1][j - 1]);
                }
                if (j < cols - 1) {
                    min = Math.min(min, A[i - 1][j + 1]);
                }
                A[i][j] += min;
            }
        }
        int min = Integer.MAX_VALUE;
        for (int j = 0; j < cols; j++) {
            min = Math.min(min, A[rows - 1][j]);
        }
        return min;
    }

    /**
     * 935. 骑士拨号器
     *
     * <p>国际象棋中的骑士可以按下图所示进行移动：
     *
     * <p>这一次，我们将 “骑士” 放在电话拨号盘的任意数字键（如上图所示）上，接下来，骑士将会跳 N-1 步。每一步必须是从一个数字键跳到另一个数字键。
     *
     * <p>每当它落在一个键上（包括骑士的初始位置），都会拨出键所对应的数字，总共按下 N 位数字。
     *
     * <p>你能用这种方式拨出多少个不同的号码？
     *
     * <p>因为答案可能很大，所以输出答案模 10^9 + 7。
     *
     * <p>示例 1：
     *
     * <p>输入：1 输出：10 示例 2：
     *
     * <p>输入：2 输出：20 示例 3：
     *
     * <p>输入：3 输出：46
     *
     * <p>提示：
     *
     * <p>1 <= N <= 5000
     *
     * @param n
     * @return
     */
    public int knightDialer(int n) {
        int result = 0;
        int[][] path = {
            {4, 6}, {6, 8}, {7, 9}, {4, 8}, {0, 3, 9}, {}, {0, 1, 7}, {2, 6}, {1, 3}, {2, 4}
        };
        int[][] dp = new int[n][10];
        Arrays.fill(dp[0], 1);
        for (int i = 1; i < n; i++) {
            for (int num = 0; num < 10; num++) {
                for (int from : path[num]) {
                    dp[i][num] += dp[i - 1][from];
                    dp[i][num] %= MOD;
                }
            }
        }

        for (int num = 0; num < 10; num++) {
            result += dp[n - 1][num];
            result %= MOD;
        }

        return result;
    }

    /**
     * 978. 最长湍流子数组
     *
     * <p>当 A 的子数组 A[i], A[i+1], ..., A[j] 满足下列条件时，我们称其为湍流子数组：
     *
     * <p>若 i <= k < j，当 k 为奇数时， A[k] > A[k+1]，且当 k 为偶数时，A[k] < A[k+1]； 或 若 i <= k < j，当 k 为偶数时，A[k]
     * > A[k+1] ，且当 k 为奇数时， A[k] < A[k+1]。 也就是说，如果比较符号在子数组中的每个相邻元素对之间翻转，则该子数组是湍流子数组。
     *
     * <p>返回 A 的最大湍流子数组的长度。
     *
     * <p>示例 1：
     *
     * <p>输入：[9,4,2,10,7,8,8,1,9] 输出：5 解释：(A[1] > A[2] < A[3] > A[4] < A[5]) 示例 2：
     *
     * <p>输入：[4,8,12,16] 输出：2 示例 3：
     *
     * <p>输入：[100] 输出：1
     *
     * <p>提示：
     *
     * <p>1 <= A.length <= 40000 0 <= A[i] <= 10^9
     *
     * @param A
     * @return
     */
    public int maxTurbulenceSize(int[] A) {
        int len = A.length;
        // 0 当 k 为奇数时，A[k] > A[k+1]，且当 k 为偶数时，A[k] < A[k+1]
        // 1 当 k 为偶数时，A[k] > A[k+1]，且当 k 为奇数时，A[k] < A[k+1]。
        int[][] dp = new int[len][2];
        int max = 1;
        dp[len - 1][0] = 1;
        dp[len - 1][1] = 1;
        for (int i = len - 2; i >= 0; i--) {

            if ((i & 1) == 1) {
                // 奇数
                if (A[i] > A[i + 1]) {
                    dp[i][0] = dp[i + 1][0] + 1;
                } else {
                    dp[i][0] = 1;
                }

                if (A[i] < A[i + 1]) {
                    dp[i][1] = dp[i + 1][1] + 1;
                } else {
                    dp[i][1] = 1;
                }
            } else {
                // 偶数
                if (A[i] > A[i + 1]) {
                    dp[i][1] = dp[i + 1][1] + 1;
                } else {
                    dp[i][1] = 1;
                }

                if (A[i] < A[i + 1]) {
                    dp[i][0] = dp[i + 1][0] + 1;
                } else {
                    dp[i][0] = 1;
                }
            }
            max = Math.max(max, dp[i][0]);
            max = Math.max(max, dp[i][1]);
        }
        logResult(dp);
        return max;
    }

    @Test
    public void maxTurbulenceSize() {
        int[] nums = {0, 8, 45, 88, 48, 68, 28, 55, 17, 24};
        logResult(maxTurbulenceSize(nums));
    }

    /**
     * 1027. 最长等差数列
     *
     * <p>给定一个整数数组 A，返回 A 中最长等差子序列的长度。
     *
     * <p>回想一下，A 的子序列是列表 A[i_1], A[i_2], ..., A[i_k] 其中 0 <= i_1 < i_2 < ... < i_k <= A.length -
     * 1。并且如果 B[i+1] - B[i]( 0 <= i < B.length - 1) 的值都相同，那么序列 B 是等差的。
     *
     * <p>示例 1：
     *
     * <p>输入：[3,6,9,12] 输出：4 解释： 整个数组是公差为 3 的等差数列。 示例 2：
     *
     * <p>输入：[9,4,7,2,10] 输出：3 解释： 最长的等差子序列是 [4,7,10]。 示例 3：
     *
     * <p>输入：[20,1,15,3,10,5,8] 输出：4 解释： 最长的等差子序列是 [20,15,10,5]。
     *
     * <p>提示：
     *
     * <p>2 <= A.length <= 2000 0 <= A[i] <= 10000
     *
     * @param A
     * @return
     */
    public int longestArithSeqLength(int[] A) {
        int max = 0, len = A.length;
        // dp[i][j]为以A[i]和A[j]为最后两个元素的等差数列的长度
        int[][] dp = new int[len][len];
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                dp[i][j] = 2;
            }
        }
        Map<Integer, Integer> indexMap = new HashMap<>();
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                int last = (A[i] << 1) - A[j];
                int index = indexMap.getOrDefault(last, -1);
                if (index != -1) {
                    dp[i][j] = dp[index][i] + 1;
                }
                max = Math.max(max, dp[i][j]);
            }
            indexMap.put(A[i], i);
        }

        return max;
    }

    /**
     * 1039. 多边形三角剖分的最低得分
     *
     * <p>给定 N，想象一个凸 N 边多边形，其顶点按顺时针顺序依次标记为 A[0], A[i], ..., A[N-1]。
     *
     * <p>假设您将多边形剖分为 N-2 个三角形。对于每个三角形，该三角形的值是顶点标记的乘积，三角剖分的分数是进行三角剖分后所有 N-2 个三角形的值之和。
     *
     * <p>返回多边形进行三角剖分后可以得到的最低分。
     *
     * <p>示例 1：
     *
     * <p>输入：[1,2,3] 输出：6 解释：多边形已经三角化，唯一三角形的分数为 6。 示例 2：
     *
     * <p>输入：[3,7,4,5] 输出：144 解释：有两种三角剖分，可能得分分别为：3*7*5 + 4*5*7 = 245，或 3*4*5 + 3*4*7 = 144。最低分数为
     * 144。 示例 3：
     *
     * <p>输入：[1,3,1,4,1,5] 输出：13 解释：最低分数三角剖分的得分情况为 1*1*3 + 1*1*4 + 1*1*5 + 1*1*1 = 13。
     *
     * <p>提示：
     *
     * <p>3 <= A.length <= 50 1 <= A[i] <= 100
     *
     * @param A
     * @return
     */
    public int minScoreTriangulation(int[] A) {
        // dp[i][j] = min(dp[i][j], dp[i][k] + dp[k][j] + A[i] * A[j] * A[k]);
        int len = A.length;
        int[][] dp = new int[len][len];
        for (int i = len - 3; i >= 0; i--) {
            for (int j = i + 2; j < len; j++) {
                for (int k = i + 1; k < j; k++) {
                    if (dp[i][j] == 0) {
                        dp[i][j] = dp[i][k] + dp[k][j] + A[i] * A[j] * A[k];
                    } else {
                        dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k][j] + A[i] * A[j] * A[k]);
                    }
                }
            }
        }
        return dp[0][len - 1];
    }

    /**
     * 1035. 不相交的线
     *
     * <p>我们在两条独立的水平线上按给定的顺序写下 A 和 B 中的整数。
     *
     * <p>现在，我们可以绘制一些连接两个数字 A[i] 和 B[j] 的直线，只要 A[i] == B[j]，且我们绘制的直线不与任何其他连线（非水平线）相交。
     *
     * <p>以这种方法绘制线条，并返回我们可以绘制的最大连线数。
     *
     * <p>示例 1：
     *
     * <p>输入：A = [1,4,2], B = [1,2,4] 输出：2 解释： 我们可以画出两条不交叉的线，如上图所示。 我们无法画出第三条不相交的直线，因为从 A[1]=4 到
     * B[2]=4 的直线将与从 A[2]=2 到 B[1]=2 的直线相交。 示例 2：
     *
     * <p>输入：A = [2,5,1,2,5], B = [10,5,2,1,5,2] 输出：3 示例 3：
     *
     * <p>输入：A = [1,3,7,1,7,5], B = [1,9,2,5,1] 输出：2
     *
     * <p>提示：
     *
     * <p>1 <= A.length <= 500 1 <= B.length <= 500 1 <= A[i], B[i] <= 2000
     *
     * @param A
     * @param B
     * @return
     */
    public int maxUncrossedLines(int[] A, int[] B) {
        // 最长公共子序列
        int len1 = A.length, len2 = B.length;
        int[][] dp = new int[len1 + 1][len2 + 1];
        for (int i = 0; i < len1; i++) {
            for (int j = 0; j < len2; j++) {
                if (A[i] == B[j]) {
                    dp[i + 1][j + 1] = dp[i][j] + 1;
                } else {
                    dp[i + 1][j + 1] = Math.max(dp[i + 1][j], dp[i][j + 1]);
                }
            }
        }

        return dp[len1][len2];
    }

    /**
     * 115. 不同的子序列
     *
     * <p>给定一个字符串 s 和一个字符串 t ，计算在 s 的子序列中 t 出现的个数。
     *
     * <p>字符串的一个 子序列 是指，通过删除一些（也可以不删除）字符且不干扰剩余字符相对位置所组成的新字符串。（例如，"ACE" 是 "ABCDE" 的一个子序列，而 "AEC" 不是）
     *
     * <p>题目数据保证答案符合 32 位带符号整数范围。
     *
     * <p>示例 1：
     *
     * <p>输入：s = "rabbbit", t = "rabbit" 输出：3 解释： 如下图所示, 有 3 种可以从 s 中得到 "rabbit" 的方案。 (上箭头符号 ^
     * 表示选取的字母) rabbbit ^^^^ ^^ rabbbit ^^ ^^^^ rabbbit ^^^ ^^^ 示例 2：
     *
     * <p>输入：s = "babgbag", t = "bag" 输出：5 解释： 如下图所示, 有 5 种可以从 s 中得到 "bag" 的方案。 (上箭头符号 ^ 表示选取的字母)
     * babgbag ^^ ^ babgbag ^^ ^ babgbag ^ ^^ babgbag ^ ^^ babgbag ^^^
     *
     * <p>提示：
     *
     * <p>0 <= s.length, t.length <= 1000 s 和 t 由英文字母组成
     *
     * @param s
     * @param t
     * @return
     */
    public int numDistinct(String s, String t) {
        int len1 = s.length(), len2 = t.length();
        int[][] dp = new int[len1 + 1][len2 + 1];
        for (int i = 0; i <= len1; i++) {
            dp[i][0] = 1;
        }

        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                dp[i][j] = dp[i - 1][j];
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    dp[i][j] += dp[i - 1][j - 1];
                }
            }
        }

        return dp[len1][len2];
    }

    @Test
    public void numDistinct() {
        String s = "rabbbit", t = "rabbit";
        logResult(numDistinct(s, t));
    }

    /**
     * 123. 买卖股票的最佳时机 III
     *
     * <p>给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。
     *
     * <p>设计一个算法来计算你所能获取的最大利润。你最多可以完成 两笔 交易。
     *
     * <p>注意: 你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
     *
     * <p>示例 1:
     *
     * <p>输入: [3,3,5,0,0,3,1,4] 输出: 6 解释: 在第 4 天（股票价格 = 0）的时候买入，在第 6 天（股票价格 = 3）的时候卖出，这笔交易所能获得利润 =
     * 3-0 = 3 。 随后，在第 7 天（股票价格 = 1）的时候买入，在第 8 天 （股票价格 = 4）的时候卖出，这笔交易所能获得利润 = 4-1 = 3 。 示例 2:
     *
     * <p>输入: [1,2,3,4,5] 输出: 4 解释: 在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 =
     * 4 。 注意你不能在第 1 天和第 2 天接连购买股票，之后再将它们卖出。 因为这样属于同时参与了多笔交易，你必须在再次购买前出售掉之前的股票。 示例 3:
     *
     * <p>输入: [7,6,4,3,1] 输出: 0 解释: 在这个情况下, 没有交易完成, 所以最大利润为 0。
     *
     * @param prices
     * @return
     */
    public int maxProfitIII(int[] prices) {
        int max = 0, len = prices.length;
        // dp0：初始化状态
        // dp1：第一次买入
        // dp2：第一次卖出
        // dp3：第二次买入
        // dp4：第二次卖出
        // dp1 = Math.max(dp1,dp0 - prices[i]);
        // dp2 = Math.max(dp2,dp1 + prices[i]);
        // dp3 = Math.max(dp3,dp2 - prices[i]);
        // dp4 = Math.max(dp4,dp3 + prices[i]);

        int dp1 = -prices[0], dp2 = 0, dp3 = -prices[0], dp4 = 0;

        for (int i = 1; i < len; i++) {

            dp4 = Math.max(dp4, dp3 + prices[i]);
            dp3 = Math.max(dp3, dp2 - prices[i]);
            dp2 = Math.max(dp2, dp1 + prices[i]);
            dp1 = Math.max(dp1, -prices[i]);
        }
        // 返回最大值
        return dp4;
    }

    @Test
    public void maxProfitIII() {
        int[] prices = {7, 6, 4, 3, 1};
        logResult(maxProfitIII(prices));
    }

    /**
     * 132. 分割回文串 II
     *
     * <p>给定一个字符串 s，将 s 分割成一些子串，使每个子串都是回文串。
     *
     * <p>返回符合要求的最少分割次数。
     *
     * <p>示例:
     *
     * <p>输入: "aab" 输出: 1 解释: 进行一次分割就可将 s 分割成 ["aa","b"] 这样两个回文子串。
     *
     * @param s
     * @return
     */
    public int minCut(String s) {
        int len = s.length();
        int[] dp = new int[len + 1];
        // 预处理, 记录所有的 回文结束位置
        List<List<Integer>> indexList = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            indexList.add(new ArrayList<>());
        }
        for (int i = 0; i < len - 1; i++) {
            setPalindrome(indexList, s, i - 1, i + 1);
            setPalindrome(indexList, s, i, i + 1);
        }

        for (int i = 0; i < len; i++) {
            dp[i + 1] = dp[i] + 1;
            List<Integer> list = indexList.get(i);
            for (int idx : list) {
                dp[i + 1] = Math.min(dp[i + 1], dp[idx] + 1);
            }
        }

        return dp[len] - 1;
    }

    private void setPalindrome(List<List<Integer>> indexList, String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            indexList.get(right).add(left);
            left--;
            right++;
        }
    }

    @Test
    public void minCut() {
        String s = "aabbaacbbc";
        logResult(minCut(s));
    }

    /**
     * 188. 买卖股票的最佳时机 IV
     *
     * <p>给定一个整数数组 prices ，它的第 i 个元素 prices[i] 是一支给定的股票在第 i 天的价格。
     *
     * <p>设计一个算法来计算你所能获取的最大利润。你最多可以完成 k 笔交易。
     *
     * <p>注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
     *
     * <p>示例 1：
     *
     * <p>输入：k = 2, prices = [2,4,1] 输出：2 解释：在第 1 天 (股票价格 = 2) 的时候买入，在第 2 天 (股票价格 = 4)
     * 的时候卖出，这笔交易所能获得利润 = 4-2 = 2 。 示例 2：
     *
     * <p>输入：k = 2, prices = [3,2,6,5,0,3] 输出：7 解释：在第 2 天 (股票价格 = 2) 的时候买入，在第 3 天 (股票价格 = 6) 的时候卖出,
     * 这笔交易所能获得利润 = 6-2 = 4 。 随后，在第 5 天 (股票价格 = 0) 的时候买入，在第 6 天 (股票价格 = 3) 的时候卖出, 这笔交易所能获得利润 = 3-0 =
     * 3 。
     *
     * <p>提示：
     *
     * <p>0 <= k <= 109 0 <= prices.length <= 1000 0 <= prices[i] <= 1000
     *
     * @param k
     * @param prices
     * @return
     */
    public int maxProfitIV(int k, int[] prices) {
        int len = prices.length;
        if (len < 2) {
            return 0;
        }
        // dp[i][0] 表示  k 次交易的 利润
        // 0 表示买入, 1 表示卖出
        int[][] dp = new int[k + 1][2];
        for (int i = 0; i <= k; i++) {
            dp[i][0] = -prices[0];
        }

        for (int i = 1; i < len; i++) {
            // 第 j 次交易的最大利润
            for (int j = k; j > 0; j--) {

                // 前一天买入 今天卖出
                // 前一天 j 次交易卖出的最大利润, 前一天j 次交易买入 今天卖出的利润
                dp[j][1] = Math.max(dp[j][1], dp[j][0] + prices[i]);
                // 前一天卖出 今天买入
                // 前一天 j 次交易买入的最大利润, 前一天 j - 1 次交易买出 今天买入的利润
                dp[j][0] = Math.max(dp[j][0], dp[j - 1][1] - prices[i]);
            }
        }
        return dp[k][1];
    }
    /*public int maxProfitIV(int k, int[] prices) {
        int len = prices.length;
        if (len < 2) {
            return 0;
        }

        // dp[i][j] 表示第 i 天 k 次交易的 利润
        // 0 表示买入, 1 表示卖出
        int[][][] dp = new int[len][k + 1][2];

        for (int j = 1; j <= k; j++) {
            dp[0][j][0] = -prices[0];
        }

        for (int i = 1; i < len; i++) {
            // 第 j 次交易的最大利润
            for (int j = k; j > 0; j--) {

                // 前一天买入 今天卖出
                // 前一天 j 次交易卖出的最大利润, 前一天j 次交易买入 今天卖出的利润
                dp[i][j][1] = Math.max(dp[i - 1][j][1], dp[i - 1][j][0] + prices[i]);
                // 前一天卖出 今天买入
                // 前一天 j 次交易买入的最大利润, 前一天 j - 1 次交易买出 今天买入的利润
                dp[i][j][0] = Math.max(dp[i - 1][j][0], dp[i - 1][j - 1][1] - prices[i]);
            }
        }
        logResult(dp);
        return dp[len - 1][k][1];
    }*/

    @Test
    public void maxProfitIV() {
        int k = 2;
        int[] prices = {3, 2, 6, 5, 0, 3};
        logResult(maxProfitIV(k, prices));
    }

    /**
     * 403. 青蛙过河
     *
     * <p>一只青蛙想要过河。 假定河流被等分为 x 个单元格，并且在每一个单元格内都有可能放有一石子（也有可能没有）。 青蛙可以跳上石头，但是不可以跳入水中。
     *
     * <p>给定石子的位置列表（用单元格序号升序表示）， 请判定青蛙能否成功过河（即能否在最后一步跳至最后一个石子上）。 开始时，
     * 青蛙默认已站在第一个石子上，并可以假定它第一步只能跳跃一个单位（即只能从单元格1跳至单元格2）。
     *
     * <p>如果青蛙上一步跳跃了 k 个单位，那么它接下来的跳跃距离只能选择为 k - 1、k 或 k + 1个单位。 另请注意，青蛙只能向前方（终点的方向）跳跃。
     *
     * <p>请注意：
     *
     * <p>石子的数量 ≥ 2 且 < 1100； 每一个石子的位置序号都是一个非负整数，且其 < 231； 第一个石子的位置永远是0。 示例 1:
     *
     * <p>[0,1,3,5,6,8,12,17]
     *
     * <p>总共有8个石子。 第一个石子处于序号为0的单元格的位置, 第二个石子处于序号为1的单元格的位置, 第三个石子在序号为3的单元格的位置， 以此定义整个数组...
     * 最后一个石子处于序号为17的单元格的位置。
     *
     * <p>返回 true。即青蛙可以成功过河，按照如下方案跳跃： 跳1个单位到第2块石子, 然后跳2个单位到第3块石子, 接着 跳2个单位到第4块石子, 然后跳3个单位到第6块石子,
     * 跳4个单位到第7块石子, 最后，跳5个单位到第8个石子（即最后一块石子）。 示例 2:
     *
     * <p>[0,1,2,3,4,8,9,11]
     *
     * <p>返回 false。青蛙没有办法过河。 这是因为第5和第6个石子之间的间距太大，没有可选的方案供青蛙跳跃过去。
     *
     * @param stones
     * @return
     */
    public boolean canCross(int[] stones) {
        int len = stones.length;
        Map<Integer, Set<Integer>> stoneMap = new HashMap<>();
        for (int stone : stones) {
            stoneMap.put(stone, new HashSet<>());
        }
        stoneMap.get(0).add(0);

        for (int stone : stones) {
            for (int k : stoneMap.get(stone)) {
                for (int step = k - 1; step <= k + 1; step++) {
                    if (step > 0 && stoneMap.containsKey(stone + step)) {
                        stoneMap.get(stone + step).add(step);
                    }
                }
            }
        }

        return !stoneMap.get(stones[len - 1]).isEmpty();
    }

    /**
     * 446. 等差数列划分 II - 子序列
     *
     * <p>如果一个数列至少有三个元素，并且任意两个相邻元素之差相同，则称该数列为等差数列。
     *
     * <p>例如，以下数列为等差数列:
     *
     * <p>1, 3, 5, 7, 9 7, 7, 7, 7 3, -1, -5, -9 以下数列不是等差数列。
     *
     * <p>1, 1, 2, 5, 7
     *
     * <p>数组 A 包含 N 个数，且索引从 0 开始。该数组子序列将划分为整数序列 (P0, P1, ..., Pk)，满足 0 ≤ P0 < P1 < ... < Pk < N。
     *
     * <p>如果序列 A[P0]，A[P1]，...，A[Pk-1]，A[Pk] 是等差的，那么数组 A 的子序列 (P0，P1，…，PK) 称为等差序列。值得注意的是，这意味着 k ≥ 2。
     *
     * <p>函数要返回数组 A 中所有等差子序列的个数。
     *
     * <p>输入包含 N 个整数。每个整数都在 -231 和 231-1 之间，另外 0 ≤ N ≤ 1000。保证输出小于 231-1。
     *
     * <p>示例：
     *
     * <p>输入：[2, 4, 6, 8, 10]
     *
     * <p>输出：7
     *
     * <p>解释： 所有的等差子序列为： [2,4,6] [4,6,8] [6,8,10] [2,4,6,8] [4,6,8,10] [2,4,6,8,10] [2,6,10]
     *
     * @param A
     * @return
     */
    public int numberOfArithmeticSlicesII(int[] A) {
        int len = A.length;
        // dp[i][j]为以A[i]和A[j]为最后两个元素的等差数列的个数
        int[][] dp = new int[len][len];
        int count = 0;
        Map<Long, List<Integer>> indexMap = new HashMap<>();
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                long last = (((long) A[i]) << 1) - A[j];
                List<Integer> indexList = indexMap.getOrDefault(last, new ArrayList<>());
                for (int index : indexList) {
                    dp[i][j] += dp[index][i] + 1;
                }
                count += dp[i][j];
            }
            List<Integer> list = indexMap.computeIfAbsent((long) A[i], k -> new ArrayList<>());
            list.add(i);
        }
        logResult(dp);

        return count;
    }

    @Test
    public void numberOfArithmeticSlicesII() {
        int[] A = {0, 2000000000, -294967296};
        logResult(numberOfArithmeticSlicesII(A));
    }

    /**
     * 552. 学生出勤记录 II
     *
     * <p>给定一个正整数 n，返回长度为 n 的所有可被视为可奖励的出勤记录的数量。 答案可能非常大，你只需返回结果mod 109 + 7的值。
     *
     * <p>学生出勤记录是只包含以下三个字符的字符串：
     *
     * <p>'A' : Absent，缺勤 'L' : Late，迟到 'P' : Present，到场
     * 如果记录不包含多于一个'A'（缺勤）或超过两个连续的'L'（迟到），则该记录被视为可奖励的。
     *
     * <p>示例 1:
     *
     * <p>输入: n = 2 输出: 8 解释： 有8个长度为2的记录将被视为可奖励： "PP" , "AP", "PA", "LP", "PL", "AL", "LA", "LL"
     * 只有"AA"不会被视为可奖励，因为缺勤次数超过一次。 注意：n 的值不会超过100000。
     *
     * @param n
     * @return
     */
    public int checkRecord(int n) {
        if (n == 1) {
            return 3;
        }
        // LL结尾包含A的数量
        // LL结尾不包含A的数量
        // L结尾包含A的数量
        // L结尾不包含A的数量
        // 其他包含A的数量
        // 其他不包含A的数量
        long dp0 = 0, dp1 = 1, dp2 = 1, dp3 = 1, dp4 = 3, dp5 = 2;
        for (int i = 3; i <= n; i++) {
            long tmp4 = dp4, tmp5 = dp5;
            dp4 += dp0 + dp1 + dp2 + dp3 + dp5;
            dp4 %= MOD;

            dp5 += dp1 + dp3;
            dp5 %= MOD;

            dp0 = dp2;
            dp1 = dp3;
            dp2 = tmp4;
            dp3 = tmp5;
        }

        dp0 += dp1 + dp2 + dp3 + dp4 + dp5;
        dp0 %= MOD;

        return (int) dp0;
    }

    @Test
    public void checkRecord() {
        int n = 101;
        logResult(checkRecord(n));
    }

    /**
     * 639. 解码方法 2
     *
     * <p>一条包含字母 A-Z 的消息通过以下的方式进行了编码：
     *
     * <p>'A' -> 1 'B' -> 2 ... 'Z' -> 26 除了上述的条件以外，现在加密字符串可以包含字符 '*'了，字符'*'可以被当做1到9当中的任意一个数字。
     *
     * <p>给定一条包含数字和字符'*'的加密信息，请确定解码方法的总数。
     *
     * <p>同时，由于结果值可能会相当的大，所以你应当对109 + 7取模。（翻译者标注：此处取模主要是为了防止溢出）
     *
     * <p>示例 1 :
     *
     * <p>输入: "*" 输出: 9 解释: 加密的信息可以被解密为: "A", "B", "C", "D", "E", "F", "G", "H", "I". 示例 2 :
     *
     * <p>输入: "1*" 输出: 9 + 9 = 18（翻译者标注：这里1*可以分解为1,* 或者当做1*来处理，所以结果是9+9=18） 说明 :
     *
     * <p>输入的字符串长度范围是 [1, 105]。 输入的字符串只会包含字符 '*' 和 数字'0' - '9'。
     *
     * @param s
     * @return
     */
    public int numDecodings(String s) {
        int len = s.length();
        long[] dp = new long[len + 1];
        char[] chars = s.toCharArray();
        dp[0] = 1L;
        for (int i = 0; i < len; i++) {
            // * 是 1 到 9
            if (chars[i] == '*') {
                dp[i + 1] += dp[i] * 9;
            } else if (chars[i] == '0') {

                if (i > 0) {
                    if (chars[i - 1] == '*') {
                        dp[i + 1] += dp[i - 1] * 2;
                    } else if (chars[i - 1] == '1' || chars[i - 1] == '2') {
                        dp[i + 1] += dp[i - 1];
                    }
                }
                continue;
            } else {
                dp[i + 1] += dp[i];
            }
            if (i > 0) {

                if (chars[i - 1] == '1') {
                    if (chars[i] == '*') {
                        dp[i + 1] += dp[i - 1] * 9;
                    } else {
                        dp[i + 1] += dp[i - 1];
                    }

                } else if (chars[i - 1] == '2') {
                    if (chars[i] == '*') {
                        dp[i + 1] += dp[i - 1] * 6;
                    } else if (chars[i] <= '6') {
                        dp[i + 1] += dp[i - 1];
                    }
                } else if (chars[i - 1] == '*') {
                    if (chars[i] == '*') {
                        dp[i + 1] += dp[i - 1] * 15;
                    } else if (chars[i] <= '6') {
                        dp[i + 1] += dp[i - 1] * 2;
                    } else {
                        dp[i + 1] += dp[i - 1];
                    }
                }
            }
            dp[i + 1] %= MOD;
        }
        log.debug("dp:{}", dp);
        return (int) dp[len];
    }

    @Test
    public void numDecodings() {
        String s = "**";
        logResult(numDecodings(s));
    }

    /**
     * 600. 不含连续1的非负整数
     *
     * <p>给定一个正整数 n，找出小于或等于 n 的非负整数中，其二进制表示不包含 连续的1 的个数。
     *
     * <p>示例 1:
     *
     * <p>输入: 5 输出: 5
     *
     * <p>解释: 下面是带有相应二进制表示的非负整数<= 5：
     *
     * <p>0 : 0
     *
     * <p>1 : 1
     *
     * <p>2 : 10
     *
     * <p>3 : 11
     *
     * <p>4 : 100
     *
     * <p>5 : 101
     *
     * <p>其中，只有整数3违反规则（有两个连续的1），其他5个满足规则。
     *
     * <p>说明: 1 <= n <= 109
     *
     * @param num
     * @return
     */
    public int findIntegers(int num) {
        // dp[i] 表示 小于1 << i  符合要求的数
        int[] dp = new int[31];
        dp[0] = 1;
        dp[1] = 2;
        for (int i = 2; i < 31; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        int sum = 1;
        /*int m = 1, idx = 0;
        while (m <= num) {
            if ((m & num) != 0) {
                sum += dp[idx];
            }
            idx++;
            m <<= 1;
        }*/
        int m = (1 << 30), idx = 30;
        int preBit = 0;
        while (m > 0) {
            if ((num & m) != 0) {
                sum += dp[idx];
                if (preBit == 1) {
                    sum--;
                    break;
                }
                preBit = 1;

            } else {
                preBit = 0;
            }
            idx--;
            m >>>= 1;
        }

        return sum;
    }

    @Test
    public void findIntegers() {
        // 999999999
        int num = 7;
        logResult(findIntegers(num));
    }

    /**
     * 5627. 石子游戏 VII
     *
     * <p>石子游戏中，爱丽丝和鲍勃轮流进行自己的回合，爱丽丝先开始 。
     *
     * <p>有 n 块石子排成一排。每个玩家的回合中，可以从行中 移除 最左边的石头或最右边的石头，并获得与该行中剩余石头值之 和 相等的得分。当没有石头可移除时，得分较高者获胜。
     *
     * <p>鲍勃发现他总是输掉游戏（可怜的鲍勃，他总是输），所以他决定尽力 减小得分的差值 。爱丽丝的目标是最大限度地 扩大得分的差值 。
     *
     * <p>给你一个整数数组 stones ，其中 stones[i] 表示 从左边开始 的第 i 个石头的值，如果爱丽丝和鲍勃都 发挥出最佳水平 ，请返回他们 得分的差值 。
     *
     * <p>示例 1：
     *
     * <p>输入：stones = [5,3,1,4,2] 输出：6 解释： - 爱丽丝移除 2 ，得分 5 + 3 + 1 + 4 = 13 。游戏情况：爱丽丝 = 13 ，鲍勃 = 0
     * ，石子 = [5,3,1,4] 。 - 鲍勃移除 5 ，得分 3 + 1 + 4 = 8 。游戏情况：爱丽丝 = 13 ，鲍勃 = 8 ，石子 = [3,1,4] 。 - 爱丽丝移除 3
     * ，得分 1 + 4 = 5 。游戏情况：爱丽丝 = 18 ，鲍勃 = 8 ，石子 = [1,4] 。 - 鲍勃移除 1 ，得分 4 。游戏情况：爱丽丝 = 18 ，鲍勃 = 12 ，石子
     * = [4] 。 - 爱丽丝移除 4 ，得分 0 。游戏情况：爱丽丝 = 18 ，鲍勃 = 12 ，石子 = [] 。 得分的差值 18 - 12 = 6 。 示例 2：
     *
     * <p>输入：stones = [7,90,5,1,100,10,10,2] 输出：122
     *
     * <p>提示：
     *
     * <p>n == stones.length 2 <= n <= 1000 1 <= stones[i] <= 1000
     *
     * @param stones
     * @return
     */
    public int stoneGameVII(int[] stones) {
        int len = stones.length;
        int[][] dp = new int[len][len];
        /*for (int i = 0; i < len; i++) {
            dp[i][i] = stones[i];
        }*/
        // dp[i][j] 表示 得分
        for (int i = len - 2; i >= 0; i--) {
            int sum = stones[i];
            for (int j = i + 1; j < len; j++) {
                sum += stones[j];
                dp[i][j] = Math.max(sum - stones[i] - dp[i + 1][j], sum - stones[j] - dp[i][j - 1]);
            }
        }
        logResult(dp);
        return dp[0][len - 1];
    }

    @Test
    public void stoneGameVII() {
        int[] stones = {7, 90, 5, 1, 100, 10, 10, 2};
        logResult(stoneGameVII(stones));
    }

    /**
     * 664. 奇怪的打印机
     *
     * <p>有台奇怪的打印机有以下两个特殊要求：
     *
     * <p>打印机每次只能打印同一个字符序列。 每次可以在任意起始和结束位置打印新字符，并且会覆盖掉原来已有的字符。
     * 给定一个只包含小写英文字母的字符串，你的任务是计算这个打印机打印它需要的最少次数。
     *
     * <p>示例 1:
     *
     * <p>输入: "aaabbb" 输出: 2 解释: 首先打印 "aaa" 然后打印 "bbb"。 示例 2:
     *
     * <p>输入: "aba" 输出: 2 解释: 首先打印 "aaa" 然后在第二个位置打印 "b" 覆盖掉原来的字符 'a'。 提示: 输入字符串的长度不会超过 100。
     *
     * @param s
     * @return
     */
    public int strangePrinter(String s) {
        // dp[i][j]的含义是s[i]到s[j]的打印次数；
        // [i,j]分成两个部分， dp[i,j] = dp[i,k] + dp[k+1,j];
        int len = s.length();
        if (len == 0) {
            return 0;
        }
        int[][] dp = new int[len][len];
        for (int i = 0; i < len; i++) {
            dp[i][i] = 1;
        }

        for (int l = 1; l < len; l++) {
            // 长度 从小到大
            for (int i = 0; i < len - l; i++) {
                int j = i + l;
                dp[i][j] = l + 1;
                for (int k = i; k < j; k++) {
                    int total = dp[i][k] + dp[k + 1][j];
                    if (s.charAt(j) == s.charAt(k)) {
                        total--;
                    }
                    dp[i][j] = Math.min(dp[i][j], total);
                }
            }
        }
        logResult(dp);

        return dp[0][len - 1];
    }

    @Test
    public void strangePrinter() {
        String s = "aaabbb";
        logResult(strangePrinter(s));
    }

    /**
     * 689. 三个无重叠子数组的最大和
     *
     * <p>给定数组 nums 由正整数组成，找到三个互不重叠的子数组的最大和。
     *
     * <p>每个子数组的长度为k，我们要使这3*k个项的和最大化。
     *
     * <p>返回每个区间起始索引的列表（索引从 0 开始）。如果有多个结果，返回字典序最小的一个。
     *
     * <p>示例:
     *
     * <p>输入: [1,2,1,2,6,7,5,1], 2 输出: [0, 3, 5] 解释: 子数组 [1, 2], [2, 6], [7, 5] 对应的起始索引为 [0, 3, 5]。
     * 我们也可以取 [2, 1], 但是结果 [1, 3, 5] 在字典序上更大。 注意:
     *
     * <p>nums.length的范围在[1, 20000]之间。 nums[i]的范围在[1, 65535]之间。 k的范围在[1, floor(nums.length / 3)]之间。
     *
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
        int len = nums.length - k + 1;
        int[] sums = new int[len];
        int sum = 0;
        for (int i = 0; i < k; i++) {
            sum += nums[i];
        }
        sums[0] = sum;
        for (int i = 0; i < len - 1; i++) {
            sum += nums[i + k];
            sum -= nums[i];
            sums[i + 1] = sum;
        }
        log.debug("sums:{}", sums);
        int[] result = new int[] {-1, -1, -1};
        int[] left = new int[len], right = new int[len];
        left[0] = 0;
        for (int i = 1; i < len; i++) {
            int idx = left[i - 1];
            if (sums[i] > sums[idx]) {
                idx = i;
            }
            left[i] = idx;
        }
        log.debug("left:{}", left);
        right[len - 1] = len - 1;
        for (int i = len - 2; i > 0; i--) {
            int idx = right[i + 1];
            if (sums[i] >= sums[idx]) {
                idx = i;
            }
            right[i] = idx;
        }
        log.debug("right:{}", right);
        for (int i = k; i < len - k; i++) {
            int leftIndex = left[i - k], rightIndex = right[i + k];
            if (result[0] == -1
                    || sums[leftIndex] + sums[i] + sums[rightIndex]
                            > sums[result[0]] + sums[result[1]] + sums[result[2]]) {
                result[0] = leftIndex;
                result[1] = i;
                result[2] = rightIndex;
            }
        }

        return result;
    }

    @Test
    public void maxSumOfThreeSubarrays() {
        int[] nums = {1, 2, 1, 2, 1, 2, 1, 2, 1};
        int k = 2;
        log.debug("result:{}", maxSumOfThreeSubarrays(nums, k));
    }

    /**
     * 741. 摘樱桃
     *
     * <p>一个N x N的网格(grid) 代表了一块樱桃地，每个格子由以下三种数字的一种来表示：
     *
     * <p>0 表示这个格子是空的，所以你可以穿过它。 1 表示这个格子里装着一个樱桃，你可以摘到樱桃然后穿过它。 -1 表示这个格子里有荆棘，挡着你的路。
     * 你的任务是在遵守下列规则的情况下，尽可能的摘到最多樱桃：
     *
     * <p>从位置 (0, 0) 出发，最后到达 (N-1, N-1) ，只能向下或向右走，并且只能穿越有效的格子（即只可以穿过值为0或者1的格子）； 当到达 (N-1, N-1)
     * 后，你要继续走，直到返回到 (0, 0) ，只能向上或向左走，并且只能穿越有效的格子； 当你经过一个格子且这个格子包含一个樱桃时，你将摘到樱桃并且这个格子会变成空的（值变为0）； 如果在
     * (0, 0) 和 (N-1, N-1) 之间不存在一条可经过的路径，则没有任何一个樱桃能被摘到。 示例 1:
     *
     * <p>输入: grid = [[0, 1, -1], [1, 0, -1], [1, 1, 1]] 输出: 5 解释：
     * 玩家从（0,0）点出发，经过了向下走，向下走，向右走，向右走，到达了点(2, 2)。 在这趟单程中，总共摘到了4颗樱桃，矩阵变成了[[0,1,-1],[0,0,-1],[0,0,0]]。
     * 接着，这名玩家向左走，向上走，向上走，向左走，返回了起始点，又摘到了1颗樱桃。 在旅程中，总共摘到了5颗樱桃，这是可以摘到的最大值了。 说明:
     *
     * <p>grid 是一个 N * N 的二维数组，N的取值范围是1 <= N <= 50。 每一个 grid[i][j] 都是集合 {-1, 0, 1}其中的一个数。 可以保证起点
     * grid[0][0] 和终点 grid[N-1][N-1] 的值都不会是 -1。
     *
     * @param grid
     * @return
     */
    public int cherryPickup(int[][] grid) {
        int N = grid.length;
        int[][] dp = new int[N + 1][N + 1];
        for (int[] row : dp) {
            Arrays.fill(row, Integer.MIN_VALUE);
        }

        // 动态规划涉及方向，此题倒序来找
        dp[N - 1][N - 1] = grid[N - 1][N - 1];

        // sum表示一共要走的步数，也就是k，通过一个循环递增，来降低一个维度，从而不需要使用三维数组k那一维,
        // 当前走第sum步，一共要走2*N-2步（n-1）*2,下标的话就是2N-3，注意是倒序的
        for (int sum = 2 * N - 3; sum >= 0; sum--) {
            for (int i1 = Math.max(0, sum - N + 1); i1 <= Math.min(N - 1, sum); i1++) {
                for (int i2 = i1; i2 <= Math.min(N - 1, sum); i2++) {
                    // i1、j2的关联：一共要走sum步，sum<2*n，因此起点为Math.max(0,sum-N+1),限定了i1的范围，因此 j1 = sum -i1 =
                    // sum - (sum-n+1) = n-1,也就是当i1取最大，j1的下标也只能为n-1
                    // i2的优化：从i1开始计算，表明第二个人一定走在i1的下面
                    int j1 = sum - i1;
                    int j2 = sum - i2;
                    if (grid[i1][j1] == -1 || grid[i2][j2] == -1) {
                        // 遇到荆棘
                        dp[i1][i2] = Integer.MIN_VALUE;
                    } else {
                        if (i1 != i2 || j1 != j2) {
                            // 不重合在同一个点，则获取的最大值=A的格子+B的格子+AB往哪个方向走，也就是上一个状态是怎么来得，
                            dp[i1][i2] =
                                    grid[i1][j1]
                                            + grid[i2][j2]
                                            + Math.max(
                                                    Math.max(dp[i1][i2 + 1], dp[i1 + 1][i2]),
                                                    Math.max(dp[i1][i2], dp[i1 + 1][i2 + 1]));
                        } else {
                            // 重合在一个点，grid[i1][j1] == grid[i2][j2]，取一个即可，后面是4个方向
                            dp[i1][i2] =
                                    grid[i1][j1]
                                            + Math.max(
                                                    Math.max(dp[i1][i2 + 1], dp[i1 + 1][i2]),
                                                    Math.max(dp[i1][i2], dp[i1 + 1][i2 + 1]));
                        }
                    }
                }
            }
        }
        return Math.max(0, dp[0][0]);
    }

    /**
     * 691. 贴纸拼词
     *
     * <p>我们给出了 N 种不同类型的贴纸。每个贴纸上都有一个小写的英文单词。
     *
     * <p>你希望从自己的贴纸集合中裁剪单个字母并重新排列它们，从而拼写出给定的目标字符串 target。
     *
     * <p>如果你愿意的话，你可以不止一次地使用每一张贴纸，而且每一张贴纸的数量都是无限的。
     *
     * <p>拼出目标 target 所需的最小贴纸数量是多少？如果任务不可能，则返回 -1。
     *
     * <p>示例 1：
     *
     * <p>输入：
     *
     * <p>["with", "example", "science"], "thehat" 输出：
     *
     * <p>3 解释：
     *
     * <p>我们可以使用 2 个 "with" 贴纸，和 1 个 "example" 贴纸。 把贴纸上的字母剪下来并重新排列后，就可以形成目标 “thehat“ 了。
     * 此外，这是形成目标字符串所需的最小贴纸数量。 示例 2：
     *
     * <p>输入：
     *
     * <p>["notice", "possible"], "basicbasic" 输出：
     *
     * <p>-1 解释：
     *
     * <p>我们不能通过剪切给定贴纸的字母来形成目标“basicbasic”。
     *
     * <p>提示：
     *
     * <p>stickers 长度范围是 [1, 50]。 stickers 由小写英文单词组成（不带撇号）。 target 的长度在 [1, 15] 范围内，由小写字母组成。
     * 在所有的测试案例中，所有的单词都是从 1000 个最常见的美国英语单词中随机选取的，目标是两个随机单词的串联。 时间限制可能比平时更具挑战性。预计 50
     * 个贴纸的测试案例平均可在35ms内解决。
     *
     * @param stickers
     * @param target
     * @return
     */
    public int minStickers(String[] stickers, String target) {
        int n = stickers.length, len = target.length();
        // 每个字符 用 0 1 状态表示
        int m = 1 << len;
        int[] dp = new int[m];
        Arrays.fill(dp, -1);
        dp[0] = 0;
        for (String sticker : stickers) {
            for (int status = 0; status < m; status++) {
                if (dp[status] == -1) {
                    continue;
                }
                int curStatus = status;
                for (char c : sticker.toCharArray()) {
                    for (int i = 0; i < target.length(); i++) {
                        if (c == target.charAt(i) && (curStatus & (1 << i)) == 0) {
                            curStatus |= 1 << i;
                            break;
                        }
                    }
                }
                dp[curStatus] =
                        dp[curStatus] == -1
                                ? dp[status] + 1
                                : Math.min(dp[curStatus], dp[status] + 1);
            }
        }

        return dp[m - 1];
    }

    /**
     * 1706. 球会落何处
     *
     * <p>用一个大小为 m x n 的二维网格 grid 表示一个箱子。你有 n 颗球。箱子的顶部和底部都是开着的。
     *
     * <p>箱子中的每个单元格都有一个对角线挡板，跨过单元格的两个角，可以将球导向左侧或者右侧。
     *
     * <p>将球导向右侧的挡板跨过左上角和右下角，在网格中用 1 表示。 将球导向左侧的挡板跨过右上角和左下角，在网格中用 -1 表示。
     * 在箱子每一列的顶端各放一颗球。每颗球都可能卡在箱子里或从底部掉出来。如果球恰好卡在两块挡板之间的 "V" 形图案，或者被一块挡导向到箱子的任意一侧边上，就会卡住。
     *
     * <p>返回一个大小为 n 的数组 answer ，其中 answer[i] 是球放在顶部的第 i 列后从底部掉出来的那一列对应的下标，如果球卡在盒子里，则返回 -1 。
     *
     * <p>示例 1：
     *
     * <p>输入：grid = [[1,1,1,-1,-1],[1,1,1,-1,-1],[-1,-1,-1,1,1],[1,1,1,1,-1],[-1,-1,-1,-1,-1]]
     * 输出：[1,-1,-1,-1,-1] 解释：示例如图： b0 球开始放在第 0 列上，最终从箱子底部第 1 列掉出。 b1 球开始放在第 1 列上，会卡在第 2、3 列和第 1 行之间的
     * "V" 形里。 b2 球开始放在第 2 列上，会卡在第 2、3 列和第 0 行之间的 "V" 形里。 b3 球开始放在第 3 列上，会卡在第 2、3 列和第 0 行之间的 "V" 形里。
     * b4 球开始放在第 4 列上，会卡在第 2、3 列和第 1 行之间的 "V" 形里。 示例 2：
     *
     * <p>输入：grid = [[-1]] 输出：[-1] 解释：球被卡在箱子左侧边上。
     *
     * <p>提示：
     *
     * <p>m == grid.length n == grid[i].length 1 <= m, n <= 100 grid[i][j] 为 1 或 -1
     *
     * @param grid
     * @return
     */
    public int[] findBall(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[] result = new int[n];

        // 默认位置
        for (int i = 0; i < n; i++) {
            result[i] = i;
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (result[j] == -1) {
                    continue;
                }
                // 右移
                if (grid[i][result[i]] == 1 && result[i] + 1 < n && grid[i][result[i] + 1] == 1) {
                    result[j]++;
                } else if (grid[i][result[i]] == -1
                        && result[i] - 1 >= 0
                        && grid[i][result[i] - 1] == -1) {
                    // 左移
                    result[j]--;
                } else {
                    // 卡住
                    result[j] = -1;
                }
            }
        }

        return result;
    }

    /**
     * 730. 统计不同回文子序列
     *
     * <p>给定一个字符串 S，找出 S 中不同的非空回文子序列个数，并返回该数字与 10^9 + 7 的模。
     *
     * <p>通过从 S 中删除 0 个或多个字符来获得子序列。
     *
     * <p>如果一个字符序列与它反转后的字符序列一致，那么它是回文字符序列。
     *
     * <p>如果对于某个 i，A_i != B_i，那么 A_1, A_2, ... 和 B_1, B_2, ... 这两个字符序列是不同的。
     *
     * <p>示例 1：
     *
     * <p>输入： S = 'bccb' 输出：6 解释： 6 个不同的非空回文子字符序列分别为：'b', 'c', 'bb', 'cc', 'bcb', 'bccb'。 注意：'bcb'
     * 虽然出现两次但仅计数一次。 示例 2：
     *
     * <p>输入： S = 'abcdabcdabcdabcdabcdabcdabcdabcddcbadcbadcbadcbadcbadcbadcbadcba' 输出：104860361
     * 解释： 共有 3104860382 个不同的非空回文子序列，对 10^9 + 7 取模为 104860361。
     *
     * <p>提示：
     *
     * <p>字符串 S 的长度将在[1, 1000]范围内。 每个字符 S[i] 将会是集合 {'a', 'b', 'c', 'd'} 中的某一个。
     *
     * @param S
     * @return
     */
    public int countPalindromicSubsequences(String S) {
        int len = S.length();
        int[][] dp = new int[len][len];
        for (int i = 0; i < len; i++) {
            dp[i][i] = 1;
        }
        for (int i = len - 2; i >= 0; i--) {
            for (int j = i + 1; j < len; j++) {
                if (S.charAt(i) != S.charAt(j)) {
                    dp[i][j] = dp[i + 1][j] + dp[i][j - 1] - dp[i + 1][j - 1];
                } else {
                    dp[i][j] = dp[i + 1][j - 1] * 2 + 2;
                    int l = i + 1, r = j - 1;
                    while (l <= r && S.charAt(l) != S.charAt(i)) {
                        l++;
                    }
                    while (l <= r && S.charAt(r) != S.charAt(i)) {
                        r--;
                    }
                    // 第2.1种情况 中间有1个s[i]
                    if (l == r) {
                        dp[i][j]--;
                    }
                    // 第2.2种情况 中间有2个s[i]
                    else if (l < r) {
                        dp[i][j] -= 2 + dp[l + 1][r - 1];
                    }
                }

                dp[i][j] = dp[i][j] >= 0 ? dp[i][j] % MOD : dp[i][j] + MOD;
            }
        }

        return dp[0][len - 1];
    }

    /**
     * 1074. 元素和为目标值的子矩阵数量
     *
     * <p>给出矩阵 matrix 和目标值 target，返回元素总和等于目标值的非空子矩阵的数量。
     *
     * <p>子矩阵 x1, y1, x2, y2 是满足 x1 <= x <= x2 且 y1 <= y <= y2 的所有单元 matrix[x][y] 的集合。
     *
     * <p>如果 (x1, y1, x2, y2) 和 (x1', y1', x2', y2') 两个子矩阵中部分坐标不同（如：x1 != x1'），那么这两个子矩阵也不同。
     *
     * <p>示例 1：
     *
     * <p>输入：matrix = [[0,1,0],[1,1,1],[0,1,0]], target = 0 输出：4
     *
     * <p>解释：四个只含 0 的 1x1 子矩阵。
     *
     * <p>示例 2：
     *
     * <p>输入：matrix = [[1,-1],[-1,1]], target = 0 输出：5
     *
     * <p>解释：两个 1x2 子矩阵，加上两个 2x1 子矩阵，再加上一个 2x2 子矩阵。
     *
     * <p>提示：
     *
     * <p>1 <= matrix.length <= 300 1 <= matrix[0].length <= 300 -1000 <= matrix[i] <= 1000 -10^8 <=
     * target <= 10^8
     *
     * @param matrix
     * @param target
     * @return
     */
    public int numSubmatrixSumTarget(int[][] matrix, int target) {
        int rows = matrix.length, cols = matrix[0].length;

        int[][] sums = new int[rows][cols];

        // 先计算每一行的前缀和
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                sums[i][j] = matrix[i][j];
                if (j > 0) {
                    sums[i][j] += sums[i][j - 1];
                }
            }
        }
        logResult(sums);
        int result = 0;
        Map<Integer, Integer> countMap = new HashMap<>();
        for (int j = 0; j < cols; j++) {
            for (int k = j; k < cols; k++) {
                countMap.clear();
                int temp = 0;
                for (int i = 0; i < rows; i++) {
                    // 代码中最关键的部分，计算扫描线i和j之间的矩阵值
                    temp += sums[i][k] - sums[i][j] + matrix[i][j];

                    // 此矩阵值为target，增加result
                    if (temp == target) {
                        result++;
                    }

                    // 每次是一个矩阵值，mp里面保存着子矩阵值
                    int count = countMap.getOrDefault(temp - target, 0);

                    result += count;
                    count = countMap.getOrDefault(temp, 0);
                    countMap.put(temp, count + 1);
                }
            }
        }

        return result;
    }

    @Test
    public void numSubmatrixSumTarget() {
        int[][] matrix = {
            {0, 1, 0, 0, 1}, {0, 0, 1, 1, 1}, {1, 1, 1, 0, 1}, {1, 1, 0, 1, 1}, {0, 1, 1, 0, 0}
        };
        int target = 1;
        logResult(numSubmatrixSumTarget(matrix, target));
    }

    /**
     * 1092. 最短公共超序列
     *
     * <p>给出两个字符串 str1 和 str2，返回同时以 str1 和 str2 作为子序列的最短字符串。如果答案不止一个，则可以返回满足条件的任意一个答案。
     *
     * <p>（如果从字符串 T 中删除一些字符（也可能不删除，并且选出的这些字符可以位于 T 中的 任意位置），可以得到字符串 S，那么 S 就是 T 的子序列）
     *
     * <p>示例：
     *
     * <p>输入：str1 = "abac", str2 = "cab" 输出："cabac" 解释： str1 = "abac" 是 "cabac" 的一个子串，因为我们可以删去
     * "cabac" 的第一个 "c"得到 "abac"。 str2 = "cab" 是 "cabac" 的一个子串，因为我们可以删去 "cabac" 末尾的 "ac" 得到 "cab"。
     * 最终我们给出的答案是满足上述属性的最短字符串。
     *
     * <p>提示：
     *
     * <p>1 <= str1.length, str2.length <= 1000 str1 和 str2 都由小写英文字母组成。
     *
     * @param str1
     * @param str2
     * @return
     */
    public String shortestCommonSupersequence(String str1, String str2) {
        int len1 = str1.length(), len2 = str2.length();
        int[][] dp = dpSequence(str1, str2);
        logResult(dp);
        StringBuilder sb = new StringBuilder();
        int i = len1, j = len2;
        while (i > 0 || j > 0) {
            char c;
            if (i == 0) {
                c = str2.charAt(--j);
            } else if (j == 0) {
                c = str1.charAt(--i);
            } else if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                c = str1.charAt(i - 1);
                i--;
                j--;
            } else if (dp[i][j - 1] == dp[i][j]) {
                c = str2.charAt(--j);
            } else {
                c = str1.charAt(--i);
            }
            sb.append(c);
        }
        return sb.reverse().toString();
    }

    private int[][] dpSequence(String str1, String str2) {
        int len1 = str1.length(), len2 = str2.length();
        int[][] dp = new int[len1 + 1][len2 + 1];
        for (int i = 0; i < len1; i++) {
            for (int j = 0; j < len2; j++) {
                if (str1.charAt(i) == str2.charAt(j)) {
                    dp[i + 1][j + 1] = dp[i][j] + 1;
                } else {
                    dp[i + 1][j + 1] = Math.max(dp[i][j + 1], dp[i + 1][j]);
                }
            }
        }

        return dp;
    }

    @Test
    public void shortestCommonSupersequence() {
        String str1 = "abac", str2 = "cab";
        logResult(shortestCommonSupersequence(str1, str2));
    }

    /**
     * 1125. 最小的必要团队
     *
     * <p>作为项目经理，你规划了一份需求的技能清单 req_skills，并打算从备选人员名单 people 中选出些人组成一个「必要团队」（ 编号为 i 的备选人员 people[i]
     * 含有一份该备选人员掌握的技能列表）。
     *
     * <p>所谓「必要团队」，就是在这个团队中，对于所需求的技能列表 req_skills 中列出的每项技能，团队中至少有一名成员已经掌握。
     *
     * <p>我们可以用每个人的编号来表示团队中的成员：例如，团队 team = [0, 1, 3] 表示掌握技能分别为 people[0]，people[1]，和 people[3]
     * 的备选人员。
     *
     * <p>请你返回 任一 规模最小的必要团队，团队成员用人员编号表示。你可以按任意顺序返回答案，本题保证答案存在。
     *
     * <p>示例 1： 输入：req_skills = ["java","nodejs","reactjs"], people =
     * [["java"],["nodejs"],["nodejs","reactjs"]] 输出：[0,2]
     *
     * <p>示例 2： 输入：req_skills = ["algorithms","math","java","reactjs","csharp","aws"], people =
     * [["algorithms","math","java"],["algorithms","math","reactjs"],["java","csharp","aws"],["reactjs","csharp"],["csharp","math"],["aws","java"]]
     * 输出：[1,2]
     *
     * <p>提示：
     *
     * <p>1 <= req_skills.length <= 16
     *
     * <p>1 <= people.length <= 60
     *
     * <p>1 <= people[i].length, req_skills[i].length, people[i][j].length <= 16
     *
     * <p>req_skills 和 people[i] 中的元素分别各不相同
     *
     * <p>req_skills[i][j], people[i][j][k] 都由小写英文字母组成
     *
     * <p>本题保证「必要团队」一定存在
     *
     * @param req_skills
     * @param people
     * @return
     */
    public int[] smallestSufficientTeam(String[] req_skills, List<List<String>> people) {

        Map<String, Integer> skillMap = new HashMap<>();

        for (int i = 0; i < req_skills.length; i++) {
            skillMap.put(req_skills[i], 1 << i);
        }

        int size = people.size();
        int[] peopleSkill = new int[size];
        int total = 1 << req_skills.length;
        int full = total - 1;

        for (int i = 0; i < size; i++) {
            List<String> skills = people.get(i);
            int skillNum = 0;
            for (String skill : skills) {
                skillNum |= skillMap.get(skill);
            }
            peopleSkill[i] = skillNum;
        }
        int[][] dp = new int[total][];
        dp[0] = new int[0];
        int maxNum = 0;
        log.debug("peopleSkill:{}", peopleSkill);
        for (int i = 0; i < size; i++) {

            for (int j = 0; j <= maxNum; j++) {
                if (Objects.isNull(dp[j])) {
                    continue;
                }
                int sum = j | peopleSkill[i];
                if (Objects.isNull(dp[sum]) || dp[j].length + 1 < dp[sum].length) {
                    dp[sum] = Arrays.copyOf(dp[j], dp[j].length + 1);

                    dp[sum][dp[j].length] = i;
                }
            }

            maxNum |= peopleSkill[i];
        }
        return dp[full];
    }

    @Test
    public void smallestSufficientTeam() {
        String[] req_skills = {"java", "nodejs", "reactjs"};
        List<List<String>> people = new ArrayList<>();
        people.add(Arrays.asList("java"));
        people.add(Arrays.asList("nodejs"));
        people.add(Arrays.asList("nodejs", "reactjs"));
        int[] result = smallestSufficientTeam(req_skills, people);
        log.debug("result:{}", result);
    }

    /**
     * 1220. 统计元音字母序列的数目
     *
     * <p>给你一个整数 n，请你帮忙统计一下我们可以按下述规则形成多少个长度为 n 的字符串：
     *
     * <p>字符串中的每个字符都应当是小写元音字母（'a', 'e', 'i', 'o', 'u'）
     *
     * <p>每个元音 'a' 后面都只能跟着 'e'
     *
     * <p>每个元音 'e' 后面只能跟着 'a' 或者是 'i'
     *
     * <p>每个元音 'i' 后面 不能 再跟着另一个 'i'
     *
     * <p>每个元音 'o' 后面只能跟着 'i' 或者是 'u'
     *
     * <p>每个元音 'u' 后面只能跟着 'a'
     *
     * <p>由于答案可能会很大，所以请你返回 模 10^9 + 7 之后的结果。
     *
     * <p>示例 1：
     *
     * <p>输入：n = 1 输出：5 解释：所有可能的字符串分别是："a", "e", "i" , "o" 和 "u"。 示例 2：
     *
     * <p>输入：n = 2 输出：10 解释：所有可能的字符串分别是："ae", "ea", "ei", "ia", "ie", "io", "iu", "oi", "ou" 和 "ua"。
     * 示例 3：
     *
     * <p>输入：n = 5 输出：68
     *
     * <p>提示：
     *
     * <p>1 <= n <= 2 * 10^4
     *
     * @param n
     * @return
     */
    public int countVowelPermutation(int n) {
        int[][] dp = new int[n][5];
        Arrays.fill(dp[0], 1);
        // 'a', 'e', 'i', 'o', 'u'
        // 0    1    2    3    4

        for (int i = 1; i < n; i++) {
            // 每个元音 'a' 后面都只能跟着 'e'
            dp[i][1] += dp[i - 1][0];
            dp[i][1] %= MOD;
            // 每个元音 'e' 后面只能跟着 'a' 或者是 'i'
            dp[i][0] += dp[i - 1][1];
            dp[i][0] %= MOD;
            dp[i][2] += dp[i - 1][1];
            dp[i][2] %= MOD;
            // 每个元音 'i' 后面 不能 再跟着另一个 'i'
            for (int j = 0; j < 5; j++) {
                if (j == 2) {
                    continue;
                }
                dp[i][j] += dp[i - 1][2];
                dp[i][j] %= MOD;
            }
            // 每个元音 'o' 后面只能跟着 'i' 或者是 'u'
            dp[i][2] += dp[i - 1][3];
            dp[i][2] %= MOD;
            dp[i][4] += dp[i - 1][3];
            dp[i][4] %= MOD;
            // 每个元音 'u' 后面只能跟着 'a'
            dp[i][0] += dp[i - 1][4];
            dp[i][0] %= MOD;
        }
        int sum = 0;
        for (int num : dp[n - 1]) {
            sum += num;
            sum %= MOD;
        }
        return sum;
    }

    @Test
    public void countVowelPermutation() {
        int n = 158;
        logResult(countVowelPermutation(n));
    }

    /**
     * 1187. 使数组严格递增
     *
     * <p>给你两个整数数组 arr1 和 arr2，返回使 arr1 严格递增所需要的最小「操作」数（可能为 0）。
     *
     * <p>每一步「操作」中，你可以分别从 arr1 和 arr2 中各选出一个索引，分别为 i 和 j，0 <= i < arr1.length 和 0 <= j <
     * arr2.length，然后进行赋值运算 arr1[i] = arr2[j]。
     *
     * <p>如果无法让 arr1 严格递增，请返回 -1。
     *
     * <p>示例 1：
     *
     * <p>输入：arr1 = [1,5,3,6,7], arr2 = [1,3,2,4] 输出：1 解释：用 2 来替换 5，之后 arr1 = [1, 2, 3, 6, 7]。 示例 2：
     *
     * <p>输入：arr1 = [1,5,3,6,7], arr2 = [4,3,1] 输出：2 解释：用 3 来替换 5，然后用 4 来替换 3，得到 arr1 = [1, 3, 4, 6,
     * 7]。 示例 3：
     *
     * <p>输入：arr1 = [1,5,3,6,7], arr2 = [1,6,3,3] 输出：-1 解释：无法使 arr1 严格递增。
     *
     * <p>提示：
     *
     * <p>1 <= arr1.length, arr2.length <= 2000 0 <= arr1[i], arr2[i] <= 10^9
     *
     * @param arr1
     * @param arr2
     * @return
     */
    public int makeArrayIncreasing(int[] arr1, int[] arr2) {
        int len1 = arr1.length;
        if (len1 == 1) {
            return 0;
        }
        TreeSet<Integer> treeSet = new TreeSet<>();
        for (int num : arr2) {
            treeSet.add(num);
        }
        int[][] dp = new int[len1 + 1][len1 + 1];
        for (int i = 0; i <= len1; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        dp[0][0] = Integer.MIN_VALUE;
        // dp[i][j] 表示 将数组arr1的前j个元素通过i次替换后变为严格递增序列时，序列中最后一个元素的最小值,第j个元素的最小值
        for (int j = 1; j <= len1; j++) {
            for (int i = 0; i <= j; i++) {
                if (arr1[j - 1] > dp[i][j - 1]) {
                    dp[i][j] = arr1[j - 1];
                }

                if (i > 0) {
                    Integer highVal = treeSet.higher(dp[i - 1][j - 1]);
                    if (Objects.nonNull(highVal)) {
                        dp[i][j] = Math.min(dp[i][j], highVal);
                    }
                }
                if (j == len1 && dp[i][j] != Integer.MAX_VALUE) {
                    return i;
                }
            }
        }
        return -1;
    }

    /**
     * 1240. 铺瓷砖
     *
     * <p>你是一位施工队的工长，根据设计师的要求准备为一套设计风格独特的房子进行室内装修。
     *
     * <p>房子的客厅大小为 n x m，为保持极简的风格，需要使用尽可能少的 正方形 瓷砖来铺盖地面。
     *
     * <p>假设正方形瓷砖的规格不限，边长都是整数。
     *
     * <p>请你帮设计师计算一下，最少需要用到多少块方形瓷砖？
     *
     * <p>示例 1：
     *
     * <p>输入：n = 2, m = 3 输出：3 解释：3 块地砖就可以铺满卧室。 2 块 1x1 地砖 1 块 2x2 地砖 示例 2：
     *
     * <p>输入：n = 5, m = 8 输出：5 示例 3：
     *
     * <p>输入：n = 11, m = 13 输出：6
     *
     * <p>提示：
     *
     * <p>1 <= n <= 13 1 <= m <= 13
     *
     * @param n
     * @param m
     * @return
     */
    public int tilingRectangle(int n, int m) {
        // 动态规划
        int[][] dp = new int[m + 1][n + 1];

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (i == j) {
                    dp[i][j] = 1;
                    continue;
                }
                dp[i][j] = Integer.MAX_VALUE;
                // 横切
                for (int k = 1; k < i; k++) {
                    dp[i][j] = Math.min(dp[i][j], dp[k][j] + dp[i - k][j]);
                }

                // 竖切
                for (int k = 1; k < j; k++) {
                    dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[i][j - k]);
                }

                // 横竖切
                int min = Math.min(i, j);
                for (int k = 1; k < min; k++) {
                    for (int l = 1; l < k && k + l < i; l++) {
                        log.debug("i:{} j :{} k :{} l :{}", i, j, k, l);

                        dp[i][j] =
                                Math.min(
                                        dp[i][j],
                                        dp[i - k][k - l]
                                                + dp[k + l][j - k]
                                                + dp[i - k - l][j - k + l]
                                                + 2);
                    }
                }
            }
        }

        return dp[m][n];
    }

    @Test
    public void tilingRectangle() {
        int n = 7, m = 4;
        logResult(tilingRectangle(n, m));
    }

    /**
     * 1269. 停在原地的方案数
     *
     * <p>有一个长度为 arrLen 的数组，开始有一个指针在索引 0 处。
     *
     * <p>每一步操作中，你可以将指针向左或向右移动 1 步，或者停在原地（指针不能被移动到数组范围外）。
     *
     * <p>给你两个整数 steps 和 arrLen ，请你计算并返回：在恰好执行 steps 次操作以后，指针仍然指向索引 0 处的方案数。
     *
     * <p>由于答案可能会很大，请返回方案数 模 10^9 + 7 后的结果。
     *
     * <p>示例 1：
     *
     * <p>输入：steps = 3, arrLen = 2 输出：4 解释：3 步后，总共有 4 种不同的方法可以停在索引 0 处。 向右，向左，不动 不动，向右，向左 向右，不动，向左
     * 不动，不动，不动 示例 2：
     *
     * <p>输入：steps = 2, arrLen = 4 输出：2 解释：2 步后，总共有 2 种不同的方法可以停在索引 0 处。 向右，向左 不动，不动 示例 3：
     *
     * <p>输入：steps = 4, arrLen = 2 输出：8
     *
     * <p>提示：
     *
     * <p>1 <= steps <= 500 1 <= arrLen <= 10^6
     *
     * @param steps
     * @param arrLen
     * @return
     */
    public int numWays(int steps, int arrLen) {
        int[][] dp = new int[2][arrLen];
        dp[0][0] = 1;
        int curIdx = 0, lastIdx = 0;
        for (int i = 1; i <= steps; i++) {
            curIdx = i % 2;

            lastIdx = 1 - curIdx;
            for (int j = 0; j < Math.min(steps + 1, arrLen); j++) {
                // 原地不动
                // dp[j] = dp[j];
                dp[curIdx][j] = dp[lastIdx][j];
                // 右移
                if (j > 0) {
                    dp[curIdx][j] += dp[lastIdx][j - 1];
                    dp[curIdx][j] %= MOD;
                }
                // 左移
                if (j < arrLen - 1) {
                    dp[curIdx][j] += dp[lastIdx][j + 1];
                    dp[curIdx][j] %= MOD;
                }
            }
        }
        log.debug("curIdx:{}", curIdx);
        logResult(dp);
        return dp[curIdx][0];
    }

    @Test
    public void numWays() {
        int steps = 4, arrLen = 2;
        logResult(numWays(steps, arrLen));
    }

    /**
     * 1235. 规划兼职工作
     *
     * <p>你打算利用空闲时间来做兼职工作赚些零花钱。
     *
     * <p>这里有 n 份兼职工作，每份工作预计从 startTime[i] 开始到 endTime[i] 结束，报酬为 profit[i]。
     *
     * <p>给你一份兼职工作表，包含开始时间 startTime，结束时间 endTime 和预计报酬 profit 三个数组，请你计算并返回可以获得的最大报酬。
     *
     * <p>注意，时间上出现重叠的 2 份工作不能同时进行。
     *
     * <p>如果你选择的工作在时间 X 结束，那么你可以立刻进行在时间 X 开始的下一份工作。
     *
     * <p>示例 1：
     *
     * <p>输入：startTime = [1,2,3,3], endTime = [3,4,5,6], profit = [50,10,40,70] 输出：120 解释： 我们选出第 1
     * 份和第 4 份工作， 时间范围是 [1-3]+[3-6]，共获得报酬 120 = 50 + 70。 示例 2：
     *
     * <p>输入：startTime = [1,2,3,4,6], endTime = [3,5,10,6,9], profit = [20,20,100,70,60] 输出：150 解释：
     * 我们选择第 1，4，5 份工作。 共获得报酬 150 = 20 + 70 + 60。 示例 3：
     *
     * <p>输入：startTime = [1,1,1], endTime = [2,3,4], profit = [5,6,4] 输出：6
     *
     * <p>提示：
     *
     * <p>1 <= startTime.length == endTime.length == profit.length <= 5 * 10^4 1 <= startTime[i] <
     * endTime[i] <= 10^9 1 <= profit[i] <= 10^4
     *
     * @param startTime
     * @param endTime
     * @param profit
     * @return
     */
    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        int len = startTime.length;
        List<Job> jobList = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            jobList.add(new Job(startTime[i], endTime[i], profit[i]));
        }
        jobList.sort(Comparator.comparingInt(o -> o.end));
        int[] dp = new int[len + 1];
        int maxProfit = 0;
        for (int i = 0; i < len; i++) {
            // 根据start 找 dp的index（二分查找）
            Job job = jobList.get(i);
            int start = job.start;
            // 二分查找
            int index = getEndTimeIndex(jobList, start);
            log.debug("index:{}", index);
            dp[i + 1] = Math.max(dp[i], dp[index] + job.profix);
            // dp[i + 1] ;
            maxProfit = Math.max(maxProfit, dp[i + 1]);
        }

        return maxProfit;
    }

    static class Job {
        int start, end, profix;

        public Job(int start, int end, int profix) {
            this.start = start;
            this.end = end;
            this.profix = profix;
        }
    }

    private int getEndTimeIndex(List<Job> jobList, int start) {
        int low = 0, high = jobList.size() - 1;
        int result = 0;
        while (low < high) {
            int mid = (low + high) >> 1;
            if (jobList.get(mid).end <= start) {
                result = mid;
                low = mid + 1;
            } else {
                high = mid;
            }
        }

        return low;
    }

    @Test
    public void jobScheduling() {
        int[] startTime = {1, 2, 3, 4, 6},
                endTime = {3, 5, 10, 6, 9},
                profit = {20, 20, 100, 70, 60};
        logResult(jobScheduling(startTime, endTime, profit));
    }

    /**
     * LCP 09. 最小跳跃次数
     *
     * <p>为了给刷题的同学一些奖励，力扣团队引入了一个弹簧游戏机。游戏机由 N 个特殊弹簧排成一排，编号为 0 到 N-1。初始有一个小球在编号 0 的弹簧处。若小球在编号为 i
     * 的弹簧处，通过按动弹簧，可以选择把小球向右弹射 jump[i] 的距离，或者向左弹射到任意左侧弹簧的位置。也就是说，在编号为 i 弹簧处按动弹簧，小球可以弹向 0 到 i-1
     * 中任意弹簧或者 i+jump[i] 的弹簧（若 i+jump[i]>=N ，则表示小球弹出了机器）。小球位于编号 0 处的弹簧时不能再向左弹。
     *
     * <p>为了获得奖励，你需要将小球弹出机器。请求出最少需要按动多少次弹簧，可以将小球从编号 0 弹簧弹出整个机器，即向右越过编号 N-1 的弹簧。
     *
     * <p>示例 1：
     *
     * <p>输入：jump = [2, 5, 1, 1, 1, 1]
     *
     * <p>输出：3
     *
     * <p>解释：小 Z 最少需要按动 3 次弹簧，小球依次到达的顺序为 0 -> 2 -> 1 -> 6，最终小球弹出了机器。
     *
     * <p>限制：
     *
     * <p>1 <= jump.length <= 10^6 1 <= jump[i] <= 10000
     *
     * @param jump
     * @return
     */
    public int minJump(int[] jump) {
        int len = jump.length;
        int[] dp = new int[len];
        // 倒过来思考
        dp[len - 1] = 1;
        for (int i = len - 1; i >= 0; i--) {
            // 弹到 右边
            dp[i] = i + jump[i] >= len ? 1 : dp[i + jump[i]] + 1;
            // 更新 右边节点弹到当前i节点
            for (int j = i + 1; j < len && dp[j] > dp[i]; j++) {
                dp[j] = dp[i] + 1;
            }
        }

        return dp[0];
    }

    @Test
    public void minJump() {
        int[] jump = {2, 5, 1, 1, 1, 1};
        logResult(minJump(jump));
    }

    /**
     * 1723. 完成所有工作的最短时间
     *
     * <p>给你一个整数数组 jobs ，其中 jobs[i] 是完成第 i 项工作要花费的时间。
     *
     * <p>请你将这些工作分配给 k 位工人。所有工作都应该分配给工人，且每项工作只能分配给一位工人。工人的 工作时间
     * 是完成分配给他们的所有工作花费时间的总和。请你设计一套最佳的工作分配方案，使工人的 最大工作时间 得以 最小化 。
     *
     * <p>返回分配方案中尽可能 最小 的 最大工作时间 。
     *
     * <p>示例 1：
     *
     * <p>输入：jobs = [3,2,3], k = 3 输出：3 解释：给每位工人分配一项工作，最大工作时间是 3 。 示例 2：
     *
     * <p>输入：jobs = [1,2,4,7,8], k = 2 输出：11 解释：按下述方式分配工作： 1 号工人：1、2、8（工作时间 = 1 + 2 + 8 = 11） 2
     * 号工人：4、7（工作时间 = 4 + 7 = 11） 最大工作时间是 11 。
     *
     * <p>提示：
     *
     * <p>1 <= k <= jobs.length <= 12 1 <= jobs[i] <= 107
     *
     * @param jobs
     * @param k
     * @return
     */
    public int minimumTimeRequired(int[] jobs, int k) {
        int len = jobs.length;
        int max = Arrays.stream(jobs).max().getAsInt();
        if (len <= k) {
            return max;
        }
        // 背包问题
        int maxLen = 1 << len;
        int[] totalTime = new int[maxLen];

        for (int i = 1; i < maxLen; i++) {
            for (int j = 0; j < len; j++) {
                if ((i & (1 << j)) == 0) {
                    continue;
                }
                totalTime[i] += jobs[j];
            }
        }
        log.debug("totalTime:{}", totalTime);

        int[][] dp = new int[k + 1][maxLen];
        for (int i = 0; i <= k; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE / 2);
        }
        dp[0][0] = 0;

        for (int i = 1; i <= k; i++) {
            for (int j = 1; j < maxLen; j++) {
                for (int l = j; l > 0; l = (l - 1) & j) {
                    dp[i][j] = Math.min(dp[i][j], Math.max(dp[i - 1][j ^ l], totalTime[l]));
                }
            }
        }

        return dp[k][maxLen - 1];
    }

    @Test
    public void minimumTimeRequired() {
        int[] jobs = {1, 2, 4, 7, 8};
        int k = 2;
        logResult(minimumTimeRequired(jobs, k));
    }

    /**
     * LCP 25. 古董键盘
     *
     * <p>小扣在秋日市集购买了一个古董键盘。由于古董键盘年久失修，键盘上只有 26 个字母 a~z 可以按下，且每个字母最多仅能被按 k 次。
     *
     * <p>小扣随机按了 n 次按键，请返回小扣总共有可能按出多少种内容。由于数字较大，最终答案需要对 1000000007 (1e9 + 7) 取模。
     *
     * <p>示例 1：
     *
     * <p>输入：k = 1, n = 1
     *
     * <p>输出：26
     *
     * <p>解释：由于只能按一次按键，所有可能的字符串为 "a", "b", ... "z"
     *
     * <p>示例 2：
     *
     * <p>输入：k = 1, n = 2
     *
     * <p>输出：650
     *
     * <p>解释：由于只能按两次按键，且每个键最多只能按一次，所有可能的字符串（按字典序排序）为 "ab", "ac", ... "zy"
     *
     * <p>提示：
     *
     * <p>1 <= k <= 5 1 <= n <= 26*k
     *
     * @param k
     * @param n
     * @return
     */
    public int keyboard(int k, int n) {
        long[][] dp = new long[n + 1][27];
        long[][] C = new long[26 * k + 1][k + 1];
        C[0][0] = 1;
        for (int i = 1; i < C.length; i++) {
            for (int j = 0; j <= Math.min(k, i); j++) {
                if (j == 0) {
                    C[i][j] = 1;
                }
                // 对于j位置：当选它时 C[i - 1][j - 1], 不选j位置C[i - 1][j].
                // 对于j位置分析这有两种情况都有可能因此等于这两种之和。
                else {
                    C[i][j] = C[i - 1][j] + C[i - 1][j - 1];
                }
            }
        }
        // dp[i][j] 表示前面i个位置，考虑了前j种字母组成的i长度的字符串的种数。
        // C[i][x] 表示从i个位置中选出x个位置的组合种数
        for (int i = 0; i <= 26; ++i) {
            dp[0][i] = 1;
        }

        for (int i = 1; i <= n; ++i) {
            for (int j = 1; j <= 26; ++j) {
                for (int x = 0; x <= k && x <= i; ++x) {
                    dp[i][j] += dp[i - x][j - 1] * C[i][x];
                }
                dp[i][j] %= MOD;
            }
        }
        return (int) dp[n][26];
    }

    /**
     * 1531. 压缩字符串 II
     *
     * <p>行程长度编码 是一种常用的字符串压缩方法，它将连续的相同字符（重复 2 次或更多次）替换为字符和表示字符计数的数字（行程长度）。例如，用此方法压缩字符串 "aabccc" ，将
     * "aa" 替换为 "a2" ，"ccc" 替换为` "c3" 。因此压缩后的字符串变为 "a2bc3" 。
     *
     * <p>注意，本问题中，压缩时没有在单个字符后附加计数 '1' 。
     *
     * <p>给你一个字符串 s 和一个整数 k 。你需要从字符串 s 中删除最多 k 个字符，以使 s 的行程长度编码长度最小。
     *
     * <p>请你返回删除最多 k 个字符后，s 行程长度编码的最小长度 。
     *
     * <p>示例 1：
     *
     * <p>输入：s = "aaabcccd", k = 2 输出：4 解释：在不删除任何内容的情况下，压缩后的字符串是 "a3bc3d" ，长度为 6 。最优的方案是删除 'b' 和
     * 'd'，这样一来，压缩后的字符串为 "a3c3" ，长度是 4 。 示例 2：
     *
     * <p>输入：s = "aabbaa", k = 2 输出：2 解释：如果删去两个 'b' 字符，那么压缩后的字符串是长度为 2 的 "a4" 。 示例 3：
     *
     * <p>输入：s = "aaaaaaaaaaa", k = 0 输出：3 解释：由于 k 等于 0 ，不能删去任何字符。压缩后的字符串是 "a11" ，长度为 3 。
     *
     * <p>提示：
     *
     * <p>1 <= s.length <= 100 0 <= k <= s.length s 仅包含小写英文字母
     *
     * @param s
     * @param k
     * @return
     */
    public int getLengthOfOptimalCompression(String s, int k) {
        int n = s.length();
        int[][] dp = new int[n + 1][k + 1];
        for (int[] nums : dp) {
            Arrays.fill(nums, n);
        }
        dp[0][0] = 0;
        for (int i = 1; i <= n; i++) {
            // 前i个字符
            for (int j = 0; j <= i && j <= k; j++) {
                // 删除 j 的 长度
                if (j > 0) {
                    dp[i][j] = dp[i - 1][j - 1];
                }

                int count = 0, del = 0;

                for (int l = i; l >= 1 && del <= j; l--) {
                    if (s.charAt(i - 1) == s.charAt(l - 1)) {
                        count++;
                        dp[i][j] = Math.min(dp[i][j], dp[l - 1][j - del] + getLen(count));
                    } else {
                        del++;
                    }
                }
            }
        }
        logResult(dp);
        return dp[n][k];
    }

    private int getLen(int count) {
        if (count <= 1) {
            return 1;
        } else if (count < 10) {
            return 2;
        } else if (count < 100) {
            return 3;
        }
        return 4;
    }

    @Test
    public void getLengthOfOptimalCompression() {
        String s = "aaabcccd";
        int k = 2;
        logResult(getLengthOfOptimalCompression(s, k));
    }

    /**
     * 1537. 最大得分
     *
     * <p>你有两个 有序 且数组内元素互不相同的数组 nums1 和 nums2 。
     *
     * <p>一条 合法路径 定义如下：
     *
     * <p>选择数组 nums1 或者 nums2 开始遍历（从下标 0 处开始）。 从左到右遍历当前数组。 如果你遇到了 nums1 和 nums2
     * 中都存在的值，那么你可以切换路径到另一个数组对应数字处继续遍历（但在合法路径中重复数字只会被统计一次）。 得分定义为合法路径中不同数字的和。
     *
     * <p>请你返回所有可能合法路径中的最大得分。
     *
     * <p>由于答案可能很大，请你将它对 10^9 + 7 取余后返回。
     *
     * <p>示例 1：
     *
     * <p>输入：nums1 = [2,4,5,8,10], nums2 = [4,6,8,9] 输出：30 解释：合法路径包括： [2,4,5,8,10], [2,4,5,8,9],
     * [2,4,6,8,9], [2,4,6,8,10],（从 nums1 开始遍历） [4,6,8,9], [4,5,8,10], [4,5,8,9], [4,6,8,10] （从
     * nums2 开始遍历） 最大得分为上图中的绿色路径 [2,4,6,8,10] 。 示例 2：
     *
     * <p>输入：nums1 = [1,3,5,7,9], nums2 = [3,5,100] 输出：109 解释：最大得分由路径 [1,3,5,100] 得到。 示例 3：
     *
     * <p>输入：nums1 = [1,2,3,4,5], nums2 = [6,7,8,9,10] 输出：40 解释：nums1 和 nums2 之间无相同数字。 最大得分由路径
     * [6,7,8,9,10] 得到。 示例 4：
     *
     * <p>输入：nums1 = [1,4,5,8,9,11,19], nums2 = [2,3,4,11,12] 输出：61
     *
     * <p>提示：
     *
     * <p>1 <= nums1.length <= 10^5 1 <= nums2.length <= 10^5 1 <= nums1[i], nums2[i] <= 10^7 nums1
     * 和 nums2 都是严格递增的数组。
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public int maxSum(int[] nums1, int[] nums2) {
        // nums1 nums2 是有序的，可以使用双指针，类似归并排序
        int len1 = nums1.length, len2 = nums2.length;
        int i = 0, j = 0;
        long sum1 = 0L, sum2 = 0L;
        while (i < len1 || j < len2) {
            if (i == len1) {
                sum2 += nums2[j++];
                continue;
            }
            if (j == len2) {
                sum1 += nums1[i++];
                continue;
            }

            if (nums1[i] < nums2[j]) {
                sum1 += nums1[i++];
            } else if (nums1[i] > nums2[j]) {
                sum2 += nums2[j++];
            } else {
                // 两个相等
                if (sum1 > sum2) {
                    sum1 += nums1[i];
                    sum2 = sum1;
                } else {
                    sum2 += nums1[i];
                    sum1 = sum2;
                }
                i++;
                j++;
            }
        }

        return (int) (Math.max(sum1, sum2) % MOD);
    }

    /**
     * 1547. 切棍子的最小成本
     *
     * <p>有一根长度为 n 个单位的木棍，棍上从 0 到 n 标记了若干位置。例如，长度为 6 的棍子可以标记如下：
     *
     * <p>给你一个整数数组 cuts ，其中 cuts[i] 表示你需要将棍子切开的位置。
     *
     * <p>你可以按顺序完成切割，也可以根据需要更改切割的顺序。
     *
     * <p>每次切割的成本都是当前要切割的棍子的长度，切棍子的总成本是历次切割成本的总和。对棍子进行切割将会把一根木棍分成两根较小的木棍（这两根木棍的长度和就是切割前木棍的长度）。请参阅第一个示例以获得更直观的解释。
     *
     * <p>返回切棍子的 最小总成本 。
     *
     * <p>示例 1：
     *
     * <p>输入：n = 7, cuts = [1,3,4,5] 输出：16 解释：按 [1, 3, 4, 5] 的顺序切割的情况如下所示：
     *
     * <p>第一次切割长度为 7 的棍子，成本为 7 。第二次切割长度为 6 的棍子（即第一次切割得到的第二根棍子），第三次切割为长度 4 的棍子，最后切割长度为 3 的棍子。总成本为 7 +
     * 6 + 4 + 3 = 20 。 而将切割顺序重新排列为 [3, 5, 1, 4] 后，总成本 = 16（如示例图中 7 + 4 + 3 + 2 = 16）。 示例 2：
     *
     * <p>输入：n = 9, cuts = [5,6,1,4,2] 输出：22 解释：如果按给定的顺序切割，则总成本为 25 。总成本 <= 25 的切割顺序很多，例如，[4, 6, 5,
     * 2, 1] 的总成本 = 22，是所有可能方案中成本最小的。
     *
     * <p>提示：
     *
     * <p>2 <= n <= 10^6 1 <= cuts.length <= min(n - 1, 100) 1 <= cuts[i] <= n - 1 cuts 数组中的所有整数都
     * 互不相同
     *
     * @param n
     * @param cuts
     * @return
     */
    public int minCost(int n, int[] cuts) {

        Arrays.sort(cuts);
        int m = cuts.length;
        int[] newCuts = new int[m + 2];
        System.arraycopy(cuts, 0, newCuts, 1, m);
        newCuts[m + 1] = n;
        int[][] dp = new int[m + 2][m + 2];

        //  dp(0,m + 1) = min {dp(0,i) + dp(i,m + 1) + cuts[m + 1] - cuts[0]}
        for (int i = m; i >= 0; i--) {
            // 枚举区间 [i,j]
            for (int j = i + 2; j <= m + 1; j++) {
                int min = Integer.MAX_VALUE;
                // 枚举中间的分割点
                for (int k = i + 1; k < j; k++) {
                    int num = dp[i][k] + dp[k][j] + newCuts[j] - newCuts[i];
                    min = Math.min(min, num);
                }
                dp[i][j] = min;
            }
        }
        logResult(dp);

        return dp[0][m + 1];
    }

    @Test
    public void minCost() {
        int n = 9;
        int[] cuts = {5, 6, 1, 4, 2};
        logResult(minCost(n, cuts));
    }

    /**
     * 1510. 石子游戏 IV
     *
     * <p>Alice 和 Bob 两个人轮流玩一个游戏，Alice 先手。
     *
     * <p>一开始，有 n 个石子堆在一起。每个人轮流操作，正在操作的玩家可以从石子堆里拿走 任意 非零 平方数 个石子。
     *
     * <p>如果石子堆里没有石子了，则无法操作的玩家输掉游戏。
     *
     * <p>给你正整数 n ，且已知两个人都采取最优策略。如果 Alice 会赢得比赛，那么返回 True ，否则返回 False 。
     *
     * <p>示例 1：
     *
     * <p>输入：n = 1 输出：true 解释：Alice 拿走 1 个石子并赢得胜利，因为 Bob 无法进行任何操作。 示例 2：
     *
     * <p>输入：n = 2 输出：false 解释：Alice 只能拿走 1 个石子，然后 Bob 拿走最后一个石子并赢得胜利（2 -> 1 -> 0）。 示例 3：
     *
     * <p>输入：n = 4 输出：true 解释：n 已经是一个平方数，Alice 可以一次全拿掉 4 个石子并赢得胜利（4 -> 0）。 示例 4：
     *
     * <p>输入：n = 7 输出：false 解释：当 Bob 采取最优策略时，Alice 无法赢得比赛。 如果 Alice 一开始拿走 4 个石子， Bob 会拿走 1 个石子，然后
     * Alice 只能拿走 1 个石子，Bob 拿走最后一个石子并赢得胜利（7 -> 3 -> 2 -> 1 -> 0）。 如果 Alice 一开始拿走 1 个石子， Bob 会拿走 4
     * 个石子，然后 Alice 只能拿走 1 个石子，Bob 拿走最后一个石子并赢得胜利（7 -> 6 -> 2 -> 1 -> 0）。 示例 5：
     *
     * <p>输入：n = 17 输出：false 解释：如果 Bob 采取最优策略，Alice 无法赢得胜利。
     *
     * <p>提示：
     *
     * <p>1 <= n <= 10^5
     *
     * @param n
     * @return
     */
    public boolean winnerSquareGame(int n) {
        // dp[i] 表示 有i个石子是是否必胜
        boolean[] dp = new boolean[n + 1];
        for (int i = 1; i <= n; i++) {

            for (int j = 1; j * j <= i; j++) {
                // 对面必输
                if (!dp[i - j * j]) {
                    dp[i] = true;
                    break;
                }
            }
        }
        log.debug("dp:{}", dp);
        return dp[n];
    }

    @Test
    public void winnerSquareGame() {
        int n = 4;
        logResult(winnerSquareGame(n));
    }

    /**
     * 1563. 石子游戏 V
     *
     * <p>几块石子 排成一行 ，每块石子都有一个关联值，关联值为整数，由数组 stoneValue 给出。
     *
     * <p>游戏中的每一轮：Alice 会将这行石子分成两个 非空行（即，左侧行和右侧行）；Bob 负责计算每一行的值，即此行中所有石子的值的总和。Bob 会丢弃值最大的行，Alice
     * 的得分为剩下那行的值（每轮累加）。如果两行的值相等，Bob 让 Alice 决定丢弃哪一行。下一轮从剩下的那一行开始。
     *
     * <p>只 剩下一块石子 时，游戏结束。Alice 的分数最初为 0 。
     *
     * <p>返回 Alice 能够获得的最大分数 。
     *
     * <p>示例 1：
     *
     * <p>输入：stoneValue = [6,2,3,4,5,5] 输出：18 解释：在第一轮中，Alice 将行划分为 [6，2，3]，[4，5，5] 。左行的值是 11 ，右行的值是
     * 14 。Bob 丢弃了右行，Alice 的分数现在是 11 。 在第二轮中，Alice 将行分成 [6]，[2，3] 。这一次 Bob 扔掉了左行，Alice 的分数变成了 16（11
     * + 5）。 最后一轮 Alice 只能将行分成 [2]，[3] 。Bob 扔掉右行，Alice 的分数现在是 18（16 + 2）。游戏结束，因为这行只剩下一块石头了。 示例 2：
     *
     * <p>输入：stoneValue = [7,7,7,7,7,7,7] 输出：28 示例 3：
     *
     * <p>输入：stoneValue = [4] 输出：0
     *
     * <p>提示：
     *
     * <p>1 <= stoneValue.length <= 500 1 <= stoneValue[i] <= 10^6
     *
     * @param stoneValue
     * @return
     */
    public int stoneGameV(int[] stoneValue) {
        int n = stoneValue.length;
        if (n <= 1) {
            return 0;
        }
        int[] sums = new int[n + 1];
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += stoneValue[i];
            sums[i + 1] = sum;
        }
        int[][] dp = new int[n][n];
        // dp[i][j]代表： 从i到j 区间,Alice分数最大值
        for (int i = n - 2; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {

                // 切割点 k
                for (int k = i; k < j; k++) {
                    int leftDp = dp[i][k], rightDp = dp[k + 1][j];
                    int leftSum = sums[k + 1] - sums[i], rightSum = sums[j + 1] - sums[k + 1];
                    if (leftSum == rightSum) {
                        // Alice 决定丢弃哪一行 选大的
                        int score = leftSum + Math.max(leftDp, rightDp);
                        dp[i][j] = Math.max(dp[i][j], score);
                    } else if (leftSum < rightSum) {
                        dp[i][j] = Math.max(dp[i][j], leftDp + leftSum);
                    } else {
                        dp[i][j] = Math.max(dp[i][j], rightDp + rightSum);
                    }
                }
            }
        }

        return dp[0][n - 1];
    }

    @Test
    public void stoneGameV() {
        int[] stoneValue = {7, 7, 7, 7, 7, 7, 7};
        logResult(stoneGameV(stoneValue));
    }

    /**
     * 1569. 将子数组重新排序得到同一个二叉查找树的方案数
     *
     * <p>给你一个数组 nums 表示 1 到 n 的一个排列。我们按照元素在 nums 中的顺序依次插入一个初始为空的二叉查找树（BST）。请你统计将 nums
     * 重新排序后，统计满足如下条件的方案数：重排后得到的二叉查找树与 nums 原本数字顺序得到的二叉查找树相同。
     *
     * <p>比方说，给你 nums = [2,1,3]，我们得到一棵 2 为根，1 为左孩子，3 为右孩子的树。数组 [2,3,1] 也能得到相同的 BST，但 [3,2,1]
     * 会得到一棵不同的 BST 。
     *
     * <p>请你返回重排 nums 后，与原数组 nums 得到相同二叉查找树的方案数。
     *
     * <p>由于答案可能会很大，请将结果对 10^9 + 7 取余数。
     *
     * <p>示例 1：
     *
     * <p>输入：nums = [2,1,3] 输出：1 解释：我们将 nums 重排， [2,3,1] 能得到相同的 BST 。没有其他得到相同 BST 的方案了。 示例 2：
     *
     * <p>输入：nums = [3,4,5,1,2] 输出：5 解释：下面 5 个数组会得到相同的 BST： [3,1,2,4,5] [3,1,4,2,5] [3,1,4,5,2]
     * [3,4,1,2,5] [3,4,1,5,2] 示例 3：
     *
     * <p>输入：nums = [1,2,3] 输出：0 解释：没有别的排列顺序能得到相同的 BST 。 示例 4：
     *
     * <p>输入：nums = [3,1,2,5,4,6] 输出：19 示例 5：
     *
     * <p>输入：nums = [9,4,2,1,3,6,5,7,8,14,11,10,12,13,16,15,17,18] 输出：216212978 解释：得到相同 BST 的方案数是
     * 3216212999。将它对 10^9 + 7 取余后得到 216212978。
     *
     * <p>提示：
     *
     * <p>1 <= nums.length <= 1000 1 <= nums[i] <= nums.length nums 中所有数 互不相同 。
     *
     * @param nums
     * @return
     */
    public int numOfWays(int[] nums) {
        int n = nums.length;

        initComp(n);
        List<Integer> list = new ArrayList<>();
        for (int num : nums) {
            list.add(num);
        }
        long count = getNumOfTree(list);
        if (count == 0) {
            return MOD - 1;
        }

        return (int) (count - 1);
    }

    long[][] compNum = new long[1010][1010];

    /**
     * 初始化组合数
     *
     * @param n
     */
    private void initComp(int n) {
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= i; j++) {
                if (j == 0) {
                    compNum[i][j] = 1;
                } else {
                    compNum[i][j] = (compNum[i - 1][j] + compNum[i - 1][j - 1]) % MOD;
                }
            }
        }
    }

    private long getNumOfTree(List<Integer> list) {
        if (list.size() <= 1) {
            return 1;
        }
        int root = list.get(0);
        List<Integer> leftList =
                list.stream().filter(num -> num < root).collect(Collectors.toList());
        List<Integer> rightList =
                list.stream().filter(num -> num > root).collect(Collectors.toList());
        int num = Math.min(leftList.size(), rightList.size());
        long comb = compNum[list.size() - 1][num];

        long result = comb * (getNumOfTree(leftList) * getNumOfTree(rightList) % MOD) % MOD;

        return result;
    }

    @Test
    public void numOfWays() {
        int[] nums = {
            31, 23, 14, 24, 15, 12, 25, 28, 5, 35, 17, 6, 9, 11, 1, 27, 18, 20, 2, 3, 33, 10, 13, 4,
            7, 36, 32, 29, 8, 30, 26, 19, 34, 22, 21, 16
        };
        logResult(numOfWays(nums));
    }

    /**
     * 1575. 统计所有可行路径
     *
     * <p>给你一个 互不相同 的整数数组，其中 locations[i] 表示第 i 个城市的位置。同时给你 start，finish 和 fuel
     * 分别表示出发城市、目的地城市和你初始拥有的汽油总量
     *
     * <p>每一步中，如果你在城市 i ，你可以选择任意一个城市 j ，满足 j != i 且 0 <= j < locations.length ，并移动到城市 j 。从城市 i 移动到 j
     * 消耗的汽油量为 |locations[i] - locations[j]|，|x| 表示 x 的绝对值。
     *
     * <p>请注意， fuel 任何时刻都 不能 为负，且你 可以 经过任意城市超过一次（包括 start 和 finish ）。
     *
     * <p>请你返回从 start 到 finish 所有可能路径的数目。
     *
     * <p>由于答案可能很大， 请将它对 10^9 + 7 取余后返回。
     *
     * <p>示例 1：
     *
     * <p>输入：locations = [2,3,6,8,4], start = 1, finish = 3, fuel = 5 输出：4 解释：以下为所有可能路径，每一条都用了 5
     * 单位的汽油： 1 -> 3 1 -> 2 -> 3 1 -> 4 -> 3 1 -> 4 -> 2 -> 3 示例 2：
     *
     * <p>输入：locations = [4,3,1], start = 1, finish = 0, fuel = 6 输出：5 解释：以下为所有可能的路径： 1 -> 0，使用汽油量为
     * fuel = 1 1 -> 2 -> 0，使用汽油量为 fuel = 5 1 -> 2 -> 1 -> 0，使用汽油量为 fuel = 5 1 -> 0 -> 1 -> 0，使用汽油量为
     * fuel = 3 1 -> 0 -> 1 -> 0 -> 1 -> 0，使用汽油量为 fuel = 5 示例 3：
     *
     * <p>输入：locations = [5,2,1], start = 0, finish = 2, fuel = 3 输出：0 解释：没有办法只用 3 单位的汽油从 0 到达 2
     * 。因为最短路径需要 4 单位的汽油。 示例 4 ：
     *
     * <p>输入：locations = [2,1,5], start = 0, finish = 0, fuel = 3 输出：2 解释：总共有两条可行路径，0 和 0 -> 1 -> 0
     * 。 示例 5：
     *
     * <p>输入：locations = [1,2,3], start = 0, finish = 2, fuel = 40 输出：615088286 解释：路径总数为 2615088300
     * 。将结果对 10^9 + 7 取余，得到 615088286 。
     *
     * <p>提示：
     *
     * <p>2 <= locations.length <= 100 1 <= locations[i] <= 10^9 所有 locations 中的整数 互不相同 。 0 <=
     * start, finish < locations.length 1 <= fuel <= 200
     *
     * @param locations
     * @param start
     * @param finish
     * @param fuel
     * @return
     */
    public int countRoutes(int[] locations, int start, int finish, int fuel) {
        int n = locations.length;
        // dp[i][k]表示花正好k的油到达i点的路径数
        int[][] dp = new int[n][fuel + 1];
        dp[start][0] = 1;
        for (int k = 0; k < fuel; k++) {

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (i == j) {
                        continue;
                    }
                    int useFuel = Math.abs(locations[i] - locations[j]);
                    if (k + useFuel > fuel) {
                        continue;
                    }
                    dp[j][k + useFuel] += dp[i][k];
                    dp[j][k + useFuel] %= MOD;
                }
            }
        }
        int result = 0;
        for (int k = 0; k <= fuel; k++) {
            result += dp[finish][k];
            result %= MOD;
        }

        return result;
    }

    /**
     * 1595. 连通两组点的最小成本
     *
     * <p>给你两组点，其中第一组中有 size1 个点，第二组中有 size2 个点，且 size1 >= size2 。
     *
     * <p>任意两点间的连接成本 cost 由大小为 size1 x size2 矩阵给出，其中 cost[i][j] 是第一组中的点 i 和第二组中的点 j
     * 的连接成本。如果两个组中的每个点都与另一组中的一个或多个点连接，则称这两组点是连通的。换言之，第一组中的每个点必须至少与第二组中的一个点连接，且第二组中的每个点必须至少与第一组中的一个点连接。
     *
     * <p>返回连通两组点所需的最小成本。
     *
     * <p>示例 1：
     *
     * <p>输入：cost = [[15, 96], [36, 2]] 输出：17 解释：连通两组点的最佳方法是： 1--A 2--B 总成本为 17 。 示例 2：
     *
     * <p>输入：cost = [[1, 3, 5], [4, 1, 1], [1, 5, 3]] 输出：4 解释：连通两组点的最佳方法是： 1--A 2--B 2--C 3--A 最小成本为
     * 4 。 请注意，虽然有多个点连接到第一组中的点 2 和第二组中的点 A ，但由于题目并不限制连接点的数目，所以只需要关心最低总成本。 示例 3：
     *
     * <p>输入：cost = [[2, 5, 1], [3, 4, 7], [8, 1, 2], [6, 2, 4], [3, 8, 8]] 输出：10
     *
     * <p>提示：
     *
     * <p>size1 == cost.length size2 == cost[i].length 1 <= size1, size2 <= 12 size1 >= size2 0 <=
     * cost[i][j] <= 100
     *
     * @param cost
     * @return
     */
    public int connectTwoGroups(List<List<Integer>> cost) {
        int m = cost.size(), n = cost.get(0).size();
        // 1 <= size1, size2 <= 12

        int[][] costSum = new int[m][1 << n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < 1 << n; j++) {
                int costNum = 0;
                for (int k = 0; k < n; k++) {
                    // 选择了 第二组 第k个点
                    if ((j & (1 << k)) > 0) {
                        costNum += cost.get(i).get(k);
                    }
                }
                costSum[i][j] = costNum;
            }
        }
        logResult(costSum);

        log.debug("11");
        // dp[i][j]表示当前选取到第 i 行, 每列的选取状况为 jj 时总的最小开销
        // dp[i][j ∣ k]=Math.min(dp[i][j ∣ k],dp[i−1][k]+costSum[i][j])
        int[][] dp = new int[m][1 << n];
        for (int i = 1; i < m; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        dp[0] = costSum[0];
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < 1 << n; j++) {
                // 第i行至少要选择一个元素
                for (int k = 0; k < n; k++) {
                    dp[i][j | (1 << k)] =
                            Math.min(dp[i][j | (1 << k)], dp[i - 1][j] + cost.get(i).get(k));
                }
                int rest = (1 << n) - 1 - j;
                // 原本是0的位始终为0，即只遍历没选过的列的所有组合即可
                for (int k = rest; k >= 1; k = rest & (k - 1)) {
                    dp[i][j | k] = Math.min(dp[i][j | k], dp[i - 1][j] + costSum[i][k]);
                }
            }
        }

        logResult(dp);

        return dp[m - 1][(1 << n) - 1];
    }

    @Test
    public void connectTwoGroups() {
        List<List<Integer>> cost = new ArrayList<>();
        cost.add(Arrays.asList(15, 96));
        cost.add(Arrays.asList(36, 2));
        logResult(connectTwoGroups(cost));
    }

    /**
     * 1340. 跳跃游戏 V
     *
     * <p>给你一个整数数组 arr 和一个整数 d 。每一步你可以从下标 i 跳到：
     *
     * <p>i + x ，其中 i + x < arr.length 且 0 < x <= d 。 i - x ，其中 i - x >= 0 且 0 < x <= d 。 除此以外，你从下标
     * i 跳到下标 j 需要满足：arr[i] > arr[j] 且 arr[i] > arr[k] ，其中下标 k 是所有 i 到 j 之间的数字（更正式的，min(i, j) < k <
     * max(i, j)）。
     *
     * <p>你可以选择数组的任意下标开始跳跃。请你返回你 最多 可以访问多少个下标。
     *
     * <p>请注意，任何时刻你都不能跳到数组的外面。
     *
     * <p>示例 1：
     *
     * <p>输入：arr = [6,4,14,6,8,13,9,7,10,6,12], d = 2 输出：4 解释：你可以从下标 10 出发，然后如上图依次经过 10 --> 8 --> 6
     * --> 7 。 注意，如果你从下标 6 开始，你只能跳到下标 7 处。你不能跳到下标 5 处因为 13 > 9 。你也不能跳到下标 4 处，因为下标 5 在下标 4 和 6 之间且 13
     * > 9 。 类似的，你不能从下标 3 处跳到下标 2 或者下标 1 处。 示例 2：
     *
     * <p>输入：arr = [3,3,3,3,3], d = 3 输出：1 解释：你可以从任意下标处开始且你永远无法跳到任何其他坐标。 示例 3：
     *
     * <p>输入：arr = [7,6,5,4,3,2,1], d = 1 输出：7 解释：从下标 0 处开始，你可以按照数值从大到小，访问所有的下标。 示例 4：
     *
     * <p>输入：arr = [7,1,7,1,7,1], d = 2 输出：2 示例 5：
     *
     * <p>输入：arr = [66], d = 1 输出：1
     *
     * <p>提示：
     *
     * <p>1 <= arr.length <= 1000 1 <= arr[i] <= 10^5 1 <= d <= arr.length
     *
     * @param arr
     * @param d
     * @return
     */
    public int maxJumps(int[] arr, int d) {

        int len = arr.length;
        int max = 0;
        int[] dp = new int[len];
        for (int i = 0; i < len; i++) {
            max = Math.max(max, dpJump(dp, arr, d, i));
        }
        return max;
    }

    private int dpJump(int[] dp, int[] arr, int d, int idx) {
        if (dp[idx] != 0) {
            return dp[idx];
        }
        dp[idx] = 1;
        // 向右跳
        for (int i = idx + 1; i < arr.length && i <= idx + d; i++) {
            if (arr[i] >= arr[idx]) {
                break;
            }
            dp[idx] = Math.max(dp[idx], dpJump(dp, arr, d, i) + 1);
        }

        // 向左跳
        for (int i = idx - 1; i >= 0 && i >= idx - d; i--) {
            if (arr[i] >= arr[idx]) {
                break;
            }
            dp[idx] = Math.max(dp[idx], dpJump(dp, arr, d, i) + 1);
        }

        return dp[idx];
    }

    /**
     * 818. 赛车
     *
     * <p>你的赛车起始停留在位置 0，速度为 +1，正行驶在一个无限长的数轴上。（车也可以向负数方向行驶。）
     *
     * <p>你的车会根据一系列由 A（加速）和 R（倒车）组成的指令进行自动驾驶 。
     *
     * <p>当车得到指令 "A" 时, 将会做出以下操作： position += speed, speed *= 2。
     *
     * <p>当车得到指令 "R" 时, 将会做出以下操作：如果当前速度是正数，则将车速调整为 speed = -1 ；否则将车速调整为 speed = 1。 (当前所处位置不变。)
     *
     * <p>例如，当得到一系列指令 "AAR" 后, 你的车将会走过位置 0->1->3->3，并且速度变化为 1->2->4->-1。
     *
     * <p>现在给定一个目标位置，请给出能够到达目标位置的最短指令列表的长度。
     *
     * <p>示例 1: 输入: target = 3 输出: 2 解释: 最短指令列表为 "AA" 位置变化为 0->1->3 示例 2: 输入: target = 6 输出: 5 解释:
     * 最短指令列表为 "AAARA" 位置变化为 0->1->3->7->7->6 说明:
     *
     * <p>1 <= target（目标位置） <= 10000。
     *
     * @param target
     * @return
     */
    public int racecar(int target) {
        int[] dp = new int[target + 3];
        // dp[1] => A
        // dp[2] = dp[3] + dp[1] => AARA
        // dp[3] = 2^2 - 1 => AA
        // dp[7] = 2^3 - 1 => AAA
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 4;
        int k = 2;

        for (int i = 3; i <= target; i++) {
            // i ==  2 ^ k - 1
            if ((i & (i + 1)) == 0) {
                // 全是1
                dp[i] = k++;
                continue;
            }
            // 计算中间值 两种
            //  1 回退 走到 1<<k - 1 回退
            dp[i] = k + 1 + dp[(1 << k) - 1 - i];

            // 2 连续k-1个A后，回退j步后，再前进
            for (int j = 0; j < k - 1; j++) {
                // 回退后还需前进的距离：i+S(back)-S(k-1)
                int distance = i + (1 << j) - (1 << (k - 1));
                // 两个 R
                dp[i] = Math.min(dp[i], k - 1 + j + 2 + dp[distance]);
            }
        }
        log.debug("dp:{}", dp);
        return dp[target];
    }

    @Test
    public void racecar() {
        int target = 6;
        logResult(racecar(target));
    }

    /**
     * 879. 盈利计划
     *
     * <p>集团里有 n 名员工，他们可以完成各种各样的工作创造利润。
     *
     * <p>第 i 种工作会产生 profit[i] 的利润，它要求 group[i] 名成员共同参与。如果成员参与了其中一项工作，就不能参与另一项工作。
     *
     * <p>工作的任何至少产生 minProfit 利润的子集称为盈利计划。并且工作的成员总数最多为 n 。
     *
     * <p>有多少种计划可以选择？因为答案很大，所以 返回结果模 10^9 + 7 的值。
     *
     * <p>示例 1：
     *
     * <p>输入：n = 5, minProfit = 3, group = [2,2], profit = [2,3] 输出：2 解释：至少产生 3 的利润，该集团可以完成工作 0 和工作
     * 1 ，或仅完成工作 1 。 总的来说，有两种计划。 示例 2：
     *
     * <p>输入：n = 10, minProfit = 5, group = [2,3,5], profit = [6,7,8] 输出：7 解释：至少产生 5
     * 的利润，只要完成其中一种工作就行，所以该集团可以完成任何工作。 有 7 种可能的计划：(0)，(1)，(2)，(0,1)，(0,2)，(1,2)，以及 (0,1,2) 。
     *
     * <p>提示：
     *
     * <p>1 <= n <= 100 0 <= minProfit <= 100 1 <= group.length <= 100 1 <= group[i] <= 100
     * profit.length == group.length 0 <= profit[i] <= 100
     *
     * @param n
     * @param minProfit
     * @param group
     * @param profit
     * @return
     */
    public int profitableSchemes(int n, int minProfit, int[] group, int[] profit) {
        int len = group.length;
        // 背包问题
        // dp [i][j] = 表示 i个员工 获取 j 利润 的 计划
        int[][] dp = new int[n + 1][minProfit + 1];
        dp[0][0] = 1;

        for (int k = 0; k < len; k++) {
            // 员工数 利润
            int groupNum = group[k], profitNum = profit[k];
            for (int i = n; i >= groupNum; i--) {
                for (int j = minProfit; j >= 0; j--) {
                    // 达到 j 利润 需要的利润 为 j - profitNum
                    int lastProfit = Math.max(j - profitNum, 0);
                    // i - groupNum 个 员工
                    int lastNum = i - groupNum;

                    dp[i][j] += dp[lastNum][lastProfit];
                    dp[i][j] %= MOD;
                }
            }
        }
        logResult(dp);
        // 所有 利润达到 minProfit 的 计划
        int result = 0;
        for (int i = 0; i <= n; i++) {
            result += dp[i][minProfit];
            result %= MOD;
        }
        return result;
    }

    @Test
    public void profitableSchemes() {
        int n = 10, minProfit = 5;
        int[] group = {2, 3, 5}, profit = {6, 7, 8};
        logResult(profitableSchemes(n, minProfit, group, profit));
    }

    /**
     * 902. 最大为 N 的数字组合
     *
     * <p>我们有一组排序的数字 D，它是 {'1','2','3','4','5','6','7','8','9'} 的非空子集。（请注意，'0' 不包括在内。）
     *
     * <p>现在，我们用这些数字进行组合写数字，想用多少次就用多少次。例如 D = {'1','3','5'}，我们可以写出像 '13', '551', '1351315' 这样的数字。
     *
     * <p>返回可以用 D 中的数字写出的小于或等于 N 的正整数的数目。
     *
     * <p>示例 1：
     *
     * <p>输入：D = ["1","3","5","7"], N = 100 输出：20 解释： 可写出的 20 个数字是： 1, 3, 5, 7, 11, 13, 15, 17, 31,
     * 33, 35, 37, 51, 53, 55, 57, 71, 73, 75, 77. 示例 2：
     *
     * <p>输入：D = ["1","4","9"], N = 1000000000 输出：29523 解释： 我们可以写 3 个一位数字，9 个两位数字，27 个三位数字， 81
     * 个四位数字，243 个五位数字，729 个六位数字， 2187 个七位数字，6561 个八位数字和 19683 个九位数字。 总共，可以使用D中的数字写出 29523 个整数。
     *
     * <p>提示：
     *
     * <p>D 是按排序顺序的数字 '1'-'9' 的子集。 1 <= N <= 10^9
     *
     * @param digits
     * @param n
     * @return
     */
    public int atMostNGivenDigitSet(String[] digits, int n) {
        String s = String.valueOf(n);
        int len = s.length();
        int[] dp = new int[len + 1];
        dp[0] = 1;

        for (int i = 0; i < len; i++) {
            int num = s.charAt(len - i - 1) - '0';
            for (String d : digits) {
                if (Integer.valueOf(d) < num) {
                    dp[i + 1] += Math.pow(digits.length, i);
                } else if (Integer.valueOf(d) == num) {
                    dp[i + 1] += dp[i];
                }
            }
        }

        for (int i = 1; i < len; ++i) {
            dp[len] += Math.pow(digits.length, i);
        }
        log.debug("dp:{}", dp);
        return dp[len];
    }

    @Test
    public void atMostNGivenDigitSet() {
        String[] digits = {"1", "4", "9"};
        int n = 1000000000;
        logResult(atMostNGivenDigitSet(digits, n));
    }

    /**
     * 903. DI 序列的有效排列
     *
     * <p>我们给出 S，一个源于 {'D', 'I'} 的长度为 n 的字符串 。（这些字母代表 “减少” 和 “增加”。） 有效排列 是对整数 {0, 1, ..., n} 的一个排列
     * P[0], P[1], ..., P[n]，使得对所有的 i：
     *
     * <p>如果 S[i] == 'D'，那么 P[i] > P[i+1]，以及； 如果 S[i] == 'I'，那么 P[i] < P[i+1]。
     * 有多少个有效排列？因为答案可能很大，所以请返回你的答案模 10^9 + 7.
     *
     * <p>示例：
     *
     * <p>输入："DID" 输出：5 解释： (0, 1, 2, 3) 的五个有效排列是： (1, 0, 3, 2) (2, 0, 3, 1) (2, 1, 3, 0) (3, 0, 2,
     * 1) (3, 1, 2, 0)
     *
     * <p>提示：
     *
     * <p>1 <= S.length <= 200 S 仅由集合 {'D', 'I'} 中的字符组成。
     *
     * @param S
     * @return
     */
    public int numPermsDISequence(String S) {
        int n = S.length();
        int[][] dp = new int[n + 1][n + 1];
        dp[0][0] = 1;

        for (int i = 1; i <= n; i++) {
            char c = S.charAt(i - 1);
            if (c == 'D') {
                for (int j = i - 1; j >= 0; j--) {
                    dp[i][j] = dp[i][j + 1] + dp[i - 1][j];
                    dp[i][j] %= MOD;
                }
            } else {
                for (int j = 1; j <= i; j++) {
                    dp[i][j] = dp[i][j - 1] + dp[i - 1][j - 1];
                    dp[i][j] %= MOD;
                }
            }
        }

        int result = 0;
        for (int num : dp[n]) {
            result += num;
            result %= MOD;
        }
        return result;
    }

    /**
     * 1799. N 次操作后的最大分数和
     *
     * <p>给你 nums ，它是一个大小为 2 * n 的正整数数组。你必须对这个数组执行 n 次操作。
     *
     * <p>在第 i 次操作时（操作编号从 1 开始），你需要：
     *
     * <p>选择两个元素 x 和 y 。 获得分数 i * gcd(x, y) 。 将 x 和 y 从 nums 中删除。 请你返回 n 次操作后你能获得的分数和最大为多少。
     *
     * <p>函数 gcd(x, y) 是 x 和 y 的最大公约数。
     *
     * <p>示例 1：
     *
     * <p>输入：nums = [1,2] 输出：1 解释：最优操作是： (1 * gcd(1, 2)) = 1 示例 2：
     *
     * <p>输入：nums = [3,4,6,8] 输出：11 解释：最优操作是： (1 * gcd(3, 6)) + (2 * gcd(4, 8)) = 3 + 8 = 11 示例 3：
     *
     * <p>输入：nums = [1,2,3,4,5,6] 输出：14 解释：最优操作是： (1 * gcd(1, 5)) + (2 * gcd(2, 4)) + (3 * gcd(3,
     * 6)) = 1 + 4 + 9 = 14
     *
     * <p>提示：
     *
     * <p>1 <= n <= 7 nums.length == 2 * n 1 <= nums[i] <= 106
     *
     * @param nums
     * @return
     */
    public int maxScore(int[] nums) {
        int len = nums.length;
        int[] dp = new int[1 << len];
        int[][] gcdNums = new int[len][len];

        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                gcdNums[i][j] = MathUtils.getGcd(nums[i], nums[j]);
            }
        }

        for (int i = 0; i < 1 << len; i++) {
            int bitCount = Integer.bitCount(i);
            if ((bitCount & 1) == 1) {
                // 不能为奇数
                continue;
            }
            // 操作编号 分数
            int score = bitCount >> 1;
            for (int j = 0; j < len; j++) {
                for (int k = j + 1; k < len; k++) {
                    int curStatus = (1 << j) | (1 << k);
                    if ((curStatus & i) == curStatus) {
                        dp[i] = Math.max(dp[i], dp[i ^ curStatus] + gcdNums[j][k] * score);
                    }
                }
            }
        }

        return dp[(1 << len) - 1];
    }

    /**
     * 5728. 最少侧跳次数
     *
     * <p>给你一个长度为 n 的 3 跑道道路 ，它总共包含 n + 1 个 点 ，编号为 0 到 n 。一只青蛙从 0 号点第二条跑道 出发 ，它想要跳到点 n
     * 处。然而道路上可能有一些障碍。
     *
     * <p>给你一个长度为 n + 1 的数组 obstacles ，其中 obstacles[i] （取值范围从 0 到 3）表示在点 i 处的 obstacles[i]
     * 跑道上有一个障碍。如果 obstacles[i] == 0 ，那么点 i 处没有障碍。任何一个点的三条跑道中 最多有一个 障碍。
     *
     * <p>比方说，如果 obstacles[2] == 1 ，那么说明在点 2 处跑道 1 有障碍。 这只青蛙从点 i 跳到点 i + 1 且跑道不变的前提是点 i + 1
     * 的同一跑道上没有障碍。为了躲避障碍，这只青蛙也可以在 同一个 点处 侧跳 到 另外一条 跑道（这两条跑道可以不相邻），但前提是跳过去的跑道该点处没有障碍。
     *
     * <p>比方说，这只青蛙可以从点 3 处的跑道 3 跳到点 3 处的跑道 1 。 这只青蛙从点 0 处跑道 2 出发，并想到达点 n 处的 任一跑道 ，请你返回 最少侧跳次数 。
     *
     * <p>注意：点 0 处和点 n 处的任一跑道都不会有障碍。
     *
     * <p>示例 1：
     *
     * <p>输入：obstacles = [0,1,2,3,0] 输出：2 解释：最优方案如上图箭头所示。总共有 2 次侧跳（红色箭头）。 注意，这只青蛙只有当侧跳时才可以跳过障碍（如上图点
     * 2 处所示）。 示例 2：
     *
     * <p>输入：obstacles = [0,1,1,3,3,0] 输出：0 解释：跑道 2 没有任何障碍，所以不需要任何侧跳。 示例 3：
     *
     * <p>输入：obstacles = [0,2,1,0,3,0] 输出：2 解释：最优方案如上图所示。总共有 2 次侧跳。
     *
     * <p>提示：
     *
     * <p>obstacles.length == n + 1 1 <= n <= 5 * 105 0 <= obstacles[i] <= 3 obstacles[0] ==
     * obstacles[n] == 0
     *
     * @param obstacles
     * @return
     */
    public int minSideJumps(int[] obstacles) {
        int len = obstacles.length;
        int[][] dp = new int[len][3];

        dp[0][1] = 0;
        dp[0][0] = 1;
        dp[0][2] = 1;

        for (int i = 1; i < len; i++) {
            int idx = obstacles[i];
            for (int j = 0; j < 3; j++) {
                dp[i][j] = dp[i - 1][j];
            }
            if (idx > 0) {
                dp[i][idx - 1] = Integer.MAX_VALUE >> 1;
            }
            // 上路
            if (idx != 1) {
                dp[i][0] = Math.min(dp[i - 1][0], Math.min(dp[i][1], dp[i][2]) + 1);
            }

            // 中路
            if (idx != 2) {
                dp[i][1] = Math.min(dp[i - 1][1], Math.min(dp[i][0], dp[i][2]) + 1);
            }

            // 下路
            if (idx != 3) {
                dp[i][2] = Math.min(dp[i - 1][2], Math.min(dp[i][0], dp[i][1]) + 1);
            }
        }
        logResult(dp);
        int result = Integer.MAX_VALUE;
        for (int num : dp[len - 1]) {
            result = Math.min(result, num);
        }
        return result;
    }

    @Test
    public void minSideJumps() {
        int[] obstacles = {0, 2, 1, 0, 3, 0};
        logResult(minSideJumps(obstacles));
    }

    /**
     * 1771. 由子序列构造的最长回文串的长度
     *
     * <p>给你两个字符串 word1 和 word2 ，请你按下述方法构造一个字符串：
     *
     * <p>从 word1 中选出某个 非空 子序列 subsequence1 。 从 word2 中选出某个 非空 子序列 subsequence2 。 连接两个子序列
     * subsequence1 + subsequence2 ，得到字符串。 返回可按上述方法构造的最长 回文串 的 长度 。如果无法构造回文串，返回 0 。
     *
     * <p>字符串 s 的一个 子序列 是通过从 s 中删除一些（也可能不删除）字符而不更改其余字符的顺序生成的字符串。
     *
     * <p>回文串 是正着读和反着读结果一致的字符串。
     *
     * <p>示例 1：
     *
     * <p>输入：word1 = "cacb", word2 = "cbba" 输出：5 解释：从 word1 中选出 "ab" ，从 word2 中选出 "cba" ，得到回文串
     * "abcba" 。 示例 2：
     *
     * <p>输入：word1 = "ab", word2 = "ab" 输出：3 解释：从 word1 中选出 "ab" ，从 word2 中选出 "a" ，得到回文串 "aba" 。 示例
     * 3：
     *
     * <p>输入：word1 = "aa", word2 = "bb" 输出：0 解释：无法按题面所述方法构造回文串，所以返回 0 。
     *
     * <p>提示：
     *
     * <p>1 <= word1.length, word2.length <= 1000 word1 和 word2 由小写英文字母组成
     *
     * @param word1
     * @param word2
     * @return
     */
    public int longestPalindrome(String word1, String word2) {
        //  516. 最长回文子序列
        String s = word1 + word2;
        int len = s.length();
        int[][] dp = new int[len][len];
        int max = 0;
        // dp[i][j] 表示 i ~ j 最长的回文子序列长度
        for (int i = len - 1; i >= 0; i--) {
            dp[i][i] = 1;
            for (int j = i + 1; j < len; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                    if (i < word1.length() && j >= word1.length()) {
                        max = Math.max(max, dp[i][j]);
                    }
                } else {
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i + 1][j]);
                }
            }
        }

        return max;
    }

    @Test
    public void longestPalindrome() {
        String word1 = "aa", word2 = "bb";
        logResult(longestPalindrome(word1, word2));
    }

    /**
     * 1787. 使所有区间的异或结果为零
     *
     * <p>给你一个整数数组 nums 和一个整数 k 。区间 [left, right]（left <= right）的 异或结果 是对下标位于 left 和 right（包括 left 和
     * right ）之间所有元素进行 XOR 运算的结果：nums[left] XOR nums[left+1] XOR ... XOR nums[right] 。
     *
     * <p>返回数组中 要更改的最小元素数 ，以使所有长度为 k 的区间异或结果等于零。
     *
     * <p>示例 1：
     *
     * <p>输入：nums = [1,2,0,3,0], k = 1 输出：3 解释：将数组 [1,2,0,3,0] 修改为 [0,0,0,0,0] 示例 2：
     *
     * <p>输入：nums = [3,4,5,2,1,7,3,4,7], k = 3 输出：3 解释：将数组 [3,4,5,2,1,7,3,4,7] 修改为
     * [3,4,7,3,4,7,3,4,7] 示例 3：
     *
     * <p>输入：nums = [1,2,4,1,2,5,1,2,6], k = 3 输出：3 解释：将数组[1,2,4,1,2,5,1,2,6] 修改为
     * [1,2,3,1,2,3,1,2,3]
     *
     * <p>提示：
     *
     * <p>1 <= k <= nums.length <= 2000 0 <= nums[i] < 2^10
     *
     * @param nums
     * @param k
     * @return
     */
    public int minChanges(int[] nums, int k) {
        int result = 0;
        if (k == 1) {
            return (int) Arrays.stream(nums).filter(num -> num > 0).count();
        }
        int len = nums.length;
        // 分成 k 组 每一组  取出 每一组的众数
        List<Map<Integer, Integer>> list = new ArrayList<>();

        int[] maxCounts = new int[k], sums = new int[k];
        for (int i = 0; i < k; i++) {
            Map<Integer, Integer> countMap = new HashMap<>();

            int maxCount = 0, sum = 0;
            for (int j = i; j < len; j += k) {
                int count = countMap.getOrDefault(nums[j], 0);
                countMap.put(nums[j], count + 1);
                sum++;
                maxCount = Math.max(maxCount, count);
            }
            maxCounts[i] = maxCount;
            sums[i] = sum;
            list.add(countMap);
        }
        // 排序
        int[] maxCountsTmp = Arrays.copyOf(maxCounts, k);
        Arrays.sort(maxCountsTmp);
        // 贪心结果
        int greedyResult = len - Arrays.stream(maxCountsTmp).sum() + maxCountsTmp[0];

        // 动态规划
        int[] dp = new int[1 << 10];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int i = 0; i < k; i++) {
            // 1 修改 所有元素
            int[] dpTmp = new int[1 << 10];
            int min = Arrays.stream(dp).min().getAsInt();
            Arrays.fill(dpTmp, min + sums[i]);

            // 2 修改 集合中存在的元素
            Map<Integer, Integer> countMap = list.get(i);
            for (int j = 0; j < 1 << 10; j++) {
                if (dp[j] == Integer.MAX_VALUE) {
                    continue;
                }
                for (Map.Entry<Integer, Integer> entry : countMap.entrySet()) {
                    int key = entry.getKey(), val = entry.getValue();
                    int next = key ^ j;
                    dpTmp[next] = Math.min(dpTmp[next], dp[j] + sums[i] - val);
                }
            }

            dp = dpTmp;
        }

        return Math.min(dp[0], greedyResult);
    }

    @Test
    public void minChanges() {
        int[] nums = {1, 2, 4, 1, 2, 5, 1, 2, 6};
        int k = 3;
        logResult(minChanges(nums, k));
    }

    /**
     * 920. 播放列表的数量
     *
     * <p>你的音乐播放器里有 N 首不同的歌，在旅途中，你的旅伴想要听 L 首歌（不一定不同，即，允许歌曲重复）。请你为她按如下规则创建一个播放列表：
     *
     * <p>每首歌至少播放一次。 一首歌只有在其他 K 首歌播放完之后才能再次播放。 返回可以满足要求的播放列表的数量。由于答案可能非常大，请返回它模 10^9 + 7 的结果。
     *
     * <p>示例 1：
     *
     * <p>输入：N = 3, L = 3, K = 1 输出：6 解释：有 6 种可能的播放列表。[1, 2, 3]，[1, 3, 2]，[2, 1, 3]，[2, 3, 1]，[3, 1,
     * 2]，[3, 2, 1]. 示例 2：
     *
     * <p>输入：N = 2, L = 3, K = 0 输出：6 解释：有 6 种可能的播放列表。[1, 1, 2]，[1, 2, 1]，[2, 1, 1]，[2, 2, 1]，[2, 1,
     * 2]，[1, 2, 2] 示例 3：
     *
     * <p>输入：N = 2, L = 3, K = 1 输出：2 解释：有 2 种可能的播放列表。[1, 2, 1]，[2, 1, 2]
     *
     * <p>提示：
     *
     * <p>0 <= K < N <= L <= 100
     *
     * @param N
     * @param L
     * @param K
     * @return
     */
    public int numMusicPlaylists(int N, int L, int K) {
        // 令 dp[i][j] 为播放列表长度为 i 包含恰好 j 首不同歌曲的数量。我们需要计算 dp[L][N]
        long[][] dp = new long[L + 1][N + 1];
        dp[0][0] = 1L;
        for (int i = 1; i <= L; i++) {
            for (int j = 1; j <= N; j++) {
                // 第 i 首歌曲
                // 1 随机从剩下的 N - j + 1 选择
                dp[i][j] += dp[i - 1][j - 1] * (N - j + 1);
                // 2 从之前的歌曲中取一个重复的
                dp[i][j] += dp[i - 1][j] * Math.max(j - K, 0);
                dp[i][j] %= MOD;
            }
        }

        return (int) dp[L][N];
    }

    /**
     * 956. 最高的广告牌
     *
     * <p>你正在安装一个广告牌，并希望它高度最大。这块广告牌将有两个钢制支架，两边各一个。每个钢支架的高度必须相等。
     *
     * <p>你有一堆可以焊接在一起的钢筋 rods。举个例子，如果钢筋的长度为 1、2 和 3，则可以将它们焊接在一起形成长度为 6 的支架。
     *
     * <p>返回广告牌的最大可能安装高度。如果没法安装广告牌，请返回 0。
     *
     * <p>示例 1：
     *
     * <p>输入：[1,2,3,6] 输出：6 解释：我们有两个不相交的子集 {1,2,3} 和 {6}，它们具有相同的和 sum = 6。 示例 2：
     *
     * <p>输入：[1,2,3,4,5,6] 输出：10 解释：我们有两个不相交的子集 {2,3,5} 和 {4,6}，它们具有相同的和 sum = 10。 示例 3：
     *
     * <p>输入：[1,2] 输出：0 解释：没法安装广告牌，所以返回 0。
     *
     * <p>提示：
     *
     * <p>0 <= rods.length <= 20 1 <= rods[i] <= 1000 钢筋的长度总和最多为 5000
     *
     * @param rods
     * @return
     */
    public int tallestBillboard(int[] rods) {
        // dp[n][i]  表示前n根管子（这里的n根管子可用可不用），当组成左右高度差为i的情况时，左、右的最大公共高度是多少
        int len = rods.length;
        if (len == 0) {
            return 0;
        }
        int sum = Arrays.stream(rods).sum();
        int[] dp = new int[sum + 1];
        Arrays.fill(dp, -1);
        dp[0] = 0;
        for (int height : rods) {
            int[] dpTemp = Arrays.copyOf(dp, dp.length);
            for (int i = 0; i <= sum - height; i++) {

                // 不存在高度差为 i 的 组合
                if (dpTemp[i] < 0) {
                    continue;
                }
                // 高度差 i + height
                dp[i + height] = Math.max(dp[i + height], dpTemp[i]);
                // 高度差 abs(i - height)
                int sub = Math.abs(i - height);
                dp[sub] = Math.max(dp[sub], dpTemp[i] + Math.min(i, height));
            }
        }
        return dp[0];
    }

    /**
     * 943. 最短超级串
     *
     * <p>给定一个字符串数组 words，找到以 words 中每个字符串作为子字符串的最短字符串。如果有多个有效最短字符串满足题目条件，返回其中 任意一个 即可。
     *
     * <p>我们可以假设 words 中没有字符串是 words 中另一个字符串的子字符串。
     *
     * <p>示例 1：
     *
     * <p>输入：words = ["alex","loves","leetcode"] 输出："alexlovesleetcode" 解释："alex"，"loves"，"leetcode"
     * 的所有排列都会被接受。 示例 2：
     *
     * <p>输入：words = ["catg","ctaagt","gcta","ttca","atgcatc"] 输出："gctaagttcatgcatc"
     *
     * <p>提示：
     *
     * <p>1 <= words.length <= 12 1 <= words[i].length <= 20 words[i] 由小写英文字母组成 words 中的所有字符串 互不相同
     *
     * @param words
     * @return
     */
    public String shortestSuperstring(String[] words) {
        int len = words.length;
        // 重复 长度
        int[][] repeatLens = new int[len][len];

        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                if (i == j) {
                    continue;
                }
                int l = Math.min(words[i].length(), words[j].length());
                for (int k = l; k >= 0; k--) {
                    // 重复长度
                    if (words[i].endsWith(words[j].substring(0, k))) {
                        repeatLens[i][j] = k;
                        break;
                    }
                }
            }
        }

        // 使用动态规划计算出所有的 dp(mask, i)，并记录每个状态从哪个状态转移得来，记为 parent
        int[][] dp = new int[1 << len][len];
        int[][] parent = new int[1 << len][len];
        for (int mask = 1; mask < 1 << len; mask++) {
            Arrays.fill(parent[mask], -1);
            for (int j = 0; j < len; j++) {
                if ((mask & (1 << j)) == 0) {
                    continue;
                }
                int parentMask = mask ^ (1 << j);
                if (parentMask == 0) {
                    continue;
                }
                for (int i = 0; i < len; i++) {
                    if ((parentMask & (1 << i)) == 0) {
                        continue;
                    }
                    int val = dp[parentMask][i] + repeatLens[i][j];
                    if (val > dp[mask][j]) {
                        dp[mask][j] = val;
                        parent[mask][j] = i;
                    }
                }
            }
        }

        int[] perm = new int[len];
        boolean[] seen = new boolean[len];
        int t = 0;
        int mask = (1 << len) - 1;

        int p = 0;
        for (int j = 0; j < len; ++j) {
            if (dp[(1 << len) - 1][j] > dp[(1 << len) - 1][p]) {
                p = j;
            }
        }

        while (p != -1) {
            perm[t++] = p;
            seen[p] = true;
            int p2 = parent[mask][p];
            mask ^= 1 << p;
            p = p2;
        }

        // Reverse perm
        for (int i = 0; i < t / 2; ++i) {
            int v = perm[i];
            perm[i] = perm[t - 1 - i];
            perm[t - 1 - i] = v;
        }

        for (int i = 0; i < len; ++i) {
            if (!seen[i]) {
                perm[t++] = i;
            }
        }

        StringBuilder sb = new StringBuilder(words[perm[0]]);
        for (int i = 1; i < len; ++i) {
            int overlap = repeatLens[perm[i - 1]][perm[i]];
            sb.append(words[perm[i]].substring(overlap));
        }

        return sb.toString();
    }

    /**
     * 1473. 粉刷房子 III
     *
     * <p>在一个小城市里，有 m 个房子排成一排，你需要给每个房子涂上 n 种颜色之一（颜色编号为 1 到 n ）。有的房子去年夏天已经涂过颜色了，所以这些房子不需要被重新涂色。
     *
     * <p>我们将连续相同颜色尽可能多的房子称为一个街区。（比方说 houses = [1,2,2,3,3,2,1,1] ，它包含 5 个街区 [{1}, {2,2}, {3,3}, {2},
     * {1,1}] 。）
     *
     * <p>给你一个数组 houses ，一个 m * n 的矩阵 cost 和一个整数 target ，其中：
     *
     * <p>houses[i]：是第 i 个房子的颜色，0 表示这个房子还没有被涂色。 cost[i][j]：是将第 i 个房子涂成颜色 j+1 的花费。
     * 请你返回房子涂色方案的最小总花费，使得每个房子都被涂色后，恰好组成 target 个街区。如果没有可用的涂色方案，请返回 -1 。
     *
     * <p>示例 1：
     *
     * <p>输入：houses = [0,0,0,0,0], cost = [[1,10],[10,1],[10,1],[1,10],[5,1]], m = 5, n = 2, target
     * = 3 输出：9 解释：房子涂色方案为 [1,2,2,1,1] 此方案包含 target = 3 个街区，分别是 [{1}, {2,2}, {1,1}]。 涂色的总花费为 (1 + 1
     * + 1 + 1 + 5) = 9。 示例 2：
     *
     * <p>输入：houses = [0,2,1,2,0], cost = [[1,10],[10,1],[10,1],[1,10],[5,1]], m = 5, n = 2, target
     * = 3 输出：11 解释：有的房子已经被涂色了，在此基础上涂色方案为 [2,2,1,2,2] 此方案包含 target = 3 个街区，分别是 [{2,2}, {1}, {2,2}]。
     * 给第一个和最后一个房子涂色的花费为 (10 + 1) = 11。 示例 3：
     *
     * <p>输入：houses = [0,0,0,0,0], cost = [[1,10],[10,1],[1,10],[10,1],[1,10]], m = 5, n = 2, target
     * = 5 输出：5 示例 4：
     *
     * <p>输入：houses = [3,1,2,3], cost = [[1,1,1],[1,1,1],[1,1,1],[1,1,1]], m = 4, n = 3, target = 3
     * 输出：-1 解释：房子已经被涂色并组成了 4 个街区，分别是 [{3},{1},{2},{3}] ，无法形成 target = 3 个街区。
     *
     * <p>提示：
     *
     * <p>m == houses.length == cost.length n == cost[i].length 1 <= m <= 100 1 <= n <= 20 1 <=
     * target <= m 0 <= houses[i] <= n 1 <= cost[i][j] <= 10^4
     *
     * @param houses
     * @param cost
     * @param m
     * @param n
     * @param target
     * @return
     */
    public int minCost(int[] houses, int[][] cost, int m, int n, int target) {
        // 状态表示：dp[i][j][k] 涂完前i个房子，第i个房子的颜色为j, 目前有k个街区，且的所有方案 cost最小的。
        // 状态转移：dp[i][j][k] 和第i-1个房子的颜色与cost有关。具体看代码注释。

        // 颜色编号 改为 0 ~ n-1  houses 颜色 -1  空颜色为-1
        for (int i = 0; i < m; i++) {
            houses[i]--;
        }

        int[][][] dp = new int[m][n][target + 1];
        int INF = Integer.MAX_VALUE >> 1;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                Arrays.fill(dp[i][j], INF);
            }
        }
        if (houses[0] == -1) {
            // 房子0 颜色为空 涂成 j 颜色
            for (int j = 0; j < n; j++) {
                dp[0][j][1] = cost[0][j];
            }
        } else {
            dp[0][houses[0]][1] = 0;
        }
        // 房子 1 开始 到 m - 1
        for (int i = 1; i < m; i++) {
            // k 个社区
            for (int k = 1; k <= Math.min(i + 1, target); k++) {
                if (houses[i] == -1) {
                    // 空颜色 和前一个颜色有关
                    for (int j = 0; j < n; j++) { // 当前颜色j
                        // 前一个颜色  last
                        for (int last = 0; last < n; last++) {
                            // dp[i][j][k]
                            if (j == last) {
                                dp[i][j][k] =
                                        Math.min(dp[i][j][k], dp[i - 1][last][k] + cost[i][j]);
                            } else {
                                dp[i][j][k] =
                                        Math.min(dp[i][j][k], dp[i - 1][last][k - 1] + cost[i][j]);
                            }
                        }
                    }
                } else {
                    int color = houses[i];
                    // 前一个颜色  last
                    for (int last = 0; last < n; last++) {
                        if (last == color) {
                            dp[i][color][k] = Math.min(dp[i][color][k], dp[i - 1][last][k]);
                        } else {
                            dp[i][color][k] = Math.min(dp[i][color][k], dp[i - 1][last][k - 1]);
                        }
                    }
                }
            }
        }
        logResult(dp);
        int result = INF;
        // 颜色 j
        for (int j = 0; j < n; j++) {
            result = Math.min(result, dp[m - 1][j][target]);
        }

        return result == INF ? -1 : result;
    }

    @Test
    public void minCostIII() {
        int[] houses = {0, 0, 0, 0, 0};
        int[][] cost = {{1, 10}, {10, 1}, {10, 1}, {1, 10}, {5, 1}};
        int m = 5, n = 2, target = 3;
        logResult(minCost(houses, cost, m, n, target));
    }

    /**
     * 982. 按位与为零的三元组
     *
     * <p>给定一个整数数组 A，找出索引为 (i, j, k) 的三元组，使得：
     *
     * <p>0 <= i < A.length 0 <= j < A.length 0 <= k < A.length A[i] & A[j] & A[k] == 0，其中 &
     * 表示按位与（AND）操作符。
     *
     * <p>示例：
     *
     * <p>输入：[2,1,3] 输出：12 解释：我们可以选出如下 i, j, k 三元组： (i=0, j=0, k=1) : 2 & 2 & 1 (i=0, j=1, k=0) : 2
     * & 1 & 2 (i=0, j=1, k=1) : 2 & 1 & 1 (i=0, j=1, k=2) : 2 & 1 & 3 (i=0, j=2, k=1) : 2 & 3 & 1
     * (i=1, j=0, k=0) : 1 & 2 & 2 (i=1, j=0, k=1) : 1 & 2 & 1 (i=1, j=0, k=2) : 1 & 2 & 3 (i=1,
     * j=1, k=0) : 1 & 1 & 2 (i=1, j=2, k=0) : 1 & 3 & 2 (i=2, j=0, k=1) : 3 & 2 & 1 (i=2, j=1, k=0)
     * : 3 & 1 & 2
     *
     * <p>提示：
     *
     * <p>1 <= A.length <= 1000 0 <= A[i] < 2^16
     *
     * @param A
     * @return
     */
    public int countTriplets(int[] A) {
        int[] dp = new int[1 << 16];
        int len = A.length;
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                int num = A[i] & A[j];
                dp[num]++;
            }
        }
        int result = 0;
        int max = (1 << 16) - 1;
        for (int a : A) {
            int num = a ^ max;
            int next = num;
            while (next > 0) {
                result += dp[next];
                next = (next - 1) & num;
            }
            result += dp[0];
        }

        return result;
    }

    /**
     * 1000. 合并石头的最低成本
     *
     * <p>有 N 堆石头排成一排，第 i 堆中有 stones[i] 块石头。
     *
     * <p>每次移动（move）需要将连续的 K 堆石头合并为一堆，而这个移动的成本为这 K 堆石头的总数。
     *
     * <p>找出把所有石头合并成一堆的最低成本。如果不可能，返回 -1 。
     *
     * <p>示例 1：
     *
     * <p>输入：stones = [3,2,4,1], K = 2 输出：20 解释： 从 [3, 2, 4, 1] 开始。 合并 [3, 2]，成本为 5，剩下 [5, 4, 1]。 合并
     * [4, 1]，成本为 5，剩下 [5, 5]。 合并 [5, 5]，成本为 10，剩下 [10]。 总成本 20，这是可能的最小值。 示例 2：
     *
     * <p>输入：stones = [3,2,4,1], K = 3 输出：-1 解释：任何合并操作后，都会剩下 2 堆，我们无法再进行合并。所以这项任务是不可能完成的。. 示例 3：
     *
     * <p>输入：stones = [3,5,1,2,6], K = 3 输出：25 解释： 从 [3, 5, 1, 2, 6] 开始。 合并 [5, 1, 2]，成本为 8，剩下 [3,
     * 8, 6]。 合并 [3, 8, 6]，成本为 17，剩下 [17]。 总成本 25，这是可能的最小值。
     *
     * <p>提示：
     *
     * <p>1 <= stones.length <= 30 2 <= K <= 30 1 <= stones[i] <= 100
     *
     * @param stones
     * @param K
     * @return
     */
    public int mergeStones(int[] stones, int K) {
        int N = stones.length;
        if ((N - 1) % (K - 1) != 0) {
            return -1;
        }
        // 前缀和
        int[] sums = new int[N + 1];
        int[][] dp = new int[N][N];
        for (int i = 0; i < N; i++) {
            sums[i + 1] = sums[i] + stones[i];
            // dp[i][i] = stones[i];
        }

        // 枚举区间长度
        for (int len = K; len <= N; len++) {
            // 枚举起点
            for (int i = 0; i + len - 1 < N; i++) {
                // 终点 j
                int j = i + len - 1;
                dp[i][j] = Integer.MAX_VALUE;
                // 枚举分界点
                for (int k = i; k < j; k += (K - 1)) {
                    dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k + 1][j]);
                }
                if ((j - i) % (K - 1) == 0) {
                    dp[i][j] += sums[j + 1] - sums[i];
                }
            }
        }
        logResult(dp);
        return dp[0][N - 1];
    }

    @Test
    public void mergeStones() {
        int[] stones = {3, 5, 1, 2, 6};
        int K = 3;
        logResult(mergeStones(stones, K));
    }

    /**
     * 1012. 至少有 1 位重复的数字
     *
     * <p>给定正整数 N，返回小于等于 N 且具有至少 1 位重复数字的正整数的个数。
     *
     * <p>示例 1：
     *
     * <p>输入：20 输出：1 解释：具有至少 1 位重复数字的正数（<= 20）只有 11 。 示例 2：
     *
     * <p>输入：100 输出：10 解释：具有至少 1 位重复数字的正数（<= 100）有 11，22，33，44，55，66，77，88，99 和 100 。 示例 3：
     *
     * <p>输入：1000 输出：262
     *
     * <p>提示：
     *
     * <p>1 <= N <= 10^9
     *
     * @param N
     * @return
     */
    public int numDupDigitsAtMostN(int N) {
        if (N <= 10) {
            return 0;
        }
        // 逆向思维 具有至少 1 位重复数字的正数 = 所有数 - 不重复的数
        nums = new int[10];
        int idx = 0;
        while (N > 0) {
            nums[idx++] = N % 10;
            N /= 10;
        }

        dp = new int[1 << 10][idx];
        for (int i = 0; i < 1 << 10; i++) {
            Arrays.fill(dp[i], -1);
        }

        return N + 1 - dfs(idx - 1, 0, 1);
    }

    private int[][] dp;
    private int[] nums;

    public int dfs(int pos, int mask, int limit) {
        // 递归返回条件0
        if (pos == -1) {
            return 1;
        }

        // 加入记忆化 (优化)
        if (dp[mask][pos] != -1 && limit == 0) {
            return dp[mask][pos];
        }

        int up = limit == 1 ? nums[pos] : 9;

        int res = 0;
        for (int i = 0; i <= up; i++) {
            // 首先当前位的状态没有出现过
            // （本体计算的是不满足 至少两次的所有情况 逆向思维）
            if ((mask & (1 << i)) == 0) {
                // 关键是理解下面 if - else
                if (i == 0 && mask == 0) { // 001的情况
                    res += dfs(pos - 1, mask, 0);
                } else {
                    res += dfs(pos - 1, mask | (1 << i), (limit == 1 && i == nums[pos]) ? 1 : 0);
                }
            }
        }

        if (limit == 0) {
            dp[mask][pos] = res;
        }

        return res;
    }

    /**
     * 964. 表示数字的最少运算符
     *
     * <p>给定一个正整数 x，我们将会写出一个形如 x (op1) x (op2) x (op3) x ... 的表达式，其中每个运算符 op1，op2，…
     * 可以是加、减、乘、除（+，-，*，或是 /）之一。例如，对于 x = 3，我们可以写出表达式 3 * 3 / 3 + 3 - 3，该式的值为 3 。
     *
     * <p>在写这样的表达式时，我们需要遵守下面的惯例：
     *
     * <p>除运算符（/）返回有理数。 任何地方都没有括号。 我们使用通常的操作顺序：乘法和除法发生在加法和减法之前。 不允许使用一元否定运算符（-）。例如，“x - x”
     * 是一个有效的表达式，因为它只使用减法，但是 “-x + x” 不是，因为它使用了否定运算符。 我们希望编写一个能使表达式等于给定的目标值 target
     * 且运算符最少的表达式。返回所用运算符的最少数量。
     *
     * <p>示例 1：
     *
     * <p>输入：x = 3, target = 19 输出：5 解释：3 * 3 + 3 * 3 + 3 / 3 。表达式包含 5 个运算符。 示例 2：
     *
     * <p>输入：x = 5, target = 501 输出：8 解释：5 * 5 * 5 * 5 - 5 * 5 * 5 + 5 / 5 。表达式包含 8 个运算符。 示例 3：
     *
     * <p>输入：x = 100, target = 100000000 输出：3 解释：100 * 100 * 100 * 100 。表达式包含 3 个运算符。
     *
     * <p>提示：
     *
     * <p>2 <= x <= 100 1 <= target <= 2 * 10^8
     *
     * @param x
     * @param target
     * @return
     */
    public int leastOpsExpressTarget(int x, int target) {

        // 转为 x 进制
        int[] nums = new int[32];
        int idx = 0;

        while (target > 0) {
            int num = target % x;
            target /= x;
            nums[idx++] = num;
        }
        log.debug("nums:{}", nums);

        int result = 0;
        // 表示更高一位为转换为反向表示时需要增加的运算符数目
        int lastReverse = Integer.MAX_VALUE;
        for (int i = idx - 1; i >= 0; i--) {
            // i 次幂 需要的 操作数 0（x - x）
            int cnt = i == 0 ? 2 : i;

            // 正向表示
            int forward = nums[i] * cnt;
            // 反向表示 高位进1
            int reverse = (x - nums[i]) * cnt + Math.min(lastReverse - i - 1, i + 1);

            result += Math.min(forward, reverse);

            lastReverse = forward >= reverse ? 0 : reverse - forward;
        }

        return result - 1;
    }

    @Test
    public void leastOpsExpressTarget() {
        int x = 3, target = 19;
        logResult(leastOpsExpressTarget(x, target));
    }
}
