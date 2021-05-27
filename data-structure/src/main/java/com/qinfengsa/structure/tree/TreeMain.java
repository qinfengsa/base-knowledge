package com.qinfengsa.structure.tree;

import com.qinfengsa.structure.leetcode.TreeNode;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;

/**
 * 树
 *
 * @author wangheng
 * @date 2021/5/26 8:20
 */
@Slf4j
public class TreeMain {

    /**
     * 1373. 二叉搜索子树的最大键值和
     *
     * <p>给你一棵以 root 为根的 二叉树 ，请你返回 任意 二叉搜索子树的最大键值和。
     *
     * <p>二叉搜索树的定义如下：
     *
     * <p>任意节点的左子树中的键值都 小于 此节点的键值。 任意节点的右子树中的键值都 大于 此节点的键值。 任意节点的左子树和右子树都是二叉搜索树。
     *
     * <p>示例 1：
     *
     * <p>输入：root = [1,4,3,2,4,2,5,null,null,null,null,null,null,4,6] 输出：20 解释：键值为 3 的子树是和最大的二叉搜索树。
     * 示例 2：
     *
     * <p>输入：root = [4,3,null,1,2] 输出：2 解释：键值为 2 的单节点子树是和最大的二叉搜索树。 示例 3：
     *
     * <p>输入：root = [-4,-2,-5] 输出：0 解释：所有节点键值都为负数，和最大的二叉搜索树为空。 示例 4：
     *
     * <p>输入：root = [2,1,3] 输出：6 示例 5：
     *
     * <p>输入：root = [5,4,8,3,null,6,3] 输出：7
     *
     * <p>提示：
     *
     * <p>每棵树有 1 到 40000 个节点。 每个节点的键值在 [-4 * 10^4 , 4 * 10^4] 之间。
     *
     * @param root
     * @return
     */
    public int maxSumBST(TreeNode root) {
        // dfs
        dfsSumBST(root);

        return intResult;
    }

    private BstResult dfsSumBST(TreeNode root) {
        if (Objects.isNull(root)) {
            return new BstResult(true, Integer.MAX_VALUE, Integer.MIN_VALUE, 0);
        }
        BstResult leftResult = dfsSumBST(root.left);
        BstResult rightResult = dfsSumBST(root.right);
        // 左右不是二叉搜索树
        if (!leftResult.bst || !rightResult.bst) {
            return new BstResult(false, 0, 0, 0);
        }
        // val <=左子树的最大值 或者 >= 右子树的最小值
        if (root.val <= leftResult.max || root.val >= rightResult.min) {
            return new BstResult(false, 0, 0, 0);
        }
        int min = Objects.isNull(root.left) ? root.val : leftResult.min;
        int max = Objects.isNull(root.right) ? root.val : rightResult.max;
        int sum = leftResult.sum + root.val + rightResult.sum;
        intResult = Math.max(intResult, sum);
        return new BstResult(true, min, max, sum);
    }

    static class BstResult {
        // 是否二叉搜索树
        boolean bst;
        int min, max, sum;

        public BstResult(boolean bst, int min, int max, int sum) {
            this.bst = bst;
            this.min = min;
            this.max = max;
            this.sum = sum;
        }
    }

    private int intResult = 0;

    /**
     * 1377. T 秒后青蛙的位置
     *
     * <p>给你一棵由 n 个顶点组成的无向树，顶点编号从 1 到 n。青蛙从 顶点 1 开始起跳。规则如下：
     *
     * <p>在一秒内，青蛙从它所在的当前顶点跳到另一个 未访问 过的顶点（如果它们直接相连）。 青蛙无法跳回已经访问过的顶点。
     * 如果青蛙可以跳到多个不同顶点，那么它跳到其中任意一个顶点上的机率都相同。 如果青蛙不能跳到任何未访问过的顶点上，那么它每次跳跃都会停留在原地。 无向树的边用数组 edges 描述，其中
     * edges[i] = [fromi, toi] 意味着存在一条直接连通 fromi 和 toi 两个顶点的边。
     *
     * <p>返回青蛙在 t 秒后位于目标顶点 target 上的概率。
     *
     * <p>示例 1：
     *
     * <p>输入：n = 7, edges = [[1,2],[1,3],[1,7],[2,4],[2,6],[3,5]], t = 2, target = 4
     * 输出：0.16666666666666666 解释：上图显示了青蛙的跳跃路径。青蛙从顶点 1 起跳，第 1 秒 有 1/3 的概率跳到顶点 2 ，然后第 2 秒 有 1/2
     * 的概率跳到顶点 4，因此青蛙在 2 秒后位于顶点 4 的概率是 1/3 * 1/2 = 1/6 = 0.16666666666666666 。 示例 2：
     *
     * <p>输入：n = 7, edges = [[1,2],[1,3],[1,7],[2,4],[2,6],[3,5]], t = 1, target = 7
     * 输出：0.3333333333333333 解释：上图显示了青蛙的跳跃路径。青蛙从顶点 1 起跳，有 1/3 = 0.3333333333333333 的概率能够 1 秒 后跳到顶点 7
     * 。 示例 3：
     *
     * <p>输入：n = 7, edges = [[1,2],[1,3],[1,7],[2,4],[2,6],[3,5]], t = 20, target = 6
     * 输出：0.16666666666666666
     *
     * <p>提示：
     *
     * <p>1 <= n <= 100 edges.length == n-1 edges[i].length == 2 1 <= edges[i][0], edges[i][1] <= n
     * 1 <= t <= 50 1 <= target <= n 与准确值误差在 10^-5 之内的结果将被判定为正确。
     *
     * @param n
     * @param edges
     * @param t
     * @param target
     * @return
     */
    public double frogPosition(int n, int[][] edges, int t, int target) {
        if (n == 1) {
            return target == 1 && t >= 1 ? 1.0 : 0.0;
        }

        // 节点i 的父节点
        int[] parents = new int[n + 1];
        // 节点i 的子节点数量
        int[] childNum = new int[n + 1];

        for (int[] edge : edges) {
            // 较小的为父节点
            int p, c;
            if (edge[0] < edge[1]) {
                p = edge[0];
                c = edge[1];
            } else {
                p = edge[1];
                c = edge[0];
            }
            parents[c] = p;
            childNum[p]++;
        }
        // 从 target 开始找 父节点
        double result = 1.0;
        int num = target, time = 0;

        while (parents[num] != 0) {
            int p = parents[num];
            int count = childNum[p];
            result *= 1.0 / (double) count;
            num = p;
            time++;
        }
        // target 是叶子节点
        if (childNum[target] == 0 && time <= t) {
            return result;
        }
        // target 不是叶子节点 时间必须相等
        if (childNum[target] != 0 && time == t) {
            return result;
        }
        return 0.0;
    }
}
