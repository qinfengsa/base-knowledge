package com.qinfengsa.algorithm.dynamic;

import static com.qinfengsa.algorithm.util.LogUtils.logResult;

import java.util.Arrays;
import java.util.List;

/**
 * 动态规划
 *
 * @author qinfengsa
 * @date 2021/5/7 13:24
 */
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
}
