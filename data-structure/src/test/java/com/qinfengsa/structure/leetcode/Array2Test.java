package com.qinfengsa.structure.leetcode;

import static com.qinfengsa.structure.util.LogUtils.logResult;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.TreeMap;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * @author qinfengsa
 * @date 2021/01/10 11:01
 */
@Slf4j
public class Array2Test {

    /**
     * 5649. 解码异或后的数组
     *
     * <p>未知 整数数组 arr 由 n 个非负整数组成。
     *
     * <p>经编码后变为长度为 n - 1 的另一个整数数组 encoded ，其中 encoded[i] = arr[i] XOR arr[i + 1] 。例如，arr =
     * [1,0,2,1] 经编码后得到 encoded = [1,2,3] 。
     *
     * <p>给你编码后的数组 encoded 和原数组 arr 的第一个元素 first（arr[0]）。
     *
     * <p>请解码返回原数组 arr 。可以证明答案存在并且是唯一的。
     *
     * <p>示例 1：
     *
     * <p>输入：encoded = [1,2,3], first = 1 输出：[1,0,2,1] 解释：若 arr = [1,0,2,1] ，那么 first = 1 且 encoded
     * = [1 XOR 0, 0 XOR 2, 2 XOR 1] = [1,2,3] 示例 2：
     *
     * <p>输入：encoded = [6,2,7,3], first = 4 输出：[4,2,0,7,4]
     *
     * <p>提示：
     *
     * <p>2 <= n <= 104 encoded.length == n - 1 0 <= encoded[i] <= 105 0 <= first <= 105
     *
     * @param encoded
     * @param first
     * @return
     */
    public int[] decode(int[] encoded, int first) {
        int n = encoded.length;
        int[] result = new int[n + 1];
        result[0] = first;

        for (int i = 1; i <= n; i++) {
            result[i] = encoded[i - 1] ^ result[i - 1];
        }

        return result;
    }

    /**
     * 5650. 执行交换操作后的最小汉明距离
     *
     * <p>给你两个整数数组 source 和 target ，长度都是 n 。还有一个数组 allowedSwaps ，其中每个 allowedSwaps[i] = [ai, bi]
     * 表示你可以交换数组 source 中下标为 ai 和 bi（下标从 0 开始）的两个元素。注意，你可以按 任意 顺序 多次 交换一对特定下标指向的元素。
     *
     * <p>相同长度的两个数组 source 和 target 间的 汉明距离 是元素不同的下标数量。形式上，其值等于满足 source[i] != target[i] （下标从 0
     * 开始）的下标 i（0 <= i <= n-1）的数量。
     *
     * <p>在对数组 source 执行 任意 数量的交换操作后，返回 source 和 target 间的 最小汉明距离 。
     *
     * <p>示例 1：
     *
     * <p>输入：source = [1,2,3,4], target = [2,1,4,5], allowedSwaps = [[0,1],[2,3]] 输出：1 解释：source
     * 可以按下述方式转换： - 交换下标 0 和 1 指向的元素：source = [2,1,3,4] - 交换下标 2 和 3 指向的元素：source = [2,1,4,3] source
     * 和 target 间的汉明距离是 1 ，二者有 1 处元素不同，在下标 3 。 示例 2：
     *
     * <p>输入：source = [1,2,3,4], target = [1,3,2,4], allowedSwaps = [] 输出：2 解释：不能对 source 执行交换操作。
     * source 和 target 间的汉明距离是 2 ，二者有 2 处元素不同，在下标 1 和下标 2 。 示例 3：
     *
     * <p>输入：source = [5,1,2,4,3], target = [1,5,4,2,3], allowedSwaps = [[0,4],[4,2],[1,3],[1,4]]
     * 输出：0
     *
     * <p>提示：
     *
     * <p>n == source.length == target.length 1 <= n <= 105 1 <= source[i], target[i] <= 105 0 <=
     * allowedSwaps.length <= 105 allowedSwaps[i].length == 2 0 <= ai, bi <= n - 1 ai != bi
     *
     * @param source
     * @param target
     * @param allowedSwaps
     * @return
     */
    public int minimumHammingDistance(int[] source, int[] target, int[][] allowedSwaps) {
        int result = 0, n = source.length;

        // 并查集
        swapParents = new int[n];
        for (int i = 0; i < n; i++) {
            swapParents[i] = i;
        }
        for (int[] swap : allowedSwaps) {
            union(swap[0], swap[1]);
        }
        Map<Integer, List<Integer>> targetMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            targetMap.computeIfAbsent(target[i], k -> new ArrayList<>()).add(i);
        }

        for (int i = 0; i < n; i++) {
            if (!targetMap.containsKey(source[i])) {
                result++;
                continue;
            }
            List<Integer> list = targetMap.get(source[i]);
            Iterator<Integer> iterator = list.iterator();
            boolean flag = false;
            while (iterator.hasNext()) {
                Integer index = iterator.next();
                if (findParent(i) == findParent(index)) {
                    iterator.remove();
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                result++;
            }
        }
        return result;
    }

    private int[] swapParents;

    private void union(int child, int parent) {
        child = findParent(child);
        parent = findParent(parent);
        swapParents[child] = parent;
    }

    private int findParent(int child) {
        int parent = swapParents[child];
        if (parent != child) {
            return swapParents[child] = findParent(parent);
        }
        return child;
    }

