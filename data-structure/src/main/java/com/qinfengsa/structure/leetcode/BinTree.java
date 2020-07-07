package com.qinfengsa.structure.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Queue;
import java.util.Stack;

/**
 * @author: qinfengsa
 * @date: 2019/5/13 19:05
 */
public class BinTree {

    /**
     * 前序遍历 DLR 前序遍历（DLR）二叉树的操作定义为： 若二叉树为空，则空操作；否则 ① 访问根结点； ② 前序遍历左子树； ③ 前序遍历右子树。
     *
     * @param root
     * @return
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (Objects.isNull(root)) {
            return result;
        }

        preOrder(root, result);
        return result;
    }

    /**
     * 前序遍历 DLR 非递归算法 得用栈解决
     *
     * @param root
     * @return
     */
    public List<Integer> preorderTraversal2(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (Objects.isNull(root)) {
            return result;
        }
        TreeNode node = root;

        Stack<TreeNode> stack = new Stack<>();
        while (Objects.nonNull(node)) {
            while (Objects.nonNull(node)) {

                result.add(node.val);
                // 先放进去的右结点最后遍历
                if (Objects.nonNull(node.right)) {
                    stack.push(node.right);
                }
                node = node.left;
            }
            if (!stack.isEmpty()) {
                node = stack.pop();
            }
        }

        return result;
    }

    /**
     * 前序遍历，递归
     *
     * @param root
     * @param list
     */
    private void preOrder(TreeNode root, List<Integer> list) {

        if (Objects.isNull(root)) {
            return;
        }
        list.add(root.val);
        preOrder(root.left, list);
        preOrder(root.right, list);
    }

    /**
     * 中序遍历，非递归
     *
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal2(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (Objects.isNull(root)) {
            return list;
        }

        TreeNode node = root;

        Stack<TreeNode> stack = new Stack<>();
        while (Objects.nonNull(node) || !stack.isEmpty()) {
            while (Objects.nonNull(node)) {
                // 先把根结点入栈
                stack.push(node);
                node = node.left;
            }
            if (!stack.isEmpty()) {
                node = stack.pop();
                list.add(node.val);

                // 左结点已经出栈
                node = node.right;
            }
        }

        return list;
    }

    /**
     * 中序遍历 中序遍历（LDR）二叉树的操作定义为： 若二叉树为空，则空操作； 否则 ① 中序遍历左子树； ② 访问根结点； ③ 中序遍历右子树。
     *
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (Objects.isNull(root)) {
            return list;
        }
        inOrder(root, list);

        return list;
    }

    /**
     * 中序遍历 递归
     *
     * @param root
     * @param list
     */
    private void inOrder(TreeNode root, List<Integer> list) {

        if (Objects.isNull(root)) {
            return;
        }
        inOrder(root.left, list);
        list.add(root.val);

        inOrder(root.right, list);
    }

    /**
     * 后序遍历，非递归
     *
     * @param root
     * @return
     */
    public List<Integer> postorderTraversal2(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (Objects.isNull(root)) {
            return list;
        }

        TreeNode node = root;

        Stack<TreeNode> stack = new Stack<>();
        while (Objects.nonNull(node) || !stack.isEmpty()) {
            while (Objects.nonNull(node)) {
                // 先把根结点入栈
                stack.push(node);
                // 先左后右不断深入

                if (Objects.nonNull(node.left)) {
                    node = node.left;

                } else {
                    node = node.right;
                }
            }
            if (!stack.isEmpty()) {
                // 取出栈顶根结点访问
                node = stack.pop();
                list.add(node.val);
            }

            // 满足条件时，说明栈顶根节点右子树已访问，应出栈访问
            while (!stack.isEmpty() && (stack.peek().right == node)) {
                node = stack.pop();
                list.add(node.val);
            }
            // 转向栈顶根结点的右子树继续后序遍历
            if (!stack.isEmpty()) {
                node = stack.peek().right;
            } else {
                node = null;
            }
        }

        return list;
    }

    /**
     * 后序遍历 后序遍历（LRD）二叉树的操作定义为： 若二叉树为空，则空操作；否则 ① 后序遍历左子树； ② 后序遍历右子树； ③ 访问根结点。
     *
     * @param root
     * @return
     */
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (Objects.isNull(root)) {
            return list;
        }
        postOrder(root, list);

