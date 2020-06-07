package com.qinfengsa.structure.leetcode;

import static com.qinfengsa.structure.util.LogUtils.logResult;

import com.qinfengsa.structure.util.LogUtils;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Stack;
import java.util.TreeSet;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * 二叉搜索树Test
 *
 * @author: qinfengsa
 * @date: 2019/5/15 12:40
 */
@Slf4j
public class BinSearchTreeTest {

    @Test
    public void isValidBST() {
        TreeNode root = new TreeNode(2);
        TreeNode node2 = new TreeNode(1);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(11);
        TreeNode node5 = new TreeNode(13);
        TreeNode node6 = new TreeNode(4);
        TreeNode node7 = new TreeNode(7);
        TreeNode node8 = new TreeNode(2);
        TreeNode node9 = new TreeNode(1);

        root.left = node2;
        root.right = node3;
        /* node2.left = node4;

        node3.left = node5;
        node3.right = node6;

        node4.left = node7;
        node4.right = node8;

        node6.right = node9;*/

        boolean result = isValidBST(root);

        log.debug("result:{} ", result);
    }

    /**
     * 验证二叉搜索树 给定一个二叉树，判断其是否是一个有效的二叉搜索树。
     *
     * <p>假设一个二叉搜索树具有如下特征：
     *
     * <p>节点的左子树只包含小于当前节点的数。 节点的右子树只包含大于当前节点的数。 所有左子树和右子树自身必须也是二叉搜索树。 示例 1:
     *
     * <p>输入: 2 / \ 1 3 输出: true 示例 2:
     *
     * <p>输入: 5 / \ 1 4 / \ 3 6 输出: false 解释: 输入为: [5,1,4,null,null,3,6]。 根节点的值为 5 ，但是其右子节点值为 4 。
     *
     * @param root
     * @return
     */
    public boolean isValidBST(TreeNode root) {
        // 中序遍历，
        TreeNode node = root;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode lastNode = null;
        while (Objects.nonNull(node) || !stack.isEmpty()) {

            while (Objects.nonNull(node)) {
                // 先把根结点入栈
                stack.push(node);
                node = node.left;
            }
            if (!stack.isEmpty()) {
                node = stack.pop();

                if (Objects.nonNull(lastNode)) {
                    if (lastNode.val > node.val) {
                        return false;
                    }
                }

                lastNode = node;
                // 左结点已经出栈
                node = node.right;
            }
        }
        return true;
    }

    @Test
    public void BSTIteratorTest() {
        TreeNode root = new TreeNode(7);
        TreeNode node2 = new TreeNode(3);
        TreeNode node3 = new TreeNode(15);
        TreeNode node4 = new TreeNode(9);
        TreeNode node5 = new TreeNode(20);
        TreeNode node6 = new TreeNode(4);
        TreeNode node7 = new TreeNode(7);
        TreeNode node8 = new TreeNode(2);
        TreeNode node9 = new TreeNode(1);
        root.left = node2;
        root.right = node3;
        node3.left = node4;
        node3.right = node5;
        BSTIterator iterator = new BSTIterator(root);

        int index = 1;
        log.debug("{}:{} ", index++, iterator.next()); // 返回 3
        log.debug("{}:{} ", index++, iterator.next()); // 返回 7
        log.debug("{}:{} ", index++, iterator.hasNext()); // 返回 true
        log.debug("{}:{} ", index++, iterator.next()); // 返回 9
        log.debug("{}:{} ", index++, iterator.hasNext()); // 返回 true
        log.debug("{}:{} ", index++, iterator.next()); // 返回 15
        log.debug("{}:{} ", index++, iterator.hasNext()); // 返回 true
        log.debug("{}:{} ", index++, iterator.next()); // 返回 20
        log.debug("{}:{} ", index++, iterator.hasNext()); // 返回 false
    }

    @Test
    public void searchBST() {
        TreeNode root = new TreeNode(7);
        TreeNode node2 = new TreeNode(3);
        TreeNode node3 = new TreeNode(15);
        TreeNode node4 = new TreeNode(9);
        TreeNode node5 = new TreeNode(20);
        TreeNode node6 = new TreeNode(4);
        TreeNode node7 = new TreeNode(7);
        TreeNode node8 = new TreeNode(2);
        TreeNode node9 = new TreeNode(1);
        root.left = node2;
        root.right = node3;
        node3.left = node4;
        node3.right = node5;

        TreeNode result = searchBST(root, 3);

        log.debug("result:{} ", result);
        if (Objects.nonNull(result)) {
            log.debug("val:{} ", result.val);
        }
    }

