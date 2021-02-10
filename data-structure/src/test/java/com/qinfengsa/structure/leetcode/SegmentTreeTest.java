package com.qinfengsa.structure.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * 线段树
 *
 * @author qinfengsa
 * @date 2021/02/10 09:53
 */
@Slf4j
public class SegmentTreeTest {

    /**
     * LCP 05. 发 LeetCoin
     *
     * <p>力扣决定给一个刷题团队发LeetCoin作为奖励。同时，为了监控给大家发了多少LeetCoin，力扣有时候也会进行查询。
     *
     * <p>该刷题团队的管理模式可以用一棵树表示：
     *
     * <p>团队只有一个负责人，编号为1。除了该负责人外，每个人有且仅有一个领导（负责人没有领导）； 不存在循环管理的情况，如A管理B，B管理C，C管理A。
     *
     * <p>力扣想进行的操作有以下三种：
     *
     * <p>给团队的一个成员（也可以是负责人）发一定数量的LeetCoin；
     * 给团队的一个成员（也可以是负责人），以及他/她管理的所有人（即他/她的下属、他/她下属的下属，……），发一定数量的LeetCoin；
     * 查询某一个成员（也可以是负责人），以及他/她管理的所有人被发到的LeetCoin之和。
     *
     * <p>输入：
     *
     * <p>N表示团队成员的个数（编号为1～N，负责人为1）； leadership是大小为(N - 1) * 2的二维数组，其中每个元素[a, b]代表b是a的下属；
     * operations是一个长度为Q的二维数组，代表以时间排序的操作，格式如下： operations[i][0] = 1:
     * 代表第一种操作，operations[i][1]代表成员的编号，operations[i][2]代表LeetCoin的数量； operations[i][0] = 2:
     * 代表第二种操作，operations[i][1]代表成员的编号，operations[i][2]代表LeetCoin的数量； operations[i][0] = 3:
     * 代表第三种操作，operations[i][1]代表成员的编号； 输出：
     *
     * <p>返回一个数组，数组里是每次查询的返回值（发LeetCoin的操作不需要任何返回值）。由于发的LeetCoin很多，请把每次查询的结果模1e9+7 (1000000007)。
     *
     * <p>示例 1：
     *
     * <p>输入：N = 6, leadership = [[1, 2], [1, 6], [2, 3], [2, 5], [1, 4]], operations = [[1, 1,
     * 500], [2, 2, 50], [3, 1], [2, 6, 15], [3, 1]] 输出：[650, 665] 解释：团队的管理关系见下图。
     * 第一次查询时，每个成员得到的LeetCoin的数量分别为（按编号顺序）：500, 50, 50, 0, 50, 0;
     * 第二次查询时，每个成员得到的LeetCoin的数量分别为（按编号顺序）：500, 50, 50, 0, 50, 15.
     *
     * <p>限制：
     *
     * <p>1 <= N <= 50000 1 <= Q <= 50000 operations[i][0] != 3 时，1 <= operations[i][2] <= 5000
     *
     * @param n
     * @param leadership
     * @param operations
     * @return
     */
    public int[] bonus(int n, int[][] leadership, int[][] operations) {

        for (int i = 1; i <= n; i++) {
            memberMap.put(i, new BonusNode(i));
        }

        for (int[] ship : leadership) {
            memberMap.get(ship[0]).children.add(memberMap.get(ship[1]));
        }

        bonusDfs(memberMap.get(1));
        SegTree segTree = new SegTree(0, n - 1);
        List<Integer> list = new ArrayList<>();
        for (int[] op : operations) {
            int nodeId = op[1];
            BonusNode node = memberMap.get(nodeId);
            switch (op[0]) {
                case 1:
                    segTree.update(node.right, node.right, op[2]);
                    break;
                case 2:
                    segTree.update(node.left, node.right, op[2]);
                    break;
                case 3:
                    long result = segTree.query(node.left, node.right, 0);

                    list.add((int) (result % MOD));
                    break;
            }
        }

        int[] result = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            result[i] = list.get(i);
        }
        return result;
    }

    int id = 0;

    static final int MOD = 1000000007;

    private int bonusDfs(BonusNode root) {
        List<BonusNode> children = root.children;
        int min = Integer.MAX_VALUE;
        for (BonusNode node : children) {
            min = Math.min(min, bonusDfs(node));
        }
        int r = id;
        min = Math.min(min, r);
        int l = min;
        id++;
        root.left = l;
        root.right = r;
        return min;
    }

    Map<Integer, BonusNode> memberMap = new HashMap<>();

    static class SegTree {
        int start, end;
        SegTree left, right;
        long sum = 0L, addVal = 0L;

        SegTree(int start, int end) {
            this.start = start;
            this.end = end;
            if (start != end) {
                int mid = (start + end) >> 1;
                this.left = new SegTree(start, mid);
                this.right = new SegTree(mid + 1, end);
            }
        }

        void update(int l, int r, long val) {
            if (l == start && r == end) {
                this.addVal += val;
                this.sum += val * (r - l + 1);
                return;
            }
            int mid = (start + end) >> 1;
            if (r <= mid) {
                left.update(l, r, val);
            } else if (l >= mid + 1) {
                right.update(l, r, val);
            } else {
                left.update(l, mid, val);
                right.update(mid + 1, r, val);
            }
            this.sum += val * (r - l + 1);
        }

        long query(int l, int r, long parentAdd) {
            if (l == start && r == end) {
                return this.sum + parentAdd * (r - l + 1);
            }
            int mid = (start + end) >> 1;
            parentAdd += this.addVal;
            if (r <= mid) {
                return left.query(l, r, parentAdd);
            } else if (l >= mid + 1) {
                return right.query(l, r, parentAdd);
            } else {
                return left.query(l, mid, parentAdd) + right.query(mid + 1, r, parentAdd);
            }
        }
    }

    static class BonusNode {
        int id;
        int left, right;

        List<BonusNode> children;

        public BonusNode(int id) {
            this.id = id;
            this.children = new ArrayList<>();
        }
    }

    @Test
    public void bonus() {
        int n = 6;
        int[][] leadership = {{1, 2}, {1, 6}, {2, 3}, {2, 5}, {1, 4}},
                operations = {{1, 1, 500}, {2, 2, 50}, {3, 1}, {2, 6, 15}, {3, 1}};
        int[] result = bonus(n, leadership, operations);
        log.debug("result:{}", result);
    }
}
