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

    /**
     * 5785. 合并若干三元组以形成目标三元组
     *
     * <p>三元组 是一个由三个整数组成的数组。给你一个二维整数数组 triplets ，其中 triplets[i] = [ai, bi, ci] 表示第 i 个 三元组
     * 。同时，给你一个整数数组 target = [x, y, z] ，表示你想要得到的 三元组 。
     *
     * <p>为了得到 target ，你需要对 triplets 执行下面的操作 任意次（可能 零 次）：
     *
     * <p>选出两个下标（下标 从 0 开始 计数）i 和 j（i != j），并 更新 triplets[j] 为 [max(ai, aj), max(bi, bj), max(ci,
     * cj)] 。 例如，triplets[i] = [2, 5, 3] 且 triplets[j] = [1, 7, 5]，triplets[j] 将会更新为 [max(2, 1),
     * max(5, 7), max(3, 5)] = [2, 7, 5] 。 如果通过以上操作我们可以使得目标 三元组 target 成为 triplets 的一个 元素 ，则返回 true
     * ；否则，返回 false 。
     *
     * <p>示例 1：
     *
     * <p>输入：triplets = [[2,5,3],[1,8,4],[1,7,5]], target = [2,7,5] 输出：true 解释：执行下述操作： -
     * 选择第一个和最后一个三元组 [[2,5,3],[1,8,4],[1,7,5]] 。更新最后一个三元组为 [max(2,1), max(5,7), max(3,5)] = [2,7,5]
     * 。triplets = [[2,5,3],[1,8,4],[2,7,5]] 目标三元组 [2,7,5] 现在是 triplets 的一个元素。 示例 2：
     *
     * <p>输入：triplets = [[1,3,4],[2,5,8]], target = [2,5,8] 输出：true 解释：目标三元组 [2,5,8] 已经是 triplets
     * 的一个元素。 示例 3：
     *
     * <p>输入：triplets = [[2,5,3],[2,3,4],[1,2,5],[5,2,3]], target = [5,5,5] 输出：true 解释：执行下述操作： -
     * 选择第一个和第三个三元组 [[2,5,3],[2,3,4],[1,2,5],[5,2,3]] 。更新第三个三元组为 [max(2,1), max(5,2), max(3,5)] =
     * [2,5,5] 。triplets = [[2,5,3],[2,3,4],[2,5,5],[5,2,3]] 。 - 选择第三个和第四个三元组
     * [[2,5,3],[2,3,4],[2,5,5],[5,2,3]] 。更新第四个三元组为 [max(2,5), max(5,2), max(5,3)] = [5,5,5]
     * 。triplets = [[2,5,3],[2,3,4],[2,5,5],[5,5,5]] 。 目标三元组 [5,5,5] 现在是 triplets 的一个元素。 示例 4：
     *
     * <p>输入：triplets = [[3,4,5],[4,5,6]], target = [3,2,5] 输出：false 解释：无法得到 [3,2,5] ，因为 triplets 不含
     * 2 。
     *
     * <p>提示：
     *
     * <p>1 <= triplets.length <= 105 triplets[i].length == target.length == 3 1 <= ai, bi, ci, x,
     * y, z <= 1000
     *
     * @param triplets
     * @param target
     * @return
     */
    public boolean mergeTriplets(int[][] triplets, int[] target) {

        List<int[]> list = new ArrayList<>();
        for (int[] triplet : triplets) {
            if (triplet[0] > target[0] || triplet[1] > target[1] || triplet[2] > target[2]) {
                continue;
            }
            if (triplet[0] == target[0] && triplet[1] == target[1] && triplet[2] == target[2]) {
                return true;
            }
            list.add(triplet);
        }
        int x = 0, y = 0, z = 0;
        for (int[] triplet : list) {
            x = Math.max(x, triplet[0]);
            y = Math.max(y, triplet[1]);
            z = Math.max(z, triplet[2]);
        }

        return x == target[0] && y == target[1] && z == target[2];
    }

    /**
     * 1851. 包含每个查询的最小区间
     *
     * <p>给你一个二维整数数组 intervals ，其中 intervals[i] = [lefti, righti] 表示第 i 个区间开始于 lefti 、结束于
     * righti（包含两侧取值，闭区间）。区间的 长度 定义为区间中包含的整数数目，更正式地表达是 righti - lefti + 1 。
     *
     * <p>再给你一个整数数组 queries 。第 j 个查询的答案是满足 lefti <= queries[j] <= righti 的 长度最小区间 i 的长度
     * 。如果不存在这样的区间，那么答案是 -1 。
     *
     * <p>以数组形式返回对应查询的所有答案。
     *
     * <p>示例 1：
     *
     * <p>输入：intervals = [[1,4],[2,4],[3,6],[4,4]], queries = [2,3,4,5] 输出：[3,3,1,4] 解释：查询处理如下： -
     * Query = 2 ：区间 [2,4] 是包含 2 的最小区间，答案为 4 - 2 + 1 = 3 。 - Query = 3 ：区间 [2,4] 是包含 3 的最小区间，答案为 4 -
     * 2 + 1 = 3 。 - Query = 4 ：区间 [4,4] 是包含 4 的最小区间，答案为 4 - 4 + 1 = 1 。 - Query = 5 ：区间 [3,6] 是包含 5
     * 的最小区间，答案为 6 - 3 + 1 = 4 。 示例 2：
     *
     * <p>输入：intervals = [[2,3],[2,5],[1,8],[20,25]], queries = [2,19,5,22] 输出：[2,-1,4,6] 解释：查询处理如下：
     * - Query = 2 ：区间 [2,3] 是包含 2 的最小区间，答案为 3 - 2 + 1 = 2 。 - Query = 19：不存在包含 19 的区间，答案为 -1 。 -
     * Query = 5 ：区间 [2,5] 是包含 5 的最小区间，答案为 5 - 2 + 1 = 4 。 - Query = 22：区间 [20,25] 是包含 22 的最小区间，答案为
     * 25 - 20 + 1 = 6 。
     *
     * <p>提示：
     *
     * <p>1 <= intervals.length <= 105 1 <= queries.length <= 105 queries[i].length == 2 1 <= lefti
     * <= righti <= 107 1 <= queries[j] <= 107
     *
     * @param intervals
     * @param queries
     * @return
     */
    public int[] minInterval(int[][] intervals, int[] queries) {
        // 从小到大排序
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
        int len = queries.length;
        List<IntervalNode> list = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            list.add(new IntervalNode(i, queries[i]));
        }
        list.sort(Comparator.comparingInt(a -> a.query));
        int[] result = new int[len];
        Arrays.fill(result, -1);
        PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(a -> a[1] - a[0]));
        int index = 0;
        for (IntervalNode node : list) {

            while (index < intervals.length && node.query >= intervals[index][0]) {
                queue.offer(intervals[index]);
                index++;
            }
            //  把区间右边界小于queries[i]的区间删除
            while (!queue.isEmpty() && queue.peek()[1] < node.query) {
                queue.poll();
            }
            if (!queue.isEmpty()) {
                int[] interval = queue.peek();
                result[node.idx] = interval[1] - interval[0] + 1;
            }
        }

        return result;
    }

    static class IntervalNode {
        private final int idx, query;

        public IntervalNode(int idx, int query) {
            this.idx = idx;
            this.query = query;
        }
    }

    /**
     * 5791. 统计子岛屿
     *
     * <p>给你两个 m x n 的二进制矩阵 grid1 和 grid2 ，它们只包含 0 （表示水域）和 1 （表示陆地）。一个 岛屿 是由 四个方向 （水平或者竖直）上相邻的 1
     * 组成的区域。任何矩阵以外的区域都视为水域。
     *
     * <p>如果 grid2 的一个岛屿，被 grid1 的一个岛屿 完全 包含，也就是说 grid2 中该岛屿的每一个格子都被 grid1 中同一个岛屿完全包含，那么我们称 grid2
     * 中的这个岛屿为 子岛屿 。
     *
     * <p>请你返回 grid2 中 子岛屿 的 数目 。
     *
     * <p>示例 1：
     *
     * <p>输入：grid1 = [[1,1,1,0,0],[0,1,1,1,1],[0,0,0,0,0],[1,0,0,0,0],[1,1,0,1,1]], grid2 =
     * [[1,1,1,0,0],[0,0,1,1,1],[0,1,0,0,0],[1,0,1,1,0],[0,1,0,1,0]] 输出：3 解释：如上图所示，左边为 grid1 ，右边为
     * grid2 。 grid2 中标红的 1 区域是子岛屿，总共有 3 个子岛屿。 示例 2：
     *
     * <p>输入：grid1 = [[1,0,1,0,1],[1,1,1,1,1],[0,0,0,0,0],[1,1,1,1,1],[1,0,1,0,1]], grid2 =
     * [[0,0,0,0,0],[1,1,1,1,1],[0,1,0,1,0],[0,1,0,1,0],[1,0,0,0,1]] 输出：2 解释：如上图所示，左边为 grid1 ，右边为
     * grid2 。 grid2 中标红的 1 区域是子岛屿，总共有 2 个子岛屿。
     *
     * <p>提示：
     *
     * <p>m == grid1.length == grid2.length n == grid1[i].length == grid2[i].length 1 <= m, n <= 500
     * grid1[i][j] 和 grid2[i][j] 都要么是 0 要么是 1 。
     *
     * @param grid1
     * @param grid2
     * @return
     */
    public int countSubIslands(int[][] grid1, int[][] grid2) {

        // 深度优先遍历 把 grid1 中是0 grid2中是1 的元素全部去除
        this.m = grid1.length;
        this.n = grid1[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid1[i][j] == 0 && grid2[i][j] == 1) {
                    dfsChange(grid2, i, j);
                }
            }
        }
        logResult(grid2);
        // 遍历 grid2 计算岛屿数量
        int result = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid2[i][j] == 1) {
                    result++;
                    updateAround(grid2, i, j);
                }
            }
        }
        return result;
    }

    private void dfsChange(int[][] grid, int row, int col) {
        grid[row][col] = 0;
        for (int i = 0; i < 4; i++) {
            int nextRow = row + DIR_ROW[i], nextCol = col + DIR_COL[i];
            if (inArea(nextRow, nextCol, m, n) && grid[nextRow][nextCol] == 1) {
                dfsChange(grid, nextRow, nextCol);
            }
        }
    }

    private void updateAround(int[][] grid, int row, int col) {

        // 更新坐标
        grid[row][col] = 2;
        // 更新相邻坐标
        for (int i = 0; i < 4; i++) {
            int nextRow = row + DIR_ROW[i], nextCol = col + DIR_COL[i];
            if (inArea(nextRow, nextCol, m, n) && grid[nextRow][nextCol] == 1) {
                updateAround(grid, nextRow, nextCol);
            }
        }
    }

    /**
     * 1889. 装包裹的最小浪费空间
     *
     * <p>给你 n 个包裹，你需要把它们装在箱子里，每个箱子装一个包裹。总共有 m 个供应商提供 不同尺寸 的箱子（每个规格都有无数个箱子）。如果一个包裹的尺寸 小于等于
     * 一个箱子的尺寸，那么这个包裹就可以放入这个箱子之中。
     *
     * <p>包裹的尺寸用一个整数数组 packages 表示，其中 packages[i] 是第 i 个包裹的尺寸。供应商用二维数组 boxes 表示，其中 boxes[j] 是第 j
     * 个供应商提供的所有箱子尺寸的数组。
     *
     * <p>你想要选择 一个供应商 并只使用该供应商提供的箱子，使得 总浪费空间最小 。对于每个装了包裹的箱子，我们定义 浪费的 空间等于 箱子的尺寸 - 包裹的尺寸 。总浪费空间 为 所有
     * 箱子中浪费空间的总和。
     *
     * <p>比方说，如果你想要用尺寸数组为 [4,8] 的箱子装下尺寸为 [2,3,5] 的包裹，你可以将尺寸为 2 和 3 的两个包裹装入两个尺寸为 4 的箱子中，同时把尺寸为 5
     * 的包裹装入尺寸为 8 的箱子中。总浪费空间为 (4-2) + (4-3) + (8-5) = 6 。 请你选择 最优 箱子供应商，使得 总浪费空间最小 。如果 无法
     * 将所有包裹放入箱子中，请你返回 -1 。由于答案可能会 很大 ，请返回它对 109 + 7 取余 的结果。
     *
     * <p>示例 1：
     *
     * <p>输入：packages = [2,3,5], boxes = [[4,8],[2,8]] 输出：6 解释：选择第一个供应商最优，用两个尺寸为 4 的箱子和一个尺寸为 8 的箱子。
     * 总浪费空间为 (4-2) + (4-3) + (8-5) = 6 。 示例 2：
     *
     * <p>输入：packages = [2,3,5], boxes = [[1,4],[2,3],[3,4]] 输出：-1 解释：没有箱子能装下尺寸为 5 的包裹。 示例 3：
     *
     * <p>输入：packages = [3,5,8,10,11,12], boxes = [[12],[11,9],[10,5,14]] 输出：9 解释：选择第三个供应商最优，用两个尺寸为
     * 5 的箱子，两个尺寸为 10 的箱子和两个尺寸为 14 的箱子。 总浪费空间为 (5-3) + (5-5) + (10-8) + (10-10) + (14-11) + (14-12)
     * = 9 。
     *
     * <p>提示：
     *
     * <p>n == packages.length m == boxes.length 1 <= n <= 105 1 <= m <= 105 1 <= packages[i] <= 105
     * 1 <= boxes[j].length <= 105 1 <= boxes[j][k] <= 105 sum(boxes[j].length) <= 105 boxes[j] 中的元素
     * 互不相同 。
     *
     * @param packages
     * @param boxes
     * @return
     */
    public int minWastedSpace(int[] packages, int[][] boxes) {
        Arrays.sort(packages);
        int n = packages.length;
        int[] pkgSums = new int[n + 1];
        for (int i = 0; i < n; i++) {
            pkgSums[i + 1] = pkgSums[i] + packages[i];
        }
        long result = Long.MAX_VALUE;
        for (int[] box : boxes) {
            Arrays.sort(box);
            int m = box.length;
            // 最大的放不下
            if (packages[n - 1] > box[m - 1]) {
                continue;
            }
            long space = 0L;
            int left = 0;
            for (int num : box) {
                // 查询
                int idx = getIndex(packages, left, n, num);
                space += (long) num * (idx - left) - (pkgSums[idx] - pkgSums[left]);
                left = idx;
            }
            result = Math.min(result, space);
        }

        return result == Long.MAX_VALUE ? -1 : (int) (result % MOD);
    }

    private int getIndex(int[] nums, int start, int end, int target) {
        // 二分查找
        int left = start, right = end;
        while (left < right) {
            int mid = (left + right) >> 1;
            if (target >= nums[mid]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }

    /**
     * LCS 03. 主题空间
     *
     * <p>「以扣会友」线下活动所在场地由若干主题空间与走廊组成，场地的地图记作由一维字符串型数组 grid，字符串中仅包含 "0"～"5" 这 6 个字符。地图上每一个字符代表面积为 1
     * 的区域，其中 "0" 表示走廊，其他字符表示主题空间。相同且连续（连续指上、下、左、右四个方向连接）的字符组成同一个主题空间。
     *
     * <p>假如整个 grid 区域的外侧均为走廊。请问，不与走廊直接相邻的主题空间的最大面积是多少？如果不存在这样的空间请返回 0。
     *
     * <p>示例 1:
     *
     * <p>输入：grid = ["110","231","221"]
     *
     * <p>输出：1
     *
     * <p>解释：4 个主题空间中，只有 1 个不与走廊相邻，面积为 1。 image.png
     *
     * <p>示例 2:
     *
     * <p>输入：grid = ["11111100000","21243101111","21224101221","11111101111"]
     *
     * <p>输出：3
     *
     * <p>解释：8 个主题空间中，有 5 个不与走廊相邻，面积分别为 3、1、1、1、2，最大面积为 3。 image.png
     *
     * <p>提示：
     *
     * <p>1 <= grid.length <= 500 1 <= grid[i].length <= 500 grid[i][j] 仅可能是 "0"～"5"
     *
     * @param grid
     * @return
     */
    public int largestArea(String[] grid) {
        this.m = grid.length;
        this.n = grid[0].length();
        int[][] grids = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int num = grid[i].charAt(j) - '0';
                grids[i][j] = num;
            }
        }
        // dfs 把所有的走廊 的 改为 -1
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int num = grids[i][j];
                if (num == 0) {
                    grids[i][j] = -1;
                    dfsCorridor(grids, i, j, 0);
                } else if (i == 0 || j == 0 || i == m - 1 || j == n - 1) {
                    grids[i][j] = -1;
                    dfsCorridor(grids, i, j, num);
                }
            }
        }
        logResult(grids);
        int maxResult = 0;
        // 遍历剩下的区域
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int num = grids[i][j];
                if (num > 0 && num <= 5) {
                    grids[i][j] += 10;
                    maxResult = Math.max(maxResult, getArea(grids, i, j, num));
                }
            }
        }
        return maxResult;
    }

    private int getArea(int[][] grids, int row, int col, int num) {
        int result = 1;
        for (int i = 0; i < 4; i++) {
            int nextRow = row + DIR_ROW[i], nextCol = col + DIR_COL[i];
            if (!inArea(nextRow, nextCol, m, n)) {
                continue;
            }
            if (grids[nextRow][nextCol] != num) {
                continue;
            }
            grids[nextRow][nextCol] += 10;
            result += getArea(grids, nextRow, nextCol, num);
        }
        return result;
    }

    private void dfsCorridor(int[][] grids, int row, int col, int num) {
        for (int i = 0; i < 4; i++) {
            int nextRow = row + DIR_ROW[i], nextCol = col + DIR_COL[i];
            if (!inArea(nextRow, nextCol, m, n)) {
                continue;
            }
            // 如果 num == 0 遍历
            int nextNum = grids[nextRow][nextCol];
            if (nextNum > 0) {
                if (num == 0) {
                    grids[nextRow][nextCol] = -1;
                    dfsCorridor(grids, nextRow, nextCol, nextNum);
                } else if (num == nextNum) {
                    grids[nextRow][nextCol] = -1;
                    dfsCorridor(grids, nextRow, nextCol, num);
                }
            }
        }
    }

    /**
     * 1906. 查询差绝对值的最小值
     *
     * <p>一个数组 a 的 差绝对值的最小值 定义为：0 <= i < j < a.length 且 a[i] != a[j] 的 |a[i] - a[j]| 的 最小值。如果 a
     * 中所有元素都 相同 ，那么差绝对值的最小值为 -1 。
     *
     * <p>比方说，数组 [5,2,3,7,2] 差绝对值的最小值是 |2 - 3| = 1 。注意答案不为 0 ，因为 a[i] 和 a[j] 必须不相等。 给你一个整数数组 nums
     * 和查询数组 queries ，其中 queries[i] = [li, ri] 。对于每个查询 i ，计算 子数组 nums[li...ri] 中 差绝对值的最小值 ，子数组
     * nums[li...ri] 包含 nums 数组（下标从 0 开始）中下标在 li 和 ri 之间的所有元素（包含 li 和 ri 在内）。
     *
     * <p>请你返回 ans 数组，其中 ans[i] 是第 i 个查询的答案。
     *
     * <p>子数组 是一个数组中连续的一段元素。
     *
     * <p>|x| 的值定义为：
     *
     * <p>如果 x >= 0 ，那么值为 x 。 如果 x < 0 ，那么值为 -x 。
     *
     * <p>示例 1：
     *
     * <p>输入：nums = [1,3,4,8], queries = [[0,1],[1,2],[2,3],[0,3]] 输出：[2,1,4,1] 解释：查询结果如下： -
     * queries[0] = [0,1]：子数组是 [1,3] ，差绝对值的最小值为 |1-3| = 2 。 - queries[1] = [1,2]：子数组是 [3,4]
     * ，差绝对值的最小值为 |3-4| = 1 。 - queries[2] = [2,3]：子数组是 [4,8] ，差绝对值的最小值为 |4-8| = 4 。 - queries[3] =
     * [0,3]：子数组是 [1,3,4,8] ，差的绝对值的最小值为 |3-4| = 1 。 示例 2：
     *
     * <p>输入：nums = [4,5,2,2,7,10], queries = [[2,3],[0,2],[0,5],[3,5]] 输出：[-1,1,1,3] 解释：查询结果如下： -
     * queries[0] = [2,3]：子数组是 [2,2] ，差绝对值的最小值为 -1 ，因为所有元素相等。 - queries[1] = [0,2]：子数组是 [4,5,2]
     * ，差绝对值的最小值为 |4-5| = 1 。 - queries[2] = [0,5]：子数组是 [4,5,2,2,7,10] ，差绝对值的最小值为 |4-5| = 1 。 -
     * queries[3] = [3,5]：子数组是 [2,7,10] ，差绝对值的最小值为 |7-10| = 3 。
     *
     * <p>提示：
     *
     * <p>2 <= nums.length <= 105 1 <= nums[i] <= 100 1 <= queries.length <= 2 * 104 0 <= li < ri <
     * nums.length
     *
     * @param nums
     * @param queries
     * @return
     */
    public int[] minDifference(int[] nums, int[][] queries) {
        //  1 <= nums[i] <= 100
        int len = nums.length;
        // preNums[i][j] 表示 前 i 个 元素组成的数组 中 数字j 的个数
        int[][] preNums = new int[len + 1][MAX_NUM + 1];
        for (int i = 0; i < len; i++) {
            int num = nums[i];
            System.arraycopy(preNums[i], 0, preNums[i + 1], 0, MAX_NUM + 1);
            preNums[i + 1][num]++;
        }
        int queryLen = queries.length;
        int[] result = new int[queryLen];

        for (int i = 0; i < queryLen; i++) {
            int left = queries[i][0], right = queries[i][1];
            int last = 0, min = Integer.MAX_VALUE;
            for (int num = 1; num <= MAX_NUM; num++) {
                if (preNums[right + 1][num] > preNums[left][num]) {
                    if (last > 0) {
                        min = Math.min(min, num - last);
                    }
                    last = num;
                }
            }
            result[i] = min == Integer.MAX_VALUE ? -1 : min;
        }

        return result;
    }

    static final int MAX_NUM = 100;

    /**
     * 1901. Find a Peak Element II
     *
     * <p>A peak element in a 2D grid is an element that is strictly greater than all of its
     * adjacent neighbors to the left, right, top, and bottom.
     *
     * <p>Given a 0-indexed m x n matrix mat where no two adjacent cells are equal, find any peak
     * element mat[i][j] and return the length 2 array [i,j].
     *
     * <p>You may assume that the entire matrix is surrounded by an outer perimeter with the value
     * -1 in each cell.
     *
     * <p>You must write an algorithm that runs in O(m log(n)) or O(n log(m)) time.
     *
     * <p>Example 1:
     *
     * <p>Input: mat = [[1,4],[3,2]] Output: [0,1] Explanation: Both 3 and 4 are peak elements so
     * [1,0] and [0,1] are both acceptable answers. Example 2:
     *
     * <p>Input: mat = [[10,20,15],[21,30,14],[7,16,32]] Output: [1,1] Explanation: Both 30 and 32
     * are peak elements so [1,1] and [2,2] are both acceptable answers.
     *
     * <p>Constraints:
     *
     * <p>m == mat.length n == mat[i].length 1 <= m, n <= 500 1 <= mat[i][j] <= 105 No two adjacent
     * cells are equal.
     *
     * @param mat
     * @return
     */
    public int[] findPeakGrid(int[][] mat) {
        // 二维网格中的峰值元素是严格大于其左、右、上、下相邻元素的元素。
        // 给定一个索引为 0 的m x n矩阵mat，其中没有两个相邻单元相等，求任意峰值元素mat[i][j]并返回长度为2的数组[i，j]。
        // 您可以假设整个矩阵被每个单元格中值为-1的外周长包围。
        // 必须编写在O（m log（n））或O（n log（m））时间内运行的算法。
        this.m = mat.length;
        this.n = mat[0].length;
        this.maxCols = new int[n];
        this.grid = mat;
        // int up = 0, down = m - 1;
        int left = 0, right = n - 1;
        int[] result = new int[2];
        while (left <= right) {
            int mid = (left + right) >> 1;
            int midLeftMax = getColMax(mid - 1), midRightMax = getColMax(mid + 1);
            int midMax = getColMax(mid);
            if (midMax >= Math.max(midLeftMax, midRightMax)) {

                for (int i = 0; i < m; i++) {
                    if (mat[i][mid] == midMax) {
                        result[0] = i;
                        break;
                    }
                }

                result[1] = mid;
                return result;
            } else if (midLeftMax >= Math.max(midMax, midRightMax)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return result;
    }

    private int[] maxCols;

    private int getColMax(int col) {
        if (col < 0 || col >= n) {
            return -1;
        }
        if (maxCols[col] > 0) {
            return maxCols[col];
        }
        int max = 0;
        for (int i = 0; i < m; i++) {
            max = Math.max(max, grid[i][col]);
        }
        maxCols[col] = max;
        return max;
    }

    /**
     * 5780. 删除一个元素使数组严格递增
     *
     * <p>给你一个下标从 0 开始的整数数组 nums ，如果 恰好 删除 一个 元素后，数组 严格递增 ，那么请你返回 true ，否则返回 false
     * 。如果数组本身已经是严格递增的，请你也返回 true 。
     *
     * <p>数组 nums 是 严格递增 的定义为：对于任意下标的 1 <= i < nums.length 都满足 nums[i - 1] < nums[i] 。
     *
     * <p>示例 1：
     *
     * <p>输入：nums = [1,2,10,5,7] 输出：true 解释：从 nums 中删除下标 2 处的 10 ，得到 [1,2,5,7] 。 [1,2,5,7]
     * 是严格递增的，所以返回 true 。 示例 2：
     *
     * <p>输入：nums = [2,3,1,2] 输出：false 解释： [3,1,2] 是删除下标 0 处元素后得到的结果。 [2,1,2] 是删除下标 1 处元素后得到的结果。
     * [2,3,2] 是删除下标 2 处元素后得到的结果。 [2,3,1] 是删除下标 3 处元素后得到的结果。 没有任何结果数组是严格递增的，所以返回 false 。 示例 3：
     *
     * <p>输入：nums = [1,1,1] 输出：false 解释：删除任意元素后的结果都是 [1,1] 。 [1,1] 不是严格递增的，所以返回 false 。 示例 4：
     *
     * <p>输入：nums = [1,2,3] 输出：true 解释：[1,2,3] 已经是严格递增的，所以返回 true 。
     *
     * <p>提示：
     *
     * <p>2 <= nums.length <= 1000 1 <= nums[i] <= 1000
     *
     * @param nums
     * @return
     */
    public boolean canBeIncreasing(int[] nums) {
        int len = nums.length;
        if (len <= 2) {
            return true;
        }
        // 寻找连续递增子序列

        // 从左向右找最长递增子序列
        int[] lens = new int[len];
        Arrays.fill(lens, 1);
        int maxLen = 0;
        for (int i = 0; i < len; i++) {
            int num = nums[i];
            for (int j = i - 1; j >= Math.max(0, i - 2); j--) {
                if (num <= nums[j]) {
                    continue;
                }
                lens[i] = Math.max(lens[j] + 1, lens[i]);
            }
            maxLen = Math.max(maxLen, lens[i]);
        }
        log.debug("lens:{}", lens);
        return maxLen >= nums.length - 1;
    }

    /**
     * 5782. 最大子序列交替和
     *
     * <p>一个下标从 0 开始的数组的 交替和 定义为 偶数 下标处元素之 和 减去 奇数 下标处元素之 和 。
     *
     * <p>比方说，数组 [4,2,5,3] 的交替和为 (4 + 5) - (2 + 3) = 4 。 给你一个数组 nums ，请你返回 nums 中任意子序列的 最大交替和
     * （子序列的下标 重新 从 0 开始编号）。
     *
     * <p>一个数组的 子序列 是从原数组中删除一些元素后（也可能一个也不删除）剩余元素不改变顺序组成的数组。比方说，[2,7,4] 是 [4,2,3,7,2,1,4]
     * 的一个子序列（加粗元素），但是 [2,4,2] 不是。
     *
     * <p>示例 1：
     *
     * <p>输入：nums = [4,2,5,3] 输出：7 解释：最优子序列为 [4,2,5] ，交替和为 (4 + 5) - 2 = 7 。 示例 2：
     *
     * <p>输入：nums = [5,6,7,8] 输出：8 解释：最优子序列为 [8] ，交替和为 8 。 示例 3：
     *
     * <p>输入：nums = [6,2,1,2,4,5] 输出：10 解释：最优子序列为 [6,1,5] ，交替和为 (6 + 5) - 1 = 10 。
     *
     * <p>提示：
     *
     * <p>1 <= nums.length <= 105 1 <= nums[i] <= 105
     *
     * @param nums
     * @return
     */
    public long maxAlternatingSum(int[] nums) {
        int len = nums.length;
        long max = 0L;
        // 奇数结尾 odd 和 偶数 结尾 even
        long odd = 0L, even = nums[0];

        for (int i = 1; i < len; i++) {
            int num = nums[i];
            // 奇数结尾
            long tmpOdd = Math.max(odd, even - num);
            // 偶数结尾
            long tmpEven = Math.max(even, odd + num);
            odd = tmpOdd;
            even = tmpEven;
        }
        log.debug("odd:{} even:{}", odd, even);

        return Math.max(odd, even);
    }

    /**
     * 5797. 两个数对之间的最大乘积差
     *
     * <p>两个数对 (a, b) 和 (c, d) 之间的 乘积差 定义为 (a * b) - (c * d) 。
     *
     * <p>例如，(5, 6) 和 (2, 7) 之间的乘积差是 (5 * 6) - (2 * 7) = 16 。 给你一个整数数组 nums ，选出四个 不同的 下标 w、x、y 和 z
     * ，使数对 (nums[w], nums[x]) 和 (nums[y], nums[z]) 之间的 乘积差 取到 最大值 。
     *
     * <p>返回以这种方式取得的乘积差中的 最大值 。
     *
     * <p>示例 1：
     *
     * <p>输入：nums = [5,6,2,7,4] 输出：34 解释：可以选出下标为 1 和 3 的元素构成第一个数对 (6, 7) 以及下标 2 和 4 构成第二个数对 (2, 4)
     * 乘积差是 (6 * 7) - (2 * 4) = 34 示例 2：
     *
     * <p>输入：nums = [4,2,5,9,7,4,8] 输出：64 解释：可以选出下标为 3 和 6 的元素构成第一个数对 (9, 8) 以及下标 1 和 5 构成第二个数对 (2,
     * 4) 乘积差是 (9 * 8) - (2 * 4) = 64
     *
     * <p>提示：
     *
     * <p>4 <= nums.length <= 104 1 <= nums[i] <= 104
     *
     * @param nums
     * @return
     */
    public int maxProductDifference(int[] nums) {
        int min1, min2, max1, max2;

        Arrays.sort(nums);
        int len = nums.length;

        int result = nums[len - 1] * nums[len - 2] - nums[0] * nums[1];

        return result;
    }

    /**
     * 5798. 循环轮转矩阵
     *
     * <p>给你一个大小为 m x n 的整数矩阵 grid，其中 m 和 n 都是 偶数 ；另给你一个整数 k 。
     *
     * <p>矩阵由若干层组成，如下图所示，每种颜色代表一层：
     *
     * <p>矩阵的循环轮转是通过分别循环轮转矩阵中的每一层完成的。在对某一层进行一次循环旋转操作时，层中的每一个元素将会取代其 逆时针 方向的相邻元素。轮转示例如下：
     *
     * <p>返回执行 k 次循环轮转操作后的矩阵。
     *
     * <p>示例 1：
     *
     * <p>输入：grid = [[40,10],[30,20]], k = 1 输出：[[10,20],[40,30]] 解释：上图展示了矩阵在执行循环轮转操作时每一步的状态。 示例 2：
     *
     * <p>输入：grid = [[1,2,3,4],[5,6,7,8],[9,10,11,12],[13,14,15,16]], k = 2
     * 输出：[[3,4,8,12],[2,11,10,16],[1,7,6,15],[5,9,13,14]] 解释：上图展示了矩阵在执行循环轮转操作时每一步的状态。
     *
     * <p>提示：
     *
     * <p>m == grid.length n == grid[i].length 2 <= m, n <= 50 m 和 n 都是 偶数 1 <= grid[i][j] <= 5000 1
     * <= k <= 109
     *
     * @param grid
     * @param k
     * @return
     */
    public int[][] rotateGrid(int[][] grid, int k) {
        int m = grid.length, n = grid[0].length;

        int maxCount = Math.min(m, n) >> 1;
        int[][] result = new int[m][n];
        for (int i = 0; i < maxCount; i++) {
            // 计算 每层的元素个数
            int num = (m + n) * 2 - 4 - 8 * i;
            int cycle = k % num;
            // 逆时针
            int rows = m - 2 * i, cols = n - 2 * i;
            for (int j = 0; j < num; j++) {
                int[] idx1 = getGridIndex(j, rows, cols),
                        idx2 = getGridIndex((j + cycle) % num, rows, cols);
                int row = i + idx1[0], col = i + idx1[1];
                int nextRow = i + idx2[0], nextCol = i + idx2[1];
                result[row][col] = grid[nextRow][nextCol];
            }
        }

        return result;
    }

    private int[] getGridIndex(int idx, int m, int n) {
        int[] result = new int[2];
        if (idx < n) {
            result[1] = idx;
        } else if (idx < n + m - 1) {
            result[0] = idx - n + 1;
            result[1] = n - 1;
        } else if (idx < 2 * n + m - 3) {
            result[0] = m - 1;
            result[1] = 2 * n + m - 3 - idx;
        } else {
            result[0] = 2 * n + 2 * m - 4 - idx;
        }
        return result;
    }

    /**
     * 5800. 基于排列构建数组
     *
     * <p>给你一个 从 0 开始的排列 nums（下标也从 0 开始）。请你构建一个 同样长度 的数组 ans ，其中，对于每个 i（0 <= i < nums.length），都满足
     * ans[i] = nums[nums[i]] 。返回构建好的数组 ans 。
     *
     * <p>从 0 开始的排列 nums 是一个由 0 到 nums.length - 1（0 和 nums.length - 1 也包含在内）的不同整数组成的数组。
     *
     * <p>示例 1：
     *
     * <p>输入：nums = [0,2,1,5,3,4] 输出：[0,1,2,4,5,3] 解释：数组 ans 构建如下： ans = [nums[nums[0]],
     * nums[nums[1]], nums[nums[2]], nums[nums[3]], nums[nums[4]], nums[nums[5]]] = [nums[0],
     * nums[2], nums[1], nums[5], nums[3], nums[4]] = [0,1,2,4,5,3] 示例 2：
     *
     * <p>输入：nums = [5,0,1,2,3,4] 输出：[4,5,0,1,2,3] 解释：数组 ans 构建如下： ans = [nums[nums[0]],
     * nums[nums[1]], nums[nums[2]], nums[nums[3]], nums[nums[4]], nums[nums[5]]] = [nums[5],
     * nums[0], nums[1], nums[2], nums[3], nums[4]] = [4,5,0,1,2,3]
     *
     * <p>提示：
     *
     * <p>1 <= nums.length <= 1000 0 <= nums[i] < nums.length nums 中的元素 互不相同
     *
     * @param nums
     * @return
     */
    public int[] buildArray(int[] nums) {
        int len = nums.length;
        int[] result = new int[len];

        for (int i = 0; i < len; i++) {
            result[i] = nums[nums[i]];
        }

        return result;
    }

    /**
     * 5801. 消灭怪物的最大数量
     *
     * <p>你正在玩一款电子游戏，在游戏中你需要保护城市免受怪物侵袭。给你一个 下标从 0 开始 且长度为 n 的整数数组 dist ，其中 dist[i] 是第 i 个怪物与城市的
     * 初始距离（单位：米）。
     *
     * <p>怪物以 恒定 的速度走向城市。给你一个长度为 n 的整数数组 speed 表示每个怪物的速度，其中 speed[i] 是第 i 个怪物的速度（单位：米/分）。
     *
     * <p>怪物从 第 0 分钟 时开始移动。你有一把武器，并可以 选择 在每一分钟的开始时使用，包括第 0
     * 分钟。但是你无法在一分钟的中间使用武器。这种武器威力惊人，一次可以消灭任一还活着的怪物。
     *
     * <p>一旦任一怪物到达城市，你就输掉了这场游戏。如果某个怪物 恰 在某一分钟开始时到达城市，这会被视为 输掉 游戏，在你可以使用武器之前，游戏就会结束。
     *
     * <p>返回在你输掉游戏前可以消灭的怪物的 最大 数量。如果你可以在所有怪物到达城市前将它们全部消灭，返回 n 。
     *
     * <p>示例 1：
     *
     * <p>输入：dist = [1,3,4], speed = [1,1,1] 输出：3 解释： 第 0 分钟开始时，怪物的距离是 [1,3,4]，你消灭了第一个怪物。 第 1
     * 分钟开始时，怪物的距离是 [X,2,3]，你没有消灭任何怪物。 第 2 分钟开始时，怪物的距离是 [X,1,2]，你消灭了第二个怪物。 第 3 分钟开始时，怪物的距离是
     * [X,X,1]，你消灭了第三个怪物。 所有 3 个怪物都可以被消灭。 示例 2：
     *
     * <p>输入：dist = [1,1,2,3], speed = [1,1,1,1] 输出：1 解释： 第 0 分钟开始时，怪物的距离是 [1,1,2,3]，你消灭了第一个怪物。 第 1
     * 分钟开始时，怪物的距离是 [X,0,1,2]，你输掉了游戏。 你只能消灭 1 个怪物。 示例 3：
     *
     * <p>输入：dist = [3,2,4], speed = [5,3,2] 输出：1 解释： 第 0 分钟开始时，怪物的距离是 [3,2,4]，你消灭了第一个怪物。 第 1
     * 分钟开始时，怪物的距离是 [X,0,2]，你输掉了游戏。 你只能消灭 1 个怪物。
     *
     * <p>提示：
     *
     * <p>n == dist.length == speed.length 1 <= n <= 105 1 <= dist[i], speed[i] <= 105
     *
     * @param dist
     * @param speed
     * @return
     */
    public int eliminateMaximum(int[] dist, int[] speed) {
        int n = dist.length;

        int[] mins = new int[n];
        for (int i = 0; i < n; ++i) {
            mins[i] = (dist[i] - 1) / speed[i];
        }
        int result = 0;
        Arrays.sort(mins);
        for (int i = 0; i < n; ++i) {
            if (mins[i] < i) {
                break;
            }
            result++;
        }
        return result;
    }

    /**
     * 5793. 迷宫中离入口最近的出口
     *
     * <p>给你一个 m x n 的迷宫矩阵 maze （下标从 0 开始），矩阵中有空格子（用 '.' 表示）和墙（用 '+' 表示）。同时给你迷宫的入口 entrance ，用
     * entrance = [entrancerow, entrancecol] 表示你一开始所在格子的行和列。
     *
     * <p>每一步操作，你可以往 上，下，左 或者 右 移动一个格子。你不能进入墙所在的格子，你也不能离开迷宫。你的目标是找到离 entrance 最近 的出口。出口 的含义是 maze 边界
     * 上的 空格子。entrance 格子 不算 出口。
     *
     * <p>请你返回从 entrance 到最近出口的最短路径的 步数 ，如果不存在这样的路径，请你返回 -1 。
     *
     * <p>示例 1：
     *
     * <p>输入：maze = [["+","+",".","+"],[".",".",".","+"],["+","+","+","."]], entrance = [1,2] 输出：1
     * 解释：总共有 3 个出口，分别位于 (1,0)，(0,2) 和 (2,3) 。 一开始，你在入口格子 (1,2) 处。 - 你可以往左移动 2 步到达 (1,0) 。 - 你可以往上移动
     * 1 步到达 (0,2) 。 从入口处没法到达 (2,3) 。 所以，最近的出口是 (0,2) ，距离为 1 步。 示例 2：
     *
     * <p>输入：maze = [["+","+","+"],[".",".","."],["+","+","+"]], entrance = [1,0] 输出：2 解释：迷宫中只有 1
     * 个出口，在 (1,2) 处。 (1,0) 不算出口，因为它是入口格子。 初始时，你在入口与格子 (1,0) 处。 - 你可以往右移动 2 步到达 (1,2) 处。 所以，最近的出口为
     * (1,2) ，距离为 2 步。 示例 3：
     *
     * <p>输入：maze = [[".","+"]], entrance = [0,0] 输出：-1 解释：这个迷宫中没有出口。
     *
     * <p>提示：
     *
     * <p>maze.length == m maze[i].length == n 1 <= m, n <= 100 maze[i][j] 要么是 '.' ，要么是 '+' 。
     * entrance.length == 2 0 <= entrancerow < m 0 <= entrancecol < n entrance 一定是空格子。
     *
     * @param maze
     * @param entrance
     * @return
     */
    public int nearestExit(char[][] maze, int[] entrance) {
        int m = maze.length, n = maze[0].length;

        boolean[][] visited = new boolean[m][n];
        // 广度优先遍历
        Queue<int[]> queue = new LinkedList<>();
        int step = 0;
        queue.offer(entrance);
        visited[entrance[0]][entrance[1]] = true;
        while (!queue.isEmpty()) {
            int size = queue.size();
            step++;
            for (int i = 0; i < size; i++) {
                int[] nums = queue.poll();
                int row = nums[0], col = nums[1];
                for (int j = 0; j < 4; j++) {
                    int nextRow = row + DIR_ROW[j], nextCol = col + DIR_COL[j];
                    if (!inArea(nextRow, nextCol, m, n)) {
                        continue;
                    }
                    if (visited[nextRow][nextCol]) {
                        continue;
                    }
                    if (maze[nextRow][nextCol] != '.') {
                        continue;
                    }

                    if (nextRow == 0 || nextRow == m - 1 || nextCol == 0 || nextCol == n - 1) {
                        return step;
                    }
                    visited[nextRow][nextCol] = true;
                    queue.offer(new int[] {nextRow, nextCol});
                }
            }
        }

        return -1;
    }

    /**
     * 5808. 数组串联
     *
     * <p>给你一个长度为 n 的整数数组 nums 。请你构建一个长度为 2n 的答案数组 ans ，数组下标 从 0 开始计数 ，对于所有 0 <= i < n 的 i
     * ，满足下述所有要求：
     *
     * <p>ans[i] == nums[i] ans[i + n] == nums[i] 具体而言，ans 由两个 nums 数组 串联 形成。
     *
     * <p>返回数组 ans 。
     *
     * <p>示例 1：
     *
     * <p>输入：nums = [1,2,1] 输出：[1,2,1,1,2,1] 解释：数组 ans 按下述方式形成： - ans =
     * [nums[0],nums[1],nums[2],nums[0],nums[1],nums[2]] - ans = [1,2,1,1,2,1] 示例 2：
     *
     * <p>输入：nums = [1,3,2,1] 输出：[1,3,2,1,1,3,2,1] 解释：数组 ans 按下述方式形成： - ans =
     * [nums[0],nums[1],nums[2],nums[3],nums[0],nums[1],nums[2],nums[3]] - ans = [1,3,2,1,1,3,2,1]
     *
     * <p>提示：
     *
     * <p>n == nums.length 1 <= n <= 1000 1 <= nums[i] <= 1000
     *
     * @param nums
     * @return
     */
    public int[] getConcatenation(int[] nums) {
        int n = nums.length;
        int[] result = new int[n << 1];

        for (int i = 0; i < n; i++) {
            result[i] = nums[i];
            result[i + n] = nums[i];
        }

        return result;
    }

    /**
     * 5814. 新增的最少台阶数
     *
     * <p>给你一个 严格递增 的整数数组 rungs ，用于表示梯子上每一台阶的 高度 。当前你正站在高度为 0 的地板上，并打算爬到最后一个台阶。
     *
     * <p>另给你一个整数 dist 。每次移动中，你可以到达下一个距离你当前位置（地板或台阶）不超过 dist 高度的台阶。当然，你也可以在任何正 整数 高度处插入尚不存在的新台阶。
     *
     * <p>返回爬到最后一阶时必须添加到梯子上的 最少 台阶数。
     *
     * <p>示例 1：
     *
     * <p>输入：rungs = [1,3,5,10], dist = 2 输出：2 解释： 现在无法到达最后一阶。 在高度为 7 和 8 的位置增设新的台阶，以爬上梯子。 梯子在高度为
     * [1,3,5,7,8,10] 的位置上有台阶。 示例 2：
     *
     * <p>输入：rungs = [3,6,8,10], dist = 3 输出：0 解释： 这个梯子无需增设新台阶也可以爬上去。 示例 3：
     *
     * <p>输入：rungs = [3,4,6,7], dist = 2 输出：1 解释： 现在无法从地板到达梯子的第一阶。 在高度为 1 的位置增设新的台阶，以爬上梯子。 梯子在高度为
     * [1,3,4,6,7] 的位置上有台阶。 示例 4：
     *
     * <p>输入：rungs = [5], dist = 10 输出：0 解释：这个梯子无需增设新台阶也可以爬上去。
     *
     * <p>提示：
     *
     * <p>1 <= rungs.length <= 105 1 <= rungs[i] <= 109 1 <= dist <= 109 rungs 严格递增
     *
     * @param rungs
     * @param dist
     * @return
     */
    public int addRungs(int[] rungs, int dist) {
        int len = rungs.length;
        // 贪心
        int last = 0, result = 0;

        for (int num : rungs) {
            if (num - last <= dist) {
                last = num;
                continue;
            }
            int count = (num - last - 1) / dist;

            last = num;
            result += count;
        }

        return result;
    }

    /**
     * 5805. 最小未被占据椅子的编号
     *
     * <p>有 n 个朋友在举办一个派对，这些朋友从 0 到 n - 1 编号。派对里有 无数 张椅子，编号为 0 到 infinity 。当一个朋友到达派对时，他会占据 编号最小
     * 且未被占据的椅子。
     *
     * <p>比方说，当一个朋友到达时，如果椅子 0 ，1 和 5 被占据了，那么他会占据 2 号椅子。
     * 当一个朋友离开派对时，他的椅子会立刻变成未占据状态。如果同一时刻有另一个朋友到达，可以立即占据这张椅子。
     *
     * <p>给你一个下标从 0 开始的二维整数数组 times ，其中 times[i] = [arrivali, leavingi] 表示第 i 个朋友到达和离开的时刻，同时给你一个整数
     * targetFriend 。所有到达时间 互不相同 。
     *
     * <p>请你返回编号为 targetFriend 的朋友占据的 椅子编号 。
     *
     * <p>示例 1：
     *
     * <p>输入：times = [[1,4],[2,3],[4,6]], targetFriend = 1 输出：1 解释： - 朋友 0 时刻 1 到达，占据椅子 0 。 - 朋友 1
     * 时刻 2 到达，占据椅子 1 。 - 朋友 1 时刻 3 离开，椅子 1 变成未占据。 - 朋友 0 时刻 4 离开，椅子 0 变成未占据。 - 朋友 2 时刻 4 到达，占据椅子 0
     * 。 朋友 1 占据椅子 1 ，所以返回 1 。 示例 2：
     *
     * <p>输入：times = [[3,10],[1,5],[2,6]], targetFriend = 0 输出：2 解释： - 朋友 1 时刻 1 到达，占据椅子 0 。 - 朋友 2
     * 时刻 2 到达，占据椅子 1 。 - 朋友 0 时刻 3 到达，占据椅子 2 。 - 朋友 1 时刻 5 离开，椅子 0 变成未占据。 - 朋友 2 时刻 6 离开，椅子 1
     * 变成未占据。 - 朋友 0 时刻 10 离开，椅子 2 变成未占据。 朋友 0 占据椅子 2 ，所以返回 2 。
     *
     * <p>提示：
     *
     * <p>n == times.length 2 <= n <= 104 times[i].length == 2 1 <= arrivali < leavingi <= 105 0 <=
     * targetFriend <= n - 1 每个 arrivali 时刻 互不相同 。
     *
     * @param times
     * @param targetFriend
     * @return
     */
    public int smallestChair(int[][] times, int targetFriend) {
        int n = times.length;
        List<FriendNode> friendNodeList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            friendNodeList.add(new FriendNode(i, times[i][0], times[i][1]));
        }
        friendNodeList.sort((a, b) -> a.start == b.start ? a.idx - b.idx : a.start - b.start);
        int curChair = 0;
        PriorityQueue<TimeNode> heap = new PriorityQueue<>((a, b) -> a.time - b.time);

        PriorityQueue<Integer> chairQueue = new PriorityQueue<>();
        for (FriendNode node : friendNodeList) {
            int time = node.start;
            while (!heap.isEmpty() && heap.peek().time <= time) {
                TimeNode timeNode = heap.poll();
                chairQueue.offer(timeNode.chair);
            }
            int chair;
            if (chairQueue.isEmpty()) {
                chair = curChair++;
            } else {
                chair = chairQueue.poll();
            }
            if (node.idx == targetFriend) {
                return chair;
            }
            heap.offer(new TimeNode(node.end, chair));
        }

        return -1;
    }

    static class FriendNode {
        int idx, start, end;

        public FriendNode(int idx, int start, int end) {
            this.idx = idx;
            this.start = start;
            this.end = end;
        }
    }

    static class TimeNode {
        int time, chair;

        public TimeNode(int time, int chair) {
            this.time = time;
            this.chair = chair;
        }
    }

    /**
     * 5806. 描述绘画结果
     *
     * <p>给你一个细长的画，用数轴表示。这幅画由若干有重叠的线段表示，每个线段有 独一无二 的颜色。给你二维整数数组 segments ，其中 segments[i] = [starti,
     * endi, colori] 表示线段为 半开区间 [starti, endi) 且颜色为 colori 。
     *
     * <p>线段间重叠部分的颜色会被 混合 。如果有两种或者更多颜色混合时，它们会形成一种新的颜色，用一个 集合 表示这个混合颜色。
     *
     * <p>比方说，如果颜色 2 ，4 和 6 被混合，那么结果颜色为 {2,4,6} 。 为了简化题目，你不需要输出整个集合，只需要用集合中所有元素的 和 来表示颜色集合。
     *
     * <p>你想要用 最少数目 不重叠 半开区间 来 表示 这幅混合颜色的画。这些线段可以用二维数组 painting 表示，其中 painting[j] = [leftj, rightj,
     * mixj] 表示一个 半开区间[leftj, rightj) 的颜色 和 为 mixj 。
     *
     * <p>比方说，这幅画由 segments = [[1,4,5],[1,7,7]] 组成，那么它可以表示为 painting = [[1,4,12],[4,7,7]] ，因为： [1,4)
     * 由颜色 {5,7} 组成（和为 12），分别来自第一个线段和第二个线段。 [4,7) 由颜色 {7} 组成，来自第二个线段。 请你返回二维数组 painting
     * ，它表示最终绘画的结果（没有 被涂色的部分不出现在结果中）。你可以按 任意顺序 返回最终数组的结果。
     *
     * <p>半开区间 [a, b) 是数轴上点 a 和点 b 之间的部分，包含 点 a 且 不包含 点 b 。
     *
     * <p>示例 1：
     *
     * <p>输入：segments = [[1,4,5],[4,7,7],[1,7,9]] 输出：[[1,4,14],[4,7,16]] 解释：绘画借故偶可以表示为： - [1,4) 颜色为
     * {5,9} （和为 14），分别来自第一和第二个线段。 - [4,7) 颜色为 {7,9} （和为 16），分别来自第二和第三个线段。 示例 2：
     *
     * <p>输入：segments = [[1,7,9],[6,8,15],[8,10,7]] 输出：[[1,6,9],[6,7,24],[7,8,15],[8,10,7]]
     * 解释：绘画结果可以以表示为： - [1,6) 颜色为 9 ，来自第一个线段。 - [6,7) 颜色为 {9,15} （和为 24），来自第一和第二个线段。 - [7,8) 颜色为 15
     * ，来自第二个线段。 - [8,10) 颜色为 7 ，来自第三个线段。 示例 3：
     *
     * <p>输入：segments = [[1,4,5],[1,4,7],[4,7,1],[4,7,11]] 输出：[[1,4,12],[4,7,12]] 解释：绘画结果可以表示为： -
     * [1,4) 颜色为 {5,7} （和为 12），分别来自第一和第二个线段。 - [4,7) 颜色为 {1,11} （和为 12），分别来自第三和第四个线段。 注意，只返回一个单独的线段
     * [1,7) 是不正确的，因为混合颜色的集合不相同。
     *
     * <p>提示：
     *
     * <p>1 <= segments.length <= 2 * 104 segments[i].length == 3 1 <= starti < endi <= 105 1 <=
     * colori <= 109 每种颜色 colori 互不相同。
     *
     * @param segments
     * @return
     */
    public List<List<Long>> splitPainting(int[][] segments) {
        List<List<Long>> result = new ArrayList<>();

        Arrays.sort(segments, (a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
        int len = segments.length;
        long last = 0, color = 0;

        PriorityQueue<SegmentNode> queue = new PriorityQueue<>((a, b) -> a.num - b.num);
        for (int[] segment : segments) {

            while (!queue.isEmpty() && queue.peek().num <= segment[0]) {
                SegmentNode node = queue.poll();
                if (node.num > last) {
                    List<Long> list = new ArrayList<>();
                    list.add(last);
                    list.add((long) node.num);
                    list.add(color);
                    result.add(list);
                }
                last = node.num;
                color -= node.color;
            }
            if (queue.isEmpty()) {
                last = 0;
            }

            if (last == 0) {
                last = segment[0];
            } else if (segment[0] > last) {
                List<Long> list = new ArrayList<>();
                list.add(last);
                list.add((long) segment[0]);
                list.add(color);
                result.add(list);
                last = segment[0];
            }

            queue.offer(new SegmentNode(segment[1], segment[2]));
            color += segment[2];
        }

        while (!queue.isEmpty()) {
            SegmentNode node = queue.poll();
            if (node.num > last) {
                List<Long> list = new ArrayList<>();
                list.add(last);
                list.add((long) node.num);
                list.add(color);
                result.add(list);
                last = node.num;
            }
            color -= node.color;
        }

        return result;
    }

    static class SegmentNode {
        int num, color;

        public SegmentNode(int num, int color) {
            this.num = num;
            this.color = color;
        }
    }

    /**
     * 5825. 最大兼容性评分和
     *
     * <p>有一份由 n 个问题组成的调查问卷，每个问题的答案要么是 0（no，否），要么是 1（yes，是）。
     *
     * <p>这份调查问卷被分发给 m 名学生和 m 名导师，学生和导师的编号都是从 0 到 m - 1 。学生的答案用一个二维整数数组 students 表示，其中 students[i]
     * 是一个整数数组，包含第 i 名学生对调查问卷给出的答案（下标从 0 开始）。导师的答案用一个二维整数数组 mentors 表示，其中 mentors[j] 是一个整数数组，包含第 j
     * 名导师对调查问卷给出的答案（下标从 0 开始）。
     *
     * <p>每个学生都会被分配给 一名 导师，而每位导师也会分配到 一名 学生。配对的学生与导师之间的兼容性评分等于学生和导师答案相同的次数。
     *
     * <p>例如，学生答案为[1, 0, 1] 而导师答案为 [0, 0, 1] ，那么他们的兼容性评分为 2 ，因为只有第二个和第三个答案相同。 请你找出最优的学生与导师的配对方案，以
     * 最大程度上 提高 兼容性评分和 。
     *
     * <p>给你 students 和 mentors ，返回可以得到的 最大兼容性评分和 。
     *
     * <p>示例 1：
     *
     * <p>输入：students = [[1,1,0],[1,0,1],[0,0,1]], mentors = [[1,0,0],[0,0,1],[1,1,0]] 输出：8
     * 解释：按下述方式分配学生和导师： - 学生 0 分配给导师 2 ，兼容性评分为 3 。 - 学生 1 分配给导师 0 ，兼容性评分为 2 。 - 学生 2 分配给导师 1 ，兼容性评分为
     * 3 。 最大兼容性评分和为 3 + 2 + 3 = 8 。 示例 2：
     *
     * <p>输入：students = [[0,0],[0,0],[0,0]], mentors = [[1,1],[1,1],[1,1]] 输出：0 解释：任意学生与导师配对的兼容性评分都是
     * 0 。
     *
     * <p>提示：
     *
     * <p>m == students.length == mentors.length n == students[i].length == mentors[j].length 1 <=
     * m, n <= 8 students[i][k] 为 0 或 1 mentors[j][k] 为 0 或 1
     *
     * @param students
     * @param mentors
     * @return
     */
    public int maxCompatibilitySum(int[][] students, int[][] mentors) {
        int m = students.length, n = students[0].length;

        // state 1 ~ 2^m - 1 表示 m 名教师 状态 state 能活动的最大匹配度
        int maxState = 1 << m;
        int[] dp = new int[maxState];
        for (int i = 0; i < m; i++) {
            // 学生i
            for (int j = 0; j < m; j++) {
                // 教师j
                int score = 0;
                for (int k = 0; k < n; k++) {
                    score += students[i][k] == mentors[j][k] ? 1 : 0;
                }

                for (int state = 1; state < maxState; state++) {

                    if ((state & (1 << j)) > 0 && Integer.bitCount(state) == (1 + i)) {
                        dp[state] = Math.max(dp[state], dp[state ^ (1 << j)] + score);
                    }
                }
            }
            log.debug("dp:{}", dp);
        }

        return dp[maxState - 1];
    }

    /**
     * 5831. 你可以工作的最大周数
     *
     * <p>给你 n 个项目，编号从 0 到 n - 1 。同时给你一个整数数组 milestones ，其中每个 milestones[i] 表示第 i 个项目中的阶段任务数量。
     *
     * <p>你可以按下面两个规则参与项目中的工作：
     *
     * <p>每周，你将会完成 某一个 项目中的 恰好一个 阶段任务。你每周都 必须 工作。 在 连续的 两周中，你 不能 参与并完成同一个项目中的两个阶段任务。
     * 一旦所有项目中的全部阶段任务都完成，或者仅剩余一个阶段任务都会导致你违反上面的规则，那么你将 停止工作 。注意，由于这些条件的限制，你可能无法完成所有阶段任务。
     *
     * <p>返回在不违反上面规则的情况下你 最多 能工作多少周。
     *
     * <p>示例 1：
     *
     * <p>输入：milestones = [1,2,3] 输出：6 解释：一种可能的情形是：
     *
     * <p>- 第 1 周，你参与并完成项目 0 中的一个阶段任务。
     *
     * <p>- 第 2 周，你参与并完成项目 2 中的一个阶段任务。
     *
     * <p>- 第 3 周，你参与并完成项目 1 中的一个阶段任务。
     *
     * <p>- 第 4 周，你参与并完成项目 2 中的一个阶段任务。
     *
     * <p>- 第 5 周，你参与并完成项目 1 中的一个阶段任务。
     *
     * <p>- 第 6 周，你参与并完成项目 2 中的一个阶段任务。
     *
     * <p>总周数是 6 。 示例 2：
     *
     * <p>输入：milestones = [5,2,1] 输出：7 解释：一种可能的情形是：
     *
     * <p>- 第 1 周，你参与并完成项目 0 中的一个阶段任务。
     *
     * <p>- 第 2 周，你参与并完成项目 1 中的一个阶段任务。
     *
     * <p>- 第 3 周，你参与并完成项目 0 中的一个阶段任务。
     *
     * <p>- 第 4 周，你参与并完成项目 1 中的一个阶段任务。
     *
     * <p>- 第 5 周，你参与并完成项目 0 中的一个阶段任务。
     *
     * <p>- 第 6 周，你参与并完成项目 2 中的一个阶段任务。
     *
     * <p>- 第 7 周，你参与并完成项目 0 中的一个阶段任务。
     *
     * <p>总周数是 7 。 注意，你不能在第 8 周参与完成项目 0 中的最后一个阶段任务，因为这会违反规则。 因此，项目 0 中会有一个阶段任务维持未完成状态。
     *
     * <p>提示：
     *
     * <p>n == milestones.length 1 <= n <= 105 1 <= milestones[i] <= 109
     *
     * @param milestones
     * @return
     */
    public long numberOfWeeks(int[] milestones) {
        long sum = 0L, max = 0L;
        for (int num : milestones) {
            sum += num;
            max = Math.max(max, num);
        }
        if (sum < 2 * max) {
            return (sum - max) * 2 + 1;
        }
        return sum;
    }
}
