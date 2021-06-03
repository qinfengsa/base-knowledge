package com.qinfengsa.algorithm.dynamic;

import static com.qinfengsa.algorithm.util.LogUtils.logResult;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
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
}
