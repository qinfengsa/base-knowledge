package com.qinfengsa.algorithm.dynamic;

import static com.qinfengsa.algorithm.util.LogUtils.logResult;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Queue;
import java.util.TreeMap;
import lombok.extern.slf4j.Slf4j;

/**
 * 动态规划
 *
 * @author qinfengsa
 * @date 2021/5/7 13:24
 */
@Slf4j
public class DynamicProgrammingMain {

    int n, m;

    static int[] DIR_ROW = {1, 0, -1, 0};
    static int[] DIR_COL = {0, 1, 0, -1};

    private boolean inArea(int row, int col, int rows, int cols) {
        return row >= 0 && row < rows && col >= 0 && col < cols;
    }

    /**
     * LCP 31. 变换的迷宫
     *
     * <p>某解密游戏中，有一个 N*M 的迷宫，迷宫地形会随时间变化而改变，迷宫出口一直位于 (n-1,m-1) 位置。迷宫变化规律记录于 maze 中，maze[i] 表示 i
     * 时刻迷宫的地形状态，"." 表示可通行空地，"#" 表示陷阱。
     *
     * <p>地形图初始状态记作 maze[0]，此时小力位于起点 (0,0)。此后每一时刻可选择往上、下、左、右其一方向走一步，或者停留在原地。
     *
     * <p>小力背包有以下两个魔法卷轴（卷轴使用一次后消失）：
     *
     * <p>临时消除术：将指定位置在下一个时刻变为空地； 永久消除术：将指定位置永久变为空地。 请判断在迷宫变化结束前（含最后时刻），小力能否在不经过任意陷阱的情况下到达迷宫出口呢？
     *
     * <p>注意： 输入数据保证起点和终点在所有时刻均为空地。
     *
     * <p>示例 1：
     *
     * <p>输入：maze = [[".#.","#.."],["...",".#."],[".##",".#."],["..#",".#."]]
     *
     * <p>输出：true
     *
     * <p>解释： maze.gif
     *
     * <p>示例 2：
     *
     * <p>输入：maze = [[".#.","..."],["...","..."]]
     *
     * <p>输出：false
     *
     * <p>解释：由于时间不够，小力无法到达终点逃出迷宫。
     *
     * <p>示例 3：
     *
     * <p>输入：maze =
     * [["...","...","..."],[".##","###","##."],[".##","###","##."],[".##","###","##."],[".##","###","##."],[".##","###","##."],[".##","###","##."]]
     *
     * <p>输出：false
     *
     * <p>解释：由于道路不通，小力无法到达终点逃出迷宫。
     *
     * <p>提示：
     *
     * <p>1 <= maze.length <= 100 1 <= maze[i].length, maze[i][j].length <= 50 maze[i][j] 仅包含
     * "."、"#"
     *
     * @param maze
     * @return
     */
    public boolean escapeMaze(List<List<String>> maze) {
        int T = maze.size();
        int m = maze.get(0).size();
        int n = maze.get(0).get(0).length();
        // 状态转移 0 未使用  1 使用临时消除术  2 使用永久消失术 3 都使用  4 不可达
        boolean[][][][] dp = new boolean[T][m][n][4];

        dp[0][0][0][0] = true;

        // 位置 i,j  是否使用永久消失术
        boolean[][] usePerms = new boolean[m][n];

        int[][] dirs = {{1, 0}, {0, 1}, {0, 0}, {-1, 0}, {0, -1}};
        for (int t = 1; t < T; t++) {
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {

                    char c = maze.get(t).get(i).charAt(j);

                    for (int status = 0; status < 4; status++) {
                        boolean lastStatus = false;
                        for (int[] dir : dirs) {
                            int lastRow = i + dir[0], lastCol = j + dir[1];
                            if (!inArea(lastRow, lastCol, m, n)) {
                                continue;
                            }
                            if (dp[t - 1][lastRow][lastCol][status]) {
                                lastStatus = true;
                                break;
                            }
                        }
                        // 不可达
                        if (!lastStatus) {
                            continue;
                        }
                        if (c == '.') {
                            dp[t][i][j][status] = true;
                        } else {
                            // 使用临时消除术 使用永久消失术
                            boolean useTmp = (status & 1) == 1, usePerm = (status & 2) == 2;
                            if (!useTmp) {
                                dp[t][i][j][status | 1] = true;
                            }
                            if (!usePerm) {

                                if (!usePerms[i][j]) {
                                    for (int t1 = t; t1 < T; t1++) {
                                        dp[t1][i][j][status | 2] = true;
                                    }

                                    usePerms[i][j] = true;
                                }
                            }
                        }
                    }
                }
            }

            for (int status = 0; status < 4; status++) {
                if (dp[t][m - 1][n - 1][status]) {
                    return true;
                }
            }
        }
        logResult(dp);