    /**
     * 803. 打砖块
     *
     * <p>有一个 m x n 的二元网格，其中 1 表示砖块，0 表示空白。砖块 稳定（不会掉落）的前提是：
     *
     * <p>一块砖直接连接到网格的顶部，或者 至少有一块相邻（4 个方向之一）砖块 稳定 不会掉落时 给你一个数组 hits ，这是需要依次消除砖块的位置。每当消除 hits[i] =
     * (rowi, coli) 位置上的砖块时，对应位置的砖块（若存在）会消失，然后其他的砖块可能因为这一消除操作而掉落。一旦砖块掉落，它会立即从网格中消失（即，它不会落在其他稳定的砖块上）。
     *
     * <p>返回一个数组 result ，其中 result[i] 表示第 i 次消除操作对应掉落的砖块数目。
     *
     * <p>注意，消除可能指向是没有砖块的空白位置，如果发生这种情况，则没有砖块掉落。
     *
     * <p>示例 1：
     *
     * <p>输入：grid = [[1,0,0,0],[1,1,1,0]], hits = [[1,0]] 输出：[2] 解释： 网格开始为： [[1,0,0,0]， [1,1,1,0]]
     * 消除 (1,0) 处加粗的砖块，得到网格： [[1,0,0,0] [0,1,1,0]]
     * 两个加粗的砖不再稳定，因为它们不再与顶部相连，也不再与另一个稳定的砖相邻，因此它们将掉落。得到网格： [[1,0,0,0], [0,0,0,0]] 因此，结果为 [2] 。 示例 2：
     *
     * <p>输入：grid = [[1,0,0,0],[1,1,0,0]], hits = [[1,1],[1,0]] 输出：[0,0] 解释： 网格开始为： [[1,0,0,0],
     * [1,1,0,0]] 消除 (1,1) 处加粗的砖块，得到网格： [[1,0,0,0], [1,0,0,0]] 剩下的砖都很稳定，所以不会掉落。网格保持不变： [[1,0,0,0],
     * [1,0,0,0]] 接下来消除 (1,0) 处加粗的砖块，得到网格： [[1,0,0,0], [0,0,0,0]] 剩下的砖块仍然是稳定的，所以不会有砖块掉落。 因此，结果为
     * [0,0] 。
     *
     * <p>提示：
     *
     * <p>m == grid.length n == grid[i].length 1 <= m, n <= 200 grid[i][j] 为 0 或 1 1 <= hits.length
     * <= 4 * 104 hits[i].length == 2 0 <= xi <= m - 1 0 <= yi <= n - 1 所有 (xi, yi) 互不相同
     *
     * @param grid
     * @param hits
     * @return
     */
    public int[] hitBricks(int[][] grid, int[][] hits) {
        int len = hits.length, m = grid.length, n = grid[0].length;
        int[] result = new int[len];
        // 逆向思考，利用并查集擅长合并的特点，从最后一次敲砖块，向前进行合并

        // 1 将 hits 中的砖块全部击碎
        for (int i = 0; i < len; i++) {
            int[] hit = hits[i];
            if (grid[hit[0]][hit[1]] == 0) {
                result[i] = -1;
            } else {
                grid[hit[0]][hit[1]] = 0;
            }
        }
        int size = m * n;
        HitBrick brick = new HitBrick(size + 1);
        for (int j = 0; j < n; j++) {
            if (grid[0][j] == 1) {
                brick.union(j, size);
            }
        }

        for (int i = 1; i < m; i++) {

            for (int j = 0; j < n; j++) {
                if (grid[i][j] != 1) {
                    continue;
                }
                // 上方
                if (grid[i - 1][j] == 1) {

                    brick.union((i - 1) * n + j, i * n + j);
                }
                // 左边
                if (j > 0 && grid[i][j - 1] == 1) {
                    brick.union(i * n + j - 1, i * n + j);
                }
            }
        }

        // 然后倒叙, 把hits中的砖块变成1
        for (int i = len - 1; i >= 0; i--) {
            if (result[i] == -1) {
                result[i] = 0;
                continue;
            }
            int row = hits[i][0], col = hits[i][1];
            int originNum = brick.sizeNum(size);
            if (row == 0) {
                brick.union(col, size);
            }
            for (int j = 0; j < 4; j++) {
                int newRow = row + DIR_ROW[j], newCol = col + DIR_COL[j];
                if (inArea(newRow, newCol, m, n) && grid[newRow][newCol] == 1) {
                    brick.union(row * n + col, newRow * n + newCol);
                }
            }
            int currentNum = brick.sizeNum(size);
            result[i] = Math.max(0, currentNum - originNum - 1);

            grid[row][col] = 1;
        }

        return result;
    }

    static int[] DIR_ROW = {1, 0, -1, 0};
    static int[] DIR_COL = {0, 1, 0, -1};

    private boolean inArea(int row, int col, int rows, int cols) {
        return row >= 0 && row < rows && col >= 0 && col < cols;
    }

    static class HitBrick {

        private int[] parents;

        private int[] sizeNums;

        HitBrick(int n) {
            parents = new int[n];
            sizeNums = new int[n];
            for (int i = 0; i < n; i++) {
                parents[i] = i;
                sizeNums[i] = 1;
            }
        }

        void union(int child, int parent) {
            child = findParent(child);
            parent = findParent(parent);
            if (child == parent) {
                return;
            }
            parents[child] = parent;
            sizeNums[parent] += sizeNums[child];
        }

        int findParent(int child) {
            int parent = parents[child];
            if (parent != child) {
                return parents[child] = findParent(parent);
            }
            return child;
        }

        int sizeNum(int child) {
            return sizeNums[findParent(child)];
        }
    }

    @Test
    public void hitBricks() {
        int[][] grid = {{1, 0, 0, 0}, {1, 1, 1, 0}};
        int[][] hits = {{1, 0}};
        int[] result = hitBricks(grid, hits);
        log.debug("result :{}", result);
    }

