package com.qinfengsa.structure.leetcode;

import static com.qinfengsa.structure.util.LogUtils.logResult;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Queue;
import java.util.Set;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author qinfengsa
 * @date 2019/5/13 19:46
 */
@Slf4j
public class TreeTest {

    /** 前序遍历测试 */
    @Test
    public void preTest() {
        TreeNode root = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);
        root.left = node2;
        root.right = node4;
        node2.left = node3;
        node2.right = node5;
        node4.left = node6;

        BinTree tree = new BinTree();
        List<Integer> result = tree.preorderTraversal2(root);
        log.debug("result:{} ", result);

        // [1, 2, 3, 5, 4, 6]
    }

    /** 中序遍历测试 */
    @Test
    public void inTest() {
        TreeNode root = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);
        root.left = node2;
        root.right = node4;
        node2.left = node3;
        node2.right = node5;
        node4.left = node6;

        BinTree tree = new BinTree();
        List<Integer> result = tree.inorderTraversal2(root);
        log.debug("result:{} ", result);

        // [3, 2, 5, 1, 6, 4]
    }

    /** 后序遍历测试 */
    @Test
    public void postTest() {
        TreeNode root = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);
        root.left = node2;
        root.right = node4;
        node2.left = node3;
        node2.right = node5;
        node4.left = node6;

        BinTree tree = new BinTree();
        List<Integer> result = tree.postorderTraversal2(root);
        log.debug("result:{} ", result);

        // [3, 5, 2, 6, 4, 1]
    }

    /**
     * 二叉树的层次遍历 给定一个二叉树，返回其按层次遍历的节点值。 （即逐层地，从左到右访问所有节点）。
     *
     * <p>例如: 给定二叉树: [3,9,20,null,null,15,7],
     *
     * <p>3 / \ 9 20 / \ 15 7 返回其层次遍历结果：
     *
     * <p>[ [3], [9,20], [15,7] ]
     */
    @Test
    public void levelOrder() {
        TreeNode root = new TreeNode(2);
        TreeNode node2 = new TreeNode(9);
        TreeNode node3 = new TreeNode(20);
        TreeNode node4 = new TreeNode(15);
        TreeNode node5 = new TreeNode(7);
        root.left = node2;
        root.right = node3;
        node3.left = node4;
        node3.right = node5;
        BinTree tree = new BinTree();
        List<List<Integer>> result = tree.levelOrder(root);
        log.debug("result:{} ", result);
    }

    /**
     * 二叉树的最大深度 给定一个二叉树，找出其最大深度。
     *
     * <p>二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
     *
     * <p>说明: 叶子节点是指没有子节点的节点。
     *
     * <p>示例： 给定二叉树 [3,9,20,null,null,15,7]，
     *
     * <p>3 / \ 9 20 / \ 15 7 返回它的最大深度 3 。
     */
    @Test
    public void maxDepth() {
        TreeNode root = new TreeNode(2);
        TreeNode node2 = new TreeNode(9);
        TreeNode node3 = new TreeNode(20);
        TreeNode node4 = new TreeNode(15);
        TreeNode node5 = new TreeNode(7);
        root.left = node2;
        root.right = node3;
        node3.left = node4;
        node3.right = node5;
        BinTree tree = new BinTree();
        int result = tree.maxDepth(root);
        log.debug("result:{} ", result);
    }

    /**
     * 对称二叉树 给定一个二叉树，检查它是否是镜像对称的。
     *
     * <p>例如，二叉树 [1,2,2,3,4,4,3] 是对称的。
     *
     * <p>1 / \ 2 2 / \ / \ 3 4 4 3 但是下面这个 [1,2,2,null,3,null,3] 则不是镜像对称的:
     *
     * <p>1 / \ 2 2 \ \ 3 3 说明:
     *
     * <p>如果你可以运用递归和迭代两种方法解决这个问题，会很加分。
     */
    @Test
    public void isSymmetric() {
        TreeNode root = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(2);
        TreeNode node4 = new TreeNode(3);
        TreeNode node5 = new TreeNode(3);
        TreeNode node6 = new TreeNode(4);
        TreeNode node7 = new TreeNode(3);
        root.left = node2;
        root.right = node3;
        node2.left = node4;
        // node2.right = node5;
        // node3.left = node6;
        node3.right = node7;
        BinTree tree = new BinTree();
        boolean result = tree.isSymmetric(root);
        log.debug("result:{} ", result);
    }

    /**
     * 路径总和 给定一个二叉树和一个目标和，判断该树中是否存在根节点到叶子节点的路径，这条路径上所有节点值相加等于目标和。
     *
     * <p>说明: 叶子节点是指没有子节点的节点。
     *
     * <p>示例: 给定如下二叉树，以及目标和 sum = 22，
     *
     * <p>5 / \ 4 8 / / \ 11 13 4 / \ \ 7 2 1 返回 true, 因为存在目标和为 22 的根节点到叶子节点的路径 5->4->11->2。
     */
    @Test
    public void hasPathSum() {
        /*TreeNode root = new TreeNode(5);
        TreeNode node2 = new TreeNode(4);
        TreeNode node3 = new TreeNode(8);
        TreeNode node4 = new TreeNode(11);
        TreeNode node5 = new TreeNode(13);
        TreeNode node6 = new TreeNode(4);
        TreeNode node7 = new TreeNode(7);
        TreeNode node8 = new TreeNode(2);
        TreeNode node9 = new TreeNode(1);
        root.left = node2;
        root.right = node3;
        node2.left = node4;

        node3.left = node5;
        node3.right = node6;

        node4.left = node7;
        node4.right = node8;

        node6.right = node9;*/

        TreeNode root = new TreeNode(-2);
        TreeNode node2 = new TreeNode(-3);
        root.right = node2;
        BinTree tree = new BinTree();
        boolean result = tree.hasPathSum(root, -5);
        log.debug("result:{} ", result);
    }

    @Test
    public void pathSum() {
        // 10,5,-3,3,2,null,11,3,-2,null,1]
        /* TreeNode root = new TreeNode(10);
        TreeNode node2 = new TreeNode(5);
        TreeNode node3 = new TreeNode(-3);
        TreeNode node4 = new TreeNode(3);
        TreeNode node5 = new TreeNode(2);
        TreeNode node6 = new TreeNode(11);
        TreeNode node7 = new TreeNode(3);
        TreeNode node8 = new TreeNode(-2);
        TreeNode node9 = new TreeNode(1);
        root.left = node2;
        root.right = node3;
        node2.left = node4;

        node2.right = node5;
        node3.right = node6;

        node4.left = node7;
        node4.right = node8;

        node5.right = node9;*/
        TreeNode root = new TreeNode(-1);
        TreeNode node2 = new TreeNode(-4);
        TreeNode node3 = new TreeNode(-3);
        root.left = node2;
        root.right = node3;
        int sum = -5;
        logResult(pathSum(root, sum));
    }

    /**
     * 437. 路径总和 III 给定一个二叉树，它的每个结点都存放着一个整数值。
     *
     * <p>找出路径和等于给定数值的路径总数。
     *
     * <p>路径不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的（只能从父节点到子节点）。
     *
     * <p>二叉树不超过1000个节点，且节点数值范围是 [-1000000,1000000] 的整数。
     *
     * <p>示例：
     *
     * <p>root = [10,5,-3,3,2,null,11,3,-2,null,1], sum = 8
     *
     * <p>10 / \ 5 -3 / \ \ 3 2 11 / \ \ 3 -2 1
     *
     * <p>返回 3。和等于 8 的路径有:
     *
     * <p>1. 5 -> 3 2. 5 -> 2 -> 1 3. -3 -> 11
     *
     * @param root
     * @param sum
     * @return
     */
    public int pathSum(TreeNode root, int sum) {
        int result = 0;
        if (root == null) {
            return 0;
        }
        // boolean existsLeft = root.left != null;
        // boolean existsRight = root.right != null;

        result += pathRemainingSum(root, sum);
        result += pathSum(root.left, sum);
        result += pathSum(root.right, sum);
        return result;
    }

    /**
     * 求剩余的 sum
     *
     * @param root
     * @param sum
     * @return
     */
    public int pathRemainingSum(TreeNode root, int sum) {
        int result = 0;
        if (root == null) {
            return 0;
        }
        if (root.val == sum) {
            result++;
        }
        result += pathRemainingSum(root.left, sum - root.val);
        result += pathRemainingSum(root.right, sum - root.val);
        return result;
    }

    @Test
    public void pathSum3() {
        // 10,5,-3,3,2,null,11,3,-2,null,1]
        TreeNode root = new TreeNode(5);
        TreeNode node2 = new TreeNode(4);
        TreeNode node3 = new TreeNode(8);
        TreeNode node4 = new TreeNode(11);
        TreeNode node5 = new TreeNode(13);
        TreeNode node6 = new TreeNode(4);
        TreeNode node7 = new TreeNode(7);
        TreeNode node8 = new TreeNode(2);
        TreeNode node9 = new TreeNode(5);
        TreeNode node10 = new TreeNode(1);
        root.left = node2;
        root.right = node3;
        node2.left = node4;

        node3.left = node5;
        node3.right = node6;

        node4.left = node7;
        node4.right = node8;

        node6.left = node9;
        node6.right = node10;
        int sum = 22;
        logResult(pathSum2(root, sum));
    }

    /**
     * 113. 路径总和 II 给定一个二叉树和一个目标和，找到所有从根节点到叶子节点路径总和等于给定目标和的路径。
     *
     * <p>说明: 叶子节点是指没有子节点的节点。
     *
     * <p>示例: 给定如下二叉树，以及目标和 sum = 22，
     *
     * <p>5 / \ 4 8 / / \ 11 13 4 / \ / \ 7 2 5 1 返回:
     *
     * <p>[ [5,4,11,2], [5,8,4,5] ]
     *
     * @param root
     * @param sum
     * @return
     */
    public List<List<Integer>> pathSum2(TreeNode root, int sum) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        pathRemainingSum(root, sum, new LinkedList<>(), result);
        return result;
    }

    private void pathRemainingSum(
            TreeNode root, int sum, Deque<Integer> deque, List<List<Integer>> result) {
        if (root == null) {
            return;
        }
        boolean existsLeft = root.left != null;
        boolean existsRight = root.right != null;
        if (!existsLeft && !existsRight && root.val == sum) {
            deque.addLast(root.val);
            result.add(new ArrayList<>(deque));
            deque.removeLast();
            return;
        }
        deque.addLast(root.val);
        pathRemainingSum(root.left, sum - root.val, deque, result);
        pathRemainingSum(root.right, sum - root.val, deque, result);
        deque.removeLast();
    }

    /**
     * 从中序与后序遍历序列构造二叉树 根据一棵树的中序遍历与后序遍历构造二叉树。
     *
     * <p>注意: 你可以假设树中没有重复的元素。
     *
     * <p>例如，给出
     *
     * <p>中序遍历 inorder = [9,3,15,20,7] 后序遍历 postorder = [9,15,7,20,3] 返回如下的二叉树：
     *
     * <p>3 / \ 9 20 / \ 15 7
     */
    @Test
    public void buildTree() {
        int[] inorder = {9, 3, 15, 20, 7};
        int[] postorder = {9, 15, 7, 20, 3};

        BinTree tree = new BinTree();
        TreeNode node = tree.buildTree(inorder, postorder);
        log.debug("result:{} ", node.val);
    }

    @Test
    public void buildTree2() {
        int[] preorder = {3, 9, 20, 15, 7};
        int[] inorder = {9, 3, 15, 20, 7};

        BinTree tree = new BinTree();
        TreeNode node = tree.buildTree2(preorder, inorder);
        log.debug("result:{} ", node.val);
    }

    /** 序列化测试 5 / \ 4 8 / / \ 11 13 4 / \ \ 7 2 1 */
    @Test
    public void serialize() {

        TreeNode root = new TreeNode(5);
        TreeNode node2 = new TreeNode(4);
        TreeNode node3 = new TreeNode(8);
        TreeNode node4 = new TreeNode(11);
        TreeNode node5 = new TreeNode(13);
        TreeNode node6 = new TreeNode(4);
        TreeNode node7 = new TreeNode(7);
        TreeNode node8 = new TreeNode(2);
        TreeNode node9 = new TreeNode(1);
        root.left = node2;
        root.right = node3;
        node2.left = node4;

        node3.left = node5;
        node3.right = node6;

        node4.left = node7;
        node4.right = node8;

        node6.right = node9;
        BinTree tree = new BinTree();
        String data = tree.serialize(root);
        log.debug("data:{} ", data);
    }

    /** 反序列化测试 */
    @Test
    public void deserialize() {
        String data = "5,4,11,7,#,#,2,#,#,#,8,13,#,#,4,#,1,#,#,";
        BinTree tree = new BinTree();
        TreeNode root = tree.deserialize(data);
        log.debug("root:{} ", root);
    }

    @Test
    public void zigzagLevelOrder() {
        TreeNode root = new TreeNode(3);
        TreeNode node2 = new TreeNode(9);
        TreeNode node3 = new TreeNode(20);
        TreeNode node4 = new TreeNode(15);
        TreeNode node5 = new TreeNode(7);
        root.left = node2;
        root.right = node3;
        node3.left = node4;
        node3.right = node5;
        List<List<Integer>> result = zigzagLevelOrder(root);
        log.debug("result:{}", result);
    }

    /**
     * 二叉树的锯齿形层次遍历 给定一个二叉树，返回其节点值的锯齿形层次遍历。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。
     *
     * <p>例如： 给定二叉树 [3,9,20,null,null,15,7],
     *
     * <p>3 / \ 9 20 / \ 15 7 返回锯齿形层次遍历如下：
     *
     * <p>[ [3], [20,9], [15,7] ]
     *
     * @param root
     * @return
     */
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();

        if (Objects.isNull(root)) {
            return result;
        }

        LinkedList<TreeNode> nodeList = new LinkedList<>();

        nodeList.addLast(root);
        boolean isLeft = true;
        while (!nodeList.isEmpty()) {
            isLeft = !isLeft;
            List<Integer> rowList = new ArrayList<>();
            for (TreeNode treeNode : nodeList) {
                rowList.add(treeNode.val);
            }
            nodeList = getList(nodeList, isLeft);

            result.add(rowList);
        }
        return result;
    }

    private LinkedList<TreeNode> getList(LinkedList<TreeNode> nodeList, boolean isLeft) {
        LinkedList<TreeNode> result = new LinkedList<>();
        while (!nodeList.isEmpty()) {

            TreeNode node = nodeList.pollLast();
            if (isLeft) {
                if (Objects.nonNull(node.left)) {
                    result.addLast(node.left);
                }
                if (Objects.nonNull(node.right)) {
                    result.addLast(node.right);
                }
            } else {
                if (Objects.nonNull(node.right)) {
                    result.addLast(node.right);
                }
                if (Objects.nonNull(node.left)) {
                    result.addLast(node.left);
                }
            }
        }

        return result;
    }

    @Test
    public void kthSmallest() {
        TreeNode root = new TreeNode(5);
        TreeNode node2 = new TreeNode(3);
        TreeNode node3 = new TreeNode(6);
        TreeNode node4 = new TreeNode(2);
        TreeNode node5 = new TreeNode(4);
        root.left = node2;
        root.right = node3;
        node2.left = node4;
        node2.right = node5;
        node4.left = new TreeNode(1);
        int result = kthSmallest(root, 3);
        log.debug("result:{}", result);
    }

    /**
     * 二叉搜索树中第K小的元素 给定一个二叉搜索树，编写一个函数 kthSmallest 来查找其中第 k 个最小的元素。
     *
     * <p>说明： 你可以假设 k 总是有效的，1 ≤ k ≤ 二叉搜索树元素个数。
     *
     * <p>示例 1:
     *
     * <p>输入: root = [3,1,4,null,2], k = 1 3 / \ 1 4 \ 2 输出: 1 示例 2:
     *
     * <p>输入: root = [5,3,6,2,4,null,null,1], k = 3 5 / \ 3 6 / \ 2 4 / 1 输出: 3 进阶：
     * 如果二叉搜索树经常被修改（插入/删除操作）并且你需要频繁地查找第 k 小的值，你将如何优化 kthSmallest 函数？
     *
     * @param root
     * @param k
     * @return
     */
    public int kthSmallest(TreeNode root, int k) {
        // 中序遍历，
        List<Integer> list = new ArrayList<>();
        if (Objects.isNull(root)) {
            return 0;
        }
        inOrder(root, list, k);

        return list.get(k - 1);
    }

    private void inOrder(TreeNode root, List<Integer> list, int k) {

        if (Objects.isNull(root)) {
            return;
        }
        inOrder(root.left, list, k);
        list.add(root.val);
        if (list.size() == k) {
            return;
        }

        inOrder(root.right, list, k);
    }

    @Test
    public void generateTrees() {
        int n = 3;
        List<TreeNode> trees = generateTrees(n);
        log.debug("result:{}", trees);
    }

    /**
     * 不同的二叉搜索树 II 给定一个整数 n，生成所有由 1 ... n 为节点所组成的二叉搜索树。
     *
     * <p>示例:
     *
     * <p>输入: 3 输出: [ [1,null,3,2], [3,2,null,1], [3,1,null,null,2], [2,1,3], [1,null,2,null,3] ]
     * 解释: 以上的输出对应以下 5 种不同结构的二叉搜索树：
     *
     * <p>1 3 3 2 1 \ / / / \ \ 3 2 1 1 3 2 / / \ \ 2 1 2 3
     *
     * @param n
     * @return
     */
    public List<TreeNode> generateTrees(int n) {

        if (n < 1) {
            return new ArrayList<>();
        }
        return createTree(1, n);
    }

    /**
     * 从开始结点到结束结点构建一棵树
     *
     * @param start
     * @param end
     * @return
     */
    private List<TreeNode> createTree(int start, int end) {
        List<TreeNode> trees = new ArrayList<>();
        if (start > end) {
            trees.add(null);
            return trees;
        }
        // 循环，通过每个结点构造一棵树
        for (int i = start; i <= end; i++) {
            List<TreeNode> leftTree = createTree(start, i - 1);
            List<TreeNode> rightTree = createTree(i + 1, end);

            for (TreeNode left : leftTree) {
                for (TreeNode right : rightTree) {
                    TreeNode curNode = new TreeNode(i);
                    curNode.left = left;
                    curNode.right = right;
                    trees.add(curNode);
                }
            }
        }
        return trees;
    }

    @Test
    public void maxPathSum() {
        /*TreeNode root = new TreeNode(5);
        TreeNode node2 = new TreeNode(3);
        TreeNode node3 = new TreeNode(6);
        TreeNode node4 = new TreeNode(2);
        TreeNode node5 = new TreeNode(4);
        root.left = node2;
        root.right = node3;
        node2.left = node4;
        node2.right = node5;
        node4.left = new TreeNode(1);*/
        /* TreeNode root = new TreeNode(1);
        root.left =  new TreeNode(2);
        root.right =  new TreeNode(3);*/
        TreeNode root = new TreeNode(-1);
        TreeNode node2 = new TreeNode(6);
        root.left = node2;
        node2.left = new TreeNode(0);
        node2.right = new TreeNode(-6);
        int result = maxPathSum(root);
        log.debug("result :{}", result);
    }

    // 全局变量，记录最大值，由于Integer 作为参数是值传递，无法使用引用修改
    private int maxSum = Integer.MIN_VALUE;

    /**
     * 二叉树中的最大路径和 给定一个非空二叉树，返回其最大路径和。
     *
     * <p>本题中，路径被定义为一条从树中任意节点出发，达到任意节点的序列。该路径至少包含一个节点，且不一定经过根节点。
     *
     * <p>示例 1:
     *
     * <p>输入: [1,2,3]
     *
     * <p>1 / \ 2 3
     *
     * <p>输出: 6 示例 2:
     *
     * <p>输入: [-10,9,20,null,null,15,7]
     *
     * <p>-10 / \ 9 20 / \ 15 7
     *
     * <p>输出: 42
     *
     * @param root
     * @return
     */
    public int maxPathSum(TreeNode root) {

        // 每个节点有两个值，单边最大和总和最大

        // 最大的路径，可能的路径情况：
        //    a
        //   / \
        //  b   c
        // b + a + c。
        // b + a + a 的父结点。
        // a + c + a 的父结点。
        maxSumNode(root);
        return maxSum;
    }

    private int maxSumNode(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int leftSum = maxSumNode(node.left);
        int rightSum = maxSumNode(node.right);
        // 当前节点最大的和
        int currentNodeSum = node.val + leftSum + rightSum;
        log.debug("currentNodeSum:{}", currentNodeSum);
        maxSum = Math.max(maxSum, currentNodeSum);
        // 选择一个子结点，计算父节点路径的和
        int maxChild = Math.max(leftSum, rightSum);
        int maxVal = node.val + Math.max(maxChild, 0);
        maxSum = Math.max(maxSum, maxVal);
        return maxVal;
    }

    @Test
    public void checkInclusion() {
        String s1 = "ab", s2 = "eidbaooo";
        boolean result = checkInclusion(s1, s2);
        log.debug("result:{}", result);
    }

    /**
     * 字符串的排列 给定两个字符串 s1 和 s2，写一个函数来判断 s2 是否包含 s1 的排列。
     *
     * <p>换句话说，第一个字符串的排列之一是第二个字符串的子串。
     *
     * <p>示例1:
     *
     * <p>输入: s1 = "ab" s2 = "eidbaooo" 输出: True 解释: s2 包含 s1 的排列之一 ("ba").
     *
     * <p>示例2:
     *
     * <p>输入: s1= "ab" s2 = "eidboaoo" 输出: False
     *
     * <p>注意：
     *
     * <p>输入的字符串只包含小写字母 两个字符串的长度都在 [1, 10,000] 之间
     *
     * @param s1
     * @param s2
     * @return
     */
    public boolean checkInclusion(String s1, String s2) {
        if (s1.length() > s2.length()) {
            return false;
        }
        // 思路，用 长度为26的数组（只有小写字母） 记录 s1 的字符信息，使用
        int[] s1map = new int[26];
        int[] s2map = new int[26];
        for (int i = 0; i < s1.length(); i++) {
            s1map[s1.charAt(i) - 'a']++;
            s2map[s2.charAt(i) - 'a']++;
        }
        // 滑动窗口
        for (int i = 0; i < s2.length() - s1.length(); i++) {
            // 比较上一次滑动窗口 之后 的匹配结果
            if (matches(s1map, s2map)) {
                return true;
            }
            // 把位置i + s1.length()的字符 添加入数组
            s2map[s2.charAt(i + s1.length()) - 'a']++;
            // 把当前位置i的字符移除，因为当前不匹配
            s2map[s2.charAt(i) - 'a']--;
        }

        return matches(s1map, s2map);
    }

    private boolean matches(int[] s1map, int[] s2map) {
        for (int i = 0; i < 26; i++) {
            if (s1map[i] != s2map[i]) return false;
        }
        return true;
    }

    @Test
    public void widthOfBinaryTree() {
        /*TreeNode root = new TreeNode(5);
        TreeNode node2 = new TreeNode(3);
        TreeNode node3 = new TreeNode(6);
        TreeNode node4 = new TreeNode(2);
        TreeNode node5 = new TreeNode(4);
        root.left = node2;
        root.right = node3;
        node2.left = node4;
        node2.right = node5;*/
        TreeNode root = new TreeNode(1);
        TreeNode node2 = new TreeNode(3);
        TreeNode node3 = new TreeNode(2);
        TreeNode node4 = new TreeNode(5);
        TreeNode node5 = new TreeNode(3);
        TreeNode node6 = new TreeNode(3);
        root.left = node2;
        // root.right = node3;
        node2.left = node4;
        node2.right = node5;
        // node3.right = node6;
        int result = widthOfBinaryTree(root);
        log.debug("result:{}", result);
    }

    /**
     * 二叉树最大宽度 给定一个二叉树，编写一个函数来获取这个树的最大宽度。树的宽度是所有层中的最大宽度。这个二叉树与满二叉树（full binary tree）结构相同，但一些节点为空。
     *
     * <p>每一层的宽度被定义为两个端点（该层最左和最右的非空节点，两端点间的null节点也计入长度）之间的长度。
     *
     * <p>示例 1:
     *
     * <p>输入:
     *
     * <p>1 / \ 3 2 / \ \ 5 3 9
     *
     * <p>输出: 4 解释: 最大值出现在树的第 3 层，宽度为 4 (5,3,null,9)。 示例 2:
     *
     * <p>输入:
     *
     * <p>1 / 3 / \ 5 3
     *
     * <p>输出: 2 解释: 最大值出现在树的第 3 层，宽度为 2 (5,3)。 示例 3:
     *
     * <p>输入:
     *
     * <p>1 / \ 3 2 / 5
     *
     * <p>输出: 2 解释: 最大值出现在树的第 2 层，宽度为 2 (3,2)。 示例 4:
     *
     * <p>输入:
     *
     * <p>1 / \ 3 2 / \ 5 9 / \ 6 7 输出: 8 解释: 最大值出现在树的第 4 层，宽度为 8
     * (6,null,null,null,null,null,null,7)。 注意: 答案在32位有符号整数的表示范围内。
     *
     * @param root
     * @return
     */
    public int widthOfBinaryTree(TreeNode root) {
        int result = 0;
        if (root == null) {
            return result;
        }
        if (root.left == null && root.right == null) {
            return 1;
        }

        // 使用队列完成二叉树的按层遍历
        Queue<TreeNode> queue = new LinkedList<>();
        // 序号队列
        Queue<Integer> indexQueue = new LinkedList<>();
        // 根结点入队
        queue.offer(root);
        indexQueue.offer(0);
        while (!queue.isEmpty()) {

            int size = queue.size();
            int start = 0;
            int end = 0;
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                int index = indexQueue.poll();
                if (i == 0) start = index;
                if (i == size - 1) end = index;
                if (cur.left != null) {
                    queue.offer(cur.left);
                    indexQueue.offer(2 * index + 1);
                }
                if (cur.right != null) {
                    queue.offer(cur.right);
                    indexQueue.offer(2 * index + 2);
                }
            }
            result = Math.max(result, end - start + 1);
            // 取出队首结点 node
            /*TreeNode node = queue.poll();
            Integer index = indexQueue.poll();
            //layer.add(node.val);
            if (leftIndex == -1) {
                leftIndex = index;
            }

            // 左右子结点分别入队
            if (Objects.nonNull(node.left)) {
                queue.offer(node.left);
                indexQueue.offer(2 * index + 1);
                layerLast = node.left;
            }
            if (Objects.nonNull(node.right)) {
                queue.offer(node.right);
                indexQueue.offer(2 * index + 2);
                layerLast = node.right;
            }
            // layerLast 记录每行最后的结点
            // last 记录每行最后的结点
            if (last == node) {
                last = layerLast;
                result = Math.max(result, index - leftIndex + 1);
                leftIndex = -1;
            }*/
        }
        return result;
    }

    @Test
    public void isSameTree() {
        TreeNode p = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);

        p.left = node2;
        p.right = node3;

        TreeNode q = new TreeNode(1);
        TreeNode node4 = new TreeNode(2);
        TreeNode node5 = new TreeNode(5);
        q.left = node4;
        q.right = node5;
        logResult(isSameTree(p, q));

        // TreeNode node6 = new TreeNode(3);
    }

    /**
     * 100. 相同的树 给定两个二叉树，编写一个函数来检验它们是否相同。
     *
     * <p>如果两个树在结构上相同，并且节点具有相同的值，则认为它们是相同的。
     *
     * <p>示例 1:
     *
     * <p>输入: 1 1 / \ / \ 2 3 2 3
     *
     * <p>[1,2,3], [1,2,3]
     *
     * <p>输出: true 示例 2:
     *
     * <p>输入: 1 1 / \ 2 2
     *
     * <p>[1,2], [1,null,2]
     *
     * <p>输出: false 示例 3:
     *
     * <p>输入: 1 1 / \ / \ 2 1 1 2
     *
     * <p>[1,2,1], [1,1,2]
     *
     * <p>输出: false
     *
     * @param p
     * @param q
     * @return
     */
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        if (p == null || q == null) {
            return false;
        }
        if (p.val != q.val) {
            return false;
        }
        boolean b1 = isSameTree(p.left, q.left);
        boolean b2 = isSameTree(p.right, q.right);

        return b1 & b2;
    }

    @Test
    public void levelOrderBottom() {
        TreeNode root = new TreeNode(3);
        TreeNode node2 = new TreeNode(9);
        TreeNode node3 = new TreeNode(20);
        TreeNode node4 = new TreeNode(15);
        TreeNode node5 = new TreeNode(7);
        root.left = node2;
        root.right = node3;
        node3.left = node4;
        node3.right = node5;
        List<List<Integer>> result = levelOrderBottom(root);
        log.debug("result:{}", result);
    }

    /**
     * 107. 二叉树的层次遍历 II 给定一个二叉树，返回其节点值自底向上的层次遍历。 （即按从叶子节点所在层到根节点所在的层，逐层从左向右遍历）
     *
     * <p>例如： 给定二叉树 [3,9,20,null,null,15,7],
     *
     * <p>3 / \ 9 20 / \ 15 7 返回其自底向上的层次遍历为：
     *
     * <p>[ [15,7], [9,20], [3] ]
     *
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        LinkedList<List<Integer>> result = new LinkedList<>();

        if (Objects.isNull(root)) {
            return result;
        }

        // 通过队列实现
        LinkedList<TreeNode> nodeList = new LinkedList<>();

        nodeList.addLast(root);
        while (!nodeList.isEmpty()) {
            List<Integer> rowList = new ArrayList<>();
            int size = nodeList.size();
            for (int i = 0; i < size; i++) {
                TreeNode treeNode = nodeList.removeFirst();
                rowList.add(treeNode.val);
                if (treeNode.left != null) {
                    nodeList.addLast(treeNode.left);
                }
                if (treeNode.right != null) {
                    nodeList.addLast(treeNode.right);
                }
            }
            result.addFirst(rowList);
        }
        return result;
    }

    @Test
    public void minDepth() {
        TreeNode root = new TreeNode(3);
        TreeNode node2 = new TreeNode(9);
        TreeNode node3 = new TreeNode(20);
        TreeNode node4 = new TreeNode(15);
        TreeNode node5 = new TreeNode(7);
        root.left = node2;
        root.right = node3;
        node3.left = node4;
        node3.right = node5;
        logResult(minDepth(root));
    }

    /**
     * 111. 二叉树的最小深度 给定一个二叉树，找出其最小深度。
     *
     * <p>最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
     *
     * <p>说明: 叶子节点是指没有子节点的节点。
     *
     * <p>示例:
     *
     * <p>给定二叉树 [3,9,20,null,null,15,7],
     *
     * <p>3 / \ 9 20 / \ 15 7 返回它的最小深度 2.
     *
     * @param root
     * @return
     */
    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return 1;
        }
        int min = Integer.MAX_VALUE;
        if (root.left != null) {
            min = Math.min(minDepth(root.left), min);
        }
        if (root.right != null) {
            min = Math.min(minDepth(root.right), min);
        }

        return min + 1;
    }

    @Test
    public void invertTree() {
        TreeNode root = new TreeNode(4);
        TreeNode node2 = new TreeNode(7);
        TreeNode node3 = new TreeNode(2);
        TreeNode node4 = new TreeNode(9);
        TreeNode node5 = new TreeNode(6);
        TreeNode node6 = new TreeNode(3);
        TreeNode node7 = new TreeNode(1);
        root.left = node2;
        root.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.left = node6;
        node3.right = node7;
        logResult(levelOrderBottom(root));
    }

    /**
     * 翻转二叉树 翻转一棵二叉树。
     *
     * <p>示例：
     *
     * <p>输入：
     *
     * <p>4 / \ 2 7 / \ / \ 1 3 6 9 输出：
     *
     * <p>4 / \ 7 2 / \ / \ 9 6 3 1 备注: 这个问题是受到 Max Howell 的 原问题 启发的 ：
     *
     * <p>谷歌：我们90％的工程师使用您编写的软件(Homebrew)，但是您却无法在面试时在白板上写出翻转二叉树这道题，这太糟糕了。
     *
     * @param root
     * @return
     */
    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode left = root.left;
        root.left = root.right;
        root.right = left;
        invertTree(root.left);
        invertTree(root.right);
        return root;
    }

    @Test
    public void binaryTreePaths() {
        TreeNode root = new TreeNode(4);
        TreeNode node2 = new TreeNode(7);
        TreeNode node3 = new TreeNode(2);
        TreeNode node4 = new TreeNode(9);
        TreeNode node5 = new TreeNode(6);
        TreeNode node6 = new TreeNode(3);
        TreeNode node7 = new TreeNode(1);
        root.left = node2;
        root.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.left = node6;
        node3.right = node7;
        logResult(binaryTreePaths(root));
    }

    /**
     * 257. 二叉树的所有路径 给定一个二叉树，返回所有从根节点到叶子节点的路径。
     *
     * <p>说明: 叶子节点是指没有子节点的节点。
     *
     * <p>示例:
     *
     * <p>输入:
     *
     * <p>1 / \ 2 3 \ 5
     *
     * <p>输出: ["1->2->5", "1->3"]
     *
     * <p>解释: 所有根节点到叶子节点的路径为: 1->2->5, 1->3
     *
     * @param root
     * @return
     */
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> list = new ArrayList<>();
        // 深度优先遍历
        dfs(root, new StringBuilder(), list);

        return list;
    }

    private void dfs(TreeNode root, StringBuilder sb, List<String> list) {
        if (root == null) {
            return;
        }
        if (sb.length() != 0) {
            sb.append("->");
        }
        sb.append(root.val);
        if (root.left == null && root.right == null) {
            list.add(sb.toString());
            return;
        }
        int len = sb.length();
        if (root.left != null) {
            dfs(root.left, sb, list);
        }
        // 删除掉 前面的
        sb.setLength(len);
        if (root.right != null) {
            dfs(root.right, sb, list);
        }
    }

    @Test
    public void sumOfLeftLeaves() {
        TreeNode root = new TreeNode(4);
        TreeNode node2 = new TreeNode(7);
        TreeNode node3 = new TreeNode(2);
        TreeNode node4 = new TreeNode(9);
        TreeNode node5 = new TreeNode(6);
        TreeNode node6 = new TreeNode(3);
        TreeNode node7 = new TreeNode(1);
        root.left = node2;
        root.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.left = node6;
        node3.right = node7;
        logResult(sumOfLeftLeaves(root));
    }

    /**
     * 404. 左叶子之和 计算给定二叉树的所有左叶子之和。
     *
     * <p>示例：
     *
     * <p>3 / \ 9 20 / \ 15 7
     *
     * <p>在这个二叉树中，有两个左叶子，分别是 9 和 15，所以返回 24
     *
     * @param root
     * @return
     */
    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null) {
            return 0;
        }

        return leftLeave(root, false);
    }

    private int leftLeave(TreeNode root, boolean isLeft) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            if (isLeft) {
                return root.val;
            }
            return 0;
        }
        return leftLeave(root.left, true) + leftLeave(root.right, false);
    }

    @Test
    public void tree2str() {
        TreeNode root = new TreeNode(4);
        TreeNode node2 = new TreeNode(7);
        TreeNode node3 = new TreeNode(2);
        TreeNode node4 = new TreeNode(9);
        TreeNode node5 = new TreeNode(6);
        TreeNode node6 = new TreeNode(3);
        TreeNode node7 = new TreeNode(1);
        root.left = node2;
        root.right = node3;
        node2.left = node4;
        // node2.right = node5;
        // node3.left = node6;
        node3.right = node7;
        logResult(tree2str(root));
    }

    /**
     * 606. 根据二叉树创建字符串 你需要采用前序遍历的方式，将一个二叉树转换成一个由括号和整数组成的字符串。
     *
     * <p>空节点则用一对空括号 "()" 表示。而且你需要省略所有不影响字符串与原始二叉树之间的一对一映射关系的空括号对。
     *
     * <p>示例 1:
     *
     * <p>输入: 二叉树: [1,2,3,4] 1 / \ 2 3 / 4
     *
     * <p>输出: "1(2(4))(3)"
     *
     * <p>解释: 原本将是“1(2(4)())(3())”， 在你省略所有不必要的空括号对之后， 它将是“1(2(4))(3)”。 示例 2:
     *
     * <p>输入: 二叉树: [1,2,3,null,4] 1 / \ 2 3 \ 4
     *
     * <p>输出: "1(2()(4))(3)"
     *
     * <p>解释: 和第一个示例相似， 除了我们不能省略第一个对括号来中断输入和输出之间的一对一映射关系。
     *
     * @param t
     * @return
     */
    public String tree2str(TreeNode t) {
        StringBuilder sb = new StringBuilder();

        tree2str(t, sb);

        return sb.toString();
    }

    private void tree2str(TreeNode root, StringBuilder sb) {
        if (root == null) {
            return;
        }
        sb.append(root.val);
        //  right 存在是 left为空也需要加()
        if (root.left != null || root.right != null) {
            sb.append("(");
            tree2str(root.left, sb);
            sb.append(")");
        }
        if (root.right != null) {
            sb.append("(");
            tree2str(root.right, sb);
            sb.append(")");
        }
    }

    @Test
    public void mergeTrees() {
        TreeNode t1 = new TreeNode(1);
        TreeNode t2 = new TreeNode(2);

        TreeNode node1 = new TreeNode(3);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(5);
        t1.left = node1;
        t1.right = node2;
        node1.left = node3;

        TreeNode node4 = new TreeNode(1);
        TreeNode node5 = new TreeNode(3);
        TreeNode node6 = new TreeNode(4);
        TreeNode node7 = new TreeNode(7);
        t2.left = node4;
        t2.right = node5;
        node4.right = node6;
        node5.right = node7;

        TreeNode node = mergeTrees(t1, t2);
        BinTree tree = new BinTree();
        logResult(tree.serialize(node));
    }

    /**
     * 617. 合并二叉树 给定两个二叉树，想象当你将它们中的一个覆盖到另一个上时，两个二叉树的一些节点便会重叠。
     *
     * <p>你需要将他们合并为一个新的二叉树。合并的规则是如果两个节点重叠，那么将他们的值相加作为节点合并后的新值， 否则不为 NULL 的节点将直接作为新二叉树的节点。
     *
     * <p>示例 1:
     *
     * <p>输入: Tree 1 Tree 2 1 2 / \ / \ 3 2 1 3 / \ \ 5 4 7 输出: 合并后的树: 3 / \ 4 5 / \ \ 5 4 7 注意:
     * 合并必须从两个树的根节点开始。
     *
     * @param t1
     * @param t2
     * @return
     */
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if (t1 == null) {
            return t2;
        }
        if (t2 == null) {
            return t1;
        }
        t1.val += t2.val;
        t1.left = mergeTrees(t1.left, t2.left);
        t1.right = mergeTrees(t1.right, t2.right);

        return t1;
    }

    @Test
    public void diameterOfBinaryTree() {
        TreeNode root = new TreeNode(4);
        TreeNode node2 = new TreeNode(7);
        TreeNode node3 = new TreeNode(2);
        TreeNode node4 = new TreeNode(9);
        TreeNode node5 = new TreeNode(6);
        TreeNode node6 = new TreeNode(3);
        TreeNode node7 = new TreeNode(1);
        root.left = node2;
        root.right = node3;
        node2.left = node4;
        node2.right = node5;
        node4.left = node6;
        node5.right = node7;
        node6.left = new TreeNode(2);
        node7.left = new TreeNode(2);
        logResult(diameterOfBinaryTree(root));
    }

    /**
     * 543. 二叉树的直径 给定一棵二叉树，你需要计算它的直径长度。一棵二叉树的直径长度是任意两个结点路径长度中的最大值。这条路径可能穿过根结点。
     *
     * <p>示例 : 给定二叉树
     *
     * <p>1 / \ 2 3 / \ 4 5 返回 3, 它的长度是路径 [4,2,1,3] 或者 [5,2,1,3]。
     *
     * <p>注意：两结点之间的路径长度是以它们之间边的数目表示。
     *
     * @param root
     * @return
     */
    public int diameterOfBinaryTree(TreeNode root) {

        if (root == null) {
            return 0;
        }
        int leftLen = getLen(root.left);
        int rightLen = getLen(root.right);

        return Math.max(leftLen + rightLen, len);
    }

    private static int len = 0;

    private int getLen(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftLen = getLen(root.left);
        int rightLen = getLen(root.right);
        if (leftLen + rightLen > len) {
            len = leftLen + rightLen;
        }

        return Math.max(leftLen, rightLen) + 1;
    }

    static int minDiff = Integer.MAX_VALUE;

    /**
     * 563. 二叉树的坡度 给定一个二叉树，计算整个树的坡度。
     *
     * <p>一个树的节点的坡度定义即为，该节点左子树的结点之和和右子树结点之和的差的绝对值。空结点的的坡度是0。
     *
     * <p>整个树的坡度就是其所有节点的坡度之和。
     *
     * <p>示例:
     *
     * <p>输入: 1 / \ 2 3 输出: 1 解释: 结点的坡度 2 : 0 结点的坡度 3 : 0 结点的坡度 1 : |2-3| = 1 树的坡度 : 0 + 0 + 1 = 1
     * 注意:
     *
     * <p>任何子树的结点的和不会超过32位整数的范围。 坡度的值不会超过32位整数的范围。
     *
     * @param root
     * @return
     */
    public int findTilt(TreeNode root) {
        traverse(root);

        return tilt;
    }

    static int tilt = 0;

    public int traverse(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = traverse(root.left);
        int right = traverse(root.right);
        tilt += Math.abs(left - right);
        return left + root.val + right;
    }

    /**
     * 572. 另一个树的子树 给定两个非空二叉树 s 和 t，检验 s 中是否包含和 t 具有相同结构和节点值的子树。s 的一个子树包括 s 的一个节点和这个节点的所有子孙。s
     * 也可以看做它自身的一棵子树。
     *
     * <p>示例 1: 给定的树 s:
     *
     * <p>3 / \ 4 5 / \ 1 2 给定的树 t：
     *
     * <p>4 / \ 1 2 返回 true，因为 t 与 s 的一个子树拥有相同的结构和节点值。
     *
     * <p>示例 2: 给定的树 s：
     *
     * <p>3 / \ 4 5 / \ 1 2 / 0 给定的树 t：
     *
     * <p>4 / \ 1 2 返回 false。
     *
     * @param s
     * @param t
     * @return
     */
    public boolean isSubtree(TreeNode s, TreeNode t) {
        if (s == null) {
            return false;
        }
        boolean b = isSameTree(s, t);
        if (b) {
            return true;
        }

        return isSubtree(s.left, t) || isSubtree(s.right, t);
    }

    @Test
    public void averageOfLevels() {
        TreeNode root = new TreeNode(4);
        TreeNode node2 = new TreeNode(7);
        TreeNode node3 = new TreeNode(2);
        TreeNode node4 = new TreeNode(9);
        TreeNode node5 = new TreeNode(6);
        TreeNode node6 = new TreeNode(3);
        TreeNode node7 = new TreeNode(1);
        root.left = node2;
        root.right = node3;
        node2.left = node4;
        node2.right = node5;
        node4.left = node6;
        node5.right = node7;
        node6.left = new TreeNode(2);
        node7.left = new TreeNode(2);
        logResult(averageOfLevels(root));
    }

    /**
     * 637. 二叉树的层平均值 给定一个非空二叉树, 返回一个由每层节点平均值组成的数组.
     *
     * <p>示例 1:
     *
     * <p>输入: 3 / \ 9 20 / \ 15 7 输出: [3, 14.5, 11] 解释: 第0层的平均值是 3, 第1层是 14.5, 第2层是 11. 因此返回 [3,
     * 14.5, 11]. 注意：
     *
     * <p>节点值的范围在32位有符号整数范围内。
     *
     * @param root
     * @return
     */
    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> result = new ArrayList<>();
        // 按层遍历,使用队列
        Queue<TreeNode> queue = new LinkedList<>();
        if (root == null) {
            return result;
        }
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            double sum = 0;
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                sum += node.val;
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            double avg = sum / (double) size;
            result.add(avg);
        }

        return result;
    }

    /**
     * 653. 两数之和 IV - 输入 BST 给定一个二叉搜索树和一个目标结果，如果 BST 中存在两个元素且它们的和等于给定的目标结果，则返回 true。
     *
     * <p>案例 1:
     *
     * <p>输入: 5 / \ 3 6 / \ \ 2 4 7
     *
     * <p>Target = 9
     *
     * <p>输出: True
     *
     * <p>案例 2:
     *
     * <p>输入: 5 / \ 3 6 / \ \ 2 4 7
     *
     * <p>Target = 28
     *
     * <p>输出: False
     *
     * @param root
     * @param k
     * @return
     */
    public boolean findTarget(TreeNode root, int k) {
        Set<Integer> set = new HashSet<>();
        return findTarget(root, k, set);
    }

    private boolean findTarget(TreeNode node, int k, Set<Integer> set) {
        if (node == null) {
            return false;
        }
        if (set.contains(k - node.val)) {
            return true;
        }
        set.add(node.val);
        return findTarget(node.left, k, set) || findTarget(node.right, k, set);
    }

    @Test
    public void judgeCircle() {
        String moves = "UD";
        logResult(judgeCircle(moves));
    }

    /**
     * 657. 机器人能否返回原点 在二维平面上，有一个机器人从原点 (0, 0) 开始。给出它的移动顺序，判断这个机器人在完成移动后是否在 (0, 0) 处结束。
     *
     * <p>移动顺序由字符串表示。字符 move[i] 表示其第 i 次移动。机器人的有效动作有 R（右），L（左），U（上）和 D（下）。如果机器人在完成所有动作后返回原点，则返回
     * true。否则，返回 false。
     *
     * <p>注意：机器人“面朝”的方向无关紧要。 “R” 将始终使机器人向右移动一次，“L” 将始终向左移动等。此外，假设每次移动机器人的移动幅度相同。
     *
     * <p>示例 1:
     *
     * <p>输入: "UD" 输出: true 解释：机器人向上移动一次，然后向下移动一次。所有动作都具有相同的幅度，因此它最终回到它开始的原点。因此，我们返回 true。 示例 2:
     *
     * <p>输入: "LL" 输出: false 解释：机器人向左移动两次。它最终位于原点的左侧，距原点有两次 “移动” 的距离。我们返回 false，因为它在移动结束时没有返回原点。
     *
     * @param moves
     * @return
     */
    public boolean judgeCircle(String moves) {

        int x = 0, y = 0;
        for (char c : moves.toCharArray()) {
            switch (c) {
                case 'L':
                    x--;
                    break;
                case 'R':
                    x++;
                    break;
                case 'U':
                    y++;
                    break;
                case 'D':
                    y--;
                    break;
            }
        }
        return x == 0 && y == 0;
    }

    @Test
    public void longestUnivaluePath() {
        TreeNode root = new TreeNode(5);
        TreeNode node2 = new TreeNode(4);
        TreeNode node3 = new TreeNode(5);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(4);
        TreeNode node6 = new TreeNode(5);
        // TreeNode node7 = new TreeNode(1);
        root.left = node2;
        root.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.right = node6;
        // node5.right = node7;
        node6.left = new TreeNode(5);
        // node7.left = new TreeNode(2);
        logResult(longestUnivaluePath(root));
    }

    /**
     * 687. 最长同值路径 给定一个二叉树，找到最长的路径，这个路径中的每个节点具有相同值。 这条路径可以经过也可以不经过根节点。
     *
     * <p>注意：两个节点之间的路径长度由它们之间的边数表示。
     *
     * <p>示例 1:
     *
     * <p>输入:
     *
     * <p>5 / \ 4 5 / \ \ 1 1 5 输出:
     *
     * <p>2 示例 2:
     *
     * <p>输入:
     *
     * <p>1 / \ 4 5 / \ \ 4 4 5 输出:
     *
     * <p>2 注意: 给定的二叉树不超过10000个结点。 树的高度不超过1000。
     *
     * @param root
     * @return
     */
    public int longestUnivaluePath(TreeNode root) {

        lenTreePath(root);
        return longestResult;
    }

    static int longestResult = 0;

    private int lenTreePath(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int val = root.val;
        int leftLen = lenTreePath(root.left);

        int rightLen = lenTreePath(root.right);

        int rootLeftLen = 0, rootRightLen = 0;
        if (root.left != null && val == root.left.val) {
            rootLeftLen = leftLen + 1;
        }
        if (root.right != null && val == root.right.val) {
            rootRightLen = rightLen + 1;
        }

        longestResult = Math.max(longestResult, rootLeftLen + rootRightLen);

        return Math.max(rootLeftLen, rootRightLen);
    }

    @Test
    public void findSecondMinimumValue() {

        TreeNode root = new TreeNode(2);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(Integer.MAX_VALUE);
        TreeNode node4 = new TreeNode(5);
        TreeNode node5 = new TreeNode(7);
        TreeNode node6 = new TreeNode(5);
        // TreeNode node7 = new TreeNode(1);
        root.left = node2;
        root.right = node3;
        // node2.left = node4;
        // node2.right = node5;
        // node3.right = node6;
        // node5.right = node7;
        // node6.left = new TreeNode(5);
        // node7.left = new TreeNode(2);
        logResult(findSecondMinimumValue(root));
    }

    /**
     * 671. 二叉树中第二小的节点 给定一个非空特殊的二叉树，每个节点都是正数，并且每个节点的子节点数量只能为 2 或 0。
     * 如果一个节点有两个子节点的话，那么这个节点的值不大于它的子节点的值。
     *
     * <p>给出这样的一个二叉树，你需要输出所有节点中的第二小的值。如果第二小的值不存在的话，输出 -1 。
     *
     * <p>示例 1:
     *
     * <p>输入: 2 / \ 2 5 / \ 5 7
     *
     * <p>输出: 5 说明: 最小的值是 2 ，第二小的值是 5 。 示例 2:
     *
     * <p>输入: 2 / \ 2 2
     *
     * <p>输出: -1 说明: 最小的值是 2, 但是不存在第二小的值。
     *
     * @param root
     * @return
     */
    public int findSecondMinimumValue(TreeNode root) {
        // 每个节点都是正数，并且每个节点的子节点数量只能为 2 或 0。
        // 如果一个节点有两个子节点的话，那么这个节点的值不大于它的子节点的值。
        // 最小值为root.val
        // 让  min1 = root.val。当遍历结点 node，如果 node.val > min1，
        // 我们知道在 node 处的子树中的所有值都至少是 node.val，因此在该子树中不此存在第二个最小值。因此，我们不需要搜索这个子树。
        // 此外，由于我们只关心第二个最小值  ans，
        // 因此我们不需要记录任何大于当前第二个最小值的值，因此与方法 1 不同，我们可以完全不用集合存储数据。
        int min = root.val;
        secountMin = -1;
        findSecondMin(root, min);
        return secountMin;
    }

    static int secountMin;

    public void findSecondMin(TreeNode root, int min) {
        int val = root.val;
        if (val == min) {
            if (root.left != null) {
                findSecondMin(root.left, min);
                findSecondMin(root.right, min);
            }
        } else {
            if (secountMin == -1 || val < secountMin) {
                secountMin = val;
            }
        }
    }

    @Test
    public void leafSimilar() {
        TreeNode root1 = new TreeNode(5);
        TreeNode node2 = new TreeNode(4);
        TreeNode node3 = new TreeNode(5);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(4);
        TreeNode node6 = new TreeNode(5);
        // TreeNode node7 = new TreeNode(1);
        root1.left = node2;
        root1.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.right = node6;
        // node5.right = node7;
        node6.left = new TreeNode(5);
        logResult(getLeaf(root1));
    }

    /**
     * 872. 叶子相似的树 请考虑一颗二叉树上所有的叶子，这些叶子的值按从左到右的顺序排列形成一个 叶值序列 。
     *
     * <p>3 / \ 5 1 / \ / \ 6 2 9 8 / \ 7 4 举个例子，如上图所示，给定一颗叶值序列为 (6, 7, 4, 9, 8) 的树。
     *
     * <p>如果有两颗二叉树的叶值序列是相同，那么我们就认为它们是 叶相似 的。
     *
     * <p>如果给定的两个头结点分别为 root1 和 root2 的树是叶相似的，则返回 true；否则返回 false 。
     *
     * <p>提示：
     *
     * <p>给定的两颗树可能会有 1 到 200 个结点。 给定的两颗树上的值介于 0 到 200 之间。
     *
     * @param root1
     * @param root2
     * @return
     */
    public boolean leafSimilar(TreeNode root1, TreeNode root2) {

        List<Integer> list1 = getLeaf(root1);
        logResult(list1);
        List<Integer> list2 = getLeaf(root2);
        logResult(list2);
        if (list1.size() != list2.size()) {
            return false;
        }
        for (int i = 0; i < list1.size(); i++) {
            if (!Objects.equals(list1.get(i), list2.get(i))) {
                return false;
            }
        }

        return true;
    }

    private List<Integer> getLeaf(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        // 使用栈深度优先遍历
        Deque<TreeNode> stack = new LinkedList<>();
        stack.addLast(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.removeLast();
            boolean isLeaf = true;
            if (node.right != null) {
                isLeaf = false;
                stack.addLast(node.right);
            }
            if (node.left != null) {
                isLeaf = false;
                stack.addLast(node.left);
            }
            if (isLeaf) {
                list.add(node.val);
            }
        }

        return list;
    }

    /**
     * 897. 递增顺序查找树 给你一个树，请你 按中序遍历 重新排列树，使树中最左边的结点现在是树的根，并且每个结点没有左子结点，只有一个右子结点。
     *
     * <p>示例 ：
     *
     * <p>输入：[5,3,6,2,4,null,8,1,null,null,null,7,9]
     *
     * <p>5 / \ 3 6 / \ \ 2 4 8 / / \ 1 7 9
     *
     * <p>输出：[1,null,2,null,3,null,4,null,5,null,6,null,7,null,8,null,9]
     *
     * <p>1 \ 2 \ 3 \ 4 \ 5 \ 6 \ 7 \ 8 \ 9
     *
     * <p>提示：
     *
     * <p>给定树中的结点数介于 1 和 100 之间。 每个结点都有一个从 0 到 1000 范围内的唯一整数值。
     *
     * @param root
     * @return
     */
    public TreeNode increasingBST(TreeNode root) {

        if (root == null) {
            return null;
        }
        List<TreeNode> list = new ArrayList<>();
        // 中序遍历
        increasingBST(root, list);
        TreeNode head = list.get(0);
        TreeNode node = head;
        for (int i = 1; i < list.size(); i++) {
            node.left = null;
            TreeNode next = list.get(i);
            node.right = next;
            node = next;
        }
        return head;
    }

    private void increasingBST(TreeNode root, List<TreeNode> list) {
        if (root == null) {
            return;
        }
        increasingBST(root.left, list);
        list.add(root);
        increasingBST(root.right, list);
    }

    /**
     * 965. 单值二叉树 如果二叉树每个节点都具有相同的值，那么该二叉树就是单值二叉树。
     *
     * <p>只有给定的树是单值二叉树时，才返回 true；否则返回 false。
     *
     * <p>示例 1：
     *
     * <p>输入：[1,1,1,1,1,null,1] 输出：true 示例 2：
     *
     * <p>输入：[2,2,2,5,2] 输出：false
     *
     * <p>提示：
     *
     * <p>给定树的节点数范围是 [1, 100]。 每个节点的值都是整数，范围为 [0, 99] 。
     *
     * @param root
     * @return
     */
    public boolean isUnivalTree(TreeNode root) {
        if (root == null) {
            return false;
        }
        int val = root.val;

        return isUnivalTree(root, val);
    }

    private boolean isUnivalTree(TreeNode node, int val) {
        if (node == null) {
            return true;
        }
        if (node.val != val) {
            return false;
        }
        return isUnivalTree(node.left, val) && isUnivalTree(node.right, val);
    }

    /**
     * 993. 二叉树的堂兄弟节点 在二叉树中，根节点位于深度 0 处，每个深度为 k 的节点的子节点位于深度 k+1 处。
     *
     * <p>如果二叉树的两个节点深度相同，但父节点不同，则它们是一对堂兄弟节点。
     *
     * <p>我们给出了具有唯一值的二叉树的根节点 root，以及树中两个不同节点的值 x 和 y。
     *
     * <p>只有与值 x 和 y 对应的节点是堂兄弟节点时，才返回 true。否则，返回 false。
     *
     * <p>示例 1：
     *
     * <p>输入：root = [1,2,3,4], x = 4, y = 3 输出：false 示例 2：
     *
     * <p>输入：root = [1,2,3,null,4,null,5], x = 5, y = 4 输出：true 示例 3：
     *
     * <p>输入：root = [1,2,3,null,4], x = 2, y = 3 输出：false
     *
     * <p>提示：
     *
     * <p>二叉树的节点数介于 2 到 100 之间。 每个节点的值都是唯一的、范围为 1 到 100 的整数。
     *
     * @param root
     * @param x
     * @param y
     * @return
     */
    public boolean isCousins(TreeNode root, int x, int y) {

        Queue<TreeNode> queue = new LinkedList<>();
        int xDepth = 0, yDepth = 0;
        TreeNode xParent = null, yParent = null;
        queue.offer(root);

        int depth = 1;
        while (!queue.isEmpty()) {
            depth++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();

                if (node.left != null) {
                    queue.offer(node.left);
                    if (node.left.val == x) {
                        xDepth = depth;
                        xParent = node;
                    }
                    if (node.left.val == y) {
                        yDepth = depth;
                        yParent = node;
                    }
                }
                if (node.right != null) {
                    queue.offer(node.right);
                    if (node.right.val == x) {
                        xDepth = depth;
                        xParent = node;
                    }
                    if (node.right.val == y) {
                        yDepth = depth;
                        yParent = node;
                    }
                }
            }
        }

        return xDepth == yDepth && !Objects.equals(xParent, yParent);
    }

    @Test
    public void sumRootToLeaf() {
        TreeNode root = new TreeNode(1);
        TreeNode node2 = new TreeNode(0);
        TreeNode node3 = new TreeNode(1);
        TreeNode node4 = new TreeNode(0);
        TreeNode node5 = new TreeNode(1);
        TreeNode node6 = new TreeNode(0);
        TreeNode node7 = new TreeNode(1);
        root.left = node2;
        root.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.left = node6;
        node3.right = node7;
        logResult(sumRootToLeaf(root));
    }

    /**
     * 1022. 从根到叶的二进制数之和 给出一棵二叉树，其上每个结点的值都是 0 或 1 。每一条从根到叶的路径都代表一个从最高有效位开始的二进制数。例如，如果路径为 0 -> 1 -> 1
     * -> 0 -> 1，那么它表示二进制数 01101，也就是 13 。
     *
     * <p>对树上的每一片叶子，我们都要找出从根到该叶子的路径所表示的数字。
     *
     * <p>以 10^9 + 7 为模，返回这些数字之和。
     *
     * <p>示例：
     *
     * <p>1 / \ 0 1 / \ / \ 0 1 0 1
     *
     * <p>输入：[1,0,1,0,1,0,1] 输出：22 解释：(100) + (101) + (110) + (111) = 4 + 5 + 6 + 7 = 22
     *
     * <p>提示：
     *
     * <p>树中的结点数介于 1 和 1000 之间。 node.val 为 0 或 1 。
     *
     * @param root
     * @return
     */
    public int sumRootToLeaf(TreeNode root) {

        sumRootToLeaf(root, 0);
        return sumLeaf;
    }

    public void sumRootToLeaf(TreeNode root, int num) {
        if (root == null) {
            return;
        }
        boolean bLeft = root.left != null;
        boolean bRight = root.right != null;
        num = num * 2 + root.val;
        if (!bLeft && !bRight) {
            sumLeaf += num;
        }
        if (bLeft) {
            sumRootToLeaf(root.left, num);
        }
        if (bRight) {
            sumRootToLeaf(root.right, num);
        }
    }

    static int sumLeaf = 0;

    /**
     * 面试题 04.02. 最小高度树 给定一个有序整数数组，元素各不相同且按升序排列，编写一个算法，创建一棵高度最小的二叉搜索树。
     *
     * <p>示例: 给定有序数组: [-10,-3,0,5,9],
     *
     * <p>一个可能的答案是：[0,-3,9,-10,null,5]，它可以表示下面这个高度平衡二叉搜索树：
     *
     * <p>0 / \ -3 9 / / -10 5
     *
     * @param nums
     * @return
     */
    public TreeNode sortedArrayToBST(int[] nums) {

        return sortedArrayToBST(nums, 0, nums.length - 1);
    }

    private TreeNode sortedArrayToBST(int[] nums, int start, int end) {
        if (start == end) {
            return new TreeNode(nums[start]);
        }

        if (start > end) {
            return null;
        }

        int mid = (start + end) >> 1;
        TreeNode node = new TreeNode(nums[mid]);
        node.left = sortedArrayToBST(nums, start, mid - 1);
        node.right = sortedArrayToBST(nums, mid + 1, end);
        return node;
    }

    /**
     * 199. 二叉树的右视图 给定一棵二叉树，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。
     *
     * <p>示例:
     *
     * <p>输入: [1,2,3,null,5,null,4] 输出: [1, 3, 4] 解释:
     *
     * <p>1 <--- / \ 2 3 <--- \ \ 5 4 <--- 通过次数38,544提交次数60,594
     *
     * @param root
     * @return
     */
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            int val = 0;
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                val = node.val;
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            list.add(val);
        }

        return list;
    }

    static boolean balanced = true;

    /**
     * 面试题 04.04. 检查平衡性 实现一个函数，检查二叉树是否平衡。在这个问题中，平衡树的定义如下：任意一个节点，其两棵子树的高度差不超过 1。
     *
     * <p>示例 1: 给定二叉树 [3,9,20,null,null,15,7] 3 / \ 9 20 / \ 15 7 返回 true 。 示例 2: 给定二叉树
     * [1,2,2,3,3,null,null,4,4] 1 / \ 2 2 / \ 3 3 / \ 4 4 返回 false 。
     *
     * @param root
     * @return
     */
    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }

        getDepth(root);
        return balanced;
    }

    private int getDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = getDepth(root.left);
        int right = getDepth(root.right);
        if (Math.abs(left - right) > 1) {
            balanced = false;
            return 0;
        }
        return Math.max(left, right) + 1;
    }

    @Test
    public void mirrorTree() {
        TreeNode root = new TreeNode(4);
        TreeNode node2 = new TreeNode(7);
        TreeNode node3 = new TreeNode(2);
        TreeNode node4 = new TreeNode(9);
        TreeNode node5 = new TreeNode(6);
        TreeNode node6 = new TreeNode(3);
        TreeNode node7 = new TreeNode(1);
        root.left = node2;
        root.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.left = node6;
        node3.right = node7;
        logResult(mirrorTree(root));
    }

    /**
     * 面试题27. 二叉树的镜像 请完成一个函数，输入一个二叉树，该函数输出它的镜像。
     *
     * <p>例如输入：
     *
     * <p>4 / \ 2 7 / \ / \ 1 3 6 9 镜像输出：
     *
     * <p>4 / \ 7 2 / \ / \ 9 6 3 1
     *
     * <p>示例 1：
     *
     * <p>输入：root = [4,2,7,1,3,6,9] 输出：[4,7,2,9,6,3,1]
     *
     * <p>限制：
     *
     * <p>0 <= 节点个数 <= 1000
     *
     * @param root
     * @return
     */
    public TreeNode mirrorTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        if (root.left == null && root.right == null) {
            return root;
        }

        // root.left = mirrorTree(root.right);
        // root.right = mirrorTree(root.left);

        TreeNode left = mirrorTree(root.right);
        TreeNode right = mirrorTree(root.left);

        root.left = left;
        root.right = right;
        return root;
    }

    /**
     * 面试题28. 对称的二叉树 请实现一个函数，用来判断一棵二叉树是不是对称的。如果一棵二叉树和它的镜像一样，那么它是对称的。
     *
     * <p>例如，二叉树 [1,2,2,3,4,4,3] 是对称的。
     *
     * <p>1 / \ 2 2 / \ / \ 3 4 4 3 但是下面这个 [1,2,2,null,3,null,3] 则不是镜像对称的:
     *
     * <p>1 / \ 2 2 \ \ 3 3
     *
     * <p>示例 1：
     *
     * <p>输入：root = [1,2,2,3,4,4,3] 输出：true 示例 2：
     *
     * <p>输入：root = [1,2,2,null,3,null,3] 输出：false
     *
     * @param root
     * @return
     */
    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }

        /*Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();


        }*/
        return isSymmetric(root.left, root.right);
    }

    public boolean isSymmetric(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        }
        if (left == null || right == null) {
            return false;
        }
        if (left.val != right.val) {
            return false;
        }
        return isSymmetric(left.left, right.right) && isSymmetric(left.right, right.left);
    }

    /**
     * 面试题55 - I. 二叉树的深度 输入一棵二叉树的根节点，求该树的深度。从根节点到叶节点依次经过的节点（含根、叶节点）形成树的一条路径，最长路径的长度为树的深度。
     *
     * <p>例如：
     *
     * <p>给定二叉树 [3,9,20,null,null,15,7]，
     *
     * <p>3 / \ 9 20 / \ 15 7 返回它的最大深度 3 。
     *
     * <p>提示：
     *
     * <p>节点总数 <= 10000
     *
     * @param root
     * @return
     */
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = maxDepth(root.left);
        int right = maxDepth(root.right);

        return Math.max(left, right) + 1;
    }

    /**
     * 面试题32 - II. 从上到下打印二叉树 II 从上到下按层打印二叉树，同一层的节点按从左到右的顺序打印，每一层打印到一行。
     *
     * <p>例如: 给定二叉树: [3,9,20,null,null,15,7],
     *
     * <p>3 / \ 9 20 / \ 15 7 返回其层次遍历结果：
     *
     * <p>[ [3], [9,20], [15,7] ]
     *
     * <p>提示：
     *
     * <p>节点总数 <= 1000
     *
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> list = new ArrayList<>(size);
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                list.add(node.val);
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            result.add(list);
        }
        return result;
    }

    /**
     * 面试题54. 二叉搜索树的第k大节点 给定一棵二叉搜索树，请找出其中第k大的节点。
     *
     * <p>示例 1:
     *
     * <p>输入: root = [3,1,4,null,2], k = 1 3 / \ 1 4 \ 2 输出: 4 示例 2:
     *
     * <p>输入: root = [5,3,6,2,4,null,null,1], k = 3 5 / \ 3 6 / \ 2 4 / 1 输出: 4
     *
     * <p>限制：
     *
     * <p>1 ≤ k ≤ 二叉搜索树元素个数
     *
     * @param root
     * @param k
     * @return
     */
    public int kthLargest(TreeNode root, int k) {

        kthLargest2(root, k);

        return kthResult;
    }

    private void kthLargest2(TreeNode root, int k) {
        if (root == null) {
            return;
        }

        kthLargest2(root.right, k);

        kthIndex++;
        if (kthIndex == k) {
            kthResult = root.val;
        }

        kthLargest2(root.left, k);
    }

    static int kthIndex = 0, kthResult = -1;

    /**
     * 235. 二叉搜索树的最近公共祖先 给定一个二叉搜索树, 找到该树中两个指定节点的最近公共祖先。
     *
     * <p>百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x， 满足 x 是 p、q 的祖先且 x
     * 的深度尽可能大（一个节点也可以是它自己的祖先）。”
     *
     * <p>例如，给定如下二叉搜索树: root = [6,2,8,0,4,7,9,null,null,3,5]
     *
     * <p>示例 1:
     *
     * <p>输入: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 8 输出: 6 解释: 节点 2 和节点 8 的最近公共祖先是 6。 示例
     * 2:
     *
     * <p>输入: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 4 输出: 2 解释: 节点 2 和节点 4 的最近公共祖先是 2,
     * 因为根据定义最近公共祖先节点可以为节点本身。
     *
     * <p>说明:
     *
     * <p>所有节点的值都是唯一的。 p、q 为不同节点且均存在于给定的二叉搜索树中。
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // 二叉搜索树 特殊性
        if (root == null) {
            return null;
        }
        TreeNode node = p;
        if (p.val > q.val) {
            p = q;
            q = node;
        }

        if (root.val >= p.val && root.val <= q.val) {
            return root;
        }

        if (root.val > q.val) {
            return lowestCommonAncestor(root.left, p, q);
        }
        if (root.val < p.val) {
            return lowestCommonAncestor(root.right, p, q);
        }

        return null;
    }

    @Test
    public void buildTree3() {
        /*int [] preorder = {3,9,20,15,7};
        int [] inorder = {9,3,15,20,7};*/
        int[] preorder = {1, 2, 4, 5, 3, 6, 7};
        int[] inorder = {4, 2, 5, 1, 6, 3, 7};

        TreeNode treeNode = buildTree(preorder, inorder);
        log.debug("result:{} ", treeNode.val);
    }

    /**
     * 面试题07. 重建二叉树 输入某二叉树的前序遍历和中序遍历的结果，请重建该二叉树。假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
     *
     * <p>例如，给出
     *
     * <p>前序遍历 preorder = [3,9,20,15,7] 中序遍历 inorder = [9,3,15,20,7] 返回如下的二叉树：
     *
     * <p>3 / \ 9 20 / \ 15 7
     *
     * <p>限制：
     *
     * <p>0 <= 节点个数 <= 5000
     *
     * @param preorder
     * @param inorder
     * @return
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        // 前序遍历 DLR
        // 中序遍历 LDR

        // 采用递归方案
        int len = preorder.length;
        if (len == 0) {
            return null;
        }
        Map<Integer, Integer> indexMap = new HashMap<>();
        for (int i = 0; i < len; i++) {
            indexMap.put(inorder[i], i);
        }
        TreeNode root = buildTree(preorder, 0, len - 1, inorder, 0, len - 1, indexMap);

        return root;
    }

    private TreeNode buildTree(
            int[] preorder,
            int preStart,
            int preEnd,
            int[] inorder,
            int inStart,
            int inEnd,
            Map<Integer, Integer> indexMap) {
        int rootVal = preorder[preStart];
        TreeNode root = new TreeNode(rootVal);
        if (preStart == preEnd) {
            return root;
        }
        int inIndex = indexMap.get(rootVal);
        int leftLen = inIndex - inStart;
        int rightLen = inEnd - inIndex;
        log.debug("inIndex:{},leftLen:{},rightLen:{}", inIndex, leftLen, rightLen);
        if (leftLen > 0) {
            root.left =
                    buildTree(
                            preorder,
                            preStart + 1,
                            preStart + leftLen,
                            inorder,
                            inStart,
                            inIndex - 1,
                            indexMap);
        }
        if (rightLen > 0) {
            root.right =
                    buildTree(
                            preorder,
                            preEnd - rightLen + 1,
                            preEnd,
                            inorder,
                            inIndex + 1,
                            inEnd,
                            indexMap);
        }
        return root;
    }

    @Test
    public void levelOrder3() {
        TreeNode root = new TreeNode(4);
        TreeNode node2 = new TreeNode(7);
        TreeNode node3 = new TreeNode(2);
        TreeNode node4 = new TreeNode(9);
        TreeNode node5 = new TreeNode(6);
        TreeNode node6 = new TreeNode(3);
        TreeNode node7 = new TreeNode(1);
        root.left = node2;
        root.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.left = node6;
        node3.right = node7;
        logResult(levelOrder3(root));
    }

    /**
     * 面试题32 - III. 从上到下打印二叉树 III
     * 请实现一个函数按照之字形顺序打印二叉树，即第一行按照从左到右的顺序打印，第二层按照从右到左的顺序打印，第三行再按照从左到右的顺序打印，其他行以此类推。
     *
     * <p>例如: 给定二叉树: [3,9,20,null,null,15,7],
     *
     * <p>3 / \ 9 20 / \ 15 7 返回其层次遍历结果：
     *
     * <p>[ [3], [20,9], [15,7] ]
     *
     * <p>提示：
     *
     * <p>节点总数 <= 1000
     *
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder3(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        // 从左到右为true
        boolean flag = true;
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (flag) {
                    list.add(node.val);
                } else {
                    list.add(0, node.val);
                }
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            result.add(list);
            flag = !flag;
        }
        return result;
    }

    /**
     * 面试题26. 树的子结构 输入两棵二叉树A和B，判断B是不是A的子结构。(约定空树不是任意一个树的子结构)
     *
     * <p>B是A的子结构， 即 A中有出现和B相同的结构和节点值。
     *
     * <p>例如: 给定的树 A:
     *
     * <p>3 / \ 4 5 / \ 1 2 给定的树 B：
     *
     * <p>4 / 1 返回 true，因为 B 与 A 的一个子树拥有相同的结构和节点值。
     *
     * <p>示例 1：
     *
     * <p>输入：A = [1,2,3], B = [3,1] 输出：false 示例 2：
     *
     * <p>输入：A = [3,4,5,1,2], B = [4,1] 输出：true 限制：
     *
     * <p>0 <= 节点个数 <= 10000
     *
     * @param A
     * @param B
     * @return
     */
    public boolean isSubStructure(TreeNode A, TreeNode B) {

        if (Objects.isNull(A) || Objects.isNull(B)) {
            return false;
        }
        if (A.val == B.val && isSubStructure2(A, B)) {
            return true;
        }
        return isSubStructure(A.left, B) || isSubStructure(A.right, B);
    }

    private boolean isSubStructure2(TreeNode A, TreeNode B) {
        if (Objects.isNull(B)) {
            return true;
        }
        if (Objects.isNull(A)) {
            return false;
        }
        if (A.val != B.val) {
            return false;
        }

        return isSubStructure2(A.left, B.left) && isSubStructure2(A.right, B.right);
    }

    @Test
    public void pathSum21() {
        TreeNode root = new TreeNode(1);
        int sum = 0;
        logResult(pathSum21(root, sum));
    }

    /**
     * 面试题34. 二叉树中和为某一值的路径 输入一棵二叉树和一个整数，打印出二叉树中节点值的和为输入整数的所有路径。从树的根节点开始往下一直到叶节点所经过的节点形成一条路径。
     *
     * <p>示例: 给定如下二叉树，以及目标和 sum = 22，
     *
     * <p>5 / \ 4 8 / / \ 11 13 4 / \ / \ 7 2 5 1 返回:
     *
     * <p>[ [5,4,11,2], [5,8,4,5] ]
     *
     * <p>提示：
     *
     * <p>节点总数 <= 10000 注意：本题与主站 113 题相同：https://leetcode-cn.com/problems/path-sum-ii/
     *
     * @param root
     * @param sum
     * @return
     */
    public List<List<Integer>> pathSum21(TreeNode root, int sum) {
        Set<List<Integer>> result = new HashSet<>();
        if (Objects.isNull(root)) {
            return new ArrayList<>(result);
        }
        calPathSum21(root, sum, 0, result, new ArrayList<>());

        return new ArrayList<>(result);
    }

    private void calPathSum21(
            TreeNode node, int sum, int num, Set<List<Integer>> result, List<Integer> list) {
        if (sum == num
                && !list.isEmpty()
                && Objects.nonNull(node.left)
                && Objects.nonNull(node.right)) {
            result.add(new ArrayList<>(list));
            return;
        }
        if (num > sum) {
            return;
        }
        if (Objects.isNull(node)) {
            return;
        }
        num += node.val;
        int size = list.size();
        list.add(node.val);
        calPathSum21(node.left, sum, num, result, list);
        calPathSum21(node.right, sum, num, result, list);
        list.remove(size);
    }

    @Test
    public void flatten() {
        TreeNode root = new TreeNode(4);
        TreeNode node2 = new TreeNode(7);
        TreeNode node3 = new TreeNode(2);
        TreeNode node4 = new TreeNode(9);
        TreeNode node5 = new TreeNode(6);
        TreeNode node6 = new TreeNode(3);
        TreeNode node7 = new TreeNode(1);
        root.left = node2;
        root.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.left = node6;
        node3.right = node7;
        flatten(root);
        logResult(root);
    }

    /**
     * 114. 二叉树展开为链表 给定一个二叉树，原地将它展开为链表。
     *
     * <p>例如，给定二叉树
     *
     * <p>1 / \ 2 5 / \ \ 3 4 6 将其展开为：
     *
     * <p>1 \ 2 \ 3 \ 4 \ 5 \ 6 通过次数39,109提交次数56,888
     *
     * @param root
     */
    public void flatten(TreeNode root) {
        if (Objects.isNull(root)) {
            return;
        }

        // 深度优先遍历
        Deque<TreeNode> stack = new LinkedList<>();

        stack.push(root);
        TreeNode head = new TreeNode(0);
        TreeNode listNode = head;
        while (!stack.isEmpty()) {
            TreeNode node = stack.poll();
            listNode.left = null;
            listNode.right = node;
            if (Objects.nonNull(node.right)) {
                stack.push(node.right);
            }
            if (Objects.nonNull(node.left)) {
                stack.push(node.left);
            }
            listNode = node;
        }
    }

    /**
     * 109. 有序链表转换二叉搜索树 给定一个单链表，其中的元素按升序排序，将其转换为高度平衡的二叉搜索树。
     *
     * <p>本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。
     *
     * <p>示例:
     *
     * <p>给定的有序链表： [-10, -3, 0, 5, 9],
     *
     * <p>一个可能的答案是：[0, -3, 9, -10, null, 5], 它可以表示下面这个高度平衡二叉搜索树：
     *
     * <p>0 / \ -3 9 / / -10 5
     *
     * @param head
     * @return
     */
    public TreeNode sortedListToBST(ListNode head) {
        List<Integer> list = new ArrayList<>();

        while (Objects.nonNull(head)) {
            list.add(head.val);
            head = head.next;
        }

        return sortedListToBST(list, 0, list.size() - 1);
        /*if (Objects.isNull(head) ) {
            return null;
        }
        if (Objects.isNull(head.next) ) {
            return new TreeNode(head.val);
        }

        // 快慢指针
        ListNode slow = head, fast = head;
        ListNode pre = null;
        while (Objects.nonNull(fast.next) && Objects.nonNull(fast.next.next)) {
            fast = fast.next.next;
            pre = slow;
            slow = slow.next;
        }
        TreeNode root = new TreeNode(slow.val);
        if (Objects.nonNull(pre)) {
            pre.next = null;
        }
        root.left = sortedListToBST(head);
        ListNode next = slow.next;
        slow.next = null;
        root.right = sortedListToBST(next);
        return root;*/
    }

    private TreeNode sortedListToBST(List<Integer> list, int left, int right) {
        if (left > right) {
            return null;
        }
        if (left == right) {
            return new TreeNode(list.get(left));
        }

        int mid = (left + right) >> 1;
        TreeNode root = new TreeNode(list.get(mid));
        root.left = sortedListToBST(list, left, mid - 1);
        root.right = sortedListToBST(list, mid + 1, right);
        return root;
    }

    /**
     * 129. 求根到叶子节点数字之和 给定一个二叉树，它的每个结点都存放一个 0-9 的数字，每条从根到叶子节点的路径都代表一个数字。
     *
     * <p>例如，从根到叶子节点路径 1->2->3 代表数字 123。
     *
     * <p>计算从根到叶子节点生成的所有数字之和。
     *
     * <p>说明: 叶子节点是指没有子节点的节点。
     *
     * <p>示例 1:
     *
     * <p>输入: [1,2,3] 1 / \ 2 3 输出: 25 解释: 从根到叶子节点路径 1->2 代表数字 12. 从根到叶子节点路径 1->3 代表数字 13. 因此，数字总和 =
     * 12 + 13 = 25. 示例 2:
     *
     * <p>输入: [4,9,0,5,1] 4 / \ 9 0 / \ 5 1 输出: 1026 解释: 从根到叶子节点路径 4->9->5 代表数字 495. 从根到叶子节点路径
     * 4->9->1 代表数字 491. 从根到叶子节点路径 4->0 代表数字 40. 因此，数字总和 = 495 + 491 + 40 = 1026.
     *
     * @param root
     * @return
     */
    public int sumNumbers(TreeNode root) {
        sumNumbers(root, 0);
        return treeSum;
    }

    static int treeSum = 0;

    private void sumNumbers(TreeNode node, int num) {
        if (Objects.isNull(node)) {
            return;
        }
        num = num * 10 + node.val;
        if (Objects.isNull(node.left) && Objects.isNull(node.right)) {
            treeSum += num;
            return;
        }

        sumNumbers(node.left, num);
        sumNumbers(node.right, num);
    }

    /**
     * 96. 不同的二叉搜索树 给定一个整数 n，求以 1 ... n 为节点组成的二叉搜索树有多少种？
     *
     * <p>示例:
     *
     * <p>输入: 3 输出: 5 解释: 给定 n = 3, 一共有 5 种不同结构的二叉搜索树:
     *
     * <p>1 3 3 2 1 \ / / / \ \ 3 2 1 1 3 2 / / \ \ 2 1 2 3
     *
     * @param n
     * @return
     */
    public int numTrees(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            int count = 0;
            for (int j = 1; j <= i; j++) {
                // 选择 j 作根节点 左子树的个数 dp[j - 1] 又子树的个数 dp[i - j]
                count += dp[j - 1] * dp[i - j];
            }
            dp[i] = count;
        }
        return dp[n];
    }

    /**
     * 337. 打家劫舍 III 在上次打劫完一条街道之后和一圈房屋后，小偷又发现了一个新的可行窃的地区。这个地区只有一个入口，我们称之为“根”。
     * 除了“根”之外，每栋房子有且只有一个“父“房子与之相连。一番侦察之后，聪明的小偷意识到“这个地方的所有房屋的排列类似于一棵二叉树”。
     * 如果两个直接相连的房子在同一天晚上被打劫，房屋将自动报警。
     *
     * <p>计算在不触动警报的情况下，小偷一晚能够盗取的最高金额。
     *
     * <p>示例 1:
     *
     * <p>输入: [3,2,3,null,3,null,1]
     *
     * <p>3 / \ 2 3 \ \ 3 1
     *
     * <p>输出: 7 解释: 小偷一晚能够盗取的最高金额 = 3 + 3 + 1 = 7. 示例 2:
     *
     * <p>输入: [3,4,5,1,3,null,1]
     *
     * <p>3 / \ 4 5 / \ \ 1 3 1
     *
     * <p>输出: 9 解释: 小偷一晚能够盗取的最高金额 = 4 + 5 = 9.
     *
     * @param root
     * @return
     */
    public int rob(TreeNode root) {
        int[] sum = getRob(root);

        return Math.max(sum[0], sum[1]);
    }

    private int[] getRob(TreeNode root) {
        int[] result = new int[2];
        if (Objects.isNull(root)) {
            return result;
        }
        int[] left = getRob(root.left);
        int[] right = getRob(root.right);

        result[0] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
        result[1] = left[0] + right[0] + root.val;
        return result;
    }

    /**
     * 331. 验证二叉树的前序序列化 序列化二叉树的一种方法是使用前序遍历。当我们遇到一个非空节点时，我们可以记录下这个节点的值。如果它是一个空节点，我们可以使用一个标记值记录，例如 #。
     *
     * <p>_9_ / \ 3 2 / \ / \ 4 1 # 6 / \ / \ / \ # # # # # # 例如，上面的二叉树可以被序列化为字符串
     * "9,3,4,#,#,1,#,#,2,#,6,#,#"，其中 # 代表一个空节点。
     *
     * <p>给定一串以逗号分隔的序列，验证它是否是正确的二叉树的前序序列化。编写一个在不重构树的条件下的可行算法。
     *
     * <p>每个以逗号分隔的字符或为一个整数或为一个表示 null 指针的 '#' 。
     *
     * <p>你可以认为输入格式总是有效的，例如它永远不会包含两个连续的逗号，比如 "1,,3" 。
     *
     * <p>示例 1:
     *
     * <p>输入: "9,3,4,#,#,1,#,#,2,#,6,#,#" 输出: true 示例 2:
     *
     * <p>输入: "1,#" 输出: false 示例 3:
     *
     * <p>输入: "9,#,#,1" 输出: false
     *
     * @param preorder
     * @return
     */
    public boolean isValidSerialization(String preorder) {

        // 对二叉树 的 任意一棵子树 # 的 数量 = 节点数量 + 1
        /*for (int i = 0; i < preorder.length(); i++) {
            char c = preorder.charAt(i);

        }*/
        String[] nodes = preorder.split(",");
        // 一个结点 都会有个两个子结点
        int count = 1;
        for (int i = 0; i < nodes.length; i++) {
            count--;
            if (count < 0) {
                return false;
            }
            if (!Objects.equals(nodes[i], "#")) {
                count += 2;
            }
        }
        return count == 0;
    }

    /**
     * 5398. 统计二叉树中好节点的数目 给你一棵根为 root 的二叉树，请你返回二叉树中好节点的数目。
     *
     * <p>「好节点」X 定义为：从根到该节点 X 所经过的节点中，没有任何节点的值大于 X 的值。
     *
     * <p>示例 1：
     *
     * <p>输入：root = [3,1,4,3,null,1,5] 输出：4 解释：图中蓝色节点为好节点。 根节点 (3) 永远是个好节点。 节点 4 -> (3,4) 是路径中的最大值。
     * 节点 5 -> (3,4,5) 是路径中的最大值。 节点 3 -> (3,1,3) 是路径中的最大值。 示例 2：
     *
     * <p>输入：root = [3,3,null,4,2] 输出：3 解释：节点 2 -> (3, 3, 2) 不是好节点，因为 "3" 比它大。 示例 3：
     *
     * <p>输入：root = [1] 输出：1 解释：根节点是好节点。
     *
     * <p>提示：
     *
     * <p>二叉树中节点数目范围是 [1, 10^5] 。 每个节点权值的范围是 [-10^4, 10^4] 。
     *
     * @param root
     * @return
     */
    public int goodNodes(TreeNode root) {
        goodNodes(root, Integer.MIN_VALUE);
        return goodCount;
    }

    private static int goodCount = 0;

    private void goodNodes(TreeNode node, int max) {
        if (Objects.isNull(node)) {
            return;
        }

        if (node.val >= max) {
            goodCount++;
            max = node.val;
        }
        goodNodes(node.left, max);
        goodNodes(node.right, max);
    }

    @Test
    public void buildTree4() {
        int[] preorder = {3, 9, 20, 15, 7};
        int[] inorder = {9, 3, 15, 20, 7};
        /*int [] preorder = {1,2,4,5,3,6,7};
        int [] inorder = {4,2,5,1,6,3,7};*/

        TreeNode treeNode = buildTree4(preorder, inorder);
        log.debug("result:{} ", treeNode.val);
    }

    /**
     * 105. 从前序与中序遍历序列构造二叉树 根据一棵树的前序遍历与中序遍历构造二叉树。
     *
     * <p>注意: 你可以假设树中没有重复的元素。
     *
     * <p>例如，给出
     *
     * <p>前序遍历 preorder = [3,9,20,15,7] 中序遍历 inorder = [9,3,15,20,7] 返回如下的二叉树：
     *
     * <p>3 / \ 9 20 / \ 15 7
     *
     * @param preorder
     * @param inorder
     * @return
     */
    public TreeNode buildTree4(int[] preorder, int[] inorder) {
        Map<Integer, Integer> indexMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            indexMap.put(inorder[i], i);
        }
        return buildTree4(preorder, 0, preorder.length - 1, 0, inorder.length - 1, indexMap);
    }

    private TreeNode buildTree4(
            int[] preorder,
            int preStart,
            int preEnd,
            int inStart,
            int inEnd,
            Map<Integer, Integer> indexMap) {
        if (preStart > preEnd || inStart > inEnd) {
            return null;
        }
        int rootVal = preorder[preStart];
        TreeNode root = new TreeNode(rootVal);

        int inRootIndex = indexMap.get(rootVal);
        // preorder = [3,9,20,15,7]
        // inorder = [9,3,15,20,7]
        int leftLen = inRootIndex - inStart;
        int rightLen = inEnd - inRootIndex;
        if (leftLen > 0) {
            root.left =
                    buildTree4(
                            preorder,
                            preStart + 1,
                            preStart + leftLen,
                            inStart,
                            inRootIndex - 1,
                            indexMap);
        }
        if (rightLen > 0) {
            root.right =
                    buildTree4(
                            preorder,
                            preEnd - rightLen + 1,
                            preEnd,
                            inRootIndex + 1,
                            inEnd,
                            indexMap);
        }

        return root;
    }

    @Test
    public void pseudoPalindromicPaths() {
        TreeNode root = new TreeNode(2);
        TreeNode node2 = new TreeNode(3);
        TreeNode node3 = new TreeNode(1);
        TreeNode node4 = new TreeNode(3);
        TreeNode node5 = new TreeNode(1);
        // TreeNode node6 = new TreeNode(3);
        TreeNode node7 = new TreeNode(1);
        root.left = node2;
        root.right = node3;
        node2.left = node4;
        node2.right = node5;
        // node3.left = node6;
        node3.right = node7;
        logResult(pseudoPalindromicPaths(root));
    }

    /**
     * 5418. 二叉树中的伪回文路径 请你返回从根到叶子节点的所有路径中 伪回文 路径的数目。
     *
     * <p>示例 1：
     *
     * <p>输入：root = [2,3,1,3,1,null,1] 输出：2 解释：上图为给定的二叉树。总共有 3 条从根到叶子的路径：红色路径 [2,3,3] ，绿色路径 [2,1,1]
     * 和路径 [2,3,1] 。 在这些路径中，只有红色和绿色的路径是伪回文路径，因为红色路径 [2,3,3] 存在回文排列 [3,2,3] ，绿色路径 [2,1,1] 存在回文排列
     * [1,2,1] 。 示例 2：
     *
     * <p>输入：root = [2,1,1,1,3,null,null,null,null,null,1] 输出：1 解释：上图为给定二叉树。总共有 3 条从根到叶子的路径：绿色路径
     * [2,1,1] ，路径 [2,1,3,1] 和路径 [2,1] 。 这些路径中只有绿色路径是伪回文路径，因为 [2,1,1] 存在回文排列 [1,2,1] 。 示例 3：
     *
     * <p>输入：root = [9] 输出：1
     *
     * <p>提示：
     *
     * <p>给定二叉树的节点数目在 1 到 10^5 之间。 节点值在 1 到 9 之间。
     *
     * @param root
     * @return
     */
    public int pseudoPalindromicPaths(TreeNode root) {
        // 深度优先遍历
        int[] nums = new int[10];

        pseudoPalindromicPaths(root, nums);
        return pseudoPalindromicResult;
    }

    static int pseudoPalindromicResult = 0;

    private void pseudoPalindromicPaths(TreeNode root, int[] nums) {

        int val = root.val;
        nums[val]++;
        boolean left = Objects.nonNull(root.left);
        boolean right = Objects.nonNull(root.right);
        if (!left && !right) {
            if (checkPalindromic(nums)) {
                pseudoPalindromicResult++;
            }
            nums[val]--;
            return;
        }
        if (left) {
            pseudoPalindromicPaths(root.left, nums);
        }
        if (right) {
            pseudoPalindromicPaths(root.right, nums);
        }
        nums[val]--;
    }

    private boolean checkPalindromic(int[] nums) {
        int count = 0;
        for (int num : nums) {
            if ((num & 1) == 1) {
                count++;
            }
        }
        return count <= 1;
    }

    /**
     * 513. 找树左下角的值 给定一个二叉树，在树的最后一行找到最左边的值。
     *
     * <p>示例 1:
     *
     * <p>输入:
     *
     * <p>2 / \ 1 3
     *
     * <p>输出: 1
     *
     * <p>示例 2:
     *
     * <p>输入:
     *
     * <p>1 / \ 2 3 / / \ 4 5 6 / 7
     *
     * <p>输出: 7
     *
     * <p>注意: 您可以假设树（即给定的根节点）不为 NULL。
     *
     * @param root
     * @return
     */
    public int findBottomLeftValue(TreeNode root) {
        int left = -1;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (i == 0) {
                    left = node.val;
                }
                if (Objects.nonNull(node.left)) {
                    queue.offer(node.left);
                }
                if (Objects.nonNull(node.right)) {
                    queue.offer(node.right);
                }
            }
        }

        return left;
    }

    /**
     * 508. 出现次数最多的子树元素和
     *
     * <p>给你一个二叉树的根结点，请你找出出现次数最多的子树元素和。一个结点的「子树元素和」定义为以该结点为根的二叉树上所有结点的元素之和（包括结点本身）。
     *
     * <p>你需要返回出现次数最多的子树元素和。如果有多个元素出现的次数相同，返回所有出现次数最多的子树元素和（不限顺序）。
     *
     * <p>示例 1： 输入:
     *
     * <p>5 / \ 2 -3 返回 [2, -3, 4]，所有的值均只出现一次，以任意顺序返回所有值。
     *
     * <p>示例 2： 输入：
     *
     * <p>5 / \ 2 -5 返回 [2]，只有 2 出现两次，-5 只出现 1 次。
     *
     * <p>提示： 假设任意子树元素和均可以用 32 位有符号整数表示。
     *
     * @param root
     * @return
     */
    public List<Integer> largestValues(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();

        queue.offer(root);
        List<Integer> list = new ArrayList<>();

        while (!queue.isEmpty()) {
            int size = queue.size();
            int max = 0;
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                max = Math.max(max, node.val);
                if (Objects.nonNull(node.left)) {
                    queue.offer(node.left);
                }
                if (Objects.nonNull(node.right)) {
                    queue.offer(node.right);
                }
            }
        }

        return list;
    }

    @Test
    public void findFrequentTreeSum() {
        TreeNode root = new TreeNode(5);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(-3);
        TreeNode node4 = new TreeNode(3);
        TreeNode node5 = new TreeNode(1);
        // TreeNode node6 = new TreeNode(3);
        TreeNode node7 = new TreeNode(1);
        root.left = node2;
        root.right = node3;
        // node2.left = node4;
        // node2.right = node5;
        // node3.left = node6;
        // node3.right = node7;
        int[] result = findFrequentTreeSum(root);
        log.debug("result:{}", result);
    }

    /**
     * 508. 出现次数最多的子树元素和 给你一个二叉树的根结点，请你找出出现次数最多的子树元素和。一个结点的「子树元素和」定义为以该结点为根的二叉树上所有结点的元素之和（包括结点本身）。
     *
     * <p>你需要返回出现次数最多的子树元素和。如果有多个元素出现的次数相同，返回所有出现次数最多的子树元素和（不限顺序）。
     *
     * <p>示例 1： 输入:
     *
     * <p>5 / \ 2 -3 返回 [2, -3, 4]，所有的值均只出现一次，以任意顺序返回所有值。
     *
     * <p>示例 2： 输入：
     *
     * <p>5 / \ 2 -5 返回 [2]，只有 2 出现两次，-5 只出现 1 次。
     *
     * <p>提示： 假设任意子树元素和均可以用 32 位有符号整数表示。
     *
     * @param root
     * @return
     */
    public int[] findFrequentTreeSum(TreeNode root) {
        if (Objects.isNull(root)) {
            return new int[0];
        }

        Map<Integer, Integer> map = new HashMap<>();
        indFrequentTreeSum(root, map);
        List<Integer> list = new ArrayList<>();
        map.forEach(
                (k, v) -> {
                    if (v == treeSumMaxCount) {
                        list.add(k);
                    }
                });
        int[] result = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            result[i] = list.get(i);
        }
        return result;
    }

    private int indFrequentTreeSum(TreeNode root, Map<Integer, Integer> map) {
        boolean left = Objects.nonNull(root.left);
        boolean right = Objects.nonNull(root.right);
        int val = root.val;

        if (left) {
            val += indFrequentTreeSum(root.left, map);
        }
        if (right) {
            val += indFrequentTreeSum(root.right, map);
        }
        int count = map.getOrDefault(val, 0);

        map.put(val, count + 1);
        treeSumMaxCount = Math.max(treeSumMaxCount, count + 1);
        return val;
    }

    private int treeSumMaxCount = 0;

    /**
     * 面试题 04.08. 首个共同祖先
     *
     * <p>设计并实现一个算法，找出二叉树中某两个节点的第一个共同祖先。不得将其他的节点存储在另外的数据结构中。注意：这不一定是二叉搜索树。
     *
     * <p>例如，给定如下二叉树: root = [3,5,1,6,2,0,8,null,null,7,4]
     *
     * <p>3 / \ 5 1 / \ / \ 6 2 0 8 / \ 7 4 示例 1:
     *
     * <p>输入: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1 输出: 3 解释: 节点 5 和节点 1 的最近公共祖先是节点 3。
     * 示例 2:
     *
     * <p>输入: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
     *
     * <p>输出: 5 解释: 节点 5 和节点 4 的最
     *
     * <p>近公共祖先是节点 5。因为根据定义最近公共祖先节点可以为节点本身。 说明:
     *
     * <p>所有节点的值都是唯一的。 p、q 为不同节点且均存在于给定的二叉树中。
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        // 找到 p 或 q
        if (Objects.isNull(root) || root == p || root == q) {
            return root;
        }
        TreeNode leftNode = lowestCommonAncestor2(root.left, p, q);

        TreeNode rightNode = lowestCommonAncestor2(root.right, p, q);

        // 左、右子树各找到一个节点
        if (Objects.nonNull(leftNode) && Objects.nonNull(rightNode)) {
            return root;
        }
        // 单个子树找到两个节点
        return Objects.nonNull(leftNode) ? leftNode : rightNode;
    }

    /**
     * 面试题 04.03. 特定深度节点链表
     *
     * <p>给定一棵二叉树，设计一个算法，创建含有某一深度上所有节点的链表（比如，若一棵树的深度为 D，则会创建出 D个链表）。
     *
     * <p>返回一个包含所有深度的链表的数组。
     *
     * <p>示例：
     *
     * <p>输入：[1,2,3,4,5,null,7,8]
     *
     * <p>1 / \ 2 3 / \ \ 4 5 7 / 8
     *
     * <p>输出：[[1],[2,3],[4,5,7],[8]]
     *
     * @param tree
     * @return
     */
    public ListNode[] listOfDepth(TreeNode tree) {
        List<ListNode> list = new ArrayList<>();

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(tree);
        ListNode head = new ListNode(0);
        while (!queue.isEmpty()) {
            int size = queue.size();
            ListNode node = head;
            for (int i = 0; i < size; i++) {
                TreeNode treeNode = queue.poll();
                if (Objects.nonNull(treeNode.left)) {
                    queue.offer(treeNode.left);
                }
                if (Objects.nonNull(treeNode.right)) {
                    queue.offer(treeNode.right);
                }
                node.next = new ListNode(treeNode.val);
                node = node.next;
            }
            list.add(head.next);
        }

        return list.toArray(new ListNode[list.size()]);
    }

    /**
     * 面试题 04.10. 检查子树
     *
     * <p>检查子树。你有两棵非常大的二叉树：T1，有几万个节点；T2，有几万个节点。设计一个算法，判断 T2 是否为 T1 的子树。
     *
     * <p>如果 T1 有这么一个节点 n，其子树与 T2 一模一样，则 T2 为 T1 的子树，也就是说，从节点 n 处把树砍断，得到的树与 T2 完全相同。
     *
     * <p>示例1:
     *
     * <p>输入：t1 = [1, 2, 3], t2 = [2] 输出：true 示例2:
     *
     * <p>输入：t1 = [1, null, 2, 4], t2 = [3, 2] 输出：false 提示：
     *
     * <p>树的节点数目范围为[0, 20000]。
     *
     * @param t1
     * @param t2
     * @return
     */
    public boolean checkSubTree(TreeNode t1, TreeNode t2) {
        if (Objects.isNull(t1)) {
            return false;
        }
        if (t1.val == t2.val) {
            return isSameTree(t1, t2);
        }
        return checkSubTree(t1.left, t2) || checkSubTree(t1.right, t2);
    }

    @Test
    public void pathSum1() {
        TreeNode root = new TreeNode(5);
        TreeNode node21 = new TreeNode(4);
        TreeNode node22 = new TreeNode(8);
        TreeNode node31 = new TreeNode(11);
        TreeNode node33 = new TreeNode(13);
        TreeNode node34 = new TreeNode(4);
        TreeNode node41 = new TreeNode(7);
        TreeNode node42 = new TreeNode(2);
        TreeNode node47 = new TreeNode(5);
        TreeNode node48 = new TreeNode(1);
        root.left = node21;
        root.right = node22;
        node21.left = node31;
        node22.left = node33;
        node22.right = node34;
        node31.left = node41;
        node31.right = node42;
        node34.left = node47;
        node34.right = node48;
        logResult(pathSum1(root, 22));
    }

    /**
     * 面试题 04.12. 求和路径
     *
     * <p>给定一棵二叉树，其中每个节点都含有一个整数数值(该值或正或负)。设计一个算法，打印节点数值总和等于某个给定值的所有路径的数量。注意，路径不一定非得从二叉树的根节点或叶节点开始或结束，但是其方向必须向下(只能从父节点指向子节点方向)。
     *
     * <p>示例: 给定如下二叉树，以及目标和 sum = 22，
     *
     * <p>5 / \ 4 8 / / \ 11 13 4 / \ / \ 7 2 5 1 返回:
     *
     * <p>3 解释：和为 22 的路径有：[5,4,11,2], [5,8,4,5], [4,11,7] 提示：
     *
     * <p>节点总数 <= 10000
     *
     * @param root
     * @param sum
     * @return
     */
    public int pathSum1(TreeNode root, int sum) {
        int result = 0;
        if (Objects.isNull(root)) {
            return 0;
        }
        result += getPathSumFromRoot(root, sum);
        result += pathSum1(root.left, sum);
        result += pathSum1(root.right, sum);
        return result;
    }

    private int getPathSumFromRoot(TreeNode root, int sum) {
        int result = 0;
        if (Objects.isNull(root)) {
            return 0;
        }
        if (root.val == sum) {
            result++;
        }
        result += getPathSumFromRoot(root.left, sum - root.val);

        result += getPathSumFromRoot(root.right, sum - root.val);
        return result;
    }

    @Test
    public void BSTSequences() {
        //      10
        //    5     17
        //  3  7  13  20
        // 1 4      18  21
        TreeNode root = new TreeNode(10);
        TreeNode node21 = new TreeNode(5);
        TreeNode node22 = new TreeNode(17);
        TreeNode node31 = new TreeNode(3);
        TreeNode node32 = new TreeNode(7);
        TreeNode node33 = new TreeNode(13);
        TreeNode node34 = new TreeNode(20);
        TreeNode node41 = new TreeNode(1);
        TreeNode node42 = new TreeNode(4);
        TreeNode node47 = new TreeNode(18);
        TreeNode node48 = new TreeNode(21);
        root.left = node21;
        root.right = node22;
        node21.left = node31;
        node21.right = node32;
        node22.left = node33;
        node22.right = node34;
        // node31.left = node41;
        // node31.right = node42;
        // node34.left = node47;
        // node34.right = node48;
        List<List<Integer>> result = BSTSequences(root);
        for (int i = 0; i < result.size(); i++) {
            log.debug("{} : {}", i, result.get(i));
        }
    }

    /**
     * 面试题 04.09. 二叉搜索树序列
     *
     * <p>从左向右遍历一个数组，通过不断将其中的元素插入树中可以逐步地生成一棵二叉搜索树。 给定一个由不同节点组成的二叉树，输出所有可能生成此树的数组。
     *
     * <p>示例: 给定如下二叉树
     *
     * <p>2 / \ 1 3 返回:
     *
     * <p>[ [2,1,3], [2,3,1] ]
     *
     * @param root
     * @return
     */
    public List<List<Integer>> BSTSequences(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (Objects.isNull(root)) {
            result.add(new ArrayList<>());
            return result;
        }
        List<Integer> list = new ArrayList<>();
        list.add(root.val);
        addList(root, new LinkedList<>(), list, result);
        return result;
    }

    private void addList(
            TreeNode root,
            List<TreeNode> nextNodeList,
            List<Integer> list,
            List<List<Integer>> result) {
        if (Objects.isNull(root)) {
            return;
        }
        boolean left = Objects.nonNull(root.left);
        boolean right = Objects.nonNull(root.right);
        // 下个节点的所有值
        if (left) {
            nextNodeList.add(root.left);
        }
        if (right) {
            nextNodeList.add(root.right);
        }
        // 没有下一个元素
        if (nextNodeList.isEmpty()) {
            result.add(new ArrayList<>(list));
        }

        for (int i = 0; i < nextNodeList.size(); i++) {
            TreeNode node = nextNodeList.remove(i);
            list.add(node.val);
            addList(node, new ArrayList<>(nextNodeList), list, result);
            nextNodeList.add(i, node);
            list.remove(list.size() - 1);
        }
    }

    @Test
    public void recoverFromPreorder() {

        String s = "1-2--3---4-5--6---7";
        TreeNode root = recoverFromPreorder2(s);
        logResult(root);
    }

    /**
     * 1028. 从先序遍历还原二叉树
     *
     * <p>我们从二叉树的根节点 root 开始进行深度优先搜索。
     *
     * <p>在遍历中的每个节点处，我们输出 D 条短划线（其中 D 是该节点的深度），然后输出该节点的值。（如果节点的深度为 D，则其直接子节点的深度为 D + 1。根节点的深度为 0）。
     *
     * <p>如果节点只有一个子节点，那么保证该子节点为左子节点。
     *
     * <p>给出遍历输出 S，还原树并返回其根节点 root。
     *
     * <p>示例 1：
     *
     * <p>输入："1-2--3--4-5--6--7" 输出：[1,2,5,3,4,6,7] 示例 2：
     *
     * <p>输入："1-2--3---4-5--6---7" 输出：[1,2,5,3,null,6,null,4,null,7] 示例 3：
     *
     * <p>输入："1-401--349---90--88" 输出：[1,401,null,349,88,90]
     *
     * <p>提示：
     *
     * <p>原始树中的节点数介于 1 和 1000 之间。 每个节点的值介于 1 和 10 ^ 9 之间。
     *
     * @param S
     * @return
     */
    public TreeNode recoverFromPreorder(String S) {
        if (S.length() == 0) {
            return null;
        }
        int val = 0;
        int start = 0;
        char[] chars = S.toCharArray();
        while (start < chars.length && isNumber(chars[start])) {
            val = val * 10 + (chars[start] - '0');
            start++;
        }
        TreeNode root = new TreeNode(val);
        val = 0;
        int depth = 0;
        Deque<TreeNode> stack = new LinkedList<>();
        Deque<Integer> depthStack = new LinkedList<>();
        stack.push(root);
        depthStack.push(0);
        for (int i = start; i <= chars.length; i++) {
            if (i < chars.length && isNumber(chars[i])) {
                val = val * 10 + (chars[i] - '0');
                continue;
            }
            if (i - 1 > start && isNumber(chars[i - 1])) {
                // 添加节点
                log.debug("val:{} depth :{}", val, depth);
                // 找到对应的父节点
                while (depthStack.size() > 1 && depthStack.peek() >= depth) {
                    depthStack.pop();
                    stack.pop();
                }
                TreeNode parent = stack.peek();

                TreeNode node = new TreeNode(val);
                if (Objects.isNull(parent.left)) {
                    parent.left = node;
                } else {
                    parent.right = node;
                }
                stack.push(node);
                depthStack.push(depth);
                depth = 0;
            }
            val = 0;
            depth++;
        }

        return root;
    }

    public TreeNode recoverFromPreorder2(String S) {
        recoverIndex = 0;

        return getRecoverTreeNode(S, 0);
    }

    public TreeNode getRecoverTreeNode(String S, int depth) {
        if (recoverIndex >= S.length()) {
            return null;
        }
        int curDepth = 0;
        int depthIndex = recoverIndex;
        while (depthIndex < S.length() && S.charAt(depthIndex) == '-') {
            curDepth++;
            depthIndex++;
        }
        if (curDepth != depth) {
            return null;
        }
        recoverIndex = depthIndex;
        int val = 0;
        while (recoverIndex < S.length() && isNumber(S.charAt(recoverIndex))) {
            val = val * 10 + (S.charAt(recoverIndex) - '0');
            recoverIndex++;
        }
        TreeNode root = new TreeNode(val);
        root.left = getRecoverTreeNode(S, depth + 1);
        root.right = getRecoverTreeNode(S, depth + 1);
        return root;
    }

    private int recoverIndex;

    private boolean isNumber(char c) {
        switch (c) {
            case '0':
            case '1':
            case '2':
            case '3':
            case '4':
            case '5':
            case '6':
            case '7':
            case '8':
            case '9':
                return true;
        }
        return false;
    }

    @Test
    public void maxPathSum2() {
        /*TreeNode root = new TreeNode(5);
        TreeNode node2 = new TreeNode(3);
        TreeNode node3 = new TreeNode(6);
        TreeNode node4 = new TreeNode(2);
        TreeNode node5 = new TreeNode(4);
        root.left = node2;
        root.right = node3;
        node2.left = node4;
        node2.right = node5;
        node4.left = new TreeNode(1);
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);*/
        /* TreeNode root = new TreeNode(-1);
        TreeNode node2 = new TreeNode(6);
        root.left = node2;
        node2.left = new TreeNode(0);
        node2.right = new TreeNode(-6);*/
        TreeNode root = new TreeNode(9);
        TreeNode node11 = new TreeNode(6);
        TreeNode node12 = new TreeNode(-3);

        TreeNode node23 = new TreeNode(-6);
        TreeNode node24 = new TreeNode(2);

        TreeNode node37 = new TreeNode(2);

        // [9,6,-3,null,null,-6,2,null,null,2,null,-6,-6,-6]
        int result = maxPathSum2(root);
        logResult(result);
    }

    /**
     * 124. 二叉树中的最大路径和
     *
     * <p>给定一个非空二叉树，返回其最大路径和。
     *
     * <p>本题中，路径被定义为一条从树中任意节点出发，达到任意节点的序列。该路径至少包含一个节点，且不一定经过根节点。
     *
     * <p>示例 1:
     *
     * <p>输入: [1,2,3]
     *
     * <p>1 / \ 2 3
     *
     * <p>输出: 6 示例 2:
     *
     * <p>输入: [-10,9,20,null,null,15,7]
     *
     * <p>-10 / \ 9 20 / \ 15 7
     *
     * <p>输出: 42
     *
     * @param root
     * @return
     */
    public int maxPathSum2(TreeNode root) {
        getMaxPathSumNode(root);
        return maxPathSum;
    }

    /**
     * 获取root 到子节点的最大路径和
     *
     * @param root
     * @return
     */
    private int getMaxPathSumNode(TreeNode root) {
        if (Objects.isNull(root)) {
            return 0;
        }
        int val = root.val;
        int left = getMaxPathSumNode(root.left);
        int right = getMaxPathSumNode(root.right);

        int maxChild = Math.max(left, right);
        int result = val;
        if (maxChild > 0) {
            result = Math.max(left, right) + val;
        }

        int sum = val + left + right;
        maxPathSum = Math.max(result, maxPathSum);
        maxPathSum = Math.max(sum, maxPathSum);

        return result;
    }

    int maxPathSum = Integer.MIN_VALUE;

    @Test
    public void addOneRow() {
        // [4,2,6,3,1,5]
        // 1
        // 3

        TreeNode root = new TreeNode(4);
        TreeNode node11 = new TreeNode(2);
        TreeNode node12 = new TreeNode(6);

        TreeNode node21 = new TreeNode(3);
        TreeNode node22 = new TreeNode(1);

        TreeNode node23 = new TreeNode(5);
        root.left = node11;
        root.right = node12;
        node11.left = node21;
        node11.right = node22;
        node12.left = node23;
        int v = 1, d = 3;
        TreeNode result = addOneRow(root, v, d);
        log.debug("111");
    }

    /**
     * 623. 在二叉树中增加一行
     *
     * <p>给定一个二叉树，根节点为第1层，深度为 1。在其第 d 层追加一行值为 v 的节点。
     *
     * <p>添加规则：给定一个深度值 d （正整数），针对深度为 d-1 层的每一非空节点 N，为 N 创建两个值为 v 的左子树和右子树。
     *
     * <p>将 N 原先的左子树，连接为新节点 v 的左子树；将 N 原先的右子树，连接为新节点 v 的右子树。
     *
     * <p>如果 d 的值为 1，深度 d - 1 不存在，则创建一个新的根节点 v，原先的整棵树将作为 v 的左子树。
     *
     * <p>示例 1:
     *
     * <p>输入: 二叉树如下所示: 4 / \ 2 6 / \ / 3 1 5
     *
     * <p>v = 1
     *
     * <p>d = 2
     *
     * <p>输出: 4 / \ 1 1 / \ 2 6 / \ / 3 1 5
     *
     * <p>示例 2:
     *
     * <p>输入: 二叉树如下所示: 4 / 2 / \ 3 1
     *
     * <p>v = 1
     *
     * <p>d = 3
     *
     * <p>输出: 4 / 2 / \ 1 1 / \ 3 1 注意:
     *
     * <p>输入的深度值 d 的范围是：[1，二叉树最大深度 + 1]。 输入的二叉树至少有一个节点。
     *
     * @param root
     * @param v
     * @param d
     * @return
     */
    public TreeNode addOneRow(TreeNode root, int v, int d) {
        if (d == 1) {
            TreeNode head = new TreeNode(v);
            head.left = root;
            return head;
        }
        // 层次遍历
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int depth = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                boolean hasLeft = Objects.nonNull(node.left);
                boolean hasRight = Objects.nonNull(node.right);
                if (depth == d - 1) {
                    TreeNode left = node.left;
                    node.left = new TreeNode(v);
                    node.left.left = left;
                    TreeNode right = node.right;
                    node.right = new TreeNode(v);
                    node.right.right = right;
                }
                if (hasLeft) {
                    queue.offer(node.left);
                }
                if (hasRight) {
                    queue.offer(node.right);
                }
            }
            if (depth == d - 1) {
                break;
            }
            depth++;
        }

        return root;
    }

    @Test
    public void constructMaximumBinaryTree() {
        int[] nums = {3, 2, 1, 6, 0, 5};
        TreeNode root = constructMaximumBinaryTree(nums);
        logResult("1");
    }

    /**
     * 654. 最大二叉树
     *
     * <p>给定一个不含重复元素的整数数组。一个以此数组构建的最大二叉树定义如下：
     *
     * <p>二叉树的根是数组中的最大元素。 左子树是通过数组中最大值左边部分构造出的最大二叉树。 右子树是通过数组中最大值右边部分构造出的最大二叉树。
     * 通过给定的数组构建最大二叉树，并且输出这个树的根节点。
     *
     * <p>示例 ：
     *
     * <p>输入：[3,2,1,6,0,5] 输出：返回下面这棵树的根节点：
     *
     * <p>6 / \ 3 5 \ / 2 0 \ 1
     *
     * <p>提示：
     *
     * <p>给定的数组的大小在 [1, 1000] 之间。
     *
     * @param nums
     * @return
     */
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        int len = nums.length;
        if (len == 0) {
            return null;
        }
        TreeNode root = new TreeNode(nums[0]);

        for (int i = 1; i < len; i++) {
            // 1 找到右子树中 第一个比 nums[i] 小的 node 和 parent
            // parent.right = curNode
            // curNode.left = node
            TreeNode curNode = new TreeNode(nums[i]);

            TreeNode parent = null, node = root;

            while (Objects.nonNull(node) && node.val >= nums[i]) {
                parent = node;
                node = node.right;
            }
            if (Objects.nonNull(parent)) {
                parent.right = curNode;
            }
            curNode.left = node;
            if (node == root) {
                root = curNode;
            }
        }
        return root;
    }

    @Test
    public void printTree() {
        TreeNode root = new TreeNode(1);
        TreeNode node11 = new TreeNode(2);
        TreeNode node12 = new TreeNode(3);
        TreeNode node22 = new TreeNode(4);
        root.left = node11;
        root.right = node12;
        node11.right = node22;
        List<List<String>> result = printTree(root);

        for (List<String> list : result) {
            logResult(list);
        }
    }
    /**
     * 655. 输出二叉树
     *
     * <p>在一个 m*n 的二维字符串数组中输出二叉树，并遵守以下规则：
     *
     * <p>行数 m 应当等于给定二叉树的高度。 列数 n 应当总是奇数。
     * 根节点的值（以字符串格式给出）应当放在可放置的第一行正中间。根节点所在的行与列会将剩余空间划分为两部分（左下部分和右下部分）。你应该将左子树输出在左下部分，右子树输出在右下部分。左下和右下部分应当有相同的大小。即使一个子树为空而另一个非空，你不需要为空的子树输出任何东西，但仍需要为另一个子树留出足够的空间。然而，如果两个子树都为空则不需要为它们留出任何空间。
     * 每个未使用的空间应包含一个空的字符串""。 使用相同的规则输出子树。 示例 1:
     *
     * <p>输入: 1 / 2 输出: [["", "1", ""], ["2", "", ""]] 示例 2:
     *
     * <p>输入: 1 / \ 2 3 \ 4 输出: [["", "", "", "1", "", "", ""], ["", "2", "", "", "", "3", ""], ["",
     * "", "4", "", "", "", ""]] 示例 3:
     *
     * <p>输入: 1 / \ 2 5 / 3 / 4 输出: [["", "", "", "", "", "", "", "1", "", "", "", "", "", "", ""]
     * ["", "", "", "2", "", "", "", "", "", "", "", "5", "", "", ""] ["", "3", "", "", "", "", "",
     * "", "", "", "", "", "", "", ""] ["4", "", "", "", "", "", "", "", "", "", "", "", "", "",
     * ""]] 注意: 二叉树的高度在范围 [1, 10] 中。
     *
     * @param root
     * @return
     */
    public List<List<String>> printTree(TreeNode root) {
        List<List<String>> result = new ArrayList<>();
        int height = getHeight(root);
        int len = (1 << height) - 1;

        for (int i = 0; i < height; i++) {
            List<String> list = new ArrayList<>();
            for (int j = 0; j < len; j++) {
                list.add("");
            }
            result.add(list);
        }
        printTreeNode(root, 0, len - 1, result, 0);
        return result;
    }

    private void printTreeNode(
            TreeNode root, int start, int end, List<List<String>> result, int index) {
        if (Objects.isNull(root)) {
            return;
        }
        int mid = (start + end) >> 1;
        result.get(index).set(mid, String.valueOf(root.val));
        printTreeNode(root.left, start, mid - 1, result, index + 1);
        printTreeNode(root.right, mid + 1, end, result, index + 1);
    }

    private int getHeight(TreeNode root) {
        if (Objects.isNull(root)) {
            return 0;
        }
        if (Objects.isNull(root.left) && Objects.isNull(root.right)) {
            return 1;
        }

        int leftHeight = 0;
        int rightHeight = 0;

        if (Objects.nonNull(root.left)) {
            leftHeight = getHeight(root.left);
        }
        if (Objects.nonNull(root.right)) {
            rightHeight = getHeight(root.right);
        }

        int height = Math.max(leftHeight, rightHeight);

        height++;
        return height;
    }

    @Test
    public void countSubTrees() {
        int n = 6;
        // }, {
        int[][] edges = {{0, 1}, {0, 2}, {1, 3}, {3, 4}, {4, 5}};
        String labels = "cbabaa";
        int[] result = countSubTrees(n, edges, labels);
        log.debug("result:{}", result);
    }

    /**
     * 5465. 子树中标签相同的节点数
     *
     * <p>给你一棵树（即，一个连通的无环无向图），这棵树由编号从 0 到 n - 1 的 n 个节点组成，且恰好有 n - 1 条 edges 。树的根节点为节点 0
     * ，树上的每一个节点都有一个标签，也就是字符串 labels 中的一个小写字符（编号为 i 的 节点的标签就是 labels[i] ）
     *
     * <p>边数组 edges 以 edges[i] = [ai, bi] 的形式给出，该格式表示节点 ai 和 bi 之间存在一条边。
     *
     * <p>返回一个大小为 n 的数组，其中 ans[i] 表示第 i 个节点的子树中与节点 i 标签相同的节点数。
     *
     * <p>树 T 中的子树是由 T 中的某个节点及其所有后代节点组成的树。
     *
     * <p>示例 1：
     *
     * <p>输入：n = 7, edges = [[0,1],[0,2],[1,4],[1,5],[2,3],[2,6]], labels = "abaedcd"
     * 输出：[2,1,1,1,1,1,1] 解释：节点 0 的标签为 'a' ，以 'a' 为根节点的子树中，节点 2 的标签也是 'a' ，因此答案为 2
     * 。注意树中的每个节点都是这棵子树的一部分。 节点 1 的标签为 'b' ，节点 1 的子树包含节点 1、4 和 5，但是节点 4、5 的标签与节点 1 不同，故而答案为
     * 1（即，该节点本身）。 示例 2：
     *
     * <p>输入：n = 4, edges = [[0,1],[1,2],[0,3]], labels = "bbbb" 输出：[4,2,1,1] 解释：节点 2 的子树中只有节点 2
     * ，所以答案为 1 。 节点 3 的子树中只有节点 3 ，所以答案为 1 。 节点 1 的子树中包含节点 1 和 2 ，标签都是 'b' ，因此答案为 2 。 节点 0 的子树中包含节点
     * 0、1、2 和 3，标签都是 'b'，因此答案为 4 。 示例 3：
     *
     * <p>输入：n = 5, edges = [[0,1],[0,2],[1,3],[0,4]], labels = "aabab" 输出：[3,2,1,1,1] 示例 4：
     *
     * <p>输入：n = 6, edges = [[0,1],[0,2],[1,3],[3,4],[4,5]], labels = "cbabaa" 输出：[1,2,1,1,2,1] 示例
     * 5：
     *
     * <p>输入：n = 7, edges = [[0,1],[1,2],[2,3],[3,4],[4,5],[5,6]], labels = "aaabaaa"
     * 输出：[6,5,4,1,3,2,1]
     *
     * <p>提示：
     *
     * <p>1 <= n <= 10^5 edges.length == n - 1 edges[i].length == 2 0 <= ai, bi < n ai != bi
     * labels.length == n labels 仅由小写英文字母组成
     *
     * @param n
     * @param edges
     * @param labels
     * @return
     */
    public int[] countSubTrees(int n, int[][] edges, String labels) {
        countSubTreesResult = new int[n];
        Map<Integer, List<Integer>> treeMap = new HashMap<>();
        for (int[] edge : edges) {
            int start = edge[0], end = edge[1];
            List<Integer> list = treeMap.computeIfAbsent(start, k -> new ArrayList<>());
            list.add(end);
            List<Integer> list2 = treeMap.computeIfAbsent(end, k -> new ArrayList<>());
            list2.add(start);
        }
        char[] chars = labels.toCharArray();
        /*for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            result[i] = getSubTree(i, c, chars, treeMap);
        }*/
        boolean[] visited = new boolean[n];
        Map<Character, Set<Integer>> visitedMap = new HashMap<>();
        Set<Integer> set = visitedMap.computeIfAbsent(chars[0], k -> new HashSet<>());
        set.add(0);
        getSubTreeSame(0, chars, treeMap, visited);
        return countSubTreesResult;
    }

    private int[] countSubTreesResult;

    private int[] getSubTreeSame(
            int index, char[] chars, Map<Integer, List<Integer>> treeMap, boolean[] visited) {

        char c = chars[index];

        visited[index] = true;
        // 以root为根节点的子树中每个字母的个数
        int[] count = new int[26];
        count[c - 'a']++;

        if (!treeMap.containsKey(index)) {
            return count;
        }
        List<Integer> list = treeMap.get(index);
        for (int num : list) {
            if (visited[num]) {
                continue;
            }
            // 再统计每个子树
            int[] res = getSubTreeSame(num, chars, treeMap, visited);
            for (int i = 0; i < 26; i++) {
                count[i] += res[i];
            }
        }
        countSubTreesResult[index] = count[c - 'a'];
        return count;
    }

    private int getSubTree(int index, char c, char[] chars, Map<Integer, List<Integer>> treeMap) {

        int result = 0;
        if (chars[index] == c) {
            result++;
        }
        if (!treeMap.containsKey(index)) {
            return result;
        }
        List<Integer> list = treeMap.get(index);
        for (int num : list) {
            result += getSubTree(num, c, chars, treeMap);
        }
        return result;
    }

    @Test
    public void countPairs() {
        TreeNode root = new TreeNode(1);
        TreeNode node11 = new TreeNode(2);
        TreeNode node12 = new TreeNode(3);

        TreeNode node21 = new TreeNode(4);
        TreeNode node22 = new TreeNode(5);

        TreeNode node23 = new TreeNode(6);
        TreeNode node24 = new TreeNode(7);

        TreeNode node31 = new TreeNode(31);
        TreeNode node32 = new TreeNode(32);

        TreeNode node33 = new TreeNode(33);
        TreeNode node34 = new TreeNode(34);
        TreeNode node35 = new TreeNode(35);
        TreeNode node36 = new TreeNode(36);

        TreeNode node37 = new TreeNode(37);
        TreeNode node38 = new TreeNode(38);
        root.left = node11;
        root.right = node12;
        node11.left = node21;
        node11.right = node22;
        node12.left = node23;
        node12.right = node24;
        node21.left = node31;
        node21.right = node32;

        node22.left = node33;
        node22.right = node34;

        node23.left = node35;
        node23.right = node36;

        node24.left = node37;
        node24.right = node38;
        int distance = 3;
        logResult(countPairs(root, distance));
    }
    /**
     * 5474. 好叶子节点对的数量
     *
     * <p>给你二叉树的根节点 root 和一个整数 distance 。
     *
     * <p>如果二叉树中两个 叶 节点之间的 最短路径长度 小于或者等于 distance ，那它们就可以构成一组 好叶子节点对 。
     *
     * <p>返回树中 好叶子节点对的数量 。
     *
     * <p>示例 1：
     *
     * <p>输入：root = [1,2,3,null,4], distance = 3 输出：1 解释：树的叶节点是 3 和 4 ，它们之间的最短路径的长度是 3 。这是唯一的好叶子节点对。
     * 示例 2：
     *
     * <p>输入：root = [1,2,3,4,5,6,7], distance = 3 输出：2 解释：好叶子节点对为 [4,5] 和 [6,7] ，最短路径长度都是 2 。但是叶子节点对
     * [4,6] 不满足要求，因为它们之间的最短路径长度为 4 。 示例 3：
     *
     * <p>输入：root = [7,1,4,6,null,5,3,null,null,null,null,null,2], distance = 3 输出：1 解释：唯一的好叶子节点对是
     * [2,5] 。 示例 4：
     *
     * <p>输入：root = [100], distance = 1 输出：0 示例 5：
     *
     * <p>输入：root = [1,1,1], distance = 2 输出：1
     *
     * <p>提示：
     *
     * <p>tree 的节点数在 [1, 2^10] 范围内。 每个节点的值都在 [1, 100] 之间。 1 <= distance <= 10
     *
     * @param root
     * @param distance
     * @return
     */
    public int countPairs(TreeNode root, int distance) {
        int result = 0;
        // 递归
        if (Objects.isNull(root)) {
            return 0;
        }

        for (int d = 2; d <= distance; d++) {
            for (int i = 1; i < d; i++) {
                result += getCountPairs(root.left, i) * getCountPairs(root.right, d - i);
            }
        }

        // 左叶子节点
        result += countPairs(root.left, distance);
        log.debug("左：{}  {}", root.left, result);
        // 右叶子节点
        result += countPairs(root.right, distance);
        log.debug("右：{}  {}", root.right, result);
        return result;
    }

    private int getCountPairs(TreeNode root, int distance) {
        int count = 0;
        if (distance == 0) {
            return 0;
        }

        if (Objects.isNull(root)) {
            return 0;
        }

        if (Objects.isNull(root.left) && Objects.isNull(root.right) && distance == 1) {
            return 1;
        }

        count += getCountPairs(root.left, distance - 1);
        log.debug("count left：{}  {}", root.left, count);
        count += getCountPairs(root.right, distance - 1);
        log.debug("count right：{}  {}", root.right, count);
        log.debug("count :{}", count);
        return count;
    }

    /**
     * 1080. 根到叶路径上的不足节点
     *
     * <p>给定一棵二叉树的根 root，请你考虑它所有 从根到叶的路径：从根到任何叶的路径。（所谓一个叶子节点，就是一个没有子节点的节点）
     *
     * <p>假如通过节点 node 的每种可能的 “根-叶” 路径上值的总和全都小于给定的 limit，则该节点被称之为「不足节点」，需要被删除。
     *
     * <p>请你删除所有不足节点，并返回生成的二叉树的根。
     *
     * <p>示例 1：
     *
     * <p>输入：root = [1,2,3,4,-99,-99,7,8,9,-99,-99,12,13,-99,14], limit = 1
     *
     * <p>输出：[1,2,3,4,null,null,7,8,9,null,14] 示例 2：
     *
     * <p>输入：root = [5,4,8,11,null,17,4,7,1,null,null,5,3], limit = 22
     *
     * <p>输出：[5,4,8,11,null,17,4,7,null,null,null,5] 示例 3：
     *
     * <p>输入：root = [5,-6,-6], limit = 0 输出：[]
     *
     * <p>提示：
     *
     * <p>给定的树有 1 到 5000 个节点 -10^5 <= node.val <= 10^5 -10^9 <= limit <= 10^9
     *
     * @param root
     * @param limit
     * @return
     */
    public TreeNode sufficientSubset(TreeNode root, int limit) {
        if (Objects.isNull(root)) {
            return null;
        }
        if (Objects.isNull(root.left) && Objects.isNull(root.right)) {
            if (root.val < limit) {
                return null;
            }
            return root;
        }
        root.left = sufficientSubset(root.left, limit - root.val);
        root.right = sufficientSubset(root.right, limit - root.val);
        // 左右子结点都是空
        if (Objects.isNull(root.left) && Objects.isNull(root.right)) {
            return null;
        }

        return root;
    }

    @Test
    public void pathInZigZagTree() {
        int label = 26;
        logResult(pathInZigZagTree(label));
    }
    /**
     * 1104. 二叉树寻路
     *
     * <p>在一棵无限的二叉树上，每个节点都有两个子节点，树中的节点 逐行 依次按 “之” 字形进行标记。
     *
     * <p>如下图所示，在奇数行（即，第一行、第三行、第五行……）中，按从左到右的顺序进行标记；
     *
     * <p>而偶数行（即，第二行、第四行、第六行……）中，按从右到左的顺序进行标记。
     *
     * <p>给你树上某一个节点的标号 label，请你返回从根节点到该标号为 label 节点的路径，该路径是由途经的节点标号所组成的。
     *
     * <p>示例 1：
     *
     * <p>输入：label = 14 输出：[1,3,4,14] 示例 2：
     *
     * <p>输入：label = 26 输出：[1,2,6,10,26]
     *
     * <p>提示：
     *
     * <p>1 <= label <= 10^6
     *
     * @param label
     * @return
     */
    public List<Integer> pathInZigZagTree(int label) {
        List<Integer> result = new ArrayList<>();
        // 先判断层数, 然后逐层计算
        // 计算高度 1 + 2 + 2^2 + 2^3 …… 2^(n-1) = 2^n -1
        int height = (int) Math.floor(Math.log(label) / Math.log(2.0));
        int[] nums = new int[height];
        result.add(label);
        // 位运算的优先级比 +-低
        while (height > 0) {
            int firstIndex = 1 << height;
            int len = 1 << (height + 1);
            label = (firstIndex + (len - label - 1)) >> 1;
            height--;
            result.add(label);
            // nums[height] = label;
        }
        Collections.reverse(result);
        return result;
    }

    /**
     * 99. 恢复二叉搜索树
     *
     * <p>二叉搜索树中的两个节点被错误地交换。
     *
     * <p>请在不改变其结构的情况下，恢复这棵树。
     *
     * <p>示例 1:
     *
     * <p>输入: [1,3,null,null,2]
     *
     * <p>1 / 3 \ 2
     *
     * <p>输出: [3,1,null,null,2]
     *
     * <p>3 / 1 \ 2 示例 2:
     *
     * <p>输入: [3,1,4,null,null,2]
     *
     * <p>3 / \ 1 4 / 2
     *
     * <p>输出: [2,1,4,null,null,3]
     *
     * <p>2 / \ 1 4 / 3 进阶:
     *
     * <p>使用 O(n) 空间复杂度的解法很容易实现。 你能想出一个只使用常数空间的解决方案吗？
     *
     * @param root
     */
    public void recoverTree(TreeNode root) {

        TreeNode node = root;

        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode lastNode = null;
        TreeNode x = null, y = null;
        while (Objects.nonNull(node) || !stack.isEmpty()) {

            while (Objects.nonNull(node)) {
                // 先把根结点入栈
                stack.push(node);
                node = node.left;
            }
            if (!stack.isEmpty()) {
                node = stack.pop();

                if (Objects.nonNull(lastNode) && lastNode.val > node.val) {
                    y = node;
                    if (Objects.isNull(x)) {
                        x = lastNode;
                    } else {
                        break;
                    }
                }

                lastNode = node;
                // 左结点已经出栈
                node = node.right;
            }
        }

        int tmp = x.val;
        x.val = y.val;
        y.val = tmp;
    }

    /**
     * 1110. 删点成林
     *
     * <p>给出二叉树的根节点 root，树上每个节点都有一个不同的值。
     *
     * <p>如果节点值在 to_delete 中出现，我们就把该节点从树上删去，最后得到一个森林（一些不相交的树构成的集合）。
     *
     * <p>返回森林中的每棵树。你可以按任意顺序组织答案。
     *
     * <p>示例：
     *
     * <p>输入：root = [1,2,3,4,5,6,7], to_delete = [3,5] 输出：[[1,2,null,4],[6],[7]]
     *
     * <p>提示：
     *
     * <p>树中的节点数最大为 1000。 每个节点都有一个介于 1 到 1000 之间的值，且各不相同。 to_delete.length <= 1000 to_delete 包含一些从 1
     * 到 1000、各不相同的值。
     *
     * @param root
     * @param to_delete
     * @return
     */
    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        Set<Integer> deleteSet = new HashSet<>();
        for (int d : to_delete) {
            deleteSet.add(d);
        }

        List<TreeNode> result = new ArrayList<>();
        delNode(root, result, deleteSet, false);
        return result;
    }

    /**
     * @param root
     * @param result
     * @param deleteSet
     * @param parentExist
     * @return
     */
    private boolean delNode(
            TreeNode root, List<TreeNode> result, Set<Integer> deleteSet, boolean parentExist) {

        if (Objects.isNull(root)) {
            return false;
        }
        boolean del = deleteSet.contains(root.val);
        if (delNode(root.left, result, deleteSet, !del)) {
            root.left = null;
        }
        if (delNode(root.right, result, deleteSet, !del)) {
            root.right = null;
        }
        if (!del && !parentExist) {
            result.add(root);
        }

        return del;
    }

    /**
     * 1123. 最深叶节点的最近公共祖先
     *
     * <p>给你一个有根节点的二叉树，找到它最深的叶节点的最近公共祖先。
     *
     * <p>回想一下：
     *
     * <p>叶节点 是二叉树中没有子节点的节点 树的根节点的 深度 为 0，如果某一节点的深度为 d，那它的子节点的深度就是 d+1 如果我们假定 A 是一组节点 S 的 最近公共祖先，S
     * 中的每个节点都在以 A 为根节点的子树中，且 A 的深度达到此条件下可能的最大值。
     *
     * <p>示例 1：
     *
     * <p>输入：root = [1,2,3] 输出：[1,2,3] 解释： 最深的叶子是值为 2 和 3 的节点。 这些叶子的最近共同祖先是值为 1 的节点。 返回的答案为序列化的
     * TreeNode 对象（不是数组）"[1,2,3]" 。 示例 2：
     *
     * <p>输入：root = [1,2,3,4] 输出：[4] 示例 3：
     *
     * <p>输入：root = [1,2,3,4,5] 输出：[2,4,5]
     *
     * <p>提示：
     *
     * <p>给你的树中将有 1 到 1000 个节点。 树中每个节点的值都在 1 到 1000 之间。
     *
     * @param root
     * @return
     */
    public TreeNode lcaDeepestLeaves(TreeNode root) {
        // 如果当前节点是最深叶子节点的最近公共祖先，那么它的左右子树的高度一定是相等的，
        // 否则高度低的那个子树的叶子节点深度一定比另一个子树的叶子节点的深度小，因此不满足条件。
        if (Objects.isNull(root)) {
            return null;
        }
        int leftHeight = getHeight(root.left);
        int rightHeight = getHeight(root.right);

        if (leftHeight == rightHeight) {
            return root;
        } else if (leftHeight > rightHeight) {
            return lcaDeepestLeaves(root.left);
        } else {
            return lcaDeepestLeaves(root.right);
        }
    }

    /**
     * 1130. 叶值的最小代价生成树
     *
     * <p>给你一个正整数数组 arr，考虑所有满足以下条件的二叉树：
     *
     * <p>每个节点都有 0 个或是 2 个子节点。 数组 arr 中的值与树的中序遍历中每个叶节点的值一一对应。（知识回顾：如果一个节点有 0 个子节点，那么该节点为叶节点。）
     * 每个非叶节点的值等于其左子树和右子树中叶节点的最大值的乘积。 在所有这样的二叉树中，返回每个非叶节点的值的最小可能总和。这个和的值是一个 32 位整数。
     *
     * <p>示例：
     *
     * <p>输入：arr = [6,2,4] 输出：32 解释： 有两种可能的树，第一种的非叶节点的总和为 36，第二种非叶节点的总和为 32。
     *
     * <p>24 24 / \ / \ 12 4 6 8 / \ / \ 6 2 2 4
     *
     * <p>提示：
     *
     * <p>2 <= arr.length <= 40 1 <= arr[i] <= 15 答案保证是一个 32 位带符号整数，即小于 2^31。
     *
     * @param arr
     * @return
     */
    public int mctFromLeafValues(int[] arr) {
        int len = arr.length;
        int[][] dp = new int[len][len];
        int[][] maxVal = new int[len][len];
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                int max = 0;
                for (int k = i; k <= j; k++) {
                    if (max < arr[k]) {
                        max = arr[k];
                    }
                }
                maxVal[i][j] = max;
            }
        }

        // dp[i][j] 表示 i ~ j 最大的乘积
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                dp[i][j] = Integer.MAX_VALUE;
            }
        }
        for (int i = 0; i < len; i++) {
            dp[i][i] = 0;
        }

        for (int i = 1; i < len; i++) { // 长度
            for (int j = 0; j < len - i; j++) { // 起始点
                for (int k = j; k < j + i; k++) { // 中间点
                    dp[j][j + i] =
                            Math.min(
                                    dp[j][j + i],
                                    dp[j][k]
                                            + dp[k + 1][j + i]
                                            + maxVal[j][k] * maxVal[k + 1][j + i]);
                }
            }
        }

        return dp[0][len - 1];
    }

    @Test
    public void btreeGameWinningMove() {
        TreeNode root = new TreeNode(1);
        TreeNode node11 = new TreeNode(2);
        TreeNode node12 = new TreeNode(3);

        TreeNode node21 = new TreeNode(4);
        TreeNode node22 = new TreeNode(5);

        TreeNode node23 = new TreeNode(6);
        TreeNode node24 = new TreeNode(7);

        TreeNode node31 = new TreeNode(31);
        TreeNode node32 = new TreeNode(32);

        TreeNode node33 = new TreeNode(33);
        TreeNode node34 = new TreeNode(34);
        TreeNode node35 = new TreeNode(35);
        TreeNode node36 = new TreeNode(36);

        TreeNode node37 = new TreeNode(37);
        TreeNode node38 = new TreeNode(38);
        root.left = node11;
        root.right = node12;
        /*node11.left = node21;
        node11.right = node22;
        node12.left = node23;
        node12.right = node24;
        node21.left = node31;
        node21.right = node32;

        node22.left = node33;
        node22.right = node34;

        node23.left = node35;
        node23.right = node36;

        node24.left = node37;
        node24.right = node38;*/
        int n = 3, x = 1;
        logResult(btreeGameWinningMove(root, n, x));
    }

    /**
     * 1145. 二叉树着色游戏
     *
     * <p>有两位极客玩家参与了一场「二叉树着色」的游戏。游戏中，给出二叉树的根节点 root，树上总共有 n 个节点，且 n 为奇数，其中每个节点上的值从 1 到 n 各不相同。
     *
     * <p>游戏从「一号」玩家开始（「一号」玩家为红色，「二号」玩家为蓝色），最开始时，
     *
     * <p>「一号」玩家从 [1, n] 中取一个值 x（1 <= x <= n）；
     *
     * <p>「二号」玩家也从 [1, n] 中取一个值 y（1 <= y <= n）且 y != x。
     *
     * <p>「一号」玩家给值为 x 的节点染上红色，而「二号」玩家给值为 y 的节点染上蓝色。
     *
     * <p>之后两位玩家轮流进行操作，每一回合，玩家选择一个他之前涂好颜色的节点，将所选节点一个 未着色 的邻节点（即左右子节点、或父节点）进行染色。
     *
     * <p>如果当前玩家无法找到这样的节点来染色时，他的回合就会被跳过。
     *
     * <p>若两个玩家都没有可以染色的节点时，游戏结束。着色节点最多的那位玩家获得胜利 ✌️。
     *
     * <p>现在，假设你是「二号」玩家，根据所给出的输入，假如存在一个 y 值可以确保你赢得这场游戏，则返回 true；若无法获胜，就请返回 false。
     *
     * <p>示例：
     *
     * <p>输入：root = [1,2,3,4,5,6,7,8,9,10,11], n = 11, x = 3 输出：True 解释：第二个玩家可以选择值为 2 的节点。
     *
     * <p>提示：
     *
     * <p>二叉树的根节点为 root，树上由 n 个节点，节点上的值从 1 到 n 各不相同。 n 为奇数。 1 <= x <= n <= 100
     *
     * @param root
     * @param n
     * @param x
     * @return
     */
    public boolean btreeGameWinningMove(TreeNode root, int n, int x) {
        firstNum = x;
        int helf = n >> 1;
        gameWinDfs(root);
        // gameRootLeftNum gameRootRightNum > helf 选择x的子节点即可胜利
        // gameRootLeftNum + gameRootRightNum < helf 选择 x 的父节点
        if (gameRootLeftNum > helf
                || gameRootRightNum > helf
                || (gameRootLeftNum + gameRootRightNum) < helf) {
            return true;
        }

        return false;
    }

    int firstNum = 0;

    int gameRootLeftNum = 0, gameRootRightNum = 0;

    private int gameWinDfs(TreeNode root) {
        int leftNum = 0;
        int rightNum = 0;
        if (Objects.nonNull(root.left)) {
            leftNum = gameWinDfs(root.left);
        }
        if (Objects.nonNull(root.right)) {
            rightNum = gameWinDfs(root.right);
        }
        if (root.val == firstNum) {
            gameRootLeftNum = leftNum;
            gameRootRightNum = rightNum;
        }
        return leftNum + rightNum + 1;
    }

    /**
     * 1161. 最大层内元素和
     *
     * <p>给你一个二叉树的根节点 root。设根节点位于二叉树的第 1 层，而根节点的子节点位于第 2 层，依此类推。
     *
     * <p>请你找出层内元素之和 最大 的那几层（可能只有一层）的层号，并返回其中 最小 的那个。
     *
     * <p>示例：
     *
     * <p>输入：[1,7,0,7,-8,null,null] 输出：2 解释： 第 1 层各元素之和为 1， 第 2 层各元素之和为 7 + 0 = 7， 第 3 层各元素之和为 7 +
     * -8 = -1， 所以我们返回第 2 层的层号，它的层内元素之和最大。
     *
     * <p>提示：
     *
     * <p>树中的节点数介于 1 和 10^4 之间 -10^5 <= node.val <= 10^5
     *
     * @param root
     * @return
     */
    public int maxLevelSum(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();

        queue.offer(root);
        int max = Integer.MIN_VALUE;
        int maxDepth = 0, depth = 0;
        while (!queue.isEmpty()) {
            depth++;
            int sum = 0, size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                sum += node.val;
                if (Objects.nonNull(node.left)) {
                    queue.offer(node.left);
                }
                if (Objects.nonNull(node.right)) {
                    queue.offer(node.right);
                }
            }
            if (sum > max) {
                maxDepth = depth;
                max = sum;
            }
        }
        return maxDepth;
    }
}
