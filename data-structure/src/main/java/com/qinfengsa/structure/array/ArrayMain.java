package com.qinfengsa.structure.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import lombok.extern.slf4j.Slf4j;

/**
 * 数组
 *
 * @author qinfengsa
 * @date 2021/5/10 12:54
 */
@Slf4j
public class ArrayMain {

    /**
     * LCP 32. 批量处理任务
     *
     * <p>某实验室计算机待处理任务以 [start,end,period] 格式记于二维数组 tasks，表示完成该任务的时间范围为起始时间 start 至结束时间 end
     * 之间，需要计算机投入 period 的时长，注意：
     *
     * <p>period 可为不连续时间 首尾时间均包含在内 处于开机状态的计算机可同时处理任意多个任务，请返回电脑最少开机多久，可处理完所有任务。
     *
     * <p>示例 1：
     *
     * <p>输入：tasks = [[1,3,2],[2,5,3],[5,6,2]]
     *
     * <p>输出：4
     *
     * <p>解释： tasks[0] 选择时间点 2、3； tasks[1] 选择时间点 2、3、5； tasks[2] 选择时间点 5、6； 因此计算机仅需在时间点 2、3、5、6
     * 四个时刻保持开机即可完成任务。
     *
     * <p>示例 2：
     *
     * <p>输入：tasks = [[2,3,1],[5,5,1],[5,6,2]]
     *
     * <p>输出：3
     *
     * <p>解释： tasks[0] 选择时间点 2 或 3； tasks[1] 选择时间点 5； tasks[2] 选择时间点 5、6； 因此计算机仅需在时间点 2、5、6 或 3、5、6
     * 三个时刻保持开机即可完成任务。
     *
     * <p>提示：
     *
     * <p>2 <= tasks.length <= 10^5 tasks[i].length == 3 0 <= tasks[i][0] <= tasks[i][1] <= 10^9 1
     * <= tasks[i][2] <= tasks[i][1]-tasks[i][0] + 1
     *
     * @param tasks
     * @return
     */
    public int processTasks(int[][] tasks) {

        Arrays.sort(tasks, (a, b) -> a[1] == b[1] ? a[0] - b[0] : a[1] - b[1]);
        // 线段树
        List<Segment> segmentList = new ArrayList<>();
        int total = 0;

        for (int[] task : tasks) {
            int start = task[0], end = task[1] + 1, period = task[2];
            // 二分查找
            if (segmentList.isEmpty()) {
                segmentList.add(new Segment(end - period, end, total));
                total += period;
                continue;
            }
            int idx = findIndex(segmentList, start);
            if (idx == segmentList.size()) {
                // 贪心, 从后往前 排列所有的任务 开始时间 end - period 把任务排满
                segmentList.add(new Segment(end - period, end, total));
                total += period;
            } else {
                // 合并 线段
                Segment segment = segmentList.get(idx);
                log.debug("idx:{}  segment:{}", idx, segment);

                // 已经执行的 time
                int alreadyProcessTime =
                        total - segment.prevTime - Math.max(0, start - segment.start);
                if (alreadyProcessTime < period) {
                    period -= alreadyProcessTime;
                    total += period;
                    // 合并线段
                    mergeSegment(segmentList, period, end, total);
                }
            }
        }

        return total;
    }

    private void mergeSegment(List<Segment> segmentList, int period, int end, int totalTime) {
        int start = end;
        int i = segmentList.size() - 1;
        for (; i >= 0; segmentList.remove(i--)) {
            Segment segment = segmentList.get(i);
            if (start - segment.end > period) {
                break;
            }
            period -= start - segment.end;
            start = segment.start;
        }
        start -= period;
        int prevTime = totalTime - (end - start);
        segmentList.add(new Segment(start, end, prevTime));
    }