    /**
     * 765. 情侣牵手
     *
     * <p>N 对情侣坐在连续排列的 2N 个座位上，想要牵到对方的手。 计算最少交换座位的次数，以便每对情侣可以并肩坐在一起。 一次交换可选择任意两人，让他们站起来交换座位。
     *
     * <p>人和座位用 0 到 2N-1 的整数表示，情侣们按顺序编号，第一对是 (0, 1)，第二对是 (2, 3)，以此类推，最后一对是 (2N-2, 2N-1)。
     *
     * <p>这些情侣的初始座位 row[i] 是由最初始坐在第 i 个座位上的人决定的。
     *
     * <p>示例 1:
     *
     * <p>输入: row = [0, 2, 1, 3] 输出: 1 解释: 我们只需要交换row[1]和row[2]的位置即可。 示例 2:
     *
     * <p>输入: row = [3, 2, 0, 1] 输出: 0 解释: 无需交换座位，所有的情侣都已经可以手牵手了。 说明:
     *
     * <p>len(row) 是偶数且数值在 [4, 60]范围内。 可以保证row 是序列 0...len(row)-1 的一个全排列。
     *
     * @param row
     * @return
     */
    public int minSwapsCouples(int[] row) {
        int len = row.length;
        int n = len >> 1;
        SwapCouples couples = new SwapCouples(n);
        for (int i = 0; i < len; i += 2) {
            couples.union(row[i] >> 1, row[i + 1] >> 1);
        }
        int circle = 0;
        for (int i = 0; i < n; i++) {
            if (i == couples.findParent(i)) {
                circle++;
            }
        }
        return n - circle;
    }

    static class SwapCouples {
        int[] parents, sizeNums;

        SwapCouples(int n) {
            parents = new int[n];
            sizeNums = new int[n];
            for (int i = 0; i < n; i++) {
                parents[i] = i;
                sizeNums[i] = 1;
            }
        }

        void union(int x, int y) {
            x = findParent(x);
            y = findParent(y);
            if (x == y) {
                return;
            }
            if (sizeNums[x] < sizeNums[y]) {
                parents[x] = y;
                sizeNums[y] += sizeNums[x];
            } else {
                parents[y] = x;
                sizeNums[x] += sizeNums[y];
            }
        }

        int findParent(int x) {
            int parent = parents[x];
            if (parent != x) {
                return parents[x] = findParent(parent);
            }
            return x;
        }
    }

    /**
     * 768. 最多能完成排序的块 II
     *
     * <p>这个问题和“最多能完成排序的块”相似，但给定数组中的元素可以重复，输入数组最大长度为2000，其中的元素最大为10**8。
     *
     * <p>arr是一个可能包含重复元素的整数数组，我们将这个数组分割成几个“块”，并将这些块分别进行排序。之后再连接起来，使得连接的结果和按升序排序后的原数组相同。
     *
     * <p>我们最多能将数组分成多少块？
     *
     * <p>示例 1:
     *
     * <p>输入: arr = [5,4,3,2,1] 输出: 1 解释: 将数组分成2块或者更多块，都无法得到所需的结果。 例如，分成 [5, 4], [3, 2, 1] 的结果是 [4,
     * 5, 1, 2, 3]，这不是有序的数组。 示例 2:
     *
     * <p>输入: arr = [2,1,3,4,4] 输出: 4 解释: 我们可以把它分成两块，例如 [2, 1], [3, 4, 4]。 然而，分成 [2, 1], [3], [4],
     * [4] 可以得到最多的块数。 注意:
     *
     * <p>arr的长度在[1, 2000]之间。 arr[i]的大小在[0, 10**8]之间。
     *
     * @param arr
     * @return
     */
    public int maxChunksToSorted(int[] arr) {
        // 利用栈
        Deque<Integer> stack = new LinkedList<>();
        for (int num : arr) {
            if (!stack.isEmpty() && num < stack.peek()) {
                int maxNum = stack.pop();
                while (!stack.isEmpty() && num < stack.peek()) {
                    stack.pop();
                }
                stack.push(maxNum);

            } else {
                stack.push(num);
            }
        }

        return stack.size();
    }

    /**
     * 5653. 可以形成最大正方形的矩形数目
     *
     * <p>给你一个数组 rectangles ，其中 rectangles[i] = [li, wi] 表示第 i 个矩形的长度为 li 、宽度为 wi 。
     *
     * <p>如果存在 k 同时满足 k <= li 和 k <= wi ，就可以将第 i 个矩形切成边长为 k 的正方形。例如，矩形 [4,6] 可以切成边长最大为 4 的正方形。
     *
     * <p>设 maxLen 为可以从矩形数组 rectangles 切分得到的 最大正方形 的边长。
     *
     * <p>返回可以切出边长为 maxLen 的正方形的矩形 数目 。
     *
     * <p>示例 1：
     *
     * <p>输入：rectangles = [[5,8],[3,9],[5,12],[16,5]] 输出：3 解释：能从每个矩形中切出的最大正方形边长分别是 [5,3,5,5] 。
     * 最大正方形的边长为 5 ，可以由 3 个矩形切分得到。 示例 2：
     *
     * <p>输入：rectangles = [[2,3],[3,7],[4,3],[3,7]] 输出：3
     *
     * <p>提示：
     *
     * <p>1 <= rectangles.length <= 1000 rectangles[i].length == 2 1 <= li, wi <= 109 li != wi
     *
     * @param rectangles
     * @return
     */
    public int countGoodRectangles(int[][] rectangles) {
        int maxLen = 0, count = 0;
        for (int[] rectangle : rectangles) {
            int len = Math.min(rectangle[0], rectangle[1]);
            if (len > maxLen) {
                maxLen = len;
                count = 1;
            } else if (len == maxLen) {
                count++;
            }
        }

        return count;
    }

