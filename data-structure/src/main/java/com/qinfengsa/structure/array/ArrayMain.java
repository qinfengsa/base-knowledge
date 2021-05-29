package com.qinfengsa.structure.array;

import static com.qinfengsa.structure.util.LogUtils.logResult;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
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
}
