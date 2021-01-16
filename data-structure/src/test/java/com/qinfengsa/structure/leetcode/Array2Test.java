package com.qinfengsa.structure.leetcode;

import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
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
}