    /**
     * 5243. 同积元组
     *
     * <p>给你一个由 不同 正整数组成的数组 nums ，请你返回满足 a * b = c * d 的元组 (a, b, c, d) 的数量。其中 a、b、c 和 d 都是 nums
     * 中的元素，且 a != b != c != d 。
     *
     * <p>示例 1：
     *
     * <p>输入：nums = [2,3,4,6] 输出：8 解释：存在 8 个满足题意的元组： (2,6,3,4) , (2,6,4,3) , (6,2,3,4) , (6,2,4,3)
     * (3,4,2,6) , (3,4,2,6) , (3,4,6,2) , (4,3,6,2) 示例 2：
     *
     * <p>输入：nums = [1,2,4,5,10] 输出：16 解释：存在 16 个满足题意的元组： (1,10,2,5) , (1,10,5,2) , (10,1,2,5) ,
     * (10,1,5,2) (2,5,1,10) , (2,5,10,1) , (5,2,1,10) , (5,2,10,1) (2,10,4,5) , (2,10,5,4) ,
     * (10,2,4,5) , (10,2,4,5) (4,5,2,10) , (4,5,10,2) , (5,4,2,10) , (5,4,10,2) 示例 3：
     *
     * <p>输入：nums = [2,3,4,6,8,12] 输出：40 示例 4：
     *
     * <p>输入：nums = [2,3,5,7] 输出：0
     *
     * <p>提示：
     *
     * <p>1 <= nums.length <= 1000 1 <= nums[i] <= 104 nums 中的所有元素 互不相同
     *
     * @param nums
     * @return
     */
    public int tupleSameProduct(int[] nums) {
        Map<Integer, Integer> tupleMap = new HashMap<>();
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                int mult = nums[i] * nums[j];
                int count = tupleMap.getOrDefault(mult, 0);
                tupleMap.put(mult, count + 1);
            }
        }
        int result = 0;
        for (int v : tupleMap.values()) {
            if (v > 1) {
                result += 4 * v * (v - 1);
            }
        }
        return result;
    }

    @Test
    public void tupleSameProduct() {
        int[] nums = {2, 3, 4, 6, 8, 12};
        logResult(tupleSameProduct(nums));
    }

    /**
     * 5655. 重新排列后的最大子矩阵
     *
     * <p>给你一个二进制矩阵 matrix ，它的大小为 m x n ，你可以将 matrix 中的 列 按任意顺序重新排列。
     *
     * <p>请你返回最优方案下将 matrix 重新排列后，全是 1 的子矩阵面积。
     *
     * <p>示例 1：
     *
     * <p>输入：matrix = [[0,0,1],[1,1,1],[1,0,1]] 输出：4 解释：你可以按照上图方式重新排列矩阵的每一列。 最大的全 1 子矩阵是上图中加粗的部分，面积为
     * 4 。 示例 2：
     *
     * <p>输入：matrix = [[1,0,1,0,1]] 输出：3 解释：你可以按照上图方式重新排列矩阵的每一列。 最大的全 1 子矩阵是上图中加粗的部分，面积为 3 。 示例 3：
     *
     * <p>输入：matrix = [[1,1,0],[1,0,1]] 输出：2 解释：由于你只能整列整列重新排布，所以没有比面积为 2 更大的全 1 子矩形。 示例 4：
     *
     * <p>输入：matrix = [[0,0],[0,0]] 输出：0 解释：由于矩阵中没有 1 ，没有任何全 1 的子矩阵，所以面积为 0 。
     *
     * <p>提示：
     *
     * <p>m == matrix.length n == matrix[i].length 1 <= m * n <= 105 matrix[i][j] 要么是 0 ，要么是 1 。
     *
     * @param matrix
     * @return
     */
    public int largestSubmatrix(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        int result = 0;
        TreeMap<Integer, Integer> treeMap;
        for (int i = 0; i < m; i++) {
            treeMap = new TreeMap<>();
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 1) {
                    if (i > 0) {
                        matrix[i][j] += matrix[i - 1][j];
                    }
                    int len = matrix[i][j];
                    int count = treeMap.getOrDefault(len, 0);
                    treeMap.put(len, count + 1);
                }
            }
            int count = 0;
            for (Map.Entry<Integer, Integer> entry : treeMap.descendingMap().entrySet()) {
                count += entry.getValue();
                result = Math.max(result, count * entry.getKey());
            }
        }
        logResult(matrix);

        return result;
    }

    @Test
    public void largestSubmatrix() {
        int[][] matrix = {
            {0, 1, 1, 1, 0, 1, 1, 1, 0},
            {1, 1, 0, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 0, 1, 1, 0, 1},
            {1, 1, 1, 1, 1, 1, 0, 1, 0},
            {1, 1, 1, 1, 1, 1, 1, 1, 0},
            {1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 0, 1, 1, 0, 1, 1},
            {1, 1, 1, 1, 1, 0, 0, 1, 1},
            {1, 1, 0, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 0, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 0, 1, 1, 0, 1, 0, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 0, 0, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 0, 1, 1, 0, 1},
            {1, 1, 0, 0, 0, 1, 1, 1, 1},
            {0, 1, 0, 0, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 0, 1, 1, 1}
        };
        logResult(largestSubmatrix(matrix));
    }

    /**
     * 5645. 找到最高海拔
     *
     * <p>有一个自行车手打算进行一场公路骑行，这条路线总共由 n + 1 个不同海拔的点组成。自行车手从海拔为 0 的点 0 开始骑行。
     *
     * <p>给你一个长度为 n 的整数数组 gain ，其中 gain[i] 是点 i 和点 i + 1 的 净海拔高度差（0 <= i < n）。请你返回 最高点的海拔 。
     *
     * <p>示例 1：
     *
     * <p>输入：gain = [-5,1,5,0,-7] 输出：1 解释：海拔高度依次为 [0,-5,-4,1,1,-6] 。最高海拔为 1 。 示例 2：
     *
     * <p>输入：gain = [-4,-3,-2,-1,4,3,2] 输出：0 解释：海拔高度依次为 [0,-4,-7,-9,-10,-6,-3,-1] 。最高海拔为 0 。
     *
     * <p>提示：
     *
     * <p>n == gain.length 1 <= n <= 100 -100 <= gain[i] <= 100
     *
     * @param gain
     * @return
     */
    public int largestAltitude(int[] gain) {
        int result = 0, max = 0;
        for (int g : gain) {
            result += g;
            max = Math.max(result, max);
        }

        return max;
    }

    /**
     * 5646. 需要教语言的最少人数
     *
     * <p>在一个由 m 个用户组成的社交网络里，我们获取到一些用户之间的好友关系。两个用户之间可以相互沟通的条件是他们都掌握同一门语言。
     *
     * <p>给你一个整数 n ，数组 languages 和数组 friendships ，它们的含义如下：
     *
     * <p>总共有 n 种语言，编号从 1 到 n 。 languages[i] 是第 i 位用户掌握的语言集合。 friendships[i] = [ui, vi] 表示 ui 和 vi
     * 为好友关系。 你可以选择 一门 语言并教会一些用户，使得所有好友之间都可以相互沟通。请返回你 最少 需要教会多少名用户。
     *
     * <p>请注意，好友关系没有传递性，也就是说如果 x 和 y 是好友，且 y 和 z 是好友， x 和 z 不一定是好友。
     *
     * <p>示例 1：
     *
     * <p>输入：n = 2, languages = [[1],[2],[1,2]], friendships = [[1,2],[1,3],[2,3]] 输出：1 解释：你可以选择教用户
     * 1 第二门语言，也可以选择教用户 2 第一门语言。 示例 2：
     *
     * <p>输入：n = 3, languages = [[2],[1,3],[1,2],[3]], friendships = [[1,4],[1,2],[3,4],[2,3]] 输出：2
     * 解释：教用户 1 和用户 2 第三门语言，需要教 2 名用户。
     *
     * <p>提示：
     *
     * <p>2 <= n <= 500 languages.length == m 1 <= m <= 500 1 <= languages[i].length <= n 1 <=
     * languages[i][j] <= n 1 <= ui < vi <= languages.length 1 <= friendships.length <= 500 所有的好友关系
     * (ui, vi) 都是唯一的。 languages[i] 中包含的值互不相同。
     *
     * @param n
     * @param languages
     * @param friendships
     * @return
     */
    public int minimumTeachings(int n, int[][] languages, int[][] friendships) {

        int max = 0;
        // 语言 -> 用户
        Map<Integer, Set<Integer>> langUser = new HashMap<>();

        Set<Integer> noFriends = new HashSet<>();
        for (int[] friend : friendships) {
            // 判断两个人是否可以沟通
            if (!sameLanguage(friend[0], friend[1], languages)) {
                noFriends.add(friend[0]);
                noFriends.add(friend[1]);
                for (int ulang : languages[friend[0] - 1]) {
                    Set<Integer> userSet = langUser.computeIfAbsent(ulang, k -> new HashSet<>());
                    userSet.add(friend[0]);
                    max = Math.max(max, userSet.size());
                }
                for (int vlang : languages[friend[1] - 1]) {
                    Set<Integer> userSet = langUser.computeIfAbsent(vlang, k -> new HashSet<>());
                    userSet.add(friend[1]);
                    max = Math.max(max, userSet.size());
                }
            }
        }
        log.debug("noLang:{}", noFriends.size());
        log.debug("max:{}", max);

        return noFriends.size() - max;
    }

    private boolean sameLanguage(int u, int v, int[][] languages) {
        for (int ulang : languages[u - 1]) {
            for (int vlang : languages[v - 1]) {
                if (ulang == vlang) {
                    return true;
                }
            }
        }
        return false;
    }

    @Test
    public void minimumTeachings() {
        int n = 3;
        int[][] languages = {{2}, {1, 3}, {1, 2}, {3}},
                friendships = {{1, 4}, {1, 2}, {3, 4}, {2, 3}};
        logResult(minimumTeachings(n, languages, friendships));
    }

    /**
     * 5647. 解码异或后的排列
     *
     * <p>给你一个整数数组 perm ，它是前 n 个正整数的排列，且 n 是个 奇数 。
     *
     * <p>它被加密成另一个长度为 n - 1 的整数数组 encoded ，满足 encoded[i] = perm[i] XOR perm[i + 1] 。比方说，如果 perm =
     * [1,3,2] ，那么 encoded = [2,1] 。
     *
     * <p>给你 encoded 数组，请你返回原始数组 perm 。题目保证答案存在且唯一。
     *
     * <p>示例 1：
     *
     * <p>输入：encoded = [3,1] 输出：[1,2,3] 解释：如果 perm = [1,2,3] ，那么 encoded = [1 XOR 2,2 XOR 3] = [3,1]
     * 示例 2：
     *
     * <p>输入：encoded = [6,5,4,6] 输出：[2,4,1,5,3]
     *
     * <p>提示：
     *
     * <p>3 <= n < 105 n 是奇数。 encoded.length == n - 1
     *
     * @param encoded
     * @return
     */
    public int[] decode(int[] encoded) {
        int n = encoded.length + 1;
        int[] result = new int[n];
        // a b c d e
        // encoded = [6,5,4,6] 输出：[2,4,1,5,3]
        // 先算 b ^ c ^ d ^ e = 5 ^ 6 ;
        int sum = 0;
        for (int i = 1; i <= n; i++) {
            sum ^= i;
        }
        int right = 0;
        for (int i = 1; i < encoded.length; i += 2) {
            right ^= encoded[i];
        }
        int first = sum ^ right;
        result[0] = first;
        for (int i = 0; i < encoded.length; i++) {

            result[i + 1] = result[i] ^ encoded[i];
        }

        return result;
    }

    @Test
    public void decode() {
        int[] encoded = {3, 1};
        int[] result = decode(encoded);
        log.debug("result:{}", result);
    }

    /**
     * 5663. 找出第 K 大的异或坐标值
     *
     * <p>给你一个二维矩阵 matrix 和一个整数 k ，矩阵大小为 m x n 由非负整数组成。
     *
     * <p>矩阵中坐标 (a, b) 的 值 可由对所有满足 0 <= i <= a < m 且 0 <= j <= b < n 的元素 matrix[i][j]（下标从 0
     * 开始计数）执行异或运算得到。
     *
     * <p>请你找出 matrix 的所有坐标中第 k 大的值（k 的值从 1 开始计数）。
     *
     * <p>示例 1：
     *
     * <p>输入：matrix = [[5,2],[1,6]], k = 1 输出：7 解释：坐标 (0,1) 的值是 5 XOR 2 = 7 ，为最大的值。 示例 2：
     *
     * <p>输入：matrix = [[5,2],[1,6]], k = 2 输出：5 解释：坐标 (0,0) 的值是 5 = 5 ，为第 2 大的值。 示例 3：
     *
     * <p>输入：matrix = [[5,2],[1,6]], k = 3 输出：4 解释：坐标 (1,0) 的值是 5 XOR 1 = 4 ，为第 3 大的值。 示例 4：
     *
     * <p>输入：matrix = [[5,2],[1,6]], k = 4 输出：0 解释：坐标 (1,1) 的值是 5 XOR 2 XOR 1 XOR 6 = 0 ，为第 4 大的值。
     *
     * <p>提示：
     *
     * <p>m == matrix.length n == matrix[i].length 1 <= m, n <= 1000 0 <= matrix[i][j] <= 106 1 <= k
     * <= m * n
     *
     * @param matrix
     * @param k
     * @return
     */
    public int kthLargestValue(int[][] matrix, int k) {
        // 优先队列 堆 最小堆
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        int m = matrix.length, n = matrix[0].length;
        heap.offer(matrix[0][0]);
        for (int i = 1; i < m; i++) {
            matrix[i][0] ^= matrix[i - 1][0];
            addHeap(heap, k, matrix[i][0]);
        }
        for (int j = 1; j < n; j++) {
            matrix[0][j] ^= matrix[0][j - 1];
            addHeap(heap, k, matrix[0][j]);
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                matrix[i][j] ^= matrix[i - 1][j];
                matrix[i][j] ^= matrix[i][j - 1];
                matrix[i][j] ^= matrix[i - 1][j - 1];
                addHeap(heap, k, matrix[i][j]);
            }
        }
        logResult(matrix);

        return heap.peek();
    }

    private void addHeap(PriorityQueue<Integer> heap, int k, int num) {
        if (heap.size() == k) {
            if (heap.peek() < num) {
                heap.poll();
                heap.offer(num);
            }
        } else {
            heap.offer(num);
        }
    }

    @Test
    public void kthLargestValue() {
        int[][] matrix = {{5, 2}, {1, 6}};
        int k = 3;
        logResult(kthLargestValue(matrix, k));
    }

    /**
     * 778. 水位上升的泳池中游泳
     *
     * <p>在一个 N x N 的坐标方格 grid 中，每一个方格的值 grid[i][j] 表示在位置 (i,j) 的平台高度。
     *
     * <p>现在开始下雨了。当时间为 t 时，此时雨水导致水池中任意位置的水位为 t
     * 。你可以从一个平台游向四周相邻的任意一个平台，但是前提是此时水位必须同时淹没这两个平台。假定你可以瞬间移动无限距离，也就是默认在方格内部游动是不耗时的。当然，在你游泳的时候你必须待在坐标方格里面。
     *
     * <p>你从坐标方格的左上平台 (0，0) 出发。最少耗时多久你才能到达坐标方格的右下平台 (N-1, N-1)？
     *
     * <p>示例 1:
     *
     * <p>输入: [[0,2],[1,3]] 输出: 3 解释: 时间为0时，你位于坐标方格的位置为 (0, 0)。 此时你不能游向任意方向，因为四个相邻方向平台的高度都大于当前时间为 0
     * 时的水位。
     *
     * <p>等时间到达 3 时，你才可以游向平台 (1, 1). 因为此时的水位是 3，坐标方格中的平台没有比水位 3 更高的，所以你可以游向坐标方格中的任意位置 示例2:
     *
     * <p>输入: [[0,1,2,3,4],[24,23,22,21,5],[12,13,14,15,16],[11,17,18,19,20],[10,9,8,7,6]] 输出: 16
     * 解释: 0 1 2 3 4 24 23 22 21 5 12 13 14 15 16 11 17 18 19 20 10 9 8 7 6
     *
     * <p>最终的路线用加粗进行了标记。 我们必须等到时间为 16，此时才能保证平台 (0, 0) 和 (4, 4) 是连通的
     *
     * <p>提示:
     *
     * <p>2 <= N <= 50. grid[i][j] 是 [0, ..., N*N - 1] 的排列。
     *
     * @param grid
     * @return
     */
    public int swimInWater(int[][] grid) {
        int N = grid.length;
        // 优先队列
        PriorityQueue<Integer> priorityQueue =
                new PriorityQueue<>(Comparator.comparingInt(a -> grid[a / N][a % N]));

        int result = 0;
        priorityQueue.offer(0);
        Set<Integer> visited = new HashSet<>();
        visited.add(0);
        while (!priorityQueue.isEmpty()) {
            int num = priorityQueue.poll();
            int row = num / N, col = num % N;
            result = Math.max(result, grid[row][col]);
            if (row == N - 1 && col == N - 1) {
                return result;
            }
            for (int i = 0; i < 4; i++) {
                int rowNum = row + DIR_ROW[i], colNum = col + DIR_COL[i];
                num = rowNum * N + colNum;
                if (inArea(rowNum, colNum, N, N) && !visited.contains(num)) {
                    priorityQueue.offer(num);
                    visited.add(num);
                }
            }
        }

        return result;
    }

    int[][] grid;
    static int M, N;

    /**
     * 778. 水位上升的泳池中游泳
     *
     * <p>二分法
     *
     * @param grid
     * @return
     */
    public int swimInWater2(int[][] grid) {
        this.grid = grid;
        this.N = grid.length;

        int left = 1, right = N * N - 1;

        while (left < right) {
            int mid = (left + right) >> 1;
            // 判断能否 以mid 游到终点
            if (canSwim2End(0, 0, mid, new boolean[N][N])) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }

    /**
     * dfs 深度优先遍历
     *
     * @param row
     * @param col
     * @param max
     * @param visited
     * @return
     */
    private boolean canSwim2End(int row, int col, int max, boolean[][] visited) {
        if (row == N - 1 && col == N - 1) {
            return true;
        }
        if (grid[row][col] > max) {
            return false;
        }
        visited[row][col] = true;
        for (int i = 0; i < 4; i++) {
            int rowNum = row + DIR_ROW[i], colNum = col + DIR_COL[i];
            if (!inArea(rowNum, colNum, N, N)) {
                continue;
            }
            if (visited[rowNum][colNum]) {
                continue;
            }
            if (grid[rowNum][colNum] > max) {
                continue;
            }
            if (canSwim2End(rowNum, colNum, max, visited)) {
                return true;
            }
        }

        return false;
    }

    /**
     * 773. 滑动谜题
     *
     * <p>在一个 2 x 3 的板上（board）有 5 块砖瓦，用数字 1~5 来表示, 以及一块空缺用 0 来表示.
     *
     * <p>一次移动定义为选择 0 与一个相邻的数字（上下左右）进行交换.
     *
     * <p>最终当板 board 的结果是 [[1,2,3],[4,5,0]] 谜板被解开。
     *
     * <p>给出一个谜板的初始状态，返回最少可以通过多少次移动解开谜板，如果不能解开谜板，则返回 -1 。
     *
     * <p>示例：
     *
     * <p>输入：board = [[1,2,3],[4,0,5]] 输出：1 解释：交换 0 和 5 ，1 步完成
     *
     * <p>输入：board = [[1,2,3],[5,4,0]] 输出：-1 解释：没有办法完成谜板
     *
     * <p>输入：board = [[4,1,2],[5,0,3]] 输出：5
     *
     * <p>解释： 最少完成谜板的最少移动次数是 5 ， 一种移动路径: 尚未移动: [[4,1,2],[5,0,3]] 移动 1 次: [[4,1,2],[0,5,3]] 移动 2 次:
     * [[0,1,2],[4,5,3]] 移动 3 次: [[1,0,2],[4,5,3]] 移动 4 次: [[1,2,0],[4,5,3]] 移动 5 次:
     * [[1,2,3],[4,5,0]]
     *
     * <p>输入：board = [[3,2,4],[1,5,0]] 输出：14
     *
     * <p>提示：board 是一个如上所述的 2 x 3 的数组. board[i][j] 是一个 [0, 1, 2, 3, 4, 5] 的排列.
     *
     * @param board
     * @return
     */
    public int slidingPuzzle(int[][] board) {
        // 把 324150 变成 123450 的最小操作
        int[] nums = new int[6];
        // 广度优先遍历
        Set<String> visited = new HashSet<>();
        Queue<PuzzleNode> queue = new LinkedList<>();
        int index = 0;
        StringBuilder sb = new StringBuilder();
        int rows = board.length, cols = board[0].length;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                sb.append(board[i][j]);
                if (board[i][j] == 0) {
                    index = i * cols + j;
                }
            }
        }
        queue.offer(new PuzzleNode(index, sb.toString()));
        visited.add(sb.toString());
        int step = 0;

        int[][] exchangeArrs = {
            {1, 3},
            {0, 2, 4},
            {1, 5},
            {0, 4},
            {1, 3, 5},
            {2, 4}
        };

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                PuzzleNode node = queue.poll();
                if (Objects.equals(node.val, "123450")) {
                    return step;
                }
                int[] exchanges = exchangeArrs[node.index];
                for (int idx : exchanges) {
                    String next = getSlidingResult(node.val, node.index, idx);
                    if (!visited.contains(next)) {
                        queue.offer(new PuzzleNode(idx, next));
                        visited.add(next);
                    }
                }
            }
            step++;
        }

        return -1;
    }

    private String getSlidingResult(String val, int idx1, int idx2) {
        char[] chars = val.toCharArray();
        char tmp = chars[idx1];
        chars[idx1] = chars[idx2];
        chars[idx2] = tmp;
        return new String(chars);
    }

    class PuzzleNode {
        int index;

        String val;

        PuzzleNode(int index, String val) {
            this.index = index;
            this.val = val;
        }
    }

    @Test
    public void slidingPuzzle() {
        int[][] board = {{3, 2, 4}, {1, 5, 0}};
        logResult(slidingPuzzle(board));
    }

    /**
     * 5665. 从相邻元素对还原数组
     *
     * <p>存在一个由 n 个不同元素组成的整数数组 nums ，但你已经记不清具体内容。好在你还记得 nums 中的每一对相邻元素。
     *
     * <p>给你一个二维整数数组 adjacentPairs ，大小为 n - 1 ，其中每个 adjacentPairs[i] = [ui, vi] 表示元素 ui 和 vi 在 nums
     * 中相邻。
     *
     * <p>题目数据保证所有由元素 nums[i] 和 nums[i+1] 组成的相邻元素对都存在于 adjacentPairs 中，存在形式可能是 [nums[i], nums[i+1]]
     * ，也可能是 [nums[i+1], nums[i]] 。这些相邻元素对可以 按任意顺序 出现。
     *
     * <p>返回 原始数组 nums 。如果存在多种解答，返回 其中任意一个 即可。
     *
     * <p>示例 1：
     *
     * <p>输入：adjacentPairs = [[2,1],[3,4],[3,2]] 输出：[1,2,3,4] 解释：数组的所有相邻元素对都在 adjacentPairs 中。
     * 特别要注意的是，adjacentPairs[i] 只表示两个元素相邻，并不保证其 左-右 顺序。 示例 2：
     *
     * <p>输入：adjacentPairs = [[4,-2],[1,4],[-3,1]] 输出：[-2,4,1,-3] 解释：数组中可能存在负数。 另一种解答是 [-3,1,4,-2]
     * ，也会被视作正确答案。 示例 3：
     *
     * <p>输入：adjacentPairs = [[100000,-100000]] 输出：[100000,-100000]
     *
     * <p>提示：
     *
     * <p>nums.length == n adjacentPairs.length == n - 1 adjacentPairs[i].length == 2 2 <= n <= 105
     * -105 <= nums[i], ui, vi <= 105 题目数据保证存在一些以 adjacentPairs 作为元素对的数组 nums
     *
     * @param adjacentPairs
     * @return
     */
    public int[] restoreArray(int[][] adjacentPairs) {

        if (adjacentPairs.length == 1) {
            return adjacentPairs[0];
        }
        // 并查集
        int n = adjacentPairs.length + 1;
        Map<Integer, List<Integer>> arrayMap = new HashMap<>();

        for (int[] pairs : adjacentPairs) {
            int u = pairs[0], v = pairs[1];
            List<Integer> list1 = arrayMap.computeIfAbsent(u, k -> new ArrayList<>());
            list1.add(v);
            List<Integer> list2 = arrayMap.computeIfAbsent(v, k -> new ArrayList<>());
            list2.add(u);
        }
        int start = -1;
        for (Map.Entry<Integer, List<Integer>> entry : arrayMap.entrySet()) {
            if (entry.getValue().size() == 1) {
                start = entry.getKey();
                break;
            }
        }
        Set<Integer> visited = new HashSet<>();
        visited.add(start);

        int[] result = new int[n];
        result[0] = start;
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);
        int index = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int num = queue.poll();
                for (int next : arrayMap.get(num)) {
                    if (!visited.contains(next)) {
                        result[index++] = next;
                        queue.offer(next);
                        visited.add(next);
                        break;
                    }
                }
            }
        }

        return result;
    }

    @Test
    public void restoreArray() {
        int[][] adjacentPairs = {{4, -2}, {1, 4}, {-3, 1}};
        int[] result = restoreArray(adjacentPairs);
        log.debug("result:{}", result);
    }

    /**
     * 839. 相似字符串组
     *
     * <p>如果交换字符串 X 中的两个不同位置的字母，使得它和字符串 Y 相等，那么称 X 和 Y 两个字符串相似。如果这两个字符串本身是相等的，那它们也是相似的。
     *
     * <p>例如，"tars" 和 "rats" 是相似的 (交换 0 与 2 的位置)； "rats" 和 "arts" 也是相似的，但是 "star" 不与 "tars"，"rats"，或
     * "arts" 相似。
     *
     * <p>总之，它们通过相似性形成了两个关联组：{"tars", "rats", "arts"} 和 {"star"}。注意，"tars" 和 "arts"
     * 是在同一组中，即使它们并不相似。形式上，对每个组而言，要确定一个单词在组中，只需要这个词和该组中至少一个单词相似。
     *
     * <p>给你一个字符串列表 strs。列表中的每个字符串都是 strs 中其它所有字符串的一个字母异位词。请问 strs 中有多少个相似字符串组？
     *
     * <p>示例 1：
     *
     * <p>输入：strs = ["tars","rats","arts","star"] 输出：2 示例 2：
     *
     * <p>输入：strs = ["omv","ovm"] 输出：1
     *
     * <p>提示：
     *
     * <p>1 <= strs.length <= 100 1 <= strs[i].length <= 1000 sum(strs[i].length) <= 2 * 104 strs[i]
     * 只包含小写字母。 strs 中的所有单词都具有相同的长度，且是彼此的字母异位词。
     *
     * <p>备注：
     *
     * <p>字母异位词（anagram），一种把某个字符串的字母的位置（顺序）加以改换所形成的新词。
     *
     * @param strs
     * @return
     */
    public int numSimilarGroups(String[] strs) {

        int len = strs.length;
        groupMap = new HashMap<>();
        for (String str : strs) {
            groupMap.put(str, str);
        }

        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                if (Objects.equals(strs[i], strs[j])) {
                    continue;
                }
                if (isSimilar(strs[i], strs[j])) {
                    unionGroup(strs[i], strs[j]);
                }
            }
        }
        Set<String> set = new HashSet<>();
        for (String str : strs) {
            set.add(findRoot(str));
        }

        return set.size();
    }

    /**
     * 判断两个字符串是否相似
     *
     * @param str1
     * @param str2
     * @return
     */
    private boolean isSimilar(String str1, String str2) {
        if (Objects.equals(str1, str2)) {
            return true;
        }
        if (str1.length() != str2.length()) {
            return false;
        }
        int count = 0, len = str1.length();
        char[] chars1 = str1.toCharArray(), chars2 = str2.toCharArray();
        int firstIndex = -1, secondIndex = -1;
        for (int i = 0; i < len; i++) {
            if (chars1[i] != chars2[i]) {
                if (firstIndex == -1) {
                    firstIndex = i;
                } else {
                    secondIndex = i;
                }
                count++;
            }
            if (count > 2) {
                return false;
            }
        }
        return chars1[firstIndex] == chars2[secondIndex]
                && chars1[secondIndex] == chars2[firstIndex];
    }

    Map<String, String> groupMap;

    private void unionGroup(String str1, String str2) {
        str1 = findRoot(str1);
        str2 = findRoot(str2);
        groupMap.put(str2, str1);
    }

    private String findRoot(String target) {
        String root = groupMap.get(target);
        if (!Objects.equals(root, target)) {
            root = findRoot(root);
            // 压缩路径
            groupMap.put(target, root);
            return root;
        }
        return target;
    }

    @Test
    public void numSimilarGroups() {
        String[] strs = {"omv", "ovm"};
        logResult(numSimilarGroups(strs));
    }

    /*
    Map<Integer, List<Integer>> listMap;
    Map<Integer, Integer> union;

    private void unionArray(int u, int v) {
        u = findRoot(u);
        v = findRoot(v);
        union.put(u, v);
    }

    private int findRoot(int target) {
        Integer root = union.get(target);
        if (Objects.isNull(root)) {
            union.put(target, target);
            return target;
        }
        if (root != target) {
            root = findRoot(root);
            // 压缩路径
            union.put(target, root);
            return root;
        }

        return target;
    }*/
}
