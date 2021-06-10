package com.qinfengsa.structure.trie;

import java.util.Objects;
import lombok.extern.slf4j.Slf4j;

/**
 * 字典树
 *
 * @author qinfengsa
 * @date 2021/6/9 10:19
 */
@Slf4j
public class TrieMain {

    /**
     * 1803. 统计异或值在范围内的数对有多少
     *
     * <p>给你一个整数数组 nums （下标 从 0 开始 计数）以及两个整数：low 和 high ，请返回 漂亮数对 的数目。
     *
     * <p>漂亮数对 是一个形如 (i, j) 的数对，其中 0 <= i < j < nums.length 且 low <= (nums[i] XOR nums[j]) <= high 。
     *
     * <p>示例 1：
     *
     * <p>输入：nums = [1,4,2,7], low = 2, high = 6 输出：6 解释：所有漂亮数对 (i, j) 列出如下： - (0, 1): nums[0] XOR
     * nums[1] = 5 - (0, 2): nums[0] XOR nums[2] = 3 - (0, 3): nums[0] XOR nums[3] = 6 - (1, 2):
     * nums[1] XOR nums[2] = 6 - (1, 3): nums[1] XOR nums[3] = 3 - (2, 3): nums[2] XOR nums[3] = 5
     * 示例 2：
     *
     * <p>输入：nums = [9,8,4,2,1], low = 5, high = 14 输出：8 解释：所有漂亮数对 (i, j) 列出如下： - (0, 2): nums[0]
     * XOR nums[2] = 13 - (0, 3): nums[0] XOR nums[3] = 11 - (0, 4): nums[0] XOR nums[4] = 8 - (1,
     * 2): nums[1] XOR nums[2] = 12 - (1, 3): nums[1] XOR nums[3] = 10 - (1, 4): nums[1] XOR nums[4]
     * = 9 - (2, 3): nums[2] XOR nums[3] = 6 - (2, 4): nums[2] XOR nums[4] = 5
     *
     * <p>提示：
     *
     * <p>1 <= nums.length <= 2 * 104 1 <= nums[i] <= 2 * 104 1 <= low <= high <= 2 * 104
     *
     * @param nums
     * @param low
     * @param high
     * @return
     */
    public int countPairs(int[] nums, int low, int high) {

        for (int num : nums) {
            insert(num);
        }
        int lowCount = 0, highCount = 0;
        for (int num : nums) {
            lowCount += query(num, low);
            highCount += query(num, high + 1);
        }

        return (highCount - lowCount) >> 1;
    }

    private NumTrieNode root = new NumTrieNode();

    static class NumTrieNode {

        private int count;
        // 所有的儿子节点
        private final NumTrieNode[] children;

        NumTrieNode() {
            count = 0;
            // 两个节点 0 1
            children = new NumTrieNode[2];
        }
    }

    private void insert(int num) {
        NumTrieNode node = root;
        // 最大值 2 * 10^4  < 2^15 - 1 用 14 位即可表示全部数
        for (int i = 14; i >= 0; i--) {
            int bit = (num >> i) & 1;
            if (Objects.isNull(node.children[bit])) {
                node.children[bit] = new NumTrieNode();
            }
            node = node.children[bit];
            node.count++;
        }
    }

    private int query(int num, int limit) {
        NumTrieNode node = root;
        int result = 0;
        for (int i = 14; i >= 0; i--) {
            int numBit = (num >> i) & 1, limitBit = (limit >> i) & 1;
            if (limitBit == 0) {
                // 寻找 相同的
                if (Objects.isNull(node.children[numBit])) {
                    break;
                }
                node = node.children[numBit];
            } else {
                // 当前位 异或为 0 肯定比limit 小
                if (Objects.nonNull(node.children[numBit])) {
                    result += node.children[numBit].count;
                }
                // 当前位 异或为 1 寻找下一位
                if (Objects.isNull(node.children[numBit ^ 1])) {
                    break;
                }
                node = node.children[numBit ^ 1];
            }
        }

        return result;
    }
}