        return false;
    }

    /**
     * 1278. 分割回文串 III
     *
     * <p>给你一个由小写字母组成的字符串 s，和一个整数 k。
     *
     * <p>请你按下面的要求分割字符串：
     *
     * <p>首先，你可以将 s 中的部分字符修改为其他的小写英文字母。 接着，你需要把 s 分割成 k 个非空且不相交的子串，并且每个子串都是回文串。
     * 请返回以这种方式分割字符串所需修改的最少字符数。
     *
     * <p>示例 1：
     *
     * <p>输入：s = "abc", k = 2 输出：1 解释：你可以把字符串分割成 "ab" 和 "c"，并修改 "ab" 中的 1 个字符，将它变成回文串。 示例 2：
     *
     * <p>输入：s = "aabbc", k = 3 输出：0 解释：你可以把字符串分割成 "aa"、"bb" 和 "c"，它们都是回文串。 示例 3：
     *
     * <p>输入：s = "leetcode", k = 8 输出：0
     *
     * <p>提示：
     *
     * <p>1 <= k <= s.length <= 100 s 中只含有小写英文字母。
     *
     * @param s
     * @param k
     * @return
     */
    public int palindromePartition(String s, int k) {
        int len = s.length();
        if (len == k) {
            return 0;
        }
        char[] chars = s.toCharArray();
        if (k == 1) {
            return cost(chars, 0, len - 1);
        }
        // dp[i][j] 表示对于字符串 S 的前 i 个字符，将它分割成 j 个非空且不相交的回文串，最少需要修改的字符数。
        // 状态转移方程：
        // dp[i][j] = min(dp[idx][j - 1] + cost(S, idx + 1, i))
        int[][] dp = new int[len + 1][k + 1];
        for (int i = 0; i <= len; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        dp[0][0] = 0;
        for (int i = 1; i <= len; i++) {
            for (int j = 1; j <= Math.min(i, k); j++) {
                if (j == 1) {
                    dp[i][j] = cost(chars, 0, i - 1);
                    continue;
                }
                for (int idx = j - 1; idx < i; idx++) {
                    dp[i][j] = Math.min(dp[i][j], dp[idx][j - 1] + cost(chars, idx, i - 1));
                }
            }
        }
        logResult(dp);

        return dp[len][k];
    }

    /**
     * 从 start 到 end 转换为 回文字符串需要的 修改的字符数
     *
     * @param chars
     * @param start
     * @param end
     * @return
     */
    private int cost(char[] chars, int start, int end) {
        int result = 0;

        while (start < end) {
            if (chars[start++] != chars[end--]) {
                result++;
            }
        }

        return result;
    }

    /**
     * 1289. 下降路径最小和 II
     *
     * <p>给你一个整数方阵 arr ，定义「非零偏移下降路径」为：从 arr 数组中的每一行选择一个数字，且按顺序选出来的数字中，相邻数字不在原数组的同一列。
     *
     * <p>请你返回非零偏移下降路径数字和的最小值。
     *
     * <p>示例 1：
     *
     * <p>输入：arr = [[1,2,3],[4,5,6],[7,8,9]] 输出：13 解释： 所有非零偏移下降路径包括： [1,5,9], [1,5,7], [1,6,7],
     * [1,6,8], [2,4,8], [2,4,9], [2,6,7], [2,6,8], [3,4,8], [3,4,9], [3,5,7], [3,5,9] 下降路径中数字和最小的是
     * [1,5,7] ，所以答案是 13 。
     *
     * <p>提示：
     *
     * <p>1 <= arr.length == arr[i].length <= 200 -99 <= arr[i][j] <= 99
     *
     * @param arr
     * @return
     */
    public int minFallingPathSum(int[][] arr) {
        int rows = arr.length, cols = arr[0].length;

        int[] dp = Arrays.copyOf(arr[0], cols);

        for (int i = 1; i < rows; i++) {
            // 找到最小和第二小的 之和idx
            int minIdx = 0, secondIdx = -1;
            for (int j = 1; j < cols; j++) {
                if (dp[j] < dp[minIdx]) {
                    secondIdx = minIdx;
                    minIdx = j;
                } else if (secondIdx == -1 || dp[j] < dp[secondIdx]) {
                    secondIdx = j;
                }
            }
            int[] tmpDp = new int[cols];
            for (int j = 0; j < cols; j++) {
                if (j == minIdx) {
                    tmpDp[j] = arr[i][j] + dp[secondIdx];
                } else {
                    tmpDp[j] = arr[i][j] + dp[minIdx];
                }
            }
            dp = tmpDp;
        }
        int result = Integer.MAX_VALUE;
        for (int i = 0; i < cols; i++) {
            result = Math.min(result, dp[i]);
        }
        return result;
    }

    static int MOD = 1_000_000_007;

    /**
     * 1301. 最大得分的路径数目
     *
     * <p>给你一个正方形字符数组 board ，你从数组最右下方的字符 'S' 出发。
     *
     * <p>你的目标是到达数组最左上角的字符 'E' ，数组剩余的部分为数字字符 1, 2, ..., 9 或者障碍
     * 'X'。在每一步移动中，你可以向上、向左或者左上方移动，可以移动的前提是到达的格子没有障碍。
     *
     * <p>一条路径的 「得分」 定义为：路径上所有数字的和。
     *
     * <p>请你返回一个列表，包含两个整数：第一个整数是 「得分」 的最大值，第二个整数是得到最大得分的方案数，请把结果对 10^9 + 7 取余。
     *
     * <p>如果没有任何路径可以到达终点，请返回 [0, 0] 。
     *
     * <p>示例 1：
     *
     * <p>输入：board = ["E23","2X2","12S"] 输出：[7,1] 示例 2：
     *
     * <p>输入：board = ["E12","1X1","21S"] 输出：[4,2] 示例 3：
     *
     * <p>输入：board = ["E11","XXX","11S"] 输出：[0,0]
     *
     * <p>提示：
     *
     * <p>2 <= board.length == board[i].length <= 100
     *
     * @param board
     * @return
     */
    public int[] pathsWithMaxScore(List<String> board) {
        int rows = board.size(), cols = board.get(0).length();
        // 0 表示 最大分数， 1 表示 最大分数的
        int[][][] dp = new int[rows][cols][2];

        dp[rows - 1][cols - 1][0] = 0;
        dp[rows - 1][cols - 1][1] = 1;

        for (int i = rows - 2; i >= 0; i--) {
            char c = board.get(i).charAt(cols - 1);
            if (c == 'X') {
                break;
            }
            dp[i][cols - 1][0] = dp[i + 1][cols - 1][0] + (c - '0');
            dp[i][cols - 1][1] = dp[i + 1][cols - 1][1];
        }
        for (int j = cols - 2; j >= 0; j--) {
            char c = board.get(rows - 1).charAt(j);
            if (c == 'X') {
                break;
            }
            dp[rows - 1][j][0] = dp[rows - 1][j + 1][0] + (c - '0');
            dp[rows - 1][j][1] = dp[rows - 1][j + 1][1];
        }

        for (int i = rows - 2; i >= 0; i--) {
            for (int j = cols - 2; j >= 0; j--) {
                char c = board.get(i).charAt(j);
                if (c == 'X') {
                    continue;
                }
                int num = c == 'E' ? 0 : c - '0';

                // 右边
                if (dp[i][j + 1][1] > 0) {
                    dp[i][j][0] = dp[i][j + 1][0] + num;
                    dp[i][j][1] = dp[i][j + 1][1];
                    dp[i][j][1] %= MOD;
                }
                // 下边
                if (dp[i + 1][j][1] > 0) {
                    int result = dp[i + 1][j][0] + num;
                    if (result > dp[i][j][0]) {
                        dp[i][j][0] = dp[i + 1][j][0] + num;
                        dp[i][j][1] = dp[i + 1][j][1];
                    } else if (result == dp[i][j][0]) {
                        dp[i][j][1] += dp[i + 1][j][1];
                    }
                    dp[i][j][1] %= MOD;
                }
                // 右下
                if (dp[i + 1][j + 1][1] > 0) {
                    int result = dp[i + 1][j + 1][0] + num;
                    if (result > dp[i][j][0]) {
                        dp[i][j][0] = dp[i + 1][j + 1][0] + num;
                        dp[i][j][1] = dp[i + 1][j + 1][1];
                    } else if (result == dp[i][j][0]) {
                        dp[i][j][1] += dp[i + 1][j + 1][1];
                    }
                    dp[i][j][1] %= MOD;
                }
            }
        }
        logResult(dp);
        return dp[0][0];
    }

    /**
     * 1312. 让字符串成为回文串的最少插入次数
     *
     * <p>给你一个字符串 s ，每一次操作你都可以在字符串的任意位置插入任意字符。
     *
     * <p>请你返回让 s 成为回文串的 最少操作次数 。
     *
     * <p>「回文串」是正读和反读都相同的字符串。
     *
     * <p>示例 1：
     *
     * <p>输入：s = "zzazz" 输出：0 解释：字符串 "zzazz" 已经是回文串了，所以不需要做任何插入操作。 示例 2：
     *
     * <p>输入：s = "mbadm" 输出：2 解释：字符串可变为 "mbdadbm" 或者 "mdbabdm" 。 示例 3：
     *
     * <p>输入：s = "leetcode" 输出：5 解释：插入 5 个字符后字符串变为 "leetcodocteel" 。 示例 4：
     *
     * <p>输入：s = "g" 输出：0 示例 5：
     *
     * <p>输入：s = "no" 输出：1
     *
     * <p>提示：
     *
     * <p>1 <= s.length <= 500 s 中所有字符都是小写字母。
     *
     * @param s
     * @return
     */
    public int minInsertions(String s) {
        int len = s.length();
        int[][] dp = new int[len][len];
        // dp[i][j]  i 到 j 组成回文字符串 需要的最少插入次数
        for (int i = len - 2; i >= 0; i--) {
            for (int j = i + 1; j < len; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i + 1][j - 1];
                } else {
                    dp[i][j] = Math.min(dp[i][j - 1], dp[i + 1][j]) + 1;
                }
            }
        }

        return dp[0][len - 1];
    }

    /**
     * 1320. 二指输入的的最小距离
     *
     * <p>二指输入法定制键盘在 XY 平面上的布局如上图所示，其中每个大写英文字母都位于某个坐标处，例如字母 A 位于坐标 (0,0)，字母 B 位于坐标 (0,1)，字母 P 位于坐标
     * (2,3) 且字母 Z 位于坐标 (4,1)。
     *
     * <p>给你一个待输入字符串 word，请你计算并返回在仅使用两根手指的情况下，键入该字符串需要的最小移动总距离。坐标 (x1,y1) 和 (x2,y2) 之间的距离是 |x1 - x2|
     * + |y1 - y2|。
     *
     * <p>注意，两根手指的起始位置是零代价的，不计入移动总距离。你的两根手指的起始位置也不必从首字母或者前两个字母开始。
     *
     * <p>示例 1：
     *
     * <p>输入：word = "CAKE" 输出：3 解释： 使用两根手指输入 "CAKE" 的最佳方案之一是： 手指 1 在字母 'C' 上 -> 移动距离 = 0 手指 1 在字母
     * 'A' 上 -> 移动距离 = 从字母 'C' 到字母 'A' 的距离 = 2 手指 2 在字母 'K' 上 -> 移动距离 = 0 手指 2 在字母 'E' 上 -> 移动距离 =
     * 从字母 'K' 到字母 'E' 的距离 = 1 总距离 = 3 示例 2：
     *
     * <p>输入：word = "HAPPY" 输出：6 解释： 使用两根手指输入 "HAPPY" 的最佳方案之一是： 手指 1 在字母 'H' 上 -> 移动距离 = 0 手指 1 在字母
     * 'A' 上 -> 移动距离 = 从字母 'H' 到字母 'A' 的距离 = 2 手指 2 在字母 'P' 上 -> 移动距离 = 0 手指 2 在字母 'P' 上 -> 移动距离 =
     * 从字母 'P' 到字母 'P' 的距离 = 0 手指 1 在字母 'Y' 上 -> 移动距离 = 从字母 'A' 到字母 'Y' 的距离 = 4 总距离 = 6 示例 3：
     *
     * <p>输入：word = "NEW" 输出：3 示例 4：
     *
     * <p>输入：word = "YEAR" 输出：7
     *
     * <p>提示：
     *
     * <p>2 <= word.length <= 300 每个 word[i] 都是一个大写英文字母。
     *
     * @param word
     * @return
     */
    public int minimumDistance(String word) {
        int len = word.length();
        if (len == 2) {
            return 0;
        }
        // dp[i][l][r] 左手 在l 右手在r 是的 最小值
        int[][][] dp = new int[len][26][26];
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < 26; j++) {
                Arrays.fill(dp[i][j], Integer.MAX_VALUE >> 1);
            }
        }
        char c1 = word.charAt(0);
        for (int i = 0; i < 26; i++) {
            dp[0][c1 - 'A'][i] = dp[0][i][c1 - 'A'] = 0;
        }

        for (int i = 1; i < len; i++) {
            char c = word.charAt(i);
            int curNum = c - 'A', preNum = word.charAt(i - 1) - 'A';
            int d = getDistance(curNum, preNum);
            for (int j = 0; j < 26; j++) {
                // 左手 移动
                dp[i][curNum][j] = Math.min(dp[i][curNum][j], dp[i - 1][preNum][j] + d);
                // 右手 移动
                dp[i][j][curNum] = Math.min(dp[i][j][curNum], dp[i - 1][j][preNum] + d);
                if (j == preNum) {
                    // 前一位 不需要移动
                    for (int k = 0; k < 26; k++) {
                        int dis = getDistance(curNum, k);
                        // 左手 移动
                        dp[i][curNum][j] = Math.min(dp[i][curNum][j], dp[i - 1][k][j] + dis);
                        // 右手 移动
                        dp[i][j][curNum] = Math.min(dp[i][j][curNum], dp[i - 1][j][k] + dis);
                    }
                }
            }
        }
        int end = word.charAt(len - 1) - 'A';
        int result = Integer.MAX_VALUE;
        for (int i = 0; i < 26; i++) {
            result = Math.min(result, dp[len - 1][i][end]);
            result = Math.min(result, dp[len - 1][end][i]);
        }

        return result;
    }

    private int getDistance(int num1, int num2) {
        int x1 = num1 / 6, y1 = num1 % 6;
        int x2 = num2 / 6, y2 = num2 % 6;
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }

    /**
     * 1335. 工作计划的最低难度
     *
     * <p>你需要制定一份 d 天的工作计划表。工作之间存在依赖，要想执行第 i 项工作，你必须完成全部 j 项工作（ 0 <= j < i）。
     *
     * <p>你每天 至少 需要完成一项任务。工作计划的总难度是这 d 天每一天的难度之和，而一天的工作难度是当天应该完成工作的最大难度。
     *
     * <p>给你一个整数数组 jobDifficulty 和一个整数 d，分别代表工作难度和需要计划的天数。第 i 项工作的难度是 jobDifficulty[i]。
     *
     * <p>返回整个工作计划的 最小难度 。如果无法制定工作计划，则返回 -1 。
     *
     * <p>示例 1：
     *
     * <p>输入：jobDifficulty = [6,5,4,3,2,1], d = 2 输出：7 解释：第一天，您可以完成前 5 项工作，总难度 = 6.
     * 第二天，您可以完成最后一项工作，总难度 = 1. 计划表的难度 = 6 + 1 = 7
     *
     * <p>示例 2： 输入：jobDifficulty = [9,9,9], d = 4 输出：-1
     *
     * <p>解释：就算你每天完成一项工作，仍然有一天是空闲的，你无法制定一份能够满足既定工作时间的计划表。
     *
     * <p>示例 3： 输入：jobDifficulty = [1,1,1], d = 3 输出：3 解释：工作计划为每天一项工作，总难度为 3 。
     *
     * <p>示例 4： 输入：jobDifficulty = [7,1,7,1,7,1], d = 3 输出：15
     *
     * <p>示例 5： 输入：jobDifficulty = [11,111,22,222,33,333,44,444], d = 6 输出：843
     *
     * <p>提示：
     *
     * <p>1 <= jobDifficulty.length <= 300 0 <= jobDifficulty[i] <= 1000 1 <= d <= 10
     *
     * @param jobDifficulty
     * @param d
     * @return
     */
    public int minDifficulty(int[] jobDifficulty, int d) {
        int len = jobDifficulty.length;
        if (len < d) {
            return -1;
        }
        // dp[i][j] 表示 第 i 项工作 j 天完成 最小难度
        // dp[i][j] = Min(dp[k][j - 1] + max[k + 1][i])
        int[][] dp = new int[len][d];

        for (int i = 0; i < len; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE >> 1);
        }

        //  第一天 完成前 i 项的任务
        int max = 0;
        for (int i = 0; i < len - d + 1; i++) {
            max = Math.max(max, jobDifficulty[i]);
            dp[i][0] = max;
        }
        // dp 从第二天开始
        for (int j = 1; j < d; j++) {
            for (int i = j; i < len; i++) {

                max = 0;
                //  前一天 （j - 1）完成 k 项任务 第 k + 1 项任务 j 天完成
                for (int k = i - 1; k >= 0; k--) {
                    max = Math.max(max, jobDifficulty[k + 1]);
                    dp[i][j] = Math.min(dp[i][j], dp[k][j - 1] + max);
                }
            }
        }
        logResult(dp);

        return dp[len - 1][d - 1];
    }

    /**
     * 1349. 参加考试的最大学生数
     *
     * <p>给你一个 m * n 的矩阵 seats 表示教室中的座位分布。如果座位是坏的（不可用），就用 '#' 表示；否则，用 '.' 表示。
     *
     * <p>学生可以看到左侧、右侧、左上、右上这四个方向上紧邻他的学生的答卷，但是看不到直接坐在他前面或者后面的学生的答卷。请你计算并返回该考场可以容纳的一起参加考试且无法作弊的最大学生人数。
     *
     * <p>学生必须坐在状况良好的座位上。
     *
     * <p>示例 1：
     *
     * <p>输入：seats = [["#",".","#","#",".","#"], [".","#","#","#","#","."],
     * ["#",".","#","#",".","#"]] 输出：4 解释：教师可以让 4 个学生坐在可用的座位上，这样他们就无法在考试中作弊。
     *
     * <p>示例 2：
     *
     * <p>输入：seats = [[".","#"], ["#","#"], ["#","."], ["#","#"], [".","#"]] 输出：3 解释：让所有学生坐在可用的座位上。
     *
     * <p>示例 3：
     *
     * <p>输入：seats = [["#",".",".",".","#"], [".","#",".","#","."], [".",".","#",".","."],
     * [".","#",".","#","."], ["#",".",".",".","#"]] 输出：10 解释：让学生坐在第 1、3 和 5 列的可用座位上。
     *
     * <p>提示：
     *
     * <p>seats 只包含字符 '.' 和'#'
     *
     * <p>m == seats.length n == seats[i].length 1 <= m <= 8 1 <= n <= 8
     *
     * @param seats
     * @return
     */
    public int maxStudents(char[][] seats) {
        int m = seats.length, n = seats[0].length;
        // 总共 m 行, 每 行 2^n 中可能性
        int stateNum = 1 << n;
        int[][] dp = new int[m][stateNum];
        // m 行的座位状态
        int[] rowState = new int[m];
        for (int i = 0; i < m; i++) {
            int state = 0;
            for (int j = 0; j < n; j++) {
                if (seats[i][j] == '#') {
                    continue;
                }
                state |= 1 << j;
            }
            rowState[i] = state;
        }
        log.debug("rowState:{}", rowState);

        for (int i = 0; i < m; i++) {
            Arrays.fill(dp[i], -1);
        }

        // 第一行
        for (int j = 0; j < stateNum; j++) {
            // 判断 能否 坐下  (j & rowState[0]) == j
            // 且 左右没人 (j & j >> 1) == 0
            if ((j & rowState[0]) == j && (j & j >> 1) == 0) {
                dp[0][j] = Integer.bitCount(j);
            }
        }

        for (int i = 1; i < m; i++) {
            // 第i行
            for (int j = 0; j < stateNum; j++) {

                if ((j & rowState[i]) != j) {
                    continue;
                }
                if ((j & j >> 1) != 0) {
                    continue;
                }
                int bitCnt = Integer.bitCount(j);
                // 遍历上一行
                for (int k = 0; k < stateNum; k++) {
                    if (dp[i - 1][k] == -1) {
                        continue;
                    }
                    // 判断 左前方 和 右前方
                    if ((j & (k >> 1)) != 0) {
                        continue;
                    }
                    if ((j & (k << 1)) != 0) {
                        continue;
                    }
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][k] + bitCnt);
                }
            }
        }
        logResult(dp);
        int max = 0;
        for (int num : dp[m - 1]) {
            max = Math.max(max, num);
        }

        return max;
    }

    /**
     * 1363. 形成三的最大倍数
     *
     * <p>给你一个整数数组 digits，你可以通过按任意顺序连接其中某些数字来形成 3 的倍数，请你返回所能得到的最大的 3 的倍数。
     *
     * <p>由于答案可能不在整数数据类型范围内，请以字符串形式返回答案。
     *
     * <p>如果无法得到答案，请返回一个空字符串。
     *
     * <p>示例 1：
     *
     * <p>输入：digits = [8,1,9] 输出："981" 示例 2：
     *
     * <p>输入：digits = [8,6,7,1,0] 输出："8760" 示例 3：
     *
     * <p>输入：digits = [1] 输出："" 示例 4：
     *
     * <p>输入：digits = [0,0,0,0,0,0] 输出："0"
     *
     * <p>提示：
     *
     * <p>1 <= digits.length <= 10^4 0 <= digits[i] <= 9 返回的结果不应包含不必要的前导零。
     *
     * @param digits
     * @return
     */
    public String largestMultipleOfThree(int[] digits) {
        int sum = 0;
        int[] buckets = new int[10];
        for (int num : digits) {
            sum += num;
            buckets[num]++;
        }
        int rm = sum % 3;
        int cnt1 = buckets[1] + buckets[4] + buckets[7],
                cnt2 = buckets[2] + buckets[5] + buckets[8];
        int rm1 = 0, rm2 = 0;
        if (rm == 1) {
            if (cnt1 > 0) {
                rm1 = 1;
            } else {
                rm2 = 2;
            }
        } else if (rm == 2) {
            if (cnt2 > 0) {
                rm2 = 1;
            } else {
                rm1 = 2;
            }
        }
        int[] mod1 = {1, 4, 7};
        int[] mod2 = {2, 5, 8};
        for (int i = 0; i < rm1; i++) {
            for (int m : mod1) {
                if (buckets[m] > 0) {
                    buckets[m]--;
                    break;
                }
            }
        }
        for (int i = 0; i < rm2; i++) {
            for (int m : mod2) {
                if (buckets[m] > 0) {
                    buckets[m]--;
                    break;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 9; i >= 0; i--) {
            if (buckets[i] == 0) {
                continue;
            }
            for (int j = 0; j < buckets[i]; j++) {
                sb.append(i);
            }
        }
        if (sb.length() > 0 && sb.charAt(0) == '0') {
            return "0";
        }

        return sb.toString();
    }

    /**
     * 1388. 3n 块披萨
     *
     * <p>给你一个披萨，它由 3n 块不同大小的部分组成，现在你和你的朋友们需要按照如下规则来分披萨：
     *
     * <p>你挑选 任意 一块披萨。 Alice 将会挑选你所选择的披萨逆时针方向的下一块披萨。 Bob 将会挑选你所选择的披萨顺时针方向的下一块披萨。 重复上述过程直到没有披萨剩下。
     * 每一块披萨的大小按顺时针方向由循环数组 slices 表示。
     *
     * <p>请你返回你可以获得的披萨大小总和的最大值。
     *
     * <p>示例 1：
     *
     * <p>输入：slices = [1,2,3,4,5,6] 输出：10 解释：选择大小为 4 的披萨，Alice 和 Bob 分别挑选大小为 3 和 5 的披萨。然后你选择大小为 6
     * 的披萨，Alice 和 Bob 分别挑选大小为 2 和 1 的披萨。你获得的披萨总大小为 4 + 6 = 10 。 示例 2：
     *
     * <p>输入：slices = [8,9,8,6,1,1] 输出：16 解释：两轮都选大小为 8 的披萨。如果你选择大小为 9 的披萨，你的朋友们就会选择大小为 8
     * 的披萨，这种情况下你的总和不是最大的。 示例 3：
     *
     * <p>输入：slices = [4,1,2,5,8,3,1,9,7] 输出：21 示例 4：
     *
     * <p>输入：slices = [3,1,2] 输出：3
     *
     * <p>提示：
     *
     * <p>1 <= slices.length <= 500 slices.length % 3 == 0 1 <= slices[i] <= 1000
     *
     * @param slices
     * @return
     */
    public int maxSizeSlices(int[] slices) {
        // 本题可以转化成如下问题：
        // 给一个长度为 3n  的环状序列，你可以在其中选择 n 个数，并且任意两个数不能相邻，求这 n 个数的最大值。

        int len = slices.length;
        // 分为两种情况 选第一个 不选第一个 然后分别dp
        int[] slices0 = Arrays.copyOf(slices, len - 1);
        int[] slices1 = Arrays.copyOfRange(slices, 1, len);
        int result0 = dpSlice(slices0), result1 = dpSlice(slices1);

        return Math.max(result0, result1);
    }

    private int dpSlice(int[] slices) {
        int len = slices.length;
        int choose = (len + 1) / 3;
        int[][] dp = new int[len + 1][choose + 1];
        for (int i = 1; i <= len; i++) {

            for (int j = 1; j <= choose; j++) {
                dp[i][j] = Math.max(dp[i - 1][j], (i >= 2 ? dp[i - 2][j - 1] : 0) + slices[i - 1]);
            }
        }

        return dp[len][choose];
    }

    /**
     * 1411. 给 N x 3
     *
     * <p>网格图涂色的方案数 你有一个 n x 3 的网格图 grid ，你需要用 红，黄，绿
     * 三种颜色之一给每一个格子上色，且确保相邻格子颜色不同（也就是有相同水平边或者垂直边的格子颜色不同）。
     *
     * <p>给你网格图的行数 n 。
     *
     * <p>请你返回给 grid 涂色的方案数。由于答案可能会非常大，请你返回答案对 10^9 + 7 取余的结果。
     *
     * <p>示例 1：
     *
     * <p>输入：n = 1 输出：12 解释：总共有 12 种可行的方法：
     *
     * <p>示例 2：
     *
     * <p>输入：n = 2 输出：54 示例 3：
     *
     * <p>输入：n = 3 输出：246 示例 4：
     *
     * <p>输入：n = 7 输出：106494 示例 5：
     *
     * <p>输入：n = 5000 输出：30228214
     *
     * <p>提示：
     *
     * <p>n == grid.length grid[i].length == 3 1 <= n <= 5000
     *
     * @param n
     * @return
     */
    public int numOfWays(int n) {
        // 我们可以把它们分成两类：
        // ABC 类：三个颜色互不相同，一共有 66 种：012, 021, 102, 120, 201, 210；
        // ABA 类：左右两侧的颜色相同，也有 66 种：010, 020, 101, 121, 202, 212
        long num0 = 6, num1 = 6;

        for (int i = 2; i <= n; i++) {
            // 当前行 i 行 是 ABC 上一行可能性 BAC BCA CAB   BAB   BCB
            // = (i - 1 行 ABC) * 3 +  (i - 1 行 ABA) * 3
            long nextNum0 = (num0 * 3L + num1 * 2L) % MOD;
            // 当前行 i 行 是 ABA 上一行可能性 BAC CAB  BAB CAC (A只能在中间)
            long nextNum1 = (num0 * 2L + num1 * 2L) % MOD;
            num0 = nextNum0;
            num1 = nextNum1;
        }

        return (int) ((num0 + num1) % MOD);
    }

    /**
     * 1416. 恢复数组
     *
     * <p>某个程序本来应该输出一个整数数组。但是这个程序忘记输出空格了以致输出了一个数字字符串，我们所知道的信息只有：数组中所有整数都在 [1, k] 之间，且数组中的数字都没有前导 0 。
     *
     * <p>给你字符串 s 和整数 k 。可能会有多种不同的数组恢复结果。
     *
     * <p>按照上述程序，请你返回所有可能输出字符串 s 的数组方案数。
     *
     * <p>由于数组方案数可能会很大，请你返回它对 10^9 + 7 取余 后的结果。
     *
     * <p>示例 1：
     *
     * <p>输入：s = "1000", k = 10000 输出：1 解释：唯一一种可能的数组方案是 [1000] 示例 2：
     *
     * <p>输入：s = "1000", k = 10 输出：0 解释：不存在任何数组方案满足所有整数都 >= 1 且 <= 10 同时输出结果为 s 。 示例 3：
     *
     * <p>输入：s = "1317", k = 2000 输出：8 解释：可行的数组方案为
     * [1317]，[131,7]，[13,17]，[1,317]，[13,1,7]，[1,31,7]，[1,3,17]，[1,3,1,7] 示例 4：
     *
     * <p>输入：s = "2020", k = 30 输出：1 解释：唯一可能的数组方案是 [20,20] 。 [2020] 不是可行的数组方案，原因是 2020 > 30 。
     * [2,020] 也不是可行的数组方案，因为 020 含有前导 0 。 示例 5：
     *
     * <p>输入：s = "1234567890", k = 90 输出：34
     *
     * <p>提示：
     *
     * <p>1 <= s.length <= 10^5. s 只包含数字且不包含前导 0 。 1 <= k <= 10^9.
     *
     * @param s
     * @param k
     * @return
     */
    public int numberOfArrays(String s, int k) {
        int len = s.length();
        long[] dp = new long[len + 1];

        dp[len] = 1L;

        for (int i = len - 1; i >= 0; i--) {
            char c = s.charAt(i);
            if (c == '0') {
                dp[i] = 0;
                continue;
            }
            // 向后遍历
            long num = 0;
            for (int j = i; j < Math.min(i + 10, len); j++) {
                char c1 = s.charAt(j);
                num = num * 10 + (c1 - '0');
                if (num > k) {
                    break;
                }
                dp[i] += dp[j + 1];
                dp[i] %= MOD;
            }
        }

        return (int) (dp[0] % MOD);
    }

    /**
     * 1420. 生成数组
     *
     * <p>给你三个整数 n、m 和 k 。下图描述的算法用于找出正整数数组中最大的元素。
     *
     * <p>请你生成一个具有下述属性的数组 arr ：
     *
     * <p>arr 中有 n 个整数。 1 <= arr[i] <= m 其中 (0 <= i < n) 。 将上面提到的算法应用于 arr ，search_cost 的值等于 k 。
     * 返回上述条件下生成数组 arr 的 方法数 ，由于答案可能会很大，所以 必须 对 10^9 + 7 取余。
     *
     * <p>示例 1：
     *
     * <p>输入：n = 2, m = 3, k = 1 输出：6 解释：可能的数组分别为 [1, 1], [2, 1], [2, 2], [3, 1], [3, 2] [3, 3] 示例
     * 2：
     *
     * <p>输入：n = 5, m = 2, k = 3 输出：0 解释：没有数组可以满足上述条件 示例 3：
     *
     * <p>输入：n = 9, m = 1, k = 1 输出：1 解释：可能的数组只有 [1, 1, 1, 1, 1, 1, 1, 1, 1] 示例 4：
     *
     * <p>输入：n = 50, m = 100, k = 25 输出：34549172 解释：不要忘了对 1000000007 取余 示例 5：
     *
     * <p>输入：n = 37, m = 17, k = 7 输出：418930126
     *
     * <p>提示：
     *
     * <p>1 <= n <= 50 1 <= m <= 100 0 <= k <= n
     *
     * @param n
     * @param m
     * @param k
     * @return
     */
    public int numOfArrays(int n, int m, int k) {
        if (k > m || k == 0) {
            return 0;
        }
        // dp[i][j][s] 长度为 i  最大值为 j 搜索代价为 s 的数组的数量

        // 搜索代价不变  dp[i - 1][j][s] * j;
        // 搜索+1  sum(dp[i - 1][j0][s-1]) j0 < j ;
        // dp[i][j][s] = dp[i - 1][j][s] * j + sum(dp[i - 1][j0][s-1])
        long[][][] dp = new long[n + 1][m + 1][k + 1];

        for (int j = 1; j <= m; j++) {
            dp[1][j][1] = 1L;
        }

        for (int i = 2; i <= n; i++) {
            for (int s = 1; s <= k && s <= i; s++) {
                for (int j = 1; j <= m; j++) {

                    // 搜索代价不变
                    dp[i][j][s] += dp[i - 1][j][s] * j;
                    dp[i][j][s] %= MOD;
                    // 搜索+1
                    for (int j0 = 1; j0 < j; j0++) {
                        dp[i][j][s] += dp[i - 1][j0][s - 1];
                    }
                    dp[i][j][s] %= MOD;
                }
            }
        }
        int result = 0;
        for (int j = 1; j <= m; j++) {
            result += dp[n][j][k];
            result %= MOD;
        }

        return result;
    }

    /**
     * 1425. 带限制的子序列和
     *
     * <p>给你一个整数数组 nums 和一个整数 k ，请你返回 非空 子序列元素和的最大值，子序列需要满足：子序列中每两个 相邻 的整数 nums[i] 和 nums[j]
     * ，它们在原数组中的下标 i 和 j 满足 i < j 且 j - i <= k 。
     *
     * <p>数组的子序列定义为：将数组中的若干个数字删除（可以删除 0 个数字），剩下的数字按照原本的顺序排布。
     *
     * <p>示例 1：
     *
     * <p>输入：nums = [10,2,-10,5,20], k = 2 输出：37 解释：子序列为 [10, 2, 5, 20] 。 示例 2：
     *
     * <p>输入：nums = [-1,-2,-3], k = 1 输出：-1 解释：子序列必须是非空的，所以我们选择最大的数字。 示例 3：
     *
     * <p>输入：nums = [10,-2,-10,-5,20], k = 2 输出：23 解释：子序列为 [10, -2, -5, 20] 。
     *
     * <p>提示：
     *
     * <p>1 <= k <= nums.length <= 10^5 -10^4 <= nums[i] <= 10^4
     *
     * @param nums
     * @param k
     * @return
     */
    public int constrainedSubsetSum(int[] nums, int k) {
        int len = nums.length, result = Integer.MIN_VALUE;
        // 前i个元素 选择i的 最大子序列
        int[] dp = new int[len];
        // 参考 239. 滑动窗口最大值
        // 双端队列
        Deque<Integer> deque = new LinkedList<>();
        for (int i = 0; i < k; i++) {
            dp[i] = nums[i];
            if (!deque.isEmpty()) {
                dp[i] = Math.max(dp[i], deque.peekFirst() + nums[i]);
            }
            // 小于当前dp[i] 的 移除, 后面的都会使用更大的dp[i]
            while (!deque.isEmpty() && deque.peekLast() < dp[i]) {
                deque.removeLast();
            }
            deque.addLast(dp[i]);
            result = Math.max(dp[i], result);
        }
        // result[0] = deque.peekFirst();

        for (int i = k; i < len; i++) {
            dp[i] = nums[i];
            if (!deque.isEmpty()) {
                dp[i] = Math.max(dp[i], deque.peekFirst() + nums[i]);
            }
            // 移除 i - k
            if (!deque.isEmpty() && deque.peekFirst() == dp[i - k]) {
                deque.removeFirst();
            }

            // 小于当前dp[i] 的 移除, 后面的都会使用更大的dp[i]
            while (!deque.isEmpty() && deque.peekLast() < dp[i]) {
                deque.removeLast();
            }
            deque.addLast(dp[i]);
            result = Math.max(dp[i], result);
        }
        log.debug("dp:{}", dp);
        return result;
    }

    /**
     * 1434. 每个人戴不同帽子的方案数
     *
     * <p>总共有 n 个人和 40 种不同的帽子，帽子编号从 1 到 40 。
     *
     * <p>给你一个整数列表的列表 hats ，其中 hats[i] 是第 i 个人所有喜欢帽子的列表。
     *
     * <p>请你给每个人安排一顶他喜欢的帽子，确保每个人戴的帽子跟别人都不一样，并返回方案数。
     *
     * <p>由于答案可能很大，请返回它对 10^9 + 7 取余后的结果。
     *
     * <p>示例 1：
     *
     * <p>输入：hats = [[3,4],[4,5],[5]] 输出：1 解释：给定条件下只有一种方法选择帽子。 第一个人选择帽子 3，第二个人选择帽子 4，最后一个人选择帽子 5。 示例
     * 2：
     *
     * <p>输入：hats = [[3,5,1],[3,5]] 输出：4 解释：总共有 4 种安排帽子的方法： (3,5)，(5,3)，(1,3) 和 (1,5) 示例 3：
     *
     * <p>输入：hats = [[1,2,3,4],[1,2,3,4],[1,2,3,4],[1,2,3,4]] 输出：24 解释：每个人都可以从编号为 1 到 4 的帽子中选。
     * (1,2,3,4) 4 个帽子的排列方案数为 24 。 示例 4：
     *
     * <p>输入：hats = [[1,2,3],[2,3,5,6],[1,3,7,9],[1,8,9],[2,5,7]] 输出：111
     *
     * <p>提示：
     *
     * <p>n == hats.length 1 <= n <= 10 1 <= hats[i].length <= 40 1 <= hats[i][j] <= 40 hats[i]
     * 包含一个数字互不相同的整数列表。
     *
     * @param hats
     * @return
     */
    public int numberWays(List<List<Integer>> hats) {
        // 给 10 个 人 戴帽子 选择 40 种 帽子
        int n = hats.size(), maxHatId = 0;
        int state = 1 << n;
        Map<Integer, List<Integer>> colorPerson = new HashMap<>();

        for (int i = 0; i < n; i++) {
            for (int hat : hats.get(i)) {
                List<Integer> list = colorPerson.computeIfAbsent(hat, k -> new ArrayList<>());
                list.add(i);
                maxHatId = Math.max(maxHatId, hat);
            }
        }
        // dp[0][0] = 1;

        int[] dp = new int[state];
        dp[0] = 1;
        for (int hat = 1; hat <= maxHatId; hat++) {
            if (!colorPerson.containsKey(hat)) {
                continue;
            }
            // 帽子 hat
            int[] tmpDp = dp.clone();
            List<Integer> persons = colorPerson.get(hat);
            for (int i = 0; i < state; i++) {
                for (int p : persons) {
                    int num = 1 << p;
                    if ((i & num) != 0) {
                        dp[i] += tmpDp[i ^ num];
                        dp[i] %= MOD;
                    }
                }
            }
        }

        return dp[state - 1];
    }

    /**
     * 1444. 切披萨的方案数
     *
     * <p>给你一个 rows x cols 大小的矩形披萨和一个整数 k ，矩形包含两种字符： 'A' （表示苹果）和 '.' （表示空白格子）。你需要切披萨 k-1 次，得到 k
     * 块披萨并送给别人。
     *
     * <p>切披萨的每一刀，先要选择是向垂直还是水平方向切，再在矩形的边界上选一个切的位置，将披萨一分为二。如果垂直地切披萨，那么需要把左边的部分送给一个人，如果水平地切，那么需要把上面的部分送给一个人。在切完最后一刀后，需要把剩下来的一块送给最后一个人。
     *
     * <p>请你返回确保每一块披萨包含 至少 一个苹果的切披萨方案数。由于答案可能是个很大的数字，请你返回它对 10^9 + 7 取余的结果。
     *
     * <p>示例 1：
     *
     * <p>输入：pizza = ["A..","AAA","..."], k = 3 输出：3 解释：上图展示了三种切披萨的方案。注意每一块披萨都至少包含一个苹果。 示例 2：
     *
     * <p>输入：pizza = ["A..","AA.","..."], k = 3 输出：1 示例 3：
     *
     * <p>输入：pizza = ["A..","A..","..."], k = 1 输出：1
     *
     * <p>提示：
     *
     * <p>1 <= rows, cols <= 50 rows == pizza.length cols == pizza[i].length 1 <= k <= 10 pizza
     * 只包含字符 'A' 和 '.' 。
     *
     * @param pizza
     * @param k
     * @return
     */
    public int ways(String[] pizza, int k) {
        int rows = pizza.length, cols = pizza[0].length();

        //  左上角在i行j列 剩余的苹果
        int[][] apples = new int[rows + 1][cols + 1];
        for (int i = rows - 1; i >= 0; i--) {
            for (int j = cols - 1; j >= 0; j--) {
                apples[i][j] = apples[i + 1][j] + apples[i][j + 1] - apples[i + 1][j + 1];
                if (pizza[i].charAt(j) == 'A') {
                    apples[i][j]++;
                }
            }
        }
        // dp[i][j][k] 表示 剩余 左上角 i行j列 然后 切了k次
        int[][][] dp = new int[rows][cols][k];

        dp[0][0][0] = 1;
        // 切了n次
        for (int n = 1; n < k; n++) {
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    // 剩余苹果数量为 0
                    if (apples[i][j] == 0) {
                        continue;
                    }
                    // 剩余 左上角 i行j列
                    int count = 0;

                    // 横切 枚举 上一个 i1 切的是 i1 ~ i
                    for (int i1 = 0; i1 < i; i1++) {
                        // 横切的苹果数量
                        int num = apples[i1][j] - apples[i][j];
                        if (num == 0) {
                            continue;
                        }
                        count += dp[i1][j][n - 1];
                        count %= MOD;
                    }

                    // 竖切
                    for (int j1 = 0; j1 < j; j1++) {
                        // 竖切的苹果数量
                        int num = apples[i][j1] - apples[i][j];
                        if (num == 0) {
                            continue;
                        }
                        count += dp[i][j1][n - 1];
                        count %= MOD;
                    }

                    dp[i][j][n] = count;
                }
            }
        }
        int result = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result += dp[i][j][k - 1];
                result %= MOD;
            }
        }

        return result;
    }

    /**
     * 1463. 摘樱桃 II
     *
     * <p>给你一个 rows x cols 的矩阵 grid 来表示一块樱桃地。 grid 中每个格子的数字表示你能获得的樱桃数目。
     *
     * <p>你有两个机器人帮你收集樱桃，机器人 1 从左上角格子 (0,0) 出发，机器人 2 从右上角格子 (0, cols-1) 出发。
     *
     * <p>请你按照如下规则，返回两个机器人能收集的最多樱桃数目：
     *
     * <p>从格子 (i,j) 出发，机器人可以移动到格子 (i+1, j-1)，(i+1, j) 或者 (i+1, j+1) 。
     * 当一个机器人经过某个格子时，它会把该格子内所有的樱桃都摘走，然后这个位置会变成空格子，即没有樱桃的格子。 当两个机器人同时到达同一个格子时，它们中只有一个可以摘到樱桃。
     * 两个机器人在任意时刻都不能移动到 grid 外面。 两个机器人最后都要到达 grid 最底下一行。
     *
     * <p>示例 1：
     *
     * <p>输入：grid = [[3,1,1],[2,5,1],[1,5,5],[2,1,1]] 输出：24 解释：机器人 1 和机器人 2 的路径在上图中分别用绿色和蓝色表示。 机器人 1
     * 摘的樱桃数目为 (3 + 2 + 5 + 2) = 12 。 机器人 2 摘的樱桃数目为 (1 + 5 + 5 + 1) = 12 。 樱桃总数为： 12 + 12 = 24 。 示例
     * 2：
     *
     * <p>输入：grid =
     * [[1,0,0,0,0,0,1],[2,0,0,0,0,3,0],[2,0,9,0,0,0,0],[0,3,0,5,4,0,0],[1,0,2,3,0,0,6]] 输出：28
     * 解释：机器人 1 和机器人 2 的路径在上图中分别用绿色和蓝色表示。 机器人 1 摘的樱桃数目为 (1 + 9 + 5 + 2) = 17 。 机器人 2 摘的樱桃数目为 (1 + 3
     * + 4 + 3) = 11 。 樱桃总数为： 17 + 11 = 28 。 示例 3：
     *
     * <p>输入：grid = [[1,0,0,3],[0,0,0,3],[0,0,3,3],[9,0,3,3]] 输出：22 示例 4：
     *
     * <p>输入：grid = [[1,1],[1,1]] 输出：4
     *
     * <p>提示：
     *
     * <p>rows == grid.length cols == grid[i].length 2 <= rows, cols <= 70 0 <= grid[i][j] <= 100
     *
     * @param grid
     * @return
     */
    public int cherryPickup(int[][] grid) {
        int rows = grid.length, cols = grid[0].length;
        // dp[i][j1][j2] 表示第一个机器人从 (0, 0)走到 (i,j1)，第二个机器人从 (0, n-1)走到 (i, j2)最多能收集的樱桃数目
        int[][][] dp = new int[rows][cols][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                Arrays.fill(dp[i][j], -1);
            }
        }

        dp[0][0][cols - 1] = grid[0][0] + grid[0][cols - 1];

        for (int i = 1; i < rows; i++) {
            for (int j1 = 0; j1 < cols; j1++) {
                for (int j2 = j1 + 1; j2 < cols; j2++) {
                    // 枚举上一行
                    for (int lj1 = Math.max(j1 - 1, 0); lj1 <= Math.min(j1 + 1, cols - 1); lj1++) {

                        for (int lj2 = Math.max(j2 - 1, 0);
                                lj2 <= Math.min(j2 + 1, cols - 1);
                                lj2++) {
                            if (dp[i - 1][lj1][lj2] == -1) {
                                continue;
                            }

                            dp[i][j1][j2] =
                                    Math.max(
                                            dp[i][j1][j2],
                                            dp[i - 1][lj1][lj2]
                                                    + (j1 == j2
                                                            ? grid[i][j1]
                                                            : (grid[i][j1] + grid[i][j2])));
                        }
                    }
                }
            }
        }
        logResult(dp);
        int result = 0;
        for (int j1 = 0; j1 < cols; j1++) {
            for (int j2 = j1 + 1; j2 < cols; j2++) {
                result = Math.max(result, dp[rows - 1][j1][j2]);
            }
        }

        return result;
    }

    /**
     * 1478. 安排邮筒
     *
     * <p>给你一个房屋数组houses 和一个整数 k ，其中 houses[i] 是第 i 栋房子在一条街上的位置，现需要在这条街上安排 k 个邮筒。
     *
     * <p>请你返回每栋房子与离它最近的邮筒之间的距离的 最小 总和。
     *
     * <p>答案保证在 32 位有符号整数范围以内。
     *
     * <p>示例 1：
     *
     * <p>输入：houses = [1,4,8,10,20], k = 3 输出：5 解释：将邮筒分别安放在位置 3， 9 和 20 处。 每个房子到最近邮筒的距离和为 |3-1| +
     * |4-3| + |9-8| + |10-9| + |20-20| = 5 。 示例 2：
     *
     * <p>输入：houses = [2,3,5,12,18], k = 2 输出：9 解释：将邮筒分别安放在位置 3 和 14 处。 每个房子到最近邮筒距离和为 |2-3| + |3-3|
     * + |5-3| + |12-14| + |18-14| = 9 。 示例 3：
     *
     * <p>输入：houses = [7,4,6,1], k = 1 输出：8 示例 4：
     *
     * <p>输入：houses = [3,6,14,10], k = 4 输出：0
     *
     * <p>提示：
     *
     * <p>n == houses.length 1 <= n <= 100 1 <= houses[i] <= 10^4 1 <= k <= n 数组 houses 中的整数互不相同。
     *
     * @param houses
     * @param k
     * @return
     */
    public int minDistance(int[] houses, int k) {
        int n = houses.length;
        if (k == n) {
            return 0;
        }
        Arrays.sort(houses);
        if (k == 1) {
            return cost(houses, 0, n - 1);
        }
        int[][] costNums = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                costNums[i][j] = cost(houses, i, j);
            }
        }

        // 动态规划
        // dp[i][j] 表示 前 i 个房子 安排 j 个 邮筒
        int[][] dp = new int[n][k];

        for (int i = 0; i < n; i++) {
            dp[i][0] = costNums[0][i];
        }

        for (int j = 1; j < k; j++) {
            for (int i = j; i < n; i++) {
                int min = Integer.MAX_VALUE;
                // 枚举 前一个 房子的位置
                for (int last = 0; last < i; last++) {
                    min = Math.min(min, dp[last][j - 1] + costNums[last + 1][i]);
                }
                dp[i][j] = min;
            }
        }
        logResult(dp);
        return dp[n - 1][k - 1];
    }

    private int cost(int[] houses, int start, int end) {
        int result = 0;
        while (start < end) {
            result += houses[end] - houses[start];
            start++;
            end--;
        }
        return result;
    }

    /**
     * 1601. 最多可达成的换楼请求数目
     *
     * <p>我们有 n 栋楼，编号从 0 到 n - 1 。每栋楼有若干员工。由于现在是换楼的季节，部分员工想要换一栋楼居住。
     *
     * <p>给你一个数组 requests ，其中 requests[i] = [fromi, toi] ，表示一个员工请求从编号为 fromi 的楼搬到编号为 toi 的楼。
     *
     * <p>一开始 所有楼都是满的，所以从请求列表中选出的若干个请求是可行的需要满足 每栋楼员工净变化为 0 。意思是每栋楼 离开 的员工数目 等于 该楼 搬入 的员工数数目。比方说 n =
     * 3 且两个员工要离开楼 0 ，一个员工要离开楼 1 ，一个员工要离开楼 2 ，如果该请求列表可行，应该要有两个员工搬入楼 0 ，一个员工搬入楼 1 ，一个员工搬入楼 2 。
     *
     * <p>请你从原请求列表中选出若干个请求，使得它们是一个可行的请求列表，并返回所有可行列表中最大请求数目。
     *
     * <p>示例 1：
     *
     * <p>输入：n = 5, requests = [[0,1],[1,0],[0,1],[1,2],[2,0],[3,4]] 输出：5 解释：请求列表如下： 从楼 0 离开的员工为 x 和
     * y ，且他们都想要搬到楼 1 。 从楼 1 离开的员工为 a 和 b ，且他们分别想要搬到楼 2 和 0 。 从楼 2 离开的员工为 z ，且他想要搬到楼 0 。 从楼 3 离开的员工为
     * c ，且他想要搬到楼 4 。 没有员工从楼 4 离开。 我们可以让 x 和 b 交换他们的楼，以满足他们的请求。 我们可以让 y，a 和 z 三人在三栋楼间交换位置，满足他们的要求。
     * 所以最多可以满足 5 个请求。 示例 2：
     *
     * <p>输入：n = 3, requests = [[0,0],[1,2],[2,1]] 输出：3 解释：请求列表如下： 从楼 0 离开的员工为 x ，且他想要回到原来的楼 0 。 从楼
     * 1 离开的员工为 y ，且他想要搬到楼 2 。 从楼 2 离开的员工为 z ，且他想要搬到楼 1 。 我们可以满足所有的请求。 示例 3：
     *
     * <p>输入：n = 4, requests = [[0,3],[3,1],[1,2],[2,0]] 输出：4
     *
     * <p>提示：
     *
     * <p>1 <= n <= 20 1 <= requests.length <= 16 requests[i].length == 2 0 <= fromi, toi < n
     *
     * @param n
     * @param requests
     * @return
     */
    public int maximumRequests(int n, int[][] requests) {
        // dfs 枚举 requests (进行请求i和不进行请求i)
        this.intResult = 0;
        this.n = n;
        this.requests = requests;
        int len = requests.length;
        int[] floorChanges = new int[n];
        for (int i = 0; i < len; i++) {
            // 接受剩余请求后 比最大请求小 break
            if (len - i < intResult) {
                break;
            }
            dfsRequest(i, floorChanges, 1);
        }
        return intResult;
    }

    private int intResult;

    private int[][] requests;

    private void dfsRequest(int idx, int[] floorChanges, int num) {
        int len = requests.length;

        int from = requests[idx][0], to = requests[idx][1];
        floorChanges[from]--;
        floorChanges[to]++;
        if (checkRequest(floorChanges)) {
            intResult = Math.max(intResult, num);
        }

        for (int i = idx + 1; i < len; i++) {
            // 接受剩余请求后 比最大请求小 break
            if (num + len - i < intResult) {
                break;
            }

            dfsRequest(i, floorChanges, num + 1);
        }
        floorChanges[from]++;
        floorChanges[to]--;
    }

    private boolean checkRequest(int[] floorChanges) {
        for (int num : floorChanges) {
            if (num != 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * 1639. 通过给定词典构造目标字符串的方案数
     *
     * <p>给你一个字符串列表 words 和一个目标字符串 target 。words 中所有字符串都 长度相同 。
     *
     * <p>你的目标是使用给定的 words 字符串列表按照下述规则构造 target ：
     *
     * <p>从左到右依次构造 target 的每一个字符。 为了得到 target 第 i 个字符（下标从 0 开始），当 target[i] = words[j][k] 时，你可以使用
     * words 列表中第 j 个字符串的第 k 个字符。 一旦你使用了 words 中第 j 个字符串的第 k 个字符，你不能再使用 words 字符串列表中任意单词的第 x 个字符（x
     * <= k）。也就是说，所有单词下标小于等于 k 的字符都不能再被使用。 请你重复此过程直到得到目标字符串 target 。 请注意， 在构造目标字符串的过程中，你可以按照上述规定使用
     * words 列表中 同一个字符串 的 多个字符 。
     *
     * <p>请你返回使用 words 构造 target 的方案数。由于答案可能会很大，请对 109 + 7 取余 后返回。
     *
     * <p>（译者注：此题目求的是有多少个不同的 k 序列，详情请见示例。）
     *
     * <p>示例 1：
     *
     * <p>输入：words = ["acca","bbbb","caca"], target = "aba" 输出：6 解释：总共有 6 种方法构造目标串。 "aba" -> 下标为 0
     * ("acca")，下标为 1 ("bbbb")，下标为 3 ("caca") "aba" -> 下标为 0 ("acca")，下标为 2 ("bbbb")，下标为 3 ("caca")
     * "aba" -> 下标为 0 ("acca")，下标为 1 ("bbbb")，下标为 3 ("acca") "aba" -> 下标为 0 ("acca")，下标为 2
     * ("bbbb")，下标为 3 ("acca") "aba" -> 下标为 1 ("caca")，下标为 2 ("bbbb")，下标为 3 ("acca") "aba" -> 下标为 1
     * ("caca")，下标为 2 ("bbbb")，下标为 3 ("caca") 示例 2：
     *
     * <p>输入：words = ["abba","baab"], target = "bab" 输出：4 解释：总共有 4 种不同形成 target 的方法。 "bab" -> 下标为 0
     * ("baab")，下标为 1 ("baab")，下标为 2 ("abba") "bab" -> 下标为 0 ("baab")，下标为 1 ("baab")，下标为 3 ("baab")
     * "bab" -> 下标为 0 ("baab")，下标为 2 ("baab")，下标为 3 ("baab") "bab" -> 下标为 1 ("abba")，下标为 2
     * ("baab")，下标为 3 ("baab") 示例 3：
     *
     * <p>输入：words = ["abcd"], target = "abcd" 输出：1 示例 4：
     *
     * <p>输入：words = ["abab","baba","abba","baab"], target = "abba" 输出：16
     *
     * <p>提示：
     *
     * <p>1 <= words.length <= 1000 1 <= words[i].length <= 1000 words 中所有单词长度相同。 1 <= target.length
     * <= 1000 words[i] 和 target 都仅包含小写英文字母。
     *
     * @param words
     * @param target
     * @return
     */
    public int numWays(String[] words, String target) {
        int len = target.length(), wordLen = words[0].length();
        long[][] dp = new long[len + 1][wordLen + 1];
        int[][] letterNum = new int[wordLen][26];
        for (int i = 0; i < wordLen; i++) {
            for (String word : words) {
                char c = word.charAt(i);
                letterNum[i][c - 'a']++;
            }
        }
        Arrays.fill(dp[0], 1);
        for (int i = 0; i < len; i++) {
            int num = target.charAt(i) - 'a';
            for (int j = i; j < wordLen; j++) {
                // 前一位匹配
                dp[i + 1][j + 1] = dp[i + 1][j];
                // 当前 target 的字符 在 j 存在
                if (letterNum[j][num] > 0) {
                    dp[i + 1][j + 1] += dp[i][j] * letterNum[j][num] % MOD;
                    dp[i + 1][j + 1] %= MOD;
                }
            }
        }
        logResult(dp);
        return (int) (dp[len][wordLen] % MOD);
    }

    /**
     * 1655. 分配重复整数
     *
     * <p>给你一个长度为 n 的整数数组 nums ，这个数组中至多有 50 个不同的值。同时你有 m 个顾客的订单 quantity ，其中，整数 quantity[i] 是第 i
     * 位顾客订单的数目。请你判断是否能将 nums 中的整数分配给这些顾客，且满足：
     *
     * <p>第 i 位顾客 恰好 有 quantity[i] 个整数。 第 i 位顾客拿到的整数都是 相同的 。 每位顾客都满足上述两个要求。 如果你可以分配 nums
     * 中的整数满足上面的要求，那么请返回 true ，否则返回 false 。
     *
     * <p>示例 1：
     *
     * <p>输入：nums = [1,2,3,4], quantity = [2] 输出：false 解释：第 0 位顾客没办法得到两个相同的整数。 示例 2：
     *
     * <p>输入：nums = [1,2,3,3], quantity = [2] 输出：true 解释：第 0 位顾客得到 [3,3] 。整数 [1,2] 都没有被使用。 示例 3：
     *
     * <p>输入：nums = [1,1,2,2], quantity = [2,2] 输出：true 解释：第 0 位顾客得到 [1,1] ，第 1 位顾客得到 [2,2] 。 示例 4：
     *
     * <p>输入：nums = [1,1,2,3], quantity = [2,2] 输出：false 解释：尽管第 0 位顾客可以得到 [1,1] ，第 1 位顾客没法得到 2
     * 个一样的整数。 示例 5：
     *
     * <p>输入：nums = [1,1,1,1,1], quantity = [2,3] 输出：true 解释：第 0 位顾客得到 [1,1] ，第 1 位顾客得到 [1,1,1] 。
     *
     * <p>提示：
     *
     * <p>n == nums.length 1 <= n <= 105 1 <= nums[i] <= 1000 m == quantity.length 1 <= m <= 10 1 <=
     * quantity[i] <= 105 nums 中至多有 50 个不同的数字。
     *
     * @param nums
     * @param quantity
     * @return
     */
    public boolean canDistribute(int[] nums, int[] quantity) {

        int m = quantity.length, n = nums.length;

        // nums 中至多有 50 个不同的数字
        int[] cnts = new int[50];
        Arrays.sort(nums);
        int numIdx = 0;
        for (int i = 0; i < n; i++) {
            if (i != 0 && nums[i] != nums[i - 1]) {
                numIdx++;
            }
            cnts[numIdx]++;
        }
        log.debug("cnts:{}", cnts);
        if (m == 1) {
            return Arrays.stream(cnts).anyMatch(a -> a >= quantity[0]);
        }

        int maxState = 1 << m;
        int[] qtys = new int[maxState];

        for (int i = 1; i < maxState; i++) {
            for (int j = 0; j < m; j++) {
                if ((i & (1 << j)) == 0) {
                    continue;
                }
                qtys[i] = qtys[i - (1 << j)] + quantity[j];
                break;
            }
        }

        boolean[][] dp = new boolean[numIdx + 1][maxState];
        for (int i = 0; i <= numIdx; i++) {
            dp[i][0] = true;
        }
        for (int i = 0; i <= numIdx; i++) {
            int cnt = cnts[i];
            for (int j = 1; j < maxState; j++) {
                if (i > 0 && dp[i - 1][j]) {
                    dp[i][j] = true;
                    continue;
                }

                int qty = qtys[j];
                if (i == 0) {
                    dp[i][j] = cnt >= qty;
                    continue;
                }
                for (int k = j; k != 0; k = ((k - 1) & j)) {
                    int prev = j - k;
                    if (dp[i - 1][prev] && cnt >= qtys[k]) {
                        dp[i][j] = true;
                        break;
                    }
                }
            }
        }
        logResult(dp);

        return dp[numIdx][maxState - 1];
    }

    /**
     * 1659. 最大化网格幸福感
     *
     * <p>给你四个整数 m、n、introvertsCount 和 extrovertsCount 。有一个 m x n 网格，和两种类型的人：内向的人和外向的人。总共有
     * introvertsCount 个内向的人和 extrovertsCount 个外向的人。
     *
     * <p>请你决定网格中应当居住多少人，并为每个人分配一个网格单元。 注意，不必 让所有人都生活在网格中。
     *
     * <p>每个人的 幸福感 计算如下：
     *
     * <p>内向的人 开始 时有 120 个幸福感，但每存在一个邻居（内向的或外向的）他都会 失去 30 个幸福感。 外向的人 开始 时有 40
     * 个幸福感，每存在一个邻居（内向的或外向的）他都会 得到 20 个幸福感。 邻居是指居住在一个人所在单元的上、下、左、右四个直接相邻的单元中的其他人。
     *
     * <p>网格幸福感 是每个人幸福感的 总和 。 返回 最大可能的网格幸福感 。
     *
     * <p>示例 1：
     *
     * <p>输入：m = 2, n = 3, introvertsCount = 1, extrovertsCount = 2 输出：240 解释：假设网格坐标 (row, column) 从
     * 1 开始编号。 将内向的人放置在单元 (1,1) ，将外向的人放置在单元 (1,3) 和 (2,3) 。 - 位于 (1,1) 的内向的人的幸福感：120（初始幸福感）- (0 *
     * 30)（0 位邻居）= 120 - 位于 (1,3) 的外向的人的幸福感：40（初始幸福感）+ (1 * 20)（1 位邻居）= 60 - 位于 (2,3)
     * 的外向的人的幸福感：40（初始幸福感）+ (1 * 20)（1 位邻居）= 60 网格幸福感为：120 + 60 + 60 = 240
     * 上图展示该示例对应网格中每个人的幸福感。内向的人在浅绿色单元中，而外向的人在浅紫色单元中。 示例 2：
     *
     * <p>输入：m = 3, n = 1, introvertsCount = 2, extrovertsCount = 1 输出：260 解释：将内向的人放置在单元 (1,1) 和
     * (3,1) ，将外向的人放置在单元 (2,1) 。 - 位于 (1,1) 的内向的人的幸福感：120（初始幸福感）- (1 * 30)（1 位邻居）= 90 - 位于 (2,1)
     * 的外向的人的幸福感：40（初始幸福感）+ (2 * 20)（2 位邻居）= 80 - 位于 (3,1) 的内向的人的幸福感：120（初始幸福感）- (1 * 30)（1 位邻居）= 90
     * 网格幸福感为 90 + 80 + 90 = 260 示例 3：
     *
     * <p>输入：m = 2, n = 2, introvertsCount = 4, extrovertsCount = 0 输出：240
     *
     * <p>提示：
     *
     * <p>1 <= m, n <= 5 0 <= introvertsCount, extrovertsCount <= min(m * n, 6)
     *
     * @param m
     * @param n
     * @param introvertsCount
     * @param extrovertsCount
     * @return
     */
    public int getMaxGridHappiness(int m, int n, int introvertsCount, int extrovertsCount) {

        this.m = m;
        this.n = n;
        // 按行进行dp 每行最大state 3 ^ n
        int maxState = 1;

        for (int i = 0; i < n; i++) {
            maxState *= 3;
        }
        this.mod = maxState / 3;
        // dp[m n] [in][ex][lastState]。 表示：现在在 m, n 行，还有 in 个内向的人和 ex 个外向的人没有安排；
        // 同时，之前 n 个格子的安排方式是 lastState
        // lastState 当前位置 前 n 个 位置的状态  左边 是 最后一个3  上边是第1个3
        this.dp = new int[m * n][introvertsCount + 1][extrovertsCount + 1][maxState];

        return dfsGridHappiness(0, introvertsCount, extrovertsCount, 0);
    }

    private int[][][][] dp;

    private int mod;

    private int dfsGridHappiness(int idx, int in, int ex, int lastState) {
        if (idx == m * n) {
            return 0;
        }
        if (dp[idx][in][ex][lastState] != 0) {
            return dp[idx][in][ex][lastState];
        }
        int row = idx / n, col = idx % n;
        int upState = lastState / mod, leftState = lastState % 3;

        int curState = (lastState % mod) * 3;
        // 不安排人
        int result = dfsGridHappiness(idx + 1, in, ex, curState);
        // 安排内向的人
        if (in > 0) {
            int score = 120;
            if (row > 0 && upState != 0) {
                score -= 30;
                // 上方是 内向的人 -30 外向的人 +20
                score += upState == 1 ? -30 : 20;
            }
            if (col > 0 && leftState != 0) {
                score -= 30;
                // 上方是 内向的人 -30 外向的人 +20
                score += leftState == 1 ? -30 : 20;
            }
            result = Math.max(result, score + dfsGridHappiness(idx + 1, in - 1, ex, curState + 1));
        }

        // 安排外向的人
        if (ex > 0) {
            int score = 40;
            if (row > 0 && upState != 0) {
                score += 20;
                // 上方是 内向的人 -30 外向的人 +20
                score += upState == 1 ? -30 : 20;
            }
            if (col > 0 && leftState != 0) {
                score += 20;
                // 上方是 内向的人 -30 外向的人 +20
                score += leftState == 1 ? -30 : 20;
            }
            result = Math.max(result, score + dfsGridHappiness(idx + 1, in, ex - 1, curState + 2));
        }

        return dp[idx][in][ex][lastState] = result;
    }

    /**
     * 1691. 堆叠长方体的最大高度
     *
     * <p>给你 n 个长方体 cuboids ，其中第 i 个长方体的长宽高表示为 cuboids[i] = [widthi, lengthi, heighti]（下标从 0 开始）。请你从
     * cuboids 选出一个 子集 ，并将它们堆叠起来。
     *
     * <p>如果 widthi <= widthj 且 lengthi <= lengthj 且 heighti <= heightj ，你就可以将长方体 i 堆叠在长方体 j
     * 上。你可以通过旋转把长方体的长宽高重新排列，以将它放在另一个长方体上。
     *
     * <p>返回 堆叠长方体 cuboids 可以得到的 最大高度 。
     *
     * <p>示例 1：
     *
     * <p>输入：cuboids = [[50,45,20],[95,37,53],[45,23,12]] 输出：190 解释： 第 1 个长方体放在底部，53x37 的一面朝下，高度为 95
     * 。 第 0 个长方体放在中间，45x20 的一面朝下，高度为 50 。 第 2 个长方体放在上面，23x12 的一面朝下，高度为 45 。 总高度是 95 + 50 + 45 = 190
     * 。 示例 2：
     *
     * <p>输入：cuboids = [[38,25,45],[76,35,3]] 输出：76 解释： 无法将任何长方体放在另一个上面。 选择第 1 个长方体然后旋转它，使 35x3
     * 的一面朝下，其高度为 76 。 示例 3：
     *
     * <p>输入：cuboids = [[7,11,17],[7,17,11],[11,7,17],[11,17,7],[17,7,11],[17,11,7]] 输出：102 解释：
     * 重新排列长方体后，可以看到所有长方体的尺寸都相同。 你可以把 11x7 的一面朝下，这样它们的高度就是 17 。 堆叠长方体的最大高度为 6 * 17 = 102 。
     *
     * <p>提示：
     *
     * <p>n == cuboids.length 1 <= n <= 100 1 <= widthi, lengthi, heighti <= 100
     *
     * @param cuboids
     * @return
     */
    public int maxHeight(int[][] cuboids) {
        for (int[] cuboid : cuboids) {
            Arrays.sort(cuboid);
        }
        Arrays.sort(
                cuboids,
                (a, b) -> {
                    if (a[0] != b[0]) {
                        return Integer.compare(a[0], b[0]);
                    } else if (a[1] != b[1]) {
                        return Integer.compare(a[1], b[1]);
                    }
                    return Integer.compare(a[2], b[2]);
                });

        int len = cuboids.length, result = 0;
        int[] dp = new int[len];

        for (int i = 0; i < len; i++) {
            int height = 0;
            for (int j = 0; j < i; j++) {
                if (cuboids[i][2] >= cuboids[j][2] && cuboids[i][1] >= cuboids[j][1]) {
                    height = Math.max(height, dp[j]);
                }
            }
            height += cuboids[i][2];
            dp[i] = height;
            result = Math.max(result, height);
        }
        log.debug("dp:{}", dp);

        return result;
    }

    /**
     * 1687. 从仓库到码头运输箱子
     *
     * <p>你有一辆货运卡车，你需要用这一辆车把一些箱子从仓库运送到码头。这辆卡车每次运输有 箱子数目的限制 和 总重量的限制 。
     *
     * <p>给你一个箱子数组 boxes 和三个整数 portsCount, maxBoxes 和 maxWeight ，其中 boxes[i] = [portsi, weighti] 。
     *
     * <p>portsi 表示第 i 个箱子需要送达的码头， weightsi 是第 i 个箱子的重量。 portsCount 是码头的数目。 maxBoxes 和 maxWeight
     * 分别是卡车每趟运输箱子数目和重量的限制。 箱子需要按照 数组顺序 运输，同时每次运输需要遵循以下步骤：
     *
     * <p>卡车从 boxes 队列中按顺序取出若干个箱子，但不能违反 maxBoxes 和 maxWeight 限制。 对于在卡车上的箱子，我们需要 按顺序 处理它们，卡车会通过 一趟行程
     * 将最前面的箱子送到目的地码头并卸货。如果卡车已经在对应的码头，那么不需要 额外行程 ，箱子也会立马被卸货。 卡车上所有箱子都被卸货后，卡车需要 一趟行程
     * 回到仓库，从箱子队列里再取出一些箱子。 卡车在将所有箱子运输并卸货后，最后必须回到仓库。
     *
     * <p>请你返回将所有箱子送到相应码头的 最少行程 次数。
     *
     * <p>示例 1：
     *
     * <p>输入：boxes = [[1,1],[2,1],[1,1]], portsCount = 2, maxBoxes = 3, maxWeight = 3 输出：4
     * 解释：最优策略如下： - 卡车将所有箱子装上车，到达码头 1 ，然后去码头 2 ，然后再回到码头 1 ，最后回到仓库，总共需要 4 趟行程。 所以总行程数为 4 。
     * 注意到第一个和第三个箱子不能同时被卸货，因为箱子需要按顺序处理（也就是第二个箱子需要先被送到码头 2 ，然后才能处理第三个箱子）。 示例 2：
     *
     * <p>输入：boxes = [[1,2],[3,3],[3,1],[3,1],[2,4]], portsCount = 3, maxBoxes = 3, maxWeight = 6
     * 输出：6 解释：最优策略如下： - 卡车首先运输第一个箱子，到达码头 1 ，然后回到仓库，总共 2 趟行程。 - 卡车运输第二、第三、第四个箱子，到达码头 3 ，然后回到仓库，总共 2
     * 趟行程。 - 卡车运输第五个箱子，到达码头 3 ，回到仓库，总共 2 趟行程。 总行程数为 2 + 2 + 2 = 6 。 示例 3：
     *
     * <p>输入：boxes = [[1,4],[1,2],[2,1],[2,1],[3,2],[3,4]], portsCount = 3, maxBoxes = 6, maxWeight
     * = 7 输出：6 解释：最优策略如下： - 卡车运输第一和第二个箱子，到达码头 1 ，然后回到仓库，总共 2 趟行程。 - 卡车运输第三和第四个箱子，到达码头 2 ，然后回到仓库，总共
     * 2 趟行程。 - 卡车运输第五和第六个箱子，到达码头 3 ，然后回到仓库，总共 2 趟行程。 总行程数为 2 + 2 + 2 = 6 。 示例 4：
     *
     * <p>输入：boxes = [[2,4],[2,5],[3,1],[3,2],[3,7],[3,1],[4,4],[1,3],[5,2]], portsCount = 5,
     * maxBoxes = 5, maxWeight = 7 输出：14 解释：最优策略如下： - 卡车运输第一个箱子，到达码头 2 ，然后回到仓库，总共 2 趟行程。 -
     * 卡车运输第二个箱子，到达码头 2 ，然后回到仓库，总共 2 趟行程。 - 卡车运输第三和第四个箱子，到达码头 3 ，然后回到仓库，总共 2 趟行程。 - 卡车运输第五个箱子，到达码头 3
     * ，然后回到仓库，总共 2 趟行程。 - 卡车运输第六和第七个箱子，到达码头 3 ，然后去码头 4 ，然后回到仓库，总共 3 趟行程。 - 卡车运输第八和第九个箱子，到达码头 1
     * ，然后去码头 5 ，然后回到仓库，总共 3 趟行程。 总行程数为 2 + 2 + 2 + 2 + 3 + 3 = 14 。
     *
     * <p>提示：
     *
     * <p>1 <= boxes.length <= 105 1 <= portsCount, maxBoxes, maxWeight <= 105 1 <= portsi <=
     * portsCount 1 <= weightsi <= maxWeight
     *
     * @param boxes
     * @param portsCount
     * @param maxBoxes
     * @param maxWeight
     * @return
     */
    public int boxDelivering(int[][] boxes, int portsCount, int maxBoxes, int maxWeight) {

        // 滑动窗口
        int len = boxes.length;
        // 第 i 个送达码头所需的 最少行程 次数
        int[] dp = new int[len + 1];
        dp[1] = 2;
        int boxNum = 1, costNum = 2, weight = boxes[0][1];
        int left = 0;
        for (int i = 1; i < len; i++) {

            boxNum++;
            weight += boxes[i][1];
            // 不同的 终点
            if (boxes[i][0] != boxes[i - 1][0]) {
                costNum++;
            }
            while (weight > maxWeight || boxNum > maxBoxes || dp[left] == dp[left + 1]) {
                boxNum--;
                weight -= boxes[left][1];
                if (boxes[left][0] != boxes[left + 1][0]) {
                    costNum--;
                }
                left++;
            }

            dp[i + 1] = dp[left] + costNum;
        }
        log.debug("dp:{}", dp);
        return dp[len];
    }

    /**
     * 1815. 得到新鲜甜甜圈的最多组数
     *
     * <p>有一个甜甜圈商店，每批次都烤 batchSize 个甜甜圈。这个店铺有个规则，就是在烤一批新的甜甜圈时，之前 所有 甜甜圈都必须已经全部销售完毕。给你一个整数 batchSize
     * 和一个整数数组 groups ，数组中的每个整数都代表一批前来购买甜甜圈的顾客，其中 groups[i] 表示这一批顾客的人数。每一位顾客都恰好只要一个甜甜圈。
     *
     * <p>当有一批顾客来到商店时，他们所有人都必须在下一批顾客来之前购买完甜甜圈。如果一批顾客中第一位顾客得到的甜甜圈不是上一组剩下的，那么这一组人都会很开心。
     *
     * <p>你可以随意安排每批顾客到来的顺序。请你返回在此前提下，最多 有多少组人会感到开心。
     *
     * <p>示例 1：
     *
     * <p>输入：batchSize = 3, groups = [1,2,3,4,5,6] 输出：4 解释：你可以将这些批次的顾客顺序安排为 [6,2,4,5,1,3] 。那么第
     * 1，2，4，6 组都会感到开心。 示例 2：
     *
     * <p>输入：batchSize = 4, groups = [1,3,2,5,2,2,1,6] 输出：4
     *
     * <p>提示：
     *
     * <p>1 <= batchSize <= 9 1 <= groups.length <= 30 1 <= groups[i] <= 109
     *
     * @param batchSize
     * @param groups
     * @return
     */
    public int maxHappyGroups(int batchSize, int[] groups) {
        int len = groups.length;
        if (batchSize == 1) {
            return len;
        }
        int[] modNums = new int[batchSize];
        // 求余
        for (int g : groups) {
            int mod = g % batchSize;
            modNums[mod]++;
        }
        return modNums[0] + dfsHappyGroups(0, modNums, len - modNums[0]);
    }

    private static Map<String, Integer> happyGroupCount = new HashMap<>();

    private int dfsHappyGroups(int remainVal, int[] modNums, int remainCount) {
        if (remainCount == 0) {
            return 0;
        }
        int result = 0;
        int batchSize = modNums.length;
        String key = Arrays.toString(modNums) + remainVal;
        if (Objects.nonNull(happyGroupCount.get(key))) {
            return happyGroupCount.get(key);
        }

        if (remainVal == 0) {
            result++;
        } else if (remainVal < 0) {
            remainVal += batchSize;
        }
        int max = 0;
        if (remainVal != 0 && modNums[remainVal] != 0) {
            modNums[remainVal]--;
            int dfsResult = dfsHappyGroups(0, modNums, remainCount - 1);
            modNums[remainVal]++;
            max = Math.max(max, dfsResult);
        } else {
            // 遍历所有余数
            for (int i = 1; i < batchSize; i++) {
                if (modNums[i] == 0) {
                    continue;
                }
                modNums[i]--;
                int dfsResult = dfsHappyGroups(remainVal - i, modNums, remainCount - 1);
                modNums[i]++;
                max = Math.max(max, dfsResult);
            }
        }
        result += max;
        happyGroupCount.put(key, result);
        return result;
    }

    /**
     * 1397. 找到所有好字符串
     *
     * <p>给你两个长度为 n 的字符串 s1 和 s2 ，以及一个字符串 evil 。请你返回 好字符串 的数目。
     *
     * <p>好字符串 的定义为：它的长度为 n ，字典序大于等于 s1 ，字典序小于等于 s2 ，且不包含 evil 为子字符串。
     *
     * <p>由于答案可能很大，请你返回答案对 10^9 + 7 取余的结果。
     *
     * <p>示例 1：
     *
     * <p>输入：n = 2, s1 = "aa", s2 = "da", evil = "b" 输出：51 解释：总共有 25 个以 'a'
     * 开头的好字符串："aa"，"ac"，"ad"，...，"az"。还有 25 个以 'c' 开头的好字符串："ca"，"cc"，"cd"，...，"cz"。最后，还有一个以 'd'
     * 开头的好字符串："da"。 示例 2：
     *
     * <p>输入：n = 8, s1 = "leetcode", s2 = "leetgoes", evil = "leet" 输出：0 解释：所有字典序大于等于 s1 且小于等于 s2
     * 的字符串都以 evil 字符串 "leet" 开头。所以没有好字符串。 示例 3：
     *
     * <p>输入：n = 2, s1 = "gx", s2 = "gz", evil = "x" 输出：2
     *
     * <p>提示：
     *
     * <p>s1.length == n s2.length == n s1 <= s2 1 <= n <= 500 1 <= evil.length <= 50
     * 所有字符串都只包含小写英文字母。
     *
     * @param n
     * @param s1
     * @param s2
     * @param evil
     * @return
     */
    public int findGoodStrings(int n, String s1, String s2, String evil) {
        int m = evil.length();
        // 0表示s1和s2都有限制，1表s1有限制， 2表示s2有限制， 3表示s1和s2无限制
        int[][][] dp = new int[n + 1][m + 1][4];

        for (int j = 0; j < m; j++) {
            dp[n][j][0] = 1;
            dp[n][j][1] = 1;

            dp[n][j][2] = 1;
            dp[n][j][3] = 1;
        }
        this.prefix = getPrefix(evil);
        for (int i = n - 1; i >= 0; i--) {
            char c1 = s1.charAt(i), c2 = s2.charAt(i);
            for (int j = 0; j < m; j++) {
                // s1和s2都有限制
                for (char c = c1; c <= c2; c++) {
                    int nextState;
                    if (c == c1 && c == c2) {
                        nextState = 0;
                    } else if (c == c1) {
                        nextState = 1;
                    } else if (c == c2) {
                        nextState = 2;
                    } else {
                        nextState = 3;
                    }
                    dp[i][j][0] += dp[i + 1][getNext(evil, c, j)][nextState];
                    dp[i][j][0] %= MOD;
                }
                // s1有限制
                for (char c = c1; c <= 'z'; c++) {
                    int nextState = c == c1 ? 1 : 3;
                    dp[i][j][1] += dp[i + 1][getNext(evil, c, j)][nextState];
                    dp[i][j][1] %= MOD;
                }

                // s2有限制
                for (char c = 'a'; c <= c2; c++) {
                    int nextState = c == c2 ? 2 : 3;
                    dp[i][j][2] += dp[i + 1][getNext(evil, c, j)][nextState];
                    dp[i][j][2] %= MOD;
                }

                // s1和s2无限制
                for (char c = 'a'; c <= 'z'; c++) {
                    int nextState = 3;
                    dp[i][j][3] += dp[i + 1][getNext(evil, c, j)][nextState];
                    dp[i][j][3] %= MOD;
                }
            }
        }

        return dp[0][0][0];
    }

    private int[] getPrefix(String evil) {
        int len = evil.length();
        int[] prefix = new int[len];

        int j = 0; // len of match string 表示匹配的长度
        for (int i = 1; i < len; i++) {
            while (j > 0 && evil.charAt(i) != evil.charAt(j)) {
                j = prefix[j - 1];
            }
            if (evil.charAt(i) == evil.charAt(j)) {
                j++;
            }
            prefix[i] = j;
        }
        return prefix;
    }

    private int getNext(String evil, char c, int j) {
        while (j > 0 && c != evil.charAt(j)) {
            j = prefix[j - 1];
        }
        if (c == evil.charAt(j)) {
            j++;
        }
        return j;
    }

    int[] prefix;

    public int findGoodStrings2(int n, String s1, String s2, String evil) {
        int mod = (int) 1e9 + 7;
        int m = evil.length();
        int[][][] dp =
                new int[n + 1][4][m + 1]; // 第二维度中， 0表示s1和s2都有限制，1表s1有限制， 2表示s2有限制， 3表示s1和s2无限制；
        // 第三维度表示前面已经匹配的evil的长度
        // 初始化
        for (int i = 0; i < m; i++) {
            dp[n][0][i] = 1;
            dp[n][1][i] = 1;
            dp[n][2][i] = 1;
            dp[n][3][i] = 1;
        }
        char[] p = evil.toCharArray();
        this.prefix = getPrefix(evil); // O(n)，计算前缀数组
        for (int i = n - 1; i >= 0; i--) {
            for (int j = 0; j < m; j++) {
                // handle 0
                for (char k = s1.charAt(i); k <= s2.charAt(i); k++) {
                    int state = 0;
                    if (k == s1.charAt(i) && k == s2.charAt(i)) {
                        state = 0;
                    } else if (k == s1.charAt(i)) {
                        state = 1;
                    } else if (k == s2.charAt(i)) {
                        state = 2;
                    } else {
                        state = 3;
                    }
                    dp[i][0][j] += dp[i + 1][state][getNext(evil, k, j)];
                    dp[i][0][j] %= mod;
                }
                // handle 1
                for (char k = s1.charAt(i); k <= 'z'; k++) {
                    int state = k == s1.charAt(i) ? 1 : 3;
                    dp[i][1][j] += dp[i + 1][state][getNext(evil, k, j)];
                    dp[i][1][j] %= mod;
                }
                // handle 2
                for (char k = 'a'; k <= s2.charAt(i); k++) {
                    int state = k == s2.charAt(i) ? 2 : 3;
                    dp[i][2][j] += dp[i + 1][state][getNext(evil, k, j)];
                    dp[i][2][j] %= mod;
                }
                // handle 3
                for (char k = 'a'; k <= 'z'; k++) {
                    int state = 3;
                    dp[i][3][j] += dp[i + 1][state][getNext(evil, k, j)];
                    dp[i][3][j] %= mod;
                }
            }
        }
        return dp[0][0][0];
    }

    /**
     * 1866. 恰有 K 根木棍可以看到的排列数目
     *
     * <p>有 n 根长度互不相同的木棍，长度为从 1 到 n 的整数。请你将这些木棍排成一排，并满足从左侧 可以看到 恰好 k 根木棍。从左侧 可以看到 木棍的前提是这个木棍的 左侧
     * 不存在比它 更长的 木棍。
     *
     * <p>例如，如果木棍排列为 [1,3,2,5,4] ，那么从左侧可以看到的就是长度分别为 1、3 、5 的木棍。 给你 n 和 k ，返回符合题目要求的排列 数目
     * 。由于答案可能很大，请返回对 109 + 7 取余 的结果。
     *
     * <p>示例 1：
     *
     * <p>输入：n = 3, k = 2 输出：3 解释：[1,3,2], [2,3,1] 和 [2,1,3] 是仅有的能满足恰好 2 根木棍可以看到的排列。
     * 可以看到的木棍已经用粗体+斜体标识。 示例 2：
     *
     * <p>输入：n = 5, k = 5 输出：1 解释：[1,2,3,4,5] 是唯一一种能满足全部 5 根木棍可以看到的排列。 可以看到的木棍已经用粗体+斜体标识。 示例 3：
     *
     * <p>输入：n = 20, k = 11 输出：647427950 解释：总共有 647427950 (mod 109 + 7) 种能满足恰好有 11 根木棍可以看到的排列。
     *
     * <p>提示：
     *
     * <p>1 <= n <= 1000 1 <= k <= n
     *
     * @param n
     * @param k
     * @return
     */
    public int rearrangeSticks(int n, int k) {
        // dp[i][j] 长度位 i 时 可以看到 j 个木棍 的 方案shu
        long[][] dp = new long[n + 1][k + 1];
        dp[0][0] = 1L;
        for (int i = 1; i <= n; i++) {

            for (int j = 1; j <= k; j++) {
                dp[i][j] = (dp[i - 1][j - 1] + (i - 1) * dp[i - 1][j]) % MOD;
            }
        }

        return (int) dp[n][k];
    }

    /**
     * 1872. 石子游戏 VIII
     *
     * <p>Alice 和 Bob 玩一个游戏，两人轮流操作， Alice 先手 。
     *
     * <p>总共有 n 个石子排成一行。轮到某个玩家的回合时，如果石子的数目 大于 1 ，他将执行以下操作：
     *
     * <p>选择一个整数 x > 1 ，并且 移除 最左边的 x 个石子。 将 移除 的石子价值之 和 累加到该玩家的分数中。 将一个 新的石子 放在最左边，且新石子的值为被移除石子值之和。
     * 当只剩下 一个 石子时，游戏结束。
     *
     * <p>Alice 和 Bob 的 分数之差 为 (Alice 的分数 - Bob 的分数) 。 Alice 的目标是 最大化 分数差，Bob 的目标是 最小化 分数差。
     *
     * <p>给你一个长度为 n 的整数数组 stones ，其中 stones[i] 是 从左边起 第 i 个石子的价值。请你返回在双方都采用 最优 策略的情况下，Alice 和 Bob 的
     * 分数之差 。
     *
     * <p>示例 1：
     *
     * <p>输入：stones = [-1,2,-3,4,-5] 输出：5 解释： - Alice 移除最左边的 4 个石子，得分增加 (-1) + 2 + (-3) + 4 = 2
     * ，并且将一个价值为 2 的石子放在最左边。stones = [2,-5] 。 - Bob 移除最左边的 2 个石子，得分增加 2 + (-5) = -3 ，并且将一个价值为 -3
     * 的石子放在最左边。stones = [-3] 。 两者分数之差为 2 - (-3) = 5 。 示例 2：
     *
     * <p>输入：stones = [7,-6,5,10,5,-2,-6] 输出：13 解释： - Alice 移除所有石子，得分增加 7 + (-6) + 5 + 10 + 5 + (-2)
     * + (-6) = 13 ，并且将一个价值为 13 的石子放在最左边。stones = [13] 。 两者分数之差为 13 - 0 = 13 。 示例 3：
     *
     * <p>输入：stones = [-10,-12] 输出：-22 解释： - Alice 只有一种操作，就是移除所有石子。得分增加 (-10) + (-12) = -22
     * ，并且将一个价值为 -22 的石子放在最左边。stones = [-22] 。 两者分数之差为 (-22) - 0 = -22 。
     *
     * <p>提示：
     *
     * <p>n == stones.length 2 <= n <= 105 -104 <= stones[i] <= 104
     *
     * @param stones
     * @return
     */
    public int stoneGameVIII(int[] stones) {
        int n = stones.length;
        int sum = 0;
        for (int stone : stones) {
            sum += stone;
        }
        int result = sum;

        for (int i = n - 1; i >= 2; i--) {
            sum -= stones[i];
            result = Math.max(result, sum - result);
        }

        return result;
    }

    /**
     * 1879. 两个数组最小的异或值之和
     *
     * <p>给你两个整数数组 nums1 和 nums2 ，它们长度都为 n 。
     *
     * <p>两个数组的 异或值之和 为 (nums1[0] XOR nums2[0]) + (nums1[1] XOR nums2[1]) + ... + (nums1[n - 1] XOR
     * nums2[n - 1]) （下标从 0 开始）。
     *
     * <p>比方说，[1,2,3] 和 [3,2,1] 的 异或值之和 等于 (1 XOR 3) + (2 XOR 2) + (3 XOR 1) = 2 + 0 + 2 = 4 。 请你将
     * nums2 中的元素重新排列，使得 异或值之和 最小 。
     *
     * <p>请你返回重新排列之后的 异或值之和 。
     *
     * <p>示例 1：
     *
     * <p>输入：nums1 = [1,2], nums2 = [2,3] 输出：2 解释：将 nums2 重新排列得到 [3,2] 。 异或值之和为 (1 XOR 3) + (2 XOR
     * 2) = 2 + 0 = 2 。 示例 2：
     *
     * <p>输入：nums1 = [1,0,3], nums2 = [5,3,4] 输出：8 解释：将 nums2 重新排列得到 [5,4,3] 。 异或值之和为 (1 XOR 5) + (0
     * XOR 4) + (3 XOR 3) = 4 + 4 + 0 = 8 。
     *
     * <p>提示：
     *
     * <p>n == nums1.length n == nums2.length 1 <= n <= 14 0 <= nums1[i], nums2[i] <= 107
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public int minimumXORSum(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int maxState = 1 << n;
        // dp[i] nums1  状态 i 的 最小值(i的bit位 时 前 bit 给元素)
        int[] dp = new int[maxState];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        for (int state = 1; state < maxState; state++) {
            int bit = Integer.bitCount(state);
            for (int i = 0; i < n; i++) {
                if ((state & (1 << i)) == 0) {
                    continue;
                }
                dp[state] = Math.min(dp[state], dp[state ^ (1 << i)] + (nums1[bit - 1] ^ nums2[i]));
            }
        }
        return dp[maxState - 1];
    }

    /**
     * 1857. 有向图中最大颜色值
     *
     * <p>给你一个 有向图 ，它含有 n 个节点和 m 条边。节点编号从 0 到 n - 1 。
     *
     * <p>给你一个字符串 colors ，其中 colors[i] 是小写英文字母，表示图中第 i 个节点的 颜色 （下标从 0 开始）。同时给你一个二维数组 edges ，其中
     * edges[j] = [aj, bj] 表示从节点 aj 到节点 bj 有一条 有向边 。
     *
     * <p>图中一条有效 路径 是一个点序列 x1 -> x2 -> x3 -> ... -> xk ，对于所有 1 <= i < k ，从 xi 到 xi+1 在图中有一条有向边。路径的
     * 颜色值 是路径中 出现次数最多 颜色的节点数目。
     *
     * <p>请你返回给定图中有效路径里面的 最大颜色值 。如果图中含有环，请返回 -1 。
     *
     * <p>示例 1：
     *
     * <p>输入：colors = "abaca", edges = [[0,1],[0,2],[2,3],[3,4]] 输出：3 解释：路径 0 -> 2 -> 3 -> 4 含有 3
     * 个颜色为 "a" 的节点（上图中的红色节点）。 示例 2：
     *
     * <p>输入：colors = "a", edges = [[0,0]] 输出：-1 解释：从 0 到 0 有一个环。
     *
     * <p>提示：
     *
     * <p>n == colors.length m == edges.length 1 <= n <= 105 0 <= m <= 105 colors 只含有小写英文字母。 0 <=
     * aj, bj < n
     *
     * @param colors
     * @param edges
     * @return
     */
    public int largestPathValue(String colors, int[][] edges) {
        int n = colors.length(), m = edges.length;
        // 拓扑排序流程
        // ①将所有入度为0的点加入队列中
        // ②每次出队一个入度为0的点，然后将该点删除（意思是将所有与该点相连的边都删掉，即将边另一端对应的点的入度减1），若删除该点后与该点相连的点入度变为了0，则将该点加入队列。
        // ③重复②过程直到队列中的元素被删完
        //
        // 排除有环的情况
        // 因为只有入度为 0 的点才能入队，故若存在环，环上的点一定无法入队。
        // 所以只需统计入过队的点数之和是否等于点的总数 nn 即可。
        int[] degree = new int[n];
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int[] edge : edges) {
            degree[edge[1]]++;
            List<Integer> list = graph.computeIfAbsent(edge[0], k -> new ArrayList<>());
            list.add(edge[1]);
        }
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            // 入度为0 作为起点
            if (degree[i] == 0) {
                queue.offer(i);
            }
        }
        int count = 0;
        // dp[i][j] 表示 以 节点 i 结尾 颜色 j 的 出现次数
        int[][] dp = new int[n][26];
        while (!queue.isEmpty()) {
            int node = queue.poll();
            char c = colors.charAt(node);
            dp[node][c - 'a']++;
            count++;
            List<Integer> list = graph.get(node);
            if (Objects.isNull(list) || list.isEmpty()) {
                continue;
            }
            for (int next : list) {
                degree[next]--;
                // 枚举每个颜色
                for (int j = 0; j < 26; j++) {
                    dp[next][j] = Math.max(dp[next][j], dp[node][j]);
                }
                if (degree[next] == 0) {
                    queue.offer(next);
                }
            }
        }
        if (count != n) {
            return -1;
        }
        int result = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 26; j++) {
                result = Math.max(result, dp[i][j]);
            }
        }

        return result;
    }

    /**
     * 1883. 准时抵达会议现场的最小跳过休息次数
     *
     * <p>给你一个整数 hoursBefore ，表示你要前往会议所剩下的可用小时数。要想成功抵达会议现场，你必须途经 n 条道路。道路的长度用一个长度为 n 的整数数组 dist
     * 表示，其中 dist[i] 表示第 i 条道路的长度（单位：千米）。另给你一个整数 speed ，表示你在道路上前进的速度（单位：千米每小时）。
     *
     * <p>当你通过第 i 条路之后，就必须休息并等待，直到 下一个整数小时 才能开始继续通过下一条道路。注意：你不需要在通过最后一条道路后休息，因为那时你已经抵达会议现场。
     *
     * <p>例如，如果你通过一条道路用去 1.4 小时，那你必须停下来等待，到 2 小时才可以继续通过下一条道路。如果通过一条道路恰好用去 2 小时，就无需等待，可以直接继续。
     * 然而，为了能准时到达，你可以选择 跳过 一些路的休息时间，这意味着你不必等待下一个整数小时。注意，这意味着与不跳过任何休息时间相比，你可能在不同时刻到达接下来的道路。
     *
     * <p>例如，假设通过第 1 条道路用去 1.4 小时，且通过第 2 条道路用去 0.6 小时。跳过第 1 条道路的休息时间意味着你将会在恰好 2 小时完成通过第 2
     * 条道路，且你能够立即开始通过第 3 条道路。 返回准时抵达会议现场所需要的 最小跳过次数 ，如果 无法准时参会 ，返回 -1 。
     *
     * <p>示例 1：
     *
     * <p>输入：dist = [1,3,2], speed = 4, hoursBefore = 2 输出：1 解释： 不跳过任何休息时间，你将用 (1/4 + 3/4) + (3/4 +
     * 1/4) + (2/4) = 2.5 小时才能抵达会议现场。 可以跳过第 1 次休息时间，共用 ((1/4 + 0) + (3/4 + 0)) + (2/4) = 1.5
     * 小时抵达会议现场。 注意，第 2 次休息时间缩短为 0 ，由于跳过第 1 次休息时间，你是在整数小时处完成通过第 2 条道路。 示例 2：
     *
     * <p>输入：dist = [7,3,5,5], speed = 2, hoursBefore = 10 输出：2 解释： 不跳过任何休息时间，你将用 (7/2 + 1/2) + (3/2
     * + 1/2) + (5/2 + 1/2) + (5/2) = 11.5 小时才能抵达会议现场。 可以跳过第 1 次和第 3 次休息时间，共用 ((7/2 + 0) + (3/2 +
     * 0)) + ((5/2 + 0) + (5/2)) = 10 小时抵达会议现场。 示例 3：
     *
     * <p>输入：dist = [7,3,5,5], speed = 1, hoursBefore = 10 输出：-1 解释：即使跳过所有的休息时间，也无法准时参加会议。
     *
     * <p>提示：
     *
     * <p>n == dist.length 1 <= n <= 1000 1 <= dist[i] <= 105 1 <= speed <= 106 1 <= hoursBefore <=
     * 107
     *
     * @param dist
     * @param speed
     * @param hoursBefore
     * @return
     */
    public int minSkips(int[] dist, int speed, int hoursBefore) {
        int n = dist.length;
        // dp[i][j] 表示经过了 dist[0] 到 dist[i−1] 的 i段道路，并且跳过了 j 次的最短距离（+ 额外用时）
        long[][] dp = new long[n + 1][n + 1];
        for (int i = 0; i <= n; i++) {
            Arrays.fill(dp[i], Long.MAX_VALUE >> 1);
        }
        dp[0][0] = 0;
        for (int i = 1; i <= n; i++) {
            // j 次
            for (int j = 0; j <= i; j++) {
                if (j != 0) {
                    // 跳了 j - 1 次
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][j - 1] + dist[i - 1]);
                }
                if (j != i) {
                    // 跳了 j 次   dist[i] 不能跳
                    dp[i][j] =
                            Math.min(
                                    dp[i][j],
                                    ((dp[i - 1][j] + dist[i - 1] - 1) / speed + 1) * (long) speed);
                }
            }
        }

        for (int j = 0; j <= n; j++) {
            if (dp[n][j] <= (long) speed * hoursBefore) {
                return j;
            }
        }

        return -1;
    }

    /**
     * LCP 36. 最多牌组数
     *
     * <p>麻将的游戏规则中，共有两种方式凑成「一组牌」：
     *
     * <p>顺子：三张牌面数字连续的麻将，例如 [4,5,6] 刻子：三张牌面数字相同的麻将，例如 [10,10,10] 给定若干数字作为麻将牌的数值（记作一维数组 tiles），请返回所给
     * tiles 最多可组成的牌组数。
     *
     * <p>注意：凑成牌组时，每张牌仅能使用一次。
     *
     * <p>示例 1：
     *
     * <p>输入：tiles = [2,2,2,3,4]
     *
     * <p>输出：1
     *
     * <p>解释：最多可以组合出 [2,2,2] 或者 [2,3,4] 其中一组牌。
     *
     * <p>示例 2：
     *
     * <p>输入：tiles = [2,2,2,3,4,1,3]
     *
     * <p>输出：2
     *
     * <p>解释：最多可以组合出 [1,2,3] 与 [2,3,4] 两组牌。
     *
     * <p>提示：
     *
     * <p>1 <= tiles.length <= 10^5 1 <= tiles[i] <= 10^9
     *
     * @param tiles
     * @return
     */
    public int maxGroupNumber(int[] tiles) {
        TreeMap<Integer, Integer> countMap = new TreeMap<>();
        for (int num : tiles) {
            int count = countMap.getOrDefault(num, 0);
            countMap.put(num, count + 1);
        }
        int[][] dp = new int[5][5];
        for (int i = 0; i < 5; i++) {
            Arrays.fill(dp[i], -1);
        }
        dp[0][0] = 0;
        int last = 0;
        for (int num : countMap.navigableKeySet()) {
            // 如果上一张牌和这张牌没法连起来
            // 意味着无论之前留几张牌，都无法和tile一起组成顺子，因此，只保留dp[0][0]的情形。
            if (last != num - 1) {
                int tmpNum = dp[0][0];
                for (int i = 0; i < 5; i++) {
                    Arrays.fill(dp[i], -1);
                }
                dp[0][0] = tmpNum;
            }

            int[][] tmp = new int[5][5];
            for (int i = 0; i < 5; i++) {
                Arrays.fill(tmp[i], -1);
            }
            int cnt = countMap.getOrDefault(num, 0);
            for (int cnt2 = 0; cnt2 < 5; cnt2++) { // num - 2 的牌数
                for (int cnt1 = 0; cnt1 < 5; cnt1++) { // num - 1 的牌数
                    // 如果之前没有留下这么多张牌
                    if (dp[cnt2][cnt1] < 0) {
                        continue;
                    }
                    // 顺子的数量 不能超过 cnt1, cnt2, cnt
                    for (int shunzi = 0; shunzi <= Math.min(cnt, Math.min(cnt1, cnt2)); shunzi++) {
                        // 对于下一个点数 num + 1  newCnt2(num + 1 - 2) = cnt1 - shunzi
                        int newCnt2 = cnt1 - shunzi;
                        // 对于下一个点数 num + 1  newCnt1(num + 1 - 1) = cnt - shunzi
                        for (int newCnt1 = 0; newCnt1 <= Math.min(4, cnt - shunzi); newCnt1++) {
                            // 新的牌数
                            // 1. dp 数组保留的 dp[cnt2][cnt1]
                            // 2. 顺子数量
                            // 3. num 生成的 刻子 cnt - 顺子数量 - 为 下一个点数 num + 1 保留的 newCnt1
                            int newScore = dp[cnt2][cnt1] + shunzi + (cnt - shunzi - newCnt1) / 3;
                            tmp[newCnt2][newCnt1] = Math.max(tmp[newCnt2][newCnt1], newScore);
                        }
                    }
                }
            }

            dp = tmp;
            last = num;
        }
        logResult(dp);
        int result = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                result = Math.max(result, dp[i][j]);
            }
        }
        return result;
    }

    /**
     * LCP 38. 守卫城堡
     *
     * <p>城堡守卫游戏的胜利条件为使恶魔无法从出生点到达城堡。游戏地图可视作 2*N 的方格图，记作字符串数组 grid，其中：
     *
     * <p>"." 表示恶魔可随意通行的平地； "#" 表示恶魔不可通过的障碍物，玩家可通过在 平地 上设置障碍物，即将 "." 变为 "#" 以阻挡恶魔前进； "S"
     * 表示恶魔出生点，将有大量的恶魔该点生成，恶魔可向上/向下/向左/向右移动，且无法移动至地图外； "P" 表示瞬移点，移动到 "P" 点的恶魔可被传送至任意一个 "P"
     * 点，也可选择不传送； "C" 表示城堡。 然而在游戏中用于建造障碍物的金钱是有限的，请返回玩家最少需要放置几个障碍物才能获得胜利。若无论怎样放置障碍物均无法获胜，请返回 -1。
     *
     * <p>注意：
     *
     * <p>地图上可能有一个或多个出生点 地图上有且只有一个城堡 示例 1
     *
     * <p>输入：grid = ["S.C.P#P.", ".....#.S"]
     *
     * <p>输出：3
     *
     * <p>解释：至少需要放置三个障碍物
     *
     * <p>示例 2：
     *
     * <p>输入：grid = ["SP#P..P#PC#.S", "..#P..P####.#"]
     *
     * <p>输出：-1
     *
     * <p>解释：无论怎样修筑障碍物，均无法阻挡最左侧出生的恶魔到达城堡位置 image.png
     *
     * <p>示例 3：
     *
     * <p>输入：grid = ["SP#.C.#PS", "P.#...#.P"]
     *
     * <p>输出：0
     *
     * <p>解释：无需放置障碍物即可获得胜利
     *
     * <p>示例 4：
     *
     * <p>输入：grid = ["CP.#.P.", "...S..S"]
     *
     * <p>输出：4
     *
     * <p>解释：至少需要放置 4 个障碍物，示意图为放置方法之一
     *
     * <p>提示：
     *
     * <p>grid.length == 2 2 <= grid[0].length == grid[1].length <= 10^4 grid[i][j] 仅包含字符
     * "."、"#"、"C"、"P"、"S"
     *
     * @param grid
     * @return
     */
    public int guardCastle(String[] grid) {
        int n = grid[0].length();
        int result = Integer.MAX_VALUE;
        // 1 所有的传送门 -> 恶魔
        char[][] grids = new char[2][n];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < n; j++) {
                char c = grid[i].charAt(j);
                if (c == 'P') {
                    grids[i][j] = 'S';
                } else {
                    grids[i][j] = c;
                }
            }
        }
        result = Math.min(result, dpGuardCastle(grids));
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < n; j++) {
                char c = grid[i].charAt(j);
                if (c == 'P') {
                    grids[i][j] = 'C';
                }
            }
        }
        result = Math.min(result, dpGuardCastle(grids));
        // 2 所有的传送门 -> 城堡

        return result == Integer.MAX_VALUE ? -1 : result;
    }

    private int dpGuardCastle(char[][] grids) {
        int n = grids[0].length;
        // 判断网格中是否有相邻的 恶魔 S 和城堡 C相邻无法分开
        int result = Integer.MAX_VALUE;
        for (int j = 0; j < n; j++) {
            // 上下
            char up = grids[0][j], down = grids[1][j];
            if ((up == 'S' && down == 'C') || (up == 'C' && down == 'S')) {
                return result;
            }
            if (j == 0) {
                continue;
            }
            // 第1行左右
            char left = grids[0][j - 1], right = grids[0][j];
            if ((left == 'S' && right == 'C') || (left == 'C' && right == 'S')) {
                return result;
            }
            // 第2行左右
            left = grids[1][j - 1];
            right = grids[1][j];
            if ((left == 'S' && right == 'C') || (left == 'C' && right == 'S')) {
                return result;
            }
        }

        // dp[i][s1][s2] 表示 处理到第i列 两个格子状态是s1和 s2 所需的最小操作数
        // s 0表示空地 1表示 城堡或者之前的城堡可以到达该位置
        // 2 表示 恶魔或者之前的恶魔可以到达该位置 3表示 障碍物
        dp3 = new int[n + 1][4][4];
        int MAX_NUM = n << 1;
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j < 4; j++) {
                Arrays.fill(dp3[i][j], 0xfff);
            }
        }
        dp3[0][0][0] = 0;
        Map<Character, Integer> stateMap = new HashMap<>();
        stateMap.put('.', 0);
        stateMap.put('C', 1);
        stateMap.put('S', 2);
        stateMap.put('#', 3);
        for (int i = 1; i <= n; i++) {
            char up = grids[0][i - 1], down = grids[1][i - 1];
            // 当前 的状态
            int t1 = stateMap.get(up), t2 = stateMap.get(down);
            for (int s1 = 0; s1 < 4; s1++) {
                for (int s2 = 0; s2 < 4; s2++) {
                    // 不加障碍物
                    updateDpGuardCastle(i, s1, s2, t1, t2, 0);
                    // 加一个障碍物
                    if (up == '.') {
                        updateDpGuardCastle(i, s1, s2, 3, t2, 1);
                    }

                    if (down == '.') {
                        updateDpGuardCastle(i, s1, s2, t1, 3, 1);
                    }
                    // 加两个障碍物
                    if (up == '.' && down == '.') {
                        updateDpGuardCastle(i, s1, s2, 3, 3, 2);
                    }
                }
            }
        }

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                result = Math.min(result, dp3[n][i][j]);
            }
        }

        return result;
    }

    private int[][][] dp3;

    /**
     * @param i 第 i 列
     * @param s1 i - 1 列的状态1
     * @param s2 i - 1 列的状态2
     * @param t1 第 i 列 本身的状态1
     * @param t2 第 i 列 本身的状态1
     * @param count 需要的障碍物数量
     */
    private void updateDpGuardCastle(int i, int s1, int s2, int t1, int t2, int count) {

        if (s1 == 1 || s1 == 2) {
            // s1 和 t1 是 恶魔和城堡
            if (s1 + t1 == 3) {
                return;
            }
            if (t1 == 0) {
                t1 = s1;
            }
        }

        if (s2 == 1 || s2 == 2) {
            // s2 和 t2 是 恶魔和城堡
            if (s2 + t2 == 3) {
                return;
            }
            if (t2 == 0) {
                t2 = s2;
            }
        }
        // t1 和 t2 分别是 恶魔和城堡
        if ((t1 == 1 && t2 == 2) || (t1 == 2 && t2 == 1)) {
            return;
        }
        // 判断 t1 t2 相互影响
        if (t1 == 0 && (t2 == 1 || t2 == 2)) {
            t1 = t2;
        }
        if (t2 == 0 && (t1 == 1 || t1 == 2)) {
            t2 = t1;
        }
        dp3[i][t1][t2] = Math.min(dp3[i][t1][t2], dp3[i - 1][s1][s2] + count);
    }
}
