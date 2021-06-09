package com.qinfengsa.algorithm.back;

import com.qinfengsa.algorithm.util.CompUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import lombok.extern.slf4j.Slf4j;

/**
 * 回溯算法
 *
 * @author qinfengsa
 * @date 2021/05/18 21:45
 */
@Slf4j
public class BackTrackingMain {

    /**
     * 1307. 口算难题
     *
     * <p>给你一个方程，左边用 words 表示，右边用 result 表示。
     *
     * <p>你需要根据以下规则检查方程是否可解：
     *
     * <p>每个字符都会被解码成一位数字（0 - 9）。 每对不同的字符必须映射到不同的数字。 每个 words[i] 和 result 都会被解码成一个没有前导零的数字。
     * 左侧数字之和（words）等于右侧数字（result）。 如果方程可解，返回 True，否则返回 False。
     *
     * <p>示例 1：
     *
     * <p>输入：words = ["SEND","MORE"], result = "MONEY" 输出：true 解释：映射 'S'-> 9, 'E'->5, 'N'->6,
     * 'D'->7, 'M'->1, 'O'->0, 'R'->8, 'Y'->'2' 所以 "SEND" + "MORE" = "MONEY" , 9567 + 1085 = 10652
     * 示例 2：
     *
     * <p>输入：words = ["SIX","SEVEN","SEVEN"], result = "TWENTY" 输出：true 解释：映射 'S'-> 6, 'I'->5,
     * 'X'->0, 'E'->8, 'V'->7, 'N'->2, 'T'->1, 'W'->'3', 'Y'->4 所以 "SIX" + "SEVEN" + "SEVEN" =
     * "TWENTY" , 650 + 68782 + 68782 = 138214 示例 3：
     *
     * <p>输入：words = ["THIS","IS","TOO"], result = "FUNNY" 输出：true 示例 4：
     *
     * <p>输入：words = ["LEET","CODE"], result = "POINT" 输出：false
     *
     * <p>提示：
     *
     * <p>2 <= words.length <= 5 1 <= words[i].length, results.length <= 7 words[i], result
     * 只含有大写英文字母 表达式中使用的不同字符数最大为 10
     *
     * @param words
     * @param result
     * @return
     */
    public boolean isSolvable(String[] words, String result) {
        notZero = new boolean[26];
        // 字符权重
        charWeightMap = new HashMap<>();
        for (String word : words) {
            handleWord(word, true);
        }
        handleWord(result, false);
        charList = new ArrayList<>(charWeightMap.keySet());
        // 高权重 往前
        charList.sort((a, b) -> Math.abs(charWeightMap.get(b)) - Math.abs(charWeightMap.get(a)));
        N = charList.size();

        visited = new boolean[10];
        // 回溯
        return backSolvable(0, 0);
    }

    private boolean backSolvable(int i, int sum) {
        if (i == N) {
            return sum == 0;
        }
        char c = charList.get(i);
        int j = notZero[c - 'A'] ? 1 : 0;
        for (; j <= 9; j++) {

            if (visited[j]) {
                continue;
            }
            visited[j] = true;

            if (backSolvable(i + 1, sum + j * charWeightMap.get(c))) {
                return true;
            }

            visited[j] = false;
        }

        return false;
    }

    private void handleWord(String word, boolean flag) {
        if (word.length() > 1) {
            notZero[word.charAt(0) - 'A'] = true;
        }

        int num = flag ? 1 : -1;
        for (int i = word.length() - 1; i >= 0; i--) {
            char c = word.charAt(i);
            int weight = charWeightMap.getOrDefault(c, 0);
            weight += num;
            charWeightMap.put(c, weight);
            num *= 10;
        }
    }

    Map<Character, Integer> charWeightMap;

    boolean[] notZero;

    boolean[] visited;

    int N;

    List<Character> charList;

