package com.qinfengsa.algorithm.dynamic;

import static com.qinfengsa.algorithm.util.LogUtils.logResult;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

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
     * 买卖股票的最佳时机 II 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
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
     * Best Time to Buy and Sell Stock with Cooldown 给定一个整数数组，其中第 i 个元素代表了第 i 天的股票价格 。​
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
     * <p>1 0 1 0 0 1 0 1 1 1 1 1 1 1 1 1 0 0 1 0
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

    public void findNumberOfLIS() {
        int[] nums = {1, 3, 5, 4, 7};
    }

    /**
     * 最长递增子序列的个数 给定一个未排序的整数数组，找到最长递增子序列的个数。
     *
     * <p>示例 1:
     *
     * <p>输入: [1,3,5,4,7] 输出: 2 解释: 有两个最长递增子序列，分别是 [1, 3, 4, 7] 和[1, 3, 5, 7]。 示例 2:
     *
     * <p>输入: [2,2,2,2,2] 输出: 5 解释: 最长递增子序列的长度是1，并且存在5个子序列的长度为1，因此输出5。 注意: 给定的数组长度不超过 2000
     * 并且结果一定是32位有符号整数。
     *
     * @param nums
     * @return
     */
    public int findNumberOfLIS(int[] nums) {
        int result = 0;

        return result;
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
        logResult(longestValidParenthesesStack(s));
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
            return new LinkedList<>();
        }
        Arrays.sort(nums);
        int[] dp = new int[nums.length];
        dp[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            int max = 0;
            for (int j = 0; j < i; j++) {
                if (nums[i] % nums[j] == 0 && dp[j] > max) {
                    max = dp[j];
                }
            }
            dp[i] = max + 1;
        }
        int maxIndex = 0, maxSize = 0;
        for (int i = 0; i < nums.length; i++) {

            if (dp[i] > maxSize) {
                maxIndex = i;
                maxSize = dp[i];
            }
        }
        List<Integer> result = new ArrayList<>();
        for (int i = maxIndex; i >= 0; i--) {

            if (nums[maxIndex] % nums[i] == 0 && dp[i] == maxSize) {
                maxSize--;
                result.add(nums[i]);
            }
        }
        Collections.reverse(result);
        return result;

        /*List<Integer>[] subsetList = new List[nums.length];

        List<Integer> result = null;

        subsetList[0] = new LinkedList<>();
        subsetList[0].add(nums[0]);


        for (int i = 1; i < nums.length; i++) {
            int max = 0,maxIndex = -1;
            for (int j = 0; j < i; j++) {
                if (nums[i] % nums[j] == 0 && subsetList[j].size() > max) {
                    maxIndex = j;
                    max = subsetList[j].size();
                }
            }
            if (maxIndex == -1) {
                subsetList[i] = new LinkedList<>();
            } else {
                subsetList[i] = new LinkedList<>(subsetList[maxIndex]);
            }
            subsetList[i].add(nums[i]);
        }
        int maxSize = 0;

        for (List<Integer> subset : subsetList) {

            if (subset.size() > maxSize) {
                result = subset;
                maxSize = subset.size();
            }
        }

        return result;*/
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
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if ((sum & 1) == 1) {
            return false;
        }
        int target = sum >> 1;

        boolean[][] dp = new boolean[nums.length][target + 1];

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
}
