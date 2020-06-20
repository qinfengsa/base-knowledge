package com.qinfengsa.structure.leetcode;

import lombok.extern.slf4j.Slf4j;

/**
 * 427. 建立四叉树
 *
 * <p>给你一个 n * n 矩阵 grid ，矩阵由若干 0 和 1 组成。请你用四叉树表示该矩阵 grid 。
 *
 * <p>你需要返回能表示矩阵的 四叉树 的根结点。
 *
 * <p>注意，当 isLeaf 为 False 时，你可以把 True 或者 False 赋值给节点，两种值都会被判题机制 接受 。
 *
 * <p>四叉树数据结构中，每个内部节点只有四个子节点。此外，每个节点都有两个属性：
 *
 * <p>val：储存叶子结点所代表的区域的值。1 对应 True，0 对应 False； isLeaf: 当这个节点是一个叶子结点时为 True，如果它有 4 个子节点则为 False 。
 * class Node { public boolean val; public boolean isLeaf; public Node topLeft; public Node
 * topRight; public Node bottomLeft; public Node bottomRight; } 我们可以按以下步骤为二维区域构建四叉树：
 *
 * <p>如果当前网格的值相同（即，全为 0 或者全为 1），将 isLeaf 设为 True ，将 val 设为网格相应的值，并将四个子节点都设为 Null 然后停止。 如果当前网格的值不同，将
 * isLeaf 设为 False， 将 val 设为任意值，然后如下图所示，将当前网格划分为四个子网格。 使用适当的子网格递归每个子节点。
 *
 * <p>如果你想了解更多关于四叉树的内容，可以参考 wiki 。
 *
 * <p>四叉树格式：
 *
 * <p>输出为使用层序遍历后四叉树的序列化形式，其中 null 表示路径终止符，其下面不存在节点。
 *
 * <p>它与二叉树的序列化非常相似。唯一的区别是节点以列表形式表示 [isLeaf, val] 。
 *
 * <p>如果 isLeaf 或者 val 的值为 True ，则表示它在列表 [isLeaf, val] 中的值为 1 ；如果 isLeaf 或者 val 的值为 False ，则表示值为 0 。
 *
 * <p>示例 1：
 *
 * <p>输入：grid = [[0,1],[1,0]] 输出：[[0,1],[1,0],[1,1],[1,1],[1,0]] 解释：此示例的解释如下： 请注意，在下面四叉树的图示中，0 表示
 * false，1 表示 True 。
 *
 * <p>示例 2：
 *
 * <p>输入：grid =
 * [[1,1,1,1,0,0,0,0],[1,1,1,1,0,0,0,0],[1,1,1,1,1,1,1,1],[1,1,1,1,1,1,1,1],[1,1,1,1,0,0,0,0],[1,1,1,1,0,0,0,0],[1,1,1,1,0,0,0,0],[1,1,1,1,0,0,0,0]]
 * 输出：[[0,1],[1,1],[0,1],[1,1],[1,0],null,null,null,null,[1,0],[1,0],[1,1],[1,1]]
 * 解释：网格中的所有值都不相同。我们将网格划分为四个子网格。 topLeft，bottomLeft 和 bottomRight 均具有相同的值。 topRight 具有不同的值，因此我们将其再分为
 * 4 个子网格，这样每个子网格都具有相同的值。 解释如下图所示：
 *
 * <p>示例 3：
 *
 * <p>输入：grid = [[1,1],[1,1]] 输出：[[1,1]] 示例 4：
 *
 * <p>输入：grid = [[0]] 输出：[[1,0]] 示例 5：
 *
 * <p>输入：grid = [[1,1,0,0],[1,1,0,0],[0,0,1,1],[0,0,1,1]] 输出：[[0,1],[1,1],[1,0],[1,0],[1,1]]
 *
 * <p>提示：
 *
 * <p>n == grid.length == grid[i].length n == 2^x 其中 0 <= x <= 6
 *
 * @author qinfengsa
 * @date 2020/06/20 08:30
 */
@Slf4j
public class QuadTrees {

    public static void main(String[] args) {
        int[][] grid = {
            {1, 1, 1, 1, 0, 0, 0, 0},
            {1, 1, 1, 1, 0, 0, 0, 0},
            {1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 0, 0, 0, 0},
            {1, 1, 1, 1, 0, 0, 0, 0},
            {1, 1, 1, 1, 0, 0, 0, 0},
            {1, 1, 1, 1, 0, 0, 0, 0}
        };
        Node root = new QuadTrees().construct(grid);
        log.debug("success");
    }

    public Node construct(int[][] grid) {
        int n = grid.length;

        return constructNode(grid, 0, 0, n);
    }

    private Node constructNode(int[][] grid, int startRow, int startCol, int n) {
        if (n == 1) {
            return new Node(grid[startRow][startCol] == 1, true);
        }
        // 判断所有值是否相同, 相同直接返回
        boolean isLeaf = true;
        int val = grid[startRow][startCol];
        outer:
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[startRow + i][startCol + j] != val) {
                    isLeaf = false;
                    break outer;
                }
            }
        }

        Node root = new Node(grid[startRow][startCol] == 1, isLeaf);
        if (isLeaf) {
            return root;
        }
        n >>= 1;
        root.topLeft = constructNode(grid, startRow, startCol, n);
        root.topRight = constructNode(grid, startRow, startCol + n, n);
        root.bottomLeft = constructNode(grid, startRow + n, startCol, n);
        root.bottomRight = constructNode(grid, startRow + n, startCol + n, n);

        return root;
    }

    class Node {
        public boolean val;
        public boolean isLeaf;
        public Node topLeft;
        public Node topRight;
        public Node bottomLeft;
        public Node bottomRight;

        public Node() {
            this.val = false;
            this.isLeaf = false;
            this.topLeft = null;
            this.topRight = null;
            this.bottomLeft = null;
            this.bottomRight = null;
        }

        public Node(boolean val, boolean isLeaf) {
            this.val = val;
            this.isLeaf = isLeaf;
            this.topLeft = null;
            this.topRight = null;
            this.bottomLeft = null;
            this.bottomRight = null;
        }

        public Node(
                boolean val,
                boolean isLeaf,
                Node topLeft,
                Node topRight,
                Node bottomLeft,
                Node bottomRight) {
            this.val = val;
            this.isLeaf = isLeaf;
            this.topLeft = topLeft;
            this.topRight = topRight;
            this.bottomLeft = bottomLeft;
            this.bottomRight = bottomRight;
        }
    }
}