    /**
     * 1467. 两个盒子中球的颜色数相同的概率
     *
     * <p>桌面上有 2n 个颜色不完全相同的球，球上的颜色共有 k 种。给你一个大小为 k 的整数数组 balls ，其中 balls[i] 是颜色为 i 的球的数量。
     *
     * <p>所有的球都已经 随机打乱顺序 ，前 n 个球放入第一个盒子，后 n 个球放入另一个盒子（请认真阅读示例 2 的解释部分）。
     *
     * <p>注意：这两个盒子是不同的。例如，两个球颜色分别为 a 和 b，盒子分别为 [] 和 ()，那么 [a] (b) 和 [b] (a) 这两种分配方式是不同的（请认真阅读示例 1
     * 的解释部分）。
     *
     * <p>请计算「两个盒子中球的颜色数相同」的情况的概率。
     *
     * <p>示例 1：
     *
     * <p>输入：balls = [1,1] 输出：1.00000 解释：球平均分配的方式只有两种： - 颜色为 1 的球放入第一个盒子，颜色为 2 的球放入第二个盒子 - 颜色为 2
     * 的球放入第一个盒子，颜色为 1 的球放入第二个盒子 这两种分配，两个盒子中球的颜色数都相同。所以概率为 2/2 = 1 。 示例 2：
     *
     * <p>输入：balls = [2,1,1] 输出：0.66667 解释：球的列表为 [1, 1, 2, 3] 随机打乱，得到 12 种等概率的不同打乱方案，每种方案概率为 1/12 ：
     * [1,1 / 2,3], [1,1 / 3,2], [1,2 / 1,3], [1,2 / 3,1], [1,3 / 1,2], [1,3 / 2,1], [2,1 / 1,3],
     * [2,1 / 3,1], [2,3 / 1,1], [3,1 / 1,2], [3,1 / 2,1], [3,2 / 1,1]
     * 然后，我们将前两个球放入第一个盒子，后两个球放入第二个盒子。 这 12 种可能的随机打乱方式中的 8 种满足「两个盒子中球的颜色数相同」。 概率 = 8/12 = 0.66667 示例
     * 3：
     *
     * <p>输入：balls = [1,2,1,2] 输出：0.60000 解释：球的列表为 [1, 2, 2, 3, 4, 4]。要想显示所有 180
     * 种随机打乱方案是很难的，但只检查「两个盒子中球的颜色数相同」的 108 种情况是比较容易的。 概率 = 108 / 180 = 0.6 。 示例 4：
     *
     * <p>输入：balls = [3,2,1] 输出：0.30000 解释：球的列表为 [1, 1, 1, 2, 2, 3]。要想显示所有 60
     * 种随机打乱方案是很难的，但只检查「两个盒子中球的颜色数相同」的 18 种情况是比较容易的。 概率 = 18 / 60 = 0.3 。 示例 5：
     *
     * <p>输入：balls = [6,6,6,6,6,6] 输出：0.90327
     *
     * <p>提示：
     *
     * <p>1 <= balls.length <= 8 1 <= balls[i] <= 6 sum(balls) 是偶数 答案与真实值误差在 10^-5 以内，则被视为正确答案
     *
     * @param balls
     * @return
     */
    public double getProbability(int[] balls) {
        int N = 0;
        // 计算组合
        for (int ball : balls) {
            N += ball;
        }
        this.n = N >> 1;
        // 计算组合数
        compNum = CompUtils.initComp(N);

        this.balls = balls;
        // 计算总的组合数 从 N 个元素中随机取n个 C(N, n);
        long total = compNum[N][n];
        ballResult = 0L;
        backBall(0, 0, 0, 0, 1);
        return (double) ballResult / (double) total;
    }

    long[][] compNum;

    private int[] balls;

    private int n;

    private long ballResult;

    /**
     * @param idx 颜色位置
     * @param leftNum 左侧球数
     * @param leftColor 左侧颜色
     * @param rightColor 右侧颜色
     * @param comp 组合数
     */
    private void backBall(int idx, int leftNum, int leftColor, int rightColor, long comp) {
        if (idx == balls.length) {
            if (leftNum == n && leftColor == rightColor) {
                ballResult += comp;
            }

            return;
        }
        int ballNum = balls[idx];
        // 分 球
        for (int i = 0; i <= balls[idx]; i++) {
            if (i + leftNum > n) {
                break;
            }
            backBall(
                    idx + 1,
                    leftNum + i,
                    leftColor + (i == 0 ? 0 : 1),
                    rightColor + (i == balls[idx] ? 0 : 1),
                    comp * compNum[ballNum][i]);
        }
    }

