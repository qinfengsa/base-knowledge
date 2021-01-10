package com.qinfengsa.structure.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;

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
}