    private int findIndex(List<Segment> segmentList, int target) {
        // 二分查找
        int left = 0, right = segmentList.size() - 1;
        if (target > segmentList.get(right).end) {
            return right + 1;
        } else if (target == segmentList.get(right).end) {
            return right;
        }
        while (left < right) {
            int mid = (left + right) >> 1;
            if (target >= segmentList.get(mid).end) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return right;
    }

    static class Segment {
        int start, end, prevTime;

        Segment(int start, int end, int prevTime) {
            this.start = start;
            this.end = end;
            this.prevTime = prevTime;
        }

        @Override
        public String toString() {
            return "Segment{" + "start=" + start + ", end=" + end + ", prevTime=" + prevTime + '}';
        }
    }

    int n, m;

    static int[] DIR_ROW = {1, 0, -1, 0};
    static int[] DIR_COL = {0, 1, 0, -1};

    private boolean inArea(int row, int col, int rows, int cols) {
        return row >= 0 && row < rows && col >= 0 && col < cols;
    }

    /**
     * 1293. 网格中的最短路径
     *
     * <p>给你一个 m * n 的网格，其中每个单元格不是 0（空）就是 1（障碍物）。每一步，您都可以在空白单元格中上、下、左、右移动。
     *
     * <p>如果您 最多 可以消除 k 个障碍物，请找出从左上角 (0, 0) 到右下角 (m-1, n-1) 的最短路径，并返回通过该路径所需的步数。如果找不到这样的路径，则返回 -1。
     *
     * <p>示例 1：
     *
     * <p>输入： grid = [[0,0,0], [1,1,0], [0,0,0], [0,1,1], [0,0,0]], k = 1 输出：6 解释： 不消除任何障碍的最短路径是 10。
     * 消除位置 (3,2) 处的障碍后，最短路径是 6 。该路径是 (0,0) -> (0,1) -> (0,2) -> (1,2) -> (2,2) -> (3,2) -> (4,2).
     *
     * <p>示例 2：
     *
     * <p>输入： grid = [[0,1,1], [1,1,1], [1,0,0]], k = 1 输出：-1 解释： 我们至少需要消除两个障碍才能找到这样的路径。
     *
     * <p>提示：
     *
     * <p>grid.length == m grid[0].length == n 1 <= m, n <= 40 1 <= k <= m*n grid[i][j] == 0 or 1
     * grid[0][0] == grid[m-1][n-1] == 0
     *
     * @param grid
     * @param k
     * @return
     */
    public int shortestPath(int[][] grid, int k) {
        int m = grid.length, n = grid[0].length;
        Queue<ShortestPathNode> queue = new LinkedList<>();
        int step = 0;

        queue.offer(new ShortestPathNode(0, 0, 0));

        boolean[][][] visited = new boolean[m][n][k + 1];
        visited[0][0][0] = true;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                ShortestPathNode node = queue.poll();
                int row = node.row, col = node.col, removeNum = node.removeNum;
                if (row == m - 1 && col == n - 1) {
                    return step;
                }

                for (int j = 0; j < 4; j++) {
                    int nextRow = row + DIR_ROW[j], nextCol = col + DIR_COL[j];
                    if (!inArea(nextRow, nextCol, m, n)) {
                        continue;
                    }

                    if (grid[nextRow][nextCol] == 0 && !visited[nextRow][nextCol][removeNum]) {
                        queue.offer(new ShortestPathNode(nextRow, nextCol, removeNum));
                        visited[nextRow][nextCol][removeNum] = true;
                    } else if (grid[nextRow][nextCol] == 1
                            && removeNum < k
                            && !visited[nextRow][nextCol][removeNum + 1]) {
                        queue.offer(new ShortestPathNode(nextRow, nextCol, removeNum + 1));
                        visited[nextRow][nextCol][removeNum + 1] = true;
                    }
                }
            }

            step++;
        }

