package com.qinfengsa.structure.leetcode;

import static com.qinfengsa.structure.util.LogUtils.logResult;

import java.util.Arrays;
import lombok.extern.slf4j.Slf4j;

/**
 * 1483. 树节点的第 K 个祖先
 *
 * <p>给你一棵树，树上有 n 个节点，按从 0 到 n-1 编号。树以父节点数组的形式给出，其中 parent[i] 是节点 i 的父节点。树的根节点是编号为 0 的节点。
 *
 * <p>请你设计并实现 getKthAncestor(int node, int k) 函数，函数返回节点 node 的第 k 个祖先节点。如果不存在这样的祖先节点，返回 -1 。
 *
 * <p>树节点的第 k 个祖先节点是从该节点到根节点路径上的第 k 个节点。
 *
 * <p>示例：
 *
 * <p>输入： ["TreeAncestor","getKthAncestor","getKthAncestor","getKthAncestor"]
 * [[7,[-1,0,0,1,1,2,2]],[3,1],[5,2],[6,3]]
 *
 * <p>输出： [null,1,0,-1]
 *
 * <p>解释： TreeAncestor treeAncestor = new TreeAncestor(7, [-1, 0, 0, 1, 1, 2, 2]);
 *
 * <p>treeAncestor.getKthAncestor(3, 1); // 返回 1 ，它是 3 的父节点 treeAncestor.getKthAncestor(5, 2); // 返回
 * 0 ，它是 5 的祖父节点 treeAncestor.getKthAncestor(6, 3); // 返回 -1 因为不存在满足要求的祖先节点
 *
 * <p>提示：
 *
 * <p>1 <= k <= n <= 5*10^4 parent[0] == -1 表示编号为 0 的节点是根节点。 对于所有的 0 < i < n ，0 <= parent[i] < n 总成立
 * 0 <= node < n 至多查询 5*10^4 次
 *
 * @author qinfengsa
 * @date 2020/06/14 11:27
 */
@Slf4j
public class TreeAncestor {

    int[][] dp;

    int len;

    public TreeAncestor(int n, int[] parent) {

        dp = new int[n][20];
        Arrays.fill(dp[0], -1);
        dp[0][0] = -1;
        for (int i = 1; i < n; i++) {
            Arrays.fill(dp[i], -1);
            dp[i][0] = parent[i];
        }

        // 使用二分进行压缩
        for (int j = 1; j < 20; j++) {
            for (int i = 0; i < n; i++) {
                if (dp[i][j - 1] == -1) {
                    continue;
                }
                dp[i][j] = dp[dp[i][j - 1]][j - 1];
            }
        }
        len = n;
        logResult(dp);
    }

    public int getKthAncestor(int node, int k) {
        if (node >= len) {
            return -1;
        }
        if (node == -1) {
            return -1;
        }
        int result = node;
        for (int j = 0; j < 20; j++) {
            if (result == -1) {
                break;
            }
            if (((1 << j) & k) > 0) {
                result = dp[result][j];
            }
        }

        return result;
    }

    public static void main(String[] args) {
        /*TreeAncestor treeAncestor = new TreeAncestor(7, new int[] {-1, 0, 0, 1, 1, 2, 2});
        int val1 = treeAncestor.getKthAncestor(3, 1);
        log.debug("val1:{}", val1);
        int val2 = treeAncestor.getKthAncestor(5, 2);
        log.debug("val2:{}", val2);
        int val3 = treeAncestor.getKthAncestor(6, 3);
        log.debug("val3:{}", val3);*/

        TreeAncestor treeAncestor = new TreeAncestor(5, new int[] {-1, 0, 0, 0, 3});
        int val1 = treeAncestor.getKthAncestor(1, 5);
        log.debug("val1:{}", val1);
        int val2 = treeAncestor.getKthAncestor(3, 2);
        log.debug("val2:{}", val2);
        int val3 = treeAncestor.getKthAncestor(0, 1);
        log.debug("val3:{}", val3);
        int val4 = treeAncestor.getKthAncestor(3, 1);
        log.debug("val4:{}", val4);
        int val5 = treeAncestor.getKthAncestor(3, 5);
        log.debug("val5:{}", val5);
        int val6 = treeAncestor.getKthAncestor(4, 2);
        log.debug("val6:{}", val6);
    }
}