    /**
     * Search in a Binary Search Tree 给定二叉搜索树（BST）的根节点和一个值。 你需要在BST中找到节点值等于给定值的节点。 返回以该节点为根的子树。
     * 如果节点不存在，则返回 NULL。
     *
     * <p>例如，
     *
     * <p>给定二叉搜索树:
     *
     * <p>4 / \ 2 7 / \ 1 3
     *
     * <p>和值: 2 你应该返回如下子树:
     *
     * <p>2 / \ 1 3 在上述示例中，如果要找的值是 5，但因为没有节点值为 5，我们应该返回 NULL。
     *
     * @param root
     * @param val
     * @return
     */
    public TreeNode searchBST(TreeNode root, int val) {

        if (Objects.isNull(root)) {
            return null;
        }
        TreeNode node = root;
        while (Objects.nonNull(node)) {
            if (node.val == val) {
                break;
            } else if (val < node.val) {
                node = node.left;
            } else {
                node = node.right;
            }
        }
        return node;
    }

    @Test
    public void insertIntoBST() {
        TreeNode root = new TreeNode(7);
        TreeNode node2 = new TreeNode(3);
        TreeNode node3 = new TreeNode(15);
        TreeNode node4 = new TreeNode(9);
        TreeNode node5 = new TreeNode(20);
        TreeNode node6 = new TreeNode(4);
        TreeNode node7 = new TreeNode(7);
        TreeNode node8 = new TreeNode(2);
        TreeNode node9 = new TreeNode(1);
        root.left = node2;
        root.right = node3;
        node3.left = node4;
        node3.right = node5;

        TreeNode result = insertIntoBST(root, 4);

        log.debug("result:{} ", result);
        if (Objects.nonNull(result)) {
            log.debug("val:{} ", result.val);
        }
    }