        return -1;
    }

    static class ShortestPathNode {
        int row, col, removeNum;

        public ShortestPathNode(int row, int col, int removeNum) {
            this.row = row;
            this.col = col;
            this.removeNum = removeNum;
        }
    }

    /**
     * 5744. 旋转盒子
     *
     * <p>给你一个 m x n 的字符矩阵 box ，它表示一个箱子的侧视图。箱子的每一个格子可能为：
     *
     * <p>'#' 表示石头 '*' 表示固定的障碍物 '.' 表示空位置 这个箱子被 顺时针旋转 90 度
     * ，由于重力原因，部分石头的位置会发生改变。每个石头会垂直掉落，直到它遇到障碍物，另一个石头或者箱子的底部。重力 不会 影响障碍物的位置，同时箱子旋转不会产生惯性
     * ，也就是说石头的水平位置不会发生改变。
     *
     * <p>题目保证初始时 box 中的石头要么在一个障碍物上，要么在另一个石头上，要么在箱子的底部。
     *
     * <p>请你返回一个 n x m的矩阵，表示按照上述旋转后，箱子内的结果。
     *
     * <p>示例 1：
     *
     * <p>输入：box = [["#",".","#"]] 输出：[["."], ["#"], ["#"]] 示例 2：
     *
     * <p>输入：box = [["#",".","*","."], ["#","#","*","."]] 输出：[["#","."], ["#","#"], ["*","*"],
     * [".","."]] 示例 3：
     *
     * <p>输入：box = [["#","#","*",".","*","."], ["#","#","#","*",".","."], ["#","#","#",".","#","."]]
     * 输出：[[".","#","#"], [".","#","#"], ["#","#","*"], ["#","*","."], ["#",".","*"], ["#",".","."]]
     *
     * <p>提示：
     *
     * <p>m == box.length n == box[i].length 1 <= m, n <= 500 box[i][j] 只可能是 '#' ，'*' 或者 '.' 。
     *
     * @param box
     * @return
     */
    public char[][] rotateTheBox(char[][] box) {
        int m = box.length, n = box[0].length;
        char[][] result = new char[n][m];

        for (int i = 0; i < n; i++) {
            Arrays.fill(result[i], '.');
        }

        for (int i = 0; i < m; i++) {
            int idx = n - 1;

            for (int j = n - 1; j >= 0; j--) {
                if (box[i][j] == '*') {
                    result[j][m - i - 1] = '*';
                    idx = j - 1;
                } else if (box[i][j] == '#') {
                    result[idx--][m - i - 1] = '#';
                }
            }
        }

        return result;
    }

    /**
     * 5759. 找出所有子集的异或总和再求和
     *
     * <p>一个数组的 异或总和 定义为数组中所有元素按位 XOR 的结果；如果数组为 空 ，则异或总和为 0 。
     *
     * <p>例如，数组 [2,5,6] 的 异或总和 为 2 XOR 5 XOR 6 = 1 。 给你一个数组 nums ，请你求出 nums 中每个 子集 的 异或总和
     * ，计算并返回这些值相加之 和 。
     *
     * <p>注意：在本题中，元素 相同 的不同子集应 多次 计数。
     *
     * <p>数组 a 是数组 b 的一个 子集 的前提条件是：从 b 删除几个（也可能不删除）元素能够得到 a 。
     *
     * <p>示例 1：
     *
     * <p>输入：nums = [1,3] 输出：6 解释：[1,3] 共有 4 个子集： - 空子集的异或总和是 0 。 - [1] 的异或总和为 1 。 - [3] 的异或总和为 3 。
     * - [1,3] 的异或总和为 1 XOR 3 = 2 。 0 + 1 + 3 + 2 = 6 示例 2：
     *
     * <p>输入：nums = [5,1,6] 输出：28 解释：[5,1,6] 共有 8 个子集： - 空子集的异或总和是 0 。 - [5] 的异或总和为 5 。 - [1] 的异或总和为
     * 1 。 - [6] 的异或总和为 6 。 - [5,1] 的异或总和为 5 XOR 1 = 4 。 - [5,6] 的异或总和为 5 XOR 6 = 3 。 - [1,6] 的异或总和为
     * 1 XOR 6 = 7 。 - [5,1,6] 的异或总和为 5 XOR 1 XOR 6 = 2 。 0 + 5 + 1 + 6 + 4 + 3 + 7 + 2 = 28 示例 3：
     *
     * <p>输入：nums = [3,4,5,6,7,8] 输出：480 解释：每个子集的全部异或总和值之和为 480 。
     *
     * <p>提示：
     *
     * <p>1 <= nums.length <= 12 1 <= nums[i] <= 20
     *
     * @param nums
     * @return
     */
    public int subsetXORSum(int[] nums) {
        int len = nums.length;
        int total = 0, sum = 0;
        for (int num : nums) {
            total ^= num;
        }
        int max = (1 << len) - 1;
        int[] result = new int[1 << len];
        Arrays.fill(result, -1);
        for (int i = 0; i < len; i++) {
            int idx = 1 << i;
            result[idx] = nums[i];
            int num = total ^ nums[i];
            result[max ^ idx] = num;
        }

        for (int i = 1; i < 1 << len; i++) {
            if (result[i] != -1) {
                sum += result[i];

                continue;
            }

            int num = result[i - 1] ^ result[i ^ (i - 1)];
            result[i] = num;
            int num2 = total ^ num;
            result[max ^ i] = num2;
            sum += num;
        }
        log.debug("result:{}", result);
        return sum;
    }
}
