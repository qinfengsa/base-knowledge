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
}