    /**
     * 1681. 最小不兼容性
     *
     * <p>给你一个整数数组 nums 和一个整数 k 。你需要将这个数组划分到 k 个相同大小的子集中，使得同一个子集里面没有两个相同的元素。
     *
     * <p>一个子集的 不兼容性 是该子集里面最大值和最小值的差。
     *
     * <p>请你返回将数组分成 k 个子集后，各子集 不兼容性 的 和 的 最小值 ，如果无法分成分成 k 个子集，返回 -1 。
     *
     * <p>子集的定义是数组中一些数字的集合，对数字顺序没有要求。
     *
     * <p>示例 1：
     *
     * <p>输入：nums = [1,2,1,4], k = 2 输出：4 解释：最优的分配是 [1,2] 和 [1,4] 。 不兼容性和为 (2-1) + (4-1) = 4 。 注意到
     * [1,1] 和 [2,4] 可以得到更小的和，但是第一个集合有 2 个相同的元素，所以不可行。 示例 2：
     *
     * <p>输入：nums = [6,3,8,1,3,1,2,2], k = 4 输出：6 解释：最优的子集分配为 [1,2]，[2,3]，[6,8] 和 [1,3] 。 不兼容性和为
     * (2-1) + (3-2) + (8-6) + (3-1) = 6 。 示例 3：
     *
     * <p>输入：nums = [5,3,3,6,3,3], k = 3 输出：-1 解释：没办法将这些数字分配到 3 个子集且满足每个子集里没有相同数字。
     *
     * <p>提示：
     *
     * <p>1 <= k <= nums.length <= 16 nums.length 能被 k 整除。 1 <= nums[i] <= nums.length
     *
     * @param nums
     * @param k
     * @return
     */
    public int minimumIncompatibility(int[] nums, int k) {
        int len = nums.length;
        if (len == k) {
            return 0;
        }
        if (len % k != 0) {
            return -1;
        }

        int[] numCnts = new int[17];
        for (int num : nums) {
            numCnts[num]++;
            if (numCnts[num] > k) {
                return -1;
            }
        }
        Arrays.sort(nums);
        if (k == 1) {
            return nums[len - 1] - nums[0];
        }
        int state = 1 << len, m = len / k;
        int[] dp = new int[state], incompatibilities = new int[state];
        Arrays.fill(incompatibilities, -1);
        Arrays.fill(dp, Integer.MAX_VALUE >> 1);

        for (int i = 1; i < state; i++) {
            int bit = Integer.bitCount(i);
            if (bit != m) {
                continue;
            }
            int max = 0, min = len + 1;
            Set<Integer> set = new HashSet<>();
            boolean flag = true;
            for (int j = 0; j < len; j++) {
                if ((i & (1 << j)) == 0) {
                    continue;
                }
                if (set.contains(nums[j])) {
                    flag = false;
                    break;
                }
                min = Math.min(min, nums[j]);
                max = Math.max(max, nums[j]);
                set.add(nums[j]);
            }
            if (flag) {
                incompatibilities[i] = max - min;
            }
        }
        // 遍历 dp
        dp[0] = 0;
        for (int i = 0; i < state; i++) {
            int bit = Integer.bitCount(i);
            if (bit % m != 0) {
                continue;
            }
            for (int j = i; j != 0; j = (j - 1) & i) {
                if (incompatibilities[j] == -1) {
                    continue;
                }
                dp[i] = Math.min(dp[i], dp[i ^ j] + incompatibilities[j]);
            }
        }
        log.debug("dp:{}", dp);
        return dp[state - 1] == Integer.MAX_VALUE >> 1 ? -1 : dp[state - 1];
    }
}
