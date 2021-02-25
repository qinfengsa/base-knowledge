package com.qinfengsa.structure.leetcode;

import static com.qinfengsa.structure.util.LogUtils.logResult;

import java.util.ArrayList;
import java.util.Arrays;
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

    /**
     * 1263. 推箱子
     *
     * <p>「推箱子」是一款风靡全球的益智小游戏，玩家需要将箱子推到仓库中的目标位置。
     *
     * <p>游戏地图用大小为 n * m 的网格 grid 表示，其中每个元素可以是墙、地板或者是箱子。
     *
     * <p>现在你将作为玩家参与游戏，按规则将箱子 'B' 移动到目标位置 'T' ：
     *
     * <p>玩家用字符 'S' 表示，只要他在地板上，就可以在网格中向上、下、左、右四个方向移动。 地板用字符 '.' 表示，意味着可以自由行走。 墙用字符 '#'
     * 表示，意味着障碍物，不能通行。 箱子仅有一个，用字符 'B' 表示。相应地，网格上有一个目标位置 'T'。
     * 玩家需要站在箱子旁边，然后沿着箱子的方向进行移动，此时箱子会被移动到相邻的地板单元格。记作一次「推动」。 玩家无法越过箱子。 返回将箱子推到目标位置的最小 推动
     * 次数，如果无法做到，请返回 -1。
     *
     * <p>示例 1：
     *
     * <p>输入：grid = [["#","#","#","#","#","#"], ["#","T","#","#","#","#"],
     * ["#",".",".","B",".","#"], ["#",".","#","#",".","#"], ["#",".",".",".","S","#"],
     * ["#","#","#","#","#","#"]] 输出：3 解释：我们只需要返回推箱子的次数。 示例 2：
     *
     * <p>输入：grid = [["#","#","#","#","#","#"], ["#","T","#","#","#","#"],
     * ["#",".",".","B",".","#"], ["#","#","#","#",".","#"], ["#",".",".",".","S","#"],
     * ["#","#","#","#","#","#"]] 输出：-1 示例 3：
     *
     * <p>输入：grid = [["#","#","#","#","#","#"], ["#","T",".",".","#","#"],
     * ["#",".","#","B",".","#"], ["#",".",".",".",".","#"], ["#",".",".",".","S","#"],
     * ["#","#","#","#","#","#"]] 输出：5 解释：向下、向左、向左、向上再向上。 示例 4：
     *
     * <p>输入：grid = [["#","#","#","#","#","#","#"], ["#","S","#",".","B","T","#"],
     * ["#","#","#","#","#","#","#"]] 输出：-1
     *
     * <p>提示：
     *
     * <p>1 <= grid.length <= 20 1 <= grid[i].length <= 20 grid 仅包含字符 '.', '#', 'S' , 'T', 以及 'B'。
     * grid 中 'S', 'B' 和 'T' 各只能出现一个。
     *
     * @param grid
     * @return
     */
    public int minPushBox(char[][] grid) {

        this.boxGrid = grid;

        // bfs 广度优先遍历
        M = grid.length;
        N = grid[0].length;
        int[] box = new int[2], target = new int[2], person = new int[2];

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                switch (grid[i][j]) {
                    case 'S':
                        {
                            person[0] = i;
                            person[1] = j;
                        }
                        break;
                    case 'T':
                        {
                            target[0] = i;
                            target[1] = j;
                        }
                        break;
                    case 'B':
                        {
                            box[0] = i;
                            box[1] = j;
                        }
                        break;
                }
            }
        }
        // 记录访问的节点和方向
        boolean[][][] visited = new boolean[M][N][4];
        // 找到箱子, 目标 人 的位置
        // 广度优先遍历 箱子向四个方向移动
        // 判断 人能否到达指定位置(深度优先遍历)
        Queue<BoxNode> queue = new LinkedList<>();
        queue.offer(new BoxNode(box[0], box[1], person[0], person[1]));
        int step = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int k = 0; k < size; k++) {
                BoxNode node = queue.poll();
                if (node.boxRow == target[0] && node.boxCol == target[1]) {
                    return step;
                }
                for (int i = 0; i < 4; i++) {
                    int nextRow = node.boxRow + DIR_ROW[i], nextCol = node.boxCol + DIR_COL[i];

                    if (!inArea(nextRow, nextCol, M, N)) {
                        continue;
                    }
                    if (grid[nextRow][nextCol] == '#') {
                        continue;
                    }
                    if (visited[nextRow][nextCol][i]) {
                        continue;
                    }
                    int pTargetRow = node.boxRow - DIR_ROW[i],
                            pTargetCol = node.boxCol - DIR_COL[i];

                    if (!inArea(pTargetRow, pTargetCol, M, N)) {
                        continue;
                    }
                    if (grid[pTargetRow][pTargetCol] == '#') {
                        continue;
                    }
                    // 判断 人能否到达指定位置(深度优先遍历)
                    if (canArriveTarget(
                            new int[] {node.personRow, node.personCol},
                            new int[] {pTargetRow, pTargetCol},
                            new int[] {node.boxRow, node.boxCol},
                            new boolean[M][N])) {

                        visited[nextRow][nextCol][i] = true;
                        // person 的 位置会在原来 box 的位置
                        queue.offer(new BoxNode(nextRow, nextCol, node.boxRow, node.boxCol));
                    }
                }
            }
            step++;
        }
        return -1;
    }

    char[][] boxGrid;

    static class BoxNode {
        int boxRow, boxCol, personRow, personCol;

        public BoxNode(int boxRow, int boxCol, int personRow, int personCol) {
            this.boxRow = boxRow;
            this.boxCol = boxCol;
            this.personRow = personRow;
            this.personCol = personCol;
        }
    }

    private boolean canArriveTarget(int[] person, int[] target, int[] box, boolean[][] visited) {
        if (person[0] == target[0] && person[1] == target[1]) {
            return true;
        }
        visited[person[0]][person[1]] = true;
        for (int i = 0; i < 4; i++) {
            int nextRow = person[0] + DIR_ROW[i], nextCol = person[1] + DIR_COL[i];
            if (!inArea(nextRow, nextCol, M, N)) {
                continue;
            }
            if (boxGrid[nextRow][nextCol] == '#') {
                continue;
            }
            if (nextRow == box[0] && nextCol == box[1]) {
                continue;
            }
            if (visited[nextRow][nextCol]) {
                continue;
            }
            visited[nextRow][nextCol] = true;
            if (canArriveTarget(new int[] {nextRow, nextCol}, target, box, visited)) {
                return true;
            }
        }

        return false;
    }

    @Test
    public void minPushBox() {
        char[][] grid = {
            {'#', '#', '#', '#', '#', '#'},
            {'#', 'T', '.', '.', '#', '#'},
            {'#', '.', '#', 'B', '.', '#'},
            {'#', '.', '.', '.', '.', '#'},
            {'#', '.', '.', '.', 'S', '#'},
            {'#', '#', '#', '#', '#', '#'}
        };
        logResult(minPushBox(grid));
    }

    /**
     * 5657. 唯一元素的和
     *
     * <p>给你一个整数数组 nums 。数组中唯一元素是那些只出现 恰好一次 的元素。
     *
     * <p>请你返回 nums 中唯一元素的 和 。
     *
     * <p>示例 1：
     *
     * <p>输入：nums = [1,2,3,2] 输出：4 解释：唯一元素为 [1,3] ，和为 4 。 示例 2：
     *
     * <p>输入：nums = [1,1,1,1,1] 输出：0 解释：没有唯一元素，和为 0 。 示例 3 ：
     *
     * <p>输入：nums = [1,2,3,4,5] 输出：15 解释：唯一元素为 [1,2,3,4,5] ，和为 15 。
     *
     * <p>提示：
     *
     * <p>1 <= nums.length <= 100 1 <= nums[i] <= 100
     *
     * @param nums
     * @return
     */
    public int sumOfUnique(int[] nums) {
        int[] countNums = new int[101];
        for (int num : nums) {
            countNums[num]++;
        }
        int result = 0;
        for (int i = 1; i <= 100; i++) {
            if (countNums[i] == 1) {
                result += i;
            }
        }
        return result;
    }

    /**
     * 5658. 任意子数组和的绝对值的最大值
     *
     * <p>给你一个整数数组 nums 。一个子数组 [numsl, numsl+1, ..., numsr-1, numsr] 的 和的绝对值 为 abs(numsl + numsl+1 +
     * ... + numsr-1 + numsr) 。
     *
     * <p>请你找出 nums 中 和的绝对值 最大的任意子数组（可能为空），并返回该 最大值 。
     *
     * <p>abs(x) 定义如下：
     *
     * <p>如果 x 是负整数，那么 abs(x) = -x 。 如果 x 是非负整数，那么 abs(x) = x 。
     *
     * <p>示例 1：
     *
     * <p>输入：nums = [1,-3,2,3,-4] 输出：5 解释：子数组 [2,3] 和的绝对值最大，为 abs(2+3) = abs(5) = 5 。 示例 2：
     *
     * <p>输入：nums = [2,-5,1,-4,3,-2] 输出：8 解释：子数组 [-5,1,-4] 和的绝对值最大，为 abs(-5+1-4) = abs(-8) = 8 。
     *
     * <p>提示：
     *
     * <p>1 <= nums.length <= 105 -104 <= nums[i] <= 104
     *
     * @param nums
     * @return
     */
    public int maxAbsoluteSum(int[] nums) {
        int len = nums.length;

        int[] maxNums = new int[len], minNums = new int[len];
        maxNums[0] = nums[0];
        minNums[0] = nums[0];
        int max = nums[0], min = nums[0];
        for (int i = 1; i < len; i++) {
            if (maxNums[i - 1] > 0) {
                maxNums[i] = maxNums[i - 1] + nums[i];
            } else {
                maxNums[i] = nums[i];
            }
            max = Math.max(max, maxNums[i]);
            if (minNums[i - 1] < 0) {
                minNums[i] = minNums[i - 1] + nums[i];
            } else {
                minNums[i] = nums[i];
            }
            min = Math.min(min, minNums[i]);
        }
        log.debug("maxNums:{}", maxNums);
        log.debug("minNums:{}", minNums);
        return Math.max(max, -min);
    }

    @Test
    public void maxAbsoluteSum() {
        int[] nums = {-11, 1, 1, 1, 1, 1};
        logResult(maxAbsoluteSum(nums));
    }

    /**
     * 5672. 检查数组是否经排序和轮转得到
     *
     * <p>给你一个数组 nums 。nums 的源数组中，所有元素与 nums 相同，但按非递减顺序排列。
     *
     * <p>如果 nums 能够由源数组轮转若干位置（包括 0 个位置）得到，则返回 true ；否则，返回 false 。
     *
     * <p>源数组中可能存在 重复项 。
     *
     * <p>注意：我们称数组 A 在轮转 x 个位置后得到长度相同的数组 B ，当它们满足 A[i] == B[(i+x) % A.length] ，其中 % 为取余运算。
     *
     * <p>示例 1：
     *
     * <p>输入：nums = [3,4,5,1,2] 输出：true 解释：[1,2,3,4,5] 为有序的源数组。 可以轮转 x = 3 个位置，使新数组从值为 3
     * 的元素开始：[3,4,5,1,2] 。 示例 2：
     *
     * <p>输入：nums = [2,1,3,4] 输出：false 解释：源数组无法经轮转得到 nums 。 示例 3：
     *
     * <p>输入：nums = [1,2,3] 输出：true 解释：[1,2,3] 为有序的源数组。 可以轮转 x = 0 个位置（即不轮转）得到 nums 。 示例 4：
     *
     * <p>输入：nums = [1,1,1] 输出：true 解释：[1,1,1] 为有序的源数组。 轮转任意个位置都可以得到 nums 。 示例 5：
     *
     * <p>输入：nums = [2,1] 输出：true 解释：[1,2] 为有序的源数组。 可以轮转 x = 5 个位置，使新数组从值为 2 的元素开始：[2,1] 。
     *
     * <p>提示：
     *
     * <p>1 <= nums.length <= 100 1 <= nums[i] <= 100
     *
     * @param nums
     * @return
     */
    public boolean check(int[] nums) {
        int len = nums.length;
        if (len <= 2) {
            return true;
        }
        // 从前往后找非递减index
        int index = 0;
        for (int i = 1; i < len; i++) {
            if (nums[i] >= nums[i - 1]) {
                index = i;
            } else {
                break;
            }
        }
        if (index == len - 1) {
            return true;
        }
        index++;
        for (int i = index + 1; i < len; i++) {
            if (nums[i] >= nums[i - 1]) {
                index = i;
            } else {
                break;
            }
        }
        if (index != len - 1) {
            return false;
        }

        if (nums[len - 1] <= nums[0]) {
            return true;
        }

        return false;
    }

    @Test
    public void check() {
        int[] nums = {1, 2, 3, 2};
        logResult(check(nums));
    }

    /**
     * 1210. 穿过迷宫的最少移动次数
     *
     * <p>你还记得那条风靡全球的贪吃蛇吗？
     *
     * <p>我们在一个 n*n 的网格上构建了新的迷宫地图，蛇的长度为 2，也就是说它会占去两个单元格。蛇会从左上角（(0, 0) 和 (0, 1)）开始移动。我们用 0 表示空单元格，用 1
     * 表示障碍物。蛇需要移动到迷宫的右下角（(n-1, n-2) 和 (n-1, n-1)）。
     *
     * <p>每次移动，蛇可以这样走：
     *
     * <p>如果没有障碍，则向右移动一个单元格。并仍然保持身体的水平／竖直状态。 如果没有障碍，则向下移动一个单元格。并仍然保持身体的水平／竖直状态。
     * 如果它处于水平状态并且其下面的两个单元都是空的，就顺时针旋转 90 度。蛇从（(r, c)、(r, c+1)）移动到 （(r, c)、(r+1, c)）。
     *
     * <p>如果它处于竖直状态并且其右面的两个单元都是空的，就逆时针旋转 90 度。蛇从（(r, c)、(r+1, c)）移动到（(r, c)、(r, c+1)）。
     *
     * <p>返回蛇抵达目的地所需的最少移动次数。
     *
     * <p>如果无法到达目的地，请返回 -1。
     *
     * <p>示例 1：
     *
     * <p>输入：grid = [[0,0,0,0,0,1], [1,1,0,0,1,0], [0,0,0,0,1,1], [0,0,1,0,1,0], [0,1,1,0,0,0],
     * [0,1,1,0,0,0]] 输出：11 解释： 一种可能的解决方案是 [右, 右, 顺时针旋转, 右, 下, 下, 下, 下, 逆时针旋转, 右, 下]。 示例 2：
     *
     * <p>输入：grid = [[0,0,1,1,1,1], [0,0,0,0,1,1], [1,1,0,0,0,1], [1,1,1,0,0,1], [1,1,1,0,0,1],
     * [1,1,1,0,0,0]] 输出：9
     *
     * <p>提示：
     *
     * <p>2 <= n <= 100 0 <= grid[i][j] <= 1 蛇保证从空单元格开始出发。
     *
     * @param grid
     * @return
     */
    public int minimumMoves(int[][] grid) {
        int n = grid.length;
        if (grid[n - 1][n - 1] != 0 || grid[n - 1][n - 2] != 0) {
            return -1;
        }

        // 广度优先遍历，

        MoveNode root = new MoveNode(0, 1, true);
        Queue<MoveNode> queue = new LinkedList<>();
        queue.offer(root);
        // 有
        int step = 0;
        boolean[][][] visited = new boolean[n][n][2];
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                MoveNode node = queue.poll();
                if (node.headRow == n - 1 && node.headCol == n - 1 && node.flag) {
                    return step;
                }
                int flagIdx = node.flag ? 1 : 0;
                if (visited[node.headRow][node.headCol][flagIdx]) {
                    continue;
                }
                visited[node.headRow][node.headCol][flagIdx] = true;

                // 横着
                if (node.flag) {
                    // 右移
                    int nextRow = node.headRow, nextCol = node.headCol + 1;
                    if (nextCol < n
                            && grid[nextRow][nextCol] == 0
                            && !visited[nextRow][nextCol][1]) {
                        queue.offer(new MoveNode(nextRow, nextCol, true));
                    }
                    // 下移 旋转
                    nextRow = node.headRow + 1;
                    nextCol = node.headCol;
                    if (nextRow < n
                            && nextCol < n
                            && grid[nextRow][nextCol - 1] == 0
                            && grid[nextRow][nextCol] == 0) {
                        // 下移
                        if (!visited[nextRow][nextCol][1]) {
                            queue.offer(new MoveNode(nextRow, nextCol, true));
                        }
                        // 旋转
                        if (!visited[nextRow][nextCol - 1][0]) {
                            queue.offer(new MoveNode(nextRow, nextCol - 1, false));
                        }
                    }

                } else {
                    // 竖着

                    // 下移
                    int nextRow = node.headRow + 1, nextCol = node.headCol;
                    if (nextRow < n
                            && grid[nextRow][nextCol] == 0
                            && !visited[nextRow][nextCol][0]) {
                        queue.offer(new MoveNode(nextRow, nextCol, false));
                    }

                    // 右移 + 旋转
                    nextRow = node.headRow;
                    nextCol = node.headCol + 1;
                    if (nextRow < n
                            && nextCol < n
                            && grid[nextRow - 1][nextCol] == 0
                            && grid[nextRow][nextCol] == 0) {
                        // 右移
                        if (!visited[nextRow][nextCol][0]) {
                            queue.offer(new MoveNode(nextRow, nextCol, false));
                        }
                        // 旋转
                        if (!visited[nextRow - 1][nextCol][1]) {
                            queue.offer(new MoveNode(nextRow - 1, nextCol, true));
                        }
                    }
                }
            }
            step++;
        }

        return -1;
    }

    static class MoveNode {
        int headRow, headCol;

        boolean flag;

        public MoveNode(int headRow, int headCol, boolean flag) {
            this.headRow = headRow;
            this.headCol = headCol;
            this.flag = flag;
        }
    }

    @Test
    public void minimumMoves() {
        int[][] grid = {
            {0, 0, 1, 1, 1, 1},
            {0, 0, 0, 0, 1, 1},
            {1, 1, 0, 0, 0, 1},
            {1, 1, 1, 0, 0, 1},
            {1, 1, 1, 0, 0, 1},
            {1, 1, 1, 0, 0, 0}
        };
        logResult(minimumMoves(grid));
    }

    /**
     * 992. K 个不同整数的子数组
     *
     * <p>给定一个正整数数组 A，如果 A 的某个子数组中不同整数的个数恰好为 K，则称 A 的这个连续、不一定独立的子数组为好子数组。
     *
     * <p>（例如，[1,2,3,1,2] 中有 3 个不同的整数：1，2，以及 3。）
     *
     * <p>返回 A 中好子数组的数目。
     *
     * <p>示例 1：
     *
     * <p>输入：A = [1,2,1,2,3], K = 2 输出：7 解释：恰好由 2 个不同整数组成的子数组：[1,2], [2,1], [1,2], [2,3], [1,2,1],
     * [2,1,2], [1,2,1,2]. 示例 2：
     *
     * <p>输入：A = [1,2,1,3,4], K = 3 输出：3 解释：恰好由 3 个不同整数组成的子数组：[1,2,1,3], [2,1,3], [1,3,4].
     *
     * <p>提示：
     *
     * <p>1 <= A.length <= 20000 1 <= A[i] <= A.length 1 <= K <= A.length
     *
     * @param A
     * @param K
     * @return
     */
    public int subarraysWithKDistinct(int[] A, int K) {
        // 滑动窗口
        int len = A.length;
        int left = 0;
        int result = 0, count = 0;
        int[] nums = new int[len + 1];
        for (int right = 0; right < len; right++) {

            if (nums[A[right]] == 0) {
                count++;
            }
            nums[A[right]]++;
            while (count > K) {
                nums[A[left]]--;
                if (nums[A[left]] == 0) {
                    count--;
                }
                left++;
            }
            if (count == K) {
                int tmpLeft = left;
                while (count == K) {
                    nums[A[left]]--;
                    if (nums[A[left]] == 0) {
                        count--;
                    }
                    left++;
                    result++;
                }
                for (int i = tmpLeft; i < left; i++) {
                    nums[A[i]]++;
                }
                left = tmpLeft;
                count = K;
            }
        }

        return result;
    }

    @Test
    public void subarraysWithKDistinct() {
        int[] A = {1, 2, 1, 3, 4};
        int K = 3;
        logResult(subarraysWithKDistinct(A, K));
    }

    /**
     * LCP 04. 覆盖
     *
     * <p>你有一块棋盘，棋盘上有一些格子已经坏掉了。你还有无穷块大小为1 *
     * 2的多米诺骨牌，你想把这些骨牌不重叠地覆盖在完好的格子上，请找出你最多能在棋盘上放多少块骨牌？这些骨牌可以横着或者竖着放。
     *
     * <p>输入：n, m代表棋盘的大小；broken是一个b * 2的二维数组，其中每个元素代表棋盘上每一个坏掉的格子的位置。
     *
     * <p>输出：一个整数，代表最多能在棋盘上放的骨牌数。
     *
     * <p>示例 1：
     *
     * <p>输入：n = 2, m = 3, broken = [[1, 0], [1, 1]] 输出：2 解释：我们最多可以放两块骨牌：[[0, 0], [0, 1]]以及[[0, 2],
     * [1, 2]]。（见下图）
     *
     * <p>示例 2：
     *
     * <p>输入：n = 3, m = 3, broken = [] 输出：4 解释：下图是其中一种可行的摆放方式
     *
     * <p>限制：
     *
     * <p>1 <= n <= 8 1 <= m <= 8 0 <= b <= n * m
     *
     * @param n
     * @param m
     * @param broken
     * @return
     */
    public int domino(int n, int m, int[][] broken) {
        int len = n * m;
        boolean[][] graph = new boolean[n][m];
        for (int[] b : broken) {
            graph[b[0]][b[1]] = true;
        }
        int[] match = new int[len];
        Arrays.fill(match, -1);
        boolean[] visited = new boolean[len];
        int result = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (graph[i][j] || ((i + j) & 1) == 1) {
                    continue;
                }
                Arrays.fill(visited, false);
                if (findDomino(graph, match, visited, i, j)) {
                    result++;
                }
            }
        }

        return result;
    }

    private boolean findDomino(
            boolean[][] graph, int[] match, boolean[] visited, int row, int col) {

        int n = graph.length, m = graph[0].length;
        for (int i = 0; i < 4; i++) {
            int newRow = row + DIR_ROW[i], newCol = col + DIR_COL[i];
            if (!inArea(newRow, newCol, n, m) || graph[newRow][newCol]) {
                continue;
            }

            int idx = newRow * m + newCol;
            if (visited[idx]) {
                continue;
            }
            visited[idx] = true;
            if (match[idx] == -1
                    || findDomino(graph, match, visited, match[idx] / m, match[idx] % m)) {
                match[idx] = row * m + col;
                return true;
            }
        }
        return false;
    }

    /**
     * LCP 20. 快速公交
     *
     * <p>小扣打算去秋日市集，由于游客较多，小扣的移动速度受到了人流影响：
     *
     * <p>小扣从 x 号站点移动至 x + 1 号站点需要花费的时间为 inc； 小扣从 x 号站点移动至 x - 1 号站点需要花费的时间为 dec。 现有 m 辆公交车，编号为 0 到
     * m-1。小扣也可以通过搭乘编号为 i 的公交车，从 x 号站点移动至 jump[i]*x 号站点，耗时仅为 cost[i]。小扣可以搭乘任意编号的公交车且搭乘公交次数不限。
     *
     * <p>假定小扣起始站点记作 0，秋日市集站点记作 target，请返回小扣抵达秋日市集最少需要花费多少时间。由于数字较大，最终答案需要对 1000000007 (1e9 + 7) 取模。
     *
     * <p>注意：小扣可在移动过程中到达编号大于 target 的站点。
     *
     * <p>示例 1：
     *
     * <p>输入：target = 31, inc = 5, dec = 3, jump = [6], cost = [10]
     *
     * <p>输出：33
     *
     * <p>解释： 小扣步行至 1 号站点，花费时间为 5； 小扣从 1 号站台搭乘 0 号公交至 6 * 1 = 6 站台，花费时间为 10； 小扣从 6 号站台步行至 5
     * 号站台，花费时间为 3； 小扣从 5 号站台搭乘 0 号公交至 6 * 5 = 30 站台，花费时间为 10； 小扣从 30 号站台步行至 31 号站台，花费时间为 5；
     * 最终小扣花费总时间为 33。
     *
     * <p>示例 2：
     *
     * <p>输入：target = 612, inc = 4, dec = 5, jump = [3,6,8,11,5,10,4], cost = [4,7,6,3,7,6,4]
     *
     * <p>输出：26
     *
     * <p>解释： 小扣步行至 1 号站点，花费时间为 4； 小扣从 1 号站台搭乘 0 号公交至 3 * 1 = 3 站台，花费时间为 4； 小扣从 3 号站台搭乘 3 号公交至 11 *
     * 3 = 33 站台，花费时间为 3； 小扣从 33 号站台步行至 34 站台，花费时间为 4； 小扣从 34 号站台搭乘 0 号公交至 3 * 34 = 102 站台，花费时间为 4；
     * 小扣从 102 号站台搭乘 1 号公交至 6 * 102 = 612 站台，花费时间为 7； 最终小扣花费总时间为 26。
     *
     * <p>提示：
     *
     * <p>1 <= target <= 10^9 1 <= jump.length, cost.length <= 10 2 <= jump[i] <= 10^6 1 <= inc,
     * dec, cost[i] <= 10^6
     *
     * @param target
     * @param inc
     * @param dec
     * @param jump
     * @param cost
     * @return
     */
    public int busRapidTransit(int target, int inc, int dec, int[] jump, int[] cost) {
        if (jump.length == 0) {
            return target * inc;
        }
        this.inc = inc;
        this.dec = dec;
        this.jump = jump;
        this.cost = cost;
        costMap = new HashMap<>();
        // 从终点出发
        return (int) (busRapidTransitDfs(target) % MOD);
    }

    static final int MOD = 1000000007;

    private long busRapidTransitDfs(long target) {
        if (target == 0) {
            return 0;
        }
        if (target == 1) {
            return inc;
        }
        if (costMap.containsKey(target)) {
            return costMap.get(target);
        }
        long result = target * inc;
        for (int i = 0; i < jump.length; i++) {
            long num = target % jump[i];
            if (num == 0) {
                result = Math.min(result, busRapidTransitDfs(target / jump[i]) + cost[i]);
            } else {
                // 左移 num 个站点
                result =
                        Math.min(
                                result, busRapidTransitDfs(target / jump[i]) + cost[i] + num * inc);
                // 右移 jump[i] - num 个站点
                result =
                        Math.min(
                                result,
                                busRapidTransitDfs(target / jump[i] + 1)
                                        + cost[i]
                                        + (jump[i] - num) * dec);
            }
        }
        costMap.put(target, result);
        return result;
    }

    private Map<Long, Long> costMap;

    private int inc, dec;
    private int[] jump, cost;

    /**
     * LCP 14. 切分数组
     *
     * <p>给定一个整数数组 nums ，小李想将 nums 切割成若干个非空子数组，使得每个子数组最左边的数和最右边的数的最大公约数大于 1
     * 。为了减少他的工作量，请求出最少可以切成多少个子数组。
     *
     * <p>示例 1：
     *
     * <p>输入：nums = [2,3,3,2,3,3]
     *
     * <p>输出：2
     *
     * <p>解释：最优切割为 [2,3,3,2] 和 [3,3] 。第一个子数组头尾数字的最大公约数为 2 ，第二个子数组头尾数字的最大公约数为 3 。
     *
     * <p>示例 2：
     *
     * <p>输入：nums = [2,3,5,7]
     *
     * <p>输出：4
     *
     * <p>解释：只有一种可行的切割：[2], [3], [5], [7]
     *
     * <p>限制：
     *
     * <p>1 <= nums.length <= 10^5 2 <= nums[i] <= 10^6
     *
     * @param nums
     * @return
     */
    public int splitArray(int[] nums) {

        int maxNum = Arrays.stream(nums).max().getAsInt();
        // 最小质数
        minPrime = new int[maxNum + 1];
        for (int i = 2; i <= maxNum; i++) {
            if (minPrime[i] > 0) {
                continue;
            }
            // 记录最小质数
            for (int j = i; j <= maxNum; j += i) {
                minPrime[j] = i;
            }
        }
        int len = nums.length;
        int[] dp = new int[len];
        primeMinIndexMap = new HashMap<>();
        for (int i = 0; i < len; i++) {
            int num = nums[i];
            // 当前num 单独拆分1个数组
            dp[i] = i > 0 ? dp[i - 1] + 1 : 1;
            // 因式分解
            while (num > 1) {
                // 当前元素的最小质数
                int factor = minPrime[num];
                int minIndex = primeMinIndexMap.getOrDefault(factor, i);
                primeMinIndexMap.put(factor, minIndex);
                if (minIndex > 0) {
                    dp[i] = Math.min(dp[i], dp[minIndex - 1] + 1);
                } else {
                    // 和第一位组合
                    dp[i] = 1;
                }
                if (dp[i] < dp[minIndex]) {
                    primeMinIndexMap.put(factor, i);
                }
                // 因式分解
                num /= factor;
            }
        }

        return dp[len - 1];
    }

    private int[] minPrime;

    Map<Integer, Integer> primeMinIndexMap;

    /**
     * LCP 15. 游乐园的迷宫
     *
     * <p>小王来到了游乐园，她玩的第一个项目是模拟推销员。有一个二维平面地图，其中散布着 N 个推销点，编号 0 到
     * N-1，不存在三点共线的情况。每两点之间有一条直线相连。游戏没有规定起点和终点，但限定了每次转角的方向。首先，小王需要先选择两个点分别作为起点和终点，然后从起点开始访问剩余 N-2
     * 个点恰好一次并回到终点。访问的顺序需要满足一串给定的长度为 N-2 由 L 和 R 组成的字符串
     * direction，表示从起点出发之后在每个顶点上转角的方向。根据这个提示，小王希望你能够帮她找到一个可行的遍历顺序，输出顺序下标（若有多个方案，输出任意一种）。可以证明这样的遍历顺序一定是存在的。
     *
     * <p>Screenshot 2020-03-20 at 17.04.58.png
     *
     * <p>（上图：A->B->C 右转； 下图：D->E->F 左转）
     *
     * <p>示例 1：
     *
     * <p>输入：points = [[1,1],[1,4],[3,2],[2,1]], direction = "LL"
     *
     * <p>输入：[0,2,1,3]
     *
     * <p>解释：[0,2,1,3] 是符合"LL"的方案之一。在 [0,2,1,3] 方案中，0->2->1 是左转方向， 2->1->3 也是左转方向 图片.gif
     *
     * <p>示例 2：
     *
     * <p>输入：points = [[1,3],[2,4],[3,3],[2,1]], direction = "LR"
     *
     * <p>输入：[0,3,1,2]
     *
     * <p>解释：[0,3,1,2] 是符合"LR"的方案之一。在 [0,3,1,2] 方案中，0->3->1 是左转方向， 3->1->2 是右转方向
     *
     * <p>限制：
     *
     * <p>3 <= points.length <= 1000 且 points[i].length == 2 1 <= points[i][0],points[i][1] <= 10000
     * direction.length == points.length - 2 direction 只包含 "L","R"
     *
     * @param points
     * @param direction
     * @return
     */
    public int[] visitOrder(int[][] points, String direction) {
        int len = points.length, start = 0, index = 0;

        int[] result = new int[len];
        boolean[] visited = new boolean[len];
        // 找最左边的点
        for (int i = 1; i < len; i++) {
            if (points[i][0] < points[start][0]) {
                start = i;
            }
        }
        result[index++] = start;
        visited[start] = true;

        for (char c : direction.toCharArray()) {
            int next = -1;

            for (int i = 0; i < len; i++) {
                if (visited[i]) {
                    continue;
                }
                if (next == -1) {
                    next = i;
                    continue;
                }
                // 斜率大 靠左 (point[next][1] - point[start][1]) y/  (point[next][0] - point[start][0])x
                // >  (point[i[1] - point[start][1]) y/  (point[i][0] - point[start][0])x
                // => (point[next][1] - point[start][1]) *  (point[i][0] - point[start][0])
                // >  (point[i[1] - point[start][1]) * (point[next][0] - point[start][0])

                int sub =
                        (points[next][1] - points[start][1]) * (points[i][0] - points[start][0])
                                - (points[i][1] - points[start][1])
                                        * (points[next][0] - points[start][0]);
                if ((c == 'L' && sub > 0) || (c == 'R' && sub < 0)) {
                    next = i;
                }
            }

            visited[next] = true;
            result[index++] = next;
            start = next;
        }

        for (int i = 0; i < len; i++) {
            if (!visited[i]) {
                result[index++] = i;
            }
        }

        return result;
    }

    @Test
    public void visitOrder() {
        int[][] points = {{1, 1}, {1, 4}, {3, 2}, {2, 1}};
        String direction = "LL";
        int[] result = visitOrder(points, direction);
        log.debug("result:{}", result);
    }

    /**
     * 1719. 重构一棵树的方案数
     *
     * <p>给你一个数组 pairs ，其中 pairs[i] = [xi, yi] ，并且满足：
     *
     * <p>pairs 中没有重复元素 xi < yi 令 ways 为满足下面条件的有根树的方案数：
     *
     * <p>树所包含的所有节点值都在 pairs 中。 一个数对 [xi, yi] 出现在 pairs 中 当且仅当 xi 是 yi 的祖先或者 yi 是 xi 的祖先。
     * 注意：构造出来的树不一定是二叉树。 两棵树被视为不同的方案当存在至少一个节点在两棵树中有不同的父节点。
     *
     * <p>请你返回：
     *
     * <p>如果 ways == 0 ，返回 0 。 如果 ways == 1 ，返回 1 。 如果 ways > 1 ，返回 2 。 一棵 有根树
     * 指的是只有一个根节点的树，所有边都是从根往外的方向。
     *
     * <p>我们称从根到一个节点路径上的任意一个节点（除去节点本身）都是该节点的 祖先 。根节点没有祖先。
     *
     * <p>示例 1：
     *
     * <p>输入：pairs = [[1,2],[2,3]] 输出：1 解释：如上图所示，有且只有一个符合规定的有根树。 示例 2：
     *
     * <p>输入：pairs = [[1,2],[2,3],[1,3]] 输出：2 解释：有多个符合规定的有根树，其中三个如上图所示。 示例 3：
     *
     * <p>输入：pairs = [[1,2],[2,3],[2,4],[1,5]] 输出：0 解释：没有符合规定的有根树。
     *
     * <p>提示：
     *
     * <p>1 <= pairs.length <= 105 1 <= xi < yi <= 500 pairs 中的元素互不相同。
     *
     * @param pairs
     * @return
     */
    public int checkWays(int[][] pairs) {
        int result = 1;
        int maxNum = 501;
        int[] degree = new int[maxNum];
        boolean[] visited = new boolean[maxNum];
        int[] pre = new int[maxNum];
        Map<Integer, List<Integer>> graph = new HashMap<>();

        for (int[] pair : pairs) {
            graph.computeIfAbsent(pair[0], k -> new ArrayList<>()).add(pair[1]);
            graph.computeIfAbsent(pair[1], k -> new ArrayList<>()).add(pair[0]);
            degree[pair[0]]++;
            degree[pair[1]]++;
            pre[pair[0]] = -1;
            pre[pair[1]] = -1;
        }
        List<int[]> degreeList = new ArrayList<>();
        for (int i = 0; i < degree.length; i++) {
            if (degree[i] == 0) {
                continue;
            }
            degreeList.add(new int[] {i, degree[i]});
        }
        degreeList.sort((a, b) -> b[1] - a[1]);
        // 所有的祖先 节点 是 degree 最大的 == 所有节点数 - 1
        if (degreeList.get(0)[1] != degreeList.size() - 1) {
            return 0;
        }

        for (int[] degreeNum : degreeList) {
            int u = degreeNum[0];
            for (int v : graph.get(u)) {
                if (degree[u] == degree[v]) {
                    result = 2;
                }
                if (!visited[v]) {
                    if (pre[u] != pre[v]) {
                        return 0;
                    }
                    pre[v] = u;
                }
                visited[u] = true;
            }
        }

        return result;
    }

    /**
     * LCP 24. 数字游戏
     *
     * <p>小扣在秋日市集入口处发现了一个数字游戏。主办方共有 N 个计数器，计数器编号为 0 ~ N-1。每个计数器上分别显示了一个数字，小扣按计数器编号升序将所显示的数字记于数组
     * nums。每个计数器上有两个按钮，分别可以实现将显示数字加一或减一。小扣每一次操作可以选择一个计数器，按下加一或减一按钮。
     *
     * <p>主办方请小扣回答出一个长度为 N 的数组，第 i 个元素(0 <= i < N)表示将 0~i 号计数器 初始 所示数字操作成满足所有条件 nums[a]+1 ==
     * nums[a+1],(0 <= a < i) 的最小操作数。回答正确方可进入秋日市集。
     *
     * <p>由于答案可能很大，请将每个最小操作数对 1,000,000,007 取余。
     *
     * <p>示例 1：
     *
     * <p>输入：nums = [3,4,5,1,6,7]
     *
     * <p>输出：[0,0,0,5,6,7]
     *
     * <p>解释： i = 0，[3] 无需操作 i = 1，[3,4] 无需操作； i = 2，[3,4,5] 无需操作； i = 3，将 [3,4,5,1] 操作成 [3,4,5,6],
     * 最少 5 次操作； i = 4，将 [3,4,5,1,6] 操作成 [3,4,5,6,7], 最少 6 次操作； i = 5，将 [3,4,5,1,6,7] 操作成
     * [3,4,5,6,7,8]，最少 7 次操作； 返回 [0,0,0,5,6,7]。
     *
     * <p>示例 2：
     *
     * <p>输入：nums = [1,2,3,4,5]
     *
     * <p>输出：[0,0,0,0,0]
     *
     * <p>解释：对于任意计数器编号 i 都无需操作。
     *
     * <p>示例 3：
     *
     * <p>输入：nums = [1,1,1,2,3,4]
     *
     * <p>输出：[0,1,2,3,3,3]
     *
     * <p>解释： i = 0，无需操作； i = 1，将 [1,1] 操作成 [1,2] 或 [0,1] 最少 1 次操作； i = 2，将 [1,1,1] 操作成 [1,2,3] 或
     * [0,1,2]，最少 2 次操作； i = 3，将 [1,1,1,2] 操作成 [1,2,3,4] 或 [0,1,2,3]，最少 3 次操作； i = 4，将 [1,1,1,2,3]
     * 操作成 [-1,0,1,2,3]，最少 3 次操作； i = 5，将 [1,1,1,2,3,4] 操作成 [-1,0,1,2,3,4]，最少 3 次操作； 返回
     * [0,1,2,3,3,3]。
     *
     * <p>提示：
     *
     * <p>1 <= nums.length <= 10^5 1 <= nums[i] <= 10^3
     *
     * @param nums
     * @return
     */
    public int[] numsGame(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] -= i;
        }
        log.debug("nums:{}", nums);
        //
        MedianFinder finder = new MedianFinder();
        finder.addNum(nums[0]);
        for (int i = 1; i < n; i++) {
            finder.addNum(nums[i]);
            int median = finder.findMedian2();
            long value =
                    finder.getMinSum()
                            - (long) finder.getMinQueue().size() * (long) median
                            + ((long) finder.getMaxQueue().size() * (long) median
                                    - finder.getMaxSum());
            result[i] = (int) (value % MOD);
        }

        return result;
    }

    @Test
    public void numsGame() {
        int[] nums = {1, 1, 1, 2, 3, 4};
        int[] result = numsGame(nums);
        log.debug("result:{}", result);
    }

    /**
     * 5678. 袋子里最少数目的球
     *
     * <p>给你一个整数数组 nums ，其中 nums[i] 表示第 i 个袋子里球的数目。同时给你一个整数 maxOperations 。
     *
     * <p>你可以进行如下操作至多 maxOperations 次：
     *
     * <p>选择任意一个袋子，并将袋子里的球分到 2 个新的袋子中，每个袋子里都有 正整数 个球。 比方说，一个袋子里有 5 个球，你可以把它们分到两个新袋子里，分别有 1 个和 4
     * 个球，或者分别有 2 个和 3 个球。 你的开销是单个袋子里球数目的 最大值 ，你想要 最小化 开销。
     *
     * <p>请你返回进行上述操作后的最小开销。
     *
     * <p>示例 1：
     *
     * <p>输入：nums = [9], maxOperations = 2 输出：3 解释： - 将装有 9 个球的袋子分成装有 6 个和 3 个球的袋子。[9] -> [6,3] 。 -
     * 将装有 6 个球的袋子分成装有 3 个和 3 个球的袋子。[6,3] -> [3,3,3] 。 装有最多球的袋子里装有 3 个球，所以开销为 3 并返回 3 。 示例 2：
     *
     * <p>输入：nums = [2,4,8,2], maxOperations = 4 输出：2 解释： - 将装有 8 个球的袋子分成装有 4 个和 4 个球的袋子。[2,4,8,2]
     * -> [2,4,4,4,2] 。 - 将装有 4 个球的袋子分成装有 2 个和 2 个球的袋子。[2,4,4,4,2] -> [2,2,2,4,4,2] 。 - 将装有 4
     * 个球的袋子分成装有 2 个和 2 个球的袋子。[2,2,2,4,4,2] -> [2,2,2,2,2,4,2] 。 - 将装有 4 个球的袋子分成装有 2 个和 2
     * 个球的袋子。[2,2,2,2,2,4,2] -> [2,2,2,2,2,2,2,2] 。 装有最多球的袋子里装有 2 个球，所以开销为 2 并返回 2 。 示例 3：
     *
     * <p>输入：nums = [7,17], maxOperations = 2 输出：7
     *
     * <p>提示：
     *
     * <p>1 <= nums.length <= 105 1 <= maxOperations, nums[i] <= 109
     *
     * @param nums
     * @param maxOperations
     * @return
     */
    public int minimumSize(int[] nums, int maxOperations) {
        int len = nums.length;
        if (len == 1) {
            int result = nums[0] / (maxOperations + 1);
            if (nums[0] % (maxOperations + 1) != 0) {
                result++;
            }
            return result;
        }
        int max = 0;
        for (int num : nums) {
            max = Math.max(max, num);
        }

        int result = max;
        // 二分法
        int low = 1, high = max;
        while (low < high) {
            int mid = (low + high) >> 1;
            if (canOperateBySize(nums, mid, maxOperations)) {
                result = mid;
                high = mid;
            } else {
                low = mid + 1;
            }
        }

        return result;
    }

    private boolean canOperateBySize(int[] nums, int targetNum, int opcount) {
        int count = 0;
        for (int num : nums) {
            if (num > targetNum) {

                int needCount = num / targetNum;
                if (num % targetNum == 0) {
                    needCount--;
                }
                count += needCount;
            }
            if (count > opcount) {
                return false;
            }
        }

        return true;
    }

    @Test
    public void minimumSize() {
        int[] nums = {7, 17};
        int maxOperations = 1;
        logResult(minimumSize(nums, maxOperations));
    }

    /**
     * 1697. 检查边长度限制的路径是否存在
     *
     * <p>给你一个 n 个点组成的无向图边集 edgeList ，其中 edgeList[i] = [ui, vi, disi] 表示点 ui 和点 vi 之间有一条长度为 disi
     * 的边。请注意，两个点之间可能有 超过一条边 。
     *
     * <p>给你一个查询数组queries ，其中 queries[j] = [pj, qj, limitj] ，你的任务是对于每个查询 queries[j] ，判断是否存在从 pj 到 qj
     * 的路径，且这条路径上的每一条边都 严格小于 limitj 。
     *
     * <p>请你返回一个 布尔数组 answer ，其中 answer.length == queries.length ，当 queries[j] 的查询结果为 true 时， answer
     * 第 j 个值为 true ，否则为 false 。
     *
     * <p>示例 1：
     *
     * <p>输入：n = 3, edgeList = [[0,1,2],[1,2,4],[2,0,8],[1,0,16]], queries = [[0,1,2],[0,2,5]]
     * 输出：[false,true] 解释：上图为给定的输入数据。注意到 0 和 1 之间有两条重边，分别为 2 和 16 。 对于第一个查询，0 和 1 之间没有小于 2 的边，所以我们返回
     * false 。 对于第二个查询，有一条路径（0 -> 1 -> 2）两条边都小于 5 ，所以这个查询我们返回 true 。 示例 2：
     *
     * <p>输入：n = 5, edgeList = [[0,1,10],[1,2,5],[2,3,9],[3,4,13]], queries = [[0,4,14],[1,4,13]]
     * 输出：[true,false] 解释：上图为给定数据。
     *
     * <p>提示：
     *
     * <p>2 <= n <= 105 1 <= edgeList.length, queries.length <= 105 edgeList[i].length == 3
     * queries[j].length == 3 0 <= ui, vi, pj, qj <= n - 1 ui != vi pj != qj 1 <= disi, limitj <=
     * 109 两个点之间可能有 多条 边。
     *
     * @param n
     * @param edgeList
     * @param queries
     * @return
     */
    public boolean[] distanceLimitedPathsExist(int n, int[][] edgeList, int[][] queries) {
        int len = queries.length;
        // 并查集

        boolean[] result = new boolean[len];
        List<QueryNode> queryNodeList = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            queryNodeList.add(new QueryNode(i, queries[i][0], queries[i][1], queries[i][2]));
        }
        // 按limit 从小到大排序
        queryNodeList.sort(Comparator.comparingInt(a -> a.limit));
        Arrays.sort(edgeList, Comparator.comparingInt(a -> a[2]));

        swapParents = new int[n];

        for (int i = 0; i < swapParents.length; i++) {
            swapParents[i] = i;
        }

        int i = 0;
        for (QueryNode node : queryNodeList) {
            for (; i < edgeList.length; i++) {
                if (edgeList[i][2] < node.limit) {
                    union(edgeList[i][0], edgeList[i][1]);
                } else {
                    break;
                }
            }
            result[node.index] = findParent(node.start) == findParent(node.end);
        }

        return result;
    }

    static class QueryNode {
        int index, start, end, limit;

        public QueryNode(int index, int start, int end, int limit) {
            this.index = index;
            this.start = start;
            this.end = end;
            this.limit = limit;
        }
    }

    /**
     * 1703. 得到连续 K 个 1 的最少相邻交换次数
     *
     * <p>给你一个整数数组 nums 和一个整数 k 。 nums 仅包含 0 和 1 。每一次移动，你可以选择 相邻 两个数字并将它们交换。
     *
     * <p>请你返回使 nums 中包含 k 个 连续 1 的 最少 交换次数。
     *
     * <p>示例 1：
     *
     * <p>输入：nums = [1,0,0,1,0,1], k = 2 输出：1 解释：在第一次操作时，nums 可以变成 [1,0,0,0,1,1] 得到连续两个 1 。 示例 2：
     *
     * <p>输入：nums = [1,0,0,0,0,0,1,1], k = 3 输出：5 解释：通过 5 次操作，最左边的 1 可以移到右边直到 nums 变为
     * [0,0,0,0,0,1,1,1] 。 示例 3：
     *
     * <p>输入：nums = [1,1,0,1], k = 2 输出：0 解释：nums 已经有连续 2 个 1 了。
     *
     * <p>提示：
     *
     * <p>1 <= nums.length <= 105 nums[i] 要么是 0 ，要么是 1 。 1 <= k <= sum(nums)
     *
     * @param nums
     * @param k
     * @return
     */
    public int minMoves(int[] nums, int k) {
        int len = nums.length;
        // 把所有的“1”的index找出来，对这些“index”做size为k的滑窗，不要对nums做滑窗。问题就成了滑窗内求中位数距离和
        List<Integer> indexList = new ArrayList<>();
        int count = -1;
        for (int i = 0; i < len; i++) {
            if (nums[i] == 1) {
                count++;
                indexList.add(i - count);
            }
        }
        log.debug("list:{}", indexList);
        int[] sums = new int[indexList.size() + 1];
        for (int i = 0; i < indexList.size(); i++) {
            sums[i + 1] = sums[i] + indexList.get(i);
        }
        log.debug("sums:{}", sums);
        // 滑动窗口, 记录距离差
        int min = Integer.MAX_VALUE;
        for (int i = 0; i + k - 1 < indexList.size(); i++) {
            int j = i + k - 1;
            int mid = (i + j) >> 1;
            int leftNum = indexList.get(mid) * (mid - i) - (sums[mid] - sums[i]);
            int rightNum = sums[j + 1] - sums[mid + 1] - indexList.get(mid) * (j - mid);
            min = Math.min(min, leftNum + rightNum);
        }

        return min;
    }

    @Test
    public void minMoves() {
        int[] nums = {1, 0, 0, 1, 0, 1};
        int k = 2;
        logResult(minMoves(nums, k));
    }

    /**
     * 1713. 得到子序列的最少操作次数
     *
     * <p>给你一个数组 target ，包含若干 互不相同 的整数，以及另一个整数数组 arr ，arr 可能 包含重复元素。
     *
     * <p>每一次操作中，你可以在 arr 的任意位置插入任一整数。比方说，如果 arr = [1,4,1,2] ，那么你可以在中间添加 3 得到 [1,4,3,1,2]
     * 。你可以在数组最开始或最后面添加整数。
     *
     * <p>请你返回 最少 操作次数，使得 target 成为 arr 的一个子序列。
     *
     * <p>一个数组的 子序列 指的是删除原数组的某些元素（可能一个元素都不删除），同时不改变其余元素的相对顺序得到的数组。比方说，[2,7,4] 是 [4,2,3,7,2,1,4]
     * 的子序列（加粗元素），但 [2,4,2] 不是子序列。
     *
     * <p>示例 1：
     *
     * <p>输入：target = [5,1,3], arr = [9,4,2,3,4] 输出：2 解释：你可以添加 5 和 1 ，使得 arr 变为 [5,9,4,1,2,3,4]
     * ，target 为 arr 的子序列。 示例 2：
     *
     * <p>输入：target = [6,4,8,1,3,2], arr = [4,7,6,2,3,8,6,1] 输出：3
     *
     * <p>提示：
     *
     * <p>1 <= target.length, arr.length <= 105 1 <= target[i], arr[i] <= 109 target 不包含任何重复元素。
     *
     * @param target
     * @param arr
     * @return
     */
    public int minOperations(int[] target, int[] arr) {
        Map<Integer, Integer> targetIndex = new HashMap<>();
        for (int i = 0; i < target.length; i++) {
            targetIndex.put(target[i], i);
        }
        int len = arr.length;
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            int index = targetIndex.getOrDefault(arr[i], -1);
            if (index != -1) {
                list.add(index);
            }
        }
        log.debug("list:{}", list);
        // 求最长上升子序列
        List<Integer> dpList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if (dpList.isEmpty() || list.get(i) > dpList.get(dpList.size() - 1)) {
                dpList.add(list.get(i));
            } else {
                int left = 0, right = dpList.size() - 1;
                while (left < right) {
                    int mid = (left + right) >> 1;
                    if (dpList.get(mid) >= list.get(i)) {
                        right = mid;
                    } else {
                        left = mid + 1;
                    }
                }
                dpList.set(left, list.get(i));
            }
        }
        return target.length - dpList.size();
    }

    @Test
    public void minOperations() {
        // int[] target = {5, 1, 3}, arr = {9, 4, 2, 3, 4};
        int[] target = {6, 4, 8, 1, 3, 2}, arr = {4, 7, 6, 2, 3, 8, 6, 1};
        logResult(minOperations(target, arr));
    }

    /**
     * 1751. 最多可以参加的会议数目 II
     *
     * <p>给你一个 events 数组，其中 events[i] = [startDayi, endDayi, valuei] ，表示第 i 个会议在 startDayi 天开始，第
     * endDayi 天结束，如果你参加这个会议，你能得到价值 valuei 。同时给你一个整数 k 表示你能参加的最多会议数目。
     *
     * <p>你同一时间只能参加一个会议。如果你选择参加某个会议，那么你必须 完整
     * 地参加完这个会议。会议结束日期是包含在会议内的，也就是说你不能同时参加一个开始日期与另一个结束日期相同的两个会议。
     *
     * <p>请你返回能得到的会议价值 最大和 。
     *
     * <p>示例 1：
     *
     * <p>输入：events = [[1,2,4],[3,4,3],[2,3,1]], k = 2 输出：7 解释：选择绿色的活动会议 0 和 1，得到总价值和为 4 + 3 = 7 。
     * 示例 2：
     *
     * <p>输入：events = [[1,2,4],[3,4,3],[2,3,10]], k = 2 输出：10 解释：参加会议 2 ，得到价值和为 10 。
     * 你没法再参加别的会议了，因为跟会议 2 有重叠。你 不 需要参加满 k 个会议。 示例 3：
     *
     * <p>输入：events = [[1,1,1],[2,2,2],[3,3,3],[4,4,4]], k = 3 输出：9 解释：尽管会议互不重叠，你只能参加 3
     * 个会议，所以选择价值最大的 3 个会议。
     *
     * <p>提示：
     *
     * <p>1 <= k <= events.length 1 <= k * events.length <= 106 1 <= startDayi <= endDayi <= 109 1
     * <= valuei <= 106
     *
     * @param events
     * @param k
     * @return
     */
    public int maxValue(int[][] events, int k) {

        int len = events.length;
        Arrays.sort(events, Comparator.comparingInt(a -> a[1]));

        int[][] dp = new int[len + 1][k + 1];

        for (int i = 0; i < len; i++) {
            int start = events[i][0], end = events[i][1], val = events[i][2];
            int prevIdx = 0;
            for (int j = i; j >= 0; j--) {
                if (events[j][1] < start) {
                    prevIdx = j + 1;
                    break;
                }
            }
            for (int j = 1; j <= k; j++) {
                dp[i + 1][j] = Math.max(dp[i][j], dp[prevIdx][j - 1] + val);
            }
        }

        return dp[len][k];
    }

    @Test
    public void maxValue() {
        int[][] events = {{1, 2, 4}, {3, 4, 3}, {2, 3, 1}};
        int k = 2;
        logResult(maxValue(events, k));
    }

    /**
     * 995. K 连续位的最小翻转次数
     *
     * <p>在仅包含 0 和 1 的数组 A 中，一次 K 位翻转包括选择一个长度为 K 的（连续）子数组，同时将子数组中的每个 0 更改为 1，而每个 1 更改为 0。
     *
     * <p>返回所需的 K 位翻转的最小次数，以便数组没有值为 0 的元素。如果不可能，返回 -1。
     *
     * <p>示例 1：
     *
     * <p>输入：A = [0,1,0], K = 1 输出：2 解释：先翻转 A[0]，然后翻转 A[2]。 示例 2：
     *
     * <p>输入：A = [1,1,0], K = 2 输出：-1 解释：无论我们怎样翻转大小为 2 的子数组，我们都不能使数组变为 [1,1,1]。 示例 3：
     *
     * <p>输入：A = [0,0,0,1,0,1,1,0], K = 3 输出：3 解释： 翻转 A[0],A[1],A[2]: A变成 [1,1,1,1,0,1,1,0] 翻转
     * A[4],A[5],A[6]: A变成 [1,1,1,1,1,0,0,0] 翻转 A[5],A[6],A[7]: A变成 [1,1,1,1,1,1,1,1]
     *
     * <p>提示：
     *
     * <p>1 <= A.length <= 30000 1 <= K <= A.length
     *
     * @param A
     * @param K
     * @return
     */
    public int minKBitFlips(int[] A, int K) {
        Queue<Integer> queue = new LinkedList<>();
        int result = 0, len = A.length;
        for (int i = 0; i < len; i++) {
            if (!queue.isEmpty() && i > queue.peek() + K - 1) {
                queue.poll();
            }
            if (queue.size() % 2 == A[i]) {
                if (i + K > A.length) {
                    return -1;
                }
                queue.add(i);
                result++;
            }
        }
        return result;
    }

    /**
     * 1755. 最接近目标值的子序列和
     *
     * <p>给你一个整数数组 nums 和一个目标值 goal 。
     *
     * <p>你需要从 nums 中选出一个子序列，使子序列元素总和最接近 goal 。也就是说，如果子序列元素和为 sum ，你需要 最小化绝对差 abs(sum - goal) 。
     *
     * <p>返回 abs(sum - goal) 可能的 最小值 。
     *
     * <p>注意，数组的子序列是通过移除原始数组中的某些元素（可能全部或无）而形成的数组。
     *
     * <p>示例 1：
     *
     * <p>输入：nums = [5,-7,3,5], goal = 6 输出：0 解释：选择整个数组作为选出的子序列，元素和为 6 。 子序列和与目标值相等，所以绝对差为 0 。 示例 2：
     *
     * <p>输入：nums = [7,-9,15,-2], goal = -5 输出：1 解释：选出子序列 [7,-9,-2] ，元素和为 -4 。 绝对差为 abs(-4 - (-5)) =
     * abs(1) = 1 ，是可能的最小值。 示例 3：
     *
     * <p>输入：nums = [1,2,3], goal = -7 输出：7
     *
     * <p>提示：
     *
     * <p>1 <= nums.length <= 40 -107 <= nums[i] <= 107 -109 <= goal <= 109
     *
     * @param nums
     * @param goal
     * @return
     */
    public int minAbsDifference(int[] nums, int goal) {
        //  1 <= nums.length <= 40
        // 首先把数组分成两个部分，然后暴力枚举子集的和，得到所有的和的可能；
        // 将其中一个部分的和暴力排序；
        // 最后，针对另一个部分的每一个情况，调用 lower_bound 函数找到第一个大于等于 goal 的和，然后将指针左移一个来找到小于等于 goal 的最大值。
        int leftLen = nums.length >> 1;
        int rightLen = nums.length - leftLen;
        // 所有组合
        int[] leftNums = new int[1 << leftLen], rightNums = new int[1 << rightLen];
        for (int i = 0; i < leftNums.length; i++) {
            for (int j = 0; j < leftLen; j++) {
                if ((i & (1 << j)) > 0) {
                    // 所有组合
                    leftNums[i] += nums[j];
                }
            }
        }

        for (int i = 0; i < rightNums.length; i++) {
            for (int j = 0; j < rightLen; j++) {
                if ((i & (1 << j)) > 0) {
                    // 所有组合
                    rightNums[i] += nums[leftLen + j];
                }
            }
        }
        // 排序 然后使用二分查找
        Arrays.sort(rightNums);
        int min = Integer.MAX_VALUE;
        for (int num : leftNums) {
            int target = goal - num;
            if (target == 0) {
                return 0;
            }
            min = Math.min(min, Math.abs(target));

            int idx = binarySearch(rightNums, target);
            min = Math.min(min, Math.abs(target - rightNums[idx]));
            if (idx < rightNums.length - 1) {
                min = Math.min(min, Math.abs(target - rightNums[idx + 1]));
            }
        }
        // e
        return min;
    }

    private int binarySearch(int[] nums, int target) {
        int low = 0, high = nums.length - 1;
        int result = 0;
        while (low < high) {
            int mid = (low + high) >> 1;
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[mid] < target) {
                low = mid + 1;
                result = mid;
            } else if (nums[mid] > target) {
                high = mid;
            }
        }
        return result;
    }

    @Test
    public void minAbsDifference() {
        int[] nums = {
            9152249, 8464156, -2493402, 8990685, -7257152, -1050240, 2243737, -82901, 8939692
        };
        int goal = 26915229;
        logResult(minAbsDifference(nums, goal));
    }

    /**
     * 5686. 移动所有球到每个盒子所需的最小操作数
     *
     * <p>有 n 个盒子。给你一个长度为 n 的二进制字符串 boxes ，其中 boxes[i] 的值为 '0' 表示第 i 个盒子是 空 的，而 boxes[i] 的值为 '1'
     * 表示盒子里有 一个 小球。
     *
     * <p>在一步操作中，你可以将 一个 小球从某个盒子移动到一个与之相邻的盒子中。第 i 个盒子和第 j 个盒子相邻需满足 abs(i - j) == 1
     * 。注意，操作执行后，某些盒子中可能会存在不止一个小球。
     *
     * <p>返回一个长度为 n 的数组 answer ，其中 answer[i] 是将所有小球移动到第 i 个盒子所需的 最小 操作数。
     *
     * <p>每个 answer[i] 都需要根据盒子的 初始状态 进行计算。
     *
     * <p>示例 1：
     *
     * <p>输入：boxes = "110" 输出：[1,1,3] 解释：每个盒子对应的最小操作数如下： 1) 第 1 个盒子：将一个小球从第 2 个盒子移动到第 1 个盒子，需要 1
     * 步操作。 2) 第 2 个盒子：将一个小球从第 1 个盒子移动到第 2 个盒子，需要 1 步操作。 3) 第 3 个盒子：将一个小球从第 1 个盒子移动到第 3 个盒子，需要 2
     * 步操作。将一个小球从第 2 个盒子移动到第 3 个盒子，需要 1 步操作。共计 3 步操作。 示例 2：
     *
     * <p>输入：boxes = "001011" 输出：[11,8,5,4,3,4]
     *
     * <p>提示：
     *
     * <p>n == boxes.length 1 <= n <= 2000 boxes[i] 为 '0' 或 '1'
     *
     * @param boxes
     * @return
     */
    public int[] minOperations(String boxes) {
        int n = boxes.length();
        int[] result = new int[n];
        // 滑动窗口

        char[] chars = boxes.toCharArray();
        int leftCount = 0;
        int[] leftNums = new int[n], rightNums = new int[n];
        for (int i = 0; i < n; i++) {
            if (i > 0) {
                leftNums[i] = leftNums[i - 1] + leftCount;
            }

            if (chars[i] == '1') {
                leftCount++;
            }
        }
        int rightCount = 0;
        for (int i = n - 1; i >= 0; i--) {
            if (i < n - 1) {
                rightNums[i] = rightNums[i + 1] + rightCount;
            }
            if (chars[i] == '1') {
                rightCount++;
            }
        }
        log.debug("leftNums:{}", leftNums);
        log.debug("rightNums:{}", rightNums);

        for (int i = 0; i < n; i++) {
            result[i] = leftNums[i] + rightNums[i];
        }

        return result;
    }

    @Test
    public void minOperations2() {
        String boxes = "001011";
        int[] result = minOperations(boxes);
        log.debug("result:{}", result);
    }

    /**
     * 5687. 执行乘法运算的最大分数
     *
     * <p>给你两个长度分别 n 和 m 的整数数组 nums 和 multipliers ，其中 n >= m ，数组下标 从 1 开始 计数。
     *
     * <p>初始时，你的分数为 0 。你需要执行恰好 m 步操作。在第 i 步操作（从 1 开始 计数）中，需要：
     *
     * <p>选择数组 nums 开头处或者末尾处 的整数 x 。 你获得 multipliers[i] * x 分，并累加到你的分数中。 将 x 从数组 nums 中移除。 在执行 m
     * 步操作后，返回 最大 分数。
     *
     * <p>示例 1：
     *
     * <p>输入：nums = [1,2,3], multipliers = [3,2,1] 输出：14 解释：一种最优解决方案如下： - 选择末尾处的整数 3 ，[1,2,3] ，得 3 *
     * 3 = 9 分，累加到分数中。 - 选择末尾处的整数 2 ，[1,2] ，得 2 * 2 = 4 分，累加到分数中。 - 选择末尾处的整数 1 ，[1] ，得 1 * 1 = 1
     * 分，累加到分数中。 总分数为 9 + 4 + 1 = 14 。 示例 2：
     *
     * <p>输入：nums = [-5,-3,-3,-2,7,1], multipliers = [-10,-5,3,4,6] 输出：102 解释：一种最优解决方案如下： - 选择开头处的整数
     * -5 ，[-5,-3,-3,-2,7,1] ，得 -5 * -10 = 50 分，累加到分数中。 - 选择开头处的整数 -3 ，[-3,-3,-2,7,1] ，得 -3 * -5 =
     * 15 分，累加到分数中。 - 选择开头处的整数 -3 ，[-3,-2,7,1] ，得 -3 * 3 = -9 分，累加到分数中。 - 选择末尾处的整数 1 ，[-2,7,1] ，得 1
     * * 4 = 4 分，累加到分数中。 - 选择末尾处的整数 7 ，[-2,7] ，得 7 * 6 = 42 分，累加到分数中。 总分数为 50 + 15 - 9 + 4 + 42 =
     * 102 。
     *
     * <p>提示：
     *
     * <p>n == nums.length m == multipliers.length 1 <= m <= 103 m <= n <= 105 -1000 <= nums[i],
     * multipliers[i] <= 1000
     *
     * @param nums
     * @param multipliers
     * @return
     */
    public int maximumScore(int[] nums, int[] multipliers) {
        int n = nums.length, m = multipliers.length;
        // 动态规划

        int result = Integer.MIN_VALUE;
        int[] dp = new int[m + 1];
        for (int k = 1; k <= m; k++) {
            int multi = multipliers[k - 1];
            //  前面取了i个数，后面取了k-i个数
            for (int i = k; i >= 0; i--) {
                int num = Integer.MIN_VALUE;
                if (i > 0) {
                    num = Math.max(num, dp[i - 1] + nums[i - 1] * multi);
                }
                if (i < k) {
                    num = Math.max(num, dp[i] + nums[n - k + i] * multi);
                }
                dp[i] = num;
                if (k == m) {
                    result = Math.max(result, dp[i]);
                }
            }
        }

        return result;
    }

    @Test
    public void maximumScore() {
        // [        // [83,315,-442,-714,461,920,-737,-93,-818,-760,558,-584,-358,-228,-220]
        int[]
                nums =
                        {
                            -854, -941, 10, 299, 995, -346, 294, -393, 351, -76, 210, 897, -651,
                            920, 624, 969, -629, 985, -695, 236, 637, -901, -817, 546, -69, 192,
                            -377, 251, 542, -316, -879, -764, -560, 927, 629, 877, 42, 381, 367,
                            -549, 602, 139, -312, -281, 105, 690, -376, -705, -906, 85, -608, 639,
                            752, 770, -139, -601, 341, 61, 969, 276, 176, -715, -545, 471, -170,
                            -126, 596, -737, 130
                        },
                multipliers =
                        {
                            83, 315, -442, -714, 461, 920, -737, -93, -818, -760, 558, -584, -358,
                            -228, -220
                        };
        // 3068054 3040819
        logResult(maximumScore(nums, multipliers));
    }

    /**
     * 5669. 通过连接另一个数组的子数组得到一个数组
     *
     * <p>给你一个长度为 n 的二维整数数组 groups ，同时给你一个整数数组 nums 。
     *
     * <p>你是否可以从 nums 中选出 n 个 不相交 的子数组，使得第 i 个子数组与 groups[i] （下标从 0 开始）完全相同，且如果 i > 0 ，那么第 (i-1)
     * 个子数组在 nums 中出现的位置在第 i 个子数组前面。（也就是说，这些子数组在 nums 中出现的顺序需要与 groups 顺序相同）
     *
     * <p>如果你可以找出这样的 n 个子数组，请你返回 true ，否则返回 false 。
     *
     * <p>如果不存在下标为 k 的元素 nums[k] 属于不止一个子数组，就称这些子数组是 不相交 的。子数组指的是原数组中连续元素组成的一个序列。
     *
     * <p>示例 1：
     *
     * <p>输入：groups = [[1,-1,-1],[3,-2,0]], nums = [1,-1,0,1,-1,-1,3,-2,0] 输出：true 解释：你可以分别在 nums
     * 中选出第 0 个子数组 [1,-1,0,1,-1,-1,3,-2,0] 和第 1 个子数组 [1,-1,0,1,-1,-1,3,-2,0] 。
     * 这两个子数组是不相交的，因为它们没有任何共同的元素。 示例 2：
     *
     * <p>输入：groups = [[10,-2],[1,2,3,4]], nums = [1,2,3,4,10,-2] 输出：false 解释：选择子数组 [1,2,3,4,10,-2]
     * 和 [1,2,3,4,10,-2] 是不正确的，因为它们出现的顺序与 groups 中顺序不同。 [10,-2] 必须出现在 [1,2,3,4] 之前。 示例 3：
     *
     * <p>输入：groups = [[1,2,3],[3,4]], nums = [7,7,1,2,3,4,7,7] 输出：false 解释：选择子数组 [7,7,1,2,3,4,7,7]
     * 和 [7,7,1,2,3,4,7,7] 是不正确的，因为它们不是不相交子数组。 它们有一个共同的元素 nums[4] （下标从 0 开始）。
     *
     * <p>提示：
     *
     * <p>groups.length == n 1 <= n <= 103 1 <= groups[i].length, sum(groups[i].length) <= 103 1 <=
     * nums.length <= 103 -107 <= groups[i][j], nums[k] <= 107
     *
     * @param groups
     * @param nums
     * @return
     */
    public boolean canChoose(int[][] groups, int[] nums) {

        // 双指针
        int len1 = nums.length, len2 = groups.length;

        int idx = 0;
        for (int i = 0; i < len1; i++) {
            int num = nums[i];
            if (num != groups[idx][0]) {
                continue;
            }
            int len = groups[idx].length;
            int j = 1;
            for (; j < len && i + j < len1; j++) {
                if (nums[i + j] != groups[idx][j]) {
                    break;
                }
            }

            if (j == len) {
                idx++;
                i += len - 1;
            }
            if (idx == len2) {
                break;
            }
        }
        return idx == len2;
    }

    @Test
    public void canChoose() {
        int[][] groups = {
            {
                1363850, 6300176, 8430440, -9635380, -1343994, -9365453, 5210548, -1702094, 8165619,
                4988596, -1524607, -4244825, -7838619, -1604017, 8054294, 3277839
            },
            {-9180987, 4743777, 9146280, -7908834, 1909925, 4434157},
            {3981590}
        };
        int[] nums = {
            -1702094, -9635380, 5210548, 8165619, 8054294, 1363850, 6300176, 8430440, -9635380,
            -1343994, -9365453, 5210548, -1702094, 8165619, 4988596, -1524607, -4244825, -7838619,
            -1604017, 8054294, 3277839, -1343994, -1524607, 1363850, 6300176, 8165619, -9180987,
            4743777, 9146280, -7908834
        };
        logResult(canChoose(groups, nums));
    }

    /**
     * 5671. 地图中的最高点
     *
     * <p>给你一个大小为 m x n 的整数矩阵 isWater ，它代表了一个由 陆地 和 水域 单元格组成的地图。
     *
     * <p>如果 isWater[i][j] == 0 ，格子 (i, j) 是一个 陆地 格子。 如果 isWater[i][j] == 1 ，格子 (i, j) 是一个 水域 格子。
     * 你需要按照如下规则给每个单元格安排高度：
     *
     * <p>每个格子的高度都必须是非负的。 如果一个格子是是 水域 ，那么它的高度必须为 0 。 任意相邻的格子高度差 至多 为 1
     * 。当两个格子在正东、南、西、北方向上相互紧挨着，就称它们为相邻的格子。（也就是说它们有一条公共边） 找到一种安排高度的方案，使得矩阵中的最高高度值 最大 。
     *
     * <p>请你返回一个大小为 m x n 的整数矩阵 height ，其中 height[i][j] 是格子 (i, j) 的高度。如果有多种解法，请返回 任意一个 。
     *
     * <p>示例 1：
     *
     * <p>输入：isWater = [[0,1],[0,0]] 输出：[[1,0],[2,1]] 解释：上图展示了给各个格子安排的高度。 蓝色格子是水域格，绿色格子是陆地格。 示例 2：
     *
     * <p>输入：isWater = [[0,0,1],[1,0,0],[0,0,0]] 输出：[[1,1,0],[0,1,1],[1,2,2]] 解释：所有安排方案中，最高可行高度为 2 。
     * 任意安排方案中，只要最高高度为 2 且符合上述规则的，都为可行方案。
     *
     * <p>提示：
     *
     * <p>m == isWater.length n == isWater[i].length 1 <= m, n <= 1000 isWater[i][j] 要么是 0 ，要么是 1 。
     * 至少有 1 个水域格子。
     *
     * @param isWater
     * @return
     */
    public int[][] highestPeak(int[][] isWater) {
        int m = isWater.length, n = isWater[0].length;

        // 广度优先遍历
        Queue<PeakNode> queue = new LinkedList<>();
        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (isWater[i][j] == 1) {
                    queue.offer(new PeakNode(i, j, 0));
                    visited[i][j] = true;
                }
            }
        }
        int[][] result = new int[m][n];

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                PeakNode node = queue.poll();
                int row = node.row, col = node.col;

                int height = node.height;
                result[row][col] = height;

                for (int j = 0; j < 4; j++) {
                    int nextRow = row + DIR_ROW[j], nextCol = col + DIR_COL[j];
                    if (inArea(nextRow, nextCol, m, n) && !visited[nextRow][nextCol]) {
                        queue.offer(new PeakNode(nextRow, nextCol, height + 1));
                        visited[nextRow][nextCol] = true;
                    }
                }
            }
        }

        return result;
    }

    static class PeakNode {
        int row, col, height;

        public PeakNode(int row, int col, int height) {
            this.row = row;
            this.col = col;
            this.height = height;
        }
    }

    @Test
    public void highestPeak() {
        int[][] isWater = {{0, 0, 1}, {1, 0, 0}, {0, 0, 0}};
        logResult(highestPeak(isWater));
    }

    /**
     * 1521. 找到最接近目标值的函数值
     *
     * <p>Winston 构造了一个如上所示的函数 func 。他有一个整数数组 arr 和一个整数 target ，他想找到让 |func(arr, l, r) - target| 最小的
     * l 和 r 。
     *
     * <p>请你返回 |func(arr, l, r) - target| 的最小值。
     *
     * <p>请注意， func 的输入参数 l 和 r 需要满足 0 <= l, r < arr.length 。
     *
     * <p>示例 1：
     *
     * <p>输入：arr = [9,12,3,7,15], target = 5 输出：2 解释：所有可能的 [l,r] 数对包括
     * [[0,0],[1,1],[2,2],[3,3],[4,4],[0,1],[1,2],[2,3],[3,4],[0,2],[1,3],[2,4],[0,3],[1,4],[0,4]]，
     * Winston 得到的相应结果为 [9,12,3,7,15,8,0,3,7,0,0,3,0,0,0] 。最接近 5 的值是 7 和 3，所以最小差值为 2 。 示例 2：
     *
     * <p>输入：arr = [1000000,1000000,1000000], target = 1 输出：999999 解释：Winston 输入函数的所有可能 [l,r]
     * 数对得到的函数值都为 1000000 ，所以最小差值为 999999 。 示例 3：
     *
     * <p>输入：arr = [1,2,4,8,16], target = 0 输出：0
     *
     * <p>提示：
     *
     * <p>1 <= arr.length <= 10^5 1 <= arr[i] <= 10^6 0 <= target <= 10^7
     *
     * @param arr
     * @param target
     * @return
     */
    public int closestToTarget(int[] arr, int target) {
        int result = Math.abs(arr[0] - target);
        Set<Integer> nums = new HashSet<>();
        nums.add(arr[0]);
        for (int num : arr) {
            Set<Integer> nextNums = new HashSet<>();
            nextNums.add(num);

            for (int lastNum : nums) {
                int curNum = lastNum & num;
                nextNums.add(curNum);
                result = Math.min(result, Math.abs(curNum - target));
            }

            nums = nextNums;
        }

        return result;
    }

    /**
     * 1526. 形成目标数组的子数组最少增加次数
     *
     * <p>给你一个整数数组 target 和一个数组 initial ，initial 数组与 target 数组有同样的维度，且一开始全部为 0 。
     *
     * <p>请你返回从 initial 得到 target 的最少操作次数，每次操作需遵循以下规则：
     *
     * <p>在 initial 中选择 任意 子数组，并将子数组中每个元素增加 1 。 答案保证在 32 位有符号整数以内。
     *
     * <p>示例 1：
     *
     * <p>输入：target = [1,2,3,2,1] 输出：3 解释：我们需要至少 3 次操作从 intial 数组得到 target 数组。 [0,0,0,0,0] 将下标为 0 到
     * 4 的元素（包含二者）加 1 。 [1,1,1,1,1] 将下标为 1 到 3 的元素（包含二者）加 1 。 [1,2,2,2,1] 将下表为 2 的元素增加 1 。
     * [1,2,3,2,1] 得到了目标数组。 示例 2：
     *
     * <p>输入：target = [3,1,1,2] 输出：4 解释：(initial)[0,0,0,0] -> [1,1,1,1] -> [1,1,1,2] -> [2,1,1,2] ->
     * [3,1,1,2] (target) 。 示例 3：
     *
     * <p>输入：target = [3,1,5,4,2] 输出：7 解释：(initial)[0,0,0,0,0] -> [1,1,1,1,1] -> [2,1,1,1,1] ->
     * [3,1,1,1,1] -> [3,1,2,2,2] -> [3,1,3,3,2] -> [3,1,4,4,2] -> [3,1,5,4,2] (target)。 示例 4：
     *
     * <p>输入：target = [1,1,1,1] 输出：1
     *
     * <p>提示：
     *
     * <p>1 <= target.length <= 10^5 1 <= target[i] <= 10^5
     *
     * @param target
     * @return
     */
    public int minNumberOperations(int[] target) {
        int len = target.length;
        // dp[i] 表示在前 i 个数时需要的操作总共是多少
        int[] dp = new int[len];
        dp[0] = target[0];
        for (int i = 1; i < len; i++) {
            if (target[i] <= target[i - 1]) {
                dp[i] = dp[i - 1];
            } else {
                dp[i] = dp[i - 1] + target[i] - target[i - 1];
            }
        }

        return dp[len - 1];
    }

    /**
     * 1559. 二维网格图中探测环
     *
     * <p>给你一个二维字符网格数组 grid ，大小为 m x n ，你需要检查 grid 中是否存在 相同值 形成的环。
     *
     * <p>一个环是一条开始和结束于同一个格子的长度 大于等于 4 的路径。对于一个给定的格子，你可以移动到它上、下、左、右四个方向相邻的格子之一，可以移动的前提是这两个格子有 相同的值 。
     *
     * <p>同时，你也不能回到上一次移动时所在的格子。比方说，环 (1, 1) -> (1, 2) -> (1, 1) 是不合法的，因为从 (1, 2) 移动到 (1, 1)
     * 回到了上一次移动时的格子。
     *
     * <p>如果 grid 中有相同值形成的环，请你返回 true ，否则返回 false 。
     *
     * <p>示例 1：
     *
     * <p>输入：grid = [["a","a","a","a"],["a","b","b","a"],["a","b","b","a"],["a","a","a","a"]]
     * 输出：true 解释：如下图所示，有 2 个用不同颜色标出来的环：
     *
     * <p>示例 2：
     *
     * <p>输入：grid = [["c","c","c","a"],["c","d","c","c"],["c","c","e","c"],["f","c","c","c"]]
     * 输出：true 解释：如下图所示，只有高亮所示的一个合法环：
     *
     * <p>示例 3：
     *
     * <p>输入：grid = [["a","b","b"],["b","z","b"],["b","b","a"]] 输出：false
     *
     * <p>提示：
     *
     * <p>m == grid.length n == grid[i].length 1 <= m <= 500 1 <= n <= 500 grid 只包含小写英文字母。
     *
     * @param grid
     * @return
     */
    public boolean containsCycle(char[][] grid) {
        this.M = grid.length;
        this.N = grid[0].length;
        this.boxGrid = grid;

        this.visited = new boolean[M][N];
        // 深度优先遍历
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (visited[i][j]) {
                    continue;
                }
                if (dfsContainsCycle(grid[i][j], -1, -1, i, j)) {
                    return true;
                }
            }
        }

        return false;
    }

    boolean[][] visited;

    private boolean dfsContainsCycle(char c, int rowIdx, int colIdx, int row, int col) {
        if (!inArea(row, col, M, N)) {
            return false;
        }
        if (boxGrid[row][col] != c) {
            return false;
        }
        if (visited[row][col]) {
            return true;
        }
        // 回溯
        visited[row][col] = true;
        for (int i = 0; i < 4; i++) {
            int nextRow = row + DIR_ROW[i], nextCol = col + DIR_COL[i];
            if (!inArea(nextRow, nextCol, M, N)) {
                continue;
            }
            if (rowIdx == nextRow && colIdx == nextCol) {
                continue;
            }
            if (dfsContainsCycle(c, row, col, nextRow, nextCol)) {
                return true;
            }
        }

        return false;
    }

    @Test
    public void containsCycle() {
        char[][] grid = {{'a', 'b', 'b'}, {'b', 'z', 'b'}, {'b', 'b', 'a'}};
        logResult(containsCycle(grid));
    }
}
