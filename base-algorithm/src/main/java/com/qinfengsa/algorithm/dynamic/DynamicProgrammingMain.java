package com.qinfengsa.algorithm.dynamic;

import static com.qinfengsa.algorithm.util.LogUtils.logResult;

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
}
