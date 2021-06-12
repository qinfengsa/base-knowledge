package com.qinfengsa.structure.array;

import static com.qinfengsa.structure.util.LogUtils.logResult;

import com.qinfengsa.structure.bit.BIT;
import com.qinfengsa.structure.dsu.Dsu;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.TreeSet;
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

    // 1 右 2 左 3 下 4 上
    static int[] DIR_ROW = {0, 0, 1, -1};
    static int[] DIR_COL = {1, -1, 0, 0};

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

    /**
     * 1298. 你能从盒子里获得的最大糖果数
     *
     * <p>给你 n 个盒子，每个盒子的格式为 [status, candies, keys, containedBoxes] ，其中：
     *
     * <p>状态字 status[i]：整数，如果 box[i] 是开的，那么是 1 ，否则是 0 。 糖果数 candies[i]: 整数，表示 box[i] 中糖果的数目。 钥匙
     * keys[i]：数组，表示你打开 box[i] 后，可以得到一些盒子的钥匙，每个元素分别为该钥匙对应盒子的下标。 内含的盒子 containedBoxes[i]：整数，表示放在
     * box[i] 里的盒子所对应的下标。 给你一个 initialBoxes
     * 数组，表示你现在得到的盒子，你可以获得里面的糖果，也可以用盒子里的钥匙打开新的盒子，还可以继续探索从这个盒子里找到的其他盒子。
     *
     * <p>请你按照上述规则，返回可以获得糖果的 最大数目 。
     *
     * <p>示例 1：
     *
     * <p>输入：status = [1,0,1,0], candies = [7,5,4,100], keys = [[],[],[1],[]], containedBoxes =
     * [[1,2],[3],[],[]], initialBoxes = [0] 输出：16 解释： 一开始你有盒子 0 。你将获得它里面的 7 个糖果和盒子 1 和 2。 盒子 1
     * 目前状态是关闭的，而且你还没有对应它的钥匙。所以你将会打开盒子 2 ，并得到里面的 4 个糖果和盒子 1 的钥匙。 在盒子 1 中，你会获得 5 个糖果和盒子 3 ，但是你没法获得盒子
     * 3 的钥匙所以盒子 3 会保持关闭状态。 你总共可以获得的糖果数目 = 7 + 4 + 5 = 16 个。 示例 2：
     *
     * <p>输入：status = [1,0,0,0,0,0], candies = [1,1,1,1,1,1], keys = [[1,2,3,4,5],[],[],[],[],[]],
     * containedBoxes = [[1,2,3,4,5],[],[],[],[],[]], initialBoxes = [0] 输出：6 解释： 你一开始拥有盒子 0
     * 。打开它你可以找到盒子 1,2,3,4,5 和它们对应的钥匙。 打开这些盒子，你将获得所有盒子的糖果，所以总糖果数为 6 个。 示例 3：
     *
     * <p>输入：status = [1,1,1], candies = [100,1,100], keys = [[],[0,2],[]], containedBoxes =
     * [[],[],[]], initialBoxes = [1] 输出：1 示例 4：
     *
     * <p>输入：status = [1], candies = [100], keys = [[]], containedBoxes = [[]], initialBoxes = []
     * 输出：0 示例 5：
     *
     * <p>输入：status = [1,1,1], candies = [2,3,2], keys = [[],[],[]], containedBoxes = [[],[],[]],
     * initialBoxes = [2,1,0] 输出：7
     *
     * <p>提示：
     *
     * <p>1 <= status.length <= 1000 status.length == candies.length == keys.length ==
     * containedBoxes.length == n status[i] 要么是 0 要么是 1 。 1 <= candies[i] <= 1000 0 <=
     * keys[i].length <= status.length 0 <= keys[i][j] < status.length keys[i] 中的值都是互不相同的。 0 <=
     * containedBoxes[i].length <= status.length 0 <= containedBoxes[i][j] < status.length
     * containedBoxes[i] 中的值都是互不相同的。 每个盒子最多被一个盒子包含。 0 <= initialBoxes.length <= status.length 0 <=
     * initialBoxes[i] < status.length
     *
     * @param status 状态
     * @param candies 糖果数
     * @param keys 钥匙
     * @param containedBoxes 内含的盒子
     * @param initialBoxes 现在得到的盒子
     * @return
     */
    public int maxCandies(
            int[] status, int[] candies, int[][] keys, int[][] containedBoxes, int[] initialBoxes) {
        int result = 0, n = status.length;

        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[n], boxSet = new boolean[n];

        for (int box : initialBoxes) {
            boxSet[box] = true;
            if (status[box] == 1) {
                queue.offer(box);
                result += candies[box];
                visited[box] = true;
            }
        }

        while (!queue.isEmpty()) {
            int box = queue.poll();
            for (int key : keys[box]) {
                status[key] = 1;
                if (!visited[key] && boxSet[key]) {
                    queue.offer(key);
                    result += candies[key];
                    visited[key] = true;
                }
            }
            for (int nextBox : containedBoxes[box]) {
                boxSet[nextBox] = true;
                if (!visited[nextBox] && status[nextBox] == 1) {
                    queue.offer(nextBox);
                    result += candies[nextBox];
                    visited[nextBox] = true;
                }
            }
        }

        return result;
    }

    /**
     * 1284. 转化为全零矩阵的最少反转次数
     *
     * <p>给你一个 m x n 的二进制矩阵 mat。
     *
     * <p>每一步，你可以选择一个单元格并将它反转（反转表示 0 变 1 ，1 变 0 ）。如果存在和它相邻的单元格，那么这些相邻的单元格也会被反转。（注：相邻的两个单元格共享同一条边。）
     *
     * <p>请你返回将矩阵 mat 转化为全零矩阵的最少反转次数，如果无法转化为全零矩阵，请返回 -1 。
     *
     * <p>二进制矩阵的每一个格子要么是 0 要么是 1 。
     *
     * <p>全零矩阵是所有格子都为 0 的矩阵。
     *
     * <p>示例 1：
     *
     * <p>输入：mat = [[0,0],[0,1]] 输出：3 解释：一个可能的解是反转 (1, 0)，然后 (0, 1) ，最后是 (1, 1) 。 示例 2：
     *
     * <p>输入：mat = [[0]] 输出：0 解释：给出的矩阵是全零矩阵，所以你不需要改变它。 示例 3：
     *
     * <p>输入：mat = [[1,1,1],[1,0,1],[0,0,0]] 输出：6 示例 4：
     *
     * <p>输入：mat = [[1,0,0],[1,0,0]] 输出：-1 解释：该矩阵无法转变成全零矩阵
     *
     * <p>提示：
     *
     * <p>m == mat.length n == mat[0].length 1 <= m <= 3 1 <= n <= 3 mat[i][j] 是 0 或 1 。
     *
     * @param mat
     * @return
     */
    public int minFlips(int[][] mat) {
        // 对于每个单元格来说，它只有两种状态：被反转 or 不被反转。 遍历 N 个单元格
        m = mat.length;
        n = mat[0].length;
        // 深度优先遍历
        intResult = Integer.MAX_VALUE;
        int num = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[i][j] == 0) {
                    continue;
                }
                num |= 1 << (i * n + j);
            }
        }
        log.debug("num:{}", num);
        dfsMinFlips(num, 0, 0);
        return intResult == Integer.MAX_VALUE ? -1 : intResult;
    }

    private int intResult;

    private void dfsMinFlips(int num, int pos, int result) {
        if (pos == m * n) {
            if (num == 0) {
                intResult = Math.min(intResult, result);
            }
            return;
        }
        // 不翻转
        dfsMinFlips(num, pos + 1, result);
        // 翻转
        int row = pos / n, col = pos % n;
        dfsMinFlips(getNextNum(num, row, col), pos + 1, result + 1);
    }

    private int getNextNum(int num, int row, int col) {
        int[] dirRow = {1, 0, 0, -1, 0};
        int[] dirCol = {0, 1, 0, 0, -1};
        for (int i = 0; i < 5; i++) {
            int nextRow = row + dirRow[i], nextCol = col + dirCol[i];
            if (!inArea(nextRow, nextCol, m, n)) {
                continue;
            }
            num ^= 1 << (nextRow * n + nextCol);
        }
        return num;
    }

    /**
     * 5764. 准时到达的列车最小时速
     *
     * <p>给你一个浮点数 hour ，表示你到达办公室可用的总通勤时间。要到达办公室，你必须按给定次序乘坐 n 趟列车。另给你一个长度为 n 的整数数组 dist ，其中 dist[i]
     * 表示第 i 趟列车的行驶距离（单位是千米）。
     *
     * <p>每趟列车均只能在整点发车，所以你可能需要在两趟列车之间等待一段时间。
     *
     * <p>例如，第 1 趟列车需要 1.5 小时，那你必须再等待 0.5 小时，搭乘在第 2 小时发车的第 2 趟列车。 返回能满足你准时到达办公室所要求全部列车的 最小正整数
     * 时速（单位：千米每小时），如果无法准时到达，则返回 -1 。
     *
     * <p>生成的测试用例保证答案不超过 107 ，且 hour 的 小数点后最多存在两位数字 。
     *
     * <p>示例 1：
     *
     * <p>输入：dist = [1,3,2], hour = 6 输出：1 解释：速度为 1 时： - 第 1 趟列车运行需要 1/1 = 1 小时。 -
     * 由于是在整数时间到达，可以立即换乘在第 1 小时发车的列车。第 2 趟列车运行需要 3/1 = 3 小时。 - 由于是在整数时间到达，可以立即换乘在第 4 小时发车的列车。第 3
     * 趟列车运行需要 2/1 = 2 小时。 - 你将会恰好在第 6 小时到达。 示例 2：
     *
     * <p>输入：dist = [1,3,2], hour = 2.7 输出：3 解释：速度为 3 时： - 第 1 趟列车运行需要 1/3 = 0.33333 小时。 -
     * 由于不是在整数时间到达，故需要等待至第 1 小时才能搭乘列车。第 2 趟列车运行需要 3/3 = 1 小时。 - 由于是在整数时间到达，可以立即换乘在第 2 小时发车的列车。第 3
     * 趟列车运行需要 2/3 = 0.66667 小时。 - 你将会在第 2.66667 小时到达。 示例 3：
     *
     * <p>输入：dist = [1,3,2], hour = 1.9 输出：-1 解释：不可能准时到达，因为第 3 趟列车最早是在第 2 小时发车。
     *
     * <p>提示：
     *
     * <p>n == dist.length 1 <= n <= 105 1 <= dist[i] <= 105 1 <= hour <= 109 hours 中，小数点后最多存在两位数字
     *
     * @param dist
     * @param hour
     * @return
     */
    public int minSpeedOnTime(int[] dist, double hour) {
        this.dist = dist;
        this.hour = hour;

        // 二分
        int low = 1, high = Integer.MAX_VALUE >> 1;
        int result = -1;
        while (low < high) {
            int mid = (low + high) >> 1;

            if (canArriveOnTime(mid)) {
                result = mid;
                high = mid;
            } else {
                low = mid + 1;
            }
        }

        return result;
    }

    private int[] dist;

    private double hour;

    private boolean canArriveOnTime(int speed) {
        double total = 0.0;

        for (int i = 0; i < dist.length - 1; i++) {

            int num;
            if (dist[i] % speed == 0) {
                num = dist[i] / speed;
            } else {
                num = dist[i] / speed + 1;
            }
            total += num;
        }

        total += (double) dist[dist.length - 1] / (double) speed;
        return total <= hour;
    }

    /**
     * 1368. 使网格图至少有一条有效路径的最小代价
     *
     * <p>给你一个 m x n 的网格图 grid 。 grid 中每个格子都有一个数字，对应着从该格子出发下一步走的方向。 grid[i][j] 中的数字可能为以下几种情况：
     *
     * <p>1 ，下一步往右走，也就是你会从 grid[i][j] 走到 grid[i][j + 1] 2 ，下一步往左走，也就是你会从 grid[i][j] 走到 grid[i][j -
     * 1] 3 ，下一步往下走，也就是你会从 grid[i][j] 走到 grid[i + 1][j] 4 ，下一步往上走，也就是你会从 grid[i][j] 走到 grid[i -
     * 1][j] 注意网格图中可能会有 无效数字 ，因为它们可能指向 grid 以外的区域。
     *
     * <p>一开始，你会从最左上角的格子 (0,0) 出发。我们定义一条 有效路径 为从格子 (0,0) 出发，每一步都顺着数字对应方向走，最终在最右下角的格子 (m - 1, n - 1)
     * 结束的路径。有效路径 不需要是最短路径 。
     *
     * <p>你可以花费 cost = 1 的代价修改一个格子中的数字，但每个格子中的数字 只能修改一次 。
     *
     * <p>请你返回让网格图至少有一条有效路径的最小代价。
     *
     * <p>示例 1：
     *
     * <p>输入：grid = [[1,1,1,1],[2,2,2,2],[1,1,1,1],[2,2,2,2]] 输出：3 解释：你将从点 (0, 0) 出发。 到达 (3, 3)
     * 的路径为： (0, 0) --> (0, 1) --> (0, 2) --> (0, 3) 花费代价 cost = 1 使方向向下 --> (1, 3) --> (1, 2) -->
     * (1, 1) --> (1, 0) 花费代价 cost = 1 使方向向下 --> (2, 0) --> (2, 1) --> (2, 2) --> (2, 3) 花费代价 cost =
     * 1 使方向向下 --> (3, 3) 总花费为 cost = 3. 示例 2：
     *
     * <p>输入：grid = [[1,1,3],[3,2,2],[1,1,4]] 输出：0 解释：不修改任何数字你就可以从 (0, 0) 到达 (2, 2) 。 示例 3：
     *
     * <p>输入：grid = [[1,2],[4,3]] 输出：1 示例 4：
     *
     * <p>输入：grid = [[2,2,2],[2,2,2]] 输出：3 示例 5：
     *
     * <p>输入：grid = [[4]] 输出：0
     *
     * <p>提示：
     *
     * <p>m == grid.length n == grid[i].length 1 <= m, n <= 100
     *
     * @param grid
     * @return
     */
    public int minCost(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][] dist = new int[m][n];
        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE >> 1);
        }
        dist[0][0] = 0;
        Deque<int[]> queue = new LinkedList<>();
        queue.offerLast(new int[] {0, 0});

        while (!queue.isEmpty()) {
            int[] nums = queue.pollFirst();
            int row = nums[0], col = nums[1];
            if (visited[row][col]) {
                continue;
            }
            visited[row][col] = true;
            for (int i = 0; i < 4; i++) {
                int nextRow = row + DIR_ROW[i], nextCol = col + DIR_COL[i];
                if (!inArea(nextRow, nextCol, m, n)) {
                    continue;
                }
                // grid[row][col] 上一节点的方向
                boolean flag = grid[row][col] == i + 1;
                int newDist = dist[row][col] + (flag ? 0 : 1);
                if (newDist < dist[nextRow][nextCol]) {
                    dist[nextRow][nextCol] = newDist;
                    // 没有改变方向
                    if (flag) {
                        queue.offerFirst(new int[] {nextRow, nextCol});
                    } else {
                        queue.offerLast(new int[] {nextRow, nextCol});
                    }
                }
            }
        }
        logResult(dist);

        return dist[m - 1][n - 1];
    }

    /**
     * 1402. 做菜顺序
     *
     * <p>一个厨师收集了他 n 道菜的满意程度 satisfaction ，这个厨师做出每道菜的时间都是 1 单位时间。
     *
     * <p>一道菜的 「喜爱时间」系数定义为烹饪这道菜以及之前每道菜所花费的时间乘以这道菜的满意程度，也就是 time[i]*satisfaction[i] 。
     *
     * <p>请你返回做完所有菜 「喜爱时间」总和的最大值为多少。
     *
     * <p>你可以按 任意 顺序安排做菜的顺序，你也可以选择放弃做某些菜来获得更大的总和。
     *
     * <p>示例 1：
     *
     * <p>输入：satisfaction = [-1,-8,0,5,-9] 输出：14 解释：去掉第二道和最后一道菜，最大的喜爱时间系数和为 (-1*1 + 0*2 + 5*3 = 14)
     * 。每道菜都需要花费 1 单位时间完成。 示例 2：
     *
     * <p>输入：satisfaction = [4,3,2] 输出：20 解释：按照原来顺序相反的时间做菜 (2*1 + 3*2 + 4*3 = 20) 示例 3：
     *
     * <p>输入：satisfaction = [-1,-4,-5] 输出：0 解释：大家都不喜欢这些菜，所以不做任何菜可以获得最大的喜爱时间系数。 示例 4：
     *
     * <p>输入：satisfaction = [-2,5,-1,0,3,-3] 输出：35
     *
     * <p>提示：
     *
     * <p>n == satisfaction.length 1 <= n <= 500 -10^3 <= satisfaction[i] <= 10^3
     *
     * @param satisfaction
     * @return
     */
    public int maxSatisfaction(int[] satisfaction) {
        int result = 0;
        Arrays.sort(satisfaction);
        int len = satisfaction.length;
        // 前缀和
        int[] sums = new int[len + 1];

        for (int i = 1; i <= len; i++) {
            sums[i] = sums[i - 1] + satisfaction[len - i];
        }
        int sum = 0;
        for (int i = 1; i <= len; i++) {
            sum += sums[i];
            result = Math.max(sum, result);
        }

        return result;
    }

    /**
     * 5755. 数组中最大数对和的最小值
     *
     * <p>题目难度Medium 一个数对 (a,b) 的 数对和 等于 a + b 。最大数对和 是一个数对数组中最大的 数对和 。
     *
     * <p>比方说，如果我们有数对 (1,5) ，(2,3) 和 (4,4)，最大数对和 为 max(1+5, 2+3, 4+4) = max(6, 5, 8) = 8 。 给你一个长度为
     * 偶数 n 的数组 nums ，请你将 nums 中的元素分成 n / 2 个数对，使得：
     *
     * <p>nums 中每个元素 恰好 在 一个 数对中，且 最大数对和 的值 最小 。 请你在最优数对划分的方案下，返回最小的 最大数对和 。
     *
     * <p>示例 1：
     *
     * <p>输入：nums = [3,5,2,3] 输出：7 解释：数组中的元素可以分为数对 (3,3) 和 (5,2) 。 最大数对和为 max(3+3, 5+2) = max(6, 7)
     * = 7 。 示例 2：
     *
     * <p>输入：nums = [3,5,4,2,4,6] 输出：8 解释：数组中的元素可以分为数对 (3,5)，(4,4) 和 (6,2) 。 最大数对和为 max(3+5, 4+4,
     * 6+2) = max(8, 8, 8) = 8 。
     *
     * <p>提示：
     *
     * <p>n == nums.length 2 <= n <= 105 n 是 偶数 。 1 <= nums[i] <= 105
     *
     * @param nums
     * @return
     */
    public int minPairSum(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        int max = 0;
        for (int i = 0; i < n >> 1; i++) {
            int num = nums[i] + nums[n - i - 1];
            max = Math.max(max, num);
        }

        return max;
    }

    /**
     * 5757. 矩阵中最大的三个菱形和
     *
     * <p>给你一个 m x n 的整数矩阵 grid 。
     *
     * <p>菱形和 指的是 grid 中一个正菱形 边界
     * 上的元素之和。本题中的菱形必须为正方形旋转45度，且四个角都在一个格子当中。下图是四个可行的菱形，每个菱形和应该包含的格子都用了相应颜色标注在图中。
     *
     * <p>注意，菱形可以是一个面积为 0 的区域，如上图中右下角的紫色菱形所示。
     *
     * <p>请你按照 降序 返回 grid 中三个最大的 互不相同的菱形和 。如果不同的和少于三个，则将它们全部返回。
     *
     * <p>示例 1：
     *
     * <p>输入：grid = [[3,4,5,1,3],[3,3,4,2,3],[20,30,200,40,10],[1,5,5,4,1],[4,3,2,2,5]]
     * 输出：[228,216,211] 解释：最大的三个菱形和如上图所示。 - 蓝色：20 + 3 + 200 + 5 = 228 - 红色：200 + 2 + 10 + 4 = 216 -
     * 绿色：5 + 200 + 4 + 2 = 211 示例 2：
     *
     * <p>输入：grid = [[1,2,3],[4,5,6],[7,8,9]] 输出：[20,9,8] 解释：最大的三个菱形和如上图所示。 - 蓝色：4 + 2 + 6 + 8 = 20
     * - 红色：9 （右下角红色的面积为 0 的菱形） - 绿色：8 （下方中央面积为 0 的菱形） 示例 3：
     *
     * <p>输入：grid = [[7,7,7]] 输出：[7] 解释：所有三个可能的菱形和都相同，所以返回 [7] 。
     *
     * <p>提示：
     *
     * <p>m == grid.length n == grid[i].length 1 <= m, n <= 100 1 <= grid[i][j] <= 105
     *
     * @param grid
     * @return
     */
    public int[] getBiggestThree(int[][] grid) {
        int m = grid.length, n = grid[0].length;

        int[][] leftSum = new int[m][n], rightSum = new int[m][n];
        int max0 = 0, max1 = 0, max2 = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] >= max0) {
                    if (grid[i][j] > max0) {
                        max2 = max1;
                        max1 = max0;
                        max0 = grid[i][j];
                    }

                } else if (grid[i][j] >= max1) {
                    if (grid[i][j] > max1) {
                        max2 = max1;
                        max1 = grid[i][j];
                    }

                } else if (grid[i][j] > max2) {
                    max2 = grid[i][j];
                }

                if (i == 0 || j == 0) {
                    leftSum[i][j] = grid[i][j];
                } else {
                    leftSum[i][j] = leftSum[i - 1][j - 1] + grid[i][j];
                }
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = n - 1; j >= 0; j--) {
                if (i == 0 || j == n - 1) {
                    rightSum[i][j] = grid[i][j];
                } else {
                    rightSum[i][j] = rightSum[i - 1][j + 1] + grid[i][j];
                }
            }
        }
        logResult(leftSum);

        logResult(rightSum);

        for (int l = 3; l <= Math.min(m, n); l += 2) {
            // 最上方 坐标
            for (int i = 0; i <= m - l; i++) {
                int half = l >> 1;
                for (int j = half; j < n - half; j++) {
                    int leftR = i + half, leftC = j - half;
                    int rightR = i + half, rightC = j + half;
                    int downR = i + l - 1, downC = j;
                    // 计算 面积
                    int sum =
                            leftSum[rightR][rightC]
                                    - leftSum[i][j]
                                    + rightSum[leftR][leftC]
                                    - rightSum[i][j]
                                    + leftSum[downR][downC]
                                    - leftSum[leftR][leftC]
                                    + rightSum[downR][downC]
                                    - rightSum[rightR][rightC]
                                    + grid[i][j]
                                    - grid[downR][downC];
                    if (sum >= max0) {
                        if (sum > max0) {
                            max2 = max1;
                            max1 = max0;
                            max0 = sum;
                        }
                    } else if (sum >= max1) {
                        if (sum > max1) {
                            max2 = max1;
                            max1 = sum;
                        }
                    } else if (sum > max2) {
                        max2 = sum;
                    }
                }
            }
        }
        log.debug("max0,max1,max2 {} {} {}", max0, max1, max2);
        if (max1 == 0) {
            return new int[] {max0};
        } else if (max2 == 0) {
            return new int[] {max0, max1};
        }

        return new int[] {max0, max1, max2};
    }

    /**
     * 5774. 使用服务器处理任务
     *
     * <p>给你两个 下标从 0 开始 的整数数组 servers 和 tasks ，长度分别为 n 和 m 。servers[i] 是第 i 台服务器的 权重 ，而 tasks[j]
     * 是处理第 j 项任务 所需要的时间（单位：秒）。
     *
     * <p>你正在运行一个仿真系统，在处理完所有任务后，该系统将会关闭。每台服务器只能同时处理一项任务。第 0 项任务在第 0 秒可以开始处理，相应地，第 j 项任务在第 j
     * 秒可以开始处理。处理第 j 项任务时，你需要为它分配一台 权重最小 的空闲服务器。如果存在多台相同权重的空闲服务器，请选择 下标最小 的服务器。如果一台空闲服务器在第 t 秒分配到第 j
     * 项任务，那么在 t + tasks[j] 时它将恢复空闲状态。
     *
     * <p>如果没有空闲服务器，则必须等待，直到出现一台空闲服务器，并 尽可能早 地处理剩余任务。 如果有多项任务等待分配，则按照 下标递增 的顺序完成分配。
     *
     * <p>如果同一时刻存在多台空闲服务器，可以同时将多项任务分别分配给它们。
     *
     * <p>构建长度为 m 的答案数组 ans ，其中 ans[j] 是第 j 项任务分配的服务器的下标。
     *
     * <p>返回答案数组 ans 。
     *
     * <p>示例 1：
     *
     * <p>输入：servers = [3,3,2], tasks = [1,2,3,2,1,2] 输出：[2,2,0,2,1,2] 解释：事件按时间顺序如下： - 0 秒时，第 0
     * 项任务加入到任务队列，使用第 2 台服务器处理到 1 秒。 - 1 秒时，第 2 台服务器空闲，第 1 项任务加入到任务队列，使用第 2 台服务器处理到 3 秒。 - 2 秒时，第 2
     * 项任务加入到任务队列，使用第 0 台服务器处理到 5 秒。 - 3 秒时，第 2 台服务器空闲，第 3 项任务加入到任务队列，使用第 2 台服务器处理到 5 秒。 - 4 秒时，第 4
     * 项任务加入到任务队列，使用第 1 台服务器处理到 5 秒。 - 5 秒时，所有服务器都空闲，第 5 项任务加入到任务队列，使用第 2 台服务器处理到 7 秒。 示例 2：
     *
     * <p>输入：servers = [5,1,4,3,2], tasks = [2,1,2,4,5,2,1] 输出：[1,4,1,4,1,3,2] 解释：事件按时间顺序如下： - 0
     * 秒时，第 0 项任务加入到任务队列，使用第 1 台服务器处理到 2 秒。 - 1 秒时，第 1 项任务加入到任务队列，使用第 4 台服务器处理到 2 秒。 - 2 秒时，第 1 台和第
     * 4 台服务器空闲，第 2 项任务加入到任务队列，使用第 1 台服务器处理到 4 秒。 - 3 秒时，第 3 项任务加入到任务队列，使用第 4 台服务器处理到 7 秒。 - 4 秒时，第
     * 1 台服务器空闲，第 4 项任务加入到任务队列，使用第 1 台服务器处理到 9 秒。 - 5 秒时，第 5 项任务加入到任务队列，使用第 3 台服务器处理到 7 秒。 - 6 秒时，第
     * 6 项任务加入到任务队列，使用第 2 台服务器处理到 7 秒。
     *
     * <p>提示：
     *
     * <p>servers.length == n tasks.length == m 1 <= n, m <= 2 * 105 1 <= servers[i], tasks[j] <= 2
     * * 105
     *
     * @param servers
     * @param tasks
     * @return
     */
    public int[] assignTasks(int[] servers, int[] tasks) {

        int len1 = servers.length, len2 = tasks.length;
        // 空闲服务器
        PriorityQueue<ServerNode> unable =
                new PriorityQueue<>(
                        (a, b) -> a.weight == b.weight ? a.id - b.id : a.weight - b.weight);

        for (int i = 0; i < len1; i++) {
            unable.offer(new ServerNode(i, servers[i], 0));
        }
        // 运行服务器
        PriorityQueue<ServerNode> runServer =
                new PriorityQueue<>((a, b) -> (int) (a.time - b.time));
        int[] result = new int[len2];
        Arrays.fill(result, -1);
        int curTime = 0;
        for (int i = 0; i < len2; i++) {
            curTime = Math.max(curTime, i);
            // 将  运行结束的服务器runServer (运行结束) 中 <= curTime  取出并放入 空闲服务器 unable
            while (!runServer.isEmpty() && runServer.peek().time <= curTime) {
                ServerNode node = runServer.poll();
                unable.offer(node);
            }
            // 当前curTime 没有可以用的服务器
            if (unable.isEmpty()) {
                curTime = runServer.peek().time;
                while (!runServer.isEmpty() && runServer.peek().time <= curTime) {
                    ServerNode node = runServer.poll();
                    unable.offer(node);
                }
            }
            // 可用服务器
            ServerNode server = unable.poll();
            server.time = curTime + tasks[i];
            runServer.offer(server);
            result[i] = server.id;
        }

        return result;
    }

    static class ServerNode {
        int id, weight;
        int time;

        public ServerNode(int id, int weight, int time) {
            this.id = id;
            this.weight = weight;
            this.time = time;
        }
    }

    /**
     * 1439. 有序矩阵中的第 k 个最小数组和
     *
     * <p>给你一个 m * n 的矩阵 mat，以及一个整数 k ，矩阵中的每一行都以非递减的顺序排列。
     *
     * <p>你可以从每一行中选出 1 个元素形成一个数组。返回所有可能数组中的第 k 个 最小 数组和。
     *
     * <p>示例 1：
     *
     * <p>输入：mat = [[1,3,11],[2,4,6]], k = 5 输出：7 解释：从每一行中选出一个元素，前 k 个和最小的数组分别是： [1,2], [1,4],
     * [3,2], [3,4], [1,6]。其中第 5 个的和是 7 。 示例 2：
     *
     * <p>输入：mat = [[1,3,11],[2,4,6]], k = 9 输出：17 示例 3：
     *
     * <p>输入：mat = [[1,10,10],[1,4,5],[2,3,6]], k = 7 输出：9 解释：从每一行中选出一个元素，前 k 个和最小的数组分别是： [1,1,2],
     * [1,1,3], [1,4,2], [1,4,3], [1,1,6], [1,5,2], [1,5,3]。其中第 7 个的和是 9 。 示例 4：
     *
     * <p>输入：mat = [[1,1,10],[2,2,9]], k = 7 输出：12
     *
     * <p>提示：
     *
     * <p>m == mat.length n == mat.length[i] 1 <= m, n <= 40 1 <= k <= min(200, n ^ m) 1 <=
     * mat[i][j] <= 5000 mat[i] 是一个非递减数组
     *
     * @param mat
     * @param k
     * @return
     */
    public int kthSmallest(int[][] mat, int k) {
        int m = mat.length, n = mat[0].length;
        int min = 0;
        for (int[] nums : mat) {
            min += nums[0];
        }
        if (k == 1) {
            return min;
        }
        int[][] subNums = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 1; j < n; j++) {
                subNums[i][j] = mat[i][j] - mat[i][j - 1];
            }
        }
        logResult(subNums);
        PriorityQueue<ArrayNode> heap = new PriorityQueue<>(Comparator.comparingInt(a -> a.sum));
        int[] initNums = new int[m];
        heap.offer(new ArrayNode(initNums, min));

        Set<String> visit = new HashSet<>();
        visit.add(Arrays.toString(initNums));

        for (int idx = 1; idx < k; idx++) {
            ArrayNode node = heap.poll();
            // 下一个节点
            int[] preIdx = node.preIdx;
            for (int i = 0; i < m; i++) {
                if (preIdx[i] == n - 1) {
                    continue;
                }
                int[] tmp = preIdx.clone();
                tmp[i]++;
                String hash = Arrays.toString(tmp);
                if (visit.contains(hash)) {
                    continue;
                }
                visit.add(hash);
                int num = node.sum + subNums[i][tmp[i]];
                heap.offer(new ArrayNode(tmp, num));
            }
        }

        return heap.peek().sum;
    }

    static class ArrayNode {
        int[] preIdx;
        int sum;

        public ArrayNode(int[] preIdx, int sum) {
            this.preIdx = preIdx;
            this.sum = sum;
        }
    }

    /**
     * 1499. 满足不等式的最大值
     *
     * <p>给你一个数组 points 和一个整数 k 。数组中每个元素都表示二维平面上的点的坐标，并按照横坐标 x 的值从小到大排序。也就是说 points[i] = [xi, yi]
     * ，并且在 1 <= i < j <= points.length 的前提下， xi < xj 总成立。
     *
     * <p>请你找出 yi + yj + |xi - xj| 的 最大值，其中 |xi - xj| <= k 且 1 <= i < j <= points.length。
     *
     * <p>题目测试数据保证至少存在一对能够满足 |xi - xj| <= k 的点。
     *
     * <p>示例 1：
     *
     * <p>输入：points = [[1,3],[2,0],[5,10],[6,-10]], k = 1 输出：4 解释：前两个点满足 |xi - xj| <= 1 ，代入方程计算，则得到值
     * 3 + 0 + |1 - 2| = 4 。第三个和第四个点也满足条件，得到值 10 + -10 + |5 - 6| = 1 。 没有其他满足条件的点，所以返回 4 和 1 中最大的那个。
     * 示例 2：
     *
     * <p>输入：points = [[0,0],[3,0],[9,2]], k = 3 输出：3 解释：只有前两个点满足 |xi - xj| <= 3 ，代入方程后得到值 0 + 0 +
     * |0 - 3| = 3 。
     *
     * <p>提示：
     *
     * <p>2 <= points.length <= 10^5 points[i].length == 2 -10^8 <= points[i][0], points[i][1] <=
     * 10^8 0 <= k <= 2 * 10^8 对于所有的1 <= i < j <= points.length ，points[i][0] < points[j][0]
     * 都成立。也就是说，xi 是严格递增的。
     *
     * @param points
     * @param k
     * @return
     */
    public int findMaxValueOfEquation(int[][] points, int k) {
        // points 递增 滑动窗口
        int len = points.length, result = Integer.MIN_VALUE;
        int left = 0, right = 1;
        while (right < len) {
            if (left >= right) {
                right++;
                continue;
            }

            if (points[right][0] - points[left][0] > k) {
                left++;
                continue;
            }
            result =
                    Math.max(
                            result,
                            points[right][1]
                                    + points[left][1]
                                    + points[right][0]
                                    - points[left][0]);
            // 判断 下一个
            // nextNumLeft = nextX  + nextY + points[left][1] - points[left][0];
            // nextNumRight = nextX  + nextY + points[right][1] - points[right][0];

            // nextNumRight > nextNumLeft left++;
            if (points[right][1] - points[right][0] > points[left][1] - points[left][0]) {
                left++;
                continue;
            }
            if (right == len - 1) {
                left++;
                continue;
            }

            right++;
        }

        return result;
    }

    /**
     * 1606. 找到处理最多请求的服务器
     *
     * <p>你有 k 个服务器，编号为 0 到 k-1 ，它们可以同时处理多个请求组。每个服务器有无穷的计算能力但是 不能同时处理超过一个请求 。请求分配到服务器的规则如下：
     *
     * <p>第 i （序号从 0 开始）个请求到达。 如果所有服务器都已被占据，那么该请求被舍弃（完全不处理）。 如果第 (i % k) 个服务器空闲，那么对应服务器会处理该请求。
     * 否则，将请求安排给下一个空闲的服务器（服务器构成一个环，必要的话可能从第 0 个服务器开始继续找下一个空闲的服务器）。比方说，如果第 i 个服务器在忙，那么会查看第 (i+1)
     * 个服务器，第 (i+2) 个服务器等等。 给你一个 严格递增 的正整数数组 arrival ，表示第 i 个任务的到达时间，和另一个数组 load ，其中 load[i] 表示第 i
     * 个请求的工作量（也就是服务器完成它所需要的时间）。你的任务是找到 最繁忙的服务器 。最繁忙定义为一个服务器处理的请求数是所有服务器里最多的。
     *
     * <p>请你返回包含所有 最繁忙服务器 序号的列表，你可以以任意顺序返回这个列表。
     *
     * <p>示例 1：
     *
     * <p>输入：k = 3, arrival = [1,2,3,4,5], load = [5,2,3,3,3] 输出：[1] 解释： 所有服务器一开始都是空闲的。 前 3 个请求分别由前
     * 3 台服务器依次处理。 请求 3 进来的时候，服务器 0 被占据，所以它呗安排到下一台空闲的服务器，也就是服务器 1 。 请求 4 进来的时候，由于所有服务器都被占据，该请求被舍弃。
     * 服务器 0 和 2 分别都处理了一个请求，服务器 1 处理了两个请求。所以服务器 1 是最忙的服务器。 示例 2：
     *
     * <p>输入：k = 3, arrival = [1,2,3,4], load = [1,2,1,2] 输出：[0] 解释： 前 3 个请求分别被前 3 个服务器处理。 请求 3
     * 进来，由于服务器 0 空闲，它被服务器 0 处理。 服务器 0 处理了两个请求，服务器 1 和 2 分别处理了一个请求。所以服务器 0 是最忙的服务器。 示例 3：
     *
     * <p>输入：k = 3, arrival = [1,2,3], load = [10,12,11] 输出：[0,1,2] 解释：每个服务器分别处理了一个请求，所以它们都是最忙的服务器。
     * 示例 4：
     *
     * <p>输入：k = 3, arrival = [1,2,3,4,8,9,10], load = [5,2,10,3,1,2,2] 输出：[1] 示例 5：
     *
     * <p>输入：k = 1, arrival = [1], load = [1] 输出：[0]
     *
     * <p>提示：
     *
     * <p>1 <= k <= 105 1 <= arrival.length, load.length <= 105 arrival.length == load.length 1 <=
     * arrival[i], load[i] <= 109 arrival 保证 严格递增 。
     *
     * @param k
     * @param arrival
     * @param load
     * @return
     */
    public List<Integer> busiestServers(int k, int[] arrival, int[] load) {

        PriorityQueue<ServNode> queue = new PriorityQueue<>(Comparator.comparingInt(a -> a.time));
        int[] serverNum = new int[k];
        int len = arrival.length;
        TreeSet<Integer> serverSet = new TreeSet<>();
        for (int i = 0; i < k; i++) {
            serverSet.add(i);
        }

        for (int i = 0; i < len; i++) {
            int curTime = arrival[i], loadTime = load[i];
            while (!queue.isEmpty() && queue.peek().time < curTime) {
                ServNode node = queue.poll();
                serverSet.add(node.id);
            }
            // 没有空闲的服务器  跳过
            if (serverSet.isEmpty()) {
                continue;
            }
            // 找服务器id
            Integer id = serverSet.ceiling(i % k);
            if (Objects.isNull(id)) {
                id = serverSet.first();
            }
            queue.offer(new ServNode(id, curTime + loadTime - 1));
            serverSet.remove(id);
            serverNum[id]++;
        }
        log.debug("serverNum:{}", serverNum);
        List<Integer> result = new ArrayList<>();
        int max = 0;

        for (int i = 0; i < k; i++) {
            if (serverNum[i] > max) {
                max = serverNum[i];
                result.clear();
                result.add(i);

            } else if (serverNum[i] == max) {
                result.add(i);
            }
        }

        return result;
    }

    static class ServNode {
        int id, time;

        public ServNode(int id, int time) {
            this.id = id;
            this.time = time;
        }
    }

    /**
     * 5776. 判断矩阵经轮转后是否一致
     *
     * <p>给你两个大小为 n x n 的二进制矩阵 mat 和 target 。现 以 90 度顺时针轮转 矩阵 mat 中的元素 若干次 ，如果能够使 mat 与 target 一致，返回
     * true ；否则，返回 false 。
     *
     * <p>示例 1：
     *
     * <p>输入：mat = [[0,1],[1,0]], target = [[1,0],[0,1]] 输出：true 解释：顺时针轮转 90 度一次可以使 mat 和 target 一致。
     * 示例 2：
     *
     * <p>输入：mat = [[0,1],[1,1]], target = [[1,0],[0,1]] 输出：false 解释：无法通过轮转矩阵中的元素使 equal 与 target
     * 一致。 示例 3：
     *
     * <p>输入：mat = [[0,0,0],[0,1,0],[1,1,1]], target = [[1,1,1],[0,1,0],[0,0,0]] 输出：true 解释：顺时针轮转 90
     * 度两次可以使 mat 和 target 一致。
     *
     * <p>提示：
     *
     * <p>n == mat.length == target.length n == mat[i].length == target[i].length 1 <= n <= 10
     * mat[i][j] 和 target[i][j] 不是 0 就是 1
     *
     * @param mat
     * @param target
     * @return
     */
    public boolean findRotation(int[][] mat, int[][] target) {
        int n = mat.length;
        if (n == 1) {
            return mat[0][0] == target[0][0];
        }
        String[] matRow = new String[n], matCol = new String[n];
        for (int i = 0; i < n; i++) {
            StringBuilder strRow = new StringBuilder(), strCol = new StringBuilder();
            for (int j = 0; j < n; j++) {
                strRow.append(mat[i][j]);
                strCol.append(mat[j][i]);
            }
            matRow[i] = strRow.toString();
            matCol[i] = strCol.toString();
        }
        log.debug("matRow:{}", matRow.toString());
        log.debug("matCol:{}", matCol);
        String[] targetRow = new String[n];
        for (int i = 0; i < n; i++) {
            StringBuilder strRow = new StringBuilder();
            for (int j = 0; j < n; j++) {
                strRow.append(target[i][j]);
            }
            targetRow[i] = strRow.toString();
        }
        log.debug("matCol:{}", matCol);
        // 完全相同
        boolean flag = true;
        for (int i = 0; i < n; i++) {
            if (!Objects.equals(matRow[i], targetRow[i])) {
                flag = false;
                break;
            }
        }
        if (flag) {
            return true;
        }
        // 90度
        flag = true;
        for (int i = 0; i < n; i++) {
            if (!Objects.equals(new StringBuilder(matCol[i]).reverse().toString(), targetRow[i])) {
                flag = false;
                break;
            }
        }
        if (flag) {
            return true;
        }

        // 180
        flag = true;
        for (int i = 0; i < n; i++) {
            if (!Objects.equals(
                    new StringBuilder(matRow[n - 1 - i]).reverse().toString(), targetRow[i])) {
                flag = false;
                break;
            }
        }
        if (flag) {
            return true;
        }
        // 270
        flag = true;
        for (int i = 0; i < n; i++) {
            if (!Objects.equals(matCol[n - 1 - i], targetRow[i])) {
                flag = false;
                break;
            }
        }
        return flag;
    }

    /**
     * 5777. 使数组元素相等的减少操作次数
     *
     * <p>给你一个整数数组 nums ，你的目标是令 nums 中的所有元素相等。完成一次减少操作需要遵照下面的几个步骤：
     *
     * <p>找出 nums 中的 最大 值。记这个值为 largest 并取其下标 i （下标从 0 开始计数）。如果有多个元素都是最大值，则取最小的 i 。 找出 nums 中的 下一个最大
     * 值，这个值 严格小于 largest ，记为 nextLargest 。 将 nums[i] 减少到 nextLargest 。 返回使 nums 中的所有元素相等的操作次数。
     *
     * <p>示例 1：
     *
     * <p>输入：nums = [5,1,3] 输出：3 解释：需要 3 次操作使 nums 中的所有元素相等： 1. largest = 5 下标为 0 。nextLargest = 3
     * 。将 nums[0] 减少到 3 。nums = [3,1,3] 。 2. largest = 3 下标为 0 。nextLargest = 1 。将 nums[0] 减少到 1
     * 。nums = [1,1,3] 。 3. largest = 3 下标为 2 。nextLargest = 1 。将 nums[2] 减少到 1 。nums = [1,1,1] 。 示例
     * 2：
     *
     * <p>输入：nums = [1,1,1] 输出：0 解释：nums 中的所有元素已经是相等的。 示例 3：
     *
     * <p>输入：nums = [1,1,2,2,3] 输出：4 解释：需要 4 次操作使 nums 中的所有元素相等： 1. largest = 3 下标为 4 。nextLargest =
     * 2 。将 nums[4] 减少到 2 。nums = [1,1,2,2,2] 。 2. largest = 2 下标为 2 。nextLargest = 1 。将 nums[2] 减少到
     * 1 。nums = [1,1,1,2,2] 。 3. largest = 2 下标为 3 。nextLargest = 1 。将 nums[3] 减少到 1 。nums =
     * [1,1,1,1,2] 。 4. largest = 2 下标为 4 。nextLargest = 1 。将 nums[4] 减少到 1 。nums = [1,1,1,1,1] 。
     *
     * <p>提示：
     *
     * <p>1 <= nums.length <= 5 * 104 1 <= nums[i] <= 5 * 104
     *
     * @param nums
     * @return
     */
    public int reductionOperations(int[] nums) {
        Arrays.sort(nums);
        int result = 0, num = 0;

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i - 1]) {
                num++;
            }
            result += num;
        }
        return result;
    }

    /**
     * 1617. 统计子树中城市之间最大距离
     *
     * <p>给你 n 个城市，编号为从 1 到 n 。同时给你一个大小为 n-1 的数组 edges ，其中 edges[i] = [ui, vi] 表示城市 ui 和 vi
     * 之间有一条双向边。题目保证任意城市之间只有唯一的一条路径。换句话说，所有城市形成了一棵 树 。
     *
     * <p>一棵 子树 是城市的一个子集，且子集中任意城市之间可以通过子集中的其他城市和边到达。两个子树被认为不一样的条件是至少有一个城市在其中一棵子树中存在，但在另一棵子树中不存在。
     *
     * <p>对于 d 从 1 到 n-1 ，请你找到城市间 最大距离 恰好为 d 的所有子树数目。
     *
     * <p>请你返回一个大小为 n-1 的数组，其中第 d 个元素（下标从 1 开始）是城市间 最大距离 恰好等于 d 的子树数目。
     *
     * <p>请注意，两个城市间距离定义为它们之间需要经过的边的数目。
     *
     * <p>示例 1：
     *
     * <p>输入：n = 4, edges = [[1,2],[2,3],[2,4]] 输出：[3,4,0] 解释： 子树 {1,2}, {2,3} 和 {2,4} 最大距离都是 1 。 子树
     * {1,2,3}, {1,2,4}, {2,3,4} 和 {1,2,3,4} 最大距离都为 2 。 不存在城市间最大距离为 3 的子树。 示例 2：
     *
     * <p>输入：n = 2, edges = [[1,2]] 输出：[1] 示例 3：
     *
     * <p>输入：n = 3, edges = [[1,2],[2,3]] 输出：[2,1]
     *
     * <p>提示：
     *
     * <p>2 <= n <= 15 edges.length == n-1 edges[i].length == 2 1 <= ui, vi <= n 题目保证 (ui, vi)
     * 所表示的边互不相同。
     *
     * @param n
     * @param edges
     * @return
     */
    public int[] countSubgraphsForEachDiameter(int n, int[][] edges) {
        int[][] dist = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], n);
            dist[i][i] = 0;
        }
        int state = 1 << n;
        for (int[] edge : edges) {
            int from = edge[0] - 1, to = edge[1] - 1;
            dist[from][to] = 1;
            dist[to][from] = 1;
        }
        // 求两个点的最小距离
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[j][k]);
                }
            }
        }
        logResult(dist);
        int[] result = new int[n - 1];
        for (int i = 1; i < state; i++) {
            // 枚举所有子树
            if ((i & (i - 1)) == 0) {
                // 只有一个节点
                continue;
            }
            int d = getSubgraphsDist(i, n, dist);
            if (d != 0) {
                result[d - 1]++;
            }
        }

        return result;
    }

    private int getSubgraphsDist(int state, int n, int[][] dist) {
        int max = 0, edgeNum = 0, city = 0;
        for (int i = 0; i < n; i++) {
            if ((state & (1 << i)) == 0) {
                continue;
            }
            city++;
            for (int j = i + 1; j < n; j++) {
                if ((state & (1 << j)) == 0) {
                    continue;
                }
                max = Math.max(max, dist[i][j]);
                edgeNum += dist[i][j] == 1 ? 1 : 0;
            }
        }
        return edgeNum + 1 == city ? max : 0;
    }

    /**
     * 1627. 带阈值的图连通性
     *
     * <p>有 n 座城市，编号从 1 到 n 。编号为 x 和 y 的两座城市直接连通的前提是： x 和 y 的公因数中，至少有一个 严格大于 某个阈值 threshold
     * 。更正式地说，如果存在整数 z ，且满足以下所有条件，则编号 x 和 y 的城市之间有一条道路：
     *
     * <p>x % z == 0 y % z == 0 z > threshold 给你两个整数 n 和 threshold ，以及一个待查询数组，请你判断每个查询 queries[i] =
     * [ai, bi] 指向的城市 ai 和 bi 是否连通（即，它们之间是否存在一条路径）。
     *
     * <p>返回数组 answer ，其中answer.length == queries.length 。如果第 i 个查询中指向的城市 ai 和 bi 连通，则 answer[i] 为
     * true ；如果不连通，则 answer[i] 为 false 。
     *
     * <p>示例 1：
     *
     * <p>输入：n = 6, threshold = 2, queries = [[1,4],[2,5],[3,6]] 输出：[false,false,true] 解释：每个数的因数如下：
     * 1: 1 2: 1, 2 3: 1, 3 4: 1, 2, 4 5: 1, 5 6: 1, 2, 3, 6 所有大于阈值的的因数已经加粗标识，只有城市 3 和 6 共享公约数 3
     * ，因此结果是： [1,4] 1 与 4 不连通 [2,5] 2 与 5 不连通 [3,6] 3 与 6 连通，存在路径 3--6 示例 2：
     *
     * <p>输入：n = 6, threshold = 0, queries = [[4,5],[3,4],[3,2],[2,6],[1,3]]
     * 输出：[true,true,true,true,true] 解释：每个数的因数与上一个例子相同。但是，由于阈值为 0 ，所有的因数都大于阈值。因为所有的数字共享公因数 1
     * ，所以所有的城市都互相连通。 示例 3：
     *
     * <p>输入：n = 5, threshold = 1, queries = [[4,5],[4,5],[3,2],[2,3],[3,4]]
     * 输出：[false,false,false,false,false] 解释：只有城市 2 和 4 共享的公约数 2 严格大于阈值 1 ，所以只有这两座城市是连通的。 注意，同一对节点
     * [x, y] 可以有多个查询，并且查询 [x，y] 等同于查询 [y，x] 。
     *
     * <p>提示：
     *
     * <p>2 <= n <= 104 0 <= threshold <= n 1 <= queries.length <= 105 queries[i].length == 2 1 <=
     * ai, bi <= cities ai != bi
     *
     * @param n
     * @param threshold
     * @param queries
     * @return
     */
    public List<Boolean> areConnected(int n, int threshold, int[][] queries) {
        int len = queries.length;
        List<Boolean> result = new ArrayList<>();
        if (threshold == 0) {
            for (int i = 0; i < len; i++) {
                result.add(true);
            }
            return result;
        }
        // 并查集 cong threshold 开始
        this.dsu = new Dsu(n + 1);
        for (int i = threshold + 1; i <= n; i++) {

            int t = 2;
            while (i * t <= n) {

                dsu.union(i, i * t);
                t++;
            }
        }
        for (int[] query : queries) {
            int num1 = query[0], num2 = query[1];
            result.add(dsu.isConnected(num1, num2));
        }

        return result;
    }

    /**
     * 1632. 矩阵转换后的秩
     *
     * <p>给你一个 m x n 的矩阵 matrix ，请你返回一个新的矩阵 answer ，其中 answer[row][col] 是 matrix[row][col] 的秩。
     *
     * <p>每个元素的 秩 是一个整数，表示这个元素相对于其他元素的大小关系，它按照如下规则计算：
     *
     * <p>秩是从 1 开始的一个整数。 如果两个元素 p 和 q 在 同一行 或者 同一列 ，那么： 如果 p < q ，那么 rank(p) < rank(q) 如果 p == q ，那么
     * rank(p) == rank(q) 如果 p > q ，那么 rank(p) > rank(q) 秩 需要越 小 越好。 题目保证按照上面规则 answer 数组是唯一的。
     *
     * <p>示例 1：
     *
     * <p>输入：matrix = [[1,2],[3,4]] 输出：[[1,2],[2,3]] 解释： matrix[0][0] 的秩为 1 ，因为它是所在行和列的最小整数。
     * matrix[0][1] 的秩为 2 ，因为 matrix[0][1] > matrix[0][0] 且 matrix[0][0] 的秩为 1 。 matrix[1][0] 的秩为 2
     * ，因为 matrix[1][0] > matrix[0][0] 且 matrix[0][0] 的秩为 1 。 matrix[1][1] 的秩为 3 ，因为 matrix[1][1] >
     * matrix[0][1]， matrix[1][1] > matrix[1][0] 且 matrix[0][1] 和 matrix[1][0] 的秩都为 2 。 示例 2：
     *
     * <p>输入：matrix = [[7,7],[7,7]] 输出：[[1,1],[1,1]] 示例 3：
     *
     * <p>输入：matrix = [[20,-21,14],[-19,4,19],[22,-47,24],[-19,4,19]]
     * 输出：[[4,2,3],[1,3,4],[5,1,6],[1,3,4]] 示例 4：
     *
     * <p>输入：matrix = [[7,3,6],[1,4,5],[9,8,2]] 输出：[[5,1,4],[1,2,3],[6,3,1]]
     *
     * <p>提示：
     *
     * <p>m == matrix.length n == matrix[i].length 1 <= m, n <= 500 -109 <= matrix[row][col] <= 109
     *
     * @param matrix
     * @return
     */
    public int[][] matrixRankTransform(int[][] matrix) {
        this.matrix = matrix;
        this.m = matrix.length;
        this.n = matrix[0].length;
        int[][] result = new int[m][n];
        Integer[] indexs = new Integer[m * n];
        for (int i = 0; i < m * n; i++) {
            indexs[i] = i;
        }
        Arrays.sort(
                indexs,
                (a, b) -> {
                    int row1 = a / n, col1 = a % n, row2 = b / n, col2 = b % n;
                    return matrix[row1][col1] - matrix[row2][col2];
                });
        log.debug("indexs:{}", Arrays.toString(indexs));
        // 每行的最小列  每行的最小行
        int[] minRowIndex = new int[m], minColIndex = new int[n];
        Arrays.fill(minColIndex, -1);
        Arrays.fill(minRowIndex, -1);
        this.indexValues = new int[m * n];
        this.dsu = new Dsu(m * n);
        for (int idx : indexs) {
            int row = idx / n, col = idx % n;
            int val = 1;
            // 判断当前 行
            if (minRowIndex[row] != -1) {
                val = getVal(row, minRowIndex[row], idx, val);
            }
            // 判断当前 列
            if (minColIndex[col] != -1) {
                val = getVal(minColIndex[col], col, idx, val);
            }
            minRowIndex[row] = col;
            minColIndex[col] = row;
            indexValues[dsu.findParent(idx)] = val;
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int idx = i * n + j;
                result[i][j] = indexValues[dsu.findParent(idx)];
            }
        }

        return result;
    }

    private int[][] matrix;

    private int[] indexValues;

    private Dsu dsu;

    private int getVal(int preRow, int preCol, int idx, int val) {
        int row = idx / n, col = idx % n;
        int preIdx = preRow * n + preCol;
        int preVal = indexValues[dsu.findParent(preIdx)];

        if (matrix[preRow][preCol] == matrix[row][col]) {
            dsu.union(idx, preIdx);
            val = Math.max(val, preVal);
        } else {
            val = Math.max(val, preVal + 1);
        }
        return val;
    }

    /**
     * 1649. 通过指令创建有序数组
     *
     * <p>给你一个整数数组 instructions ，你需要根据 instructions 中的元素创建一个有序数组。一开始你有一个空的数组 nums ，你需要 从左到右 遍历
     * instructions 中的元素，将它们依次插入 nums 数组中。每一次插入操作的 代价 是以下两者的 较小值 ：
     *
     * <p>nums 中 严格小于 instructions[i] 的数字数目。 nums 中 严格大于 instructions[i] 的数字数目。 比方说，如果要将 3 插入到 nums
     * = [1,2,3,5] ，那么插入操作的 代价 为 min(2, 1) (元素 1 和 2 小于 3 ，元素 5 大于 3 ），插入后 nums 变成 [1,2,3,3,5] 。
     *
     * <p>请你返回将 instructions 中所有元素依次插入 nums 后的 总最小代价 。由于答案会很大，请将它对 109 + 7 取余 后返回。
     *
     * <p>示例 1：
     *
     * <p>输入：instructions = [1,5,6,2] 输出：1 解释：一开始 nums = [] 。 插入 1 ，代价为 min(0, 0) = 0 ，现在 nums = [1]
     * 。 插入 5 ，代价为 min(1, 0) = 0 ，现在 nums = [1,5] 。 插入 6 ，代价为 min(2, 0) = 0 ，现在 nums = [1,5,6] 。 插入
     * 2 ，代价为 min(1, 2) = 1 ，现在 nums = [1,2,5,6] 。 总代价为 0 + 0 + 0 + 1 = 1 。 示例 2:
     *
     * <p>输入：instructions = [1,2,3,6,5,4] 输出：3 解释：一开始 nums = [] 。 插入 1 ，代价为 min(0, 0) = 0 ，现在 nums =
     * [1] 。 插入 2 ，代价为 min(1, 0) = 0 ，现在 nums = [1,2] 。 插入 3 ，代价为 min(2, 0) = 0 ，现在 nums = [1,2,3] 。
     * 插入 6 ，代价为 min(3, 0) = 0 ，现在 nums = [1,2,3,6] 。 插入 5 ，代价为 min(3, 1) = 1 ，现在 nums = [1,2,3,5,6]
     * 。 插入 4 ，代价为 min(3, 2) = 2 ，现在 nums = [1,2,3,4,5,6] 。 总代价为 0 + 0 + 0 + 0 + 1 + 2 = 3 。 示例 3：
     *
     * <p>输入：instructions = [1,3,3,3,2,4,2,1,2] 输出：4 解释：一开始 nums = [] 。 插入 1 ，代价为 min(0, 0) = 0 ，现在
     * nums = [1] 。 插入 3 ，代价为 min(1, 0) = 0 ，现在 nums = [1,3] 。 插入 3 ，代价为 min(1, 0) = 0 ，现在 nums =
     * [1,3,3] 。 插入 3 ，代价为 min(1, 0) = 0 ，现在 nums = [1,3,3,3] 。 插入 2 ，代价为 min(1, 3) = 1 ，现在 nums =
     * [1,2,3,3,3] 。 插入 4 ，代价为 min(5, 0) = 0 ，现在 nums = [1,2,3,3,3,4] 。 ​​​​​插入 2 ，代价为 min(1, 4) = 1
     * ，现在 nums = [1,2,2,3,3,3,4] 。 插入 1 ，代价为 min(0, 6) = 0 ，现在 nums = [1,1,2,2,3,3,3,4] 。 插入 2 ，代价为
     * min(2, 4) = 2 ，现在 nums = [1,1,2,2,2,3,3,3,4] 。 总代价为 0 + 0 + 0 + 0 + 1 + 0 + 1 + 0 + 2 = 4 。
     *
     * <p>提示：
     *
     * <p>1 <= instructions.length <= 105 1 <= instructions[i] <= 105
     *
     * @param instructions
     * @return
     */
    public int createSortedArray(int[] instructions) {
        long result = 0L;
        int max = Arrays.stream(instructions).max().orElse(-1);
        BIT bit = new BIT(max);
        int count = 0;
        for (int num : instructions) {
            int left = bit.getSum(num - 1);
            int numCount = bit.getSum(num);
            result += Math.min(left, count - numCount);
            bit.update(num, 1);
            count++;
        }
        return (int) (result % MOD);
    }

    static final int MOD = 1_000_000_007;

    /**
     * 1675. 数组的最小偏移量
     *
     * <p>给你一个由 n 个正整数组成的数组 nums 。
     *
     * <p>你可以对数组的任意元素执行任意次数的两类操作：
     *
     * <p>如果元素是 偶数 ，除以 2 例如，如果数组是 [1,2,3,4] ，那么你可以对最后一个元素执行此操作，使其变成 [1,2,3,2] 如果元素是 奇数 ，乘上 2
     * 例如，如果数组是 [1,2,3,4] ，那么你可以对第一个元素执行此操作，使其变成 [2,2,3,4] 数组的 偏移量 是数组中任意两个元素之间的 最大差值 。
     *
     * <p>返回数组在执行某些操作之后可以拥有的 最小偏移量 。
     *
     * <p>示例 1：
     *
     * <p>输入：nums = [1,2,3,4] 输出：1 解释：你可以将数组转换为 [1,2,3,2]，然后转换成 [2,2,3,2]，偏移量是 3 - 2 = 1 示例 2：
     *
     * <p>输入：nums = [4,1,5,20,3] 输出：3 解释：两次操作后，你可以将数组转换为 [4,2,5,5,3]，偏移量是 5 - 2 = 3 示例 3：
     *
     * <p>输入：nums = [2,10,8] 输出：3
     *
     * <p>提示：
     *
     * <p>n == nums.length 2 <= n <= 105 1 <= nums[i] <= 109
     *
     * @param nums
     * @return
     */
    public int minimumDeviation(int[] nums) {
        int n = nums.length;
        TreeSet<Integer> treeSet = new TreeSet<>();
        for (int num : nums) {
            treeSet.add((num & 1) == 0 ? num : num << 1);
        }
        int result = treeSet.last() - treeSet.first();
        int max = treeSet.last();
        while (result > 0 && (max & 1) == 0) {
            treeSet.remove(max);
            treeSet.add(max >> 1);
            max = treeSet.last();
            result = Math.min(result, max - treeSet.first());
        }

        return result;
    }

    /**
     * 5767. 检查是否区域内所有整数都被覆盖
     *
     * <p>给你一个二维整数数组 ranges 和两个整数 left 和 right 。每个 ranges[i] = [starti, endi] 表示一个从 starti 到 endi 的
     * 闭区间 。
     *
     * <p>如果闭区间 [left, right] 内每个整数都被 ranges 中 至少一个 区间覆盖，那么请你返回 true ，否则返回 false 。
     *
     * <p>已知区间 ranges[i] = [starti, endi] ，如果整数 x 满足 starti <= x <= endi ，那么我们称整数x 被覆盖了。
     *
     * <p>示例 1：
     *
     * <p>输入：ranges = [[1,2],[3,4],[5,6]], left = 2, right = 5 输出：true 解释：2 到 5 的每个整数都被覆盖了： - 2
     * 被第一个区间覆盖。 - 3 和 4 被第二个区间覆盖。 - 5 被第三个区间覆盖。 示例 2：
     *
     * <p>输入：ranges = [[1,10],[10,20]], left = 21, right = 21 输出：false 解释：21 没有被任何一个区间覆盖。
     *
     * <p>提示：
     *
     * <p>1 <= ranges.length <= 50 1 <= starti <= endi <= 50 1 <= left <= right <= 50
     *
     * @param ranges
     * @param left
     * @param right
     * @return
     */
    public boolean isCovered(int[][] ranges, int left, int right) {

        for (int i = left; i <= right; i++) {
            boolean flag = false;
            for (int[] range : ranges) {
                if (i >= range[0] && i <= range[1]) {
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                return false;
            }
        }

        return true;
    }

    /**
     * 5768. 找到需要补充粉笔的学生编号
     *
     * <p>一个班级里有 n 个学生，编号为 0 到 n - 1 。每个学生会依次回答问题，编号为 0 的学生先回答，然后是编号为 1 的学生，以此类推，直到编号为 n - 1
     * 的学生，然后老师会重复这个过程，重新从编号为 0 的学生开始回答问题。
     *
     * <p>给你一个长度为 n 且下标从 0 开始的整数数组 chalk 和一个整数 k 。一开始粉笔盒里总共有 k 支粉笔。当编号为 i 的学生回答问题时，他会消耗 chalk[i]
     * 支粉笔。如果剩余粉笔数量 严格小于 chalk[i] ，那么学生 i 需要 补充 粉笔。
     *
     * <p>请你返回需要 补充 粉笔的学生 编号 。
     *
     * <p>示例 1：
     *
     * <p>输入：chalk = [5,1,5], k = 22 输出：0 解释：学生消耗粉笔情况如下： - 编号为 0 的学生使用 5 支粉笔，然后 k = 17 。 - 编号为 1
     * 的学生使用 1 支粉笔，然后 k = 16 。 - 编号为 2 的学生使用 5 支粉笔，然后 k = 11 。 - 编号为 0 的学生使用 5 支粉笔，然后 k = 6 。 - 编号为
     * 1 的学生使用 1 支粉笔，然后 k = 5 。 - 编号为 2 的学生使用 5 支粉笔，然后 k = 0 。 编号为 0 的学生没有足够的粉笔，所以他需要补充粉笔。 示例 2：
     *
     * <p>输入：chalk = [3,4,1,2], k = 25 输出：1 解释：学生消耗粉笔情况如下： - 编号为 0 的学生使用 3 支粉笔，然后 k = 22 。 - 编号为 1
     * 的学生使用 4 支粉笔，然后 k = 18 。 - 编号为 2 的学生使用 1 支粉笔，然后 k = 17 。 - 编号为 3 的学生使用 2 支粉笔，然后 k = 15 。 - 编号为
     * 0 的学生使用 3 支粉笔，然后 k = 12 。 - 编号为 1 的学生使用 4 支粉笔，然后 k = 8 。 - 编号为 2 的学生使用 1 支粉笔，然后 k = 7 。 - 编号为
     * 3 的学生使用 2 支粉笔，然后 k = 5 。 - 编号为 0 的学生使用 3 支粉笔，然后 k = 2 。 编号为 1 的学生没有足够的粉笔，所以他需要补充粉笔。
     *
     * <p>提示：
     *
     * <p>chalk.length == n 1 <= n <= 105 1 <= chalk[i] <= 105 1 <= k <= 109
     *
     * @param chalk
     * @param k
     * @return
     */
    public int chalkReplacer(int[] chalk, int k) {
        int len = chalk.length;
        long sum = 0L;
        for (int num : chalk) {
            sum += num;
        }

        long leftNum = k % sum;
        for (int i = 0; i < len; i++) {
            if (leftNum < chalk[i]) {
                return i;
            }
            leftNum -= chalk[i];
        }

        return 0;
    }

    /**
     * 5202. 最大的幻方
     *
     * <p>一个 k x k 的 幻方 指的是一个 k x k 填满整数的方格阵，且每一行、每一列以及两条对角线的和 全部相等 。幻方中的整数 不需要互不相同 。显然，每个 1 x 1
     * 的方格都是一个幻方。
     *
     * <p>给你一个 m x n 的整数矩阵 grid ，请你返回矩阵中 最大幻方 的 尺寸 （即边长 k）。
     *
     * <p>示例 1：
     *
     * <p>输入：grid = [[7,1,4,5,6],[2,5,1,6,4],[1,5,4,3,2],[1,2,7,3,4]] 输出：3 解释：最大幻方尺寸为 3 。
     * 每一行，每一列以及两条对角线的和都等于 12 。 - 每一行的和：5+1+6 = 5+4+3 = 2+7+3 = 12 - 每一列的和：5+5+2 = 1+4+7 = 6+3+3 =
     * 12 - 对角线的和：5+4+3 = 6+4+2 = 12 示例 2：
     *
     * <p>输入：grid = [[5,1,3,1],[9,3,3,1],[1,3,3,8]] 输出：2
     *
     * <p>提示：
     *
     * <p>m == grid.length n == grid[i].length 1 <= m, n <= 50 1 <= grid[i][j] <= 106
     *
     * @param grid
     * @return
     */
    public int largestMagicSquare(int[][] grid) {
        this.m = grid.length;
        this.n = grid[0].length;
        this.grid = grid;
        long[][] sums = new long[m + 1][n + 1];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                sums[i + 1][j + 1] = sums[i][j + 1] + sums[i + 1][j] - sums[i][j] + grid[i][j];
            }
        }
        logResult(sums);
        gridSums = sums;
        for (int len = Math.min(m, n); len >= 2; len--) {

            for (int i = 0; i + len <= m; i++) {
                for (int j = 0; j + len <= n; j++) {
                    long sum =
                            sums[i + len][j + len]
                                    + sums[i][j]
                                    - sums[i + len][j]
                                    - sums[i][j + len];
                    if (sum % len != 0) {
                        continue;
                    }
                    long num = sum / len;

                    if (checkMagicSquare(len, i, j, num)) {
                        return len;
                    }
                }
            }
        }

        return 1;
    }

    private int[][] grid;

    private long[][] gridSums;

    private boolean checkMagicSquare(int len, int row, int col, long num) {

        // 行
        for (int i = 1; i <= len; i++) {
            long rowNum =
                    gridSums[row + i][col + len]
                            + gridSums[row + i - 1][col]
                            - gridSums[row + i][col]
                            - gridSums[row + i - 1][col + len];
            if (rowNum != num) {
                return false;
            }
        }

        // 列
        for (int j = 1; j <= len; j++) {
            long colNum =
                    gridSums[row + len][col + j]
                            + gridSums[row][col + j - 1]
                            - gridSums[row][col + j]
                            - gridSums[row + len][col + j - 1];
            if (colNum != num) {
                return false;
            }
        }

        // 对角线
        long num1 = 0L, num2 = 0L;
        for (int i = 0; i < len; i++) {
            num1 += grid[row + i][col + i];
            num2 += grid[row + len - 1 - i][col + i];
        }

        return num1 == num && num2 == num;
    }
}