        return list;
    }

    /**
     * 后序遍历 递归
     *
     * @param root
     * @param list
     */
    private void postOrder(TreeNode root, List<Integer> list) {
        if (Objects.isNull(root)) {
            return;
        }
        postOrder(root.left, list);
        postOrder(root.right, list);
        list.add(root.val);
    }

    /**
     * 二叉树的层次遍历 给定一个二叉树，返回其按层次遍历的节点值。 （即逐层地，从左到右访问所有节点）。
     *
     * <p>例如: 给定二叉树: [3,9,20,null,null,15,7],
     *
     * <p>3 / \ 9 20 / \ 15 7 返回其层次遍历结果：
     *
     * <p>[ [3], [9,20], [15,7] ]
     *
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder(TreeNode root) {

        List<List<Integer>> list = new ArrayList<>();
        if (Objects.isNull(root)) {
            return list;
        }

        TreeNode last = root;
        TreeNode layerLast = null;

        List<Integer> layer = new ArrayList<>();

        // 使用队列完成二叉树的按层遍历
        Queue<TreeNode> queue = new LinkedList<>();
        // 根结点入队
        queue.offer(root);
        while (!queue.isEmpty()) {
            // 取出队首结点 node
            TreeNode node = queue.poll();
            layer.add(node.val);

            // 左右子结点分别入队
            if (Objects.nonNull(node.left)) {
                queue.offer(node.left);
                layerLast = node.left;
            }
            if (Objects.nonNull(node.right)) {
                queue.offer(node.right);
                layerLast = node.right;
            }
            // layerLast 记录每行最后的结点
            // last 记录每行最后的结点
            if (last == node) {
                last = layerLast;
                list.add(layer);
                layer = new ArrayList<Integer>();
            }
        }

        return list;
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
     *
     * @param root
     * @return
     */
    public int maxDepth(TreeNode root) {
        if (Objects.isNull(root)) {
            return 0;
        }
        int height = getHeight(root);

        return height;
    }

    private int getHeight(TreeNode root) {
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

        int height = leftHeight > rightHeight ? leftHeight : rightHeight;

        height++;
        return height;
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
     *
     * @param root
     * @return
     */
    public boolean isSymmetric(TreeNode root) {
        if (Objects.isNull(root)) {
            return true;
        }
        boolean b = isSymmetric(root.left, root.right);
        return b;
    }

    private boolean isSymmetric(TreeNode leftNode, TreeNode rightNode) {
        if (Objects.isNull(leftNode) && Objects.isNull(rightNode)) {
            return true;
        } else if (Objects.isNull(leftNode) || Objects.isNull(rightNode)) {
            return false;
        }
        boolean b1 = leftNode.val == rightNode.val;

        boolean b2 = isSymmetric(leftNode.left, rightNode.right);

        boolean b3 = isSymmetric(leftNode.right, rightNode.left);

        boolean b = b1 && b2 && b3;
        return b;
    }

    /**
     * 路径总和 给定一个二叉树和一个目标和，判断该树中是否存在根节点到叶子节点的路径，这条路径上所有节点值相加等于目标和。
     *
     * <p>说明: 叶子节点是指没有子节点的节点。
     *
     * <p>示例: 给定如下二叉树，以及目标和 sum = 22，
     *
     * <p>5 / \ 4 8 / / \ 11 13 4 / \ \ 7 2 1 返回 true, 因为存在目标和为 22 的根节点到叶子节点的路径 5->4->11->2。
     *
     * @param root
     * @param sum
     * @return
     */
    public boolean hasPathSum(TreeNode root, int sum) {
        if (Objects.isNull(root)) {
            return false;
        }
        int val = root.val;

        if (Objects.isNull(root.left) && Objects.isNull(root.right)) {
            if (val == sum) {
                pathSumResult = true;
                return true;
            }
            return false;
        }
        if (Objects.nonNull(root.left)) {
            hasPathSum(root.left, sum - val);
        }
        if (Objects.nonNull(root.right)) {
            hasPathSum(root.right, sum - val);
        }
        return pathSumResult;
    }

    private boolean pathSumResult;

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
     *
     * @param inorder
     * @param postorder
     * @return
     */
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        int size = postorder.length;
        if (inorder.length != size) {
            return null;
        }
        if (size == 0) {
            return null;
        }
        // root结点
        int rootVal = postorder[size - 1];
        TreeNode root = new TreeNode(rootVal);

        int leftSize = 0;

        // 中序遍历反遍历
        for (int i = 0; i < inorder.length; i++) {
            if (rootVal == inorder[i]) {
                leftSize = i;
            }
        }

        int rightSize = size - leftSize - 1;

        // 左子树中序遍历
        int[] inLeft = new int[leftSize];
        // 左子树后序遍历
        int[] postLeft = new int[leftSize];
        // 右子树中序遍历
        int[] inRight = new int[rightSize];
        // 右子树后序遍历
        int[] postRight = new int[rightSize];

        // 中序遍历反遍历
        for (int i = 0; i < inorder.length; i++) {

            if (i < leftSize) {
                inLeft[i] = inorder[i];
            } else if (i > leftSize) {
                inRight[i - leftSize - 1] = inorder[i];
            }
        }

        // 后序遍历反遍历
        // 中序遍历和后序遍历右子树起点相同
        for (int i = 0; i < size - 1; i++) {

            if (i < leftSize) {
                postLeft[i] = postorder[i];
            } else {
                postRight[i - leftSize] = postorder[i];
            }
        }
        TreeNode leftNode = null;
        if (leftSize > 0) {
            leftNode = buildTree(inLeft, postLeft);
        }
        TreeNode rightNode = null;
        if (rightSize > 0) {
            rightNode = buildTree(inRight, postRight);
        }
        root.left = leftNode;
        root.right = rightNode;

        return root;
    }

    /**
     * 从前序与中序遍历序列构造二叉树 根据一棵树的前序遍历与中序遍历构造二叉树。
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
    public TreeNode buildTree2(int[] preorder, int[] inorder) {
        int size = preorder.length;
        if (inorder.length != size) {
            return null;
        }
        if (size == 0) {
            return null;
        }
        // root结点
        int rootVal = preorder[0];
        TreeNode root = new TreeNode(rootVal);

        int leftSize = 0;

        // 中序遍历
        for (int i = 0; i < inorder.length; i++) {
            if (rootVal == inorder[i]) {
                leftSize = i;
            }
        }

        int rightSize = size - leftSize - 1;

        // 左子树中序遍历
        int[] inLeft = new int[leftSize];
        // 左子树前序遍历
        int[] preLeft = new int[leftSize];
        // 右子树中序遍历
        int[] inRight = new int[rightSize];
        // 右子树前序遍历
        int[] preRight = new int[rightSize];

        // 中序遍历
        for (int i = 0; i < inorder.length; i++) {
            if (i < leftSize) {
                inLeft[i] = inorder[i];
            } else if (i > leftSize) {
                inRight[i - leftSize - 1] = inorder[i];
            }
        }

        // 前序遍历
        for (int i = 1; i < size; i++) {
            if (i - 1 < leftSize) {
                preLeft[i - 1] = preorder[i];
            } else {
                preRight[i - leftSize - 1] = preorder[i];
            }
        }
        TreeNode leftNode = null;
        if (leftSize > 0) {
            leftNode = buildTree2(preLeft, inLeft);
        }
        TreeNode rightNode = null;
        if (rightSize > 0) {
            rightNode = buildTree2(preRight, inRight);
        }
        root.left = leftNode;
        root.right = rightNode;

        return root;
    }

    /**
     * 二叉树的最近公共祖先 给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
     *
     * <p>百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x
     * 的深度尽可能大（一个节点也可以是它自己的祖先）。”
     *
     * <p>例如，给定如下二叉树: root = [3,5,1,6,2,0,8,null,null,7,4] 示例 1:
     *
     * <p>输入: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1 输出: 3 解释: 节点 5 和节点 1 的最近公共祖先是节点 3。
     * 示例 2:
     *
     * <p>输入: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4 输出: 5 解释: 节点 5 和节点 4 的最近公共祖先是节点
     * 5。因为根据定义最近公共祖先节点可以为节点本身。
     *
     * <p>说明:
     *
     * <p>所有节点的值都是唯一的。 p、q 为不同节点且均存在于给定的二叉树中。
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        //
        if (root == null || root == p || root == q) {
            return root;
        }
        TreeNode leftNode = lowestCommonAncestor(root.left, p, q);
        TreeNode rightNode = lowestCommonAncestor(root.right, p, q);

        // 左右子树都找到
        if (Objects.nonNull(leftNode) && Objects.nonNull(rightNode)) {
            return root;
        }

        return Objects.nonNull(leftNode) ? leftNode : rightNode;
    }

    /**
     * 序列化
     *
     * @param root
     * @return
     */
    public String serialize(TreeNode root) {
        if (Objects.isNull(root)) {
            return "";
        }

        StringBuilder sb = serializeCode(root);
        return sb.toString();
    }

    /**
     * 先序遍历序列化
     *
     * @param root
     * @return
     */
    private StringBuilder serializeCode(TreeNode root) {

        if (Objects.isNull(root)) {
            return new StringBuilder("#,");
        }
        StringBuilder sb = new StringBuilder();
        sb.append(root.val);
        sb.append(",");
        sb.append(serializeCode(root.left));
        sb.append(serializeCode(root.right));

        return sb;
    }

    /**
     * 反序列化
     *
     * @param data
     * @return
     */
    public TreeNode deserialize(String data) {
        if (Objects.equals(data, "")) {
            return null;
        }
        String[] values = data.split(",");

        Queue<String> queue = new LinkedList<>();
        for (String value : values) {
            queue.offer(value);
        }

        TreeNode root = deserializeCode(queue);

        return root;
    }

    private TreeNode deserializeCode(Queue<String> queue) {
        if (queue.isEmpty()) {
            return null;
        }
        String value = queue.poll();
        if (Objects.equals(value, "#")) {
            return null;
        }
        TreeNode root = new TreeNode(Integer.valueOf(value));

        root.left = deserializeCode(queue);
        root.right = deserializeCode(queue);

        return root;
    }
}