    /**
     * Insert into a Binary Search Tree 给定二叉搜索树（BST）的根节点和要插入树中的值，将值插入二叉搜索树。 返回插入后二叉搜索树的根节点。
     * 保证原始二叉搜索树中不存在新值。
     *
     * <p>注意，可能存在多种有效的插入方式，只要树在插入后仍保持为二叉搜索树即可。 你可以返回任意有效的结果。
     *
     * <p>例如,
     *
     * <p>给定二叉搜索树:
     *
     * <p>4 / \ 2 7 / \ 1 3
     *
     * <p>和 插入的值: 5 你可以返回这个二叉搜索树:
     *
     * <p>4 / \ 2 7 / \ / 1 3 5 或者这个树也是有效的:
     *
     * <p>5 / \ 2 7 / \ 1 3 \ 4
     *
     * @param root
     * @param val
     * @return
     */
    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (Objects.isNull(root)) {
            return new TreeNode(val);
        }
        TreeNode node = root;
        while (Objects.nonNull(node)) {
            if (node.val == val) {
                break;
            } else if (val < node.val) {
                if (Objects.isNull(node.left)) {
                    TreeNode newNode = new TreeNode(val);
                    node.left = newNode;
                    break;
                } else {
                    node = node.left;
                }
            } else {
                if (Objects.isNull(node.right)) {
                    TreeNode newNode = new TreeNode(val);
                    node.right = newNode;
                    break;

                } else {
                    node = node.right;
                }
            }
        }
        return root;
    }

    @Test
    public void deleteNode() {
        TreeNode root = new TreeNode(5);
        TreeNode node2 = new TreeNode(3);
        TreeNode node3 = new TreeNode(6);
        TreeNode node4 = new TreeNode(2);
        TreeNode node5 = new TreeNode(4);
        TreeNode node6 = new TreeNode(7);
        TreeNode node7 = new TreeNode(7);
        TreeNode node8 = new TreeNode(2);
        TreeNode node9 = new TreeNode(1);
        root.left = node2;
        root.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.right = node6;

        TreeNode result = deleteNode(root, 3);

        log.debug("result:{} ", result);
        if (Objects.nonNull(result)) {
            log.debug("val:{} ", result.val);
        }
    }

    /**
     * Delete Node in a BST 给定一个二叉搜索树的根节点 root 和一个值 key，删除二叉搜索树中的 key
     * 对应的节点，并保证二叉搜索树的性质不变。返回二叉搜索树（有可能被更新）的根节点的引用。
     *
     * <p>一般来说，删除节点可分为两个步骤：
     *
     * <p>首先找到需要删除的节点； 如果找到了，删除它。 说明： 要求算法时间复杂度为 O(h)，h 为树的高度。
     *
     * <p>示例:
     *
     * <p>root = [5,3,6,2,4,null,7] key = 3
     *
     * <p>5 / \ 3 6 / \ \ 2 4 7
     *
     * <p>给定需要删除的节点值是 3，所以我们首先找到 3 这个节点，然后删除它。
     *
     * <p>一个正确的答案是 [5,4,6,2,null,null,7], 如下图所示。
     *
     * <p>5 / \ 4 6 / \ 2 7
     *
     * <p>另一个正确答案是 [5,2,6,null,4,null,7]。
     *
     * <p>5 / \ 2 6 \ \ 4 7
     *
     * @param root
     * @param key
     * @return
     */
    public TreeNode deleteNode(TreeNode root, int key) {
        if (Objects.isNull(root)) {
            return null;
        }

        if (root.val == key) {
            return deleteRoot(root);
        }

        TreeNode node = root;
        TreeNode lastNode = null;
        while (Objects.nonNull(node)) {
            if (node.val == key) {
                deleteOne(node, lastNode);
                break;
            } else if (key < node.val) {
                lastNode = node;
                node = node.left;
            } else {
                lastNode = node;
                node = node.right;
            }
        }
        return root;
    }

    private void deleteOne(TreeNode node, TreeNode parent) {
        if (node == parent.left) {
            TreeNode head = deleteRoot(node);
            parent.left = head;
        } else {
            TreeNode head = deleteRoot(node);
            parent.right = head;
        }
    }

    private TreeNode deleteRoot(TreeNode root) {

        if (Objects.isNull(root.left)) {
            return root.right;
        }
        if (Objects.isNull(root.right)) {
            return root.left;
        }

        TreeNode head = root.left;

        if (Objects.isNull(head.right)) {
            head.right = root.right;
        } else {
            TreeNode rightNode = head.right;
            while (Objects.nonNull(rightNode.right)) {
                rightNode = rightNode.right;
            }
            rightNode.right = root.right;
        }
        root = null;

        return head;
    }

    @Test
    public void lowestCommonAncestor() {
        TreeNode root = new TreeNode(5);
        TreeNode node2 = new TreeNode(3);
        TreeNode node3 = new TreeNode(6);
        TreeNode node4 = new TreeNode(2);
        TreeNode node5 = new TreeNode(4);
        TreeNode node6 = new TreeNode(7);
        TreeNode node7 = new TreeNode(7);
        TreeNode node8 = new TreeNode(2);
        TreeNode node9 = new TreeNode(1);
        root.left = node2;
        root.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.right = node6;

        TreeNode result = lowestCommonAncestor(root, node4, node5);

        log.debug("result:{} ", result);
        if (Objects.nonNull(result)) {
            log.debug("val:{} ", result.val);
        }
    }

    /**
     * 二叉搜索树的最近公共祖先 给定一个二叉搜索树, 找到该树中两个指定节点的最近公共祖先。
     *
     * <p>百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x
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
     * <p>所有节点的值都是唯一的。 p、q 为不同节点且均存在于给定的二叉搜索树中
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode leftNode, rightNode;
        if (p.val < q.val) {
            leftNode = p;
            rightNode = q;
        } else {
            leftNode = q;
            rightNode = p;
        }
        int leftVal = leftNode.val;
        int rightVal = rightNode.val;
        TreeNode node = root;
        while (Objects.nonNull(node)) {
            if (node.val >= leftVal && node.val <= rightVal) {
                boolean b1 = isChildNode(node, leftNode);
                boolean b2 = isChildNode(node, rightNode);
                if (b1 && b2) {
                    return node;
                } else {
                    return null;
                }

            } else if (node.val > rightVal) {
                node = node.left;
            } else if (node.val < leftVal) {
                node = node.right;
            }
        }

        return node;
    }

    private boolean isChildNode(TreeNode root, TreeNode child) {

        while (Objects.nonNull(root)) {
            if (root == child) {
                return true;
            }
            if (root.val > child.val) {
                root = root.left;
            } else {
                root = root.right;
            }
        }
        return false;
    }

    @Test
    public void isBalanced() {
        TreeNode root = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(2);
        TreeNode node4 = new TreeNode(3);
        TreeNode node5 = new TreeNode(3);
        TreeNode node6 = new TreeNode(4);
        TreeNode node7 = new TreeNode(4);
        TreeNode node8 = new TreeNode(2);
        TreeNode node9 = new TreeNode(1);
        root.left = node2;
        root.right = node3;
        node2.left = node4;
        node2.right = node5;
        node4.left = node6;
        node4.right = node7;
        boolean result = isBalanced(root);

        log.debug("result:{} ", result);
    }

    /**
     * 平衡二叉树 给定一个二叉树，判断它是否是高度平衡的二叉树。
     *
     * <p>本题中，一棵高度平衡二叉树定义为：
     *
     * <p>一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过1。
     *
     * <p>示例 1:
     *
     * <p>给定二叉树 [3,9,20,null,null,15,7]
     *
     * <p>3 / \ 9 20 / \ 15 7 返回 true 。
     *
     * <p>示例 2:
     *
     * <p>给定二叉树 [1,2,2,3,3,null,null,4,4]
     *
     * <p>1 / \ 2 2 / \ 3 3 / \ 4 4 返回 false 。
     *
     * @param root
     * @return
     */
    public boolean isBalanced(TreeNode root) {

        Integer height = getHeight(root);
        if (Objects.isNull(height)) {
            return false;
        }

        return true;
    }

    private Integer getHeight(TreeNode root) {
        if (Objects.isNull(root)) {
            return 0;
        }

        Integer leftHeight = getHeight(root.left);
        Integer rightHeight = getHeight(root.right);

        if (Objects.isNull(leftHeight) || Objects.isNull(rightHeight)) {
            return null;
        }
        Integer height;
        Integer sub;
        if (leftHeight > rightHeight) {
            sub = leftHeight - rightHeight;
            height = leftHeight;
        } else {
            sub = rightHeight - leftHeight;
            height = rightHeight;
        }
        if (sub <= 1) {
            height++;
        } else {
            return null;
        }

        return height;
    }

    @Test
    public void sortedArrayToBST() {

        int[] nums = {1, 2, 3, 5, 7, 8, 9};

        TreeNode result = sortedArrayToBST(nums);

        log.debug("result:{} ", result);
        if (Objects.nonNull(result)) {
            log.debug("val:{} ", result.val);
        }
    }

    /**
     * 将有序数组转换为二叉搜索树 将一个按照升序排列的有序数组，转换为一棵高度平衡二叉搜索树。
     *
     * <p>本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。
     *
     * <p>示例:
     *
     * <p>给定有序数组: [-10,-3,0,5,9],
     *
     * <p>一个可能的答案是：[0,-3,9,-10,null,5]，它可以表示下面这个高度平衡二叉搜索树：
     *
     * <p>0 / \ -3 9 / / -10 5
     *
     * @param nums
     * @return
     */
    public TreeNode sortedArrayToBST(int[] nums) {

        if (Objects.isNull(nums) || nums.length == 0) {
            return null;
        }
        int size = nums.length;
        TreeNode root = createRoot(nums, 0, size - 1);
        return root;
    }

    private TreeNode createRoot(int[] nums, int start, int end) {
        if (start > end) {
            return null;
        }
        if (start == end) {
            return new TreeNode(nums[start]);
        }
        // 二分法 创建结点
        int mid = start + (end - start) / 2;
        TreeNode root = new TreeNode(nums[mid]);

        TreeNode leftNode = createRoot(nums, start, mid - 1);
        TreeNode rightNode = createRoot(nums, mid + 1, end);

        root.left = leftNode;
        root.right = rightNode;
        return root;
    }

    @Test
    public void containsNearbyAlmostDuplicate() {
        int[] nums = {-1, 2147483647};
        int k = 1, t = 2147483647;
        boolean result = containsNearbyAlmostDuplicate(nums, k, t);

        log.debug("result:{} ", result);
    }

    /**
     * 存在重复元素 III 给定一个整数数组，判断数组中是否有两个不同的索引 i 和 j，使得 nums [i] 和 nums [j] 的差的绝对值最大为 t，并且 i 和 j
     * 之间的差的绝对值最大为 ķ。
     *
     * <p>示例 1:
     *
     * <p>输入: nums = [1,2,3,1], k = 3, t = 0 输出: true 示例 2:
     *
     * <p>输入: nums = [1,0,1,1], k = 1, t = 2 输出: true 示例 3:
     *
     * <p>输入: nums = [1,5,9,1,5,9], k = 2, t = 3 输出: false
     *
     * @param nums
     * @param k
     * @param t
     * @return
     */
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        /*int size = nums.length ;
        for (int i = 0; i < size; i++) {

            for (int j = 1; j <= k; j++) {
                if (i + j >= size) {
                    break;
                }
                Long a = Long.valueOf(nums[i]) ;
                Long b = Long.valueOf(nums[i+j]) ;
                Long abs =  Math.abs(a - b);
                if (abs <= t) {
                    return true;
                }

            }
        }*/
        // 构建长度为k的集合

        // 滑动窗口结合查找表，此时滑动窗口即为查找表本身（控制查找表的大小即可控制窗口大小）
        TreeSet<Long> set = new TreeSet<>();
        for (int i = 0; i < nums.length; i++) {
            // 边添加边查找
            // floor(E e) 方法返回在这个集合中小于或者等于给定元素的最大元素，如果不存在这样的元素,返回null.
            // ceiling(E e) 方法返回在这个集合中大于或者等于给定元素的最小元素，如果不存在这样的元素,返回null.

            // 查找表中是否有大于等于 nums[i] - t 且小于等于 nums[i] + t 的值
            Long ceiling = set.ceiling((long) nums[i] - (long) t);
            if (ceiling != null && ceiling <= ((long) nums[i] + (long) t)) {
                return true;
            }
            // 添加后，控制查找表（窗口）大小，移除窗口最左边元素
            set.add((long) nums[i]);
            if (set.size() == k + 1) {
                set.remove((long) nums[i - k]);
            }
        }
        return false;
    }

    @Test
    public void minDiffInBST() {
        // [90,69,null,49,89,null,52,null,null,null,null]
        TreeNode root = new TreeNode(90);
        TreeNode node2 = new TreeNode(69);
        TreeNode node3 = new TreeNode(49);
        TreeNode node4 = new TreeNode(89);
        TreeNode node5 = new TreeNode(52);
        TreeNode node6 = new TreeNode(3);
        TreeNode node7 = new TreeNode(1);
        root.left = node2;
        // root.right = node3;
        node2.left = node3;
        node2.right = node4;
        node3.right = node5;
        /*node3.right = node6;
        node5.right = node7;
        node6.left = new TreeNode(2);
        node7.left = new TreeNode(2);*/
        LogUtils.logResult(minDiffInBST(root));
    }

    /**
     * 783. 二叉搜索树结点最小距离 给定一个二叉搜索树的根结点 root，返回树中任意两节点的差的最小值。
     *
     * <p>示例：
     *
     * <p>输入: root = [4,2,6,1,3,null,null] 输出: 1 解释: 注意，root是树结点对象(TreeNode object)，而不是数组。
     *
     * <p>给定的树 [4,2,6,1,3,null,null] 可表示为下图:
     *
     * <p>4 / \ 2 6 / \ 1 3
     *
     * <p>最小的差值是 1, 它是节点1和节点2的差值, 也是节点3和节点2的差值。
     *
     * @param root
     * @return
     */
    public int minDiffInBST(TreeNode root) {
        // 这是一棵二叉搜索树, 是有序的
        // 采用中序遍历 转换成有序列表
        if (root == null) {
            return 0;
        }
        List<Integer> list = new ArrayList<>();
        inOrder(root, list);
        LogUtils.logResult(list);
        if (list.size() < 2) {
            return 0;
        }
        int min = list.get(1) - list.get(0);
        for (int i = 2; i < list.size(); i++) {
            int num = list.get(i) - list.get(i - 1);
            if (num < min) {
                min = num;
            }
        }
        return min;
        /*TreeNode node = root;
        Deque<TreeNode> stack = new LinkedList<>();
        stack.addLast(node);
        // deque.
        while (!stack.isEmpty()) {

        }



        if (root.left != null) {
            int num = root.val - root.left.val;
            if (num < minDiff) {
                minDiff = num;
            }
            minDiffInBST(root.left);
        }
        if (root.right != null) {
            int num = root.right.val - root.val;
            if (num < minDiff) {
                minDiff = num;
            }
            minDiffInBST(root.right);
        }*/

        // return minDiff;
    }

    private void inOrder(TreeNode root, List<Integer> list) {

        if (Objects.isNull(root)) {
            return;
        }
        inOrder(root.left, list);
        list.add(root.val);

        inOrder(root.right, list);
    }

    /**
     * 501. 二叉搜索树中的众数 给定一个有相同值的二叉搜索树（BST），找出 BST 中的所有众数（出现频率最高的元素）。
     *
     * <p>假定 BST 有如下定义：
     *
     * <p>结点左子树中所含结点的值小于等于当前结点的值 结点右子树中所含结点的值大于等于当前结点的值 左子树和右子树都是二叉搜索树 例如： 给定 BST [1,null,2,2],
     *
     * <p>1 \ 2 / 2 返回[2].
     *
     * <p>提示：如果众数超过1个，不需考虑输出顺序
     *
     * <p>进阶：你可以不使用额外的空间吗？（假设由递归产生的隐式调用栈的开销不被计算在内）
     *
     * @param root
     * @return
     */
    public int[] findMode(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        // 中序遍历，
        TreeNode node = root;
        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode lastNode = null;
        int count = 1;
        int max = 0;
        while (Objects.nonNull(node) || !stack.isEmpty()) {

            while (Objects.nonNull(node)) {
                // 先把根结点入栈
                stack.push(node);
                node = node.left;
            }
            if (!stack.isEmpty()) {
                node = stack.pop();

                if (Objects.nonNull(lastNode)) {
                    if (lastNode.val != node.val) {
                        if (count > max) {
                            list.clear();
                            max = count;
                            list.add(lastNode.val);
                        } else if (count == max) {
                            list.add(lastNode.val);
                        }
                        count = 1;
                    } else {
                        count++;
                    }
                }

                lastNode = node;
                // 左结点已经出栈
                node = node.right;
            }
        }

        int[] result = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            result[i] = list.get(i);
        }
        return result;
    }

    /**
     * 530. 二叉搜索树的最小绝对差 给你一棵所有节点为非负值的二叉搜索树，请你计算树中任意两节点的差的绝对值的最小值。
     *
     * <p>示例：
     *
     * <p>输入：
     *
     * <p>1 \ 3 / 2
     *
     * <p>输出： 1
     *
     * <p>解释： 最小绝对差为 1，其中 2 和 1 的差的绝对值为 1（或者 2 和 3）。
     *
     * <p>提示：
     *
     * <p>树中至少有 2 个节点。 本题与 783 https://leetcode-cn.com/problems/minimum-distance-between-bst-nodes/
     * 相同
     *
     * @param root
     * @return
     */
    public int getMinimumDifference(TreeNode root) {
        // 所有节点为非负值

        // 中序遍历，
        TreeNode node = root;
        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode lastNode = null;
        int min = Integer.MAX_VALUE;
        while (Objects.nonNull(node) || !stack.isEmpty()) {

            while (Objects.nonNull(node)) {
                // 先把根结点入栈
                stack.push(node);
                node = node.left;
            }
            if (!stack.isEmpty()) {
                node = stack.pop();

                if (Objects.nonNull(lastNode)) {
                    int sub = node.val = lastNode.val;
                    if (sub < min) {
                        min = sub;
                    }
                }

                lastNode = node;
                // 左结点已经出栈
                node = node.right;
            }
        }
        return min;
    }

    /**
     * 538. 把二叉搜索树转换为累加树 给定一个二叉搜索树（Binary Search Tree），把它转换成为累加树（Greater
     * Tree)，使得每个节点的值是原来的节点值加上所有大于它的节点值之和。
     *
     * <p>例如：
     *
     * <p>输入: 原始二叉搜索树: 5 / \ 2 13
     *
     * <p>输出: 转换为累加树: 18 / \ 20 13
     *
     * <p>注意：本题和 1038: https://leetcode-cn.com/problems/binary-search-tree-to-greater-sum-tree/ 相同
     *
     * @param root
     * @return
     */
    public TreeNode convertBST(TreeNode root) {
        // 反向中序
        if (root != null) {
            convertBST(root.right);
            sum += root.val;
            root.val = sum;
            convertBST(root.left);
        }
        return root;
        /*TreeNode node = root;
        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode lastNode = null;
        while (Objects.nonNull(node) || !stack.isEmpty()) {

            while (Objects.nonNull(node)) {
                // 先把根结点入栈
                stack.push(node);
                node = node.right;

            }
            if (!stack.isEmpty()) {
                node = stack.pop();

                if (Objects.nonNull(lastNode)) {
                    node.val += lastNode.val;
                }

                lastNode = node;
                // 右结点已经出栈
                node = node.left;
            }
        }
        return root;*/
    }

    private static int sum = 0;

    /**
     * 题目描述 评论 (121) 题解(66) 提交记录 669. 修剪二叉搜索树 给定一个二叉搜索树，同时给定最小边界L 和最大边界 R。通过修剪二叉搜索树，使得所有节点的值在[L, R]中
     * (R>=L) 。你可能需要改变树的根节点，所以结果应当返回修剪好的二叉搜索树的新的根节点。
     *
     * <p>示例 1:
     *
     * <p>输入: 1 / \ 0 2
     *
     * <p>L = 1 R = 2
     *
     * <p>输出: 1 \ 2 示例 2:
     *
     * <p>输入: 3 / \ 0 4 \ 2 / 1
     *
     * <p>L = 1 R = 3
     *
     * <p>输出: 3 / 2 / 1
     *
     * @param root
     * @param L
     * @param R
     * @return
     */
    public TreeNode trimBST(TreeNode root, int L, int R) {
        if (root == null) {
            return root;
        }
        // 最小边界L
        if (root.val < L) {
            return trimBST(root.right, L, R);
        }
        if (root.val > R) {
            return trimBST(root.left, L, R);
        }

        root.left = trimBST(root.left, L, R);
        root.right = trimBST(root.right, L, R);

        return root;
    }

    public void rangeSumBST() {
        TreeNode root = new TreeNode(10);
        int L = 7, R = 15;
        logResult(rangeSumBST(root, L, R));
    }

    /**
     * 938. 二叉搜索树的范围和 给定二叉搜索树的根结点 root，返回 L 和 R（含）之间的所有结点的值的和。
     *
     * <p>二叉搜索树保证具有唯一的值。
     *
     * <p>示例 1：
     *
     * <p>输入：root = [10,5,15,3,7,null,18], L = 7, R = 15 输出：32 示例 2：
     *
     * <p>输入：root = [10,5,15,3,7,13,18,1,null,6], L = 6, R = 10 输出：23
     *
     * <p>提示：
     *
     * <p>树中的结点数量最多为 10000 个。 最终的答案保证小于 2^31。
     *
     * @param root
     * @param L
     * @param R
     * @return
     */
    public int rangeSumBST(TreeNode root, int L, int R) {

        if (root == null) {
            return 0;
        }
        // 最小边界L
        if (root.val < L) {
            return rangeSumBST(root.right, L, R);
        }
        if (root.val > R) {
            return rangeSumBST(root.left, L, R);
        }
        rangeSum += root.val;
        rangeSumBST(root.left, L, R);
        rangeSumBST(root.right, L, R);
        return rangeSum;
    }

    static int rangeSum = 0;

    // static TreeNode head = null;

    /**
     * 面试题 17.12. BiNode 二叉树数据结构TreeNode可用来表示单向链表（其中left置空，right为下一个链表节点）。
     * 实现一个方法，把二叉搜索树转换为单向链表，要求值的顺序保持不变，转换操作应是原址的，也就是在原始的二叉搜索树上直接修改。
     *
     * <p>返回转换后的单向链表的头节点。
     *
     * <p>注意：本题相对原题稍作改动
     *
     * <p>示例：
     *
     * <p>输入： [4,2,5,1,3,null,6,0] 输出： [0,null,1,null,2,null,3,null,4,null,5,null,6] 提示：
     *
     * <p>节点数量不会超过 100000。
     *
     * @param root
     * @return
     */
    public TreeNode convertBiNode(TreeNode root) {
        if (root == null) {
            return null;
        }

        TreeNode dummy = new TreeNode(0);
        convertBiNode2(root, dummy);
        return dummy.right;
    }

    public TreeNode convertBiNode2(TreeNode root, TreeNode pre) {
        if (root == null) {
            return pre;
        }
        pre = convertBiNode2(root.left, pre);
        pre.right = root;
        root.left = null;
        pre = root;
        pre = convertBiNode2(root.right, pre);
        return pre;
    }

    /**
     * 面试题33. 二叉搜索树的后序遍历序列 输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历结果。如果是则返回 true，否则返回
     * false。假设输入的数组的任意两个数字都互不相同。
     *
     * <p>参考以下这颗二叉搜索树：
     *
     * <p>5 / \ 2 6 / \ 1 3 示例 1：
     *
     * <p>输入: [1,6,3,2,5] 输出: false 示例 2：
     *
     * <p>输入: [1,3,2,6,5] 输出: true
     *
     * <p>提示：
     *
     * <p>数组长度 <= 1000
     *
     * @param postorder
     * @return
     */
    public boolean verifyPostorder(int[] postorder) {
        int len = postorder.length;
        // 后序遍历

        return checkRoot(postorder, 0, len - 1);
    }

    private boolean checkRoot(int[] postorder, int left, int root) {
        if (left >= root) {
            return true;
        }
        int newRoot = left;
        while (postorder[newRoot] < postorder[root]) {
            newRoot++;
        }
        int rightStart = newRoot;
        while (postorder[newRoot] > postorder[root]) {
            newRoot++;
        }
        return newRoot == root
                && checkRoot(postorder, left, rightStart - 1)
                && checkRoot(postorder, rightStart, root - 1);
    }

    @Test
    public void treeToDoublyList() {
        // [90,69,null,49,89,null,52,null,null,null,null]
        TreeNode root = new TreeNode(90);
        TreeNode node2 = new TreeNode(69);
        TreeNode node3 = new TreeNode(49);
        TreeNode node4 = new TreeNode(89);
        TreeNode node5 = new TreeNode(52);
        TreeNode node6 = new TreeNode(3);
        TreeNode node7 = new TreeNode(1);
        root.left = node2;
        // root.right = node3;
        node2.left = node3;
        node2.right = node4;
        node3.right = node5;
        /*node3.right = node6;
        node5.right = node7;
        node6.left = new TreeNode(2);
        node7.left = new TreeNode(2);*/
        LogUtils.logResult(treeToDoublyList(root));
    }

    /**
     * 面试题36. 二叉搜索树与双向链表
     *
     * <p>输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的循环双向链表。要求不能创建任何新的节点，只能调整树中节点指针的指向。
     *
     * <p>为了让您更好地理解问题，以下面的二叉搜索树为例：
     *
     * <p>我们希望将这个二叉搜索树转化为双向循环链表。链表中的每个节点都有一个前驱和后继指针。对于双向循环链表， 第一个节点的前驱是最后一个节点，最后一个节点的后继是第一个节点。
     *
     * <p>下图展示了上面的二叉搜索树转化成的链表。“head” 表示指向链表中有最小元素的节点。
     *
     * <p>特别地，我们希望可以就地完成转换操作。当转化完成以后，树中节点的左指针需要指向前驱，树中节点的右指针需要指向后继。 还需要返回链表中的第一个节点的指针。
     *
     * <p>注意：本题与主站 426 题相同：
     * https://leetcode-cn.com/problems/convert-binary-search-tree-to-sorted-doubly-linked-list/
     *
     * <p>注意：此题对比原题有改动。
     *
     * @param root
     * @return
     */
    public TreeNode treeToDoublyList(TreeNode root) {
        // 中序遍历
        if (Objects.isNull(root)) {
            return null;
        }
        List<TreeNode> list = new ArrayList<>();
        inTreeToDoublyList(root, list);

        for (int i = 0; i < list.size(); i++) {
            TreeNode node = list.get(i);
            int left = i == 0 ? list.size() - 1 : i - 1;
            int right = i == list.size() - 1 ? 0 : i + 1;
            node.left = list.get(left);
            node.right = list.get(right);
        }
        return list.get(0);
    }

    private void inTreeToDoublyList(TreeNode root, List<TreeNode> list) {
        if (Objects.isNull(root)) {
            return;
        }
        inTreeToDoublyList(root.left, list);
        list.add(root);

        inTreeToDoublyList(root.right, list);
    }

    @Test
    public void isValidBST2() {
        TreeNode root = new TreeNode(2);
        TreeNode node2 = new TreeNode(1);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(11);
        TreeNode node5 = new TreeNode(13);
        TreeNode node6 = new TreeNode(4);
        TreeNode node7 = new TreeNode(7);
        TreeNode node8 = new TreeNode(2);
        TreeNode node9 = new TreeNode(1);

        root.left = node2;
        root.right = node3;
        /*node2.left = node4;

        node3.left = node5;
        node3.right = node6;

        node4.left = node7;
        node4.right = node8;

        node6.right = node9;*/

        boolean result = isValidBST2(root);

        log.debug("result:{} ", result);
    }

    /**
     * 面试题 04.05. 合法二叉搜索树
     *
     * <p>实现一个函数，检查一棵二叉树是否为二叉搜索树。
     *
     * <p>示例 1: 输入: 2 / \ 1 3 输出: true
     *
     * <p>示例 2: 输入: 5 / \ 1 4 / \ 3 6 输出: false
     *
     * <p>解释: 输入为: [5,1,4,null,null,3,6]。 根节点的值为 5 ，但是其右子节点值为 4 。
     *
     * @param root
     * @return
     */
    public boolean isValidBST2(TreeNode root) {
        // 中序遍历 使用栈
        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode lastNode = null;
        TreeNode node = root;
        while (Objects.nonNull(node) || !stack.isEmpty()) {
            while (Objects.nonNull(node)) {
                // 先把根结点入栈
                stack.push(node);
                node = node.left;
            }
            if (!stack.isEmpty()) {
                node = stack.pop();

                /*if (Objects.nonNull(lastNode)) {
                    if (lastNode.val >= node.val) {
                        return false;
                    }
                }*/
                log.debug("val:{}", node.val);
                lastNode = node;
                // 左结点已经出栈
                node = node.right;
            }
        }

        return true;
        /*if (Objects.isNull(root)) {
            return true;
        }
        if (!isValidBST2(root.left)) {
            return false;
        }
        if (Objects.nonNull(validLastNode) && validLastNode.val >= root.val) {
            return false;
        }
        validLastNode = root;
        return isValidBST2(root.right);*/
    }

    private TreeNode validLastNode = null;

    @Test
    public void inorderSuccessor() {
        TreeNode root = new TreeNode(5);
        TreeNode node2 = new TreeNode(3);
        TreeNode node3 = new TreeNode(7);
        TreeNode node4 = new TreeNode(2);
        TreeNode node5 = new TreeNode(4);
        TreeNode node6 = new TreeNode(1);
        TreeNode node7 = new TreeNode(6);
        TreeNode node8 = new TreeNode(8);
        TreeNode node9 = new TreeNode(1);

        root.left = node2;
        root.right = node3;
        node2.left = node4;
        node2.right = node5;
        node4.left = node6;

        node3.left = node7;
        node3.right = node8;

        TreeNode result = inorderSuccessor(root, node6);

        log.debug("result:{} ", result.val);
    }

    /**
     * 面试题 04.06. 后继者
     *
     * <p>设计一个算法，找出二叉搜索树中指定节点的“下一个”节点（也即中序后继）。
     *
     * <p>如果指定节点没有对应的“下一个”节点，则返回null。
     *
     * <p>示例 1:
     *
     * <p>输入: root = [2,1,3], p = 1
     *
     * <p>2 / \ 1 3
     *
     * <p>输出: 2 示例 2:
     *
     * <p>输入: root = [5,3,6,2,4,null,null,1], p = 6
     *
     * <p>5 / \ 3 6 / \ 2 4 / 1
     *
     * <p>输出: null
     *
     * @param root
     * @param p
     * @return
     */
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        // 逆中序遍历
        inorderSuccessor2(root, p);
        return validLastNode;
    }

    private void inorderSuccessor2(TreeNode root, TreeNode p) {
        if (Objects.isNull(root)) {
            return;
        }
        inorderSuccessor(root.right, p);
        if (root == p) {
            return;
        }
        if (root.val < p.val) {
            return;
        }
        log.debug("val:{}", root.val);
        validLastNode = root;
        inorderSuccessor(root.left, p);
    }
}
